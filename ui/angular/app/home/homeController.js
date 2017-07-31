app.controller('homeController', ['$scope', 'questionService', '$log', function($scope, questionService, $log) {

    var logger = $log.getInstance('homeController');

    $scope.questions = [];
    $scope.abbreviatedQuestions = [];
    $scope.sortAscending = true;
    $scope.sort = {
        propertyName: 'createdAt',
        ascending: true
    };


    $scope.questions = questionService.getQuestions().then(function success(response) {
        logger.info("Returned questions data: ", response.data);
        $scope.questions = response.data._embedded.questions;
        logger.info("$scope.questions: ", $scope.questions);
        logger.info("Is $scope,questions an array? ", angular.isArray($scope.questions));
        $scope.abbreviateQuestions($scope.questions);
    }, function error(response) {
        logger.error("Error getting questions: ", response.data);
        $scope.error = response.data;
    });

    $scope.abbreviateQuestions = function(questions) {
        angular.forEach(questions, function(question, key) {
            var questionBodyWithNoHtml = String(question.body).replace(/<[^>]+>/gm, '')
            if(questionBodyWithNoHtml.length > 200) {
                question.body =  questionBodyWithNoHtml.substr(0, 200) + '...';
            } else {
                question.body =  questionBodyWithNoHtml;
            }
            $scope.abbreviatedQuestions.push(question);
        })
    };

    $scope.sortQuestions = function(sortPropertyName) {
        logger.info("Sorting by ", sortPropertyName);
        $scope.sort.properyName = sortPropertyName;
        $scope.sort.ascending = !$scope.sort.ascending;
    };

    $scope.showCaretDown = function(sortPropertyName) {
        return $scope.sort.propertyName === sortPropertyName && !$scope.sort.ascending;
    };

    $scope.showCaretUp = function(sortPropertyName) {
        return $scope.sort.propertyName === sortPropertyName && $scope.sort.ascending;
    };

    $scope.showAndHideCaretIcon = function(sortPropertyName) {
        return ($scope.sort.propertyName === sortPropertyName && $scope.sort.ascending);
    };

    $scope.selectedSort = function(sortPropertyName) {
        return sortPropertyName == $scope.sort.propertyName;
    };


}]);