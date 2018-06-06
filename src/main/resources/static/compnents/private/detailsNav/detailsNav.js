/**
 * Created by timha on 2018/5/29.
 */
(function () {
    var data={};
    Vue.component('detailsNav',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/detailsNav/detailsNav.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return {
                        activeName: 'record',

                    }
                },
                methods:{
                    handleClick(tab, event) {
                        console.log(tab, event);
                    },
                    recordFn:function () {
                        $('.wrap').removeClass('cover');
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