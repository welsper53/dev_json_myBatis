<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="f_test" method="POST" action="/day1/hello.kh">
<!-- 전송버튼에 대한 이벤트 처리가 없는 요청이 서버에 전달되는 이유는 뭘까요? -->
<!-- submit이슈 -->
<!-- <button id="btnSend">전송</button> -->
	<input type="button" id="btnSend" value="전송">
	<script type="text/javascript">
		const btnSend = document.querySelector("#btnSend");
		btnSend.addEventListener("click", () => {
			alert("전송 버튼 클릭");
			// submit()메소드로 해당 id로 전송
			document.querySelector("#f_test").submit()
		})
	</script>
</form>
</body>
</html>