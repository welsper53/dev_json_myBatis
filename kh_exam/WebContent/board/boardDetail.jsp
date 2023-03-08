<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.Board" %>    
<%
	Board board = (Board)request.getAttribute("board");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세보기</title>
<script>
	function boardUpdate(){
		document.getElementById("f_board").submit();
	}
</script>
</head>
<body>
<button onclick="boardUpdate()">수정</button>
<form id="f_board" method="get" action="./update.bo">
<input type="hidden" name="id" value="<%=board.getId()%>">
<table border="1" width="400px" height="180px">
	<tr>
		<th width="100px">제목</th>
		<td><input type="text" name="title" size="15" value="<%=board.getTitle()%>"/></td>
	</tr>
	<tr>
		<th width="100px">작성자</th>
		<td><input type="text" name="writer" size="15" value="<%=board.getWriter()%>"/></td>
	</tr>
	<tr>
		<th width="100px">내용</th>
		<td><textarea rows="7" cols="37" name="content"><%=board.getContent()%></textarea></td>
	</tr>
</table>
</form>
</body>
</html>