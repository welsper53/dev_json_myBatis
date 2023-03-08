<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
</head>
<body>
      NullPointerException 발생
      <%
      	out.print("msg : " + msg);
      %>
</body>
</html>
