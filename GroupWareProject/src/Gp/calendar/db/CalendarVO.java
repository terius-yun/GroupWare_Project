package Gp.calendar.db;

public class CalendarVO {
	String cal_title;
	String cal_member;
	String cal_content;
	String cal_start_date;
	String cal_end_date;
	String emp_num;
	
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getCal_title() {
		return cal_title;
	}
	public void setCal_title(String cal_title) {
		this.cal_title = cal_title;
	}
	public String getCal_member() {
		return cal_member;
	}
	public void setCal_member(String cal_member) {
		this.cal_member = cal_member;
	}
	public String getCal_content() {
		return cal_content;
	}
	public void setCal_content(String cal_content) {
		this.cal_content = cal_content;
	}
	public String getCal_start_date() {
		return cal_start_date;
	}
	public void setCal_start_date(String cal_start_date) {
		this.cal_start_date = cal_start_date;
	}
	public String getCal_end_date() {
		return cal_end_date;
	}
	public void setCal_end_date(String cal_end_date) {
		this.cal_end_date = cal_end_date;
	}
	
	@Override
	public String toString() {
		return "calendarVO [cal_title=" + cal_title + ", cal_member=" + cal_member + ", cal_content=" + cal_content
				+ ", cal_start_date=" + cal_start_date + ", cal_end_date=" + cal_end_date + ", emp_num=" + emp_num
				+ "]";
	}
	
	
	
	
	
}
