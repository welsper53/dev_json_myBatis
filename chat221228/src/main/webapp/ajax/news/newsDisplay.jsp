<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<script type="text/javascript">
	// 호출하지 않아도 자동 실행
	// jquery(document) ==> $(document)
	// window <- document <- ready(function(){실행문})함수
	$(document).ready(() => {
		// 실행문
		(start = () => {
			setInterval(autoReload, 2000);	// 2초 후 실행
		})();	// 즉시실행 함수
	}) // end of ready() <- DOM을 다 읽은 후 실행
	</script>
	<div id="d_news">뉴스 준비중...</div>
	<%
		out.print("<font color='red' size=18>안녕</font>");
	%>
	
	<script>
		const autoReload = () => {
			console.log("autoReload 호출");
			
			// ajax함수는 jquery.min.js가 제공하는 api이다
			// ajax함수는 결국 XMLHttpRequewst대신함
			// const ajax = new XMLHttpRequest();
			// ajax.open("GET", url, false)
			// ajax.send()
			$.ajax({  
		        type: "GET"
		        , url: "./newsList.jsp"
		        , success: function(data) {
		            // console.log(data);
		            $(document).write(data);
		        },
		    });
		}
	</script>
</body>
</html>

<!-- 
	html(단방향, 변수선언, 제어문 그리고 이벤트 지원X) 은 순차적으로 실행
	자바스크립트 코드의 위치에 따라서 document.querySelector(ID or CLASS or ELEMENT)
	: 선언이 먼저
	자바스크립트 위치
	1) head 안에 -> 전역변수, 함수(호출을 해야 실행됨) 선언 시
	: 만일 이것을 지연하고 싶을 때 -> defer - 미룬다 (<- HTML, DOM Tree을 다 그릴 때까지)
	2) body 안에 -> 호출하지 않아도 실행됨 (단 함수로 선언된 부분은 제외)
	
	자바스크립트로 화면을 그릴 수 있다
	document.write("<font color='red' size=18></font>")
	
	write는 어디에 쓰는 건가요? -> 브라우저
	java는 브라우저에 쓸 수 없다
	
	mime type
	: 메이타입/서브타임
	jsp라고 쓰고 html이라고 읽기
	jsp라고 쓰고 json이라고 읽기		
 -->