/**
 * Created by timha on 2018/6/4.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data
        },
        methods:{
          //保存
          saveBtn:function() {
            data.formData.changeType = "2";
            ajaxMethod(this, 'post',
                'grading/saveGrading', true,
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
          //提交
          submitBtn:function() {
            data.formData.changeType = "2";
            ajaxMethod(this, 'post',
                'grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.submitBtnSuccessMethod);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/indexPage";
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          }
        }
    })
}