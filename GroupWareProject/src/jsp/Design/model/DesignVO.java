package jsp.Design.model;



public class DesignVO {
	private int design_num;
	private String emp_num
	,design_title
	,design_content
	,design_readcount
	,design_writedate
	,gw_design_file;
	
	private String member_name;				//이름
	private String member_pNum;				//전화번호
	private String member_email;			//이메일
	private String member_team;				//부서
	private String member_rank;				//직급
	
	
	public int getDesign_num() {
		return design_num;
	}
	public void setDesign_num(int design_num) {
		this.design_num = design_num;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getDesign_title() {
		return design_title;
	}
	public void setDesign_title(String design_title) {
		this.design_title = design_title;
	}
	public String getDesign_content() {
		return design_content;
	}
	public void setDesign_content(String design_content) {
		this.design_content = design_content;
	}
	public String getDesign_readcount() {
		return design_readcount;
	}
	public void setDesign_readcount(String design_readcount) {
		this.design_readcount = design_readcount;
	}
	public String getDesign_writedate() {
		return design_writedate;
	}
	public void setDesign_writedate(String design_writedate) {
		this.design_writedate = design_writedate;
	}
	public String getgw_design_file() {
		return gw_design_file;
	}
	public void setgw_design_file(String gw_design_file) {
		this.gw_design_file = gw_design_file;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pNum() {
		return member_pNum;
	}
	public void setMember_pNum(String member_pNum) {
		this.member_pNum = member_pNum;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_team() {
		return member_team;
	}
	public void setMember_team(String member_team) {
		this.member_team = member_team;
	}
	public String getMember_rank() {
		return member_rank;
	}
	public void setMember_rank(String member_rank) {
		this.member_rank = member_rank;
	}

	
	
	
}
