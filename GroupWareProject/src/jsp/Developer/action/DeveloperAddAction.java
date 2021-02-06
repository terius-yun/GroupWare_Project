package jsp.Developer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.Developer.model.DeveloperDAO;
import jsp.Developer.model.DeveloperVO;

public class DeveloperAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		DeveloperDAO bdao = new DeveloperDAO();
		DeveloperVO bvo = new DeveloperVO();
		
		String realFolder ="";//파일 업로드
		String saveFolder ="UploadFolder";
		
		HttpSession session = request.getSession();
		String empNum= (String) session.getAttribute("sessionID");
		
		int fileSize=5*1024*1024;//이미지 첨부파일크기
		
		//실제 물리적하드에 저장 폴더
		realFolder=request.getRealPath(saveFolder);
		
		boolean result=false;//developerVO자료를 db(DAO)에 삽입 여부
		
		try {
			//파일업로드 처리
			MultipartRequest multi=null;
			
			multi=new MultipartRequest(request, realFolder,
					fileSize,"utf-8",new DefaultFileRenamePolicy());
			
			//bvo.setMember_name(multi.getParameter("member_name"));
			//bvo.setMember_team(multi.getParameter("member_team"));
			bvo.setEmp_num(empNum);
			bvo.setdeveloper_title(multi.getParameter("Developer_title"));
			bvo.setdeveloper_content(multi.getParameter("Developer_content"));
			bvo.setgw_developer_file(multi.getFilesystemName("gw_Developer_file"));
			
			System.out.println("bvo title: "+bvo.getdeveloper_title()+" bvo content "+bvo.getdeveloper_content());
			
			
			result=bdao.insertDeveloper(bvo);//bdao에 저장된 데이터를 디비에 저장
			
			if(result==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("DeveloperListForm.dp");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
