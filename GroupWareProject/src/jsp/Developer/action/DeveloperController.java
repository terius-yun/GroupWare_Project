package jsp.Developer.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



//@WebServlet(name = "Developer_Controller", urlPatterns = { "/Developer_Controller" })
public class DeveloperController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String RequestURI = request.getRequestURI();// *.dp 모든 요청을 수용
		String contextPath =request.getContextPath();//프로젝트 주소
		String command =RequestURI.substring(contextPath.length());//프로젝트명  뒤부터 길이 구함.(*.dp)
		ActionForward forward=null; //포워딩될 뷰 url, 포워딩 방식 설정
		Action action = null;
		System.out.println("처음 command : "+command);
		
		HttpSession session=request.getSession();
        if(session.getAttribute("sessionID")== null) {
        	forward = new ActionForward();
        	forward.setPath("security/abnormal_approach.jsp");
        	forward.setRedirect(false);
        }else {
			if(command.equals("/DeveloperListForm.dp")) {
				action = new DeveloperList();	
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/AddDeveloper.dp")) {
				action = new DeveloperAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/DeveloperWrite.dp")) {
				forward = new ActionForward();
				
				forward.setRedirect(false);
				forward.setPath("main.jsp?contentPage=Developer/Developerwrite.jsp");
				
			}else if(command.equals("/UpdateDeveloper.dp")) {
				action = new UpdateDeveloperAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//게시판 수정 
			}else if(command.equals("/Updateform.dp")) {
				action = new DeveloperUpdateForm();
				//상세보기 수정
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/DeveloperDetailAction.dp")) {
				//상세보기
				action = new DeveloperDetailaction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/DeleteDeveloperAction.dp")) {
				action = new DeveloperDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/DeveloperSearch.dp")) {
				action = new DeveloperSearch();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
