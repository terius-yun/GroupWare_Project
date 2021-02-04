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

public class MainHRCheckinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		//session ID 가져오기
		String emp_num = (String)session.getAttribute("sessionID");
		//timestamp 시간 
		Timestamp checkin = new Timestamp(System.currentTimeMillis());
		//timestamp 형식 변경
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String hr_checkin = format.format(checkin);
		System.out.println("date"+checkin);
		
		HrVO hvo = new HrVO();
		
		hvo.setEmp_num(emp_num);
		hvo.setHr_checkin(hr_checkin);
		
		HrDAO hdao = HrDAO.getInstance();
		hdao.checkin(hvo);
		
		forward.setRedirect(false);
        forward.setNextPath("MainHRFormAction.vc");
		
		return forward;
	}

}
