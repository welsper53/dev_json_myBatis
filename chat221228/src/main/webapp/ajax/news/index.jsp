<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String menu = request.getParameter("menu");	// info or login or board or google
	out.print(menu);
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>패턴1</title>
    <style>
        a {
            text-decoration: none;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script defer type="text/javascript">
		// 뉴스 제목 출력
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
		        , success: function(data) { // 성공했을 때
		            // console.log(data);
		            $("#d_news").html(data);
		        },
		        error: function(request, status, error) {	// 실패했을 때
		        	console.log("error")
		        	console.log("error : " + request.status)
		        	$("#d_news").html(request.responseText)
		        }
		    });
		}
	</script>
</head>
<body>
	<!-- 뉴스 제목 2초마다 다르게 출력 --> 
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

    <!-- 태그는 중첩해서 사용 가능함 -->
    <!-- 크기는 %로 주는 것이 좋다. (가변적) -->
    <table border="1" align="center" width="1000px">
        <tr>
            <td width="100%" height="50px">
                <!-- ================== top 시작 ================== -->
                <%@ include file="./top.jsp" %>
                <!-- ================== top 끝 ================== -->
            </td>
        </tr>
        <tr>
            <td>
                <!-- ================== body 시작 ================== -->
                <table>
                    <tr>
                        <!-- ================== 메뉴 시작 ================== -->
                        <td width="200px" height="400px">
                            <%@ include file="./menu.jsp" %>
                        </td>
                        <!-- ================== 메뉴 끝 ================== -->
                        <td width="800px" height="400px">
                        <% 
                        if(menu == null) {
                        %>
							<%@ include file="./main.jsp" %>
                        <%
                        } else if ("info".equals(menu)) {
                        %>
							<%@ include file="./info.jsp" %>
                        <%
                        } else if ("login".equals(menu)) {
                        %>
							<%@ include file="./login.jsp" %>
                        <%
                        } else if ("board".equals(menu)) {
                        %>
							<%@ include file="./board.jsp" %>
                        <%
                        } else if ("google".equals(menu)) {
                        %>
							<%@ include file="./google.jsp" %>
                        <% 
                        }
                        %>
							
                        </td>
                    </tr>
                </table>
                <!-- ================== body 끝 ================== -->
            </td>
        </tr>
        <tr>
            <td width="1000px" height="30px">
                <!-- ================== bottom 시작 ================== -->
                <%@ include file="./bottom.jsp" %>
                <!-- ================== bottom 끝 ================== -->
            </td>
        </tr>
    </table>
</body>
</html>