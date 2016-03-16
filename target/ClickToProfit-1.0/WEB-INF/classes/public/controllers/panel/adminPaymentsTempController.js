var app = angular.module('clicktoprofit');
app.controller('adminPaymentsTempController', ['$state', '$scope', '$http', function ($state, $scope, $http) {

    $scope.page.parentproperty = "Richieste pagamenti";

    $http.get('/api/v1/getPaymentsTemp').success(function (data) {
        $scope.payments_temp = data;
    });

    $scope.predicate = 'surname';
    $scope.reverse = true;
    $scope.order = function (predicate) {
        $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
        $scope.predicate = predicate;
    };

    $scope.confirm_function = function () {
        $http.get('/api/v1/deletePaymentTemp', {
            params: {
                "id_user": this.payment_temp.id_user
            }
        }).then(function () {
            Materialize.toast("Richiesta eliminata.", 3000);
            $state.reload();
        }, function () {
            Materialize.toast("SERVER ERROR", 3000);
        });
    }

}]);