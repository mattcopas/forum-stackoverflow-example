app.service('validationService', [function() {

    this.validateRegistrationDetails = function(registrationDetails) {
        if( ! registrationDetails.email.toLowerCase().endsWith('@qa.com')) {
            return false;
        }
        if( ! registrationDetails.password === registrationDetails.confirmPassword) {
            return false;
        }
        
        // TODO Check email is not taken
        
        return true;
    }

}]);