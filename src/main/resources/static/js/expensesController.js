//expensesController.js
angular.module('app').controller(
		'ExpensesController',
		function($rootScope, $location, $http, CONSTANTS, errorDataFactory) {
			$rootScope.normalHeader = true;
			$rootScope.errorHeader = false;
			$rootScope.mainHeader.show = true;
			$rootScope.sidebarPage = true;
			$rootScope.mainclass = "";
			$rootScope.expensesclass = "active";
			$rootScope.chartsclass = "";
			$rootScope.personsclass = "";
			$rootScope.owesclass = "";	

			$http.get(CONSTANTS.expensesUrl).then(
					function(response) {
						var data = response.data;
						window.sessionStorage["expensesList"] = JSON
								.stringify(data);
						$rootScope.expensesList = JSON
								.parse(window.sessionStorage["expensesList"]);
					},
					function(error) {
						if (error) {
							console.log("errorDetailsJSONString [ "
									+ JSON.stringify(error.data) + " ]")
							var data = error.data;
							errorObject = {
								timestamp : data.timestamp,
								message : data.message,
								details : data.details
							};
							errorDataFactory.set(errorObject);
							$location.path("/error");
						} else {
							errorObject = {
								timestamp : new date(),
								message : "Unexpected Error!",
								details : ""
							};
							errorDataFactory.set(errorObject);
							$location.path("/error");
						}
					})

		});