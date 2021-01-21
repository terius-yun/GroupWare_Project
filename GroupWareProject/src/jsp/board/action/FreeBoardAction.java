package jsp.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;
import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;

public class FreeBoardAction implements Action{
/* 만든 이유 = 생성   */
	@Override
	public ActionForward execute(HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDAO Mdao = MemberDAO.getInstance();
		MemberVO member = new MemberVO();
		BoardDAO Bdao = BoardDAO.getInstanceBoard();
		BoardVO Board = new BoardVO();
		
		ActionForward forward = new ActionForward();
		
		member.setEmp_num(request.getParameter("emp_num"));//사번
		member.setMember_name(request.getParameter("member_name"));//이름
		member.setMember_pNum(request.getParameter("member_pNum"));//전화번호
		member.setMember_email(request.getParameter("member_email"));//이메일
		member.setMember_team(request.getParameter("member_team"));//부서
		member.setMember_rank(request.getParameter("member_rank"));//직급
		
		Board.setBoard_num(Integer.parseInt(request.getParameter("board_num")));//게시물 번호
		Board.setEmp_num(request.getParameter("emp_num"));//사번
		Board.setBoard_title(request.getParameter("board_title"));//글 제목
		Board.setBoard_content(request.getParameter("board_content"));//글 내용
		Board.setBoard_readcount(Integer.parseInt(request.getParameter("board_readcount")));//스택
		Board.setBoard_writedate(Timestamp.valueOf(request.getParameter("board_writedate")));//작성일
		
		
		
		return forward;
	}

}
