var app = angular.module('clicktoprofit');
app.controller('emailFormController', ['$state','$cookieStore', '$scope', '$http', function ($state, $cookieStore, $scope, $http) {

    document.getElementById('submit').onclick = function() {
        if ($scope.email && $scope.object && $scope.text) {
            $http.get('api/v1/sendEmailForm', {
                params: {
                    "email" : $scope.email,
                    "object" : $scope.object,
                    "text" : $scope.text
                }
            }).then(function() {
                Materialize.toast('Email inviata.', 2000);
                $state.go('home');
            }, function() {
                Materialize.toast('SERVER ERROR', 2000);
            });
        } else {
            Materialize.toast("Compila tutti i campi.", 2000);
        }
    }

}]);