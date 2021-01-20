package jsp.member.model;

import java.sql.Date;

public class MemberVO {
	private String emp_num;					//사번
	private String member_pw;				//비밀번호
	private String member_name;				//이름
	private String member_birth;			//생년월일
	private String member_pNum;				//전화번호
	private String member_email;			//이메일
	private String member_bank_account;		//계좌정보
	private String member_team;				//부서
	private String member_rank;				//직급
	private String member_administrator;	//관리자권한
	private int member_board_count;			//게시글 수
	private Date member_regdate;			//가입일
	
	
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
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
	public String getMember_bank_account() {
		return member_bank_account;
	}
	public void setMember_bank_account(String member_bank_account) {
		this.member_bank_account = member_bank_account;
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
	public String getMember_administrator() {
		return member_administrator;
	}
	public void setMember_administrator(String member_administrator) {
		this.member_administrator = member_administrator;
	}
	public Date getMember_regdate() {
		return member_regdate;
	}
	public void setMember_regdate(Date member_regdate) {
		this.member_regdate = member_regdate;
	}
	public int getMember_board_count() {
		return member_board_count;
	}
	public void setMember_board_count(int member_board_count) {
		this.member_board_count = member_board_count;
	}
	
	
}