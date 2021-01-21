package jsp.vc.model;

import java.sql.Timestamp;

public class VcDTO {
	private String emp_num;
	private String vc_start_date;
	private String vc_end_date;
	private String vc_content;
	
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getVc_start_date() {
		return vc_start_date;
	}
	public void setVc_start_date(String vc_start_date) {
		this.vc_start_date = vc_start_date;
	}
	public String getVc_end_date() {
		return vc_end_date;
	}
	public void setVc_end_date(String vc_end_date) {
		this.vc_end_date = vc_end_date;
	}
	public String getVc_content() {
		return vc_content;
	}
	public void setVc_content(String vc_content) {
		this.vc_content = vc_content;
	}
	@Override
	public String toString() {
		return "VcDTO [emp_num=" + emp_num + ", vc_start_date=" + vc_start_date + ", vc_end_date=" + vc_end_date
				+ ", vc_content=" + vc_content + "]";
	}
	

	 
}
