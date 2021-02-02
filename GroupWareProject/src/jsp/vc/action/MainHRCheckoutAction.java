package jsp.vc.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.HrDAO;
import jsp.vc.model.HrVO;

public class MainHRCheckoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	      ActionForward forward = new ActionForward();
	      HttpSession session=request.getSession();
	      
	      String emp_num = (String)session.getAttribute("sessionID");
	      
	      Timestamp checkout = new Timestamp(System.currentTimeMillis());

		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String hr_checkout = format.format(checkout);

	      HrVO hvo = new HrVO();
			
			hvo.setEmp_num(emp_num);
			hvo.setHr_checkout(hr_checkout);
			
			HrDAO hdao = HrDAO.getInstance();
			hdao.checkout(hvo);
			
	        forward.setNextPath("MainHRFormAction.vc");
	      
		return forward;
	}

}
