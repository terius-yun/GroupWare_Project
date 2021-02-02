package jsp.vc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class MainHRFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("utf-8");

	      ActionForward forward = new ActionForward();
	      HttpSession session=request.getSession();
	      
	     String emp_num = (String)session.getAttribute("sessionID");
	      
	      
		return forward;
	}

}
