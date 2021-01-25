package jsp.vc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import jsp.common.util.DBConnection;

public class VcDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private VcDAO() {}
	
	private static VcDAO instance = new VcDAO();
		public static VcDAO getInstance() {
			  if(instance==null)
		            instance=new VcDAO();
		        return instance;
	}
		
	public void insertVC(VcVO vvo) {
		
		
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
	// 연차 정보 출력
	public ArrayList<VcVO> VcInfo(String emp_num){
		ArrayList<VcVO> information = new ArrayList<VcVO>();
		try {
			conn = DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from GW_VC where emp_num = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
					VcVO vvo = new VcVO();
					vvo.setEmp_num(rs.getString("emp_num"));
					vvo.setVc_start_date(rs.getString("vc_start_date"));
					vvo.setVc_end_date(rs.getString("vc_end_date"));
					vvo.setVc_content(rs.getString("vc_content"));
					
					information.add(vvo);
			}
			
			
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
		return information;
		
	}
	
}
