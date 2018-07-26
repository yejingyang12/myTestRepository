/**
 * Created by timha on 2018/6/4.
 */
window.onload = function () {

  var app = new Vue({
      el:"#app",
      data:function () {
          return data;
      },
      methods : {
        submitForm:function(formName){
          data.check=false;
          bus.$emit('changeFormName',formName);
        },
        submitHandlerSuccessMethod: function(_self,data,boo){
//          if(boo){
//            data.check = false;
//            window.location.href = originUrl+"page/applicatuibChangSystemPage?fkCompanyCode=" + data.data+"&systemId="+systemId;
//          }else{
//            $(".startBox").show().delay(2000).fadeOut();
//            window.setTimeout(function () {
              window.location.href = originUrl+"page/applicatuibChangSystemPage?fkCompanyCode=" +companyCode+"&systemId="+systemId+"&companyId"+data.data;
//            }, 2300);
//          }
        },
        //返回
        returnBtn:function() {
          window.location.href = originUrl+"page/indexPage";
        }
      },
      mounted : function() {
        var _self = this;
        bus.$on('changeFormAjax',function(meg){
          if(meg!=null){
          	if(theLastStep == null || theLastStep == ''){
          		data.formData.changeType = "1";
          	}
            data.formData.systemId = systemId;
            ajaxMethod(_self, 'post',
                'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
          }
        });
      }
  })
}