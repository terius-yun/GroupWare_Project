package Gp.calendar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Gp.calendar.db.CalendarDAO;
import Gp.calendar.db.CalendarVO;


public class CalendarAddUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CalendarVO calendardate = new CalendarVO();
		ActionForward foward = new ActionForward();
		String emp_num="1111";
		
		calendardate.setCal_title(request.getParameter("cal_title"));
		calendardate.setCal_member(request.getParameter("cal_member"));
		calendardate.setCal_content(request.getParameter("cal_content"));
		calendardate.setCal_start_date(request.getParameter("cal_start_date"));
		calendardate.setCal_end_date(request.getParameter("cal_end_date"));
		calendardate.setEmp_num(emp_num);
		
		System.out.println(request.getParameter("cal_title")+request.getParameter("cal_member")+request.getParameter("cal_content")+
				request.getParameter("cal_start_date")+request.getParameter("cal_end_date")+emp_num);
		
		
		CalendarDAO cDao = CalendarDAO.getInstance();
		cDao.CalendarInsert(calendardate);
		
		foward.setRedirect(true);
		foward.setPath("calendar/sss.jsp");
		
		return foward;
	}

}
