package jsp.vc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
			
			//금일 날짜 받아와 like문에 사용가능한 format으로 다시 설정
			String yy = hvo.getHr_checkin().substring(2,4);
			String mm = hvo.getHr_checkin().substring(5,7);
			String dd = hvo.getHr_checkin().substring(8,10);
			String yymmdd = yy+"/"+mm+"/"+dd;
			//금일 날짜 받아와 format 다시 설정
			
			//금일의 출근 기록이 있는지 확인 후 없으면 출근 진행
			sql.append("select*from gw_hr where (emp_num=? AND HR_CHECKIN like '"+yymmdd+"%')");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, hvo.getEmp_num());
			rs = pstmt.executeQuery();
			if(rs.next() == false) {
			
				StringBuffer sql2 = new StringBuffer();
				// 출결 날짜 기입
				sql2.append("insert into GW_HR(EMP_NUM, HR_CHECKIN) values(?,?)");
				
				pstmt = conn.prepareStatement(sql2.toString());
				
				pstmt.setString(1, hvo.getEmp_num());
				pstmt.setString(2, hvo.getHr_checkin());
				pstmt.executeUpdate();
				conn.commit();
			}else {
				System.out.println("금일 출결처리로 출결 db입력 X");
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
	}
	public void checkout(HrVO hvo) {//퇴근

		try {
			//현재 날짜 불러오기
			Timestamp currentDay = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
			String today = format.format(currentDay);
			System.out.println("today :" +today);
			//현재 날짜 불러오기
			
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("update gw_hr set hr_checkout=? where (emp_num=? AND HR_CHECKIN like '"+ today +"%')");
			
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
	
	public int check_checkout(HrVO hvo) {
		int check  = 0;
		try {
			//현재 날짜 불러오기
			Timestamp currentDay = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
			String today = format.format(currentDay);
			System.out.println("today :" +today);
			//현재 날짜 불러오기
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
		
			StringBuffer sql = new StringBuffer();
			sql.append("select hr_checkout from gw_hr where (emp_num=? AND HR_CHECKIN like '"+ today +"%')");
			
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, hvo.getEmp_num());
	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("hr_checkout") == null) {
					check  = -1;
				}else {
					check  = 1;
				}
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
		return check;
	}
	
	
	public ArrayList<HrVO> HrInfo(String emp_num) {
		ArrayList<HrVO> HrInfo = new ArrayList<HrVO>();
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("select*from gw_hr where emp_num=? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HrVO hvo = new HrVO();
				hvo.setEmp_num(rs.getString("emp_num"));
				hvo.setHr_checkin(rs.getString("hr_checkin"));
				if(rs.getString("hr_checkout") != null) {
					hvo.setHr_checkout(rs.getString("hr_checkout"));
					hvo.setNull_checkout(0);
				}else {
					hvo.setNull_checkout(1);
				}
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
