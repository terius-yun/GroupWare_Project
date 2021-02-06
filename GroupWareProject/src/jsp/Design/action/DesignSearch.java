package jsp.Design.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.Design.model.DesignDAO;
import jsp.Design.model.DesignVO;


public class DesignSearch implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DesignDAO bdao = new DesignDAO();
		List<DesignVO> boardlist = new ArrayList<DesignVO>();
		String searchName=request.getParameter("searchName");
		String searchValue=request.getParameter("searchValue");
		System.out.print("searchValue : "+searchValue);
		
		int page=1;
		int limit=10;//한 페이지에 보여줄 게시글 10개
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		int listcount=bdao.SearchCount(searchName, searchValue);// 총 리스트 수를 받아옴
		boardlist = bdao.Search(page, limit, searchName, searchValue);// 리스트를 받아옴
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95);//0.95를 더해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수(1,11,21 등...)
		int startpage = (((int) ((double)page / 10 + 0.9))-1)* 10+1;
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30등...)
		int endpage = startpage+10-1;

		if(endpage> maxpage) endpage = maxpage;
		request.setAttribute("page", page); //현재 페이지 수
 		request.setAttribute("maxpage", maxpage); //최대 페이지 수
 		request.setAttribute("startpage", startpage); //현재 페이지에 표시할 첫 페이지 수
 		request.setAttribute("endpage", endpage); //현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("listcount",listcount); //글 수
		
		
		request.setAttribute("lists", boardlist);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Design/DesignListForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
