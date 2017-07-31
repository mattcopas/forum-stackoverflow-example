app.service('flashMessageService', ['$rootScope', function($rootScope) {
    
    this.flashMessageSuccess = function(message) {
        this.resetFlashMessages();
        $rootScope.flashMessageSuccess = message;
    };

    this.flashMessageError = function(message) {
        this.resetFlashMessages();
        $rootScope.flashMessageError = message;
    };
    
    this.resetFlashMessages = function() {
        $rootScope.flashMessageSuccess = '';
        $rootScope.flashMessageError = '';
    };
    
}]);