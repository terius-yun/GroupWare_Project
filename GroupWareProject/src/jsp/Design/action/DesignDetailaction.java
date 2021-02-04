package jsp.Design.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Design.model.DesignDAO;
import jsp.Design.model.DesignVO;

public class DesignDetailaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		DesignDAO bdao= new DesignDAO();
		DesignVO bvo= new DesignVO();
		
		//글번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		bdao.setReadCountUpdate(num);//조회수 증가
		bvo= bdao.getDetail(num); //글의 내용 BoardDAO에 내용을 BoardVO저장
		
		if(bvo==null) {//글의 내용이 없으면
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("view", bvo);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//dispatcher 호출
		forward.setPath("Design/Designview.jsp");
		
		
		return forward;
	}

}
