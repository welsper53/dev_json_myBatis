<%@ page language="java" contentType="text/html; charset=UTF-8" buffer="32kb"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.vo.Board" %>    
<%
	String s_name = (String)session.getAttribute("s_name");
	if(s_name == null){
		response.sendRedirect("../member/loginForm.jsp");
	}
	// Action -> JSP
	List<Board> boardList = (List)request.getAttribute("boardList");
	int size = 0;
	if(boardList !=null){
		size = boardList.size();
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<script type="text/javascript">
	function boardInsert(){
		location.href="./boardInsertForm.jsp";
	}
	function boardDetail(id){
		location.href="./boardDetail.bo?id="+id;
	}
	function logout(){
		location.href="../member/logout.jsp";
	}
</script>
</head>
<body>
<button onclick="boardInsert()">글쓰기</button> 
<button onclick="logout()">로그아웃</button> 
<table border="1">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>&nbsp;</th>
	</tr>
<%
	for(int i=0;i<size;i++){
		Board board = boardList.get(i);
%>	
	<tr>
		<td><%= board.getId()%></td>
		<td><%= board.getTitle() %></td>
		<td><%= board.getContent() %></td>
		<td><button onclick="boardDetail('<%=board.getId()%>')">수정화면</button></td>
	</tr>
<%
	}
%>	
</table>
</body>
</html>