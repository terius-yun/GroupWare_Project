package jsp.Developer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.Developer.model.DeveloperDAO;
import jsp.Developer.model.DeveloperVO;

public class UpdateDeveloperAction extends ActionForward implements Action {

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
		
		
		
		DeveloperDAO bdao = new DeveloperDAO();
		DeveloperVO bvo = new DeveloperVO();
		
		try {
			
			MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize ,"utf-8",new DefaultFileRenamePolicy());
			
			bvo.setdeveloper_num(Integer.parseInt(multi.getParameter("developer_num")));
			bvo.setdeveloper_title(multi.getParameter("developer_title"));
			bvo.setdeveloper_content(multi.getParameter("developer_content"));
			String img  = multi.getParameter("gw_developer_file");
			String file = multi.getFilesystemName("gw_developer_file02");
			if(file == null) {
				bvo.setgw_developer_file(img);
			}else{
				bvo.setgw_developer_file(file);
			}
			result = bdao.DeveloperModify(bvo);
			if(result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("DeveloperListForm.dp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
