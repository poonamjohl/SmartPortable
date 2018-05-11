<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

  google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
       var jsonData = $.ajax({
              url: "DataVisualization",
              dataType: "json",
              async: false
              }).responseText;
     var chartData=JSON.parse(jsonData);
    
        var data = new google.visualization.DataTable();
        data.addColumn('string','productName');
        data.addColumn('number','count');
     for(var i=0;i<chartData.length;i++){
            var currentObj = chartData[i];
           data.addRow([currentObj["productName"],currentObj["count"]]);
          
         }
  var chart = new google.visualization.barchart(document.getElementById('barchart'));

        chart.draw(data, {width: 400, height: 240});
      }
    </script>

	</head>
	<body>
	<div id='chart_div' style='width:900px'; 'height:500px';'></div>
	</body>
	</html>
	