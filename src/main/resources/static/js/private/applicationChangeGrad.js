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
          	this.returnIndex = true;
          },
          returnIndexMethod:function(){
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',_self.removeSessionSuccess);
          	window.location.href = originUrl+"page/indexPage";
          },
          removeSessionSuccess:function(){
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            //清除session
            ajaxMethod(_self, 'post',
                'main/removeSession', true,
                JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',
                _self.removeSessionSuccess);
            //显示保存成功弹窗
            var formName = _self.formData;
          	bus.$emit('placeContent',formName);
          	_self.saveSuccess = true;
          },
          //保存且提交
          saveAndSubmit:function(formName){
        		this.yesOrNotSubmit = false;
        		this.saveYesOrNo = true;
        		this.judgeSaveOrNot();
        	},
        	//保存但不提交
        	cancelSaveSuccess:function(){
          	var formName = this.formData;
          	bus.$emit('placeContent',formName);
          	this.changeFlag = true;
          	this.saveSuccess=false;
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSessionSuccess);
          },
          //新的提交方法
          judgeSaveOrNot:function(){
        		this.saveSuccess = false;
        		var _self = this;
        		//如果上次保存完又改动过数据，出提示保存弹窗
        		bus.$emit("judgeChange","change");
          	//保存，点击提交
          	if(_self.saveYesOrNo){
          			setTimeout(function(){
            			_self.submitBtn('formData');
         			 	},1000);
          	}else{//保存没提交，或，没保存
          		if(_self.changeFlag){//判断是否点击过保存
          			//保存但取消提交
          			if(_self.flag1){//数据没有改变
          				_self.yesOrNotSubmit = true;
          			}else{//数据改变
          				_self.saveThePrompt = true;
          			}
          		}else{//没有点击过保存
          			_self.saveThePrompt = true;
          		}
          	}
        	},
          
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('changeSubmitGradName',formName);
          },
          // 提交成功
          submitBtnSuccessMethod : function(_self, responseData) {
            //$(".startBox").show().delay(2000).fadeOut();
          	$(".submit").show().delay(1000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 1300);
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
              window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
            }else{
              //$(".startBox").show().delay(2000).fadeOut();
            	if(_self.formData.saveType == "1"){
            		$(".save").show().delay(1000).fadeOut();
            	}else if(_self.formData.saveType == "2"){
            		$(".submit").show().delay(1000).fadeOut();
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
            if(data.companyBySession!=null && data.companyBySession!=''){
            	ajaxMethod(_self, 'post',
            			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
            			'application/json;charset=UTF-8',_self.success);
            }
            //保存系统
            if(data.systemBySession!=null && data.systemBySession!=''){
            	data.systemBySession.changeType = "1";
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
                   _self.querySystemSessionSuccess);
            	//提交单位
               if(data.companyBySession!=null && data.companyBySession!=''){
               	ajaxMethod(_self, 'post',
               			'company/saveCompany', true,JSON.stringify(data.companyBySession), 'json',
               			'application/json;charset=UTF-8',_self.success);
               }
               //提交系统
               if(data.systemBySession!=null && data.systemBySession!=''){
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