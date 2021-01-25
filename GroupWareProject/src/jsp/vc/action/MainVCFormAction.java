package jsp.vc.action;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.VcDAO;
import jsp.vc.model.VcVO;

public class MainVCFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get ( cal.YEAR );
		int month = cal.get(cal.MONTH)+1;
		int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		//session emp_num 가져오기
		String emp_num = (String)session.getAttribute("sessionID");
		//GW_VC 정보 DAO 통해서 가져오기 
		VcDAO vdao = VcDAO.getInstance();
		ArrayList<VcVO> list = vdao.VcInfo(emp_num);
		System.out.println(list.get(3).getVc_start_date());
		
		// list에서 start_date 꺼내서 월만 자르기
		String start_date = list.get(3).getVc_start_date();
		int start = Integer.parseInt(start_date.substring(5, 7));
		System.out.println(list.get(3).getVc_end_date());
		System.out.println("시작 날짜 " + start);
		
		//list에서 end_date 꺼내서 월만 자르기 
		String end_date = list.get(3).getVc_end_date();
		int end = Integer.parseInt(end_date.substring(5, 7));
		System.out.println("종료 날짜 " + end);
		System.out.println("ddddd: "+year);
		System.out.println("ddddd: "+month);
		System.out.println("ddddd: "+endDayOfMonth);	
		
		if ( start < month ) {
			 start_date = year+"/"+month+"/1";
		}
		if( end > month) {
			end_date = year + "/" + month + "/" + endDayOfMonth;
		}
 		
		forward.setRedirect(false);
		forward.setNextPath("mainVC.vc");
		
		return forward;  
	}

}
