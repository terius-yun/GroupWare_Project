package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jsp.common.util.DBConnection;

public class BoardDAO {
	private static BoardDAO instance;
	
	//싱글톤 패턴
	private BoardDAO() {}
	public static BoardDAO getInstanceBoard() {
		if(instance==null)
			instance=new BoardDAO();
		return instance;
	}
	
	
	//등록
	public void insertBoard(BoardVO board) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DBConnection.getConnection();
			
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into GW_BOARD ");
			sql.append("values(?,?,?,?,?,systimestamp)");
			
			pstmt.setInt(1, board.getBoard_num());
			
			
		}catch (Exception e) {
			
		}finally {
			try {
				if(conn!=null)conn.close(); conn=null;
				if(pstmt!=null)pstmt.close(); pstmt=null;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	//수정
	
	//삭제
	public void deleteBoard(BoardVO board) {
		
	}
	//검색
	public void JoinBoard(BoardVO board) {
		
	}
	
	
}
