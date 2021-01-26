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
		
		//list value값만큼 배열 초기화
		String [] start_date = new String[list.size()];
		String [] end_date = new String[list.size()];
		String [] content = new String[list.size()];	
		int vcCount = 0;
		
		//변수 시작월, 종료월, 시작일, 종료일.
		int startMonth, endMonth, startDay, endDay;		
		String cal_start,cal_end;
		
		for( int i =0; i < list.size(); i++){
				start_date[i] = list.get(i).getVc_start_date();
				end_date[i] = list.get(i).getVc_end_date();
				content[i] =  list.get(i).getVc_content();
				
				startMonth = Integer.parseInt(start_date[i].substring(5,7));
				endMonth = Integer.parseInt(end_date[i].substring(5,7));
				System.out.println("날짜" + startMonth + "달" + endMonth);
				
				startDay = Integer.parseInt(start_date[i].substring(8,10));
				endDay = Integer.parseInt(end_date[i].substring(8,10));
				System.out.println(start_date[i] + ":" + end_date[i]);
				
				if ( startMonth < month ) {
					if( month < 10 ) {
						start_date[i] = year+"-0"+month+"-01";
					} else {
						start_date[i] = year+"-"+month+"-01";
					}
				}
				if( endMonth > month) {
					if( month < 10 ) {
						end_date[i] = year + "-0" + month + "-" + endDayOfMonth;
					} else {
						end_date[i] = year+"-"+month+"-" + endDayOfMonth;
					}
				}

				startDay = Integer.parseInt(start_date[i].substring(8,10));
				endDay = Integer.parseInt(end_date[i].substring(8,10));

				System.out.println("날짜" + startDay + "일" + endDay);
				
				cal_start = start_date[i];
				cal_end = end_date[i];
				
				System.out.println("날짜" + cal_start + ":" + cal_end);
				
				
		        request.setAttribute("startDay"+i, startDay);
		        request.setAttribute("endDay"+i, endDay);				
		        request.setAttribute("content"+i, content);
		        vcCount = i;
		}
		request.setAttribute("vcCount", vcCount);
		
		forward.setRedirect(false);
		forward.setNextPath("mainVC.vc");
		
		return forward;  
	}

}
