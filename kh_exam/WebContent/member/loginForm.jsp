<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%
	String s_name = null;
	s_name = (String)session.getAttribute("s_name");
	Map<String,Object> smap = (Map)session.getAttribute("smap");
	String s_name2 = null;
	if(smap!=null && smap.size()>0){
		s_name2 = smap.get("MEM_NAME").toString();		
	}
	out.print(s_name2);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
	function login(){
		document.querySelector('f_login').submit();	
	}
	function logout(){
		location.href="logout.jsp";	
	}
</script>
</head>
<body>
<%
	if(s_name == null){
%>
<form class="f_login" name="f_login" action="/kh_exam/member/loginAction.jsp" method="get">
<table border="1" width="300px">
	<tr>
		<td><input type="text" name="mem_id" size="10" placeholder="아이디"></td>
		<td rowspan="2" align="center"><button onclick="login()">로그인</button></td>
	</tr>
	<tr>
		<td><input type="text" name="mem_pw" size="10" placeholder="비밀번호"></td>
	</tr>
</table>
</form>
<%
	}
	else{
%>
<table border="1" width="300px">
	<tr>
		<td><%=s_name%>님 환영합니다.</td>
		<td rowspan="2" align="center"><button onclick="javascript:logout()">로그아웃</button></td>
	</tr>
</table>
<a href="../board/boardList.jsp">글목록</a>
<%
	}
%>
</body>
</html>