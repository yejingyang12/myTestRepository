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
          saveSystemInfo:function(formName) {
            bus.$emit('addPreSystemName',formName);
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            this.$message({
              message: '保存成功！',
              type: 'success'
            });
            window.location.href = originUrl + "page/mainCompanyInfoPage";
          },
          
          cleanSystemInfo:function (){
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
            window.location.href = originUrl + "page/mainCompanyInfoPage";
          }
        },
        mounted : function() {
          var _self = this;
          bus.$on('addPreSystemAjax',function(meg){
            data.formData.fkComCode = 2;
            if(meg!=null){
              data.formData.changeType = "2";
              if(systemId!=''&&systemId!=null){
                ajaxMethod(_self, 'post',
                    'system/editSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.saveBtnSuccessMethod);
              }else{
                ajaxMethod(_self, 'post',
                    'system/saveSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.saveBtnSuccessMethod);
              }
            }
          });
          
        }
    })
}