package jsp.board.model;

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

public class BoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO(){
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/sign");
		} catch (Exception e) {
			System.out.println("board dao e : "+e);
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
			pstmt=conn.prepareStatement("select count(*) from GW_BOARD");
			
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
	
	public List getBoardList(int page, int limit) {
		String board_list_sql="select * from " + 
				"(select rownum rnum,t2.board_num, t2.board_title, t1.member_name, t2.board_writedate, t2.board_readcount, t1.member_team " + 
				"from gw_member t1 inner join gw_board t2 on t1.emp_num=t2.emp_num order by t2.board_num desc) " + 
				"WHERE rnum>=? and rnum<=?";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		int startrow=(page-1)*10+1;//이게뭐지?
		int endrow=startrow+limit-1;//이것도뭐지
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoard_num(rs.getInt("rnum"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setBoard_writedate(rs.getString("board_writedate"));
				bvo.setBoard_readcount(rs.getString("board_readcount"));
				bvo.setMember_team(rs.getString("member_team"));
				list.add(bvo);
			}
			return list;
		} catch (Exception e) {
			System.out.println("getboardList : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return null;
	}
	//등록 이름, 팀명
	public BoardVO getNameTeam(String empNum) {
		BoardVO teamVO = new BoardVO();
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
	public boolean insertBoard(BoardVO bvo) {
		
		int num = 0;
		String sql="";
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(board_num) from gw_board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			sql="insert into gw_board(board_num,emp_num,board_title,board_content,board_readcount,board_file)"
					+ " values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bvo.getEmp_num());
			pstmt.setString(3, bvo.getBoard_title());
			pstmt.setString(4, bvo.getBoard_content());
			pstmt.setInt(5, 0);
			pstmt.setString(6, bvo.getBoard_file());
			
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
	
	//수정
	public boolean boardModify(BoardVO bvo) throws Exception{
		
		String sql="update gw_board set board_title=?,"
				+ " board_content=?,board_file=? where board_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getBoard_title());
			pstmt.setString(2, bvo.getBoard_content());
			pstmt.setString(3, bvo.getBoard_file());
			pstmt.setInt(4,bvo.getBoard_num());
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
	public void deleteBoard(BoardVO board) {
		
	}
	//검색
	public void JoinBoard(BoardVO board) {
		
	}

}
