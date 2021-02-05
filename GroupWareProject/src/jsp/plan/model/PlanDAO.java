package jsp.plan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.common.util.DBConnection;

public class PlanDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public PlanDAO(){
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/sign");
		} catch (Exception e) {
			System.out.println("plan dao e : "+e);
			return;
		}
		
	}
	//리스트
	public int getListCount() {
		int x= 0;
		
		try {
			conn=ds.getConnection();
			System.out.println("getConnection");
			
			pstmt=conn.prepareStatement("select count(*) from gw_plan_board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return x;
	}
	
	public List<PlanVO> getplanList(int page, int limit) {
		String plan_list_sql="select * from " + 
				"(select rownum rnum,t2.plan_num, t2.plan_title, t1.member_name, t2.plan_writedate, t2.plan_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_plan_board t2 on t1.emp_num=t2.emp_num order by t2.plan_num desc) " + 
				"WHERE rnum>=? and rnum<=?";
		
		List<PlanVO> list = new ArrayList<PlanVO>();
		
		int startrow=(page-1)*10+1;//이게뭐지?
		int endrow=startrow+limit-1;//이것도뭐지
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(plan_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PlanVO bvo = new PlanVO();
				bvo.setplan_num(rs.getInt("plan_num"));
				bvo.setplan_title(rs.getString("plan_title"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setplan_writedate(rs.getString("plan_writedate"));
				bvo.setplan_readcount(rs.getString("plan_readcount"));
				bvo.setMember_team(rs.getString("member_team"));
				list.add(bvo);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getplanList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	//등록 이름, 팀명
	public PlanVO getNameTeam(String empNum) {
		PlanVO teamVO = new PlanVO();
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement("select member_name from gw_member where emp_num='?'");
			pstmt.setString(1, empNum);
			rs=pstmt.executeQuery();
			
			
			while(rs.next()) {
				teamVO.setMember_name(rs.getString("member_name"));
				teamVO.setMember_team(rs.getString("member_team"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return teamVO;	
	}
	//등록 
	public boolean insertplan(PlanVO bvo) {
		
		int num = 0;
		String sql="";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(plan_num) from gw_plan_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			sql="insert into gw_plan_board(plan_num,emp_num,plan_title,plan_content,plan_readcount,gw_plan_file)"
					+ " values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bvo.getEmp_num());
			pstmt.setString(3, bvo.getplan_title());
			pstmt.setString(4, bvo.getplan_content());
			pstmt.setInt(5, 0);
			pstmt.setString(6, bvo.getgw_plan_file());
			
			result=pstmt.executeUpdate();
			if(result==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("planInsert : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//조회수
	public void setReadCountUpdate(int num)throws Exception{
		
		String sql="update gw_plan_board set plan_READCOUNT = "+
		"plan_readcount+1 where plan_num = "+num;
		
		try {
			conn= ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("setReadCountUpdate 조회수 : "+e);
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				}
				catch(Exception e){}
		}
		
	}
	//글의 내용 상세보기 출력
	public PlanVO getDetail(int num) throws Exception{
		PlanVO bvo = null;
		try {
			conn = ds.getConnection();
			pstmt = 
			conn.prepareStatement("select * from gw_plan_board where plan_num = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				bvo = new PlanVO();
				bvo.setplan_num(num);
				bvo.setplan_title(rs.getString("plan_title"));
				bvo.setplan_content(rs.getString("plan_content"));
				bvo.setplan_writedate(rs.getString("plan_writedate"));
				bvo.setgw_plan_file(rs.getString("gw_plan_file"));
				}
			return bvo;
		}catch (Exception e) {
			System.out.println("getDetail 상세보기 출력 : "+e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	
	//수정
	public boolean planModify(PlanVO bvo) throws Exception{
		
		String sql="update gw_plan_board set plan_title=?,"
				+ " plan_content=?,gw_plan_file=? where plan_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getplan_title());
			pstmt.setString(2, bvo.getplan_content());
			pstmt.setString(3, bvo.getgw_plan_file());
			pstmt.setInt(4,bvo.getplan_num());
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("planModify : "+ e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//삭제
	public boolean planDelete(int num) {
		String plan_delete_sql="delete from gw_plan_board where plan_num=?";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(plan_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
			
		} catch (Exception e) {
			System.out.println("planDelete 지우기 : "+e);
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				}
				catch(Exception ex){}
		}
		return false;
	}
	//getmemberteaminpo
	public String getmemberteaminpo(String emp_num) {
		String sql="select member_team from gw_member where emp_num=?";
		String memberteam="";
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_num);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				memberteam=rs.getString("member_team");
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				if(rs!=null)rs.close();
				}
				catch(Exception ex){}			
		}
		return memberteam;
	}
	public String getmemberRank(String emp_num) {
		String sql="select member_rank from gw_member where emp_num=?";
		String memberrank="";
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_num);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				memberrank=rs.getString("member_rank");
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				if(rs!=null)rs.close();
				}
				catch(Exception ex){}			
		}
		return memberrank;
	}
	
	
	//페이지기능구현
	 public ArrayList<PlanVO> getListplan(int startRow, int endRow) {
		 
		    ArrayList<PlanVO> list = new ArrayList<PlanVO>();
		     String sql="";
		    try {
		      conn = ds.getConnection();
		       
		      sql = "select * from " + 
		      		"(select rownum rnum,t2.plan_num, t2.plan_title, t1.member_name, t2.plan_writedate, t2.plan_readcount, t1.member_team " + 
		      		"from gw_member t1 inner join gw_plan_board t2 on t1.emp_num=t2.emp_num order by t2.plan_num desc)" + 
		      		"WHERE rnum>=? and rnum<=?";

		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1, startRow);
		      pstmt.setInt(2, endRow);
		      rs = pstmt.executeQuery();
		       
		      while(rs.next()) {
		        
		        PlanVO dto = new PlanVO();
		        dto .setplan_num(rs.getInt("plan_num"));
		        dto .setplan_title(rs.getString("plan_title"));
		        dto .setplan_readcount(rs.getString("plan_readcount"));
		        dto .setplan_writedate(rs.getString("plan_writedate"));
		        dto.setMember_team(rs.getString("member_team"));
		        dto.setMember_name(rs.getString("member_name"));
		        list.add(dto);
		      }
		       
		    } catch (Exception e){
		      e.printStackTrace();
		    } finally {
		    	try{
					if(pstmt!=null)pstmt.close();
					if(conn!=null) conn.close();
					if(rs!=null)rs.close();
					}
					catch(Exception ex){}	
		    }
		    return list;
		  }
}
