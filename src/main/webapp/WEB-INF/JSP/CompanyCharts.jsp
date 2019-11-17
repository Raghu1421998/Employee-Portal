<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display1"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<title>Company Charts</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <jsp:include page="header.jsp" />
  <link href="<c:url value="/resources/css/charts.css" />" rel="stylesheet">

 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.12.4.js"></script>
  <script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
  <script src="https://d3js.org/d3.v3.min.js" charset="utf-8"></script>
<script src="https://cdn.jsdelivr.net/npm/pptxgenjs@2.6.0/dist/pptxgen.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.2.2/jszip.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/file-saver@2.0.2/dist/FileSaver.min.js"></script>

<style>
body
{
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
}
.container-fluid {
    padding-right: 0px;
    padding-left: 0px;
    margin-right: auto;
    margin-left: auto;
    background-color: #70a6d6;
    color: white;
}
.container
{
    max-width: 1473px;
}
.col-md-6 {
    -ms-flex: 0 0 50%;
    flex: 0 0 50%;
    max-width: 100%;
}

#a,#b,#c,#d
{
	 background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
    float: right;
}
#f
{
    margin-left: 134px;
    float: right;
    margin-right: 59px;
}

#mynav {
    margin-left: 24.5px;
    width: 94.8%;
}
.navbar-default {
    background-color: white;
    border-color: white;
}
#go
{
color:white;
background-color:#70a6d6;
}
.navbar-default {
    background-color: white;
    border-color: white;
}
#main {
  width: 960px;
}

#con
{
width: 94.35%;
border:1px solid #a29e9e;
margin:30px;
height: 419px;
}
#con1
{
float:right;
padding:10px;
}
#con2
{
margin:30px;
height: 470px;
border:1px solid #a29e9e;

}
#con3
{
float:right;
padding:10px;
}
#cards
{
   width: 92%;
    margin-left: 39px;
    margin-top: 34px;
    margin-bottom: -33px
}
#chartContainer11
{
padding:50px;
margin:30px;
}
.card
{
border: 0px;
}
.btn-link {
    font-weight: 400;
    font-size: 14px;
    color: #007bff;
    text-decoration: none;
}
.navbar-brand {
    float: left;
    height: 50px;
    padding: 15px 15px;
    font-size: 18px;
    line-height: 20px;
}
.badge {
    display: inline-block;
    min-width: 10px;
    padding: 3px 7px;
    font-size: 12px;
    font-weight: 700;
    line-height: 1;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    background-color: #777;
    border-radius: 10px;
}

</style>


<script type="text/javascript">
$(document).ready(function(){	
	 $( ".container" ).sortable();
	 $( ".container" ).disableSelection();
	     $.ajax({
				url : 'card1',
				type : 'GET',
				success : function(data)
				{
				$('#h').html(data);
				}
			 });
    
	 
		 $.ajax({
				url : 'card2',
				type : 'GET',
				success : function(data)
				{
				$('#h1').html(data);
				}
			 });

	
		 
	     $.ajax({
				url : 'card3',
				type : 'GET',
				success : function(data)
				{
				$('#h2').html(data);
				}
			 }); 
	
	

		    
	     $.ajax({
				url : 'card4',
				type : 'GET',
				success : function(data)
				{
				$('#h3').html(data);
				}
			 }); 
	
	});

     var i=0;
     var a="";
    
			$.ajax({
				url : 'dash1',
				type : 'GET',
				success : function(result)
				{
				$("#chartContainer").html(result);
				var canvas = $("#chartContainer .canvasjs-chart-canvas").get(0);
				var dataURL = canvas.toDataURL();
				$("#exportButton").click(function(){
					i++;
					a+=dataURL+"raghu";
                    $(".badge").html(i);
				}); 
				}
			 });
			$.ajax({
				url : 'dash2',
				type : 'GET',
				success : function(result)
				{
				$("#chartContainer1").html(result);
				var canvas = $("#chartContainer1 .canvasjs-chart-canvas").get(0);
				var dataURL = canvas.toDataURL();
				$("#exportButton1").click(function(){
					i++;
					a+=dataURL+"raghu";
                    $(".badge").html(i);
				}); 
				}
		     });
			$.ajax({
				url : 'dash3',
				type : 'GET',
				success : function(result)
				{
				$("#chartContainer2").html(result);
				var canvas = $("#chartContainer2 .canvasjs-chart-canvas").get(0);
				var dataURL = canvas.toDataURL();
				$("#exportButton2").click(function(){
					a+=dataURL+"raghu";
					i++;
                    $(".badge").html(i);
				}); 
				}
		     });
			$.ajax({
				url : 'dash4',
				type : 'GET',
				success : function(result)
				{
				$("#chartContainer3").html(result);
				var canvas = $("#chartContainer3 .canvasjs-chart-canvas").get(0);
				var dataURL = canvas.toDataURL();
				$("#exportButton3").click(function(){
					 i++;
					 a+=dataURL+"raghu";
                     $(".badge").html(i);
				}); 
				}
		     });
			
				$("#cart").click(function()
				{
					if(i>0)
					{
					$.ajax({
						url : 'chartdata',
						type : 'POST',
						data:{val:a},
						success : function(result)
						{
							
						    alert(result);
 				        },
						
					    error : function(request, status, error)
					    {
					    	 $.ajax({
							url : 'downloads',
							type : 'GET',
						
							success : function(result)
							{
				                  location.href = "http://localhost:8081/MyBatisCrud1/downloads";
	 				        }
					     });
					     
					    }
				     });					
					}
 			}); 
			$(document).ready(function(){	
		$('#go').click(function(){
			 var startdate = $('#sdate').val();
	         //alert(startdate);
		      var closuredate = $('#cdate').val();
		      var msec = Date.parse(startdate);
		      var val=$('#se').val();
		      var d = new Date(msec);
		      //alert(d);
		      var msec1 = Date.parse(closuredate);
		      var d1 = new Date(msec1);
		      //alert(d1);
		         var timeDiff = Math.abs(d1.getTime() - d.getTime());
		         var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
		         if(val=="MONTH")
	              {
	            	 
	            	  $.ajax({
	      				url : 'dash7',
	      				type : 'GET',
	      				data:{val:val,startdate:startdate, closuredate:closuredate},
	      				success : function(result1)
	      				{
	      					
	      					 var od1 = JSON.stringify(result1) ;
	      					 $.ajax({
	      						type: "post",
	     	   	                url: "dash21",
	     	   	                data: {od1:od1,val:val},
	     	   	                success: function (data)
	     	   	                {
	     	   	               $("svg").replaceWith(data);
	     	   	                }
	     	   	            });
	      				}
	            	  });
	              }
		         else if(val=="DAY")
	              {
	            	
	      	       $.ajax({
	   				url : 'dash8',
	   				type : 'GET',
	   				data:{startdate:startdate, closuredate:closuredate },
	   				success : function(result1)
	   				{
	   				 // alert(result1);
	   				 var od1 = JSON.stringify(result1) ;
	   	            $.ajax({
	   	                type: "post",
	   	                url: "dash20",
	   	                data:{od1:od1,val:val},
	   	                success: function (data)
	   	                {
	   	               // alert(data);
	   	               $("svg").replaceWith(data);
	   	               }
	   	            });
   					
	   				}
	   			});
	           }
		});
	});
			$(document).ready(function(){	
				var chart = new CanvasJS.Chart("chartContainer11",
						{
					title:{
						text: "Population Size"
					},
					axisY: {
						title: "Number of People",
						gridThickness: 0 
					},
					      data: [
					      {
					        type: "line",
					        dataPoints: [
					        {  label: "Karnataka", y: 71 },
					        { label: "AndhraPradesh", y: 55},
					        { label: "Telangana", y: 50 },
					        { label: "Karnataka", y: 65 },
					        { label: "Kerala", y: 95 },
					        { label: "Tamilnadu", y: 68 },
					        { label: "Gujarat", y: 28 },
					        { label: "Maharashtra", y: 34 }
					        ]
					      }
					      ]
					    });
					 chart.render();
					var chartType = document.getElementById('chartType');
					chartType.addEventListener( "change",  function(){
					  chart.options.data[0].type = chartType.options[chartType.selectedIndex].value;
					  chart.render();
					});
				});
</script>
</head>
<body>
<div class="row" id="cards">
<div class="col-sm-3">
  <div class="card shadow-lg p-3 mb-5 bg-white rounded">
      <div class="card-header"><h3>Company Count</h3></div>
    <div class="card-body">
    <h4 id="h"></h4>
    <button id="d"><i class="fa fa-refresh" aria-hidden="true"></i></button></div>
  </div>
  </div>
  <div class="col-sm-3">
  <div class="card shadow-lg p-3 mb-5 bg-white rounded">
      <div class="card-header"><h3>Employee Size</h3></div>
    <div class="card-body">
    <h4 id="h1"></h4>
    <button id="a"><i class="fa fa-refresh" aria-hidden="true"></i></button></div>
  </div>
  </div>
  <div class="col-sm-3">
  <div class="card shadow-lg p-3 mb-5 bg-white rounded">
      <div class="card-header"><h3>Country Count</h3></div>
    <div class="card-body">
    <h4 id="h2"></h4>
    <button id="b"><i class="fa fa-refresh" aria-hidden="true"></i></button></div>
  </div>
  </div>
  <div class="col-sm-3">
  <div class="card shadow-lg p-3 mb-5 bg-white rounded">
      <div class="card-header"><h3>City Count</h3></div>
    <div class="card-body">
    <h4 id="h3"></h4>
    <button id="c"><i class="fa fa-refresh" aria-hidden="true"></i></button></div>
  </div>
  </div>
</div>
<!-- <div id="con2">
 <div id="con3">
<button id="exportButton" type="button">ADD</button> </div>
   <div id="chartContainer" class="card">
   </div>
    </div> -->
<div class="container">
			<!--First chart-->
			<div id="con2" class="shadow-lg p-3 mb-5 bg-white rounded" style="display:inline-block;">
			<div id="con3">
<button id="exportButton" type="button">ADD</button> </div>
					<div class="col-md-6">		
						<div id="chartContainer" style="min-width: 500px; height: 400px;"></div>	
							
					</div>
					</div>
					<div id="con2" class="shadow-lg p-3 mb-5 bg-white rounded" style="display:inline-block;">
			<div id="con3">
<button id="exportButton1" type="button">ADD</button> </div>
					<div class="col-md-6">		
						<div id="chartContainer1" style="min-width: 500px; height: 400px;"></div>	
							
					</div>
					</div>
					<div id="con2" class="shadow-lg p-3 mb-5 bg-white rounded" style="display:inline-block;">
			<div id="con3">
<button id="exportButton2" type="button">ADD</button> </div>
					<div class="col-md-6">		
						<div id="chartContainer2" style="min-width: 500px; height: 400px; "></div>	
							
					</div>
					</div>
					<div id="con2" class="shadow-lg p-3 mb-5 bg-white rounded" style="display:inline-block;">
			<div id="con3">
<button id="exportButton3" type="button">ADD</button> </div>
					<div class="col-md-6">		
						<div id="chartContainer3" style="min-width: 500px; height: 400px;  "></div>	
							
					</div>
					</div>
				
		</div>
	
	<div id="con">
	<div id="con1">Chart Type:
  <select id="chartType" name="Chart Type">
    <option value="line">Line</option>
    <option value="column">Column</option>
    <option value="bar">Bar</option>
    <option value="pie">Pie</option>
    <option value="doughnut">Doughnut</option>
  </select>  
</div>
<div id="chartContainer11" style="width: 65%; height: 300px;display:inline-block;  "></div>
</div> 
<!-- <div id="chartContainer22" style="width: 65%; height: 300px;display:inline-block;  "></div>
 --> 
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid" id="mynav">
    <div class="navbar-header">
      <a class="navbar-brand" style="color:white;">Company Report</a>
    </div>
  </div>
</nav>
<form id="f">
Starting Date:<input type="date" id="sdate"/>
Closure Date:<input type="date" id="cdate"/>
<select style="margin-left: 29px" id="se">
   <option value="" label="---REPORT BY---" />
   <option  value="DAY" label="DAY" />
   <option value="MONTH" label="MONTH" />
</select>
<input type="button" value="GO" id="go">
</form>
<svg  style="overflow: scroll;" id="sky"></svg>
</body>
</html>