var app = angular.module('clicktoprofit');
app.controller('loginController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

        $scope.login = function () {
            if ($scope.email && $scope.password) {
                $http.get('api/v1/autenticate', {
                    params: {
                        "email": $scope.email,
                        "password": $.md5($scope.password)
                    }
                }).then(function (data) {
                    if (data.data == "true") {
                        Materialize.toast('Accesso effettuato!', 2000);
                        $http.get('/api/v1/getIdByEmail', {params: {"email": $scope.email}}).success(function (data) {
                            $cookieStore.put('usr_email', $scope.email);
                            $cookieStore.put('usr_pwd', $.md5($scope.password));
                            $cookieStore.put('usr_id', data.substring(1, data.length - 1));
                            if ($scope.checkboxlogin === undefined) {
                                $cookieStore.put('usr_ka', false);
                            } else {
                                $cookieStore.put('usr_ka', $scope.checkboxlogin);
                            }

                            $http.get('/api/v1/getAdmins').success(function (data0) {
                                $cookieStore.put("usr_is_admin", false);
                                for (var i = 0; i < data0.length; i++) {
                                    if ($cookieStore.get("usr_id") == data0[i]['id']) {
                                        $cookieStore.put("usr_is_admin", true);
                                    }
                                }
                                console.log($cookieStore.get("usr_id"));
                                console.log($cookieStore.get("usr_pwd"));
                                console.log($cookieStore.get("usr_email"));
                                console.log($cookieStore.get("usr_ka"));
                                console.log($cookieStore.get("usr_is_admin"));
                                $state.go('panel.operations');
                            });

                        });
                    } else {
                        Materialize.toast('Email e/o password errati.', 2000);
                    }
                }, function (data) {
                    Materialize.toast('SERVER ERROR', 2000);
                });
            } else {
                Materialize.toast('Compila tutti i campi.', 2000);
            }
        };

    }]);