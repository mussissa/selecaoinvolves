'use strict'
    var myApp = angular.module('myApp', ['ngRoute','ngResource']);

    myApp.config(['$routeProvider', '$locationProvider',function($routeProvider, $locationProvider){
    
        $routeProvider
        .when("/",{
              templateUrl: '/views/Alertas1.html',
              controller: 'alertaCtr'
        })
        .when("/DetalheAlerta/:id",{
            templateUrl: '/views/DetalheAlerta.html',
            controller:  'alertaCtr'
        })

    }]);        
