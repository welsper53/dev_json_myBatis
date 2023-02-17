package com.mvc.controller;

import java.io.IOException;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mvc.dao.TestDao;

public class TestServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TestServlet.class);
	
	// 사용자 정의 메소드
	public void doService(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
		{
			logger.fatal("doService 호출");
			logger.debug("doService 호출");
			logger.info("doService 호출");
			logger.warn("doService 호출");
			logger.error("doService 호출");
			
			TestDao tDao = new TestDao();
			List<Map<String,Object>> mList = tDao.getBookMember();
			
			//페이지 이동
			//페이지 이름 getMemberList.jsp
			//페이지의 물리적인 경로는 어디를 바라보고 있는가? -> web.xml-> servlet-mapping[/test/test.do] 
			// -> url-pattern
			//  경로명/요청을 처리하는이름.do[뒤에 온 확장자는 의미없다- why? 해당 요청을 인터셉트해서 해당 업무를 담당하는]
			// 클래스에 연결(FrontController.java설계 - 각 업무별 XXXController필요함)을 해야 함
			//페이지 처리는 JSP에게 맡김
			//서블릿은 요청을 받아서 업무 담당자에게 연결(매핑,매칭)
			//이것을 어떻게 나눌 것인가?
			//요청은 업무담당자나 사용자 선택에 따라 결정
			// 결정에 대응하는 클래스를 FrontController연결
			// FrontController에서 실제 업무를 담당하는 XXXController에 전달할 때
			// 요청객체와 응답객체 전달되어야 한다
			// 요청 객체 무엇을 누릴 수 있나?
			// 사용자가 입력한 값을 듣기 위해 필요하다
			// requset.getParameter(""): String
			// session.getAttribute(""): Object
			// request.getParameter("~~~"); mem_id, mem_name, mem_address
			// HttpSession session = request.getSession(); // 세션객체 생성
			// http프로토콜이 비상태 프로토콜이므로 상태값을 관리하는 별도의 메카니즘 필요
			// 쿠기의 세션(시간을 지배한다-신) <- List, Map <- 객체배열[{}] <- 배열 <- 원시형 타입(변수)
			// 
			// response
			// response.setContentType(); 마임타입을 결정한다
			// 서블릿인데 json, 서블릿인데 html, 서블릿인데 xml
			// response.setContentType("application/json")
			// response.setContentType("text/html")
			// response.setContentType("text/xml")
			// response.setContentType("text/css")
			// response.sendRedirect("페이지 이름") -> 페이지 이동
			// 주소창이 바뀐다 -> 기존에 요청이 끊어지고 새로운 요청이 발생한다
			// 그런데 마치 계속 유지하고 있는 것처럼 보여져야 함 -> 그러니까 쿠키나 세션에 담아둔다 -> 왜냐면 비상태 프로토콜
			// 
			// 쿠키 : 문자열 - 객체는 못받음
			// 세션 : 캐시메모리 - 객체 - very good
			// 
			// 아래 코드를 만나기 전까지는 http://localhost:9000/test/test.do
			// 를 보여주다가 아래 문자를 만나면 그때 http://localhost:9000/test/getMemberList.jsp 를 요청함
			// 
			// 테스트 시나리오
			// 방법1: TestServlet.java 소스에서 오른쪽 버튼 눌러 실행시킨다
			// 방법2: 브라우저 주소창에 http://localhost:9000/test/test.do 라고 요청한다
			res.sendRedirect("./getMemberList.jsp");
			
			//String cTime = tDao.testDate();			
			//logger.info("today : " + cTime);
			//tDao.getBookMember();
			
		}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
		{
			doService(req, res);
		}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
		{
			doService(req, res);
		}
}

