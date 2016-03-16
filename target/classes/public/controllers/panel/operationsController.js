var app = angular.module('clicktoprofit');
app.controller('operationsController', ['$cookieStore', '$state', '$scope', '$http',
    function ($cookieStore, $state, $scope, $http) {

        $scope.page.parentproperty = "Cronologia operazioni effettuate";
        $scope.a = {
            add: false
        };

        $http.get('/api/v1/getUserOperations', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
            $scope.operations = data;
        });

        $http.get('/api/v1/getCampaigns').success(function (data) {
            $scope.campaigns = data;
        });

        $scope.predicate = 'date';
        $scope.reverse = true;
        $scope.order = function(predicate) {
            $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
            $scope.predicate = predicate;
        };

        $scope.a.datePop = function () {
            var myField = document.getElementById("a.datetime");
            var now = new Date();
            console.log(now.getTime());
            myField.valueAsNumber = now.getTime() - now.getTimezoneOffset() * 60000;
        }

        $scope.confirm_function = function () {
            if ($scope.a.selectedCampaign) {
                $http.get('api/v1/addOperation', {
                    params: {
                        "id_user": $cookieStore.get("usr_id"),
                        "id_campaign": $scope.a.selectedCampaign.id_campaign,
                        "profit": $scope.a.selectedCampaign.profit,
                        "date": document.getElementById("a.datetime").value.substring(0, 10),
                        "time": document.getElementById("a.datetime").value.substring(11, 16)
                    }
                }).then(function () {
                    Materialize.toast("Richiesta inviata. Verrai contattato via email appena possibile: un admin la analizzerà.", 5000);
                    reloadView();
                }, function () {
                    Materialize.toast("Non puoi aggiungere un'operazione già effettuata!", 3000);
                });
            }
        }

        function reloadView() {
            $state.reload();
        }

    }]);