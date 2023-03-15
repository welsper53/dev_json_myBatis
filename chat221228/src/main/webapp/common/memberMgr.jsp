<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./easyUI_common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리시스템</title>
</head>
<body>
<script>
	let g_address = "";

	$(document).ready(function() {
		$("#_easyui_textbox_input1").val();
		
		$("#btn_zipcode").linkbutton({
			onClick: function() {
				console.log("찾기 버튼 클릭");
				$('#dlg_zipcode').dialog('open');
			}
		}) 
		
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
				console.log("사용자가 입력한 동이름은 " + u_dong);
			
				$('#dg_zipcode').datagrid({
				    url:'/common/zipcodeList.st3?dong=' + $(this).val(),
				    columns:[[
				        {field:'ZIPCODE',title:'우편번호',width:100,align:'center'},
				        {field:'ADDRESS',title:'주소',width:300,align:'left'}
				    ]],
				    singleSelect: true, 
				    onSelect: function(index, row) {
				    	g_address = row.ADDRESS
				    	console.log(g_address);
				    },
					onDblClickCell: function(index, field, value) {
						if ("ZIPCODE" === field) {
							$("#mem_zipcode").textbox('setValue', value)
							$("#mem_addr").textbox('setValue', g_address)
						}
						if ("ADDRESS" === field) {
							$("#mem_zipcode").textbox('setValue', value)
							$("#mem_addr").textbox('setValue', g_address)
						}
					}
				});
			}
		}) // end of 찾기 엔터
		
	}) // end of ready - DOM Tree가 다 그려졌을 때
</script>
	<div id="dlg_zipcode" class="easyui-dialog" title="우편번호찾기" style="width:450px;height:200px;"
        data-options="iconCls:'icon-save',resizable:true,modal:true, closed:true">
		<input class="easyui-textbox" id="dong" name="dong" labelPosition="top" data-options="prompt:'동이름 이나 주소정보 입력...'" style="width:210px;">
		<a id="btn_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">찾기</a>
		<div  style="margin-bottom:10px;"></div>
		<table id="dg_zipcode"></table>
	</div>
	<!-- ============================= 회원가입 ============================ -->
	<div id="dlg_ins" style="width:100%;max-width:480px;padding:30px 60px;">
		<form id="f_ins">
			<input type="hidden" id="work" name="work" value="member"/>
	     <div style="margin-bottom:10px">
	         <input class="easyui-textbox" id="mem_id" name="mem_id" label="아이디:" labelPosition="top" data-options="prompt:'Enter a ID'" style="width:110px;">
	     </div>
	     <div style="margin-bottom:10px">
	         <input class="easyui-textbox" id="mem_name" name="mem_name" label="이름:" labelPosition="top" data-options="prompt:'Enter a Name'" style="width:150px;">
	     </div>
	     <div style="margin-bottom:10px">
	         <input class="easyui-textbox" id="mem_zipcode" name="mem_zipcode" label="우편번호:" labelPosition="top" data-options="prompt:'Enter a ZipCode'" style="width:100px;">
	     	 <a id="btn_zipcode" href="#" class="easyui-linkbutton">우편번호찾기</a>
	     </div>
	     <div style="margin-bottom:10px">
	         <input class="easyui-textbox" id="mem_addr" name="mem_addr" label="주소:" labelPosition="top" data-options="prompt:'Enter a ADDRESS'" style="width:300px;">
	     </div>
	     <div style="margin-bottom:10px">
	         <input class="easyui-textbox" id="mem_pw" name="mem_pw" label="비번:" labelPosition="top" data-options="prompt:'Enter a PASSWORD'" style="width:110px;">
	     </div>
	     </form>
	</div>
	<div id="tb_ins" style="padding:5px;">
	<a id="btn_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">저장</a>
	<a id="btn_close" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">닫기</a>
	</div>
	<!-- ============================= 회원가입 ============================ -->
</body>
</html>