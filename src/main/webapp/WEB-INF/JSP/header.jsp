
<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">
  <script src='https://kit.fontawesome.com/a076d05399.js'></script>
  
<style>
  .bg-dark
  {
      background-color: #70a6d6!important;
  }
</style>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark" id="myDIV">
  <div class="container-fluid" >
  <!-- Brand/logo -->
 <div class="navbar-header">
      <a class="navbar-brand" style="color:white;">Company Portal</a>
    </div>  
  <!-- Links -->
  <!-- <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Link 1</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 2</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 3</a>
    </li>
  </ul> -->
  <ul class="nav navbar-nav ml-auto">
        <li><form action="compscreen">
      <button type="submit" class="btn btn-link" style="color:white; text-decoration: none; outline:none"><spring:message code="lbl.company" text="COMPANY" /></button>
     </form></li>
      <li>
      <button type="submit" id="bu" class="btn btn-link" style="color:white; text-decoration: none; outline:none"><spring:message code="lbl.dashboard" text="DASHBOARD" /></button>
    </li>
     <li><form action="logout" METHOD="post">
      <button type="submit" class="btn btn-link" style="color:white; text-decoration: none; outline:none"><spring:message code="lbl.logout" text="LOGOUT" /></button>
     </form></li>
     <li>
        <button  class="btn btn-link" id="cart" style="color:white; text-decoration: none; outline:none">
          CART<i class='fas fa-cart-plus'></i>
          
            <span class="badge">0</span>
        </button>
     </li>
    </ul>
    </div>
</nav>


<%-- <nav class="navbar navbar-default">
  <div class="container-fluid" id="myDIV">
    <div class="navbar-header">
      <a class="navbar-brand" style="color:white;">Company Portal</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li><form action="compscreen">
      <button type="submit" class="btn btn-link" style="color:white; text-decoration: none;"><spring:message code="lbl.company" text="COMPANY" /></button>
     </form></li>
      <li>
      <button type="submit" id="bu" class="btn btn-link" style="color:white; text-decoration: none;"><spring:message code="lbl.dashboard" text="DASHBOARD" /></button>
    </li>
     <li><form action="logout" METHOD="post">
      <button type="submit" class="btn btn-link" style="color:white; text-decoration: none;"><spring:message code="lbl.logout" text="LOGOUT" /></button>
     </form></li>
     <li>
        <button  class="btn btn-link" id="cart" style="color:white; text-decoration: none;">
          CART<span class="glyphicon glyphicon-shopping-cart"></span>
            <span class="badge">0</span>
        </button>
     </li>
    </ul>
  </div>
</nav> --%>
<script>
 $(document).ready(function() {
	$('#bu').click(function(){
		$.ajax({
			url : 'dash',
			type : 'GET',
			success : function(result)
			{
			location.href = "http://localhost:8081/MyBatisCrud1/dash?"
			}
		});
	});
}); 
</script>
</body>
</html>