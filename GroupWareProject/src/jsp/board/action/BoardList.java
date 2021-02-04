package jsp.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;
import jsp.board.model.Paging;

public class BoardList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO bdao = new BoardDAO();
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		
		HttpSession session = request.getSession();
		String empNum= (String) session.getAttribute("sessionID");
		
 		//request.setAttribute("notices", noticelist);
 		 int totalCount = bdao.getTotalCount();
 	    int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
 	     
 	    Paging paging = new Paging();
 	    paging.setPageNo(page); //get방식의 parameter값으로 반은 page변수, 현재 페이지 번호
 	    paging.setPageSize(10); // 한페이지에 불러낼 게시물의 개수 지정
 	    paging.setTotalCount(totalCount);
 	     
 	    page = (page - 1) * 10; //select해오는 기준을 구한다.
 	     
 	   boardlist = bdao.getListBoard(page, paging.getPageSize());
 	     
 	    request.setAttribute("lists", boardlist);
 	    request.setAttribute("paging", paging);


 	
 		String team_name=bdao.getmemberteaminpo(empNum);
 		request.setAttribute("team_name", team_name);
		request.setAttribute("lists", boardlist);
		ActionForward forward = new ActionForward();
		forward.setPath("./board/BoardListForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
