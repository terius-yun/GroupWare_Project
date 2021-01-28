package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		forward.setPath("./board/WriterBoard.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
