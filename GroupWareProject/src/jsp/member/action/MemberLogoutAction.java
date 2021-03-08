package jsp.member.action;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.vc.model.HrDAO;
import jsp.vc.model.HrVO;
 
/**
 *  로그아웃 작업을 처리하는 Action 클래스
 */
public class MemberLogoutAction implements Action{
	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        HttpSession session=request.getSession();
        String emp_num = (String)session.getAttribute("sessionID");
        
        HrVO hvo = new HrVO();
		hvo.setEmp_num(emp_num);
		
        HrDAO hdao = HrDAO.getInstance();
        int check = hdao.check_checkout(hvo);
        System.out.println("퇴근 찍혔는지 o=1 x=-1 : "+check);
        
        // 로그아웃시 세션정보를 모두 삭제한다.
        //request.getSession().invalidate();
        
        if(check == 1) {
	        //로그아웃시 세션에 담긴 아이디 값을 삭제한다.
	        request.getSession().removeAttribute("sessionID");
	        
	        // 로그아웃 후 메인화면으로 돌아간다.
	        forward.setRedirect(true);
	        forward.setNextPath("index.do");
        }else {
        	request.setAttribute("logoutFailed_noCheckOut", true);
        	forward.setRedirect(false);
	        forward.setNextPath("MainHRFormAction.vc");
        }
        return forward;
    }
}