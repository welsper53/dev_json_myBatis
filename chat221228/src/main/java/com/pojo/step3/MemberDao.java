package com.pojo.step3;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class MemberDao {
	Logger logger = Logger.getLogger(MemberDao.class);
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();

	public Map<String, Object> login(Map<String, Object> pMap) {
		logger.info("login 호출");
		
		Map<String, Object> rmap = null;
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		// MyBatisConfig.xml문서를 통해 스캔한 오라클 서버 정보로 연결통로 확보
		SqlSessionFactory sqlSessionFactory = null;
		
		// 위에서 SqlSessionFactory생성되면 쿼리문을 요청하는 selectOne메소드가 필요한데
		// 그 메소드를 제공하는 클래스 및 commit, rollback지원
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		try {
			// 공통코드에서 연결통로 확보
			sqlSessionFactory = mcf.getSqlSessionFactory();
			// 연결 통로 확보로 생성된 객체로 SqlSession로딩하기
			sqlSession = sqlSessionFactory.openSession();
			logger.info("MemberDao pMap ===> " + pMap);
			
			// member.xml에서 id가 getMemberList를 찾아서 실행요청함
			rmap = sqlSession.selectOne("login", pMap); // id값
			logger.info("MemberDao rmap ===> " + rmap);		// 3건 조회
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
			System.out.println(rmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rmap;
	}
}
