package mvc.kh;

public class ActionForward {
	private String path = null;
	private boolean isRedirect = false;//true:redirect,  false:forward
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}
