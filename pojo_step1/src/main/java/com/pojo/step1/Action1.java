package com.pojo.step1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 클래스 설계에 인터페이스 필요하다
// 인터페이스 중심의 코딩을 전개하는 것이 결합도를 낮춰준다 - 의존성이 낮다 - 단위테스트 가능 - 신뢰도 높이는 코드 전개
// HttpServlet에서 강제(Override:doGet, doPost)하는 void를 다른 타입으로 바꾸어 보자
// -> 오버라이딩의 경우 선언부(파라미터, 리턴타입)를 손댈 수 없다
// 	 |ActionForward1은 setter와 getter를 가진 VO패턴의 클래스 임
//	 |변수를 2개 보유 - private접근제한자 선언 - 캡슐화
//	 |String path => res.sendRedirect(path), req.getRequestDispatcher(path)
//	 |스크립틀릿 : 서비스메소드 안에 들어간다; 지역변수
//	 |서비스메소드 : init()-service()(개발자 관여)-destroy() -> 서블릿 라이프 사이클
//	 |path는 응답페이지 이름[.jsp]이거나 응답페이지로 forward됨 서블릿의 이름이 온다
//	 |jsp액션 : 목록->글쓰기->~~->저장(서블릿-sendRedirect)->insert->select(서블릿-forward)->jsp
//	 |두번째 전역변수는 "boolean isRedirect" -> true: sendRedirect / false: forward
// 그래서 아래와 같이 바꾸었지만 파라미터 자리의 req와 res는 개발자가 인스턴스화 하는 것이 아니고
// 톰캣이 주입해주는 객체이다.
// 이 문제를 어떻게 해결하는지를 관전 포인트로 삼는다
public interface Action1 {
	public ActionForward1 execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
