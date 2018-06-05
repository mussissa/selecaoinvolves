    'use strict';

    myApp.controller('alertaCtr',['$http','$scope', function($http, $scope){
        $scope.alertas = {};
        $http.get('/alertas')
        .then(function(response){
            $scope.alertas = response.data;
            
        })


      $scope.detalhes = function(data){
         $scope.items = data;
 
            $("#btn-mensagem").click(function(){
               $("#myModal").modal();
            })
        }
        
      
     

    }]);

