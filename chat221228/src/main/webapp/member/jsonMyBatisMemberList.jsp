<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.util.*, java.util.*" %>
<%@ page import ="com.google.gson.Gson" %>
<%@ page import ="org.apache.ibatis.session.*" %>
    
<%
	//스크립플릿
	//out.print("jsonMyBatisMemberList페이지");
	MyBatisCommonFactory mcf = new MyBatisCommonFactory();
	List<Map<String,Object>> memList = null;
	
	Map<String, Object> pmap = new HashMap<String, Object>();
	pmap.put("mem_no", 0);
	
	// 물리적으로 떨어져 있는 서버와 연결통로 확보 
	//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
	SqlSessionFactory sqlSessionFactory = null;
	
	// SqlSession으로 commit과 rollback 지원받음
	SqlSession sqlSession = null;
		
	try {
		sqlSessionFactory = mcf.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		// member.xml에서 id가 getMemberList를 찾아서 실행요청함
		memList = sqlSession.selectList("getMemberList", pmap); // id값
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	Gson g= new Gson();
	String temp = null;
	temp = g.toJson(memList);
	
	out.print(temp);
%>