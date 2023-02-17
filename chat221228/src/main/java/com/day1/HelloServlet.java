package com.day1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mvc.dao.BookDao;

// form전송 시 클라이언트 측의 서블릿이 듣는다
// method="GET"이면 doGet호출
//   ''  ="POST"이면 doPost호출
// 자바가 서블릿이 되기 위한 조건은 반드시 HttpServlet을 상속받는 것이다
// (import javax.servlet.ServletException;)
// 상속을 받으면 doGet과 doPost 오버라이딩 할 수 있는데
// 이 함수의 파라미터를 통해서 request요청 객체와 response 응답 객체를 주입 받는다
// 톰캣 서버에서 주입해준다
// 웹 서비스를 위해서는 URL등록이 필수이다 <- 브라우저가 실행 주체이기 때문
// 프로젝트에 필요한 URL등록은 annotation과 web.xml문서를 통해서 가능하다
// annotion은 자동이고 편하기는 하지만 수동처리와 비교해서 추가 작업이 불가능한 단점이 있다
// xml문서를 통해서 객체 등록하고 주입 받는다
// 이유는 spring 사용 시 maven 레포를 이용한 프로젝트 생성인 경우에 xml문서로 환경 설정한다
// 클래스 상이의 객체 주입관계도 xml문서로 처리 가능하다
public class HelloServlet extends HttpServlet {
	Logger logger = Logger.getLogger(HelloServlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException
	{
		System.out.println("doGet호출");
		logger.info("doGet 호출 성공");
		
		String mem_id = req.getParameter("mem_id");
		logger.info("사용자가 입력한 아이디는 "+ mem_id + "입니다");
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String msg = "안녕하세요";
		
		// 브라우저에 요청[GET방식] 시 응답으로 나가는 문자열
		// 문자열(1.텍스트파일(숫자의경우 문자로 변환 수 쓴다)
		//		 2.바이너리파일(데이터를 있는 그대로 읽고 쓴다))
		// text메인타입 html서브타입 - 브라우저 번역 - 태그는 없고 내용만 출력
		out.print("<font size=28px color=blue>"+ msg + "</font>");	// 리소스
		BookDao bDao = new BookDao();
		logger.info(bDao.testDate());
	}
	
	// 단위테스트가 불가능하다 - Postman을 사용하면 가능하다
	// POST방식은 브라우저를 통해서 테스트 불가능하다
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		logger.info("doPost 호출 성공");
		res.setContentType("text/html;charset=UTF-8");
		//인스턴스화에서 메소드를 사용하는 경우는 뭐가 다른 걸까요? A a = new A()
		PrintWriter out = res.getWriter();
		out.print("<h3>doPost</h3>");
	}
}


/*
	웹서비스[요청하고 응답받기] 제공을 위한 언어
	요청을 어디에 하지? -> 브라우저 -> URL로 ->
	http(hyper text transfer protocol) - 텍스트 전송하는 프로토콜이다
	아니잖아 이미지, 동영상
	https = http+TLS
	요청 방식에는 몇 가지가 있다?
	요청을 위해서는 무엇이 준비되어 있어야 하나요?
	

	HTTP에서 지원하는 요청 메시지는 다음과 같다.
		GET: 클라이언트가 서버에게 URL에 해당하는 자료의 전송을 요청한다.
		HEAD: GET 요청으로 반환될 데이터 중 헤더 부분에 해당하는 데이터만 요청한다.
		POST: 클라이언트가 서버에서 처리할 수 있는 자료를 보낸다. 예를 들어, 게시판에 글을 쓸 때 클라이언트의 문서가 서버로 전송되어야 한다. 멱등성을 보장하지 않는다.
		PATCH: 클라이언트가 서버에게 지정한 URL의 데이터를 부분적으로 수정할 것을 요청한다.
		PUT: 클라이언트가 서버에게 지정한 URL에 지정한 데이터를 저장할 것을 요청한다.
		DELETE: 클라이언트가 서버에게 지정한 URL의 정보를 제거할 것을 요청한다.
		TRACE: 클라이언트가 서버에게 송신한 요청의 내용을 반환해 줄 것을 요청한다.
		CONNECT: 클라이언트가 특정 종류의 프록시 서버에게 연결을 요청한다.
		OPTIONS: 해당 URL에서 지원하는 요청 메세지의 목록을 요청한다.
		
	[GET]
	서버측의 리소스를 가져오기 위해서...
	쿼리스트링 전송(소용량)
	공유 가능 -> 노출된다 (url?mem_id=tomato)
	데이터 메시지의 head에 담아 전송한다
	검색 시 사용
	
	[POST]
	서버에 데이터를 올리기 위해 설계됨
	전송 데이터크기의 제한이 없다(대용량)
	보안에 유리 -> 공유가 어렵다
	데이터 메시지의 body에 담아 전송한다
	글쓰기, 로그인, 회원가입 시 사용
*/