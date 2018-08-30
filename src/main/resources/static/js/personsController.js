//personsController.js
angular
.module('app').controller('PersonsController', function ($rootScope,$location,$http, CONSTANTS,errorDataFactory) {
	$rootScope.normalHeader = true;
	$rootScope.errorHeader = false; 	
	$rootScope.mainHeader.show = true;
	$rootScope.sidebarPage = true; 	
	$rootScope.mainclass = ""; 	
	$rootScope.expensesclass = ""; 	
	$rootScope.chartsclass = ""; 	
	$rootScope.personsclass = "active";
	$rootScope.owesclass = "";	
	
    $http.get(CONSTANTS.personsUrl).then(
    		function (response) {
    			var data = response.data;
                window.sessionStorage["personsList"] = JSON.stringify(data);
                $rootScope.personsList = JSON.parse(window.sessionStorage["personsList"]);
            },
            function (error) {
            	console.log("errorDetailsJSONString [ " + JSON.stringify(error.data) + " ]")
            	var data = error.data;
            	errorObject = {
                		timestamp: data.timestamp,
                		message: data.message,
                		details: data.details
                	    };   
            	errorDataFactory.set(errorObject);
			    $location.path("/error");
            }	
        )
        
});