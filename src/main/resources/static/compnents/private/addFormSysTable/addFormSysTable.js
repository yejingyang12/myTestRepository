/**
 * Created by timha on 2018/6/1.
 */
(function () {
    var data={};
    Vue.component('addFormSysTable',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormSysTable/addFormSysTable.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                    addNum:function (e) {
                        var n = parseInt(e.target.previousElementSibling.value);
                        if( n == 100){
                            e.target.previousElementSibling.value = 100;
                        }else{
                            e.target.previousElementSibling.value++;
                        }
                    },
                    oddNum:function (e) {
                        var n = parseInt(e.target.previousElementSibling.previousElementSibling.value);
                        if(n == 0){
                            e.target.previousElementSibling.previousElementSibling.value = 0;
                        }else{
                            e.target.previousElementSibling.previousElementSibling.value--;
                        }
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