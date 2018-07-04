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
          saveBtn:function() {
            data.formData.changeType = "1";
            ajaxMethod(this, 'post',
                'system/editSystem', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.saveBtnSuccessMethod);
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            this.$message({
              message: '保存成功！',
              type: 'success'
            });
            window.location.href = originUrl+"page/indexPage";
          },
          //上一页
          preBtn:function(formName) {
            bus.$emit('changePreSystemName',formName);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangePage?companyCode="+companyCode;
          },
          //下一页
          nextBtn:function(formName) {
            bus.$emit('changeNextSystemName',formName);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+responseData.data;
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('changePreSystemAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
          bus.$on('changeNextSystemAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "1";
              ajaxMethod(_self, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtnSuccessMethod);
            }
          });
        }
    })
}