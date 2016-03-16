var app = angular.module('clicktoprofit');
app.controller('userPaymentMethodsController', ['$cookieStore', '$scope', '$http', function ($cookieStore, $scope, $http) {

    $scope.page.parentproperty = "Metodi di pagamento";
    $scope.a = {
        "add" : false,
        "selectedMethod" : null,
        "notes" : null
    };
    $scope.show = false;

    $http.get('/api/v1/getUserPaymentMethods', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
        $scope.payment_methods = data;
        if ($scope.payment_methods.length) $scope.show = true;
    });

    $http.get('/api/v1/getPaymentMethods').success(function (data) {
        $scope.methods = data;
    });

    $scope.confirm_function = function () {
        if ($scope.a.selectedMethod && $scope.a.notes) {
            $http.get('/api/v1/addUserPaymentMethod', {
                params : {
                    "id_user" : $cookieStore.get("usr_id"),
                    "idPaymentMethod" : $scope.a.selectedMethod.id_payment_method,
                    "notes" : $scope.a.notes
                }
            }).then(function () {
                Materialize.toast("Metodo di pagamento aggiunto correttamente", 3000);
                $scope.a.add = false;
                var table = document.getElementById('table');
                var row = table.insertRow(table.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                cell1.innerHTML = "<td>"+$scope.a.selectedMethod.method+"</td>";
                cell2.innerHTML = "<td>"+$scope.a.notes+"</td>";
            }, function() {
                Materialize.toast("SERVER ERROR", 3000);
            });
        } else {
            Materialize.toast("Compila tutti i campi", 3000);
        }
    }

}]);