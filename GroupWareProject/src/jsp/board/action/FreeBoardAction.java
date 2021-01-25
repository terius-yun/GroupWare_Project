package jsp.board.action;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;


public class FreeBoardAction implements Action{
/* 만든 이유 = 생성   */
	@Override
	public ActionForward execute(HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String emp_num= (String) session.getAttribute("sessionID");
		
		
		BoardDAO Bdao = BoardDAO.getInstanceBoard();
		BoardVO Board = new BoardVO();
		
		ArrayList<BoardVO> list = Bdao.listBoard(Board);
		ActionForward forward = new ActionForward();
		
		request.setAttribute("list", list);
		
		forward.setPath("board/FreeBoard.jsp");
		forward.setRedirect(false);
		System.out.println("프리보더액션 : "+forward.toString());
		return forward;
	}

}
