package jsp.Design.model;

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

public class DesignDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DesignDAO(){
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/sign");
		} catch (Exception e) {
			System.out.println("Design dao e : "+e);
			return;
		}
		
	}
	//리스트
	public int getListCount() {
		int x= 0;
		System.out.println("getListCount--------------");
		try {
			conn=ds.getConnection();
			System.out.println("getConnection : "+conn);
			//pstmt 문제될수있음
			pstmt=conn.prepareStatement("select count(*) from gw_Design_board");
			
			rs = pstmt.executeQuery();
			System.out.println("rs : "+rs.toString());
			if(rs.next()) {
				x=rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("getListCount e : " + e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		System.out.println("getListCount--------------");
		return x;
	}
	
	public List getDesignList(int page, int limit) {
		String board_list_sql="select * from " + 
				"(select rownum rnum,t2.Design_num, t2.Design_title, t1.member_name, t2.Design_writedate, t2.Design_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_Design_board t2 on t1.emp_num=t2.emp_num order by t2.Design_num desc) " + 
				"WHERE rnum>=? and rnum<=?";
		
		List<DesignVO> list = new ArrayList<DesignVO>();
		
		int startrow=(page-1)*10+1;//이게뭐지?
		int endrow=startrow+limit-1;//이것도뭐지
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DesignVO bvo = new DesignVO();
				bvo.setDESIGN_NUM(rs.getInt("DESIGN_NUM"));
				bvo.setDESIGN_TITLE(rs.getString("DESIGN_TITLE"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setDESIGN_WRITEDATE(rs.getString("DESIGN_WRITEDATE"));
				bvo.setDESIGN_READCOUNT(rs.getString("DESIGN_READCOUNT"));
				bvo.setMember_team(rs.getString("member_team"));
				list.add(bvo);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getDesignList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	//등록 이름, 팀명
	public DesignVO getNameTeam(String empNum) {
		DesignVO teamVO = new DesignVO();
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement("select member_name from gw_Design_board where emp_num='?'");
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
	public boolean insertDesign(DesignVO bvo) {
		
		int num = 0;
		String sql="";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(Design_num) from gw_Design_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			sql="insert into gw_board(Design_num,emp_num,Design_title,Design_content,Design_readcount,gw_Design_file)"
					+ " values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bvo.getEMP_NUM());
			pstmt.setString(3, bvo.getDESIGN_TITLE());
			pstmt.setString(4, bvo.getDESIGN_CONTENT());
			pstmt.setInt(5, 0);
			pstmt.setString(6, bvo.getDESIGN_FILE());
			
			result=pstmt.executeUpdate();
			if(result==0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("boardInsert : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//조회수
	public void setReadCountUpdate(int num)throws Exception{
		
		String sql="update GW_Design_BOARD set Design_READCOUNT = "+
		"Design_readcount+1 where Design_num = "+num;
		
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
	public DesignVO getDetail(int num) throws Exception{
		DesignVO bvo = null;
		try {
			conn = ds.getConnection();
			pstmt = 
			conn.prepareStatement("select * from gw_Design_BOARD where Design_num = ?");
			pstmt.setInt(1, num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				bvo = new DesignVO();
				bvo.setDESIGN_NUM(num);//Design
				bvo.setDESIGN_TITLE(rs.getString("DESIGN_TITLE"));
				bvo.setDESIGN_CONTENT(rs.getString("DESIGN_CONTENT"));
				bvo.setDESIGN_WRITEDATE(rs.getString("DESIGN_WRITEDATE"));
				bvo.setDESIGN_FILE(rs.getString("DESIGN_FILE"));
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
	public boolean DesignModify(DesignVO bvo) throws Exception{
		
		String sql="update GW_Design_BOARD set Design_title=?,"
				+ " Design_content=?,gw_Design_file=? where Design_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getDESIGN_TITLE());
			pstmt.setString(2, bvo.getDESIGN_CONTENT());
			pstmt.setString(3, bvo.getDESIGN_FILE());
			pstmt.setInt(4,bvo.getDESIGN_NUM());
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("boardModify : "+ e);
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return false;
	}
	//삭제
	public boolean DesignDelete(int num) {
		String board_delete_sql="delete from gw_Design_BOARD where Design_num=?";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			if(result==0)return false;
			
			return true;
			
		} catch (Exception e) {
			System.out.println("DesignDelete 지우기 : "+e);
		}finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null) conn.close();
				}
				catch(Exception ex){}
		}
		return false;
	}
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
	
	//검색
	public void JoinBoard(DesignVO board) {
		
	}


}
