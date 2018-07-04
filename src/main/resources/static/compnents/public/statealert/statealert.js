var emitdata = new Vue();

(function () {
    var data = {};
    Vue.component('statealert',function (resolve, reject) {
        $.get(comp_src+'/compnents/public/statealert/statealert.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{

                },
                created: function() {

                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())