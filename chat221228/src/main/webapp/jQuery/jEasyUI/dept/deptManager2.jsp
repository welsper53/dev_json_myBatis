<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리2</title>
<%@ include file="../../../common/easyUI_common.jsp" %>
<script type="text/javascript">
	const insertForm = () => {
		console.log("입력 화면 열기");
	}
</script>
</head>
<body>
    <h2>부서관리 시스템</h2>
    <div style="margin:20px 0;"></div>
	<script type="text/javascript">
		window.addEventListener("load", (event) => {
			// dg가 누구인지 모르면 화면에 출력 안됨
			$('#dg').datagrid({
				title:'부서관리',
			    url:'../dept.json',
			    toolbar:"#toolbar",
			    columns:[[
			        {field:'deptno',title:'부서번호',width:100},
			        {field:'dname',title:'부서명',width:100},
			        {field:'loc',title:'지역',width:100,align:'right'}
			    ]]
			});
		})
	</script>
	<table id="dg"></table>
	
	<div id="toolbar">
        <a 
            href="javascript:void(0)" 
            class="easyui-linkbutton" 
            iconCls="icon-search" 
            plain="true" 
            onclick="insertForm()"
        >
            입력
        </a>
    </div>
</body>
</html>