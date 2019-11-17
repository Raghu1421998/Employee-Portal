<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jamochatech.entity.CompanyTO"%>
<%@ page import="com.jamochatech.entity.ServicingIndustry"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%
	Gson gsonObj4 = new Gson();
	Map<Object, Object> map4 = null;
	@SuppressWarnings("unchecked")
	ArrayList<CompanyTO> list10 = (ArrayList<CompanyTO>) request.getAttribute("multilist");
	String dataPoints4 = null;
	List<Map<Object, Object>> list9 = new ArrayList<Map<Object, Object>>();

	for (CompanyTO category : list10)
	{
		map4 = new HashMap<Object, Object>();
		List<ServicingIndustry> serv = category.getServices();
		String esize = category.getEmpSize();
		for (int i = 0; i < serv.size(); i++)
		{
			map4.put("label", serv.get(i).getServicesIndustry());
		}
		map4.put("y", Double.parseDouble(esize));
		list9.add(map4);
	}
	dataPoints4 = gsonObj4.toJson(list9);
%>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script type="text/javascript">

		var chart3 = new CanvasJS.Chart("chartContainer3", {
			dataPointWidth : 50,
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
				type : "column",
				name : "Servicing Industry",
				click: onClick,
				yValueFormatString : "#,##0",
				indexLabel : "{y}",
				indexLabelFontSize : 14,
				showInLegend : true,
				dataPoints :<%out.print(dataPoints4);%>
	        }, {
				type : "line",
				name : "Servicing Industry",
				click: onClick,
				indexLabelFontSize : 14,
				showInLegend : true,
				dataPoints :<%out.print(dataPoints4);%>
	        } ]
		});
		chart3.render();
		function onClick(e) {
			alert(  e.dataSeries.type + ", dataPoint { x:" + e.dataPoint.label + ", y: "+ e.dataPoint.y + " }" );
		}
</script>

</head>

</html>
