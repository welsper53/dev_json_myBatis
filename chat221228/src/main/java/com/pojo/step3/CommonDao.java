package com.pojo.step3;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

//@Service
//@Repository
public class CommonDao {
	Logger logger = Logger.getLogger(CommonDao.class);
	MyBatisCommonFactory mcf  = new MyBatisCommonFactory();
	
	public List<Map<String, Object>> zipcodeList(Map<String, Object> pMap) {
		logger.info("zipcodeList 호출");
		
		List<Map<String, Object>> zList = null;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			zList = sqlSession.selectList("zipcodeList", pMap); // id값
			logger.info(zList);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return zList;
	}
}
