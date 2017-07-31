app.controller('registrationController', ['$scope', 'registrationService', 'validationService', function($scope, registrationService, validationService) {
    
    $scope.registrationDetails = {
        email: '',
        displayName: '',
        password: '',
        confirmPassword: ''
    };
    
    $scope.register = function() {
        console.log("Validating details");
        if(validationService.validateRegistrationDetails($scope.registrationDetails)) {
            console.log("Validation passed, posting")
            registrationService.register(
                $scope.registrationDetails.email, 
                $scope.registrationDetails.displayName,
                $scope.registrationDetails.password)
            .then(function success(response) {
                console.log("Success", response);
            }, function error(response) {
                console.log("Error: ", response);
            })
        } else {
            
        }
    }
    
}]);