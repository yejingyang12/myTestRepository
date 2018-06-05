/**
 * Created by timha on 2018/5/21.
 */
(function () {
    var data = {};
    Vue.component('addList',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addList/addList.html').then(function (res) {
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