<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mem_id = request.getParameter("mem_id");
	String mem_pw = request.getParameter("mem_pw");
	String db_id = "test";
	String db_pw = "123";
	String mem_name = null;
	if((db_id.equals(mem_id))&&(db_pw.equals(mem_pw))){
		mem_name = "강감찬";
		session.setAttribute("s_name", mem_name);
		response.sendRedirect("../board/boardList.bo");
	}
%>