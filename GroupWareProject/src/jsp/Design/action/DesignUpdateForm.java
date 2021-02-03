package jsp.Design.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class DesignUpdateForm implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		int num = Integer.parseInt(request.getParameter("board_num"));
		bvo = bdao.getDetail(num);
		
		if(bvo==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공!");
		
		request.setAttribute("bvoUpdate", bvo);
		forward.setRedirect(false);
		forward.setPath("board/UpdateBoard.jsp");
		
		return forward;
	}

}
