/**
 * Created by timha on 2018/5/29.
 */
(function () {
    var data={
    		systemName:''
    };
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
                    handleClick: function(tab, event) {
                        console.log(tab, event);
                    },
                    recordFn:function () {
                        $('.wrap').removeClass('cover');
                    },
                    toIndexPage : function(){
                    	window.location.href="/page/indexPage";
                    },
                    // 获取系统信息
                    getSystem : function(_self) {
                    	ajaxMethod(this, 'post',
                        'system/querySystemInformationBySystemId', true,
                        '{"systemId":"'+systemId+'"}', 'json',
                        'application/json;charset=UTF-8',
                        this.getSystemSuccess);
                    } ,
                    getSystemSuccess : function(_self,result){
                    	this.systemName = result.data.systemName;
                    }
                },
                created: function() {
                	 //获取系统信息
                  this.getSystem(this);
                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())