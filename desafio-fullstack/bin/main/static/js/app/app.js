'use strict'
    var myApp = angular.module('myApp', ['ngRoute','ngResource']);

    myApp.config(['$routeProvider', function($routeProvider){
        
        $routeProvider
        .when("/",{
              templateUrl: '/views/Alertas1.html',
              controller: 'alertaCtr'
        })

    }]);        
