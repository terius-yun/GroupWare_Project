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
		
	public void insertVC(VcDTO vto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("insert into GW_VC values(?,?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, vto.getEmp_num());
			pstmt.setString(2, vto.getVc_start_date());
			pstmt.setString(3, vto.getVc_end_date());
			pstmt.setString(4, vto.getVc_content());
			pstmt.executeUpdate();
			
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
