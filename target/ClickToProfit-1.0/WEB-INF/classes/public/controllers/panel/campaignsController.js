var app = angular.module('clicktoprofit');
app.controller('campaignsController', ['$scope', '$http', function ($scope, $http) {

    $scope.page.parentproperty = "Campagne";

    $http.get('/api/v1/getCampaigns').success(function (data) {
        $scope.campaigns = data;
    });

    $scope.predicate = 'deadline';
    $scope.reverse = false;
    $scope.order = function(predicate) {
        $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
        $scope.predicate = predicate;
    };

}]);