package jsp.Developer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Developer.model.DeveloperDAO;
import jsp.Developer.model.DeveloperVO;

public class DeveloperUpdateForm implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		DeveloperDAO bdao = new DeveloperDAO();
		DeveloperVO bvo = new DeveloperVO();
		
		int num = Integer.parseInt(request.getParameter("developer_num"));
		bvo = bdao.getDetail(num);
		
		if(bvo==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공!");
		
		request.setAttribute("bvoUpdate", bvo);
		forward.setRedirect(false);
		forward.setPath("Developer/UpdateDeveloper.jsp");
		
		return forward;
	}

}
