<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
</head>
<body>
	<h1>지진/해일 대피소 안내</h1>
	<input type="button" value="실행" id="btn" />
	<div id="result"></div>
	
	<script type="text/javascript">
	$("#btn").click(() => {
		$.ajax({
		    url: 'data.xml', // 읽어올 문서
		    type: 'GET', // 방식
		    dataType: 'xml', // 문서 타입
		    success: function(item) {
		    	const $table = $("<table border='1'></table>");		// 동적으로 생성할 table태그 선언
		    	const header = "<tr>"
		    			+ "<td>일련번호</td>"
		    			+ "<td>시도명</td>"
		    			+ "<td>시군구명</td>"
		    			+ "<td>대피지구명</td>"
		    			+ "<td>대피장소명</td>"
		    			+ "<td>주소</td>"
		    			+ "<td>경도</td>"
		    			+ "<td>위도</td>"
		    			+ "<td>수용가능인원(명)</td>"
		    			+ "<td>해변으로부터거리</td>"
		    			+ "<td>대피소분류명</td>"
		    			+ "<td>내진적용여부</td>"
		    			+ "<td>해발높이</td>"
		    			+ "</tr>";
		    	
		    	let data = "";
		    	
			    $(item).find('row').each(function() {  // xml 문서 item 기준으로 분리후 반복
			        const id = $(this).find("id").text();
			        const sido_name = $(this).find("sido_name").text();
			        const sigungu_name = $(this).find("sigungu_name").text();
			        const remarks = $(this).find("remarks").text();
			        const shel_nm = $(this).find("shel_nm").text();
			        const address = $(this).find("address").text();
			        const lon = $(this).find("lon").text();
			        const lat = $(this).find("lat").text();
			        const shel_av = $(this).find("shel_av").text();
			        const lenth = $(this).find("lenth").text();
			        const shel_div_type = $(this).find("shel_div_type").text();
			        const seismic = $(this).find("seismic").text();
			        const height = $(this).find("height").text();
		
			        data += "<tr><td>" + id + "</td>" 
			        			+ "<td>" + sido_name + "</td>"
			        			+ "<td>" + sigungu_name + "</td>"
			        			+ "<td>" + remarks + "</td>"
			        			+ "<td>" + shel_nm + "</td>"
			        			+ "<td>" + address + "</td>"
			        			+ "<td>" + lon + "</td>"
			        			+ "<td>" + lat + "</td>"
			        			+ "<td>" + shel_av + "</td>"
			        			+ "<td>" + lenth + "</td>"
			        			+ "<td>" + shel_div_type + "</td>"
			        			+ "<td>" + seismic + "</td>"
			        			+ "<td>" + height + "</td>"
			        			+ "</tr>"
			    });
			    
			    $table.html(header)		// 헤더 추가
			    	  .append(data)
			    	  .appendTo($("#result"))
			}, 
			error: (xhr, textStatus, err) => { // 로딩 에러시
		        console.log(xhr, textStatus, err);
		    },
		});
	});
	</script>
</body>
</html>