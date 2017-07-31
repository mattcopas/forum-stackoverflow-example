app.controller('askQuestionController', [
    '$scope',
    'questionService',
    '$location',
    '$rootScope',
    'flashMessageService',
    function($scope, questionService, $location, $rootScope, flashMessageService) {

    $scope.question = {
        title: '',
        body: '',
        userId: sessionStorage.getItem("user_id")
    };

    $scope.submitQuestion = function() {

        questionService.postQuestion($scope.question).then(function success(response) {
            console.log("Question sent");
            console.log(response.data);
            flashMessageService.flashMessageSuccess('Your question has been posted');
            $location.path('/question/' + response.data.id );
        }, function error(response) {
            flashMessageService.flashMessageError('There was an error when posting your question');
            console.log("Error: ", response.data);
        })

    };

}]);