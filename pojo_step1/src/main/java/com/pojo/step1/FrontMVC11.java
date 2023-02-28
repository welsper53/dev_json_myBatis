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
		logger.info("doService 호출");
		// -> board/getBoardList.st1 -> web.xml -> url-pattern -> *.st1
		
		/*
		req.setAttribute("menu", "돈까스");
		req.setAttribute("side", "사이다");
		// 아래 코드(sendRedirect)로 요청하면 url이 변경된다
		// res.sendRedirect("/test/crudTest.jsp");
		
		// 아래 코드(forward)로 작성해야 원본 req,res를 전달한다
		// url은 변경되지 않지만 페이지 화면은 이동된다
		RequestDispatcher view = req.getRequestDispatcher("/test/crudTest.jsp");
		view.forward(req, res);	// forward는 SELECT일 때 사용한다
		*/
		
		String uri = req.getRequestURI();
		logger.info("url : " + uri);		// "/board/getBoardList.st1"
		
		// server.xml <Context path="/">
		String context = req.getContextPath();		// "/" -> server.xml
		logger.info("context : " + context);	// " " <-공란
		
		// 톰캣서버에 요청할 때 사용되는 주소값을 가지고 업무명과 업무에 필요한 이름으로
		// 분리시켜 사용자 요청에 따라 처리를 담당할 ~~~Controller객체를 주입하는데 사용한다
		String command = uri.substring(context.length() + 1);	// context정보만 제외된 나머지 경로 정보를 담음
		logger.info("command : " + command);	// "board/getBoardList.st1"
		
		int end = command.lastIndexOf(".");		// st1 잘라내기 위해 사용
		logger.info("end : " + end);		// 16
		
		command = command.substring(0, end);
		logger.info("command : " + command);	// "board/getBoardList"
		
		String upmu[] = null;		// upmu[0] = board|폴더명, upmu[1] = 요청기능이름 (getBoardList st1제거된 상태임)
		upmu = command.split("/");
		for (String imsi : upmu) {
			logger.info("imsi : " + imsi);	// "board  getBoardList"
		}
		
		ActionForward1 af = null;
		
		// 게으른 인스턴스화
		// -> 아직 업무명이 정해지지 않았기 때문
		// 업무명이 Controller클래스의 접두어이다
		BoardController1 boardController = null;
		
		/* upmu[0] = 업무명|폴더명, upmu[1] = 요청기능이름 
		 * BoardController에서 req와 res를 받을 수 있는건 execute(req, res) 메소드 뿐이다
		 * 그러면 각 기능 별로 사용하려면 메소드로 나누면 안될 것 같다
		 * 그럼 if문으로 분기하고 DeptLogic.java에서 메소드를 분리해 나가자 */
		/* Board1Controller는 서블릿이 아니므로 요청객체와 응답객체를 주입받지 못함
		 * execute호출 시 파라미터 전달하기 - 구조
		 * 원본이 넘어가는 거니까*/
		req.setAttribute("upmu", upmu);
		
		if ("board".equals(upmu[0])) {
			// request객체는 저장소이다
			// 저장: setAttribute
			// 출력: getAttribute
			
			// 인스턴스화 -> execute() 호출하기 위해 (500: NullPointException 발생하므로)
			// 여기에 진입하기 위한 테스트url 작성하시오
			// -> http://localhost:9000/board/getBoardList.st1
			boardController = new BoardController1();	// 나는 서블릿 아니다
			
			// 서블릿이 아니지만 req, res는 반드시 필요하다 -> 웹서비스 제공을 위해
			// 톰캣서버를 경유했을 때 요청객체와 응답객체를 활용가능함
			// FrontMVC11 경유해라
			af = boardController.execute(req, res);
			// deptController는 서블릿이 아니여서 req와 res를 톰캣으로부터 주입받을 수 없다
			// (객체가 아니여서???)
		}
		
		// [JSON 형식으로 출력]
		// 페이지 이동 처리 공통코드 만듦
		// 1. res.sendRedirect("/dept/getDeptList.jsp");
		// 	: jsp -> 서블릿 -> jsp
		// 	  res.sendRedirect("/dept/getDeptList.st1");
		//	: jsp -> 서블릿(insert) -> 서블릿(select) -> jsp
		if (af != null) {
			if (af.isRedirect()) {
				res.sendRedirect(af.getPath());
			} else {
				RequestDispatcher view = req.getRequestDispatcher(af.getPath());
				view.forward(req, res);
			}
		} // end of 페이지 이동처리에 대한 공통 코드 부분
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
		// - POJO 프레임워크 요청객체와 응답객체에 의존적이다 라고 말할 수 있다
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
		// POST방식으로 요청이 일어나면 서블릿의 doPost가 받는다
		// 이 때 톰캣컨테이너로 부터 요청객체와 응답객체를 주입받는다(의존성 주입)
		// 개발자가 정의한 doService 호출 시 파라미터로 주입받은 요청객체와 응답객체를 넘긴다
		doService(req, res);	// pass by value
	}
}
