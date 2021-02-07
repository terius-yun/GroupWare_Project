package jsp.plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.plan.model.PlanDAO;
import jsp.plan.model.PlanVO;


public class planAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		PlanDAO bdao = new PlanDAO();
		PlanVO bvo = new PlanVO();
		
		String realFolder ="";//파일 업로드
		String saveFolder ="UploadFolder";
		
		HttpSession session = request.getSession();
		String empNum= (String) session.getAttribute("sessionID");
		
		int fileSize=5*1024*1024;//이미지 첨부파일크기
		
		//실제 물리적하드에 저장 폴더
		realFolder=request.getRealPath(saveFolder);
		
		boolean result=false;//planVO자료를 db(DAO)에 삽입 여부
		
		try {
			//파일업로드 처리
			MultipartRequest multi=null;
			
			multi=new MultipartRequest(request, realFolder,
					fileSize,"utf-8",new DefaultFileRenamePolicy());
			//plan
			//bvo.setMember_name(multi.getParameter("member_name"));
			//bvo.setMember_team(multi.getParameter("member_team"));
			bvo.setEmp_num(empNum);
			bvo.setplan_title(multi.getParameter("plan_title"));
			bvo.setplan_content(multi.getParameter("plan_content"));
			bvo.setgw_plan_file(multi.getFilesystemName("gw_plan_file"));
			
			System.out.println("bvo title: "+bvo.getplan_title()+" bvo content "+bvo.getplan_content());
			
			
			result=bdao.insertplan(bvo);//bdao에 저장된 데이터를 디비에 저장
			
			if(result==false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			forward.setPath("PlanListForm.pl");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
