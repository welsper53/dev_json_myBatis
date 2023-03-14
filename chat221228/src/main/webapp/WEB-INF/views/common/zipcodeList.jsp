<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="./easyUI_common.jsp" %>
<%
	List<Map<String,Object>> zipcodeList = 
			(List<Map<String,Object>>)request.getAttribute("zList");
	int size = 0;
	
	if(zipcodeList!=null){
		size = zipcodeList.size();
		
		//out.print(size);
		
		for (int i=0; i<zipcodeList.size(); i++) {
			Map<String,Object> rMap = zipcodeList.get(i);
			
			out.print("" + rMap.get("UID_NO") + "<br>");
			out.print("" + rMap.get("SIGU") + "<br>");
			out.print("" + rMap.get("ZDO") + "<br>");
			out.print("" + rMap.get("DONG") + "<br>");
			out.print("" + rMap.get("ZIPCODE") + "<br>");
			out.print("" + rMap.get("ADDRESS") + "<br>");
			out.print("" + rMap.get("APTNAME") + "<br>");
			out.print("" + rMap.get("UPD_DATE") + "<br>");
			out.print("<br>");
		}
	}		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색기</title>
</head>
<body>
	<h3>우편번호 검색기</h3>

</body>
</html>