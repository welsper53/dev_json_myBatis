package com.pojo.step1;

import java.io.IOException;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 나는 HttpServlet을 상속 받았으므로 서블릿이다
// 그래서 나느 메소드 파라미터로 request와 response를 받을 수 있다
public class FrontMVC11 extends HttpServlet {
	Logger logger = Logger.getLogger(FrontMVC11.class);
	
	private static final long serialVersionUID = 1L;
	
	// 개발자 입장에서는 GET방식 요청이든 POST방식 요청이든 모두 처리해야한다
	// 그래서 창구를 하나로 통일하려고 doService 사용자정의 메소드를 추가한 것이다
	// 이 메소드는 어떤 장애를 가지고 있나요?
	// -> 톰캣으로부터 요청객체와 응답객체를 주입받지 못한다
	//    ~~~.st1으로 요청하면 doGet만 호출이 가능하고 doPost는 불가능하다=>doService 안됨
	// 테스트 URL = "프로토콜://도메인:포트번호/작업폴더명/요청이름"
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doService호출 - 시간, 로그 레벨, 라인번호, 클래스명");
		logger.info("doService 호출");
		req.setAttribute("menu", "돈까스");
		req.setAttribute("side", "사이다");
		// 아래 코드(sendRedirect)로 요청하면 url이 변경된다
//		res.sendRedirect("/test/crudTest.jsp");
		
		// 아래 코드(forward)로 작성해야 원본 req,res를 전달한다
		// url은 변경되지 않지만 페이지 화면은 이동된다
		RequestDispatcher view = req.getRequestDispatcher("/test/crudTest.jsp");
		view.forward(req, res);	// forward는 SELECT일 때 사용한다
	}
	
	/**************************************************
	 * Restful API - GET방식
	 * - 링크 걸 수 있다
	 * - 단위 테스트 가능하다
	 * - 브라우저에 인터셉트 당한다
	 * -- 포장이 안됨, 노출되어있다(브라우저)
	 * - header에 값이 담기므로 제약이 있다
	 * -- 첨부파일 처리에 사용불가능하다
	 * - 단위테스트 가능하다
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 오버라이딩하는 doGet 또는 doPost 메소드가 아닌것은 톰캣서버로부터 req와 res를 주입받을 수 없다
		// 그래서 doGe, doPost 메소드 내에서 doService메소드를 호출할 때 파라미터로 req와 res를 넘긴다
		// 그래야 사용자 정의 메소드에서도 요청객체와 응답객체를 사용할 수 있으니까
		// - POJO 프레임워크 요청객체와 응답객체에 의조적이다 라고 말할 수 있다
		// 스프링 프레임워크에서는 요청객체와 응답객체 없어도 모든 서비스가 가능하게 되었다
		// - 없이도 가능하다는 것은 메소드의 파라미터로 주입받는다 라는 걸 의미한다
		// - 메소드의 파라미터가 가변적이다 (<- 메소드 오버로딩)
		logger.info("doGet 호출");
		doService(req, res);
	}

	/**************************************************
	 * Restful API - POST방식
	 * - 링크를 걸 수 없다
	 * - 단독으로 호출 테스트 불가능하다 (<- Postman(GET,POST,PUT,DELETE)이 필요하다)
	 * - 브라우저에 쿼리스트링에 노출이 안된다 
	 * -- 포장되어 있다
	 * -- 노출되지 않아 브라우저에 인터셉트 당하지 않는다
	 * -- 모든 요청이 무조건 서버로 전달 된다
	 * - 패킷에 body부분에 값이 담긴다 
	 * -- 용량 제한이 없다 (첨부파일 가능)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doPost 호출");
		doService(req, res);
	}
}
