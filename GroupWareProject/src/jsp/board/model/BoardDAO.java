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
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("board dao e : "+e);
			return;
		}
		
	}
	//게시판 메인 화면 리스트
	public ArrayList<BoardVO> listBoard(BoardVO board) throws SQLException{
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = DBConnection.getConnection();
			
			String sql = 
			"select t1.member_name, t1.member_pNum,t2.BOARD_NUM, t2.BOARD_READCOUNT, " + 
					"t2.BOARD_CONTENT,t2.BOARD_TITLE, t2.BOARD_WRITEDATE, " + 
					"t1.member_email, t1.member_team,t1.emp_num, t1.member_rank " + 
					"from gw_member t1 inner join  GW_BOARD t2 on " + 
					"t1.emp_num=t2.emp_num";
		
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setMember_name(rs.getString("member_name"));
				bvo.setMember_email(rs.getString("member_email"));
				bvo.setMember_pNum(rs.getString("member_pNum"));
				bvo.setBoard_num(rs.getString("board_num"));
				bvo.setBoard_readcount(rs.getString("board_readcount"));
				bvo.setBoard_content(rs.getString("board_content"));
				bvo.setBoard_title(rs.getString("board_title"));
				bvo.setBoard_writedate(rs.getString("board_writedate"));
				bvo.setMember_team(rs.getString("member_team"));
				bvo.setMember_rank(rs.getString("member_rank"));
				bvo.setEmp_num(rs.getString("emp_num"));
				list.add(bvo);
			}
			
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
		return list;
	}
	
	//등록
	public boolean insertBoard(BoardVO board ) throws SQLException {
		

		int result = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			String sql ="";
			sql="insert into GW_BOARD "+
			"values(?,?,?,?,?,systimestamp,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_num());
			pstmt.setString(2, board.getEmp_num());
			pstmt.setString(3, board.getBoard_title());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(6, board.getBoard_readcount());
			pstmt.setString(7, board.getBoard_file());
			result=pstmt.executeUpdate();
			if(result==0)return false;
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn !=null) try{conn.close();}catch(SQLException ex){}
		}
		return false;
	}
	//수정
	public boolean boardModify(){
		
		return false;
	}
	//삭제
	public void deleteBoard(BoardVO board) {
		
	}
	//검색
	public void JoinBoard(BoardVO board) {
		
	}
	
	
}
