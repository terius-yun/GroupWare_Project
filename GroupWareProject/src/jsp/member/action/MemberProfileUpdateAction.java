package jsp.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;

public class MemberProfileUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("utf-8"); // 인코딩
        
        ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		 
		//세션에서 아이디(사번) 가져오기
		String emp_num = (String) session.getAttribute("sessionID");
        
        MemberDAO dao = MemberDAO.getInstance();
        
        // 입력된 정보를 자바빈에 세팅한다.
        MemberVO member = new MemberVO();
        member.setEmp_num(emp_num);
        member.setMember_pw(request.getParameter("member_pw"));//비밀번호
        member.setMember_name(request.getParameter("member_name"));//이름
        member.setMember_pNum(request.getParameter("member_pNum"));//전화번호
        member.setMember_email(request.getParameter("member_email"));//이메일
        member.setMember_bank_account(request.getParameter("member_bank_account"));//계좌정보
		
        //업데이트 실행
        dao.updateProfile(member);
        
        // 업데이트 성공
        forward.setRedirect(true);
        forward.setNextPath("MemberLoadProfileAction.do");
        
        return forward;
	}	
}
