package com.pojo.step1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BoardController1 implements Action1 {
	Logger logger = Logger.getLogger(BoardController1.class);

	@Override
	public ActionForward1 execute(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info("execute 호출");
		logger.info("request : " + req);
		logger.info("response : " + res);
		
		// ActionForward1 af = null;	// 널 초키화 - NullPointException - 화면처리X
		ActionForward1 af = new ActionForward1();
		// FrontMVC11에서 결정된 정보를 Board1Controller에서 사용하기 위해 요청객체 저장소에 있는 값 가져오기
		String[] upmu = (String[])req.getAttribute("upmu");
		Board1Logic boardLogic = new Board1Logic();
		// FrontMVC11은 실제 업무를 처리하는 클래스가 아니므로
		// 실제 게시판 구현의 마지막 단계는 Board1Controller클래스이니까 여기서
		// path정보와 리다이렉트 여부를 결정해 주는 것이 맞다 - 업무 담당자의 마지막 위치이니까...
		String path = null;
		boolean isRedirect = true;
		
		// 너 게시글 목록을 출력할거야?
		if ("getBoardList".equals(upmu[1])) {	// upmu[1]
			logger.info("boardInsert 호출");
			List<Map<String,Object>> boardList = boardLogic.getBoardList();
			
			// 조회된 결과를 요청객체에 담기
			req.setAttribute("boardList", boardList);
			// 응답페이지 이름 결정
			path = "getBoardList.jsp";
			// 만일 vue.js나 react.js와 같은 UI라이브러리와 연계 시 json포맷을 생성하는 jsp페이지로 연결시켜야한다 - 주의!
			// path = "jsonGetBoardList.jsp" application/json
			// redirect 또는 forward로 할지 결정
			isRedirect = false;	// false는 forward이다 (요청을 유지 - 주소창은 그대로인데 페이지는 바뀜)
		} 
		// 글등록 원해?
		else if ("jsonBoardList".equals(upmu[1])) {
			logger.info("jsonBoardList 호출");
			String jsonDoc = boardLogic.jsonBoardList();
			
			logger.info("jsonDoc : " + jsonDoc);
			
			req.setAttribute("jsonDoc", jsonDoc);
			path = "jsonBoardList.jsp";
			isRedirect = false;	// false는 forward이다 (요청을 유지 - 주소창은 그대로인데 페이지는 바뀜)
		}		
		// 부서 등록할거니?
		else if ("boardInsert".equals(upmu[1])) {
			logger.info("boardInsert 호출");
			// insert into board_master_t(bm_no, bm_title, bm_writer, ...) value (?, ?, ?)
			int result = boardLogic.boardInsert();
		}
		// 부서정보 수정할거니?
		else if ("boardUpdate".equals(upmu[1])) {
			logger.info("boardUpdate 호출");
			// UPDATE board_master_t SET bm_step = ? ...
			int result = boardLogic.boardUpdate();
		}
		// 너희 부서 없어졌다
		else if ("boardDelete".equals(upmu[1])) {
			logger.info("boardDelete 호출");
			// DELTE FROM board_master_t WHERE bm_step = ? ...
			int result = boardLogic.boardDelete();
		}
		
		af.setPath(path);
		af.setRedirect(isRedirect);
		
		return af;
	}

	public static void main(String[] args) {
		BoardController1 bc = new BoardController1();
		
		try {
			bc.execute(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
