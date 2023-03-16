<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String dap1 = request.getParameter("htest1");
	out.print("1번 문제 답안지 ===> " + dap1 + "<br />");

	Cookie c1 = new Cookie("testForm1", dap1);
	// 아래 코드를 생략하면 디폴트가 현재 바라보는 물리적인 폴더며으로
	// 현재 바라보는 경로에 onLinetest가 들어있는 경우에는 생략할 수 있으나
	// 직관적으로 알 수 있도록 써준다
	// 만일 setPath("/")라고 할 경우 루트 도메인에서 읽고 수정 삭제가 가능하므로 주의해야 한다
	c1.setPath("/onLineTest");
	c1.setMaxAge(60*60);	// 평가시간이 60분일 때 시간 설정 값이다
	// 위에서 작성된 답안 번호를 저장한 값을 클라이언트 측에 반드시 내려 보내야 한다
	response.addCookie(c1);
%>
<script type="text/javascript">
	const test = (cb) => {
		for (let i=0; i<document.f_test2.cb.length; i++) {
			if (i === cb) {
				document.f_test2.cb[i].checked = 1
			} else {
				document.f_test2.cb[i].checked = 0				
			}
		}
	} // end of test
	
	const prev = () => {
		// location.href 속성으로 페이지를 전환하는 건 SPA에는 맞지않음
		// 리액트 -> const navigate = useNavigate();
		// navigate("./testForm1.jsp")
		window.location.href = "testForm1.jsp";
	} // end of prev
	
	const next = () => {
		let dap = 1
		
		for (let i=0; i<document.f_test2.cb.length; i++) {
			if (document.f_test2.cb[i].checked == 1) {
				document.f_test2.htest2.value = dap
			} else {
				dap += 1
			}
		}
		
		document.f_test2.submit();
	} // end of next
</script>
</head>
<body>
	<form name="f_test2" method="GET" action="testForm3.jsp">
		<input type="hidden" name="htest2">
		문제2. 다음 중 DDL구문이 아닌 것을 고르시오.<br>
		<input type="checkbox" name="cb" onChange="test(0)">create<br>
		<input type="checkbox" name="cb" onChange="test(1)">drop<br>
		<input type="checkbox" name="cb" onChange="test(2)">alter<br>
		<input type="checkbox" name="cb" onChange="test(3)">delete<br>
		<br>
		<input type="button" value="이전문제" onClick="prev()">
		<input type="button" value="다음문제" onClick="next()">
	</form>
</body>
</html>