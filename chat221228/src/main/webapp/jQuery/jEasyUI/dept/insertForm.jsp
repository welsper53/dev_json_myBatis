<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 등록 다이얼로그</title>
<%@ include file="../../../common/easyUI_common.jsp" %>
</head>
<body>
    <div style="margin:20px 0;"></div>
    <div style="margin: 30px">
		<form id="f_dept" method="get">
		    <div style="margin-bottom:20px">
				<input class="easyui-textbox" name="deptno" label="부서번호:" labelPosition="top" data-options="prompt:'Enter a 부서번호...'" style="width:100%;">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="dname" label="부서명:" labelPosition="top" data-options="prompt:'Enter a 부서명...'" style="width:100%;">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="loc" label="지역:" labelPosition="top" data-options="prompt:'Enter a 지역...'" style="width:100%;">
			</div>
			
			<div style="text-align:center">
			    <a href="javascript:insertAction()" class="easyui-linkbutton" iconCls="icon-ok" style="width:200px;height:32px">등록</a>
			</div>
       </form>
	</div>
	<script type="text/javascript">
		const insertAction = () => {
			// console.log("입력 호출 성공");
			console.log($("#_easyui_textbox_input1").val() + ", " + $("#_easyui_textbox_input2").val() + ", " + $("#_easyui_textbox_input3").val());
			
			const u_deptno = $("#_easyui_textbox_input1").val();
			const u_dname = $("#_easyui_textbox_input2").val();
			const u_loc = $("#_easyui_textbox_input3").val();
			// jQuery.ajax(세팅)
			$.ajax({
				method: "GET"
				, url: "/jEasyUI/dept/insertAction.do?deptno="+u_deptno+"&dname="+u_dname+"&loc="+u_loc
				, success: function(data) {
					console.log("톰캣 서버에서 응답으로 보낸 값==>" + data);
				  }
			})
		}
	</script>
</body>
</html>