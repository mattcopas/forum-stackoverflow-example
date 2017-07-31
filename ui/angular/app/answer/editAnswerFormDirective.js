app.directive('editAnswerForm', function() {

    var editAnswerController = ['$scope', 'flashMessageService', 'answerService', '$log', function($scope, flashMessageService, answerService, $log) {

        var logger = $log.getInstance('editAnswerFormDirective');

        $scope.editAnswer = function() {
            logger.info("Edited answer to send to service ", $scope.answer);
            // answerService.editAnswer($scope.answer).then(function success(response) {
            //     flashMessageService.flashMessageSuccess("Your answer has been edited");
            // }, function error(response) {
            //     flashMessageService.flashMessageError("Error editing answer");
            //     logger.error("Error editing answer: ", response);
            // })
        };
    }];

    return {
        // restrict: 'E',
        templateUrl: '/angular/app/answer/editAnswer.html',
        controller: editAnswerController,
        scope: {
            answer: '='
        }
    }
});