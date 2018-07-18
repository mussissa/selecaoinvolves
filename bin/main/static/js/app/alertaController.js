    'use strict';

    myApp.controller('alertaCtr',['$http','$scope', function($http, $scope){
        $scope.alertas = {};
        $scope.items = {};
        $scope.filtroTipo = [];
        $scope.filtroPonto = [];
        
        // pega os dados do back-end para listar. 
        $http.get('/alertas')
        .then(function(response){
            $scope.alertas = response.data;

            angular.forEach($scope.alertas, function(value){
                if($scope.filtroTipo.length == 0){
                    $scope.filtroTipo.push(value.flTipo);
                }else
                    if(($scope.filtroTipo).indexOf(value.flTipo) == -1){
                        $scope.filtroTipo.push(value.flTipo);
                }
            })

            angular.forEach($scope.alertas, function(value){
                if($scope.filtroPonto.length == 0){
                    $scope.filtroPonto.push(value.pontoDeVenda);
                }else
                    if(($scope.filtroPonto).indexOf(value.pontoDeVenda) == -1){
                        $scope.filtroPonto.push(value.pontoDeVenda);
                }
            });
        })
       
        //carregar os para o modal 
        $scope.detalhes =function(data) { 
            $scope.items = data;
            $('#btn-mensagem').on('click',function () {
                var idModal = $(this).data("#myModal")
                $(idModal).modal('show');
            }
         ) }
     

    }]);

