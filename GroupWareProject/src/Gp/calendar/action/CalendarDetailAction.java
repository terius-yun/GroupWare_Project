package Gp.calendar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Gp.calendar.db.CalendarDAO;
import Gp.calendar.db.CalendarVO;

public class CalendarDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CalendarDAO cDao = CalendarDAO.getInstance();
		
		CalendarVO calendardate = cDao.detail();
		request.setAttribute("calendardates", calendardate);
		
		System.out.println("calendardate = " + calendardate);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./calendar/mainCalendar.jsp");
		
		return forward;
	}
}
