package jsp.vc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;


public class AddVCFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8"); // 인코딩
        
        ActionForward forward = new ActionForward();
        HttpSession session=request.getSession();
        
		 //세션에서 아이디(사번) 가져오기
		 String emp_num = (String) session.getAttribute("sessionID");
		// DB에서 아이디 가져오기
	        MemberDAO dao = MemberDAO.getInstance();
	        ArrayList<MemberVO> informations = dao.getMemberInfo(emp_num);
	    //mainVC에서 휴가 시작일 가져오기         
        String start_date = request.getParameter("YYMMDD");
        System.out.println(start_date);
        
        request.setAttribute("memberInfo", informations);
        request.setAttribute("start_date", start_date);
        
        forward.setRedirect(false);
        forward.setNextPath("addVCForm.vc");
           
        return forward;
	}
	
}
