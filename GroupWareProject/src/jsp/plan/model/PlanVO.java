package jsp.plan.model;



public class PlanVO {
	private int plan_num;				//스택
	private String emp_num;					//아이디
	private String plan_title;				//글 제목
	private String plan_content;			//글 내용
	private String plan_readcount;			//읽기 계정
	private String plan_writedate;			//작성일
	private String gw_plan_file; 				//파일
	private String row_num;					//

	private String member_administrator;	//랭크구분숫자
	private String member_name;				//이름
	private String member_pNum;				//전화번호
	private String member_email;			//이메일
	private String member_team;				//부서
	private String member_rank;				//직급
	
	public String getMember_administrator() {
		return member_administrator;
	}
	public void setMember_administrator(String member_administrator) {
		this.member_administrator = member_administrator;
	}
	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
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
	public int getplan_num() {
		return plan_num;
	}
	public void setplan_num(int plan_num) {
		this.plan_num = plan_num;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getplan_title() {
		return plan_title;
	}
	public void setplan_title(String plan_title) {
		this.plan_title = plan_title;
	}
	public String getplan_content() {
		return plan_content;
	}
	public void setplan_content(String plan_content) {
		this.plan_content = plan_content;
	}
	public String getplan_readcount() {
		return plan_readcount;
	}
	public void setplan_readcount(String plan_readcount) {
		this.plan_readcount = plan_readcount;
	}
	public String getplan_writedate() {
		return plan_writedate;
	}
	public void setplan_writedate(String plan_writedate) {
		this.plan_writedate = plan_writedate;
	}
	public String getgw_plan_file() {
		return gw_plan_file;
	}
	public void setgw_plan_file(String plan_file) {
		this.gw_plan_file = plan_file;
	}
	
	
}
