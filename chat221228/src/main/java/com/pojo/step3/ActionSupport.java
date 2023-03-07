package com.pojo.step3;

import java.io.IOException;

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
			obj = HandlerMapping.getController(upmu, req, res);
			logger.info("obj : " + obj);
			
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		}
		
		if (obj != null) {	// redirect: XXX.jsp 또는 forward: XXX.jsp
			String[] pageMove = null;
			ModelAndView mav = null;
			
			if (obj instanceof String) {
				if (((String) obj).contains(":")) {
					logger.info(":포함되어 있어요.");
					pageMove = obj.toString().split(":");
				} else {
					logger.info(":포함되어 있지 않아요.");
					pageMove = obj.toString().split("/");
				}
				logger.info(pageMove[0] + ", " + pageMove[1]);
			} else if (obj instanceof ModelAndView) {
				logger.info("");
				mav = (ModelAndView)obj;
				pageMove = new String[2];
				pageMove[0] = "";	// forward -> ViewResolver else if()타게됨 -> webapp
				pageMove[1] = mav.getViewName();
			}
			
			if (pageMove != null) {
				// pageMove[0] = redirect or forward
				// pageMove[1] = XXX.jsp
				new ViewResolver(req, res, pageMove);
			
			} // end of 페이지 이동처리에 대한 공통 코드 부분
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
