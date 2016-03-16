var app = angular.module('clicktoprofit');
app.controller('adminStringsController', ['$scope', '$http', function ($scope, $http) {

    $scope.page.parentproperty = "Stringhe della home";

    $http.get('api/v1/getHomeStrings').then(function(data) {
        $scope.strings = data.data;
    });

    $scope.confirm_function = function() {
        $http.get('api/v1/updateHomeStrings', {
            params : {
                "header1": $scope.strings.header1,
                "header2": $scope.strings.header2,
                "chi_siamo": $scope.strings.chi_siamo,
                "faq1_question": $scope.strings.faq1_question,
                "faq1": $scope.strings.faq1,
                "faq2_question": $scope.strings.faq2_question,
                "faq2": $scope.strings.faq2,
                "faq3_question": $scope.strings.faq3_question,
                "faq3": $scope.strings.faq3,
                "faq4_question": $scope.strings.faq4_question,
                "faq4": $scope.strings.faq4,
                "payments": $scope.strings.payments,
                "contacts": $scope.strings.contacts
            }
        }).then(function() {
            Materialize.toast("Stringhe modificate correttamente.", 3000);
        }, function() {
            Materialize.toast("SERVER ERROR", 3000);
        })
    }

}]);