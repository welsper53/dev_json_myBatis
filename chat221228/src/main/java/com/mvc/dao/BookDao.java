package com.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.util.MyBatisCommonFactory;

public class BookDao {
	Logger logger = Logger.getLogger(BookDao.class);
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	public String currentDate() {
		System.out.println("currentDate 호출");
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		String ctime = null;
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			//member.xml에서 id가 getMemberList를 찾아서 실행요청함.
			ctime = sqlSession.selectOne("currentDate");//id값
			System.out.println(ctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctime;		
	}
	public String testDate() {
		System.out.println("testDate 호출");
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		String ctime = null;
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			//member.xml에서 id가 getMemberList를 찾아서 실행요청함.
			ctime = sqlSession.selectOne("testDate");//id값
			System.out.println(ctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctime;		
	}
}
