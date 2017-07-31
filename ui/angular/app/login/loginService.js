app.service('loginService', ['$http', 'ENV', function($http, ENV) {
    
    this.login = function(email, password) {
      return $http({
          method: 'POST',
          headers: {
              'Authorization': ENV.API_AUTHORIZATION_HEADER
          },
          url: ENV.API_URL + "oauth/token" + "?grant_type=password&username=" + email + "&password=" + password
      });  
    };
    
    this.getUserDetails = function(email) {
        return $http({
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem("access_token")
            },
            url: ENV.API_URL + "user?email=" + email
        })
    }
    
}]);