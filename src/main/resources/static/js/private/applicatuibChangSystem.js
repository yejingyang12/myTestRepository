/**
 * Created by timha on 2018/6/4.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return{

            }
        },
        methods:{
          saveBtn:function() {
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
            ajaxMethod(this, 'post',
                'system/saveSystem', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.preBtnSuccessMethod);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangePage?companyCode="+companyCode;
          },
          //下一页
          nextBtn:function() {
            ajaxMethod(this, 'post',
                'system/saveSystem', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.nextBtnSuccessMethod);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+responseData.data;
          }
        }
    })
}