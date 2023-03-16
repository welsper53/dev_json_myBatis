<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cs = request.getCookies();
	
	int size = 0;
	size = cs.length;
	
	for (int i=0; i<size; i++) {
		String c_name = cs[i].getName();
		
		if ("testForm1".equals(c_name)) {
			Cookie c = new Cookie(c_name, "");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		else if ("testForm2".equals(c_name)) {
			Cookie c = new Cookie(c_name, "");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		else if ("testForm3".equals(c_name)) {
			Cookie c = new Cookie(c_name, "");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		else if ("testForm4".equals(c_name)) {
			Cookie c = new Cookie(c_name, "");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		else if ("testForm5".equals(c_name)) {
			Cookie c = new Cookie(c_name, "");
			c.setMaxAge(0);
			response.addCookie(c);
		}
	}
	
	response.sendRedirect("./testForm1.jsp");
%>
</body>
</html>