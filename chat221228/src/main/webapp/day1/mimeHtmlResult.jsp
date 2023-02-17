<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바 import -->
<%@ page import="java.util.Map, java.util.HashMap, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MimeHtmlServlet2 응답페이지</title>
</head>
<body>
	<h2>MimeHtmlServlet2 응답페이지</h2>
	<%
	// 스크립틀릿 안에서 선언한 변수는 지변이다
	// 왜냐면?? -> a.jsp -> a_jsp.java -> service메소드 안에 들어간다(지역변수화)
		String myName = null;
		// getAttribute의 리턴타입은 Object이다 (getParameter의 리턴타입은 String이다.)
		myName = (String)session.getAttribute("myName");
		out.print(myName);		
		
		out.print("<hr>");
		
		Integer age = null;
		age = (Integer)session.getAttribute("age");
		out.print(age);		
		
		out.print("<hr>");
		
		Map<String,Object> rmap = (Map<String,Object>)session.getAttribute("rmap");
		if (rmap != null) {		// NullPointException 방어코드
			Object keys[] = rmap.keySet().toArray();	// 키 명을 배열값으로 지정
			for(int i=0; i<keys.length; i++) {
				out.print(rmap.get(keys[i]));
				out.print("<br/>");
			}
		}
		
		out.print("<hr>");
		List<Map<String,Object>> mList = (List<Map<String,Object>>)session.getAttribute("mList");
		if (mList != null) {		// NullPointException 방어코드
			for (int i=0; i<mList.size(); i++) {
				Map<String,Object> map = mList.get(i);
				
				Object keys[] = map.keySet().toArray();	// 키 명을 배열값으로 지정
				for(int j=0; j<keys.length; j++) {
					out.print(map.get(keys[j]));
					out.print("<br/>");
				}
				out.print("<hr>");
			}
		}
	%>
</body>
</html>