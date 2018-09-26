/**
 * Created by timha on 2018/5/23.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods : {
          submitHandler:function(formName){
            bus.$emit('formName',formName);
          },
          submitHandlerSuccessMethod: function(_self,returnData){
          	if(systemId!='' && systemId!='undefind' && systemId!=null){
          		window.location.href = originUrl+"page/addCompanySystemPage?companyId=" + returnData.data+"&companyCode="+data.formData.companyCode+"&systemId="+systemId;
          	}else{
          		window.location.href = originUrl+"page/addCompanySystemPage?companyId=" + returnData.data+"&companyCode="+data.formData.companyCode;
          	}
          },
          //返回,返回时判断当前页有没有被改变 ，如果有就弹窗，没有直接返回
          returnBtn:function() {
          	bus.$emit('returnIndexPage',"");
        		if(this.returnIndexFlag){
        			//没有改变
        			window.location.href = originUrl+"page/indexPage";
        		}else{
        			this.returnIndex = true;
        		}
          },
          returnIndexMethod:function(){
          	ajaxMethod(this, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',this.removeSessionSuccess);
          },
          removeSessionSuccess:function(){
          	window.location.href = originUrl+"page/indexPage";
          },
          //确认保存
          returnSave:function(formName){
              bus.$emit('returnSave',formName);
              this.check=false;
          },
          //取消保存
          cancelSave:function(){
          	this.check = false;
          	window.location.href = originUrl+"page/indexPage";
          },
          returnSaveToIndex:function(_self,returnData){
          	window.location.href = originUrl+"page/indexPage";
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('addCompany',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',_self.submitHandlerSuccessMethod);
            }
          });
          bus.$on('returnSaveToIndex',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'company/saveCompany', true,JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',_self.returnSaveToIndex);
            }
          });
        }
    })
}