app.controller('logoutController', [
    '$rootScope',
    '$location',
    'flashMessageService',
    function($rootScope, $location, flashMessageService) {

    var logout = function() {
        $rootScope.user = {};
        sessionStorage.clear();
        flashMessageService.flashMessageSuccess ('You have been logged out');
        $location.path('/');
    };

    logout();

}]);