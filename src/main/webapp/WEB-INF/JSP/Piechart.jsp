<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jamochatech.entity.CompanyTO"%>
<%@ page import="com.jamochatech.entity.ServicingIndustry"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%
	Gson gsonObj1 = new Gson();
	Map<Object, Object> map1 = null;
	@SuppressWarnings("unchecked")
	ArrayList<CompanyTO> list4 = (ArrayList<CompanyTO>) request.getAttribute("companyList1");
	String dataPoints1 = null;
	List<Map<Object, Object>> list3 = new ArrayList<Map<Object, Object>>();
	for (CompanyTO category : list4) 
	{
		map1 = new HashMap<Object, Object>();
		String city = category.getCity();
		String esize = category.getEmpSize();
		map1.put("label", city);
		map1.put("y", Double.parseDouble(esize));
		list3.add(map1);
	}
	dataPoints1 = gsonObj1.toJson(list3);
%>

<!DOCTYPE HTML>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
	
		
		var chart1 = new CanvasJS.Chart("chartContainer1", {

			theme : "light2",
			legend : 
			{
				fontSize : 14,
			},
			
			data :
				[ {
				type : "pie",
				startAngle : 25,
				click: onClick,
				toolTipContent : "<b>{label}</b>: {y}",
				showInLegend : "true",
				legendText : "{label}",
				indexLabelFontSize : 14,
				indexLabel : "{label} - {y}",
				dataPoints :<%out.print(dataPoints1);%>
	            } ]
		});
		
		
		chart1.render();
		function onClick(e) {
			alert(  e.dataSeries.type + ", dataPoint { x:" + e.dataPoint.label + ", y: "+ e.dataPoint.y + " }" );
		}
	
</script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

</head>

</html>
