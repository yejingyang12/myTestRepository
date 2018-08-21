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
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
          	$(".save").show().delay(2000).fadeOut();
            //$(".startBox").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('addSubmitMaterialFormName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            //$(".startBox").show().delay(2000).fadeOut();
          	$(".submit").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //上一页
          preBtn:function(formName) {
            data.check = false;
            bus.$emit('addPreMaterialFormName',formName);
          },
          // 成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
          	if(_self.formData.saveType == "1"){
          		$(".save").show().delay(2000).fadeOut();
          	}else if(_self.formData.saveType == "2"){
          		$(".submit").show().delay(2000).fadeOut();
          	}
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/addCompanyGradPage?systemId="+systemId;
            }else{
              window.setTimeout(function () {
                window.location.href = originUrl+"page/addCompanyGradPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
              }, 2300);
            }
          },
          //返回
          returnBtn:function() {
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
        }
    })
}