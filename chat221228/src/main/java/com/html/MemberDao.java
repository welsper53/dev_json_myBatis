package com.html;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
import com.util.DBConnectionMgr;
import com.util.MyBatisCommonFactory;
import com.vo.MemberVO;


public class MemberDao {
	DBConnectionMgr 		dbMgr = new DBConnectionMgr();
	MyBatisCommonFactory 	mcf = new MyBatisCommonFactory();
	Connection 				con = null;
	PreparedStatement 		pstmt = null;
	ResultSet 				rs = null;
	
	public List<Map<String, Object>> getMemberList() {
		System.out.println("getmemberList 호출");
		List<Map<String, Object>> memList = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_no, mem_id, mem_name from member");
		
		try {
			// 물리적으로 떨어져 있는 오라클 서버에 연결통로
			con = dbMgr.getConnection();
			// 연결통로를 통해서 select문을 가져가고 오라클서버에게 요청하는 객체로딩
			pstmt = con.prepareStatement(sql.toString());
			// 오라클서버의 커서를 조작할 객체로딩
			rs = pstmt.executeQuery();
			
			memList = new ArrayList<Map<String,Object>>();
			Map<String, Object> rmap = null;
			
			while (rs.next()) {
				rmap = new HashMap<String, Object>();
				rmap.put("mem_no", rs.getInt("mem_no"));
				rmap.put("mem_id", rs.getString("mem_id"));
				rmap.put("mem_name", rs.getString("mem_no"));
				
				memList.add(rmap);
			}
			
			System.out.println(memList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원을 반납하기 - 명시적으로 반납할것 - 실행시점을 당기기 위해 - 튜닝팀의 권장사항
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return memList;
	} // end of getMemberList
	
	
	public String jsonMemberList() {
		System.out.println("jsonMemberList 호출");
		List<Map<String, Object>> memList = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_no, mem_id, mem_name from member");
		
		try {
			// 물리적으로 떨어져 있는 오라클 서버에 연결통로
			con = dbMgr.getConnection();
			// 연결통로를 통해서 select문을 가져가고 오라클서버에게 요청하는 객체로딩
			pstmt = con.prepareStatement(sql.toString());
			// 오라클서버의 커서를 조작할 객체로딩
			rs = pstmt.executeQuery();
			
			memList = new ArrayList<Map<String,Object>>();
			Map<String, Object> rmap = null;
			
			while (rs.next()) {
				rmap = new HashMap<String, Object>();
				rmap.put("mem_no", rs.getInt("mem_no"));
				rmap.put("mem_id", rs.getString("mem_id"));
				rmap.put("mem_name", rs.getString("mem_no"));
				
				memList.add(rmap);
			}
			
//			System.out.println(memList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원을 반납하기 - 명시적으로 반납할것 - 실행시점을 당기기 위해 - 튜닝팀의 권장사항
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		Gson g = new Gson();
		String temp = null;
		temp = g.toJson(memList);
		
		return temp;
	} // end of jsonMemberList
	
	
	public List<Map<String, Object>> myBatisMemberList() {
		System.out.println("myBatisMemberList 호출");
		
		// 물리적으로 떨어져 있는 서버와 연결통로 확보 
		//-> MyBatisConfig.xml 문서 정보(드라이버, 오라클URL, 계정 정보, 쿼리문 담은 xml) 참조 
		SqlSessionFactory sqlSessionFactory = null;
		// SqlSession으로 commit과 rollback 지원받음
		SqlSession sqlSession = null;
		List<Map<String, Object>> memList = null;
		Map<String, Object> pmap = new HashMap<String, Object>();
		
		pmap.put("mem_no", 3);
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();
			
			// member.xml에서 id가 getMemberList를 찾아서 실행요청함
			memList = sqlSession.selectList("getMemberList", pmap); // id값
			
			// INSERT, UPDATE, DELETE 시 커밋 호출할 때 사용함
			// sqlSession.commit();
			System.out.println(memList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memList;
	} // end of myBatisMemberList
	
	
	public List<MemberVO> myBatisMemberList2() {
		SqlSessionFactory sqlSessionFactory = null;
		
		SqlSession sqlSession = null;
		List<MemberVO> memList = null;
		Map<String, Object> pmap = new HashMap<String, Object>();
		
		pmap.put("mem_no", 0);
		
		try {
			sqlSessionFactory = mcf.getSqlSessionFactory();
			sqlSession = sqlSessionFactory.openSession();

			// 파라미터 순서
			// id="myBatisMemberList2" / parameterType=pmap 
			// 반환값
			// resultType="com.vo.MemberVO"
			memList = sqlSession.selectList("myBatisMemberList2", pmap);
			
			System.out.println(memList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return memList;
	} // end of myBatisMemberList
	
	
	public static void main(String[] args) {
		MemberDao memDao = new MemberDao();
		Gson g = new Gson();
		
//		memDao.getMemberList();
//		
//		String temp = memDao.jsonMemberList();
//		System.out.println(temp);
		
//		String temp = memDao.myBatisMemberList();
		
//		List<Map<String, Object>> memList= null;
//		memList = memDao.myBatisMemberList();
//		
//		String temp = g.toJson(memList);
//		System.out.println(temp);
		
		List<MemberVO> memList2= null;
		memList2 = memDao.myBatisMemberList2();
		
		String temp2 = g.toJson(memList2);
		System.out.println(temp2);
		
		
	}
}
