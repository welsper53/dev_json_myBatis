package com.pojo.step3;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Board3Logic {
	Logger logger = Logger.getLogger(Board3Logic.class);
	Board3Dao boardDao = new Board3Dao();

	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출: " + pMap);
		
		List<Map<String,Object>> bList = null;
		bList = boardDao.boardList(pMap);
		logger.info(bList);
		
		return bList;
	}	
	
	public int boardUpdate(Map<String, Object> pMap) {
		logger.info("boardUpdate호출 : " + pMap);
		int result = 0;
		
		result = boardDao.boardMUpdate(pMap);
		
		return result;
	}
	
	public int boardDelete(Map<String, Object> pMap) {
		logger.info("boardDelete호출 : " + pMap);
		int result = 0;
		
		result = boardDao.boardMDelete(pMap);
		
		return result;
	}

	public int boardInsert(Map<String, Object> pMap) {
		logger.info("boardInsert 호출: " + pMap);
		int result = 0;
		int bm_no = 0;
		int bm_group = 0;
		
		bm_no = boardDao.getBNo();
		pMap.put("bm_no", bm_no);
		
		// Map안에서 꺼낸다는 건 화면에서 넘어온 값이라는 뜻
		if (pMap.get("bm_group") != null) {
			bm_group  = Integer.parseInt(pMap.get("bm_group").toString());
		}
		
		// 댓글쓰기야?
		if (bm_group > 0) {
			/* UPDATE board_master_t
				    SET bm_step = bm_step + 1
				WHERE bm_group = #{bm_group}
				    AND bm_step > #{bm_group} */
			boardDao.bStepUpdate(pMap);	// bm_group=8, bm_step>1
			
			pMap.put("bm_pos", Integer.parseInt(pMap.get("bm_pos").toString())+1);
			pMap.put("bm_step", Integer.parseInt(pMap.get("bm_step").toString())+1);
		}
		// 새글쓰기야? - 그룹번호 채번 포함
		else {
			bm_group =  boardDao.getBGroup();
			logger.info("새글쓰기 로직 호출 => "+bm_group);
			
			pMap.put("bm_group", bm_group);
			pMap.put("bm_pos", 0);
			pMap.put("bm_step", 0);
		}
		
		result = boardDao.boardInsert(pMap);
		
		return result;
	}
	
}
