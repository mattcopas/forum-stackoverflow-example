app.directive('editQuestionForm', function() {

    var editQuestionController = ['$scope', 'flashMessageService', 'questionService', function($scope, flashMessageService, questionService) {

        $scope.editQuestion = function() {
            questionService.editQuestion($scope.question).then(function success(response) {
                flashMessageService.flashMessageSuccess("Your question has been edited");
                $scope.question.editMode = false;
            }, function error(response) {
                flashMessageService.flashMessageError("Error editing question");
                logger.info(response);
            });
        };

    }];

    return {
        // restrict: 'E',
        templateUrl: '/angular/app/question/editQuestionForm.html',
        controller: editQuestionController,
        scope: {
            question: '='
        }
    };


});