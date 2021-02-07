package jsp.plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.plan.model.PlanDAO;
import jsp.plan.model.PlanVO;

public class UpdateplanAction extends ActionForward implements Action {

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
		
		
		
		PlanDAO bdao = new PlanDAO();
		PlanVO bvo = new PlanVO();
		
		try {
			
			MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize ,"utf-8",new DefaultFileRenamePolicy());
			
			bvo.setplan_num(Integer.parseInt(multi.getParameter("plan_num")));
			bvo.setplan_title(multi.getParameter("plan_title"));
			bvo.setplan_content(multi.getParameter("plan_content"));
			String img  = multi.getParameter("gw_plan_file02");
			String file = multi.getFilesystemName("gw_plan_file");
			if(file == null) {
				bvo.setgw_plan_file(img);
			}else{
				bvo.setgw_plan_file(file);
			}
			result = bdao.planModify(bvo);
			if(result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			forward.setRedirect(true);
			forward.setPath("PlanListForm.pl");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
