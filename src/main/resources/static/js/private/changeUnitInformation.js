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
            this.openCleanCompanyInfo();
            bus.$emit('formName',formName);
            this.closeDialog();
          },
          submitHandlerSuccessMethod: function(_self,returnData){
            window.location.href = originUrl+"page/mainCompanyInfoPage?companyId=" + returnData.data+"&companyCode="+data.formData.companyCode;
          },
          cleanCompanyInfo:function (){
          	this.openCleanCompanyInfo();
            bus.$emit("cleanCompanyInfo","1");
            this.closeDialog();
          },
          returnCompanyList:function (){
            bus.$emit("returnCompanyList","1");
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          },
          
          //显示弹窗
          openSaveCompanyInfo:function(){
          	$("#saveCompanyInfoDialog").css("display","block");
          	$("#saveCompanyInfoDialogShaw").css("display","block");
          },
          openCleanCompanyInfo:function(){
          	$("#cleanCompanyInfoDialog").css("display","block");
          	$("#cleanCompanyInfoDialogShaw").css("display","block");
          },
          closeDialog:function(){
          	$("#saveCompanyInfoDialog").css("display","none");
          	$("#cleanCompanyInfoDialog").css("display","none");
          	$("#saveCompanyInfoDialogShaw").css("display","none");
          	$("#cleanCompanyInfoDialogShaw").css("display","none");
          },
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