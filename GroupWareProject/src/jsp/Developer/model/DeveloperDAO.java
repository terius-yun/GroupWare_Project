package jsp.Developer.model;

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

public class DeveloperDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DeveloperDAO(){
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/sign");
		} catch (Exception e) {
			System.out.println("Developer dao e : "+e);
			return;
		}
		
	}
	//리스트
	public int getListCount() {
		int x= 0;
		
		try {
			conn=ds.getConnection();
			System.out.println("getConnection");
			
			pstmt=conn.prepareStatement("select count(*) from gw_Developer_board");
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
	
	public List<DeveloperVO> getDeveloperList(int page, int limit) {
		String Developer_list_sql="select * from " + 
				"(select rownum rnum,t2.Developer_num, t2.Developer_title, t1.member_name, t2.Developer_writedate, t2.Developer_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_Developer_board t2 on t1.emp_num=t2.emp_num order by t2.Developer_num desc) " + 
				"WHERE rnum>=? and rnum<=?";
		
		List<DeveloperVO> list = new ArrayList<DeveloperVO>();
		
		int startrow=(page-1)*10+1;
		int endrow=startrow+limit-1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(Developer_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeveloperVO bvo = new DeveloperVO();
				bvo.setdeveloper_num(rs.getInt("developer_num"));
				bvo.setdeveloper_title(rs.getString("developer_title"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setdeveloper_writedate(rs.getString("developer_writedate"));
				bvo.setdeveloper_readcount(rs.getString("developer_readcount"));
				bvo.setMember_team(rs.getString("member_team"));
				list.add(bvo);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getDeveloperList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	//등록 이름, 팀명
	public DeveloperVO getNameTeam(String empNum) {
		DeveloperVO teamVO = new DeveloperVO();
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
	public boolean insertDeveloper(DeveloperVO bvo) {
		
		int num = 0;
		String sql="";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(Developer_num) from gw_Developer_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			sql="insert into gw_Developer_board(Developer_num,emp_num,Developer_title,Developer_content,Developer_readcount,gw_Developer_file)"
					+ " values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bvo.getEmp_num());
			pstmt.setString(3, bvo.getdeveloper_title());
			pstmt.setString(4, bvo.getdeveloper_content());
			pstmt.setInt(5, 0);
			pstmt.setString(6, bvo.getgw_developer_file());
			
			result=pstmt.executeUpdate();
			if(result==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("developerInsert : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//조회수
	public void setReadCountUpdate(int num)throws Exception{
		
		String sql="update gw_developer_board set developer_READCOUNT = "+
		"Developer_readcount+1 where Developer_num = "+num;
		
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
	public DeveloperVO getDetail(int num) throws Exception{
		DeveloperVO bvo = null;
		try {
			conn = ds.getConnection();
			pstmt = 
			conn.prepareStatement("select * from gw_Developer_board where Developer_num = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				bvo = new DeveloperVO();
				bvo.setdeveloper_num(num);
				bvo.setdeveloper_title(rs.getString("developer_title"));
				bvo.setdeveloper_content(rs.getString("developer_content"));
				bvo.setdeveloper_writedate(rs.getString("developer_writedate"));
				bvo.setgw_developer_file(rs.getString("gw_developer_file"));
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
	public boolean DeveloperModify(DeveloperVO bvo) throws Exception{
		
		String sql="update gw_Developer_board set Developer_title=?,"
				+ " Developer_content=?,gw_developer_file=? where Developer_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getdeveloper_title());
			pstmt.setString(2, bvo.getdeveloper_content());
			pstmt.setString(3, bvo.getgw_developer_file());
			pstmt.setInt(4,bvo.getdeveloper_num());
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("DeveloperModify : "+ e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//삭제
	public boolean DeveloperDelete(int num) {
		String Developer_delete_sql="delete from gw_Developer_board where Developer_num=?";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(Developer_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
			
		} catch (Exception e) {
			System.out.println("DeveloperDelete 지우기 : "+e);
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
	 public ArrayList<DeveloperVO> getListDeveloper(int startRow, int endRow) {
		 
		    ArrayList<DeveloperVO> list = new ArrayList<DeveloperVO>();
		     String sql="";
		    try {
		      conn = ds.getConnection();
		       
		      sql = "select * from " + 
		      		"(select rownum rnum,t2.Developer_num, t2.Developer_title, t1.member_name, t2.Developer_writedate, t2.Developer_readcount, t1.member_team " + 
		      		"from gw_member t1 inner join gw_Developer_board t2 on t1.emp_num=t2.emp_num order by t2.Developer_num desc)" + 
		      		"WHERE rnum>=? and rnum<=?";

		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1, startRow);
		      pstmt.setInt(2, endRow);
		      rs = pstmt.executeQuery();
		       
		      while(rs.next()) {
		        
		        DeveloperVO dto = new DeveloperVO();
		        dto .setdeveloper_num(rs.getInt("developer_num"));
		        dto .setdeveloper_title(rs.getString("developer_title"));
		        dto .setdeveloper_readcount(rs.getString("developer_readcount"));
		        dto .setdeveloper_writedate(rs.getString("Developer_writedate"));
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
