angular.module('processApp', ['ui.bootstrap']);

var ProcessController = function($scope,$http,$modal,$log,$location) {
    $scope.order = {campaign:{},profile:{},catalogueItems:[]};    
 
    $scope.submitOrder = function() {
      $http({
        url: 'http://192.168.33.10:8282/api/orderservice/order',
        method: "POST",
        data: $scope.order,
        headers: {'Content-Type': 'application/json'}
        })
        .success(function (data, status, headers, config) {
          alert("Success");
        }).error(function (data, status, headers, config) {
          alert("Failure");
        });
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
      {id:'c1', name:'Campaign 1', code:'CODE1', discountPercentage:'100', startDate:'1998-09-08T23:00:00.000Z', endDate:'2998-09-08T23:00:00.000Z'},
      {id:'c2', name:'Campaign 2', code:'CODE2', discountPercentage:'20', startDate:'1998-09-08T23:00:00.000Z', endDate:'2998-09-08T23:00:00.000Z'},
      {id:'c3', name:'Campaign 3', code:'CODE3', discountPercentage:'30', startDate:'1998-09-08T23:00:00.000Z', endDate:'2998-09-08T23:00:00.000Z'},
      {id:'c4', name:'Campaign 4', code:'CODE4', discountPercentage:'30', startDate:'2098-09-08T23:00:00.000Z', endDate:'2998-09-08T23:00:00.000Z'},
      {id:'c5', name:'Campaign 5', code:'CODE4', discountPercentage:'200', startDate:'1998-09-08T23:00:00.000Z', endDate:'2998-09-08T23:00:00.000Z'}
    ];    

    $scope.availableCatalogueItems = [
      {id:'ci1', name:'Catalogue Item 1', frequency:'ONCE',activationDateTime:'1998-09-08T23:00:00.000Z', expirationDateTime:'2998-09-08T23:00:00.000Z', recurringAmount:'0', oneOffAmount:'0'},
      {id:'ci2', name:'Catalogue Item 2', frequency:'MONTHLY',activationDateTime:'1998-09-08T23:00:00.000Z', expirationDateTime:'2998-09-08T23:00:00.000Z', recurringAmount:'100', oneOffAmount:'0'},
      {id:'ci3', name:'Catalogue Item 3', frequency:'ONCE',activationDateTime:'1998-09-08T23:00:00.000Z', expirationDateTime:'2998-09-08T23:00:00.000Z', recurringAmount:'0', oneOffAmount:'100'},
      {id:'ci4', name:'Catalogue Item 4', frequency:'ONCE',activationDateTime:'2098-09-08T23:00:00.000Z', expirationDateTime:'2998-09-08T23:00:00.000Z', recurringAmount:'0', oneOffAmount:'100'}
    ];

    $scope.openCatalogueItems = function () {
        var modalInstance = $modal.open({
            templateUrl: 'catalogueItems.html',
            controller: ModalInstanceCtrl,
            size: 'sm',
            resolve: {
                items: function () {
                  return $scope.availableCatalogueItems.filter(function(toFind){
                    var matches = $.grep($scope.order.catalogueItems, function(e){ 
                      return e.id == toFind.id; 
                    });
                    /*
                    Filter returns the list of items that are found to be true
                    for the mapped function.  We want the opposite
                    */
                    return !(matches.length > 0);                    
                  });
                }
            }
    });

    $scope.removeCatalogueItem = function(index){
        $scope.order.catalogueItems.splice(index,1);
    }

    modalInstance.result.then(function (selectedItems) {        
        $scope.order.catalogueItems = $scope.order.catalogueItems.concat(selectedItems);        
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };
    
};

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {

  $scope.items = items;
  
  $scope.selection = [];

  $scope.toggleSelection = function(index){
    var idx = $scope.selection.indexOf(index);
   
    // is currently selected
    if (idx > -1) {
        $scope.selection.splice(idx, 1);
    }   
    // is newly selected
    else {
        $scope.selection.push(index);
    }  
  }

  $scope.ok = function () {
    selectedItems = [];
    angular.forEach($scope.selection,function(index){        
        selectedItems.push($scope.items[index]);
    });    
    $modalInstance.close(selectedItems);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};
