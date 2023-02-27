<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String menu = (String)request.getAttribute("menu");
	String side = (String)request.getAttribute("side");

	// null이 출력된다. 왜냐하면 리퀘스트가 새로운 req 요청객체이므로
	// 서블릿에서 주입받은 request에 담겨 있는것이지 
	// 페이지 이동으로 출력된 화면이 가진 request에는 menu 담지 않았다
	// => 기존의 요청이 끊어지고 url이 바뀌었기 때문
	// 새로운 요청이 서버에 전달되어서 페이지가 출력된것이다
	out.print("내가 선택한 메뉴는 " + menu + "이고 선택한 사이드는 " + side + "이다<br>");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>crudTest.jsp</title>
</head>
<body>
	crudTest페이지 입니다.
</body>
</html>

<!-- 
기존의 요청(url:~~.st1)이 끊어지고 새로운 요청이 들어왔다 
url:~~.st1 요청했더니 (web.xml -> st1찾아서 -> servlet-name -> servlet -> classname
-> GET방식이니까 doGet메소드가 (누가?-톰캣,시스템호출-CallBack)호출되고
그 안에서 doService메소드를 호출한다. 이때 파라미터로 톰캣에서 주입받은 (원본-얕은복사)'req와 res'를 넘겨준다 

서블릿이 호출될 때 톰캣서버로 부터 주입 받은 request객체와 response객체가
아닌 새로운 request객체와 response객체라는 것이다
-->