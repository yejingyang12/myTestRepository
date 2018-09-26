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
        	//判断定级列表是否被改变
        	judgeChange:function(judge){
        		this.judgeType = judge;//1,上一步，2，返回
        		bus.$emit("judgeChange","add");
        		if(this.judgeType == 1){//上一步
        			if(this.flag1){
        				this.preBtnSuccessMethod('', '',true);
        			}else{
        				this.check = true;
        			}
        		}else if(this.judgeType == 2){//返回
        			if(this.flag1){//没有改动
        				window.location.href = originUrl+"page/indexPage";
        			}else{
        				this.returnIndex = true;
        			}
        		}
        	},
        	
        	saveAndSubmit:function(formName){
        		this.yesOrNotSubmit = false;
        		this.saveYesOrNo = true;
        		this.judgeSaveOrNot();
        	},
        	judgeSaveOrNot:function(){
        		this.saveSuccess = false;
        		var _self = this;
        		//如果上次保存完又改动过数据，出提示保存弹窗
        		bus.$emit("judgeChange","add");
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
          //保存
          saveBtn:function(formName) {
            bus.$emit('addGradName',formName);
          },
//          saveAlertBtn:function(formName) {
//          	this.judgeTwoOrThree = true;
//            bus.$emit('addGradName',formName);
//          },
          queryGradingIdBySystemId:function(systemId){
          	ajaxMethod(_self, 'post','grading/queryEditGrading', false,
                '{"fkSystemId":"'+systemId+'"}', 'json',
                'application/json;charset=UTF-8',
                _self.getGradeSuccessMethod);
          },
          getGradeSuccessMethod:function(_self, responseData){
          	_self.formData.gradingId = responseData.data.gradingId;
          },
          saveBtnSuccessMethod : function(_self, responseData) {
          	_self.formData.fkSystemId = responseData.data;
          	//通过systemId获取定级id
          	this.queryGradingIdBySystemId(_self.formData.fkSystemId);
          	
            //点击保存，即清空所有session。
            ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
            if(_self.judgeTwoOrThree){
            	$(".save").show().delay(1000).fadeOut();
            }else{
            	var formName = _self.formData;
            	bus.$emit('placeContent',formName);
            	_self.saveSuccess = true;
            }
          },
          cancelSaveSuccess:function(){
          	var formName = this.formData;
          	bus.$emit('placeContent',formName);
          	this.changeFlag = true;
          	this.saveSuccess=false;
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('addSubmitGradName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            $(".submit").show().delay(1000).fadeOut();
            //提交也清空session
            ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 1300);
          },
          //上一页
          preBtn:function(formName) {
            data.check = false;
            bus.$emit('addPreGradName',formName);
          },
          saveGradingToSession:function(){
          //新的上一页，点击没有验证，直接过，存入session，下一步回显
          	ajaxMethod(this, 'post',
                'grading/saveGradSession', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.saveGradingSessionSuccess);
          },
          saveGradingSessionSuccess:function(_self,responseData){
          	if(responseData.data!=null){
          		window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId+"&companyId="+companyId+"&companyCode="+companyCode;
          	}
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId+"&companyId="+companyId+"&companyCode="+companyCode;
            }else{
              $(".startBox").show().delay(1000).fadeOut();
              window.setTimeout(function () {
                data.formData.gradingId = responseData.data;
                window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId+"&companyId="+companyId+"&companyCode="+companyCode;
              }, 1300);
            }
          },
          //下一页
          next1Btn:function(formName) {
            bus.$emit('addNextGradName',formName);
          },
          removeSuccess:function(){
          },
          // 获取成功
          nextBtn2SuccessMethod : function(_self, responseData) {
          	data.formData.gradingId = responseData.data;
          	ajaxMethod(this, 'post',
                'main/removeGradingSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSuccess);
            window.location.href = originUrl+"page/addCompanyMaterialPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
          },
          
          returnBtn:function() {
//          	this.returnCheck = false;
//            window.location.href = originUrl+"page/indexPage";
          	this.judgeChange(2)
          },
          returnIndexMethod:function(){
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSessionSuccess);
          },
          removeSessionSuccess:function(){
          	window.location.href = originUrl+"page/indexPage";
          },
          
          //确认保存，返回
          retuenSave:function(formName){
            bus.$emit('retuenSaveGrad',formName);
            this.returnCheck = false;
          },
          retuenSaveGradAjaxSuccess:function(_self,responseData){
          	window.location.href = originUrl+"page/indexPage";
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('addGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('addSubmitGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
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
          bus.$on('addPreGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
          bus.$on('retuenSaveGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.retuenSaveGradAjaxSuccess);
            }
          });
          
          bus.$on('addNextGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtn2SuccessMethod);
            }
          });
        }
    })
}