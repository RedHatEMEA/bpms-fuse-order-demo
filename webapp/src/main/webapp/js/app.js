angular.module('processApp', ['ui.bootstrap'])
  .controller('ProcessController', ['$scope', function($scope) {
    $scope.campaigns = [
      {text:'learn angular', done:true},
      {text:'build an angular app', done:false}];
 
    $scope.submitRequest = function() {
        var lastName = $scope.lastName;
        alert(lastName);      
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
    
  }]);