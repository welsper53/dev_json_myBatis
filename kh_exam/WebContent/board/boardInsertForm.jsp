<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String s_name = (String)session.getAttribute("s_name");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/board/binsert" method="get">
		<table align="center">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" readonly name="writer"
					value="<%=s_name%>"></td>
			</tr>
			<tr>
				<td>내용</td>

				<td><textarea cols="50" rows="7" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록하기"> 
				<a href="./boardList.bo">목록으로</a></td>
			</tr>
		</table>
	</form>
</body>
</html>