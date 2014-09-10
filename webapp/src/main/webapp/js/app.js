angular.module('processApp', ['ui.bootstrap'])
  .controller('ProcessController', ['$scope', function($scope) {
    $scope.order = {};    
 
    $scope.submitOrder = function() {
        alert("Submitting");
    };

    $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.initDate = new Date('2016-15-20');  
    $scope.format = 'dd-MMMM-yyyy';

    $scope.campaigns = [
      {id:'c1', name:'Campaign 1'},
      {id:'c2', name:'Campaign 2'},
      {id:'c3', name:'Campaign 3'},
      {id:'c4', name:'Campaign 4'},
      {id:'c5', name:'Campaign 5'}
    ];

    $scope.catalogueItems = [
      {id:'c1', name:'CI 1'},
      {id:'c2', name:'CI 2'},
      {id:'c3', name:'CI 3'},
      {id:'c4', name:'CI 4'},
    ];
    
  }]);