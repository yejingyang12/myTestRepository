/**
 * Created by timha on 2018/6/4.
 */
window.onload = function () {

  var app = new Vue({
      el:"#app",
      data:function () {
          return{
              "msg":"jdksfljafds"
          }
      },
      methods : {
        submitForm:function(){
          ajaxMethod(this, 'post',
              'company/saveCompany', true,JSON.stringify(data.formData), 'json',
              'application/json;charset=UTF-8',this.submitHandlerSuccessMethod);
        },
        submitHandlerSuccessMethod: function(_self,data){
          window.location.href = originUrl+"page/applicatuibChangSystemPage?companyCode=" + data.data+"&systemId="+systemId;
        },
      }
  })
}