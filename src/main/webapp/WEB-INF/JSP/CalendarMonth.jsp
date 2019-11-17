<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<title>Insert title here</title>
<body>
<h2 align="left">September</h2>
    <table cellspacing="10">
      <tr> 
    <c:forEach var="Comp" items="${calendarList}"> 
    <td>${Comp.month}</td>
    </c:forEach>
    </tr>
    <tr>
    <c:forEach var="Comp" items="${calendarList}"> 
    <td>${Comp.empCount}</td>
    </c:forEach>
    </tr>
</table>
</body>
</html>