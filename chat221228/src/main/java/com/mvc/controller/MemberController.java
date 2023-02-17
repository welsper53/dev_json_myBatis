package com.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mvc.dao.MemberDao;


@WebServlet("/intro/login")
public class MemberController extends HttpServlet {
	Logger logger = Logger.getLogger(MemberController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet 호출");
		
		// 오라클 서버에서 조회한 결과를 받아서 세션에 담기 위해 세션객체 생성하기
		HttpSession session = req.getSession();
		// 오라클 서버에 연동하는 일만 전담하는 ~~~.Dao클래스 인스턴스화
		MemberDao mDao = new MemberDao();
		
		// 사용자가 입력한 값 요청하기
		String mem_id = req.getParameter("mem_id");
		String mem_pw = req.getParameter("mem_pw");
		logger.info(mem_id + ", " + mem_pw);
		
		// MyBatis에 파라미터로 아이디와 비번을 Map에 담아서 넘겨야 하니까
		Map<String,Object> pmap = new HashMap<>();
		// 키값에 대응하는 사용자가 입력한 아이디와 비번 Map에 담기
		pmap.put("mem_id", mem_id);
		pmap.put("mem_pw", mem_pw);
		logger.info("pmap ===> " + pmap);
		
		// 오라클 서버에 연동하는 login메소드 호출 - 호출 시 파라미터로 pmap 넘긴다
		// pmap 안에 사용자가 입력한 아이디와 비번이 있다
		Map<String,Object> rmap = mDao.login(pmap);		// 처리 결과를 Map으로 받아온다
		logger.info("rmap ===> " + rmap);
		// 위에서 받아온 rmap에 들어있는 오라클 서버에서 select문으로 조회한 결과를
		// 꺼내서 세션 객체에 저장한다
		session.setAttribute("smem_id", rmap.get("MEM_ID"));
		session.setAttribute("smem_name", rmap.get("MEM_NAME"));
		
		// 로그인 화면을 가지고 있던 index.jsp페이지로 다시 출력 보낸다
		// 이 때는 세션을 가지고 있으니 세션값을 꺼내서 ~~~님 환영합니다.라고 말할 수 있다 -> 인증 구현 끝
		resp.sendRedirect("./index.jsp");		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost 호출");
		
		// 오라클 서버에서 조회한 결과를 받아서 세션에 담기 위해 세션객체 생성하기
		HttpSession session = req.getSession();
		// 오라클 서버에 연동하는 일만 전담하는 ~~~.Dao클래스 인스턴스화
		MemberDao mDao = new MemberDao();
		
		// 사용자가 입력한 값 요청하기
		String mem_id = req.getParameter("mem_id");
		String mem_pw = req.getParameter("mem_pw");
		logger.info(mem_id + ", " + mem_pw);
		
		// MyBatis에 파라미터로 아이디와 비번을 Map에 담아서 넘겨야 하니까
		Map<String,Object> pmap = new HashMap<>();
		// 키값에 대응하는 사용자가 입력한 아이디와 비번 Map에 담기
		pmap.put("mem_id", mem_id);
		pmap.put("mem_pw", mem_pw);
		
		// 오라클 서버에 연동하는 login메소드 호출 - 호출 시 파라미터로 pmap 넘긴다
		// pmap 안에 사용자가 입력한 아이디와 비번이 있다
		Map<String,Object> rmap = mDao.login(pmap);		// 처리 결과를 Map으로 받아온다
		// 위에서 받아온 rmap에 들어있는 오라클 서버에서 select문으로 조회한 결과를
		// 꺼내서 세션 객체에 저장한다
		session.setAttribute("smem_id", rmap.get("MEM_ID"));
		session.setAttribute("smem_name", rmap.get("MEM_NAME"));
		
		// 로그인 화면을 가지고 있던 index.jsp페이지로 다시 출력 보낸다
		// 이 때는 세션을 가지고 있으니 세션값을 꺼내서 ~~~님 환영합니다.라고 말할 수 있다 -> 인증 구현 끝
		resp.sendRedirect("./index.jsp");
	}
}
