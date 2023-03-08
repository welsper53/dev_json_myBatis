<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="32kb"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	response.sendRedirect("./boardDetail.bo?id="+id);
%>