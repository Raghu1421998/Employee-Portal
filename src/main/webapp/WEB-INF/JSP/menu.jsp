 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<h3>Excel Export</h3>

   <spring:url value="export" var="xlsURL"></spring:url>
   
	<a href="${xlsURL}" class="li">EXPORT</a>

<h3>JSON Export</h3>

 <spring:url value="returnListInResponse.web" var="xlsURL1"></spring:url>
	<a href="${xlsURL1}" class="li">EXPORT</a>
	     
	