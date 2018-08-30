//mainController.js
angular
		.module('app')
		.controller(
				'MainController',
				function($rootScope, $location, $http, CONSTANTS) {
					$rootScope.normalHeader = true;
					$rootScope.errorHeader = false;
					$rootScope.mainHeader.show = true;
					$rootScope.sidebarPage = true;
					$rootScope.mainclass = "active";
					$rootScope.expensesclass = "";
					$rootScope.chartsclass = "";
					$rootScope.personsclass = "";
					$rootScope.owesclass = "";	

					$http
							.get(CONSTANTS.personsCountUrl)
							.then(
									function(response) {
										var data = response.data;
										window.sessionStorage["personsCount"] = data;
										$rootScope.personsCount = window.sessionStorage["personsCount"];
									}, function(error) {
									})

					$http
							.get(CONSTANTS.expensesCountUrl)
							.then(
									function(response) {
										var data = response.data;
										window.sessionStorage["expensesCount"] = data;
										$rootScope.expensesCount = window.sessionStorage["expensesCount"];
									}, function(error) {
									})

					$http
							.get(CONSTANTS.owesCountUrl)
							.then(
									function(response) {
										var data = response.data;
										window.sessionStorage["owesCount"] = data;
										$rootScope.owesCount = window.sessionStorage["owesCount"];
									}, function(error) {
									})

			
					
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
																
											var chart1 = document.getElementById("line-chart").getContext("2d");
											window.myLine = new Chart(chart1).Line(lineChartData, {
											responsive: true,
											scaleLineColor: "rgba(0,85,121,1.5)",
											scaleGridLineColor: "rgba(220,220,220,0.2)",
											scaleFontColor: "#0030ff"
											});
											
									}, function(error) {
									})
					
		
									
				});