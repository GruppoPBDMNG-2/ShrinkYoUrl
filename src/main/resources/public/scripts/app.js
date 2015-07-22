/**
 * Created by Roger on 20/07/15.
 */

var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/add_short_url.html',
        controller: 'addCtrl'
    }).when('/stats', {
              templateUrl: 'views/stats.html',
              controller: 'statsCtrl'
    }).when('/search', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };

    $scope.createTodo = function () {
        console.log($scope.todo);
        $http.post('/api/v1/todos', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('addCtrl', function($scope, $http, $location) {

    $scope.shortUrl = {
        continentsClicks: [],
        countriesClicks: [],
        citiesClicks: []
   }

   $scope.addShortUrl = function(){
         $http.post('/addShortUrl', $scope.shortUrl).success(function (data) {
                    $location.path('/');
                }).error(function (data, status) {
                    console.log('Error ' + data)
                })
   }
});

app.controller('statsCtrl', function($scope, $http, $location) {

    $scope.shortUrl = {
        continentsClicks: [],
        countriesClicks: [],
        citiesClicks: []
   }

   $scope.addShortUrl = function(){
         $http.post('/addShortUrl', $scope.shortUrl).success(function (data) {
                    $location.path('/');
                }).error(function (data, status) {
                    console.log('Error ' + data)
                })
   }
});