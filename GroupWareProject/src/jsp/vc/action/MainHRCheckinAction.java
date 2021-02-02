package jsp.vc.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.HrDAO;
import jsp.vc.model.HrVO;

public class MainHRCheckinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		String emp_num = (String)session.getAttribute("sessionID");
		Timestamp hr_checkin = new Timestamp(System.currentTimeMillis());
		
		HrVO hvo = new HrVO();
		
		hvo.setEmp_num(emp_num);
		hvo.setHr_checkin(hr_checkin);
		
		HrDAO hdao = HrDAO.getInstance();
		hdao.checkin(hvo);
		
		forward.setRedirect(false);
        forward.setNextPath("mainHR.vc");
		
		return forward;
	}

}
