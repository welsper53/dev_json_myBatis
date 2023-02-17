package com.day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

// 서블릿을 java인데 브라우저가 출력할 수 있다 - 화면그리는데 서블릿을 사용한다

// 서블릿을 경유하여 응답페이지를 jsp로 가져가는 실습이다
// 최초 mimeHtmlResult.jsp를 직접 호출하는 것이 아니라
// @WebServlet("/day1/html2.do") URL로 요청했을때 "session.setAttribute()"을 만나서
// "./mimeHtmlResult.jsp"로 주소창이 변경되는 것을 관찰한 뒤 
//  서블릿에서 세션에 담은 정보를 mimeHtmlResult.jsp 페이지에서 유지되는지 확인하는 실습이다.

@WebServlet("/day1/html2.do") // 웹에서 접근 가능한 맵핑 주소 설정
public class MimeHtmlServlet2 extends HttpServlet {
	Logger logger = Logger.getLogger(MimeHtmlServlet2.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
		{
			logger.info("doGet호출");
			
			// 요청 객체로 세션객체를 생성함 - 요청객체가 있어야만 세션사용이 가능하다
			// 시간을 연장할 수 있다
			// 서블릿에서는 객체의 유지 정도가 다르다?? 다를 수 있다?? 진짜??
			// scope가 지원됨
			// request scope (-> 요청이 유지되는 동안 유지된다; URL주소가 그래로이면 유지된다
			// session scope (-> URL이 바뀌어도 유지; 톰캣 기본 30분)
			// page scope (-> 해당 페이지 안에서만 기억; 쓰레기)
			// application scope (-> 다운되지 않는다; 사용하지 않는다)
			HttpSession session = req.getSession();
			
			String myName = new String("이순신");			
			int age = 35;			
			List<Map<String,Object>> mList = new ArrayList<>();
			
			Map<String,Object> rmap = new HashMap<>();
			rmap.put("mem_id", "tomato");
			rmap.put("mem_pw", "111");
			rmap.put("mem_name", "토마토");
			if (rmap != null) {
				rmap.clear();		// 휴지통 비우기
			}
			rmap.put("mem_id", "tomato");
			rmap.put("mem_pw", "111");
			rmap.put("mem_name", "토마토");
			mList.add(rmap);
			rmap = new HashMap<>();
			rmap.put("mem_id", "kiwi");
			rmap.put("mem_pw", "111");
			rmap.put("mem_name", "키위");
			mList.add(rmap);
			rmap = new HashMap<>();
			rmap.put("mem_id", "banana");
			rmap.put("mem_pw", "111");
			rmap.put("mem_name", "바나나");
			mList.add(rmap);
			
			session.setAttribute("myName", myName);
			session.setAttribute("age", age);
			session.setAttribute("rmap", rmap);
			session.setAttribute("mList", mList);
			
			// 아래 URL에 쿠키로 세션이 유지된다
			res.sendRedirect("./mimeHtmlResult.jsp");
		}
}
