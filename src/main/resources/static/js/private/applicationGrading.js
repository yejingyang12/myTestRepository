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
            bus.$emit('gradGradingName',formName);
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
            bus.$emit('gradSubmitGradingName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            $(".startBox").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('gradGradingAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('gradSubmitGradingAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/submitGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.submitBtnSuccessMethod);
            }
          });
        }
    })
}