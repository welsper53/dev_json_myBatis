<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="./easyUI_common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색기</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("우편번호검색 다이얼로그");
		$("#btn_search").linkbutton({
			onClick: function() {
				const u_dong = $("#_easyui_textbox_input1").val();
				
				if (u_dong == null || u_dong.length<1) {
					// 동 정보가 없으면 처음부터 새로 시작해야 하니까... return을 사용
					alert("동을 입력하세요");
					
					return;
				} else {
					console.log("사용자가 입력한 동이름은 " + u_dong);
					// 오라클 서버를 경유해서 조회된 결과를 datagrid에 출력해보기
					// location.href="/common/zipcodeList.st3?dong="+u_dong";
					$('#dg_zipcode').datagrid({
					    url:'/common/zipcodeList.st3?dong=' + u_dong,
					    columns:[[
					        {field:'ZIPCODE',title:'우편번호',width:100,align:'center'},
					        {field:'ADDRESS',title:'주소',width:300,align:'left'}
					    ]]
					});
				}
			}
		}) // end of 찾기 버튼
		
		$('#dong').textbox('textbox').bind('keydown', function(e) {
			const u_dong = $("#_easyui_textbox_input1").val();
			if (e.keyCode == 13) {		// 13 : Enter키
				alert("사용자가 입력한 동이름은 " + u_dong);
			
				$('#dg_zipcode').datagrid({
				    url:'/common/zipcodeList.st3?dong=' + $(this).val(),
				    columns:[[
				        {field:'ZIPCODE',title:'우편번호',width:100,align:'center'},
				        {field:'ADDRESS',title:'주소',width:300,align:'left'}
				    ]]
				});
			}
		})
	})
</script>
	<h3>우편번호 검색기</h3>
	<!-- ========================== 우편번호 검색기 ========================= -->
	<div id="dlg_zipcode" style="width:100%;max-width:600px;padding:30px 30px;">
		<input class="easyui-textbox" id="dong" name="dong" labelPosition="top" data-options="prompt:'동이름 이나 주소정보 입력...'" style="width:210px;">
		<a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">찾기</a>
		<div  style="margin-bottom:10px;"></div>
		<table id="dg_zipcode">
		</table>
	</div>
	<!-- ========================== 우편번호 검색기 ========================= -->
</body>
</html>