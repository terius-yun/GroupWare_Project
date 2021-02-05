package Gp.calendar.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalendarFrontController.cal")
public class CalendarFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI = "+ RequestURI);
		
		String contextPath=request.getContextPath(); //프로젝트 주소
		System.out.println("contextPath = " + contextPath);

		String command = RequestURI.substring(contextPath.length());
		System.out.println("시작커멘드 : "+command);
		ActionForward forward=null;
		Action action=null;
			//추가.수정 
		if(command.equals("/CalendarAddUpdateAction.cal")) {
			action = new CalendarAddUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//메인 페이지로 
		}else if(command.equals("/CalMainCalendar.cal")) {
			action = new CalendarMainCalendarForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//수정폼 화면				
		}else if(command.equals("/CalAddForm.cal")) {
			action = new CalendarAddForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			//detail
		}else if(command.equals("/CalendarDetail.cal")) {
			action = new CalendarDetailAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Closing.cal")) {
			action=new CalendarClosingForm();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CalendarDevDelete.cal")) {
			action = new CalendarDevDelete();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CalendarDisDelete.cal")) {
			action= new CalendarDisDelete();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CalendarPlaDelete.cal")) {
			action=new CalendarPlaDelete();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("위에 결과"+action);
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println("위에 결과 redirct"+forward);
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println("위에 결과dis "+forward);
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
	
	} 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
