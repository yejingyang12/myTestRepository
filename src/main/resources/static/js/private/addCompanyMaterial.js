/**
 * Created by timha on 2018/6/1.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods:{
        	
          //保存
          saveBtn:function(formName) {
            bus.$emit('addMaterialFormName',formName);
          },
          // 保存成功
          saveBtnSuccessMethod : function(_self, responseData) {
          	var systemId = responseData.data;
          	bus.$emit('deleteConfirm','');
          	bus.$emit('placeContent',_self);
          	this.querySystemMaterialsInfo(systemId);
          	_self.saveSuccess = true;

          	//查询材料表Id
          },
          querySystemMaterialsInfo : function(systemId){
          	ajaxMethod(_self, 'post','grading/queryEditSystemMaterialsInfo', false,
                '{"fkSystemId":"'+systemId+'"}', 'json',
                'application/json;charset=UTF-8',
                _self.getMaterialsInfoSuccessMethod);
          },
          getMaterialsInfoSuccessMethod : function(_self, responseData){
          	_self.formData.systemMaterialsId = responseData.data.systemMaterialsId;
          },
          saveAndSubmit:function(formName){
          	this.yesOrNotSubmit = false;
          	this.saveYesOrNo = true;
          	this.judgeSaveOrNot();
        		
          },
          cancelSaveSuccess:function(){
          	bus.$emit('placeContent',this);
          	this.saveSuccess = false;
          	this.changeFlag = true;
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
          },
          judgeSaveOrNot:function(){
          	this.saveSuccess = false;
          	var _self = this;
        		//如果上次保存完又改动过数据，出提示保存弹窗
          	bus.$emit('judgeChange','change');
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
            bus.$emit('addSubmitMaterialFormName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
          	
          	bus.$emit('deleteConfirm','');
            //$(".startBox").show().delay(2000).fadeOut();
          	$(".submit").show().delay(1000).fadeOut();
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 1300);
          },
          removeSuccess:function(){
          },
          //上一页
          preBtn:function(formName) {
          	
            data.check = false;
            bus.$emit('addPreMaterialFormName',formName);
          },
          saveMaterialToSession:function(){
            //新的上一页，点击没有验证，直接过，存入session，下一步回显
            	ajaxMethod(this, 'post',
                  'grading/saveSystemMaterialsSession', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveMaterialSessionSuccess);
            },
            saveMaterialSessionSuccess:function(_self,responseData){
            	if(responseData.data!=null){
            		window.location.href = originUrl+"page/addCompanyGradPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
            	}
            },
          
          // 成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
          	bus.$emit('deleteConfirm','');
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/addCompanyGradPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
            }else{
	            	if(_self.formData.saveType == "1"){
	            		$(".save").show().delay(1000).fadeOut();
	            	}else if(_self.formData.saveType == "2"){
	            		$(".submit").show().delay(1000).fadeOut();
	            	}
//              window.setTimeout(function () {
                window.location.href = originUrl+"page/addCompanyGradPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
//              }, 2300);
            }
          },
          returnIndexMethod:function(){
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSessionSuccess);
          },
          removeSessionSuccess:function(){
          	window.location.href = originUrl+"page/indexPage";
          },
          //返回
          returnBtn:function() {
          	this.judgeChange();
          	if(this.flag1){
          		//没有改变
          		window.location.href = originUrl+"page/indexPage";
          	}else{
          		this.returnIndex = true;
          	}
          },
          judgeChange:function(){
          	bus.$emit('judgeChange','add');
          },
          
          //保存返回
          retuenSave:function(formName){
          	this.retuenCheck = false;
          	bus.$emit('retuenSaveAttach',formName);
          	
          },
          //不保存返回
          returnNotSave:function(){
          	this.retuenCheck = false;
          	window.location.href = originUrl+"page/indexPage";
          },
          retuenSaveAttachSuccess:function(_self,responseData){
          	bus.$emit('deleteConfirm','');
          	window.location.href = originUrl+"page/indexPage";
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('addMaterialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              data.formData.saveType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsInfo', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('addSubmitMaterialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              data.formData.saveType = "2";
              if(data.jurisdictionType==1){
                ajaxMethod(_self, 'post',
                    'grading/submitSystemMaterials', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }else if(data.jurisdictionType==2){
                ajaxMethod(_self, 'post',
                    'grading/submitSystemMaterialsForHeadquarters', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }
            }
          });
          
          bus.$on('addPreMaterialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              data.formData.saveType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsInfo', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
          
          bus.$on('retuenSaveAttachAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              data.formData.saveType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsInfo', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.retuenSaveAttachSuccess);
            }
          });
        }
    })
}