package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class WriterBoardFormAction implements Action{
	public ActionForward execute(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String emp_num = (String) session.getAttribute("sessionID");
		boolean result = false;
		
		BoardDAO bdao = BoardDAO.getInstanceBoard();
		BoardVO board = new BoardVO();
		
		
		
		try {
			
			
		} catch (Exception e) {
			
		}
		
		
		
		return null;
	}
	
}
