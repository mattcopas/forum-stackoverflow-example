var app = angular.module('app', [
    'ngRoute',
    'angular-logger',
    'ngSanitize',
    'angularTrix',
    'angularMoment'
]);

var routes = {

    '/login': {
        templateUrl: '/angular/app/login/login.html',
        controller: 'loginController',
        requiresNotLoggedIn: true
    },

    '/register': {
        templateUrl: '/angular/app/register/register.html',
        controller: 'registrationController',
        requiresNotLoggedIn: true
    },

    '/logout': {
        controller: 'logoutController',
        templateUrl: '/angular/app/logout/logout.html',
        requiresLogin: true
    },

    '/': {
        templateUrl: '/angular/app/home/home.html',
        controller: 'homeController'
    },

    '/ask': {
        templateUrl: '/angular/app/question/ask.html',
        controller: 'askQuestionController',
        requiresLogin: true
    },

    '/question/:id': {
        templateUrl: 'angular/app/question/question.html',
        controller: 'questionController'
    }
};

app.config(function($routeProvider, $locationProvider) {

    $locationProvider.hashPrefix('');

    for(var path in routes) {
        $routeProvider.when(path, routes[path]);
    }

});

app.run(function($rootScope, $location, flashMessageService) {

    $rootScope.applicationTitle = 'QAC Forum';


    $rootScope.resetFlashMessages = function() {
        flashMessageService.resetFlashMessages();
    };

    flashMessageService.resetFlashMessages();

    $rootScope.user = {
        displayName: sessionStorage.getItem('user_displayName'),
        loggedIn: sessionStorage.getItem('user_loggedIn')   
    };

    $rootScope.$on('$locationChangeStart', function(event, next, current) {
       for(var i in routes) {
           if(next.indexOf(i) != -1) {
               if(routes[i].requiresLogin && !$rootScope.user.loggedIn) {
                   $location.path('/login');
                   event.preventDefault();
               }
               if(routes[i].requiresNotLoggedIn && $rootScope.user.loggedIn) {
                   $location.path('/');
                   event.preventDefault();
               }
           }
       }
    });


});