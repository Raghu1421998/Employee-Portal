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
<%
	Gson gsonObj4 = new Gson();
	Map<Object, Object> map4 = null;
	@SuppressWarnings("unchecked")
	ArrayList<CompanyTO> list10 = (ArrayList<CompanyTO>) request.getAttribute("multilist");
	String dataPoints4 = null;
	List<Map<Object, Object>> list9 = new ArrayList<Map<Object, Object>>();

	for (CompanyTO category : list10)
	{
		map = new HashMap<Object, Object>();
		List<ServicingIndustry> serv = category.getServices();
		String esize = category.getEmpSize();
		for (int i = 0; i < serv.size(); i++)
		{
			map.put("label", serv.get(i).getServicesIndustry());
		}
		map.put("y", Double.parseDouble(esize));
		list9.add(map);
	}
	dataPoints4 = gsonObj.toJson(list9);
%>
<!DOCTYPE HTML>
<html>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>


<script type="text/javascript">
	
		var chart2 = new CanvasJS.Chart("chartContainer2", {

			axisX :
			{
				labelFontSize : 14,
		        gridThickness: 0

			},
			axisY : 
			{
				title : "Employee Size",
				labelFontSize : 14,
		        gridThickness: 0

			},
			legend :
			{
				fontSize : 14,
			},
			data :      
				[ {
				type : "line",
			
				name : "Business Type",
				showInLegend : true,
				dataPoints :<%out.print(dataPoints);%>
	       }, {
				type : "line",
				
				name : "Servicing Industry",
				showInLegend : true,
				dataPoints :<%out.print(dataPoints4);%>
	}]
	});
		chart2.render();
	
</script>

</head>

</html>
