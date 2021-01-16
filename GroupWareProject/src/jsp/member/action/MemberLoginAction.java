package jsp.member.action;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
 
/**
 * 로그인 작업을 처리하는 Action 클래스
 */
public class MemberLoginAction implements Action{

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        HttpSession session=request.getSession();
        
        // 아이디와 비밀번호를 가져온다.
        String emp_num = request.getParameter("emp_num");
        String member_pw = request.getParameter("member_pw");
        
        // DB에서 아이디, 비밀번호 확인
        MemberDAO dao = MemberDAO.getInstance();
        int check = dao.loginCheck(emp_num, member_pw);
        
        if(check == 0){    // 비밀번호 틀릴경우 -> 다시 로그인 화면으로 이동
        
            // 로그인 실패시 메시지를 request에 담는다.
               request.setAttribute("fail", "0");
               
               forward.setRedirect(false);
               forward.setNextPath("/");
        }else if(check == -1) { // 아이디가 없을 경우 -> 다시 로그인 화면으로 이동
            request.setAttribute("fail", "-1");
 
               forward.setRedirect(false);
               forward.setNextPath("/");
        }else{
            //로그인 성공 -> 세션에 아이디를 저장
               session.setAttribute("sessionID", emp_num);
               
               // 로그인 성공후 메인화면으로 이동
               forward.setRedirect(true);
               forward.setNextPath("main.do");
        }
           
        return forward;
    }
 
}