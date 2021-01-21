package jsp.vc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.VcDAO;
import jsp.vc.model.VcDTO;

public class addVCAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		VcDTO vao = new VcDTO();
		
		vao.setEmp_num("test1");
		vao.setVc_start_date(request.getParameter("vc_start_date"));
		vao.setVc_end_date(request.getParameter("vc_end_date"));
		vao.setVc_content(request.getParameter("vc_content"));
		
		VcDAO vdao = VcDAO.getInstance();
		vdao.insertVC(vao);
		
		forward.setRedirect(false);
        forward.setNextPath("");
		
		return forward;
	}
	
}
