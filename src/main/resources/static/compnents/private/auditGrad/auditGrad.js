
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
    ],
  };
  Vue.component('auditGrad',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditGrad/auditGrad.html').then(function (res) {
      resolve({
        template:res,
        data: function () {
          return data;
        },
        created: function(){
        	this.formData.fkSystemId=systemId;
        },
        methods:{
        	saveGradCheck: function(_self){
        		console.log(JSON.stringify(this.formData));
						ajaxMethod(_self,"post",
							 "checkController/saveGradCheck",false,
							 JSON.stringify(this.formData),"json",
							 'application/json;charset=UTF-8', _self.saveGradCheckSuccess);
          },
          saveGradCheckSuccess: function(_self,response){
        		window.location.href="/page/auditPage";
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
        },
        
        mounted: function() {
        	var _self=this;
          //点击提交按钮 发送请求 
          bus.$on("gradSubmit",function(meg){
            if(meg!=null){
              _self.saveGradCheck(_self);
            }
          })
          //点击返回按钮 返回到审核管理页面
          bus.$on("gradReturn",function(meg){
          	if(meg!=null){
          		window.location.href=originUrl+"page/auditPage";
          	}
          })
        }
      })
    })
  })
}())