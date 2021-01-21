package Gp.calendar.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	Statement stmt;
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
	
	
	//CalendarDetail에서 호출
	public CalendarVO detail(){
	    String sql = "select cal_title,cal_member,cal_content,cal_start_date,cal_end_date from gw_calendar";
		CalendarVO calendardate = new CalendarVO();
		try {
			conn=DBConnection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				
				calendardate.setCal_title(rs.getString("cal_title"));
				calendardate.setCal_member(rs.getString("cal_member"));
				calendardate.setCal_content(rs.getString("cal_content"));
				calendardate.setCal_start_date(rs.getString("cal_start_date"));
				calendardate.setCal_end_date(rs.getString("cal_end_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(stmt, rs, conn);
		}
		return calendardate;
	}
	
	
	
	
	
	
}
