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
          },
          
          saveSystemInfoSuccessMethod :function(_self, responseData){
          	window.location.href = originUrl + "page/mainCompanyInfoPage";
          },
          saveSystemInfo:function (){
						//bus.$emit("saveSystemInfo","1");
          	if(systemId!=''&&systemId!=null){
              ajaxMethod(this, 'post',
                  'system/editSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveSystemInfoSuccessMethod);
            }else{
              ajaxMethod(this, 'post',
                  'system/saveSystem', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  this.saveSystemInfoSuccessMethod);
            }
					},
					cleanSystemInfo:function (){
						//bus.$emit("cleanSystemInfo","1");
						data.formData={
                systemId:"",
                companyId:"",
                fkInfoSysTypeCon:"",
                fkSystemIsMerge:"",
                systemName:"",
                standardizedCode:"",
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
                companyName:"",
                fkComCode:"",
                changeType:"",
                addSystemSub:[{
                  systemName:"",
                  standardizedCode:""
                },
                {
                  systemName:"",
                  standardizedCode:""
                }],
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
                }]
              };
						$(".baseMes1").find(".btnColor").removeClass("btnColor");
					},
					returnSystemList:function (){
						//bus.$emit("returnSystemList","1");
						window.location.href = originUrl + "page/mainCompanyInfoPage";
					},
        },
        //返回
        returnBtn:function() {
          window.location.href = originUrl+"page/indexPage";
        }
    })
}