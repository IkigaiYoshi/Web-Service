var serviceApi = Vue.resource('/service');

Vue.component('data-row', {
    props: ['data'],
    template:
        '<tr>' +
        '<th><i>{{data.id}}</i></th>' +
        '<th>{{data.temperature}}</th>' +
        '<th>{{data.coordinate}}</th>' +
        '</tr>'
});


Vue.component('dataFromService-list', {
    props: ['dataFromService'],
    template:
        '<tbody><data-row v-for="data in dataFromService" :key="data.id" :data="data" /></tbody>',
    created: function () {
        serviceApi.get().then(result =>
            result.json().then(data =>
                data.forEach(data => this.dataFromService.push(data))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<div class="container"><div class="col-sm-12 text-center">' +
        '<table class="table table-bordered"><thead><tr class="active"><th>Id</th><th>Temperature</th><th>Coordinate</th></tr></thead>' +
        '<dataFromService-list :dataFromService="dataFromService"/></table>' +
        '</div></div>',
    data: {
        dataFromService: []
    }
});