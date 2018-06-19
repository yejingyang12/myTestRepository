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
          //保存
          saveBtn:function() {
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
          //上一页
          preBtn:function() {
            ajaxMethod(this, 'post',
                'grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.preBtnSuccessMethod);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicatuibChangSystemPage?systemId="+systemId;
          },
          //下一页
          nextBtn1:function() {
            ajaxMethod(this, 'post','grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.nextBtnSuccessMethod);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationChangeMaterialPage?systemId="+systemId;
          },
        }
    })
}