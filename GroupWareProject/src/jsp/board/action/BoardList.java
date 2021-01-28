package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO bdao = new BoardDAO();
		
		
		
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/BoardListForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
