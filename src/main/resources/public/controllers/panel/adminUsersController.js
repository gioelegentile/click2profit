var app = angular.module('clicktoprofit');
app.controller('adminUsersController', ['$state', '$scope', '$http',
    function ($state, $scope, $http) {

        if ($scope.user.isAdmin) {
            $scope.page.parentproperty = "Utenti";

            $http.get('/api/v1/getAllUsers').success(function (data) {
                $scope.users = data;
            });

            $scope.predicate = 'email';
            $scope.reverse = false;
            $scope.order = function (predicate) {
                $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
                $scope.predicate = predicate;
            };

            $scope.confirm_function = function () {
                $http.get('/api/v1/updateUserCurrentBalance', {
                    params: {
                        "id_user": this.user.id_user,
                        "current_balance": this.user.current_balance.replace(",", ".")
                    }
                }).then(function () {
                    Materialize.toast("Saldo modificato correttamente", 3000);
                    reloadView();
                }, function () {
                    Materialize.toast("SERVER ERROR", 3000);
                });
            }

            function reloadView() {
                $state.reload();
            }

        } else {
            $scope.panel.show = false;
            $state.go('404');
        }

    }]);