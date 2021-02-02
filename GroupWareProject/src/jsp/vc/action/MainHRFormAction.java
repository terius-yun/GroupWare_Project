package jsp.vc.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.HrDAO;
import jsp.vc.model.HrVO;

public class MainHRFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("utf-8");

		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		String emp_num = (String)session.getAttribute("sessionID");
 
		Calendar cal = Calendar.getInstance();
		String year;//받아올, 설정할 년
		String cal_year;// 캘린더에 출력될 년
		String month;// 받아올, 설정할 월
		int cal_month; // 캘린더에 출력될 월
		
		//년 구하기/ 받아온 년도가 있다면 그것으로 설정 아니라면 현재 년도로 설정.
		year = (String)request.getParameter("year");
		if(year == null) {
			cal_year = Integer.toString(cal.get ( Calendar.YEAR ));
		}else {
			cal_year = year;
		}
		//월 구하기/ 받아온 월이 있다면 그것으로 설정 아니라면 현재 월로 설정.
		month = (String) request.getParameter("month");
		if(month == null) {
			cal_month = cal.get(Calendar.MONTH)+1;
		}else {
			cal_month = Integer.parseInt(month);
		}
		System.out.println("출력되어야 하는 달은? : "+ month);
		
		cal.set(Integer.parseInt(cal_year),cal_month-1,1);
		
		int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 월의 말일 구하기
		System.out.println("이달의 마지막 일::"+endDayOfMonth);
		System.out.println("년도::"+cal_year);
		
		HrDAO hdao = HrDAO.getInstance();
		ArrayList<HrVO> hrinfo = hdao.HrInfo(emp_num);
		
		if( hrinfo != null ) {
			String [] hr_checkin = new String[hrinfo.size()];
			String [] hr_checkout = new String[hrinfo.size()];
			int hrCount=0;
			
			//i랑 비교할 날짜, 달력에 표시할 시간.
			int [] day = new int[hrinfo.size()];
			String [] cal_checkin = new String[hrinfo.size()];
			String [] cal_checkout = new String[hrinfo.size()];
			
			for( int i = 0; i < hrinfo.size(); i++) {
				hr_checkin[i] = hrinfo.get(i).getHr_checkin();
				hr_checkout[i] = hrinfo.get(i).getHr_checkout();
				
				day[i]= Integer.parseInt(hr_checkin[i].substring(8,10));
				System.out.println("in ++" +day[i]);
				
				cal_checkin[i] = hr_checkin[i].substring(11,16);
				cal_checkout[i] = hr_checkout[i].substring(11,16);
				
				System.out.println("in ++" +cal_checkin[i] +"out ++" + cal_checkout[i]);
				
				hrCount=i+1;

			}
			request.setAttribute("day", day);
			request.setAttribute("cal_checkin", cal_checkin);
			request.setAttribute("cal_checkout", cal_checkout);
			request.setAttribute("hrCount", hrCount);
		}
		request.setAttribute("cal_year", cal_year);
		request.setAttribute("cal_month", cal_month);
	    
		forward.setRedirect(false);
		forward.setNextPath("mainHR.vc");
		
		return forward;
	}

}
