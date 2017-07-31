app.service('questionService', ['$http', 'ENV', function($http, ENV) {

    this.getQuestions = function() {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/questions?projection=withUserAndNumberOfAnswers'
        });
    };

    this.getQuestionById = function(id) {
        return $http({
            method: 'GET',
            url: ENV.API_URL + 'questions/' + id + '?projection=questionWithComments'
        })
    };

    this.getQuestionAuthor = function(questionId) {
        return $http({
            method: 'GET',
            url: ENV.API_URL + 'question/' + questionId + '/author'
        })
    };

    this.postQuestion = function(question) {
        return $http({
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem('access_token')
            },
            url: ENV.API_URL + 'question/ask',
            data: question
        })
    };

    this.updateViewedCount = function(questionId) {
        return $http({
            method: 'PATCH',
            url: ENV.API_URL + "question/" + questionId + "/updateViewedCount"
        })
    }

    this.editQuestion = function(question) {
        return $http({
            method: 'PATCH',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem('access_token')
            },
            url: ENV.API_URL + "/question/edit",
            data: {
                questionId: question.id,
                userId: sessionStorage.getItem('user_id'),
                editedTitle: question.title,
                editedBody: question.body
            }
        })
    }

}]);