package Gp.calendar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Gp.calendar.db.CalendarDAO;

public class CalendarDisDelete implements Action{ 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		CalendarDAO cDao = CalendarDAO.getInstance();
		cDao.CalDisDelete();
		
		
		
		forward.setRedirect(true);
		forward.setPath("CalendarDetail.cal");
		
		return forward;
	}
}
