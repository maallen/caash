myAppModule.controller('ViewJobsController',function($scope,$routeParams,$timeout,$http,$resource, Jobs) {
    
	$scope.jobs = [];
	
	$scope.orderBy = 'price';
	
	$scope.map = { 
		center: { latitude: 53.2734, longitude: -7.7 },
		zoom: 8 };
	
	(function getJobs() {
		Jobs.query({
	        modelId : $routeParams.modelId
	    }, function(model) {
	        $scope.jobs = model;
	        $timeout(getJobs, 5000);
	    });
	})();
});

myAppModule.controller('CreateJobsController',function($scope, $http, CreateJobService){
    
    $scope.createJob = function(){
        CreateJobService.createJob($scope.job);
    };
});
