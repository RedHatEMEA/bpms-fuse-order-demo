/**
 * App JS - Basic Routing Rules 
 */

var routerApp = angular.module('routerApp', ['ui.router']).directive('bsHolder', function() {
    return {
        link: function (scope, element, attrs) {
            Holder.run({images:element.get(0), nocss:true});
        }
    };    
});

routerApp.config(function($stateProvider, $urlRouterProvider) {    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'partials/home.html'
        })
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
        	url: '/about',
            templateUrl: 'partials/about.html'     
        });
});

angular.module('routerApp')
  .controller('NavigationCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.isCurrentPath = function (path) {
      return $location.path() == path;
    };
}]);