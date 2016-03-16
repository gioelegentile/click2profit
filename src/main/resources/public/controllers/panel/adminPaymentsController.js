var app = angular.module('clicktoprofit');
app.controller('adminPaymentsController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

        if ($cookieStore.get("usr_is_admin")) {
            $scope.page.parentproperty = "Pagamenti agli utenti";
            var date = new Date();
            $scope.date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            $scope.a = {
                add: false
            };

            $http.get('/api/v1/getAllUsers').success(function (data) {
                $scope.users = data;
            });

            $scope.change = function () {
                $scope.a.add = false;
                $http.get('/api/v1/getUserPaymentMethods', {params: {"id_user": $scope.selectedUser.id_user}}).success(function (data) {
                    $scope.a.methods = data;
                });
                $http.get('/api/v1/getUserData', {params: {"id_user": $scope.selectedUser.id_user}}).success(function (data) {
                    $scope.user = data;
                });
                $http.get('/api/v1/getUserPayments', {params: {"id_user": $scope.selectedUser.id_user}}).success(function (data) {
                    $scope.a.payments = data;
                });
            }

            $scope.predicate = 'date';
            $scope.reverse = true;
            $scope.order = function (predicate) {
                $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
                $scope.predicate = predicate;
            };

            $scope.confirm_function = function () {
                if ($scope.a.selectedMethod) {
                    $http.get('api/v1/addPayment', {
                        params: {
                            "id_payment_method": $scope.a.selectedMethod.id_payment_method,
                            "id_user": $scope.selectedUser.id_user,
                            "email": $scope.selectedUser.email,
                            "payment": $scope.a.payment,
                            "date": $scope.date,
                            "method": $scope.a.selectedMethod.method
                        }
                    }).then(function () {
                        Materialize.toast("Pagamento aggiunto", 2000);
                        reloadView();
                    }, function () {
                        Materialize.toast("SERVER ERROR", 2000);
                    });
                } else {
                    console.log("asd");
                }
            }

            function reloadView() {
                //var params = angular.copy($state.params);
                //params.trigger = params.trigger === null ? "" : null;
                //$state.go($state.current, params, {reload: false, inherit: true, notify: true});
                $state.reload();
            }
        } else {
            $scope.panel.show = false;
            $state.go('404');
        }

    }]);