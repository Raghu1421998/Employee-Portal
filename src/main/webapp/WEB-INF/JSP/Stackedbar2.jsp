<!-- <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Company Charts</title>
  <script src="https://d3js.org/d3.v3.min.js" charset="utf-8"></script>
  <style type="text/css">
  svg {
    font: 12px sans-serif;
    shape-rendering: crispEdges;
    margin-right: 154px;
    float: right;
    margin-top: 120px;
    border:1px solid #a29e9e;
    margin-bottom: 60px;
  }

  .axis path,
  .axis line {
    fill: none;
    stroke: #000;
  }
 
  path.domain {
    stroke: none;
  }
 
  .y .tick line {
    stroke: #ddd;
  }
 
  </style>

</head>
<body>
<script type="text/javascript">

// Setup svg using Bostock's margin convention

var margin = {top: 20, right: 160, bottom: 35, left: 30};

var width = 1200 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var svg = d3.select("body")
  .append("svg")
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)
  .append("g")
  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


 var data1=${od1};
 //alert(data1);
 var data = JSON.parse(data1);
 //console.log(data); 
var parse = d3.time.format("%b %Y").parse;


// Transpose the data into layers
var dataset = d3.layout.stack()(["Total Employees", "Resigned"].map(function(fruit) {
  return data.map(function(d) {
    return {x: parse(d.Day), y: +d[fruit]};
  });
}));


// Set x, y and colors
var x = d3.scale.ordinal()
  .domain(dataset[0].map(function(d) { return d.x; }))
  .rangeRoundBands([10, width-10], 0.02);

var y = d3.scale.linear()
  .domain([0, d3.max(dataset, function(d) {  return d3.max(d, function(d) { return d.y0 + d.y; });  })])
  .range([height, 0]);

var colors = ["#1f77b4", "#FF0000"];


// Define and draw axes
var yAxis = d3.svg.axis()
  .scale(y)
  .orient("left")
  .ticks(5)
  .tickSize(-width, 0, 0)
  .tickFormat( function(d) { return d } );

var xAxis = d3.svg.axis()
  .scale(x)
  .orient("bottom")
  .tickFormat(d3.time.format("%b %Y"));

svg.append("g")
  .attr("class", "y axis")
  .call(yAxis);

svg.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis);


// Create groups for each series, rects for each segment 
var groups = svg.selectAll("g.cost")
  .data(dataset)
  .enter().append("g")
  .attr("class", "cost")
  .style("fill", function(d, i) { return colors[i]; });

var rect = groups.selectAll("rect")
.data(function(d) { return d; })
.enter()
.append("rect")
.attr("x", function(d) { return x(d.x); })
.attr("y", function(d) { return y(d.y0 + d.y); })
.attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
.attr("width", x.rangeBand())
.on("mouseover", function() { tooltip.style("display", null); })
.on("mouseout", function() { tooltip.style("display", "none"); })
.on("mousemove", function(d) {
  var xPosition = d3.mouse(this)[0] - 15;
  var yPosition = d3.mouse(this)[1] - 25;
  tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
  tooltip.select("text").text(d.y);
});


// Draw legend
var legend = svg.selectAll(".legend")
  .data(colors)
  .enter().append("g")
  .attr("class", "legend")
  .attr("transform", function(d, i) { return "translate(30," + i * 19 + ")"; });
 
legend.append("rect")
  .attr("x", width - 18)
  .attr("width", 18)
  .attr("height", 18)
  .style("fill", function(d, i) {return colors.slice().reverse()[i];});
 
legend.append("text")
  .attr("x", width + 5)
  .attr("y", 9)
  .attr("dy", ".35em")
  .style("text-anchor", "start")
  .text(function(d, i) { 
    switch (i) {
      case 0: return "Resigned";
      case 1: return "Total Employees";
    }
  });


// Prep the tooltip bits, initial display is hidden
var tooltip = svg.append("g")
  .attr("class", "tooltip")
  .style("display", "none");
    
tooltip.append("rect")
  .attr("width", 30)
  .attr("height", 20)
  .attr("fill", "white")
  .style("opacity", 0.5);

tooltip.append("text")
  .attr("x", 15)
  .attr("dy", "1.2em")
  .style("text-anchor", "middle")
  .attr("font-size", "12px")
  .attr("font-weight", "bold");

//d3.selectAll("svg > *").remove();
//svg.selectAll("*").remove();


</script>
</body>
</html> -->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Company Charts</title>
  <script src="https://d3js.org/d3.v3.min.js" charset="utf-8"></script>
  <style type="text/css">
  svg {
    font: 12px sans-serif;
    shape-rendering: crispEdges;
    margin-right: 154px;
    float: right;
    margin-top: 120px;
    border:1px solid #a29e9e;
    margin-bottom: 60px;
  }

  .axis path,
  .axis line {
    fill: none;
    stroke: #000;
  }
 
  path.domain {
    stroke: none;
  }
 
  .y .tick line {
    stroke: #ddd;
  }
 .label {
    display: inline;
    padding: .2em .6em .3em;
    font-size: 75%;
    font-weight: 700;
    line-height: 1;
    color: #1b1414;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    border-radius: .25em;
}
  </style>

</head>
<body>
<script type="text/javascript">
var margin = {top: 20, right: 160, bottom: 35, left: 30};

var width = 1200 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var svg = d3.select("body")
  .append("svg")
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)
  .append("g")
  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


 var data1=${od1};
 //alert(data1);
 var data = JSON.parse(data1);
 //console.log(data); 
var parse = d3.time.format("%b %Y").parse;


var dataset = d3.layout.stack()(["TotalEmployees", "Resigned"].map(function(fruit) {
  return data.map(function(d) {
    return {x: parse(d.Day), y: +d[fruit]};
  });
}));


// Set x, y and colors
var x = d3.scale.ordinal()
  .domain(dataset[0].map(function(d) { return d.x; }))
  .rangeRoundBands([10, width-10], 0.02);

var y = d3.scale.linear()
  .domain([0, d3.max(dataset, function(d) {  return d3.max(d, function(d) { return d.y0 + d.y; });  })])
  .range([height, 0]);

var colors = ["#1f77b4", "red"];


// Define and draw axes
var yAxis = d3.svg.axis()
  .scale(y)
  .orient("left")
  .ticks(5)
  .tickSize(-width, 0, 0)
  .tickFormat( function(d) { return d } );


var xAxis = d3.svg.axis()
  .scale(x)
  .orient("bottom")
  .tickFormat(d3.time.format("%b %Y"));

svg.append("g")
  .attr("class", "y axis")
  .call(yAxis);

svg.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis);


// Create groups for each series, rects for each segment 
var groups = svg.selectAll("g.cost")
  .data(dataset)
  .enter().append("g")
  .attr("class", "cost")
  .style("fill", function(d, i) { return colors[i]; });

var rect = groups.selectAll("rect")
  .data(function(d) { return d; })
  .enter()
  .append("rect")
  .attr("x", function(d) { return x(d.x); })
  .attr("y", function(d) { return y(d.y0 + d.y); })
  .attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
  .attr("width", x.rangeBand())
  .on("mouseover", function() { tooltip.style("display", null); })
  .on("mouseout", function() { tooltip.style("display", "none"); })
  .on("mousemove", function(d) {
    var xPosition = d3.mouse(this)[0] - 15;
    var yPosition = d3.mouse(this)[1] - 25;
    tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
    tooltip.select("text").text(d.y);
  });
 
   var a=0;
	svg.append("g").selectAll("text")
    .data(data).enter()
    .append("text")
    .attr("text-anchor", "middle")
    .attr("x", function(d) 
    {
    	return a+=width/data.length-3;
       })
.attr("y", function(d) { return height; })   
.text(
    function(d) 
    {
    	return (d.TotalEmployees + d.Resigned); 
    	
    })
     .attr("font-size","14px")
    .style("fill", 'white'); 
  
var legend = svg.selectAll(".legend")
  .data(colors)
  .enter().append("g")
  .attr("class", "legend")
  .attr("transform", function(d, i) { return "translate(30," + i * 19 + ")"; });
 
legend.append("rect")
.attr("x", width - 18)
.attr("width", 18)
.attr("height", 18)
.style("fill", function(d, i) {return colors.slice().reverse()[i];});
 
legend.append("text")
  .attr("x", width + 5)
  .attr("y", 9)
  .attr("dy", ".35em")
  .style("text-anchor", "start")
  .text(function(d, i) { 
    switch (i) {
      case 0: return "Resigned";
      case 1: return "TotalEmployees";
      
    }
  });


// Prep the tooltip bits, initial display is hidden
var tooltip = svg.append("g")
  .attr("class", "tooltip")
  .style("display", "none");
    
tooltip.append("rect")
  .attr("width", 10)
  .attr("height", 20)
  .attr("fill", "white")
  .style("opacity", 0.5);

tooltip.append("text")
  .attr("x", 15)
  .attr("dy", "1.2em")
  .style("text-anchor", "middle")
  .attr("font-size", "12px")
  .attr("font-weight", "bold");


</script>
</body>
</html>