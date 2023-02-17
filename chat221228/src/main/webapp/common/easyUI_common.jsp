<%
	// request.getContextPath(): server.xml 내 <Context path="/"> 을 나타낸다
	StringBuilder path = new StringBuilder(request.getContextPath());
	path.append("/");
%>

<link rel="stylesheet" type="text/css" href="<%=path.toString() %>jQuery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path.toString() %>jQuery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path.toString() %>jQuery/demo/demo.css">
<script type="text/javascript" src="<%=path.toString() %>jQuery/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path.toString() %>jQuery/js/jquery.easyui.min.js"></script>

