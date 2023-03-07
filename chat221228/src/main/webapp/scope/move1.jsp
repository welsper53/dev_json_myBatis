<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.book.scope.Sonata" %>
<%
	Sonata myCar = (Sonata)request.getAttribute("myCar");
	Sonata herCar = (Sonata)request.getAttribute("herCar");
	Sonata yourCar = (Sonata)request.getAttribute("yourCar");
	
	String oMyCar = request.getParameter("oMyCar");
	String oHerCar = request.getParameter("oHerCar");
	String oYourCar = request.getParameter("oYourCar");
	
	out.print("scope1.jsp에서 생성된 객체가 유지 되나요?");
	out.print("<br/><hr>");
	out.print(myCar + ", " + oMyCar.carName + ", " + oMyCar.concat("1") + "자동차".concat("1"));
	out.print("<br/><hr>");
	out.print(herCar + ", " + oHerCar.carName + ", " + oHerCar.indexOf(3) + "소나타".concat("1"));
	out.print("<br/><hr>");
	out.print(yourCar + ", " + oYourCar.carName + ", " + oYourCar.charAt(2) + true.concat("1") + new Boolean(true).toString());
%>