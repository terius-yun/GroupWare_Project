package jsp.board.model;

import java.sql.Timestamp;

public class BoardVO {
	private int board_num;					//스택
	private String emp_num;					//사번
	private String board_title;				//글 제목
	private String board_content;			//글 내용
	private int board_readcount;			//읽기 계정
	private Timestamp board_writedate;		//작성일
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(String emp_num) {
		this.emp_num = emp_num;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	public Timestamp getBoard_writedate() {
		return board_writedate;
	}
	public void setBoard_writedate(Timestamp board_writedate) {
		this.board_writedate = board_writedate;
	}
	
	
}
