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
        "recordsId": "131221321323",//撤销备案的备案id
        "fkSystemId": "495c3fe27e0b4f038e71e2c77dd086f4",//撤销备案的系统id
        "fkrevokematter": "变更事项",//变更事项
        "revokereason": "撤销原因",//撤销原因
        "revokeRecordsId": null,//撤销证明附件id
        "revokeRecordsName": "撤销证明附件"//撤销证明附件名称
      }
    }
  };
  Vue.component('auditCancel',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditCancel/auditCancel.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created:function(){
          this.queryRevokeRecords(this);
        },
        methods:{
        	//获取撤销信息
        	queryRevokeRecords: function(_self){
        		var queryRevokeRecordsParam = {"fkSystemId": smccId};
        		ajaxMethod(_self,"post",
            		"/records/queryRevokeRecords",false,
            		JSON.stringify(queryRevokeRecordsParam),"json",
            		'application/json;charset=UTF-8', _self.queryRevokeRecordsSuccess);
        	},
        	queryRevokeRecordsSuccess: function(_self,responseData){
        		_self.result = responseData;
          },
        	
        	//提交定级审核
        	saveCancelRecordsCheck: function(_self){
        		var selectRes=document.getElementById('selectResult').value;
        		var cancelRecordsReason=document.getElementById('textArea').value;
            var dataparmars={
          		"recordsId": _self.result.recordsId,
              "fkSystemId": smccId,//系统id
              "cancelRecordsResult":selectRes,//撤销备案的结果
              "cancelRecordsReason":cancelRecordsReason,//撤销备案的原因
            };
            ajaxMethod(_self,"post",
            		"/checkController/saveCancelRecordsCheck",false,
            		JSON.stringify(dataparmars),"json",
            		'application/json;charset=UTF-8', _self.saveCancelRecordsCheckSuccess);
        	},
        	saveCancelRecordsCheckSuccess: function(_self,response){
        		window.location.href=originUrl+encodeURI("/page/auditPage");
          },
          
          //审核结果：对输入的内容进行字数显示
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
              console.log(meg);
              _self.saveCancelRecordsCheck(_self);
            }
          }),
          //点击返回按钮 返回到审核管理页面
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
              console.log(meg);
              window.location.href=originUrl+encodeURI("/page/auditPage");
            }
          })
        }
      })
    })
  })
}())