package jsp.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "Board_Controller", urlPatterns = { "/Board_Controller" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String RequestURI = request.getRequestURI();// *.bo 모든 요청을 수용
		String contextPath =request.getContextPath();//프로젝트 주소
		String command =RequestURI.substring(contextPath.length());//프로젝트명  뒤부터 길이 구함.(*.bo)
		ActionForward forward=null; //포워딩될 뷰 url, 포워딩 방식 설정
		Action action = null;
		System.out.println("처음 command : "+command);
		if(command.equals("/BoardListForm.bo")) {
			action = new BoardList();	
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/WriteFormBoard.bo")) {
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
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
		doProcess(request, response);
	}

}
