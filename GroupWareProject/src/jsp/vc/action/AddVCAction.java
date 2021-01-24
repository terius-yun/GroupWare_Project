package jsp.vc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.VcDAO;
import jsp.vc.model.VcVO;

public class AddVCAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		//세션 아이디(사번) 가져오기
		String emp_num = (String) session.getAttribute("sessionID");
		
		//DB에서 emp_num가져오기 
//		MemberDAO dao = MemberDAO.getInstance();
//		ArrayList<MemberVO> informations = dao.getMemberInfo(emp_num);
	     
	    //빈에 입력된 정보 추가
		VcVO vvo = new VcVO();

		vvo.setEmp_num(emp_num);
		vvo.setVc_start_date(request.getParameter("vc_start_date"));
		vvo.setVc_end_date(request.getParameter("vc_end_date"));
		vvo.setVc_content(request.getParameter("vc_content"));
		
		//빈에 저장된 정보 DAO insertVC에 보내기
		VcDAO vdao = VcDAO.getInstance();
		vdao.insertVC(vvo);
		
		forward.setRedirect(false);
        forward.setNextPath("InsertVCFormAction.vc");
		
		return forward;
	}
	
}
