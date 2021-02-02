package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class BoardAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		String realFolder ="";//파일 업로드
		String saveFolder ="UploadFolder";
		
		HttpSession session = request.getSession();
		String empNum= (String) session.getAttribute("sessionID");
		
		int fileSize=5*1024*1024;//이미지 첨부파일크기
		
		//실제 물리적하드에 저장 폴더
		realFolder=request.getRealPath(saveFolder);
		
		boolean result=false;//BoardVO자료를 db(DAO)에 삽입 여부
		
		try {
			//파일업로드 처리
			MultipartRequest multi=null;
			
			multi=new MultipartRequest(request, realFolder,
					fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			//bvo.setMember_name(multi.getParameter("member_name"));
			//bvo.setMember_team(multi.getParameter("member_team"));
			bvo.setEmp_num(empNum);
			bvo.setBoard_title(multi.getParameter("board_title"));
			bvo.setBoard_content(multi.getParameter("board_content"));
			bvo.setBoard_file(multi.getFilesystemName("board_file"));
			
			System.out.println("bvo title: "+bvo.getBoard_title()+" bvo content "+bvo.getBoard_content());
			
			
			result=bdao.insertBoard(bvo);//bdao에 저장된 데이터를 디비에 저장
			
			if(result==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("BoardListForm.bo");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
