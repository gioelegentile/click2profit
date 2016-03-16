var app = angular.module('clicktoprofit');
app.controller('adminAdminsController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

        if ($cookieStore.get("usr_is_admin")) {
            $scope.page.parentproperty = "Admin di sistema";

            $http.get('/api/v1/getAdmins').success(function (data) {
                $scope.admins = data;
            })

            $http.get('/api/v1/getAllUsers').success(function (data) {
                $scope.users = data;
            });

            $scope.user.add = false;

            $scope.delete = function () {
                if (confirm("Sei sicuro di voler cancellare questo admin?")) {
                    $http.get('api/v1/deleteAdmin',
                        {
                            params: {
                                "id_user": this.admin.id
                            }
                        }).then(function () {
                        Materialize.toast("Admin cancellato correttamente", 2000);
                        reloadView();
                    }, function () {
                        Materialize.toast("SERVER ERROR", 2000);
                    });
                }
            }

            function reloadView() {
                $state.reload();
            }

            $scope.predicate = 'email';
            $scope.reverse = false;
            $scope.order = function(predicate) {
                $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
                $scope.predicate = predicate;
            };

            $scope.confirm_function = function () {
                if ($scope.user.selectedUser) {
                    $http.get('api/v1/addAdmin', {
                        params: {
                            "id_user": $scope.user.selectedUser.id_user
                        }
                    }).then(function () {
                        Materialize.toast("Admin aggiunto correttamente", 2000);
                        reloadView();
                    }, function () {
                        Materialize.toast("Non puoi aggiungere un admin già presente", 2000);
                    });
                }
            }
        } else {
            $scope.panel.show = false;
            $state.go('404');
        }

    }]);