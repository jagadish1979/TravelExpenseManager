//app.js
var app = angular.module("app", ["ngRoute"]);
app.config(function($routeProvider) {
$routeProvider
.when("/", {
	controller: 'LoginController',
    templateUrl : "/expenses/views/login.html",
	controllerAs: 'vm'
})
.when("/logout", {
	controller: 'LogoutController',
    templateUrl : "/expenses/views/logout.html",
	controllerAs: 'vm'
})
.when("/main", {
	controller: 'MainController',
    templateUrl : "/expenses/views/main.html",
	controllerAs: 'vm'
})
.when("/error", {
	controller: 'ErrorController',
    templateUrl : "/expenses/views/error.html",
	controllerAs: 'vm'
})
.when("/error_login", {
	controller: 'ErrorLoginController',
    templateUrl : "/expenses/views/error_login.html",
	controllerAs: 'vm'
})
.when("/persons", {
	controller: 'PersonsController',
    templateUrl : "/expenses/views/persons.html",
	controllerAs: 'vm'
})
.when("/expenses", {
	controller: 'ExpensesController',
    templateUrl : "/expenses/views/expenses.html",
	controllerAs: 'vm'
})
.when("/owes", {
	controller: 'OwesController',
    templateUrl : "/expenses/views/owes.html",
	controllerAs: 'vm'
})
.when("/charts", {
	controller: 'ChartsController',
    templateUrl : "/expenses/views/charts.html",
	controllerAs: 'vm'
})
.otherwise({ redirectTo: '/' });
});

app.constant("CONSTANTS", {
	     loginURL: "/expenses/doLogin",
  personsCountUrl: "/expenses/findPersonsCount",
       personsUrl: "/expenses/findAllPersons", 
      expensesUrl: "/expenses/findAllExpenses",
 expensesCountUrl: "/expenses/findExpensesCount",
     owesCountUrl: "/expenses/findPersonsOwsMoneyCount",
     calculateUrl: "/expenses/calculatePersonsOwsMoney",
 mainChartDataUrl: "/expenses/getMoneySpendByPersonsChartData",
 pieChartDataUrl: "/expenses/getExpensesByDescriptionChartData",
 owesChartDataUrl: "/expenses/getOwesChartData"
     
	 
});

app.config(function ($provide) {

    $provide.decorator('$exceptionHandler', function ($delegate,$window,errorDataFactory) {

        return function (exception, cause) {
            $delegate(exception, cause);
        	errorObject = {
            		message: "Unexpected Error Occured. Please Contat System Admin!"
            	    }; 
            errorDataFactory.set(errorObject);
		    $window.location.href = '#!error';        
		    };
    });
});

app.run(function($rootScope) {
    //Should below code be using rootScope or localStorage.. Check which one is better and why.
    if (window.sessionStorage["userDetails"]) {
    	$rootScope.mainHeader = {};    	
		$rootScope.normalHeader = true;
		$rootScope.sidebarPage = true; 	
		$rootScope.errorHeader = false; 
        $rootScope.userDetails = JSON.parse(window.sessionStorage["userDetails"]);
        window.location.href = "#!main";
    }
    else{
        window.location.href = "#!login";
    }

});
