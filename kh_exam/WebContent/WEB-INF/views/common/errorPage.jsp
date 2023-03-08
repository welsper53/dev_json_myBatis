<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8"%>
<%@ page errorPage="./errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지(errorPage.jsp)</title>
</head>
<body>
<%
	String msg = (String)request.getAttribute("msg");
	out.print(msg);
%>
</body>
</html>