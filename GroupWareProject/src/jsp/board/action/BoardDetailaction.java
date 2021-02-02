package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class BoardDetailaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BoardDAO bdao= new BoardDAO();
		BoardVO bvo= new BoardVO();
		
		//글번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		bdao.setReadCountUpdate(num);//조회수 증가
		bvo= bdao.getDetail(num); //글의 내용 BoardDAO에 내용을 BoardVO저장
		
		if(bvo==null) {//글의 내용이 없으면
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("view", bvo);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//dispatcher 호출
		forward.setPath("board/Boardview.jsp");
		
		
		return forward;
	}

}
