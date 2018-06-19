/**
 * Created by timha on 2018/5/29.
 */
(function () {
    var data={};
    Vue.component('viewDetailsNav',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/viewDetailsNav/viewDetailsNav.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return {
                        activeName: 'information'
                    }
                },
                methods:{
                    handleClick(tab, event) {
                        console.log(tab, event);
                    },
                    recordFn:function () {
                        $('.wrap').removeClass('cover');
                    },
                    toIndexPage : function(){
                    	window.location.href="/page/indexPage";
                    },
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