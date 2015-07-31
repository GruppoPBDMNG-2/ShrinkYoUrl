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
        templateUrl: 'views/home.html'
    }).when('/create', {
        templateUrl: 'views/add_short_url.html',
        controller: 'addCtrl'
    }).when('/search', {
        templateUrl: 'views/search.html',
        controller: 'searchCtrl'
    }).when('/visit', {
        templateUrl: 'views/visit.html',
        controller: 'visitCtrl'
    }).when('/stats', {
        templateUrl: 'views/stats.html',
        controller: 'statsCtrl'
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

   $scope.autoGenerate  = function() {
        $http.get('/autoGenerate/' + $scope.shortUrl.urlLong).success(function(data){
                        var str = data;
                        var res = str.substring(1, str.length-1);
                        $scope.shortUrl.urlShort = res;
                    }).error(function (data, status) {
                                          console.log('Error ' + data)
                                      })
   }

   $scope.addShortUrl = function(){
         var str = $scope.shortUrl.urlShort;
         var res = str.split('/').join('*');
         $http.get('/checkBadWords/' + res).success(function(data){
            if(data == "true"){
                $http.post('/addShortUrl', $scope.shortUrl).success(function (data) {
                                                $location.path('/');
                                                alert("ShortUrl creato");
                                            }).error(function (data, status) {
                                                console.log('Error ' + data)
                                            })
            }else{
                alert("L'url contiene una bad word");
            }

         }).error(function(data, status){
            console.log('Error ' + data)
         })

   }




});

app.controller('searchCtrl', function($scope, $http){
    $scope.bool = false;
    $scope.searchShortUrl = function(){
            var str = $scope.urlShortSearch;
             var res = str.split('/').join('*');
            $http.get('/searchUrl/' + res).success(function(data){
                $scope.data = data;
                $scope.bool = true;

                $scope.cities =  '' + data.citiesClicks;
                $scope.arrString = new Array();
                $scope.arrString = $scope.cities.split(',');

                $scope.countries = '' + data.countriesClicks;
                $scope.arrStringCountries = new Array();
                $scope.arrStringCountries = $scope.countries.split(',');
                $scope.continents = '' + data.continentsClicks;
                $scope.arrStringCont = new Array();
                $scope.arrStringCont = $scope.continents.split(',');

            }).error(function (data, status) {
                                  alert("Nessun indirizzo trovato");
                                  $scope.bool = false;
                                  console.log('Error ' + data)
                              })

            $http.get('/statsUrl/' + res).success(function(data){
                $scope.stats = data;
            }).error(function (data, status) {
                console.log('Error ' + data)
            })
       }
});

app.controller('visitCtrl', function($scope, $http){
    $scope.visitShortUrl = function(){
        var str = $scope.urlToVisit;
        var res = str.split('/').join('*');

        $http.get('/searchUrl/' + res).success(function(data){
            $scope.data = data;
            $http.post('/visitUrl/' + res).success(function(data2){
                var str = data.urlLong;
                if (str.substr(0,7) == "http://"){
                    str = str.substr(7);
                }
                window.location.href = "http://" + str;


            })
        }).error(function (data,status) {
                          alert("ShortURL non valido");
                          console.log('Error ' + data)
                      })
    }
});

app.controller('statsCtrl', function($scope, $http){
    var cont = new String("all");
    $http.get('/statsGlobal/getUrls/' + cont).success(function(data){
        $scope.urls = data;
        $http.get('/statsGlobal/getCounts/' + cont).success(function(data){
            $scope.counts = data;
        })
    }).error(function(data, status){
        console.log('Error ' + data);
    })

    var contAmer = new String("America");
    $http.get('/statsGlobal/getUrls/' + contAmer).success(function(data){
            $scope.urlsAmer = data;
            $http.get('/statsGlobal/getCounts/' + contAmer).success(function(data){
                $scope.countsAmer = data;
            })
        }).error(function(data, status){
            console.log('Error ' + data);
        })

    var contAsia = new String("Asia");
        $http.get('/statsGlobal/getUrls/' + contAsia).success(function(data){
            $scope.urlsAsia = data;
            $http.get('/statsGlobal/getCounts/' + contAsia).success(function(data){
                $scope.countsAsia = data;
            })
        }).error(function(data, status){
            console.log('Error ' + data);
        })

    var contEur = new String("Europe");
        $http.get('/statsGlobal/getUrls/' + contEur).success(function(data){
            $scope.urlsEur = data;
            $http.get('/statsGlobal/getCounts/' + contEur).success(function(data){
                $scope.countsEur = data;
            })
        }).error(function(data, status){
            console.log('Error ' + data);
        })

    var contAfr = new String("Africa");
        $http.get('/statsGlobal/getUrls/' + contAfr).success(function(data){
            $scope.urlsAfrica = data;
            $http.get('/statsGlobal/getCounts/' + contAfr).success(function(data){
                $scope.countsAfrica = data;
            })
        }).error(function(data, status){
            console.log('Error ' + data);
        })

    var contOce = new String("Oceania");
        $http.get('/statsGlobal/getUrls/' + contOce).success(function(data){
                $scope.urlsOce = data;
                $http.get('/statsGlobal/getCounts/' + contOce).success(function(data){
                    $scope.countsOce = data;
                })
            }).error(function(data, status){
                console.log('Error ' + data);
            })

});