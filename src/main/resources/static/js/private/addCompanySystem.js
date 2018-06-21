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
          saveBtn:function() {
            data.formData.changeType = "2";
            ajaxMethod(this, 'post',
                'system/saveSystem', true,
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
          preBtn:function() {
            data.formData.changeType = "2";
            if(systemId!=''&&systemId!=null){
              ajaxMethod(this, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.preBtnSuccessMethod);
            }else{
              ajaxMethod(this, 'post',
                  'system/saveSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.preBtnSuccessMethod);
            }
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
          },
          //下一页
          nextBtn:function() {
            data.formData.changeType = "2";
            if(systemId!=''&&systemId!=null){
              ajaxMethod(this, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.nextBtnSuccessMethod);
            }else{
              ajaxMethod(this, 'post',
                  'system/saveSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.nextBtnSuccessMethod);
            }
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/addCompanyGradPage?systemId="+responseData.data;
          }
        }
    })
}