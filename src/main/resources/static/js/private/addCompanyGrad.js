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
            data.formData.gradingId = responseData.data;
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
                'grading/submitGrading', true,
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
            data.formData.changeType = "2";
            ajaxMethod(this, 'post',
                'grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.preBtnSuccessMethod);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId;
          },
          //下一页
          next1Btn:function() {
            data.formData.changeType = "2";
            ajaxMethod(this, 'post',
                'grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.nextBtn2SuccessMethod);
          },
          // 获取成功
          nextBtn2SuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            window.location.href = originUrl+"page/addCompanyMaterialPage?systemId="+systemId;
          }
        }
    })
}