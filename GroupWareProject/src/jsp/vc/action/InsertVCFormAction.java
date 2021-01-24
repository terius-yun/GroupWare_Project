package jsp.vc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class InsertVCFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		
		
		
		return forward;  
	}

}
