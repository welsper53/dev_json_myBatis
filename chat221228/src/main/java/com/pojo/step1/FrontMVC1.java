package com.pojo.step1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FrontMVC1 extends HttpServlet {
	Logger logger = Logger.getLogger(FrontMVC1.class);
	
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService호출");
		
		String uri = req.getRequestURI();
		logger.info(uri);		// "dept/getDeptList.st1"
		
		String context = req.getContextPath();		// "/" -> server.xml(<ContextPath>)
		logger.info(context);	// " "
		
		// http://localhost:9000/dept/getDeptList.st1
		// http://localhost:9000/업무명폴더명/getDeptList.st1
		// http://localhost:9000/member/getMemberList.st1
		// http://localhost:9000/board/getBoardList.st1
		// http://localhost:9000/board/boardInsert.st1
		// http://localhost:9000/board/boardUpdate.st1
		// http://localhost:9000/board/boardDelete.st1
		// 톰캣서버에 요청할 때 사용되는 주소값을 가지고 업무명과 업무에 필요한 이름으로
		// 분리시켜 사용자 요청에 따라 처리를 담당할 ~~~Controller객체를 주입하는데 사용한다
		String command = uri.substring(context.length() + 1);
		System.out.println(command);	// dept/getDeptList.st1
		
		int end = command.lastIndexOf(".");		// st1 잘라내기 위해 사용
		System.out.println(end);		// 16
		
		command = command.substring(0, end);
		System.out.println(command);	// dept/getDeptList
		
		String upmu[] = null;		// upmu[0] = 업무명|폴더명, upmu[1] = 요청기능이름
		upmu = command.split("/");
		for (String imsi : upmu) {
			System.out.println(imsi);	// dept		getDeptList
		}
		
		ActionForward af = null;
		
		// 게으른 인스턴스화
		// -> 아직 업무명이 정해지지 않았기 때문
		// 업무명이 Controller클래스의 접두어이다
		DeptController deptController = null;
		EmpController empController = null;
		
		/* upmu[0] = 업무명|폴더명, upmu[1] = 요청기능이름 
		 * DeptController에서 req와 res를 받을 수 있는건 execute(req, res) 메소드 뿐이다
		 * 그러면 각 기능 별로 사용하려면 메소드로 나누면 안될 것 같다
		 * 그럼 if문으로 분기하고 DeptLogic.java에서 메소드를 분리해 나가자 */
		req.setAttribute("upmu", upmu);
		
		if ("dept".equals(upmu[0])) {
			// request객체는 저장소이다
			// 저장: setAttribute
			// 출력: getAttribute
			
			// 인스턴스화 -> execute() 호출하기 위해 (500: NullPointException 발생하므로)
			deptController = new DeptController();
			af = deptController.execute(req, res);
			// deptController는 서블릿이 아니여서 req와 res를 톰캣으로부터 주입받을 수 없다
			// (객체가 아니여서???)
		} else if ("emp".equals(upmu[0])) {
			// 인스턴스화 -> execute() 호출하기 위해 (500: NullPointException 발생하므로)
			empController = new EmpController();
			af = empController.execute(req, res);
		}
		
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
	} // end of doService

	@Override
	protected void doGet(HttpServletRequest 
			req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req,resp);
	}


}
