package com.example.demo.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	// mybatis.3.5.6.jar -> SqlSession sqlsession.commit(), sqlSession.rollback()
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;		// mybatis-spring.jar 제공 API
	
	public Map<String, Object> memberLogin(Map<String,Object> pMap) {
		Map<String, Object> rMap = null;
		
		rMap = sqlSessionTemplate.selectOne("loginMember", pMap);
		
		return rMap;
	}
}
