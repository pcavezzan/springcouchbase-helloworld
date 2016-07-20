angular.module('main', ['ngRoute'])
	.config(function($routeProvider, $httpProvider){
		$routeProvider.when('/', {
			templateUrl: 'home.html',
			controller: 'home',
			controllerAs: 'controller'
		}).when('/login',{
			templateUrl: 'login.html',
			controller: 'navigation',
			controllerAs: 'controller'
		}).when('/person/:username',{
			templateUrl: 'person.html',
			controller: 'person',
			controllerAs: 'controller'
		}).when('/person',{
			templateUrl: 'person.html',
			controller: 'person',
			controllerAs: 'controller'
		})
		.otherwise('/');
		
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	})
	.controller('home', function($http){
		var self = this;
		$http.get('/api/person/list').then(function(response){
			self.persons = response.data;
		})
	})
	.controller('person', function($http, $routeParams) {
		var self = this;
		
		if ($routeParams.username != null) {
			$http.get('/api/person/' + $routeParams.username).then(function(response) {
				self.person = response.data;
			})
		}
		
		
		self.submit = function() {
			
			if (self.person.id == null) {
				$http.post('/api/person', $.param(self.person), {
					headers : {
						"content-type" : "application/x-www-form-urlencoded"
					}
				}).then(function() {
					
				});
			} else {
				$http.put('/api/person/' + self.person.id, JSON.stringify(self.person), {
					headers : {
						"content-type" : "application/json"
					}
				}).then(function() {
					
				});
			}
		};
	})
	.controller('navigation', function($rootScope, $http, $location){
		var self = this;
		
		self.tab = function($route) {
			return $route.current && route === $route.current.controller;
		};
		
		var authenticate = function(callback) {

			$http.get('user').then(function(response) {
				if (response.data.name) {
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback();
			}, function() {
				$rootScope.authenticated = false;
				callback && callback();
			});

		}
		
		authenticate();
		
		self.credentials = {};
		
		self.login = function() {
			$http.post('login', $.param(self.credentials), {
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}
			}).then(function() {
				authenticate(function() {
					if ($rootScope.authenticated) {
						console.log("Login succeeded")
						$location.path("/");
						self.error = false;
						$rootScope.authenticated = true;
					} else {
						console.log("Login failed with redirect")
						$location.path("/login");
						self.error = true;
						$rootScope.authenticated = false;
					}
				});
			}, function() {
				console.log("Login failed")
				$location.path("/login");
				self.error = true;
				$rootScope.authenticated = false;
			})
		};
		
		self.logout = function() {
			$http.post('logout', {}).finally(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			});
		}
	});