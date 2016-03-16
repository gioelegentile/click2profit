var app = angular.module('clicktoprofit');
app.controller('adminPaymentMethodsController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

    if ($cookieStore.get("usr_is_admin")) {
        $scope.page.parentproperty = "Metodi di pagamento";
        $scope.user.add = false;

        $http.get('/api/v1/getPaymentMethods').success(function (data) {
            $scope.payment_methods = data;
        });

        function reloadView() {
            $state.reload();
        };

        $scope.confirm_function = function () {
            if ($scope.user.method) {
                $http.get('api/v1/addPaymentMethod', {
                    params: {
                        "method": $scope.user.method
                    }
                }).then(function () {
                    Materialize.toast("Metodo di pagamento aggiunto correttamente", 2000);
                    reloadView();
                }, function () {
                    Materialize.toast("SERVER ERROR", 2000);
                });
            }
        }
    } else {
        $scope.panel.show = false;
        $state.go('404');
    }

}]);