package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardDAO;
import jsp.board.model.BoardVO;

public class UpdateBoardAction extends ActionForward implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		boolean result = false;
		String realFolder ="";
		String saveFolder = "UploadFolder";
		
		HttpSession session = request.getSession();
		String empNum = (String) session.getAttribute("sessionID");
		realFolder=request.getRealPath(saveFolder);
		int fileSize=5*1024*1024;//이미지 첨부파일크기
		
		
		
		BoardDAO bdao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		try {
			
			MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize ,"utf-8",new DefaultFileRenamePolicy());
			
			bvo.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
			bvo.setBoard_title(multi.getParameter("board_title"));
			bvo.setBoard_content(multi.getParameter("board_content"));
			String img  = multi.getParameter("board_file02");
			String file = multi.getFilesystemName("board_file");
			if(file == null) {
				bvo.setBoard_file(img);
			}else{
				bvo.setBoard_file(file);
			}
			result = bdao.boardModify(bvo);
			if(result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("BoardListForm.bo");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
