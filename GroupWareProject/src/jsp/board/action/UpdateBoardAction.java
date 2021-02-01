package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class UpdateBoardAction extends ActionForward implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num=Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		try {
			bvo.setBoard_num(num);
			bvo.setBoard_title(request.getParameter("board_title"));
			bvo.setBoard_content(request.getParameter("board_content"));
			
			result = bdao.boardModify(bvo);
			if(result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("BoardListForm.bo");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
