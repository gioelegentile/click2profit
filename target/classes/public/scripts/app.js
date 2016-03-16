var app = angular.module('clicktoprofit', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ngAnimate',
    'googlechart',
    'ui.router'
])

//app.config(["$locationProvider", function($locationProvider) {
//    $locationProvider.html5Mode(true);
//}]);

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/404');
    $urlRouterProvider.when('', '/home');

    $stateProvider
        .state('404', {
            url: "/404",
            templateUrl: "views/404.html"
        }).state('home', {
        url: "/home",
        templateUrl: "views/home.html",
        controller: "homeController"
    }).state('login', {
        url: "/login",
        templateUrl: "views/login.html",
        controller: "loginController"
    }).state('panel', {
        url: "/panel",
        templateUrl: "views/panel.html",
        controller: "panelController"
    }).state('panel.operations', {
        url: "/operations",
        templateUrl: "views/panel/operations.html",
        controller: "operationsController"
    }).state('panel.payments', {
        url: "/payments",
        templateUrl: "views/panel/payments.html",
        controller: "paymentsController"
    }).state('panel.payment_methods', {
        url: "/payment_methods",
        templateUrl: "views/panel/payment_methods.html",
        controller: "userPaymentMethodsController"
    }).state('panel.campaigns', {
        url: "/campaigns",
        templateUrl: "views/panel/campaigns.html",
        controller: "campaignsController"
    }).state('panel.admin_campaigns', {
        url: "/admin/campaigns",
        templateUrl: "views/panel/admin_campaigns.html",
        controller: "adminCampaignsController"
    }).state('panel.personal_data', {
        url: "/personal_data",
        templateUrl: "views/panel/personal_data.html",
        controller: "personalDataController"
    }).state('panel.change_password', {
        url: "/change_password",
        templateUrl: "views/panel/change_password.html",
        controller: "changePasswordController"
    }).state('panel.admin_admins', {
        url: "/admin/admins",
        templateUrl: "views/panel/admin_admins.html",
        controller: "adminAdminsController"
    }).state('panel.admin_operations', {
        url: "/admin/operations",
        templateUrl: "views/panel/admin_operations.html",
        controller: "adminOperationsController"
    }).state('panel.admin_operations_temp', {
        url: "/admin/operations_temp",
        templateUrl: "views/panel/admin_operations_temp.html",
        controller: "adminOperationsTempController"
    }).state('panel.admin_payment_methods', {
        url: "/admin/payment_methods",
        templateUrl: "views/panel/admin_payment_methods.html",
        controller: "adminPaymentMethodsController"
    }).state('panel.admin_payments', {
        url: "/admin/payments",
        templateUrl: "views/panel/admin_payments.html",
        controller: "adminPaymentsController"
        //}).state('panel.admin_payments', {
        //    url: "/admin/payments/{trigger}",
        //    views: {
        //        '': {
        //            templateUrl: "views/panel/admin_payments.html",
        //            controller: "adminPaymentsController"
        //        }
        //    }
    }).state('panel.admin_users', {
        url: "/admin/users",
        templateUrl: "views/panel/admin_users.html",
        controller: "adminUsersController"
    }).state('password_recovery', {
        url: "/password_recovery",
        templateUrl: "views/password_recovery.html",
        controller: "passwordRecoveryController"
    }).state('email_form', {
        url: "/email_form",
        templateUrl: "views/email_form.html",
        controller: "emailFormController"
    }).state('panel.admin_strings', {
        url: "/admin/strings",
        templateUrl: "views/panel/admin_strings.html",
        controller: "adminStringsController"
    }).state('panel.admin_payments_temp', {
        url: "/admin/payments_temp",
        templateUrl: "views/panel/admin_payments_temp.html",
        controller: "adminPaymentsTempController"
    })

});