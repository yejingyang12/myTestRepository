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
          bus.$emit('changeFormName',formName);
        },
        submitHandlerSuccessMethod: function(_self,data){
          window.location.href = originUrl+"page/applicatuibChangSystemPage?companyCode=" + data.data+"&systemId="+systemId;
        },
      },
      mounted : function() {
        var _self = this;
        bus.$on('changeFormAjax',function(meg){
          if(meg!=null){
            data.formData.changeType = "1";
            data.formData.systemId = systemId;
            ajaxMethod(_self, 'post',
                'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
          }
        });
      }
  })
}