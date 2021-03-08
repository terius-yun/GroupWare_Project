package jsp.vc.model;

import java.sql.Timestamp;

public class HrVO {
	private String emp_num;
	private String hr_checkin;
	private String hr_checkout;
	private int null_checkout;
	
	public int getNull_checkout() {
		return null_checkout;
	}
	public void setNull_checkout(int null_checkout) {
		this.null_checkout = null_checkout;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getHr_checkin() {
		return hr_checkin;
	}
	public void setHr_checkin(String hr_checkin2) {
		this.hr_checkin = hr_checkin2;
	}
	public String getHr_checkout() {
		return hr_checkout;
	}
	public void setHr_checkout(String hr_checkout) {
		this.hr_checkout = hr_checkout;
	} 

}
