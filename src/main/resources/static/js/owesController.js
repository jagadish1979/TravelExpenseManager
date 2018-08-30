//owesController.js
angular.module('app').controller(
		'OwesController',
		function($rootScope, $location, $http, CONSTANTS, errorDataFactory) {
			$rootScope.normalHeader = true;
			$rootScope.errorHeader = false;
			$rootScope.mainHeader.show = true;
			$rootScope.sidebarPage = true;
			$rootScope.mainclass = "";
			$rootScope.expensesclass = "";
			$rootScope.chartsclass = "";
			$rootScope.personsclass = "";
			$rootScope.owesclass = "active";

			$http.get(CONSTANTS.calculateUrl).then(
					function(response) {
						var data = response.data;
						window.sessionStorage["owesList"] = JSON
								.stringify(data);
						$rootScope.owesList = JSON
								.parse(window.sessionStorage["owesList"]);
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