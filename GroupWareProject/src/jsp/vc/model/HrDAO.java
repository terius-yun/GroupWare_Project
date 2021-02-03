package jsp.vc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jsp.common.util.DBConnection;

public class HrDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private HrDAO() {}
	
	private static HrDAO instance = new HrDAO();
		public static HrDAO getInstance() {
			  if(instance==null)
		            instance=new HrDAO();
		        return instance;
	}
		
	public void checkin(HrVO hvo) {
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("insert into GW_HR(EMP_NUM, HR_CHECKIN) values(?,?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, hvo.getEmp_num());
			pstmt.setString(2, hvo.getHr_checkin());
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
	public void checkout(HrVO hvo) {

		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("update gw_hr set hr_checkout=? where emp_num=? and hr_checkout IS null");
			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, hvo.getHr_checkout());
			pstmt.setString(2, hvo.getEmp_num());

			
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
	
	public ArrayList<HrVO> HrInfo(String emp_num) {
		ArrayList<HrVO> HrInfo = new ArrayList<HrVO>();
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("select*from gw_hr where emp_num=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HrVO hvo = new HrVO();
				hvo.setEmp_num(rs.getString("emp_num"));
				hvo.setHr_checkin(rs.getString("hr_checkin"));
				hvo.setHr_checkout(rs.getString("hr_checkout"));
				
				HrInfo.add(hvo);
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
		return HrInfo;
	}
}
