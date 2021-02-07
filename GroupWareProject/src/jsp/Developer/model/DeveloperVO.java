package jsp.Developer.model;



public class DeveloperVO {
	private int developer_num;				//스택
	private String emp_num;					//아이디
	private String developer_title;				//글 제목
	private String developer_content;			//글 내용
	private String developer_readcount;			//읽기 계정
	private String developer_writedate;			//작성일
	private String gw_developer_file; 				//파일
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
	public int getdeveloper_num() {
		return developer_num;
	}
	public void setdeveloper_num(int developer_num) {
		this.developer_num = developer_num;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getdeveloper_title() {
		return developer_title;
	}
	public void setdeveloper_title(String developer_title) {
		this.developer_title = developer_title;
	}
	public String getdeveloper_content() {
		return developer_content;
	}
	public void setdeveloper_content(String developer_content) {
		this.developer_content = developer_content;
	}
	public String getdeveloper_readcount() {
		return developer_readcount;
	}
	public void setdeveloper_readcount(String developer_readcount) {
		this.developer_readcount = developer_readcount;
	}
	public String getdeveloper_writedate() {
		return developer_writedate;
	}
	public void setdeveloper_writedate(String developer_writedate) {
		this.developer_writedate = developer_writedate;
	}
	public String getgw_developer_file() {
		return gw_developer_file;
	}
	public void setgw_developer_file(String developer_file) {
		this.gw_developer_file = developer_file;
	}
	
	
}
