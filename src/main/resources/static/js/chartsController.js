//chartsController.js
angular
.module('app').controller('ChartsController', function ($rootScope,$location,$http, CONSTANTS) {
	$rootScope.normalHeader = true;
	$rootScope.errorHeader = false; 	
	$rootScope.mainHeader.show = true;
	$rootScope.sidebarPage = true; 	
	$rootScope.mainclass = ""; 	
	$rootScope.expensesclass = ""; 	
	$rootScope.chartsclass = "active"; 	
	$rootScope.personsclass = ""; 	
	$rootScope.owesclass = "";	
	
	$http
	.get(CONSTANTS.mainChartDataUrl)
	.then(
			function(response) {
				var personNames = response.data.personNames;
				var moneySpentList = response.data.moneySpentList;
				var lineChartData = {
						labels : personNames , 
						datasets : [
							{
								label: "Money Spent By Person",
								fillColor : "rgba(48, 164, 255, 0.2)",
								strokeColor : "rgba(48, 164, 255, 1)",
								pointColor : "rgba(48, 164, 255, 1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(48, 164, 255, 1)",
								data : moneySpentList 
							}

						]
					}
										
					var lineChart = document.getElementById("line-chart").getContext("2d");
					window.myLine = new Chart(lineChart).Line(lineChartData, {
					responsive: true,
					scaleLineColor: "rgba(0,85,121,1.5)",
					scaleGridLineColor: "rgba(220,220,220,0.2)",
					scaleFontColor: "#0030ff"
					});
					
					
					var barChartData = {
							labels : personNames,
							datasets : [
								{
									fillColor : "rgba(48, 164, 255, 0.2)",
									strokeColor : "rgba(48, 164, 255, 0.8)",
									highlightFill : "rgba(48, 164, 255, 0.75)",
									highlightStroke : "rgba(48, 164, 255, 1)",
									data : moneySpentList
								}
							]

							}
					
					var barChart = document.getElementById("bar-chart").getContext("2d");
					window.myBar = new Chart(barChart).Bar(barChartData, {
					responsive: true,
					scaleLineColor: "rgba(0,85,121,1.5)",
					scaleGridLineColor: "rgba(220,220,220,0.2)",
					scaleFontColor: "#0030ff"
					});
					
			}, function(error) {
			})

			$http.get(CONSTANTS.pieChartDataUrl)
				.then(
						function(response) {
							var pieData = response.data;
							var pieChart = document.getElementById("pie-chart").getContext("2d");
							window.myPie = new Chart(pieChart).Pie(pieData, {
							responsive: true,
							segmentShowStroke: false
							});	
						}, function(error) {
						})

			$http.get(CONSTANTS.owesChartDataUrl)
				.then(
						function(response) {
							var owesData = response.data;
							var owesChart = document.getElementById("doughnut-chart").getContext("2d");
							window.myDoughnut = new Chart(owesChart).Doughnut(owesData, {
							responsive: true,
							segmentShowStroke: false
							});
							
						}, function(error) {
						})
						

});