var app = angular.module('clicktoprofit');
app.controller('panelController', ['$cookieStore', '$state', '$scope', '$http', function ($cookieStore, $state, $scope, $http) {

    var cookieJson = {
        "usr_id": $cookieStore.get("usr_id"),
        "usr_email": $cookieStore.get("usr_email"),
        "usr_pwd": $cookieStore.get("usr_pwd"),
        "usr_ka": $cookieStore.get("usr_ka"),
        "usr_is_admin": $cookieStore.get("usr_is_admin")
    }

    if (!checkCookie(cookieJson)) {
        $scope.panel = {
            "show": false
        };
        $state.go('login');
    } else {
        $scope.panel = {
            "show": true
        };
        $scope.page = {};
        $scope.page.parentproperty = "";
        $scope.user = {
            "isAdmin": $cookieStore.get("usr_is_admin")
        };

        $http.get('/api/v1/getUserData', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
            $scope.page.name = data['name'];
            $scope.page.surname = data['surname'];
        });

        $http.get('/api/v1/getUserCurrentBalance', {params: {"id_user": $cookieStore.get("usr_id")}}).success(function (data) {
            $scope.page.current_balance = data.substring(1, data.length - 1);
        });

        $scope.exit = function () {
            $cookieStore.remove("usr_pwd");
            $cookieStore.remove("usr_email");
            $cookieStore.remove("usr_id");
            $cookieStore.remove("usr_ka");
            $cookieStore.remove("usr_is_admin");
        }
    }

}]);