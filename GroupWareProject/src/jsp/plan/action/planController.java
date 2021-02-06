package jsp.plan.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "Board_Controller", urlPatterns = { "/Board_Controller" })
public class planController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String RequestURI = request.getRequestURI();// *.pl 모든 요청을 수용
		String contextPath =request.getContextPath();//프로젝트 주소
		String command =RequestURI.substring(contextPath.length());//프로젝트명  뒤부터 길이 구함.(*.pl)
		ActionForward forward=null; //포워딩될 뷰 url, 포워딩 방식 설정
		Action action = null;
		System.out.println("처음 command : "+command);
		if(command.equals("/PlanListForm.pl")) {
			action = new planList();	
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AddPlan.pl")) {
			action = new planAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/PlanWrite.pl")) {
			forward = new ActionForward();
			
			forward.setRedirect(false);
			forward.setPath("plan/Planwrite.jsp");
			
		}else if(command.equals("/UpdatePlan.pl")) {
			action = new UpdateplanAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//게시판 수정 
		}else if(command.equals("/Updateform.pl")) {
			action = new planUpdateForm();
			//상세보기 수정
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/PlanDetailAction.pl")) {
			//상세보기
			action = new planDetailaction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/DeletePlanAction.pl")) {
			action = new planDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/PlanSearch.pl")) {
			action = new planSearch();
				try {
					forward= action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		
		
		System.out.println("forwad : "+forward);
		System.out.println("위에 결과 "+action);
		if(forward != null) {
			if(forward.isRedirect()) {
				//java로 가기
				System.out.println("위에 결과 redirct : "+forward);
				response.sendRedirect(forward.getPath());
			}else {
				//jsp로 가기
				System.out.println("위에 결과dis : "+forward);
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
