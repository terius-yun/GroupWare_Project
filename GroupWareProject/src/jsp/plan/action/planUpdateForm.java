package jsp.plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.plan.model.PlanDAO;
import jsp.plan.model.PlanVO;

public class planUpdateForm implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		PlanDAO bdao = new PlanDAO();
		PlanVO bvo = new PlanVO();
		
		int num = Integer.parseInt(request.getParameter("plan_num"));
		bvo = bdao.getDetail(num);
		
		if(bvo==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공!");
		
		request.setAttribute("bvoUpdate", bvo);
		forward.setRedirect(false);
		forward.setPath("main.jsp?contentPage=plan/UpdatePlan.jsp");
		
		return forward;
	}

}
