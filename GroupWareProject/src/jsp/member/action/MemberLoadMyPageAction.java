package jsp.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;

public class MemberLoadMyPageAction implements Action{

	@Override//마이페이지 정보 불러오기
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		 HttpSession session=request.getSession();
		 
		 //세션에서 아이디(사번) 가져오기
		 String emp_num = (String) session.getAttribute("sessionID");
		// DB에서 아이디, 비밀번호 확인
	        MemberDAO dao = MemberDAO.getInstance();
	        ArrayList<MemberVO> informations = dao.getMemberInfo(emp_num);
	        
	        request.setAttribute("memberInfo", informations);
	        
	        forward.setRedirect(false);
	        forward.setNextPath("myPage.do");
	        
		return forward;
	}

}
