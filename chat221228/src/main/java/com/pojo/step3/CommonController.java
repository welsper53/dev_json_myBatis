package com.pojo.step3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.util.HashMapBinder;

public class CommonController implements Controller3{
	Logger logger = Logger.getLogger(CommonController.class);
	private CommonLogic commonLogic = new CommonLogic();

	
	@Override
	public ModelAndView zipcodeList(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardList 호출");
		List<Map<String,Object>> zList = null; 
		
		// 사용자가 조건 검색을 원하는 경우 - 조건 값을 전달할 객체 생성함
		// MyBatis에서는 동적쿼릴르 지원하므로 하나로 2가지 경우 사용 가능함
		Map<String,Object> pMap = new HashMap<>();
		
		HashMapBinder hmb = new HashMapBinder(req);
		logger.info(hmb);
		hmb.bind(pMap);
		
		zList = commonLogic.zipcodeList(pMap);
		logger.info(zList);
		
		ModelAndView mav = new ModelAndView(req);
		mav.setViewName("common/zipcodeList");
		mav.addObject("zList", zList);
		
		return mav;
	}

	@Override
	public Object jsonBoardList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardList(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardDetail(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageUpload(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageDownload(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object imageGet(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardInsert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object boardDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
