package jsp.Design.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.Design.model.DesignDAO;
import jsp.Design.model.DesignVO;

public class UpdateDesignAction extends ActionForward implements Action {

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
		
		
		
		DesignDAO bdao = new DesignDAO();
		DesignVO bvo = new DesignVO();
		
		try {
			
			MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize ,"utf-8",new DefaultFileRenamePolicy());
			
			bvo.setDESIGN_NUM(Integer.parseInt(multi.getParameter("Design_num")));
			bvo.setDESIGN_TITLE(multi.getParameter("Design_title"));
			bvo.setDESIGN_CONTENT(multi.getParameter("Design_content"));
			String img  = multi.getParameter("Design_file02");
			String file = multi.getFilesystemName("GW_Design_file");
			if(file == null) {
				bvo.setDESIGN_FILE(img);
			}else{
				bvo.setDESIGN_FILE(file);
			}
			result = bdao.DesignModify(bvo);
			if(result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("DesignListForm.bo");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
