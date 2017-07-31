app.service('answerService', ['$http', 'ENV', function($http, ENV) {

    this.postAnswer = function(answer) {
        return $http({
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem('access_token')
            },
            data: answer,
            url: ENV.API_URL + "answer/add"
        })
    };

    this.getAnswersByQuestionId = function(questionId) {
        return $http({
            method: 'GET',
            url: ENV.API_URL + "/questions/" + questionId + "/answers?projection=answerWithUserAndComments"
        })
    };

    this.editAnswer = function(answer) {
        return $http({
            method: 'PATCH',
            headers: {
                'Authorization': 'Bearer' + sessionStorage.getItem('access_token')
            },
            data: {
                answerId: answer.id,
                userId: sessionStorage.getItem('user_id'),
                body: answer.body
            },
            url: ENV.API_URL + "/answer/edit"
        })
    }

}]);