/**
 * Created by timha on 2018/6/4.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods:{
        	//上一步
        	toSystemPage:function(meg){
        		if(meg!=null){
        			ajaxMethod(_self, 'post',
            			'grading/saveGradSession', true,JSON.stringify(data.formData), 'json',
            			'application/json;charset=UTF-8',this.saveGradSessionSuccess);
        		}
        	},
        	saveGradSessionSuccess : function(_self,responseData){
        		if(responseData.data!=null){
        			window.location.href = originUrl+"/page/applicatuibChangSystemPage?systemId="+systemId+"&fkCompanyCode="+companyCode;        	
        		}
        	},
        	
          //保存
          saveBtn:function(formName) {
            bus.$emit('changeGradName',formName);
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
          	$(".save").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
            //清除session
            ajaxMethod(_self, 'post',
                'main/removeSession', true,
                JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',
                _self.removeSessionSuccess);
          },
          removeSessionSuccess:function(){
          	
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('changeSubmitGradName',formName);
          },
          // 提交成功
          submitBtnSuccessMethod : function(_self, responseData) {
            //$(".startBox").show().delay(2000).fadeOut();
          	$(".submit").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
            //清除session
            ajaxMethod(_self, 'post',
                'main/removeSession', true,
                JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',
                _self.removeSessionSuccess);
          },
          //上一页
//          preBtn:function(formName) {
//            data.check = false;
//            bus.$emit('changePreGradName',formName);
//          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
          	if(boo){
              data.check = false;
              window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId;
            }else{
              //$(".startBox").show().delay(2000).fadeOut();
            	if(_self.formData.saveType == "1"){
            		$(".save").show().delay(2000).fadeOut();
            	}else if(_self.formData.saveType == "2"){
            		$(".submit").show().delay(2000).fadeOut();
            	}
//              window.setTimeout(function () {
                window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
//              }, 2300);
            }
          },
          //下一页
          nextBtn1:function(formName) {
            bus.$emit('changeNextGradName',formName);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangeMaterialPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
          },
          queryCompanySuccess :function(_self,responseData){
          	if(responseData.data!=null){
          		data.companyBySession = responseData.data;
          	}
          },
          querySystemSessionSuccess:function(_self,responseData){
          	if(responseData.data!=null){
          		data.systemBySession = responseData.data;
          	}
          },
          toAttachPage:function(_self,responseData){
          	if(responseData.data!=null&&responseData.data!=''&&responseData.data!='undefind'){
              window.location.href = originUrl+"page/applicationChangeMaterialPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
          	}
          },
          success:function(){
          	
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('changeGradAjax',function(meg){
          	//获取单位
          	ajaxMethod(_self, 'post',
          			'company/queryCompanyBySession', false,JSON.stringify(""), 'json',
          			'application/json;charset=UTF-8',_self.queryCompanySuccess);
          	//获取系统
          	 ajaxMethod(_self, 'post',
                 'system/querySystemSession', false,
                 JSON.stringify(""), 'json',
                 'application/json;charset=UTF-8',
                 _self.querySystemSessionSuccess);
          	
          	//保存单位
            if(data.companyBySession!=null){
            	ajaxMethod(_self, 'post',
            			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
            			'application/json;charset=UTF-8',_self.success);
            }
            //保存系统
            if(data.systemBySession!=null){
            	data.systemBySession.changeType = "1";
            	debugger
              ajaxMethod(_self, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.systemBySession), 'json',
                  'application/json;charset=UTF-8',
                  _self.success);
            }
          	//保存定级
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
            
          });
          bus.$on('changeSubmitGradAjax',function(meg){
            if(meg!=null){
            //获取单位
            	ajaxMethod(_self, 'post',
            			'company/queryCompanyBySession', false,JSON.stringify(""), 'json',
            			'application/json;charset=UTF-8',_self.queryCompanySuccess);
            	//获取系统
            	 ajaxMethod(_self, 'post',
                   'system/querySystemSession', false,
                   JSON.stringify(""), 'json',
                   'application/json;charset=UTF-8',
                   _self.querySystemSession);
            	//提交单位
               if(data.companyBySession!=null){
               	ajaxMethod(_self, 'post',
               			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
               			'application/json;charset=UTF-8',_self.success);
               }
               //提交系统
               if(data.systemBySession!=null){
               	data.systemBySession.changeType = "1";
                 ajaxMethod(_self, 'post',
                     'system/editSystem', true,
                     JSON.stringify(data.systemBySession), 'json',
                     'application/json;charset=UTF-8',
                     _self.success);
               }
              
              data.formData.changeType = "1";
              if(data.jurisdictionType==1){
                ajaxMethod(_self, 'post',
                    'grading/submitGrading', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }else if(data.jurisdictionType==2){
                ajaxMethod(_self, 'post',
                    'grading/submitGradingForHeadquarters', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }
            }
          });
          bus.$on('changePreGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
            
          });
          
          bus.$on('toAttachPage',function(meg){
          	if(meg!=null){
          		ajaxMethod(_self, 'post','grading/saveGradSession', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.toAttachPage);
          	}
          });
          bus.$on('changeNextGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post','grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtnSuccessMethod);
            }
          });
        }
    })
}