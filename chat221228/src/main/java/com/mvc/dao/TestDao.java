package com.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class TestDao {
	Logger logger = Logger.getLogger(TestDao.class);
	MyBatisCommonFactory 	mcf = new MyBatisCommonFactory();
	
	public List<Map<String, Object>> getBookMember() {
		System.out.println("getBookMember 호출");
		List<Map<String, Object>> mList = null;
			
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
			
			// member.xml에서 id가 getMemberList를 찾아서 실행요청함
			mList = sqlSession.selectList("getBookMember", pMap); // id값
			logger.info(mList);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
			System.out.println(mList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mList;
	}
	
	public String testDate() {
		System.out.println("testDate 호출");
		String cTime = null;
		
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
			
			// member.xml에서 id가 getMemberList를 찾아서 실행요청함
			cTime = sqlSession.selectOne("testDate", pMap); // id값
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
			System.out.println(cTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cTime;
	}
}
