var app = angular.module('clicktoprofit');
app.controller('adminCampaignsController', ['$scope', '$cookieStore', '$state', '$http',
    function ($scope, $cookieStore, $state, $http) {

        if ($cookieStore.get("usr_is_admin")) {
            $scope.page.parentproperty = "Campagne";
            $scope.a = {
                "add": false
            };

            $http.get('/api/v1/getAllCampaigns').success(function (data) {
                $scope.campaigns = data;
            });

            $scope.predicate = 'deadline';
            $scope.reverse = false;
            $scope.order = function(predicate) {
                $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
                $scope.predicate = predicate;
            };

            $scope.confirm_function = function () {
                if ($scope.a.company && $scope.a.type && $scope.a.notes
                    && $scope.a.link && $scope.a.profit && document.getElementById("a.date").value != '') {
                    console.log(document.getElementById("a.date").value);
                    $http.get('/api/v1/addCampaign', {
                        params: {
                            "company": $scope.a.company,
                            "type": $scope.a.type,
                            "notes": $scope.a.notes,
                            "link": $scope.a.link,
                            "profit": $scope.a.profit,
                            "deadline": document.getElementById("a.date").value
                        }
                    }).then(function () {
                        Materialize.toast("Campagna aggiunta correttamente", 3000);
                        reloadView();
                    }, function () {
                        Materialize.toast("SERVER ERROR", 3000);
                    });
                } else {
                    Materialize.toast("Compila tutti i campi", 3000);
                }
            }

            function reloadView() {
                $state.reload();
            }

        } else {
            $scope.panel.show = false;
            $state.go('404');
        }

    }]);