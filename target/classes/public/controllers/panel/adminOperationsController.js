var app = angular.module('clicktoprofit');
app.controller('adminOperationsController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

    if ($cookieStore.get("usr_is_admin")) {
        $scope.page.parentproperty = "Operazioni degli utenti";
        var date = new Date();
        $scope.date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        $scope.time = date.getHours() + ":" + date.getMinutes() + ":00";
        $scope.a = {
            add: false
        };

        $http.get('/api/v1/getCampaigns').success(function (data) {
            $scope.campaigns = data;
        });

        $http.get('/api/v1/getAllUsers').success(function (data) {
            $scope.users = data;
        });

        $scope.change = function () {
            $scope.a.add = false;
            $http.get('/api/v1/getUserData', {params: {"id_user": $scope.selectedUser.id_user}}).success(function (data) {
                $scope.user = data;
            });
            $http.get('/api/v1/getUserOperations', {params: {"id_user": $scope.selectedUser.id_user}}).success(function (data) {
                $scope.a.operations = data;
            });
        }

        $scope.confirm_function = function () {
            if ($scope.a.selectedCampaign) {
                $http.get('api/v1/addOperation', {
                    params: {
                        "id_user": $scope.selectedUser.id_user,
                        "id_campaign": $scope.a.selectedCampaign.id_campaign,
                        "profit": $scope.a.selectedCampaign.profit,
                        "date": $scope.date,
                        "time": $scope.time
                    }
                }).then(function () {
                    Materialize.toast("Operazione aggiunta", 2000);
                    reloadView();
                }, function () {
                    Materialize.toast("SERVER ERROR", 2000);
                });
            } else {
                console.log("asd");
            }
        }

        function reloadView() {
            $state.reload();
        }

        $scope.predicate = 'date';
        $scope.reverse = true;
        $scope.order = function(predicate) {
            $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
            $scope.predicate = predicate;
        };

    } else {
        $scope.panel.show = false;
        $state.go('404');
    }

}]);