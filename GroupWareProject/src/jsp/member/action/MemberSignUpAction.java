package jsp.member.action;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;
 
/** 
 *  회원가입을 처리하는 Action 클래스<br>
 *  JoinForm.jsp에서 넘겨받은 정보를 이용하여
 *  회원가입을 처리한다.
 */
public class MemberSignUpAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("utf-8"); // 인코딩
        
        ActionForward forward = new ActionForward();
        
        MemberDAO dao = MemberDAO.getInstance();
        
        // 입력된 정보를 자바빈에 세팅한다.
        MemberVO member = new MemberVO();
        member.setEmp_num(request.getParameter("emp_num"));//사번
        member.setMember_pw(request.getParameter("pw"));//비밀번호
        member.setMember_name(request.getParameter("name"));//이름
        member.setMember_birth(request.getParameter("birth"));//생년월일
        member.setMember_pNum(request.getParameter("pNum"));//전화번호
        member.setMember_email(request.getParameter("email"));//이메일
        member.setMember_bank_account(request.getParameter("bank_account"));//계좌정보
        member.setMember_team(request.getParameter("team"));//부서
        member.setMember_rank(request.getParameter("rank"));//직급
        member.setMember_administrator(request.getParameter("administrator"));//관리자권한
        
        // 회원가입 실행
        dao.insertMember(member);
        
        // 가입성공
        forward.setRedirect(true);
        forward.setNextPath("signUpCompleteForm.do");
        
        // 가입성공 메시지를 세션에 담는다.
        //request.getSession().setAttribute("msg", "1");
           
        return forward;
    }
}