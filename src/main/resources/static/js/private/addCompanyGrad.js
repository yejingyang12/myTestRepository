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
            bus.$emit('addGradName',formName);
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            this.$message({
              message: '保存成功！',
              type: 'success'
            });
            window.location.href = originUrl+"page/indexPage";
          },
          //提交
          submitBtn:function(formName) {
            bus.$emit('addSubmitGradName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/indexPage";
          },
          //上一页
          preBtn:function(formName) {
            bus.$emit('addPreGradName',formName);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId;
          },
          //下一页
          next1Btn:function(formName) {
            bus.$emit('addNextGradName',formName);
          },
          // 获取成功
          nextBtn2SuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            window.location.href = originUrl+"page/addCompanyMaterialPage?systemId="+systemId;
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          }
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
              ajaxMethod(_self, 'post',
                  'grading/submitGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.submitBtnSuccessMethod);
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