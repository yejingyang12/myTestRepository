/**
 * Created by timha on 2018/6/1.
 */
window.onload = function () {
		var dialogShow = {
				saveDialog: false,
				saveSuccessDialog: false,
				saveFailDialog: false,
				cleanDialog: false,
				cleanSuccessDialog: false,
				cleanFailDialog: false,
		};
	
    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods:{
          saveBtn:function(formName) {
            data.formData.changeType = "2";
            if(systemId!=''&&systemId!=null){
              ajaxMethod(this, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveBtnSuccessMethod);
            }else{
              ajaxMethod(this, 'post',
                  'system/saveSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveBtnSuccessMethod);
            }
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
            data.check = false;
            bus.$emit('addPreSystemName',formName);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
            }else{
              $(".startBox").show().delay(2000).fadeOut();
              window.setTimeout(function () {
                window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
              }, 2300);
            }
          },
          //下一页
          nextBtn:function(formName) {
            bus.$emit('addNextSystemName',formName);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
            window.location.href = originUrl+"page/addCompanyGradPage?systemId="+responseData.data+"&fkCompanyCode="+companyCode+"&companyId="+companyId;
          },
          
          saveSystemInfoSuccessMethod :function(_self, responseData){
            window.location.href = originUrl + "page/mainCompanyInfoPage?activeName=second";
          },
          saveSystemInfo:function (formName){
          	this.closeDialog();
          	data.check=false;
            bus.$emit('addPreSystemName',formName);
          },
          cleanSystemInfo:function (){
          	this.closeDialog();
            data.formData={
                systemId:data.formData.systemId,
                companyId:data.formData.companyId,
                fkInfoSysTypeCon:data.formData.fkInfoSysTypeCon,
                fkSystemIsMerge:data.formData.fkSystemIsMerge,
                systemName:data.formData.systemName,
                standardizedCode:data.formData.standardizedCode,
                gradeRecordSysName:"",
                sysBusSituationType:"",
                sysBusDescription:"",
                sysServiceSitScope:"",
                sysServiceSitObject:"",
                npCoverageRange:"",
                npNetworkProperties:"",
                interconnectionSit:"",
                productsNumber:"",
                fkNationalIsProducts:"",
                fkResponsibleType:"",
                fkCompanyCode:"",
                executiveOfficeName:"",
                executiveDireCon:"",
                executiveDireConTel:"",
                whenInvestmentUse:"",
                subIsSystem:"",
                companyName:data.formData.companyName,
                fkComCode:"",
                changeType:"",
                stars:"1",
                aa:"1",
                systemNameSon:data.formData.systemNameSon,
                systemCodeSon:data.formData.systemCodeSon,
                addSystemSubSon:data.formData.addSystemSubSon,
                addSystemSub:data.formData.addSystemSub,
                systemKeyProducts:[{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                },{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                },{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                },{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                },{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                },{
                  fkNationalIsProducts:"",
                  fkExaminStatus:"",
                  productsNumber:"",
                  nUseProbability:0,
                  otherName:""
                }],
                systemUseServices:[{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                },{
                  fkResponsibleType:"",
                  fkProductsType:"",
                  fkSystemId:"",
                  serviceIsUse:"",
                  otherName:""
                }],
                systemKeyProductsNumber:'',
                systemKeyFkNationalIsProducts:'',
                systemKeyNUseProbability:'',
                systemKeyOtherName:'',
              };
            $(".baseMes1").find(".btnColor").removeClass("btnColor");
            var array = $('#baseMes1').find('div').map(function (index, ele) {
              if(data.formData.fkInfoSysTypeCon==1&&ele.innerHTML=='自建'){
                return ele;
              }else if(data.formData.fkInfoSysTypeCon==2&&ele.innerHTML=='统建'){
                return ele;
              }else if(data.formData.fkInfoSysTypeCon==3&&ele.innerHTML=='总部系统'){
                return ele;
              }else{
                 return "";
              }
            }).get();
            $(array).addClass('btnColor');
            var array2 = $('#baseMes2').find('div').map(function (index, ele) {
              if(data.formData.fkSystemIsMerge==1&&ele.innerHTML=='是'){
                return ele;
              }else if(data.formData.fkSystemIsMerge==2&&ele.innerHTML=='否'){
                return ele;
              }else{
                 return "";
              }
            }).get();
            $(array2).addClass('btnColor');
          },
          returnSystemList:function (){
            window.location.href = originUrl + "page/mainCompanyInfoPage?activeName=second";
          },
          //返回
          returnBtn:function() {
            window.location.href = originUrl+"page/indexPage";
          },
          
          //显示弹窗
          openSaveSystemInfo:function(){
          	$("#saveSystemInfoDialog").css("display","block");
          	$("#saveSystemInfoDialogShaw").css("display","block");
          },
          openCleanSystemInfo:function(){
          	$("#cleanSystemInfoDialog").css("display","block");
          	$("#cleanSystemInfoDialogShaw").css("display","block");
          },
          closeDialog:function(){
          	$("#saveSystemInfoDialog").css("display","none");
          	$("#cleanSystemInfoDialog").css("display","none");
          	$("#saveSystemInfoDialogShaw").css("display","none");
          	$("#cleanSystemInfoDialogShaw").css("display","none");
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('addPreSystemAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              if(systemId!=''&&systemId!=null){
                ajaxMethod(_self, 'post',
                    'system/editSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.preBtnSuccessMethod);
              }else{
                ajaxMethod(_self, 'post',
                    'system/saveSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.preBtnSuccessMethod);
              }
            }
          });
          bus.$on('addNextSystemAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              if(systemId!=''&&systemId!=null){
                ajaxMethod(_self, 'post',
                    'system/editSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.nextBtnSuccessMethod);
              }else{
                ajaxMethod(_self, 'post',
                    'system/saveSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.nextBtnSuccessMethod);
              }
            }
          });
        }
    })
}