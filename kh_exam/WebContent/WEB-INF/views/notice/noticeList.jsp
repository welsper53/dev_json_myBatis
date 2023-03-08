<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.vo.Notice, mvc.kh.NoticeService" %>    
<%
	ArrayList<Notice> list = (ArrayList)request.getAttribute("list");
	int size = list.size();
	out.print("size:"+size);
	out.print("<br>");
	out.print(list.get(0).getTitle());
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 조회[WEB-INF]</title>
</head>
<body>

</body>
</html>