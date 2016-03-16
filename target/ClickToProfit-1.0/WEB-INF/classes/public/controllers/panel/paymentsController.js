var app = angular.module('clicktoprofit');
app.controller('paymentsController', ['$state', '$cookieStore', '$scope', '$http', function ($state, $cookieStore, $scope, $http) {

    $scope.page.parentproperty = "Cronologia pagamenti ricevuti";

    $http.get('/api/v1/getUserPayments', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
        $scope.payments = data;
    });

    $http.get('/api/v1/getUserPaymentMethods', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
        $scope.methods = data;
    });

    $scope.predicate = 'date';
    $scope.reverse = true;
    $scope.order = function (predicate) {
        $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
        $scope.predicate = predicate;
    };

    $scope.confirm_function = function () {
        if (parseFloat($scope.page.current_balance) >= 10) {
            if (parseFloat($scope.page.current_balance) >= parseFloat($scope.a.amount)) {
                $http.get('/api/v1/addPaymentTemp', {
                    params: {
                        "payment_method": $scope.a.selectedMethod.method,
                        "notes": $scope.a.selectedMethod.notes,
                        "payment": $scope.a.amount,
                        "id_user": $cookieStore.get("usr_id")
                    }
                }).then(function () {
                    Materialize.toast("Richiesta inviata! Al più presto un admin la riceverà.", 4000);
                    $state.reload();
                }, function () {
                    Materialize.toast("Hai già richiesto un pagamento! Non puoi richiederne più di uno contemporaneamente.", 4000);
                });
            } else {
                Materialize.toast("Non puoi richiedere un pagamento maggiore rispetto al tuo saldo.", 4000);
            }
        } else {
            Materialize.toast("Non hai ancora raggiunto la soglia di pagamento di 10&euro;", 4000);
        }
    }

}]);