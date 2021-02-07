package jsp.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
		
		HttpSession session=request.getSession();
        if(session.getAttribute("sessionID")== null) {
        	forward = new ActionForward();
        	forward.setPath("security/abnormal_approach.jsp");
        	forward.setRedirect(false);
        }else {
			if(command.equals("/BoardListForm.bo")) {
				action = new BoardList();	
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/AddBoard.bo")) {
				action = new BoardAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/BoardWrite.bo")) {
				forward = new ActionForward();
				
				forward.setRedirect(false);
				forward.setPath("main.jsp?contentPage=board/Boardwrite.jsp");
				
			}else if(command.equals("/UpdateBoard.bo")) {
				action = new UpdateBoardAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//게시판 수정 
			}else if(command.equals("/Updateform.bo")) {
				action = new BoardUpdateForm();
				//상세보기 수정
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/BoardDetailAction.bo")) {
				//상세보기
				action = new BoardDetailaction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/DeleteBoardAction.bo")) {
				action = new BoardDeleteBoardAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/BoardSearch.bo")) {
				action = new BoardSearch();
				
				try {
					forward= action.execute(request, response);
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
