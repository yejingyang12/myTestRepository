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
            $.ajax({
              type: "get",
              url : originUrl+"/jurisdiction/queryMenuJurisdictionApi", 
              async: false,
              data: "",
              dataType: "json",
              cache: false,
              processData: false,
              contentType: false,
              success: function(response) {
              	//总部新建
              	var headquarters  = getJurisdictionMethod(response,'0102010101');
              	//企业新建
              	var enterprise = getJurisdictionMethod(response,'0102010108');
              	if(headquarters){
              		data.formData.jurisdiction = "1";
              	}
              	if(enterprise){
              		data.formData.jurisdiction = "2";
              	}
              },
              error: function(err) {
              }
            });
            ajaxMethod(_self, 'post',
                'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
          }
        });
      }
  })
}