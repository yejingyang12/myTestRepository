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
          	var flag = true;
          	var beginContent = this.beginContent;
        		var currentContent = this.formData;
        		if(beginContent.companyName != currentContent.companyName){//单位名称
        			flag = false;
        		}
        		if(beginContent.companyCode != currentContent.companyCode){//单位编码
        			flag = false;
        		}
        		if(beginContent.companyAddress != currentContent.companyAddress){//单位地址
        			flag = false;
        		}
        		if(beginContent.postalCode != currentContent.postalCode){//邮政编码
        			flag = false;
        		}
        		if(beginContent.fkSubordinatePro != currentContent.fkSubordinatePro){//所属省份
        			flag = false;
        		}
        		if(beginContent.administrativeNum != currentContent.administrativeNum){//行政区划编码
        			flag = false;
        		}
        		if(beginContent.compPrincipalName != currentContent.compPrincipalName){//单位负责人姓名
        			flag = false;
        		}
        		if(beginContent.compPrincipalWorkTel != currentContent.compPrincipalWorkTel){//办公电话
        			flag = false;
        		}
        		if(beginContent.compPrincipalPhone != currentContent.compPrincipalPhone){//移动电话
        			flag = false;
        		}
        		if(beginContent.compPrincipalPost != currentContent.compPrincipalPost){//职务职称
        			flag = false;
        		}
        		if(beginContent.compPrincipalEm != currentContent.compPrincipalEm){//电子邮件
        			flag = false;
        		}
        		if(beginContent.ldContactName != currentContent.ldContactName){//责任部门联系人姓名
        			flag = false;
        		}
        		if(beginContent.ldContactWorkTel != currentContent.ldContactWorkTel){//办公电话
        			flag = false;
        		}
        		if(beginContent.ldContactPhone != currentContent.ldContactPhone){//移动电话
        			flag = false;
        		}
        		if(beginContent.ldContactPost != currentContent.ldContactPost){//职务职称
        			flag = false;
        		}
        		if(beginContent.ldContactEmail != currentContent.ldContactEmail){//电子邮件
        			flag = false;
        		}
        		if(beginContent.rDepartment != currentContent.rDepartment){//责任部门
        			flag = false;
        		}
        		if(beginContent.gpReportingComp != currentContent.gpReportingComp){//等保上报单位名称
        			flag = false;
        		}
        		if(beginContent.fkIndustryCategory != currentContent.fkIndustryCategory){//行业类别
        			flag = false;
        		}
        		if(beginContent.fkAffiliation != currentContent.fkAffiliation){//隶属关系
        			flag = false;
        		}
        		if(beginContent.fkCompanyType != currentContent.fkCompanyType){//单位类型
        			flag = false;
        		}
        		if(beginContent.fkPlateType != currentContent.fkPlateType){//板块类型
        			flag = false;
        		}
        		if(flag){
        			//没有改变
        			window.location.href = originUrl+"page/indexPage";
        		}else{
        			this.check = true;
        		}
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