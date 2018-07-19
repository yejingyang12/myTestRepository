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
          //保存
          saveBtn:function(formName) {
            bus.$emit('gradMaterialFormName',formName);
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            $(".startBox").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('gradSubmitMaterialFormName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            $(".startBox").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //上一页
          preBtn:function(formName) {
            bus.$emit('gradPreMaterialFormName',formName);
          },
          // 成功
          preBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+systemId;
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('gradMaterialFormAjax',function(meg){
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
          bus.$on('gradSubmitMaterialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              data.formData.saveType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveSystemMaterialsInfo', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.submitBtnSuccessMethod);
            }
          });
          bus.$on('gradPreMaterialFormAjax',function(meg){
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