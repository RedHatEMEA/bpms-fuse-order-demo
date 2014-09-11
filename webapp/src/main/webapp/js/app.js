angular.module('processApp', ['ui.bootstrap']);

var ProcessController = function($scope,$modal,$log,$location) {
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

    $scope.order.catalogueItems = [];

    $scope.availableCatalogueItems = [
      {id:'c1', name:'Catalogue Item 1'},
      {id:'c2', name:'Catalogue Item 2'},
      {id:'c3', name:'Catalogue Item 3'},
      {id:'c4', name:'Catalogue Item 4'},
    ];

    $scope.openCatalogueItems = function () {
        var modalInstance = $modal.open({
            templateUrl: 'catalogueItems.html',
            controller: ModalInstanceCtrl,
            size: 'sm',
            resolve: {
                items: function () {
                return $scope.availableCatalogueItems.filter(function(toFind){
                    return !$scope.order.catalogueItems.find(function(element,index,array){
                        if(toFind.id == element.id){
                            return true;
                        }
                        return false;
                    });
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