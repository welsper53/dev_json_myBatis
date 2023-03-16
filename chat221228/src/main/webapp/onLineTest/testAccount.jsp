<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String[] users = new String[5];
	Cookie[] cs = request.getCookies();
	Cookie cookie = null;
	
	int size = 0;
	size = cs.length;
	
	for (int i=0; i<size; i++) {
		cookie = cs[i];
		System.out.println(cookie.getName() + " : " + cookie.getPath() + ", " + cookie.getValue());
		
		if ("onLineTest".equals(cookie.getPath())) {
			users[i] = cs[i].getValue();
			System.out.print(users[i] + ", ");
		}
	}
	System.out.println();
	
	
	for (int i=0; i<size; i++) {
		String c_name = cs[i].getName();
		
		if ("testForm1".equals(c_name)) {
			users[0] = cs[i].getValue();
		}
		else if ("testForm2".equals(c_name)) {
			users[1] = cs[i].getValue();
		}
		else if ("testForm3".equals(c_name)) {
			users[2] = cs[i].getValue();
		}
		else if ("testForm4".equals(c_name)) {
			users[3] = cs[i].getValue();
		}
		else if ("testForm5".equals(c_name)) {
			users[4] = cs[i].getValue();
		}
	}
	

	// 채점하는 로직을 구현하시오.
	// 문제당 배점
	int jumsu = 20;	
	// 합격기준 점수
	int pass = 60;
	// 맞춘 개수 
	int cnt = 0;
	// 합격 여부
	boolean isPass = false;
	// 정답표
	String[] daps = {"3", "4", "1", "2", "4"};

	
	// 채점하는 로직을 작성해 보자
	for (int i=0; i<users.length; i++) {
		String n = users[i];
		if (n.equals(daps[i])) {
			cnt++;
		}
	}
	int sum = cnt * jumsu;
	if (sum >= pass) {
		isPass = true;
	}
%>
<script type="text/javascript">
	const cookieDelete = () => {
		location.href = "./cookieDelete.jsp";
	}
</script>
</head>
<body>
<h3>맞힌 개수 : <%=cnt %>개입니다</h3>
<h3>당신의 점수는 <%=sum %>점입니다</h3>
<div>
	<!-- 
		 리액트: 
			onClick (<- onclick)
			className (<- class)
			함수로 화면을 그린다
			함수 안에서 UI담당하는 예약어는? return
			리렌더링 - useState, props(주소번지-이벤트핸들러, state hook)
					 부모컴포넌트가 변경되면 	
	-->
	<button onclick="cookieDelete()">쿠키 삭제</button>
	그러니까 당신 
	<%
		if (isPass) {
			out.print("합격입니다");
		} else {
			out.print("탈락입니다");
		}
	%>
</div>
</body>
</html>