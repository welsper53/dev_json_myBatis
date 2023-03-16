package com.pojo.step3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pojo.step2.Board2Controller;

public class ActionSupport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ActionSupport.class);
	
	protected void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("doService호출");
		
		String uri = req.getRequestURI();
		logger.info(uri);		// "board/getBoardList.st3"
		
		String context = req.getContextPath();		// "/" -> server.xml(<ContextPath>)
		logger.info(context);	// " "
		
		// http://localhost:9000/board2/getBoardList.st3
		// http://localhost:9000/업무명폴더명/getBoardList.st3
		// 톰캣서버에 요청할 때 사용되는 주소값을 가지고 업무명과 업무에 필요한 이름으로
		// 분리시켜 사용자 요청에 따라 처리를 담당할 ~~~Controller객체를 주입하는데 사용한다
		String command = uri.substring(context.length() + 1);
		logger.info(command);	// board/getBoardList.st3
		
		int end = command.lastIndexOf(".");		// st3 잘라내기 위해 사용
		logger.info(end);		// 16
		
		command = command.substring(0, end);
		logger.info(command);	// board/getBoardList
		
		String upmu[] = null;		// upmu[0] = 업무명|폴더명, upmu[1] = 요청기능이름
		upmu = command.split("/");
		
		logger.info(upmu[0] + ", " + upmu[1]);	// board, getBoardList
		
		/* upmu[0] = 업무명|폴더명, upmu[1] = 요청기능이름 
		 * BoardController에서 req와 res를 받을 수 있는건 execute(req, res) 메소드 뿐이다
		 * 그러면 각 기능 별로 사용하려면 메소드로 나누면 안될 것 같다
		 * 그럼 if문으로 분기하고 BoardLogic.java에서 메소드를 분리해 나가자 */
		req.setAttribute("upmu", upmu);
		
		Object obj = "";
		
		try {
			// 여기를 진행하는 것은 내려가는 길(서버 안으로)
			obj = HandlerMapping.getController(upmu, req, res);
			logger.info("obj : " + obj);
			
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		}
		
		// 여기는 응답으로 나가는 길이다(클라이언트로)
		// ModelAndView 또는 String
		if (obj != null) {	// redirect: XXX.jsp 또는 forward: XXX.jsp
			// pageMove기억
			// - 응답페이지의 위치
			String[] pageMove = null;
			ModelAndView mav = null;
			logger.info(obj.toString());
			
			// String이면 webapp에 배치한다
			if (obj instanceof String) {
				logger.info("obj가 String일 때");
				
				if (((String) obj).contains(":")) {
					logger.info(":포함되어 있어요.");
					pageMove = obj.toString().split(":");
				} else if (((String) obj).contains("/")){
					logger.info("/포함되어 있어요.");
					pageMove = obj.toString().split("/");
				} else {
					// Spring Boot 	: @RestController 사용
					// Spring4버전  	: ResponseBody 사용
					// text/plain -> text형식 -> String
					logger.info(":콜론도 /도 포함되어 있지 않아요.");
					
					pageMove = new String[1];
					pageMove[0] = obj.toString();
					
					logger.info(obj.toString());
				}
				
				logger.info(pageMove[0]);
			} 
			
			// ModelAndView인 경우 WEB-INF 아래 배치한다
			else if (obj instanceof ModelAndView) {
				logger.info("obj가 ModelAndView일때");
				mav = (ModelAndView)obj;
				pageMove = new String[2];
				pageMove[0] = "";	// forward -> ViewResolver else if()타게됨 -> webapp
				pageMove[1] = mav.getViewName();
				
				logger.info(pageMove[0] + ", " + pageMove[1]);
			}
			
			logger.info("Object가 String일 때와 ModelAndview일 때가 끝나는 지점...");
			logger.info("pageMove ===> " + pageMove);
			logger.info("pageMove.length ===> " + pageMove.length);
			if (pageMove != null && pageMove.length == 2) {
				// pageMove[0] = redirect or forward
				// pageMove[1] = XXX.jsp
				new ViewResolver(req, res, pageMove);
			} // end of 페이지 이동처리에 대한 공통 코드 부분
			
			// pageMove배열이 한 개인 경우
			// - 리턴값이 String인 경우
			//   => @RestController, @ResponseBody
			// 예) 글작성 이미지 추가 후 출력
			else if (pageMove != null && pageMove.length == 1) {
				// 마임타입 설정
				res.setContentType("text/plain;charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print(pageMove[0]);
			}
		}
	} // end of doService

	@Override
	protected void doGet(HttpServletRequest  req, HttpServletResponse resp) throws ServletException, IOException {
		// 브라우저의 주소창을 통해 요청하는 건 모두 GET방식이다 - doGet()호출
		logger.info("doGet 호출");
		doService(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost 호출");
		doService(req,resp);
	}
}
