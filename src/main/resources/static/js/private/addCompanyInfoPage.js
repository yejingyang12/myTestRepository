/**
 * Created by timha on 2018/5/23.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods : {
          submitHandler:function(formName){
            bus.$emit('formName',formName);
          },
          submitHandlerSuccessMethod: function(_self,returnData){
            window.location.href = originUrl+"page/addCompanySystemPage?companyId=" + returnData.data+"&companyCode="+data.formData.companyCode;
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('addCompany',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
            }
          });
        }
    })
}