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
            bus.$emit('changeGradName',formName);
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
            bus.$emit('changeSubmitGradName',formName);
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
            data.check = false;
            bus.$emit('changePreGradName',formName);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId;
            }else{
              $(".startBox").show().delay(2000).fadeOut();
              window.setTimeout(function () {
                window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
              }, 2300);
            }
          },
          //下一页
          nextBtn1:function(formName) {
            bus.$emit('changeNextGradName',formName);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangeMaterialPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('changeGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('changeSubmitGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
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
          bus.$on('changePreGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
          bus.$on('changeNextGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post','grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtnSuccessMethod);
            }
          });
        }
    })
}