<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jamochatech.entity.CompanyTO"%>
<%@ page import="com.jamochatech.entity.ServicingIndustry"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%
	Gson gsonObj = new Gson();
	Map<Object, Object> map = null;
	@SuppressWarnings("unchecked")
	ArrayList<CompanyTO> list2 = (ArrayList<CompanyTO>) request.getAttribute("barlist");
	String dataPoints = null;
	List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
	for (CompanyTO category : list2)
	{
		map = new HashMap<Object, Object>();
		String buType = category.getBusinessType();
		String esize = category.getEmpSize();
		map.put("label", buType);
		map.put("y", Double.parseDouble(esize));
		list1.add(map);
	}
	dataPoints = gsonObj.toJson(list1);
%>
<!DOCTYPE HTML>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script type="text/javascript">

	var chart = new CanvasJS.Chart("chartContainer", {
			dataPointWidth : 50,
			axisX :
			{
				labelFontSize : 14,
		        gridThickness: 0        

			},
			axisY : 
			{
				labelFontSize : 14,
				title : "Employee Size",
		        gridThickness: 0       

			},
			legend : 
			{
				fontSize : 14,
			},
			data :
				[ { 
				type : "column",
				name : "Servicing Industry",
				click: onClick,
				yValueFormatString : "#,##0",
				indexLabel : "{y}",
				indexLabelFontSize : 14,
				showInLegend : "true",
				dataPoints :<%out.print(dataPoints);%>
	            } ]
		});
		chart.render();
		function onClick(e) {
			//alert( e.dataPoint.label);
			 /* $.ajax({
				url : 'cha',
				type : 'GET',
				data:{val:e.dataPoint.label},
				success : function(result)
				{
					$("#chartContainer22").html(result);
				} 
			}); */ 
		alert(  e.dataSeries.type + ", dataPoint { x:" + e.dataPoint.label + ", y: "+ e.dataPoint.y + " }" );
			
		}

</script>
</html>
