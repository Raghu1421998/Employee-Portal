<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:forEach var="comp" items="${companyList1}">
	<b style="color: blue;">ID:</b>	${comp.id}<br>
	<b style="color: blue;">Name:</b>${comp.name}<br>
	<b style="color: blue;">BU Type:</b>${comp.businessType}<br>
	<b style="color: blue;">Emp.Size:</b>${comp.empSize}<br>
	<b style="color: blue;">Com.Type:</b>${comp.comType}<br>
	<b style="color: blue;">Email ID:</b>${comp.emailAddress}<br>
	<b style="color: blue;">Servicing Industry:</b><c:forEach items="${comp.services}" var="sk" >${sk.servicesIndustry} </c:forEach><br>
	<b style="color: blue;">Country:</b>${comp.country}<br>
	<b style="color: blue;">City:</b> ${comp.city}<br>
</c:forEach>
