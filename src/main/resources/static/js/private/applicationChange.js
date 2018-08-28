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
        submitHandlerSuccessMethod: function(_self,data,boo){
//          if(boo){
//            data.check = false;
//            window.location.href = originUrl+"page/applicatuibChangSystemPage?fkCompanyCode=" + data.data+"&systemId="+systemId;
//          }else{
//            $(".startBox").show().delay(2000).fadeOut();
//            window.setTimeout(function () {
              window.location.href = originUrl+"page/applicatuibChangSystemPage?fkCompanyCode=" +companyCode+"&systemId="+systemId+"&companyId="+data.data;
//            }, 2300);
//          }
        },
        //返回
        returnBtn:function(_self) {
        	ajaxMethod(_self, 'post',
              'main/removeSession', true,JSON.stringify(''), 'json',
              'application/json;charset=UTF-8',this.removeSessionSuccess);
          window.location.href = originUrl+"page/indexPage";
        },
        removeSessionSuccess:function(){
        	
        },
        setFormDataToSession:function(meg){
        	bus.$emit('changeFormName',meg);
        },
        //将单位信息存入session后成功
        saveCompanyToSessionSuccess:function(_self,responseData){
        	if(responseData.data!=null){
        		window.location.href = originUrl+"/page/applicatuibChangSystemPage?fkCompanyCode=" +companyCode+"&systemId="+systemId+"&companyId="+companyId;
        	}
        },
      },
      mounted : function() {
        var _self = this;
        bus.$on('toSystemPage',function(meg){
        	ajaxMethod(_self, 'post',
              'company/saveCompanyToSession', true,JSON.stringify(data.formData), 'json',
              'application/json;charset=UTF-8',_self.saveCompanyToSessionSuccess);
        });
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
              		data.formData.jurisdictionDel = "1";
              	}
              	if(enterprise){
              		data.formData.jurisdictionDel = "2";
              	}
              },
              error: function(err) {
              }
            });
            if(fkChangeMatter){
            	data.formData.fkNodeChangeReason = changeReason;
            }
            if(changeReason){
            	data.formData.fkNodeChangeContent = changeContent;
            }
            if(changeContent){
            	data.formData.fkNodeSysChangeMatter = fkChangeMatter+"";
            }
            ajaxMethod(_self, 'post',
                'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
          }
        });
      }
  })
}