var app = angular.module('clicktoprofit');
app.controller('passwordRecoveryController', ['$cookieStore', '$state', '$scope', '$http', function ($cookieStore, $state, $scope, $http) {

    document.getElementById('submit').onclick = function() {
        $http.get('api/v1/userPasswordRecovery', {
            params: {
                "email" : $scope.email
            }
        }).then(function() {
            Materialize.toast("Email inviata.", 2000);
            $state.go("login");
        }, function() {
            Materialize.toast("SERVER ERROR", 2000);
        });
    }

}]);