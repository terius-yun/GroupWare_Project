package jsp.vc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.action.Action;
import jsp.common.action.ActionForward;

public class VCFormChangeAction implements Action{
    private String form = "main.jsp?contentPage=vc/";
    private String path;
    
    /**
     * 명령어로부터 다음 이동할 페이지 경로를 생성한다.
     * @param command 명령어
     */
    public void setCommand(String command){
        int idx = command.indexOf(".");
        path = command.substring(0, idx)+".jsp";
    }
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        forward.setRedirect(false);
        
        HttpSession session=request.getSession();
        if(!path.equals("index.jsp")&&session.getAttribute("sessionID")== null) {
        	forward.setNextPath("security/abnormal_approach.jsp");
        }else {
	        if(path.equals("main.jsp")) {
	            forward.setNextPath(path);
	        }else {
	            forward.setNextPath(form+path);
	        }
        }
        return forward;
    }
}
