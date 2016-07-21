angular.module('main', ['ngRoute','angular-growl'])
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
	.config(['growlProvider', function (growlProvider) {
	  growlProvider.globalTimeToLive(3000);
	}])
	.controller('home', function($http, flash, growl) {
		var self = this;
		self.persons = [];
		
		(function init() {
			if (flash != null && flash.getMessage() != "") {
				growl.success(flash.getMessage(), this.config);
			}
				
			$http.get('/api/person/list').then(function(response) {
			  self.persons = response.data;
			});
	     })();
		
		self.delete = function(person) {
			$http.delete('/api/person/' + person.id);
		    var index = self.persons.indexOf(person);
		    self.persons.splice(index, 1);
		}
	 })
	.controller('person', function($http, $routeParams, $location, flash) {
		var self = this;
		
		(function init() {
			if ($routeParams.username != null) {
				$http.get('/api/person/' + $routeParams.username).then(function(response) {
					self.person = response.data;
				})
			}
	    })();
				
		self.submitMsgSuccess = function(msg) {
			self.msg = null;
			flash.setMessage(msg);
			$location.path('/');
		};
		
		self.submitMsgFailure = function() {
			self.msg = "Saving has failed.";
		};
		
		self.submit = function() {
			
			if (self.person.id == null) {
				$http.post('/api/person', $.param(self.person), {
					headers : {
						"content-type" : "application/x-www-form-urlencoded"
					}
				}).then(this.submitMsgSuccess('New person saved')).catch(this.submitMsgFailure());
			} else {
				$http.put('/api/person/' + self.person.id, JSON.stringify(self.person), {
					headers : {
						"content-type" : "application/json"
					}
				}).then(this.submitMsgSuccess('Person updated.')).catch(this.submitMsgFailure());
			}
		};
	})
	.controller('navigation', function($rootScope, $http, $location){
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
		
		var self = this;
		self.credentials = {};
		
		(function init() {
			authenticate();
	    })();

		self.tab = function($route) {
			return $route.current && route === $route.current.controller;
		};
		
		self.login = function() {
			$http.post('login', $.param(self.credentials), {
				headers : {
					"content-type" : "application/x-www-form-urlencoded"
				}
			}).then(function() {
				authenticate(function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						self.error = false;
						$rootScope.authenticated = true;
					} else {
						$location.path("/login");
						self.error = true;
						$rootScope.authenticated = false;
					}
				});
			}, function() {
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
	})
	.factory("flash", function($rootScope) {
	  var queue = [];
	  var currentMessage = "";
	
	  $rootScope.$on("$routeChangeStart", function() {
	    currentMessage = queue.shift() || "";
	  });
	
	  return {
	    setMessage: function(message) {
	    	console.log(message);
	      queue.push(message);
	    },
	    getMessage: function() {
	      return currentMessage;
	    }
	  };
	});