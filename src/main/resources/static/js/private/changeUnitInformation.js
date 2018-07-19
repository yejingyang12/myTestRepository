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
          saveCompanyInfo:function(formName){
            data.check = false;
            bus.$emit('formName',formName);
          },
          submitHandlerSuccessMethod: function(_self,returnData){
            window.location.href = originUrl+"page/mainCompanyInfoPage?companyId=" + returnData.data+"&companyCode="+data.formData.companyCode;
          },
          cleanCompanyInfo:function (){
            bus.$emit("cleanCompanyInfo","1");
          },
          returnCompanyList:function (){
            bus.$emit("returnCompanyList","1");
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