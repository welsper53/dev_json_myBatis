<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="root"></div>
	<script type="text/javascript">
		const rootElement = document.querySelector("#root");
		const h1Element = document.createElement("h1");
		h1Element.textContent = "DOM Make";		// 태그 안에 텍스트노드 작성
		rootElement.appendChild(h1Element);		// #root의 자식노드 생성
	</script>
</body>
</html>

<!-- 
	확장자는 jsp이지만 mime type이 html이므로 html문서이다.

 -->