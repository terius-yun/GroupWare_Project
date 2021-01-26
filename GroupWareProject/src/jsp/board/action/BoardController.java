package jsp.board.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;


//@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();//프로젝트 주소
		String command=RequestURI.substring(contextPath.length());//프로젝트명
		System.out.println("커맨드 :  ="+ command);
		ActionForward forward=null;
		Action action = null;
		
		if(command.equals("/freeboard.bo")) {
			
			action = new FreeBoardAction();		
			try {
				forward = action.execute(request, response);
				System.out.println("freeboard.bo를 받은forward : "+forward.toString());
				if(forward != null) {
					System.out.println("forward가 not null일때 : "+forward);
					if(forward.isRedirect()) {
						response.sendRedirect(forward.getPath());
					}else {
						RequestDispatcher dispatcher = 
								request.getRequestDispatcher(forward.getPath());
						dispatcher.forward(request, response);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/WriteFormBoard.bo")) {
			
		}else if(command.equals("/Writer.bo")) { //등록
			action = new WriterBoardFormAction();
			
			
		}
		
		
		
		
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
