/**
 * Created by timha on 2018/5/25.
 */
(function () {
    var data = {};
    Vue.component('addFormCha',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addFormCha/addFormCha.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                    getCover:function () {
                        $("#cover").removeClass('cover');
                    }
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