package com.pojo.step3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class Board3Dao {
	Logger logger = Logger.getLogger(Board3Dao.class);
	// inssert here - 이종간인 MyBatis연동에 필요한 공통 클래스 객체 주입 필요
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();

	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출");
		
		List<Map<String, Object>> bList = null;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			bList = sqlSession.selectList("boardList", pMap); // id값
			logger.info(bList);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bList;
	}
	
	public int boardInsert(Map<String,Object> pMap) {
		logger.info("boardList 호출");
		int result = 0;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			// insert이지만 update하는 이유는 리턴타입이 Object이기 때문이다
			// 메소드이름은 상관없이 해당 쿼리문을 id로 찾기 때문이다
			result = sqlSession.update("boardMInsert", pMap); // id값
			
			if (result == 1) {
				sqlSession.commit();
			}
			logger.info(result);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
	
	public int getBGroup() {
		int result  = 0;
		logger.info("getBGroup호출");
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			result = sqlSession.selectOne("getBGroup", "");
			logger.info(result);//채번한 글그룹번호 
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		return result;
	}
	
	public int getBNo() {
		int result  = 0;
		logger.info("getBNo호출");
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			result = sqlSession.selectOne("getBNo", "");
			logger.info(result);//채번한 글번호 
		} catch (Exception e) {
			e.printStackTrace();
		}				
		
		return result; 
	}
	
	public void bStepUpdate(Map<String, Object> pMap) {
		int result =0;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.update("bStepUpdate", pMap);
			if(result == 1) {
				sqlSession.commit();
			}
			logger.info(result);//
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}//end of bStepUpdate
	
	/* 글 수정하기 구현
	 * @param pMap - 사용자가 입력한 값 받아옴*/
	public int boardMUpdate(Map<String, Object> pMap) {
		int result =0;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.update("boardMUpdate", pMap);
			if(result == 1) {
				sqlSession.commit();
			}
			logger.info(result);//
		} catch (Exception e) {
			e.printStackTrace();
		}			
		 return result;
	}
	
	/* 글 조회수 수정하기 구현
	 * @param int - 글 번호 가져오기*/
	public int hitCount(int bm_no) {
		int result =0;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.update("hitCount", bm_no);
			if(result == 1) {
				sqlSession.commit();
			}
			logger.info(result);//
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return result;
	}
	
	/* 글 삭제하기 구현
	 * @param pMap - 사용자가 입력한 값 받아옴*/
	public int boardMDelete(Map<String, Object> pMap) {
		int result =0;
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		
		try { 
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			int bm_no = 0;
			if (pMap.get("bm_no") != null) {
				bm_no = Integer.parseInt(pMap.get("bm_no").toString());
			}
			logger.info(bm_no);
			
			result = sqlSession.update("boardMDelete", bm_no);
			if(result == 1) {
				sqlSession.commit();
			}
			
			logger.info(result);//
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return result;
	}

	public int boardSInsert(Map<String, Object> pMap) {
		logger.info("boardSInsert 호출");
		int result = 0;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			// insert이지만 update하는 이유는 리턴타입이 Object이기 때문이다
			// 메소드이름은 상관없이 해당 쿼리문을 id로 찾기 때문이다
			result = sqlSession.update("boardSInsert", pMap); // id값
			
			if (result == 1) {
				sqlSession.commit();
			}
			logger.info(result);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
}

/* ActionServlet(페이지이동) - XXXController - XXXLogic - XXXDao - MyBatis Layer
 * 
 */

