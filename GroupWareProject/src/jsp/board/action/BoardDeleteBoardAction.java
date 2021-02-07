package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;

public class BoardDeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		boolean result = false;
		int num=Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdvo = new BoardDAO();
		
		result=bdvo.boardDelete(num);//삭제할 글 번호가 맞으면 
	   	if(result==false){
	   		System.out.println("게시판 삭제 실패");
	   		return null;
	   	}
	   	
	   	System.out.println("게시판 삭제 성공");
	   	forward.setRedirect(true);
   		forward.setPath("BoardListForm.bo");
   		return forward;
	 }
}