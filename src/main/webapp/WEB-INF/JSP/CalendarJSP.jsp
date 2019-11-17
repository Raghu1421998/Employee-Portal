<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link href="<c:url value="/resources/css/alternative.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/displaytag.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/maven-base.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/maven-theme.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/print.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/screen.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/site.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/teststyles.css" />" rel="stylesheet">
<title>Insert title here</title>
<body>
    <table class="table table-striped" >
      <tr> 
    <c:forEach var="Comp" items="${calendarList}"> 
    <td>${Comp.day}</td>
    </c:forEach>
    </tr>
    <tr>
    <c:forEach var="Comp" items="${calendarList}"> 
    <td>${Comp.empCount}</td>
    </c:forEach>
    </tr>
    <tr>
    <c:forEach var="Comp" items="${calendarList}"> 
    <td>${Comp.res}</td>
    </c:forEach>
    </tr>
</table>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<json:array name="Comp" var="item" items="${calendarList}">
    <json:object>
      <json:property name="Day" value="${item.day}"/>
      <json:property name="TotalEmployees" value="${item.empCount}"/>
      <json:property name="Resigned" value="${item.res}"/>
    </json:object>
  </json:array>
