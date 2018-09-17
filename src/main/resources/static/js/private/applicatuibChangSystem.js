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
          preBtn:function() {
          	var systemTemp = JSON.stringify(data.formData);
          	ajaxMethod(this, 'post',
                'system/saveSystemSession', true,
                systemTemp, 'json',
                'application/json;charset=UTF-8',
                this.saveSystemTempSession);
          },
          saveSystemTempSession:function(_self, responseData){
          	if(responseData.data!=null){
          		window.location.href = originUrl+"/page/applicationChangePage?fkCompanyCode="+companyCode+"&theLastStep=true&systemId="+systemId;
          	}
          },
          saveSystemSessionSuccess:function(_self,responseData){
          	if(responseData.data!=null){
              window.location.href = originUrl+"/page/applicationChangeGradPage?systemId="+systemId+"&fkCompanyCode="+companyCode;
          	}
          },
          // 获取系统信息成功
//          preBtnSuccessMethod : function(_self, responseData) {
//              window.location.href = originUrl+"page/applicationChangePage?fkCompanyCode="+companyCode+"&theLastStep=true&systemId="+systemId;
//          },
          //下一页
          nextBtn:function(formName) {
            bus.$emit('changeNextSystemName',formName);
          },
          //返回
        //返回
          returnBtn:function() {
            this.returnIndex = true;
          },
          returnIndexMethod:function(){
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSessionSuccess);
            window.location.href = originUrl+"page/indexPage";
          },
          removeSessionSuccess:function(){
          	
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/applicationChangeGradPage?systemId="+responseData.data+"&fkCompanyCode="+companyCode;
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
          
          bus.$on('toGradPage',function(meg){
        		if(meg!=null){
        			ajaxMethod(_self, 'post',
                  'system/saveSystemSession', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveSystemSessionSuccess);
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