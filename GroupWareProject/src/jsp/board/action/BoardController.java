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
			//업로드 파일 사이즈
			int fileSize = 5*1024*1024;
			//업로드될 폴더 경로 /파일 생성안했음
			String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
			//파일업로드
			MultipartRequest multi = new 
					MultipartRequest(request, uploadPath, fileSize,"utf-8", new DefaultFileRenamePolicy());
			String url = "board/WriterBoard.jsp";
			try {
				
				
				//파일 업로드
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				//파일이름 가져오기
				String fileName = "";
				Enumeration<String> names = multi.getFileNames();
				if(names.hasMoreElements()) {
					String name = names.nextElement();
					fileName = multi.getFilesystemName(name);
				}
				BoardDAO dao = BoardDAO.getInstanceBoard();
				BoardVO bvo = new BoardVO();
				
				bvo.setBoard_num(dao.getSeq());
				bvo.setMember_name(multi.getParameter("member_name"));	
				bvo.setMember_team(multi.getParameter("member_team"));
				bvo.setBoard_title(multi.getParameter("board_title"));
				bvo.setBoard_content(multi.getParameter("board_content"));
				//bvo.setBoard_file(multi.getParameter("fileName"));
				boolean result = dao.insertBoard(bvo);
			} catch (Exception e) {
				
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
