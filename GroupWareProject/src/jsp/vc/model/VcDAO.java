package jsp.vc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import jsp.common.util.DBConnection;

public class VcDAO {
	private VcDAO() {}
	
	private static VcDAO instance = new VcDAO();
		public static VcDAO getInstance() {
			  if(instance==null)
		            instance=new VcDAO();
		        return instance;
	}
		
	public void insertVC(VcVO vvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("insert into GW_VC values(?,?,?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, vvo.getEmp_num());
			pstmt.setString(2, vvo.getVc_start_date());
			pstmt.setString(3, vvo.getVc_end_date());
			pstmt.setString(4, vvo.getVc_content());
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
