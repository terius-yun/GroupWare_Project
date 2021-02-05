package Gp.calendar.action;

public class ActionForward {
	//action 인터페이스에서 명령을 수행하고 결과 값을 갖고 포워딩하려고
	private boolean isRedirect = false;  //redirect 할지 여부
	private String path = null;  //포워딩 할 위치!
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
