package jsp.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;

public class MemberLoadAddressInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();

		String member_name = request.getParameter("team");
		MemberDAO dao = MemberDAO.getInstance();
        ArrayList<MemberVO> member_info = dao.getMemberUniqueInfo(member_name);
		
        request.setAttribute("memberUniqueInfo", member_info);
        
        forward.setRedirect(false);
        forward.setNextPath("MemberAddressBookAction.do");
		
        return forward;
	}
}
