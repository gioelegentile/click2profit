var app = angular.module('clicktoprofit');
app.controller('adminOperationsTempController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

    if ($cookieStore.get("usr_is_admin")) {
        $scope.page.parentproperty = "Richieste operazioni degli utenti";

        $http.get('/api/v1/getAllNotAcceptedOperations').success(function (data) {
            $scope.operations_temp = data;
        });

        $scope.predicate = 'date';
        $scope.reverse = false;
        $scope.order = function(predicate) {
            $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
            $scope.predicate = predicate;
        };

        $scope.confirm_function = function () {
            $http.get('api/v1/acceptOperation', {
                params: {
                    "id_user": this.operation_temp.id_user,
                    "id_campaign": this.operation_temp.id_campaign,
                    "company": this.operation_temp.company,
                    "type": this.operation_temp.type,
                    "profit": this.operation_temp.profit,
                    "date": this.operation_temp.date,
                    "time": this.operation_temp.time,
                    "link": this.operation_temp.link,
                    "email": this.operation_temp.email
                }
            }).then(function () {
                Materialize.toast("Operazione aggiunta", 2000);
                reloadView();
            }, function () {
                Materialize.toast("SERVER ERROR", 2000);
            });
        }

        $scope.delete_function = function () {
            $http.get('api/v1/deleteOperation', {
                params: {
                    "id_user": this.operation_temp.id_user,
                    "id_campaign": this.operation_temp.id_campaign,
                    "company": this.operation_temp.company,
                    "type": this.operation_temp.type,
                    "profit": this.operation_temp.profit,
                    "date": this.operation_temp.date,
                    "time": this.operation_temp.time,
                    "link": this.operation_temp.link,
                    "email": this.operation_temp.email
                }
            }).then(function () {
                Materialize.toast("Operazione cancellata", 2000);
                reloadView();
            }, function () {
                Materialize.toast("SERVER ERROR", 2000);
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