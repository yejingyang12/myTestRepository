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
        	//到定级页
        	toGradPageSession:function(_self){
        			ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsSession', false,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveSystemMaterialsSessionSuccess);
        	},
        	saveSystemMaterialsSessionSuccess:function(_self,responseData){
        		if(responseData.data!=null&&responseData.data!=''&&responseData.data!='undefind'){
        			window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
        		}
        	},
          //保存
          saveBtn:function(formName) {
            bus.$emit('changeMaterialFormName',formName);
          },
          //返回
          returnBtn:function() {
          	ajaxMethod(_self, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',_self.removeSessionSuccess);
            window.location.href = originUrl+"page/indexPage";
          },
          removeSessionSuccess:function(){
          	
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            data.formData.systemMaterialsId = responseData.data;
            $(".save").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
            //清空session
            ajaxMethod(_self, 'post',
                'main/removeSession', true,
                JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',
                _self.removeSessionSuccess);
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('changeSubmitMaterialFormName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            data.formData.systemMaterialsId = responseData.data;
            //$(".startBox").show().delay(2000).fadeOut();
            $(".submit").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          //清空session
            ajaxMethod(_self, 'post',
                'main/removeSession', true,
                JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',
                _self.removeSessionSuccess);
          },
          removeSessionSuccess:function(){
          	
          },
          //上一页
          preBtn:function(formName) {
            data.check = false;
            bus.$emit('changePreMaterialFormName',formName);
          },
          // 成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
            if(boo){
              data.check = false;
              data.formData.systemMaterialsId = responseData.data;
              window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
            }else{
              $(".startBox").show().delay(2000).fadeOut();
              window.setTimeout(function () {
                window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
              }, 2300);
            }
          },
          queryCompanySuccess :function(_self,responseData){
          	if(responseData.data!=null){
          		data.companyBySession = responseData.data;
          	}
          },
          querySystemSession:function(_self,responseData){
          	if(responseData.data!=null){
          		data.systemBySession = responseData.data;
          	}
          }, 
          queryGradSessionSession:function(_self,responseData){
          	if(responseData.data!=null){
          		data.gradingBySession = responseData.data;
          	}
          },
          success:function(){
          	
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('changeMaterialFormAjax',function(meg){
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
          	 //获取定级
          	 ajaxMethod(_self, 'post',
                 'grading/queryGradSession', false,
                 JSON.stringify(""), 'json',
                 'application/json;charset=UTF-8',
                 _self.queryGradSessionSession);
          	
          	//保存单位
            if(data.companyBySession!=null){
            	ajaxMethod(_self, 'post',
            			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
            			'application/json;charset=UTF-8',_self.success);
            }
            //保存系统
            if(data.systemBySession!=null){
            	data.systemBySession.changeType = "1";
              ajaxMethod(_self, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.systemBySession), 'json',
                  'application/json;charset=UTF-8',
                  _self.success);
            }
          	//保存定级
            if(data.gradingBySession!=null){
            	data.gradingBySession.changeType = "1";
            	ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.gradingBySession), 'json',
                  'application/json;charset=UTF-8',
                  _self.success);
            }
          	
            if(meg!=null){
              data.formData.changeType = "1";
              data.formData.saveType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsInfo', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('changeSubmitMaterialFormAjax',function(meg){
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
          	 //获取定级
          	 ajaxMethod(_self, 'post',
                 'grading/queryGradSession', false,
                 JSON.stringify(""), 'json',
                 'application/json;charset=UTF-8',
                 _self.queryGradSessionSession);
          	
          	//保存单位
            if(data.companyBySession!=null){
            	ajaxMethod(_self, 'post',
            			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
            			'application/json;charset=UTF-8',_self.success);
            }
            //保存系统
            if(data.systemBySession!=null){
            	data.systemBySession.changeType = "1";
              ajaxMethod(_self, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.systemBySession), 'json',
                  'application/json;charset=UTF-8',
                  _self.success);
            }
          	//保存定级
            if(data.gradingBySession!=null){
            	data.gradingBySession.changeType = "1";
            	ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.gradingBySession), 'json',
                  'application/json;charset=UTF-8',
                  _self.success);
            }
          	
            if(meg!=null){
              data.formData.changeType = "1";
              data.formData.saveType = "2";
              if(data.jurisdictionType==1){
                ajaxMethod(_self, 'post',
                    'grading/submitSystemMaterials', false,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }else if(data.jurisdictionType==2){
                ajaxMethod(_self, 'post',
                    'grading/submitSystemMaterialsForHeadquarters', false,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }
            }
          });
          
          bus.$on('changePreMaterialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterials', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
        }
    })
}