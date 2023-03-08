<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page errorPage="./errorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
      <meta charset="UTF-8">
</head>
<body>
      <%
            String nullStr = null;
            out.println(nullStr.toString());	// NullPointException
      %>
</body>
</html>
