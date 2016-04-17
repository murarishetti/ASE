var myapp = angular.module("myApp", ['ngRoute'])

.config(function($routeProvider){
	$routeProvider
	
	.when('/', {
        templateUrl: 'templates/register.html',
        controller: 'RegistrationCtrl'
    })
    
    .when('/login', {
        templateUrl: 'templates/login.html',
        controller: 'LoginCtrl'
    })
    
    .when('/update', {
        templateUrl: 'templates/update.html',
        controller: 'UpdateCtrl'
    })
    
    .when('/delete', {
        templateUrl: 'templates/delete.html',
        controller: 'DeleteCtrl'
    })
})

.factory('user', function(){
    var data;
    function set(d) {
        data = d;
    }
    function get() {
        return data;
    }
    return {
        set:set,
        get:get
    }
})

.controller('RegistrationCtrl', function($scope, $http, $httpParamSerializerJQLike) {
    $scope.createAccount = function(username, password, roll) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/santhoshAssignment6/create/new',
            data: JSON.stringify({
                username: username,
                password: password,
                roll: roll
            }),
            contentType: "application/json"
        }).success(function() {
            $scope.statusbar = true;
            $scope.message = "Document has been created successfully!";
            $scope.username = "";
            $scope.password = "";
            $scope.roll = "";
        }).error(function(response) {
            console.log(response);
        })
    }
})

.controller('LoginCtrl', function($scope, $http, user, $location) {
    $scope.login = function(username, password) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/santhoshAssignment6/read/login',
            params: {username: username, password: password}
        }).success(function(data) {
            if(data.length==1) {
                console.log("Login Successful")
                var details = {username: username};
                user.set(details);
                $location.path("/update");
            } else {
                $scope.status = true;
                $scope.message = "Invalid Credentials";
            }
        })
    }
})

.controller('UpdateCtrl', function($scope, $http, user, $httpParamSerializerJQLike) {
    $scope.details = user.get();
    $scope.username = $scope.details.username;
    $scope.updateAccount = function(username, password) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/santhoshAssignment6/updateuser/password',
            data: $httpParamSerializerJQLike({username:$scope.details.username, password:password})
        }).success(function(){
            $scope.status = true;
            $scope.message = "Account updated successfully!";
        })
    }
})

.controller('DeleteCtrl', function($scope, $http, user) {
    $scope.details = user.get();
    $scope.username = $scope.details.username;
    
    $scope.deleteAccount = function() {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/santhoshAssignment6/deleteuser/account',
            params: {username: $scope.username}
        }).success(function() {
            $scope.statusbar = true;
            $scope.message = "Account deleted successfully!";
        })
    }
})

