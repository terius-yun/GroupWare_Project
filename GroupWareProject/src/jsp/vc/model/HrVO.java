package jsp.vc.model;

import java.sql.Timestamp;

public class HrVO {
	private String emp_num;
	private Timestamp hr_checkin;
	private Timestamp hr_checkout;
	
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public Timestamp getHr_checkin() {
		return hr_checkin;
	}
	public void setHr_checkin(Timestamp hr_checkin) {
		this.hr_checkin = hr_checkin;
	}
	public Timestamp getHr_checkout() {
		return hr_checkout;
	}
	public void setHr_checkout(Timestamp hr_checkout) {
		this.hr_checkout = hr_checkout;
	} 

}
