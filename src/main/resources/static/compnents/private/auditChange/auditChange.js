(function () {
  var data = {
		formData:{
    	fkSystemId: null,
  		scoreCheckResult: null,
  		scoreCheckReason: null
    },
    scoreCheckResultList:[
     {codeId: 1,codeName: '通过'},
     {codeId: 2,codeName: '不通过'}
    ]
  };
  Vue.component('auditChange',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditChange/auditChange.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function(){
        	this.formData.fkSystemId = smccId;
        	this.queryGradingEditAudit(this);
        },
        methods:{
        	//获取变更信息
        	queryGradingEditAudit: function(_self){
        		ajaxMethod(_self,"post",
        				"system/queryGradingEditAudit",false,
        				JSON.stringify(this.formData),"json",
        				'application/json;charset=UTF-8', _self.queryGradingEditAuditSuccess);
        	},
        	queryGradingEditAuditSuccess: function(_self,responseData){
            _self.result = responseData;
        	},
        	
        	//提交变更审核
        	saveGradChangeCheck: function(_self){
        		var selectRes=document.getElementById('selectResult').value;
        		var scoreCheckChangeReason=document.getElementById('textArea').value;
        		var dataparmars={
        				"fkSystemId": smccId,
        				"scoreCheckChangeResult":selectRes,
        				"scoreCheckChangeReason":scoreCheckChangeReason,
        		};
        		ajaxMethod(_self,"post",
        				"/checkController/saveGradChangeCheck",false,
        				JSON.stringify(dataparmars),"json",
        				'application/json;charset=UTF-8', _self.saveGradChangeCheckSuccess);
        	},
        	saveGradChangeCheckSuccess: function(_self,responseData){
            window.location.href="/page/auditPage";
        	},
        	
          text:function(){
            $('#textArea').on("keyup",function(){
              $('#textNum').text($('#textArea').val().length);//键盘按下时，实时的显示字数
              data.textArea=$('#textArea').val();
              if($('#textArea').val().length > 200){
                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
              }
            })
          },
        },
        mounted: function() {
        	var _self=this;
        	//点击提交按钮 发送请求
        	bus.$on("gradSubmit",function(meg){
        		if(meg!=null){
        			_self.saveGradChangeCheck(_self);
        		}
        	}),
        	//点击返回按钮 返回到审核页面
          bus.$on("gradReturn",function(meg){
          	if(meg!=null){
          		window.location.href="/page/auditPage";
          	}
          })
        }
      })
    })
  })
}())