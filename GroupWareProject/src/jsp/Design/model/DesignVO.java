package jsp.Design.model;



public class DesignVO {
	private int DESIGN_NUM;
	private String EMP_NUM
	,DESIGN_TITLE
	,DESIGN_CONTENT
	,DESIGN_READCOUNT
	,DESIGN_WRITEDATE
	,DESIGN_FILE;
	
	private String member_name;				//이름
	private String member_pNum;				//전화번호
	private String member_email;			//이메일
	private String member_team;				//부서
	private String member_rank;				//직급
	
	public int getDESIGN_NUM() {
		return DESIGN_NUM;
	}
	public void setDESIGN_NUM(int dESIGN_NUM) {
		DESIGN_NUM = dESIGN_NUM;
	}
	public String getEMP_NUM() {
		return EMP_NUM;
	}
	public void setEMP_NUM(String eMP_NUM) {
		EMP_NUM = eMP_NUM;
	}
	public String getDESIGN_TITLE() {
		return DESIGN_TITLE;
	}
	public void setDESIGN_TITLE(String dESIGN_TITLE) {
		DESIGN_TITLE = dESIGN_TITLE;
	}
	public String getDESIGN_CONTENT() {
		return DESIGN_CONTENT;
	}
	public void setDESIGN_CONTENT(String dESIGN_CONTENT) {
		DESIGN_CONTENT = dESIGN_CONTENT;
	}
	public String getDESIGN_READCOUNT() {
		return DESIGN_READCOUNT;
	}
	public void setDESIGN_READCOUNT(String dESIGN_READCOUNT) {
		DESIGN_READCOUNT = dESIGN_READCOUNT;
	}
	public String getDESIGN_WRITEDATE() {
		return DESIGN_WRITEDATE;
	}
	public void setDESIGN_WRITEDATE(String dESIGN_WRITEDATE) {
		DESIGN_WRITEDATE = dESIGN_WRITEDATE;
	}
	public String getDESIGN_FILE() {
		return DESIGN_FILE;
	}
	public void setDESIGN_FILE(String dESIGN_FILE) {
		DESIGN_FILE = dESIGN_FILE;
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
