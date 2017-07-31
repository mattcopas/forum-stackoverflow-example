app.controller('questionController', [
    '$scope',
    '$rootScope',
    '$routeParams',
    'questionService',
    '$log',
    'answerService',
    'flashMessageService',
    function ($scope, $rootScope, $routeParams, questionService, $log, answerService, flashMessageService) {

    const logger = $log.getInstance('questionController');

    const QUESTION_ID = $routeParams.id;

    $scope.submitAnswer = function() {
        answerService.postAnswer($scope.newAnswer).then(function success(response) {
            logger.info("Answer submitted: ", response);
            getAnswerByQuestionId(QUESTION_ID);
            $scope.newAnswer.body = '';
            flashMessageService.flashMessageSuccess('Answer submitted');

        }, function error(response) {
            logger.error("Answer error ", response);
            flashMessageService.flashMessageError('Error submitting answer');
        })
    };

    var main = function() {
        getQuestionById(QUESTION_ID);
        getQuestionAuthor(QUESTION_ID);

        $scope.newAnswer = {
            body: '',
            userId: sessionStorage.getItem('user_id'),
            questionId: QUESTION_ID
        }

    };

    var getQuestionById = function(questionId) {
        questionService.getQuestionById(questionId).then(function success(response) {
            $scope.question = response.data;
            $scope.question.redirectUrl = '/question/' + QUESTION_ID;
            logger.info("Question data returned: ", response.data);
            $scope.question.isEditable = $rootScope.user.loggedIn && ($scope.question.userViewDTO.id == sessionStorage.getItem('user_id'));
            $scope.question.editMode = false;
            getAnswerByQuestionId(QUESTION_ID);

            questionService.updateViewedCount(questionId).then(function success(response) {
                logger.info("Viewed Count updated");
            }, function error(response) {
                logger.error("Error", response);
            })
        }, function error(response) {
            logger.info("Error from server: ", response.data);
        });
    };

    var getAnswerByQuestionId = function(questionId) {
        answerService.getAnswersByQuestionId(questionId).then(function success(response) {
            logger.info("Response for getting answers: ", response);
            $scope.question.answers = response.data._embedded.answers;
            logger.info("$scope.question.answers: ", $scope.question.answers);
            angular.forEach($scope.question.answers, function(answer, value) {
                answer.isEditable = $rootScope.user.loggedIn && (answer.userViewDTO.id == sessionStorage.getItem('user_id'));
                answer.editMode = false;
            });
        }, function error(response) {
            logger.error("Error getting answers: ", response);
        });
    };

    var getQuestionAuthor = function(questionId) {
        questionService.getQuestionAuthor(questionId).then(function success(response) {
            logger.info("Question Author Data: ", response.data);
            $scope.questionAuthor = response.data;

        }, function error(response) {
            logger.error("Error getting Question Author Data", response.data);
        });
    };

    $scope.editQuestion = function() {
        questionService.editQuestion($scope.question).then(function success(response) {
            flashMessageService.flashMessageSuccess("Your question has been edited");
            $scope.question.editMode = false;
        }, function error(response) {
            flashMessageService.flashMessageError("Error editing question");
            logger.info(response);
        });
    };

    main();

}]);