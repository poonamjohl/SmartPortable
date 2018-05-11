<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

  google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawChart

var data = "[\"Dallas\",\"Roll\",4.0,\"Dallas\",\"Charge 3\",3.0,\"chicago\",\"WF-1000X\",5.0,\"chicago\",\"iPhone 7 Plus\",1.0,\"dallas\",\"Envy x360\",1.0,\"atlanta\",\"Series 2\",5.0,\"atlanta\",\"Gear 3\",4.0]";

function drawChart()) {
       
     var chartData=google.visualization.arrayToDataTable(data);
	 
  var options = {'width':600,
				'height':650,
				chart:{
					title:'Trending Products Chart',
					subtitle: productNameArr,
				},
				bars:'horizontal'
  };
  var chart= new google.visualization.BarChart(document.getElementById('chart_div');
  chart.draw(chartData, options);
      }
    </script>

	</head>
	<body>
	<div id='chart_div' style='width:900px'; 'height:500px';'></div>
	</body>
	</html>
	