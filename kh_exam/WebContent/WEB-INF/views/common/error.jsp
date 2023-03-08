<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지([WEB-INF]error.jsp)</title>
</head>
<body>
<%
	// message = 게시글 등록 실패
	String message = (String)request.getAttribute("message");
	out.print(message);
%>
</body>
</html>