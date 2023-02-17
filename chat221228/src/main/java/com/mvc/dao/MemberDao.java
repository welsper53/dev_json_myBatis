package com.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class MemberDao {
	// log4j모듈 활용해서 로그출력 -> 날짜와 시간, 클래스명, 라인번호, 링크
	// System.out.print() 대신에 사용한다
	Logger logger = Logger.getLogger(MemberDao.class);
	
	// DAO클래스와 오라클 서버 사이에 MyBatis Layer에 필요한 설정 내용담기
	// member.xml의 물리적인 위치와 오라크서버의 정보가 담긴 
	// MyBatisConfig.xml의 정보를 IO로 읽어오는 코드가 포함됨
	// mybatis는 쿼리문을 xml문서에 따로 관리한다
	// 자바로 관리하는 것보다 컴파일을 하지 않아도 되고 버전관리에도 효과적임
	MyBatisCommonFactory 	mcf = new MyBatisCommonFactory();
	
	public Map<String, Object> login(Map<String,Object> pMap) {
		System.out.println("getBookMember 호출");
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
	
	public static void main(String[] args) {
		MemberDao mDao = new MemberDao();
		Map<String,Object> pMap = new HashMap<>();
		
		pMap.put("mem_id","tomato");
		pMap.put("mem_pw","123");
		Map<String,Object> rMap = mDao.login(pMap);
		System.out.print(rMap);
	}
}
