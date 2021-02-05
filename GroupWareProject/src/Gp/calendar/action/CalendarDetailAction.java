package Gp.calendar.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Gp.calendar.db.CalendarDAO;
import Gp.calendar.db.CalendarVO;

public class CalendarDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CalendarDAO cDao = CalendarDAO.getInstance();
		
		ArrayList<CalendarVO> calendardata = cDao.detail();
			request.setAttribute("calendardatas", calendardata);
			System.out.println("calendardata = " + calendardata);
			String[] start_date = new String[calendardata.size()];
			String[] end_date = new String[calendardata.size()];
			String[] start__Month = new String[calendardata.size()];
			String[] end__Month = new String[calendardata.size()];
			String[] start__Day = new String[calendardata.size()];
			String[] end__Day = new String[calendardata.size()];
			String[] start__Year = new String[calendardata.size()];
			String[] end__Year = new String[calendardata.size()];
			int startDay[] = new int[calendardata.size()];
			int endDay[] = new int[calendardata.size()];
			int startMonth[] = new int[calendardata.size()];
			int endMonth[] = new int[calendardata.size()];
			int startYear[] = new int[calendardata.size()];
			int endYear[] = new int[calendardata.size()];	
			int cal_count = -1;
			
			String member_team[] = new String[calendardata.size()]; 
			String cal_content[] = new  String[calendardata.size()];
			String cal_member[] = new  String[calendardata.size()];
			String cal_title[] = new  String[calendardata.size()];
			
			
			   for(int i=0 ; i<calendardata.size(); i++){
				   start_date[i] = calendardata.get(i).getCal_start_date();
				   end_date[i] = calendardata.get(i).getCal_end_date();
				   member_team[i] = calendardata.get(i).getMember_team();
				   cal_content[i] = calendardata.get(i).getCal_content();
				   cal_member[i] = calendardata.get(i).getCal_member();
				   cal_title[i] = calendardata.get(i).getCal_title();
				   
				   System.out.println("member_team = " + member_team[i]);
				   System.out.println("cal_content = " + cal_content[i]);
				   
				   start__Day[i] = start_date[i].substring(8,10);
				   startDay[i] = Integer.parseInt(start__Day[i]);
				   end__Day[i] = end_date[i].substring(8,10);
				   endDay[i] = Integer.parseInt(end__Day[i]);
				   start__Month[i] = start_date[i].substring(5, 7);
				   startMonth[i]= Integer.parseInt(start__Month[i]);
				   end__Month[i] =  end_date[i].substring(5, 7);
				   endMonth[i] = Integer.parseInt(end__Month[i]);
				   start__Year[i] = start_date[i].substring(0,4);
				   startYear[i] = Integer.parseInt(start__Year[i]);
				   end__Year[i] = end_date[i].substring(0,4);
				   endYear[i] = Integer.parseInt(end__Year[i]);
				   
				    System.out.println("startDay " + startDay[i]);
				    System.out.println("endDay " + endDay[i]);
				    System.out.println("startMonth " + startMonth[i]);
				    System.out.println("endMonth " + endMonth[i]);
				    System.out.println("startYear " + startYear[i]);
				    System.out.println("endYear " + endYear[i]);
				    cal_count = i;
			   }	   
			   
			   request.setAttribute("startDay",startDay);
			   request.setAttribute("endDay",endDay);
			   request.setAttribute("startMonth",startMonth);
			   request.setAttribute("endMonth",endMonth);
			   request.setAttribute("startYear",startYear);
			   request.setAttribute("endYear",endYear);
			   request.setAttribute("cal_count", cal_count);
			   request.setAttribute("member_team",member_team);
			   request.setAttribute("cal_content", cal_content);
			   request.setAttribute("cal_member", cal_member);
			   request.setAttribute("cal_title", cal_title);
			   
			   String m = request.getParameter("month");
			   String y = request.getParameter("year");
			   
			   request.setAttribute("month_", m);
			   request.setAttribute("year_", y);
			

		//세션에서 팀,권한 가져오기
		HttpSession session = request.getSession();
		String emp_num = (String) session.getAttribute("sessionID");
		
		CalendarVO adTeam = cDao.getAdTeam(emp_num);
		request.setAttribute("adTeam",adTeam);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("main.jsp?contentPage=calendar/mainCalendar.jsp");
	
		
		return forward;
	}
}
