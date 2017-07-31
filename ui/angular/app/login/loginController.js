app.controller('loginController', [
    '$rootScope',
    '$scope',
    'loginService',
    '$location',
    '$routeParams',
    'flashMessageService',
    function($rootScope, $scope, loginService, $location, $routeParams, flashMessageService) {
    
    $scope.loginDetails = {
        email: '',
        password: ''
    };

    const REDIRECT_URL = $routeParams.redirectUrl;
        console.log("REDIRECT_URL: ", REDIRECT_URL);
    
    $scope.login = function() {
        loginService.login($scope.loginDetails.email, $scope.loginDetails.password).then(function success(response) {
            console.log("Login success: ", response);
            sessionStorage.setItem("access_token", response.data.access_token);
            
            loginService.getUserDetails($scope.loginDetails.email).then(function success(response) {
                sessionStorage.setItem("user_id", response.data.id);
                sessionStorage.setItem("user_displayName", response.data.displayName);
                sessionStorage.setItem("user_loggedIn", true);
                $rootScope.user = {
                    displayName: response.data.displayName,
                    email: response.data.email,
                    loggedIn: true
                };
                flashMessageService.flashMessageSuccess('Welcome, ' + $rootScope.user.displayName);
                if(angular.isUndefined(REDIRECT_URL)) {
                    $location.path("/");
                } else {
                    $location.path(REDIRECT_URL);
                }
            }, function error(response) {
                flashMessageService.flashMessageError('Error getting user details');
            })
            
        }, function error(response) {
            console.log("Error: ", response);
            flashMessageService.flashMessageError('Error logging in');
        })
    };
    
}]);