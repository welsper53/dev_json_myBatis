package com.ncs.test.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	// mybatis.3.5.6.jar -> SqlSession sqlsession.commit(), sqlSession.rollback()
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;		// mybatis-spring.jar 제공 API
	
	public int login(Map<String,Object> pMap) {
		int result = 0;
		
		result = sqlSessionTemplate.selectOne("login", pMap);
		
		return result;
	}
}
