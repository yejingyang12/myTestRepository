(function () {
  var data = {
    textArea:null,
    result:{
      "code": "0",
      "msg": "成功",
      "pagesize": 0,
      "currentPage": 0,
      "total": 0,
      "totalPages": 0,
      "data":
        {
        "systemId": null,
        "fkChangeMatter": "系统合并",
        "changeContent": "系统的变更内容",
        "changeReason": "系统的变更原因"
      }
    }
  };
  Vue.component('auditChange',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditChange/auditChange.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
        	//获取变更信息
        	queryGradingEditAudit: function(_self){
        		var queryGradingEditAuditParam = {"systemId":smccId};
        		ajaxMethod(_self,"post",
        				"/system/queryGradingEditAudit",false,
        				JSON.stringify(queryGradingEditAuditParam),"json",
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
        created:function(){
        	this.queryGradingEditAudit(this);
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