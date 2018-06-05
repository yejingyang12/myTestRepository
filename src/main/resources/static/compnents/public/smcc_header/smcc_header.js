/**
 * Created by timha on 2018/5/18.
 */
(function () {
    var data = {};
    Vue.component('smccHeader',function (resolve, reject) {
        $.get(comp_src+'/compnents/public/smcc_header/smcc_header.html').then(function (res) {
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