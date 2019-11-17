<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<META HTTP-EQUIV="Cache-Control" CONTENT="No-Cache,Must-Revalidate,No-Store">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<c:url value="/resources/js/validate.js" />"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
a {
    color: #ecf0f3;
    text-decoration: none;
}
.h3, h3 {
    font-size: 19.5px;
}
</style>
<body class="body" onload="stateCity()">
<%-- <jsp:include page="header.jsp" />	
 --%><div class="container">
  <div class="row">
   <div class="col-md-12">
   <form action="/MyBatisCrud1/back" method="post">
   <button onclick="location.href = 'CompanySummaryScreen';" id="myButton" class=b>
   <spring:message code="lbl.back" text="BACK" />
   </button>
   </form>
   </div>
   </div><br>
   <form:form class="form-horizontal" onsubmit="return validate()" action="/MyBatisCrud1/editsave">
   <div class="form-group">
   <label for="name" class="control-label col-sm-2 c-label">
   <spring:message code="lbl.companyName" text="Company Name " /></label>
   <div class="col-sm-10">
   <form:hidden path="Id" />
   <form:input path="name" type="text" class="form-control" id="fname" placeholder="Enter Company Name" style="border:1px solid #5a5555;color:black;" />
   <br> 
   <span class="alert" id="demo" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label class="control-label col-sm-2 c-label"><spring:message code="lbl.businessType" text="Business Type" /></label>
   <div class="col-sm-10">
   <form:select path="businessType" id="businessType" class="form-control" style="border:1px solid #5a5555;color:black">
   <form:option value="" label="---Select BusinessType---" />
   <form:option value="IT Software" label="IT Software" />
   <form:option value="IT Hardware" label="IT Hardware" />
   <form:option value="BPO" label="BPO" />
   <form:option value="Agriculture" label="Agriculture" />
   <form:option value="Banking" label="Banking" />
   <form:option value="Financial Services" label="Financial Services" />
   </form:select><br>
   <span class="alert" id="demo1" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label for="empSize" class="control-label col-sm-2 c-label">
   <spring:message code="lbl.empSize" text="Employee Size" /></label>
   <div class="col-sm-10">
   <form:input path="empSize" id="em" class="form-control" placeholder="Enter Employee Size" style="border:1px solid #5a5555;color:black;" />
   <br>
    <span class="alert" id="demo2" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label for="comType" class="control-label col-sm-2 c-label" style="color: black;">
   <spring:message code="lbl.comType" text="Company Type" /></label>
   <div class="col-sm-10">
   <label><form:radiobutton path="comType" id="g" value="Employeer" /> 
   <spring:message code="lbl.employeer" text="Employeer" /></label> 
   <label><form:radiobutton path="comType" id="h" value="Consultant" />
    <spring:message code="lbl.consultant" text="Consultant" />
    </label><br> <span id="demo3" style="color: red"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label for="contactNumber" class="control-label col-sm-2 ">
   <spring:message code="lbl.contactNumber" text="Contact Number" /></label>
   <div class="col-sm-10">
   <form:input path="contactNumber" class="form-control" id="cn" placeholder="Enter Contact Number" style="border:1px solid #5a5555;color:black;" />
   <br> <span class="alert" id="demo4" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label for="emailAddress" class="control-label col-sm-2 c-label">
   <spring:message code="lbl.emailAddress" text="Email Address" /></label>
   <div class="col-sm-10">
   <form:input path="emailAddress" id="email" placeholder="Enter Email Address" class="form-control" style="border:1px solid #5a5555;color:black;" />
   <br> <span class="alert" id="demo20" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label for="servicingIndustry" class="control-label col-sm-2 c-label">
   <spring:message code="lbl.servicingIndustry" text="Servicing Industry" /></label>
   <div class="col-sm-10">
   <table cellpadding="16">
   <tr>
   <td><label class="check">
   <input type="checkbox" name="service" class="serv" id="s" value="Medical" />
   <spring:message code="lbl.medical" text="Medical" />
   </label></td>
   <td><label class="check" style="color: black;">
   <input type="checkbox"  name="service" class="serv" id="s1" value="Financial" /> 
   <spring:message code="lbl.financial" text="Financial" />
   </label></td>
   </tr>
   <tr>
   <td><label class="check">
   <input type="checkbox" class="serv" name="service" id="s2" value="Education" /> 
   <spring:message code="lbl.education" text="Education" />
   </label></td>
   <td><label class="check"> 
   <input type="checkbox" class="serv" name="service" id="s3" value="Marketing" /> 
   <spring:message code="lbl.marketing" text="Marketing" />
   </label></td>
   </tr>
   <tr>
   <td><label class="check"> <input type="checkbox" name="service" class="serv" id="s4" value="Hospitals" />
   <spring:message code="lbl.hospitals" text="Hospitals" />
   </label></td>
   <td><label class="check"> 
   <input type="checkbox" name="service" class="serv" id="s5" value="Banking" /> 
   <spring:message code="lbl.banking" text="Banking" /></label></td>
   </tr>
   </table>
   <span class="alert" id="demo5" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label class="control-label col-sm-2 c-label"><spring:message code="lbl.country" text="Country" /></label>
   <div class="col-sm-10">
   <form:select path="country" id="input" onchange="stateCity()" class="form-control" style="border:1px solid #5a5555;color:black;">
   <form:option value="Select Country" label="Select Country" />
   <form:option value="US" label="US" />
   <form:option value="UK" label="UK" />
   <form:option value="INDIA" label="INDIA" />
   </form:select>
   <br>
   <span class="alert" id="demo6" style="color: red;"></span><br>
   </div>
   </div>
   <div class="form-group">
   <label class="control-label col-sm-2 c-label">
   <spring:message code="lbl.city" text="City" /></label>
   <div class="col-sm-10">
   <form:select path="city" id="output" class="form-control" style="border:1px solid #5a5555;color:black;">
   <form:option value="" label="Select City" id="opt" />
   </form:select>
   <br> <br>
   </div>
   </div>
	   <div class="form-group">
	   <label class="control-label col-sm-2 "><spring:message code="lbl.address" text="Address" /></label>
	   <div class="col-sm-10">
	   <form:textarea path="address" id="ad" rows="5" cols="30" class="form-control" style="border:1px solid #5a5555;color:black;" />
	   <span class="alert" id="demo7" style="color: red;"></span><br>
	   <br> <br>
	   </div>
	   </div>
	      <div class="form-group">
   <label for="dateOfJoining" class="control-label col-sm-2 ">
   <spring:message code="lbl.empSize" text="Date of Join" /></label>
	     <div class="col-sm-10">
   <form:input path="dateOfJoining" id="doj" class="form-control" placeholder="Enter Date of Join (MM/dd/yyyy)" style="border:1px solid #5a5555;color:black;" />
   <br>
    <span class="alert" id="demo40" style="color: red;"></span><br>
   </div>
   </div>
      <div class="form-group">
   <label for="dateOfJoining" class="control-label col-sm-2 ">
   <spring:message code="lbl.empSize" text="Date of Resign" /></label>
      <div class="col-sm-10">
   <form:input path="dateOfResigning" id="dor" class="form-control" placeholder="Enter Date of Resign (MM/dd/yyyy)" style="border:1px solid #5a5555;color:black;" />
   <br>
    <span class="alert" id="demo50" style="color: red;"></span><br>
   </div>
   </div>
	   <div class="row">
	   <div class="col-sm-12  text-center">
	   <input class="resetButton" type="reset" value="CLEAR" style="">
		<input class="submit" type="submit" value="SUBMIT">
		</div>
		</div>
		<script>
		 function stateCity() {
				var state = $("#input").val();
				var c='${city}';
				var city = "";
				if (state === "US") {
					var a = [ "New York", "Chicago" ];
				} else if (state === "UK") {
					 a = [ "London", "Manchester" ];
				} else {
					 a = [ "Bangalore", "Delhi" ];
				}
				if(a[a.length-1]===c)
			    {
				for (var i = a.length-1; i >=0; i--) {
					city = city + "<option>" + a[i] + "</option>";
				}
			    }
				else
					{
					for (var i = 0; i < a.length; i++) 
					{
						city = city + "<option>" + a[i] + "</option>";
					} 
					}
				city = "<select name='any_name'>" + city + "</select>";
				$("#output").html(city);
			}
	 $(document).ready(function() {
		
	 
	  var servInd = '${service}';
	  var a = servInd.split(',');
	  var obj = 
	  {
	  "servIndustry" :
	  [ "Medical", "Financial","Education", 
	  "Marketing", "Hospitals","Banking" 
	  ]
	  };
	  for (var i = 0; i < obj.servIndustry.length; i++) {
	  if (a[i] === obj.servIndustry[0]) {
	  $('#s').prop('checked', true);
	  }
	  if (a[i] === obj.servIndustry[1]) {
	  $('#s1').prop('checked', true);
	  }
	  if (a[i] === obj.servIndustry[2]) {
	  $('#s2').prop('checked', true);
	  }
	  if (a[i] === obj.servIndustry[3]) {
	  $('#s3').prop('checked', true);
	  }
	  if (a[i] === obj.servIndustry[4]) {
	  $('#s4').prop('checked', true);
	  }
	  if (a[i] === obj.servIndustry[5]) {
	  $('#s5').prop('checked', true);
	  }
	  }
	  });
		</script>
		
		</form:form>
	</div>
	
    </body>
    </html>
