package jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDAO {
	private static BoardDAO instance;
	
	//싱글톤 패턴
	private BoardDAO() {}
	public static BoardDAO getInstanceBoard() {
		if(instance==null)
			instance=new BoardDAO();
		return instance;
	}
	
	
	
	public void insertBoard(BoardVO board) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//conn = 
		}catch (Exception e) {
			
		}finally {
			
		}
	}
	
	
	
}
