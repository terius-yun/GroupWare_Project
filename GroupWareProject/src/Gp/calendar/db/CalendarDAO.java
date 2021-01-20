package Gp.calendar.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jsp.common.util.DBConnection;

public class CalendarDAO {
	private static CalendarDAO instance = new CalendarDAO();
	
	private CalendarDAO() {
		
	}
	
	public static CalendarDAO getInstance() {
		return instance;
	}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//CalendarAddUpdateAction에서 호출
	public void CalendarInsert(CalendarVO calendardata) {
		try {
			conn=DBConnection.getConnection();
			
			String sql = "insert into gw_calendar(cal_title, cal_member, cal_content, cal_start_date,cal_end_date,emp_num) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setString(1,calendardata.getCal_title());
			pstmt.setString(2, calendardata.getCal_member());
			pstmt.setString(3, calendardata.getCal_content());
			pstmt.setString(4, calendardata.getCal_start_date());
			pstmt.setString(5, calendardata.getCal_end_date());
			pstmt.setString(6, calendardata.getEmp_num());
			
			pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(pstmt, conn);
		}
		
		
		
		
	}
	
	
	
	
	
}
