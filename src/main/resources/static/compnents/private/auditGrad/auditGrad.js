(function () {
  var data = {
      jurisdictionShow:false,
      jurisdictionType:0,
		  ruleForm: {
	          name: '',
	          scoreCheckResult: '',
	          date1: '',
	          date2: '',
	          delivery: false,
	          type: [],
	          resource: '',
	          scoreCheckReason: ''
	        },
	        rules: {
		        
	        	scoreCheckResult: [
		            { required: true, message: '请选择审核结果', trigger: 'change' }
		          ],
		        
		          scoreCheckReason: [
		            { required: true, message: '请填写审核原因', trigger: 'blur' }
		          ]
		        },	        
    formData:{
    	fkSystemId: null,
    	//传入定级数据&&//获得申请变更的数据
  		scoreCheckResult: null,
  		scoreCheckReason: null,
  		fkrevokematter: null,
  		//传入撤销备案数据
  		cancelRecordsResult: null,
      cancelRecordsReason: null,
  		//传入申请变更数据
  		scoreCheckChangeResult: null,
			scoreCheckChangeReason: null,
  		//请求后端获得数据
  		systemId: null,
  		//获得撤销备案数据
  		fkChangeMatter: null,
  		changeContent: null,
  		changeReason: null,
  		businessId: null,
  		taskId: null,
    },
    scoreCheckResultList:[
     {codeId: 1,codeName: '通过'},
     {codeId: 2,codeName: '不通过'}
    ],
    fkChangeMatterShow: false,
    changeContentShow: false,
    changeReasonShow: false,
    fkrevokematterShow: false,
    revokereasonShow: false,
    revokeRecordsNameShow: false
  };
  Vue.component('auditGrad',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditGrad/auditGrad.html').then(function (res) {
      resolve({
        template:res,
        data: function () {
          return data;
        },
        created: function(){
        	this.formData.systemId=systemId;
        	this.formData.fkSystemId=systemId;
        	this.queryGradingEditAudit(this);
        },
        methods:{
        	resetForm:function(formName) {
		        this.$refs[formName].resetFields();
		      },
        	submitForm:function(_self,formName) {
        	  if(_self==''){
        	    _self = this;
        	  }
        	  this.$refs[formName].validate(function(valid){
			          if (valid) {
			        	_self.saveGradCheck(_self);
			          } else {
			          	if(_self.ruleForm.scoreCheckResult != 1){
			          		_self.open5();
				            return false;
			          	}
			         }
			     });
			 },
			 open5:function() {
			        this.$alert('<strong>请填写信息</strong>', '提示', {
			          dangerouslyUseHTMLString: true
			        });
			  }, 
        	//获取变更信息
        	queryGradingEditAudit: function(_self){
        		switch (fkBusinessNode) {
            case '1':
              break;
            case '2':
            	//撤销备案
            	ajaxMethod(_self,"post",
              		"records/queryRevokeRecords",false,
              		JSON.stringify(_self.formData),"json",
              		'application/json;charset=UTF-8', _self.queryGradingEditAuditSuccess);
              break;
            case '3':
            	//定级信息变更
            	ajaxMethod(_self,"post",
          				"system/queryGradingEditAudit",false,
          				JSON.stringify(_self.formData),"json",
          				'application/json;charset=UTF-8', _self.queryGradingEditAuditSuccess);
              break;
            default:
              break;
            }
        	},
        	queryGradingEditAuditSuccess: function(_self,responseData){
        		if(responseData.data !=null){
        			switch (fkBusinessNode) {
              case '2':
              	if(responseData.data.fkrevokematter!= null){
          				_self.fkrevokematterShow = true;
          				_self.revokereasonShow = true;
          				_self.revokeRecordsNameShow = true;
          				_self.formData.fkrevokematter = responseData.data.fkrevokematter;
          				switch (_self.formData.fkrevokematter) {
                  case '1':
                  	_self.formData.fkrevokematter = '系统合并';
                    break;
                  case '2':
                  	_self.formData.fkrevokematter = '级别降低';
                    break;
                  case '3':
                  	//定级信息变更
                  	_self.formData.fkrevokematter = '级别升高';
                    break;
                  case '4':
                  	_self.formData.fkrevokematter = '撤销备案';
                    break;
                  case '5':
                  	_self.formData.fkrevokematter = '其他';
                    break;

                  default:
                    break;
                  }
          				_self.formData.revokereason = responseData.data.revokereason;
          				_self.formData.revokeRecordsName = responseData.data.revokeRecordsName;
          				_self.formData.revokeRecordsId = responseData.data.revokeRecordsId;
          			}
              case '3':
              	if(responseData.data.fkChangeMatter!= null){
          				_self.fkChangeMatterShow = true;
          				_self.changeContentShow = true;
          				_self.changeReasonShow = true;
          				_self.formData.fkChangeMatter = responseData.data.fkChangeMatter;
          				switch (_self.formData.fkChangeMatter) {
                  case '1':
                  	_self.formData.fkChangeMatter = '系统合并';
                    break;
                  case '2':
                  	_self.formData.fkChangeMatter = '级别降低';
                    break;
                  case '3':
                  	//定级信息变更
                  	_self.formData.fkChangeMatter = '级别升高';
                    break;
                  case '4':
                  	_self.formData.fkChangeMatter = '撤销备案';
                    break;
                  case '5':
                  	_self.formData.fkChangeMatter = '其他';
                    break;

                  default:
                    break;
                  }
          				_self.formData.changeContent = responseData.data.changeContent;
          				_self.formData.changeReason = responseData.data.changeReason;
              	}
              default:
                break;
              }
        			
        		}
        	},
        	saveGradCheck: function(_self){
            if(_self.jurisdictionType==1){
              switch (fkBusinessNode) {
              case '1':
                _self.formData.scoreCheckResult = _self.ruleForm.scoreCheckResult;
                _self.formData.scoreCheckReason = _self.ruleForm.scoreCheckReason;
                ajaxMethod(_self,"post",
                   "checkController/saveGradCheck",false,
                   JSON.stringify(this.formData),"json",
                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
                break;
              case '2':
                _self.formData.cancelRecordsResult = _self.ruleForm.scoreCheckResult;
                _self.formData.cancelRecordsReason = _self.ruleForm.scoreCheckReason; 
                ajaxMethod(_self,"post",
                   "checkController/saveCancelRecordsCheck",false,
                   JSON.stringify(this.formData),"json",
                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
                break;
              case '3':
                _self.formData.scoreCheckChangeResult = _self.ruleForm.scoreCheckResult;
                _self.formData.scoreCheckChangeReason = _self.ruleForm.scoreCheckReason;
                ajaxMethod(_self,"post",
                   "checkController/saveGradChangeCheck",false,
                   JSON.stringify(this.formData),"json",
                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
                break;
    
              default:
                break;
              }
            }else if(_self.jurisdictionType==2){
              switch (fkBusinessNode) {
              case '1':
                _self.formData.scoreCheckResult = _self.ruleForm.scoreCheckResult;
              	_self.formData.scoreCheckReason = _self.ruleForm.scoreCheckReason;
                ajaxMethod(_self,"post",
                   "checkController/saveHeadGradCheck",false,
                   JSON.stringify(this.formData),"json",
                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
                break;
              case '2':
//                _self.formData.cancelRecordsResult = _self.ruleForm.scoreCheckResult;
//                _self.formData.cancelRecordsReason = _self.ruleForm.scoreCheckReason; 
//                ajaxMethod(_self,"post",
//                   "checkController/saveCancelRecordsCheck",false,
//                   JSON.stringify(this.formData),"json",
//                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
//                break;
              case '3':
                _self.formData.scoreCheckChangeResult = _self.ruleForm.scoreCheckResult;
                _self.formData.scoreCheckChangeReason = _self.ruleForm.scoreCheckReason;
                ajaxMethod(_self,"post",
                   "checkController/saveHeadGradChangeCheck",false,
                   JSON.stringify(this.formData),"json",
                   'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
                break;
    
              default:
                break;
              }
            }
          },
          saveGradCheckSuccess: function(_self,response){
        		window.location.href=originUrl+encodeURI("/page/auditPage");
          },
          getPermitJurisdictionInfo: function(_self){
            ajaxMethod(_self,"post",
                "jurisdiction/queryDataJurisdictionApi",false,
                JSON.stringify(this.formData),"json",
                'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
          },
          getPermitJurisdictionSuccess: function(_self,response){
            
            for (var i = 0; i < response.data.permssions.length; i++) {
              var permssions = response.data.permssions[i];
              if(permssions==S_STR_PERMIT_PARAM_ENTERPRISE_AUDIT){
                _self.jurisdictionType = 1;
              }
              if(permssions==S_STR_PERMIT_PARAM_HEADQUARTERS_AUDIT){
                _self.jurisdictionType = 2;
              }
            }
            if(_self.jurisdictionType==1&&fkExaminStatus=='1'){
              _self.jurisdictionShow = true;
            }else if(_self.jurisdictionType==2&&fkExaminStatus=='2'){
              _self.jurisdictionShow = true;
            }else{
              _self.jurisdictionShow = false;
              bus.$emit("jurisdictionShow","1");
            }
          },
          //审核结构：显示输
          changeScore : function(){
          	if(this.ruleForm.scoreCheckResult == 2){
          		this.rules.scoreCheckReason[0].required = true;
          		if(this.ruleForm.scoreCheckReason == "通过"){
          			this.ruleForm.scoreCheckReason = "";
          		}
          	}else if(this.ruleForm.scoreCheckResult == 1){
          		this.rules.scoreCheckReason[0].required = false;
          		this.$refs.scoreCheckReason.clearValidate();
          		if(this.ruleForm.scoreCheckReason == ""){
          			this.ruleForm.scoreCheckReason = "通过";
          		}
          	}
          },
          //审核结构：显示输入内容的字数
          text:function(){
            $('#scoreCheckReason').on("keyup",function(){
              $('#textNum').text($('#scoreCheckReason').val().length);//键盘按下时，实时的显示字数
              data.scoreCheckReason=$('#scoreCheckReason').val();
              if($('#scoreCheckReason').val().length > 200){
                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                $('#scoreCheckReason').val($('#scoreCheckReason').val().substring(0,200));//长度大于100时截取钱100个字符
              }
            })
          },
          
          downloadFile: function(fileId,attachName){
          	if(attachName == "undefined"){
          		attachName = null;
          	}
          	if(fileId == "undefined"){
          		fileId = null;
          	}
          	//下载路径
          	window.location.href = originUrl+encodeURI("fileHandle/downloadFile?attachName="+attachName+"&fileId="+fileId);
          }
        },
        
        mounted: function() {
        	var _self=this;
        	this.formData.taskId = taskId;
          this.formData.businessId = businessId;
        	//审核人
        	_self.getPermitJurisdictionInfo(_self);
        	
          //点击提交按钮 发送请求 
          bus.$on("gradSubmit",function(meg){
            if(meg!=null){
              _self.submitForm(_self,meg);
            }
          })
          //点击返回按钮 返回到审核管理页面
          bus.$on("gradReturn",function(meg){
          	if(meg!=null){
          		window.location.href=originUrl+encodeURI("page/auditPage");
          	}
          })
        }
      })
   })
 })
}())