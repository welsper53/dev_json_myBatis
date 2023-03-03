package com.pojo.step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class Board2Dao {
	Logger logger = Logger.getLogger(Board2Logic.class);
	// inssert here - 이종간인 MyBatis연동에 필요한 공통 클래스 객체 주입 필요
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();

	public List<Map<String, Object>> boardList() {
		logger.info("Board2Dao.boardList 호출");
		List<Map<String, Object>> bList = null;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("mem_id",  "tomato");
			pMap.put("mem_pw",  "123");
			
			bList = sqlSession.selectList("boardList", pMap); // id값
			logger.info(bList);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
			System.out.println(bList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bList;
	}
	
	
}

/* ActionServlet(페이지이동) - XXXController - XXXLogic - XXXDao - MyBatis Layer
 * 
 */
