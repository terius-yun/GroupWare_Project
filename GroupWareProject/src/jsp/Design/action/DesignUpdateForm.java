package jsp.Design.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Design.model.DesignDAO;
import jsp.Design.model.DesignVO;

public class DesignUpdateForm implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		DesignDAO bdao = new DesignDAO();
		DesignVO bvo = new DesignVO();
		
		int num = Integer.parseInt(request.getParameter("Design_num"));
		bvo = bdao.getDetail(num);
		
		if(bvo==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공!");
		
		request.setAttribute("bvoUpdate", bvo);
		forward.setRedirect(false);
		forward.setPath("main.jsp?contentPage=Design/UpdateDesign.jsp");
		
		return forward;
	}

}
