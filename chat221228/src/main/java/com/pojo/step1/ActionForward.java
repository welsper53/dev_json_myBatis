package com.pojo.step1;

public class ActionForward {
	// 페이지 이동 방법
	// 1. res.sendRedirect("~~~.jsp") - 표준서블릿 - MVC패턴이 아니다
	//	  또는 res.sendRedirect("dept/getDeptList.kh") - 서블릿이 요청 받는다 - MVC패턴인가?
	// jsp가 요청 받는것이 왜 문제인가?
	// => WAS가 명명 규칙이 다르기 때문, 그래서 인스턴스화를 할 수 없다
	//	  또한 인스턴스화를 한다 하더라도 request와 response객체를 주입 받지 못하는 문제가 있다
	private String path = null;		// 응답페이지 이름 or 서블릿의 이름
	
	// sendRedirect로 페이지를 이동할 것인지 아니면
	// forward로 페이지를 이동할 것인지 여부를 결정함
	// -> true: redirect(insert|update|delete), false: forward(select)
	private boolean isRedirect = false;
	
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

/*
ActionForward클래스를 왜 만드나?
> 웹 서비스에서는 main메소드 대신에 URL로 요청을 보낸다
누가? 클라이언트가 누구에게? Tomcat서버에게 요청한다
요청을 할 때 URL사용한다

*/