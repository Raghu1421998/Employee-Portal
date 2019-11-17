<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
   <meta name="viewport" content="width=device-width, initial-scale=1">
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>   
 
     <link href="<c:url value="/resources/css/alternative.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/displaytag.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/maven-base.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/maven-theme.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/print.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/screen.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/site.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/teststyles.css" />" rel="stylesheet">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
   <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
   <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
   <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
   <link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
  <script type="text/javascript" src="<c:url value="/resources/js/validate.js" />"></script>	
 <script type="text/javascript" src="<c:url value="/resources/js/row.js" />"></script>
 <style>

 </style>

</head>
<body>
 <%--  <display:column  title="Servicing Industry" sortable="true" style="width:500px" media="html xml">
    <c:forEach items="${Comp.services}" var="sk" >
    <c:out value="${sk.servicesIndustry}" />
    </c:forEach>  
    </display:column> --%>
	<div id="dialog-2">
	<span id="result1"></span>
	</div>
 	<c:if test="${param.insert eq true}">
	<div class="success" align="center" style="display: block; background-color: rgb(118, 197, 118); margin-left: 13cm; margin-right: 13cm; padding: 14px">
	<spring:message code="lbl.insert" text="Company Details Inserted Successfully!" /></div>
	</c:if>
	<c:if test="${param.update eq true}">
	<div class="success" align="center" style="display: block; background-color: rgb(118, 197, 118); margin-left: 13cm; margin-right: 13cm; padding: 14px">
	<spring:message code="lbl.update" text="Company Details Updated Successfully!" /></div>
	</c:if>
	<c:if test="${param.delete eq true}">
	<div class="success" align="center" style="display: block; background-color: rgb(118, 197, 118); margin-left: 13cm; margin-right: 13cm; padding: 14px">
	<spring:message code="lbl.delete" text="Company Details Deleted Successfully!" /></div>
	</c:if>      
	<div class="container">
	<div class="row">
	<div class="col-sm-12  text-center">
	<button type="button" onclick="location.href = 'CompanyScreen';" id="myButton">
	<spring:message code="lbl.addNew" text="ADD NEW" /></button>
    </div>	
   
    <div class="table-responsive table" id="example">
	<display:table  name="${companyLis1}" id="Comp"   pagesize="10" requestURI="/CompanySummaryScreen">
	<div class="divclasst">
	<table id="Company">
	<tbody>
	<c:url value="http://localhost:8081/MyBatisCrud1/${Comp.id}" var="Edit" />
	<c:url value="deleteemp/${Comp.id}" var="Delete" />
	<display:column title="Actions" style="width:100px" media="html" >
	<a  href="${Edit}" title="Edit"><i  class="fa fa-edit" style="font-size: 21px; color: #272525"></i></a>
    <a href="${Delete}" title="Delete" onclick="if (!(confirm('Are you sure you want to delete this Employee?'))) return false"><i class="fa fa-trash" aria-hidden="true" style="font-size: 21px; color: #272525"></i></a>
    </display:column>
    <display:column property="businessType" title="BU Type" sortable="true" group="1" style="width:150px" media="html" /> 
<%--<display:column property="businessType" title="BU Type" sortable="true" group="1" style="width:150px" media="excel" /> --%>
    <display:column property="id"  title="" sortable="true"  style="width:80px"  class="hidden" headerClass="hidden" media="html"  />
    <display:column property="name"  title="Name" sortable="true" class="bold" style="width:200px" media="html" />
    <fmt:parseNumber var="num" type="number" value="${Comp.empSize}" />   
    <display:column value="${num}"  title="Emp.Size" sortable="true"  style="width:140px" class="right1" media="html pdf"/>
    <display:column property="comType" title="Com.Type" sortable="true" style="width:130px " media="html pdf"/> 
    <display:column property="emailAddress" title="Email ID" sortable="true" style="width:200px " media="html xml"/>
    <display:column property="dateOfJoining" title="Reg.Date" sortable="true" style="width:200px " media="html xml"/>    
    <display:column property="dateOfResigning" title="Closure Date" sortable="true" style="width:200px " media="html xml"/>    
  
   
    <display:column   title="Country > City" sortable="true" style="width:300px" media="html xml">
    <c:out  value="${Comp.country}" /> > <c:out  value="${Comp.city}" /> 
    </display:column>
    </tbody>
    </table>
    </div>       
    </display:table> 
    <h1>${count}</h1>   
</div>	
</div>
 <form action="savefile1" method="post" enctype="multipart/form-data" >  
<input type="file" name="file" accept=".xls " id="myButton2"/> 
<input type="submit" value="Upload File" id="myButton3"/>  
</form>
 
<form action="savefile2" method="post" enctype="multipart/form-data" style="float:right">  
<input type="file" name="file" accept=".json " /> 
<input type="submit" value="Upload File" />  

</form>
</div>
<div class="container">
    <div class="card">
     <div class="card-header" style="border:1px solid black;"><i class="fa fa-google" style="font-size:28px;color:red"></i>
     GMAIL</div>
    <div class="card-body" style="border:1px solid black;">
    <form action="email">
   <div class="form-group">
   <div class="col-sm-10">
      <input type="email" class="form-control" id="cn" placeholder="Enter From Address" name="from" style="border:1px solid #5a5555;color:black;" />
   
  <br>
   </div>
   </div>
   <div class="form-group">
   <div class="col-sm-10">
      <input type="email" class="form-control" id="cn" placeholder="Enter To Address" name="to" style="border:1px solid #5a5555;color:black;" />
   
  <br>
   </div>
   </div>
   <div class="form-group">
   <div class="col-sm-10">
      <textarea cols="3" rows="3" placeholder="Enter Subject" class="form-control" id="cn" name="message" ></textarea>
   
  <br>
   </div>
   </div>
    <div class="form-group">
   <div class="col-sm-10">
      <input type="submit" value="Send" style='background-color:#70a6d6; margin-left:90px;     margin-right: 10cm;float: right; color:white;'/>
  <br>
   </div>
   </div>
    </form>
    </div>
   </div>
  </div>

</body>
</html>                    
