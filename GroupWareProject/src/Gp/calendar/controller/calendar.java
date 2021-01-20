package Gp.calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calendar/calendar.cal")
public class calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cal_title = request.getParameter("cal_title");
		String cal_member = request.getParameter("cal_member");
		String cal_content = request.getParameter("cal_content");
		String cal_start_date = request.getParameter("cal_start_date");
		String cal_end_date = request.getParameter("cal_end_date");
		
		System.out.println(cal_title);
		System.out.println(cal_member);
		System.out.println(cal_content);
		System.out.println(cal_start_date);
		System.out.println(cal_end_date);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
