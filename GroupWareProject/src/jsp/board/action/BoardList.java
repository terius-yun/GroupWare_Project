package jsp.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.model.BoardDAO;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO bdao = new BoardDAO();
		List boardlist = new ArrayList();
		
		HttpSession session = request.getSession();
		String empNum= (String) session.getAttribute("sessionID");
		
		int page=1;
		int limit =10;//한 페이지에 보여줄 게시글 10개
		System.out.println("다음 E");
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = bdao.getListCount(); //총 리스트 수를 받아옴
		boardlist = bdao.getBoardList(page,limit); //리스트를 받아옴
		
		//총 페이지 수
 		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리
 		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
 		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
 		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage+10-1;

 		if (endpage> maxpage) endpage= maxpage;
		request.setAttribute("lists", boardlist);
		ActionForward forward = new ActionForward();
		forward.setPath("./board/BoardListForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
