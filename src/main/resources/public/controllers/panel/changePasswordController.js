var app = angular.module('clicktoprofit');
app.controller('changePasswordController', ['$cookieStore', '$scope', '$http', function ($cookieStore, $scope, $http) {

    $scope.page.parentproperty = "Cambia password";

    $scope.updateUserPassword = function () {
        if ($scope.old_password && $scope.new_password && $scope.new_password_confirm) {
            $http.get('/api/v1/getUserData', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
                if ($.md5($scope.old_password) == data['password']) {
                    if ($scope.new_password == $scope.new_password_confirm) {
                        $http.get('/api/v1/updateUserPassword', {
                            params: {
                                "id_user": $cookieStore.get("usr_id"),
                                "password": $scope.new_password
                            }
                        }).then(function (response) {
                            $cookieStore.remove("usr_pwd");
                            $cookieStore.put("usr_pwd", $.md5($scope.new_password));
                            Materialize.toast('Password modificata correttamente', 2000);
                        }, function (response) {
                            Materialize.toast('SERVER ERROR', 2000);
                        });
                    } else {
                        Materialize.toast('Errore nella conferma della nuova password!', 2000);
                    }
                } else {
                    Materialize.toast('Password attuale errata!', 2000);
                }
            });
        } else {
            Materialize.toast('Compila tutti i campi', 2000);
        }

    }

}]);