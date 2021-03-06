(function () {
    'use strict';

    angular.module('sicobaApp')
        .factory('Notas', ['$resource', function ($resource) {
            return $resource('api/notas/:id/:action', {id: '@id'},
                {
                    gerar: {
                        method: 'POST',
                        url: 'api/notas/gerar',
                        isArray: true
                    },
                    findItensByDateOfProvision: {
                        method: 'GET',
                        url: 'api/notas/itens/dateprovision',
                        isArray: true
                    },
                    removeAll: {
                        method: 'DELETE',
                        url: 'api/notas/all?:params',
                        params: {
                            params: '@params'
                        }
                    }
                });
        }]);
}());
