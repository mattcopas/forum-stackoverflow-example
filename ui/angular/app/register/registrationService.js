app.service('registrationService', ['$http', 'ENV', function($http, ENV) {
    
    this.register = function(email, displayName, password) {
        return $http({
            method: 'POST',
            data: {
                username: email,
                password: password, // TODO BCrypt
                displayName: displayName
            },
            url: ENV.API_URL + "user"
        })
    }
    
}]);