package jsp.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberVO;

public class MemberAddressBookAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		//검색어
		String condition = request.getParameter("condition");
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberVO> list = dao.getMemberList(condition);
		ArrayList<MemberVO> team1 = dao.getMemberTeam(1);
		ArrayList<MemberVO> team2 = dao.getMemberTeam(2);
		ArrayList<MemberVO> team3 = dao.getMemberTeam(3);
		ArrayList<MemberVO> team4 = dao.getMemberTeam(4);
		
		request.setAttribute("list", list);
		request.setAttribute("team1", team1);
		request.setAttribute("team2", team2);
		request.setAttribute("team3", team3);
		request.setAttribute("team4", team4);
		
		forward.setRedirect(false);
		forward.setNextPath("memberAddressBook.do");
		return forward;
	}

}
