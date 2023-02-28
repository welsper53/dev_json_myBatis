package com.pojo.step1;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

/* FrontMVC11 -> Board1Controller -> Board1Logic -> Board1Dao -> MyBatis Layer
 * MyBatis가 실질적인 코드를 줄여주는 부분은 어디인가요?
 * 1) 커넥션 연결 - 단 오라클 서버에 대한 정보는 제공해줘야 한다
 * 	: 오라클 드라이버 클래스
 *  MyBatis
 * 	: 오라클 서버의 URL정보 - 멀티티어에서 유리한 thin 드라이버 방식, 계정이름과 PW */
public class Board1Dao {
	Logger logger = Logger.getLogger(Board1Logic.class);
	MyBatisCommonFactory 	mcf = new MyBatisCommonFactory();

	
	public List<Map<String, Object>> getBoardList() {
		logger.info("getBoardList 호출");
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		
		// 여기서 널로 하는 이유는 로직 클래스에서 인스턴스활르 해두었기 때문이다
		// NullPointException은 발생하지 않을 것이다
		List<Map<String, Object>> boardList = null;
		
		try {
			// 오라클 서버에 대한 정보를 가진 MyBatisConfig.xml문서를 읽는다 
			// -> MyBatisCommonFactory가 SqlSessionFactory().build(resource)
			// MyBatisCommonFactory에서 처리된 결과를 getter메소드를 통해 주입받음
			sqlSessionFactory = mcf.getSqlSessionFactory();
			// sqlSessionFactory객체가 제공한느 openSession()가 SqlSession객체를 생성한다
			sqlSession = sqlSessionFactory.openSession();
			
			// board.xml에서 id가 getBoardList를 찾아서 실행요청함
			// sqlSession은 쿼리문이 가능한 insert(),update(),delete(),selectOne,selectMap 등을 제공한다
			boardList = sqlSession.selectList("getBoardList", null); // id값
			
			logger.info(boardList);		// 3건 조회
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return boardList;
	}
}
