/**
 * Created by timha on 2018/5/21.
 */
(function () {
    var data = {
    		memShow:false
    };
    Vue.component('addReList',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addReList/addReList.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                	//获取单位信息
                	getGradingSuccessMethod:function(_self,responseData){
                		if(responseData.data.fkSpRanklevel != null && responseData.data.fkSpRanklevel > 302){
                			this.memShow = true;
                		}
                  },
                },
                created: function() {
//                	  var _self = this ; 
//                    ajaxMethod(_self, 'post',
//                        'grading/queryDetailsGrading', true,'{"fkSystemId":"'+systemId+'"}', 'json',
//                        'application/json;charset=UTF-8',_self.getGradingSuccessMethod);
                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())