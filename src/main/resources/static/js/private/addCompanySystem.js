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
          saveSystemToSession:function(formName){
          	//新的上一页，点击没有验证，直接过，存入session，下一步回显
          	ajaxMethod(this, 'post',
                'system/saveSystemSession', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.saveSystemSessionSuccess);
          },
          saveSystemSessionSuccess:function(_self,responseData){
          	if(responseData.data!=''&&responseData.data!=null&&responseData.data!='undefined'){
              if(data.headquarters){
               this.jurisdiction = 'headquarters';
                  window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode+"&jurisdiction="+this.jurisdiction;
              }
              if(data.enterprise){
                  window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
              }
             }
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
          	var systemId = responseData.data;
          	if(systemId){
          		if(data.headquarters){
          			this.jurisdiction = 'headquarters';
          			if(boo){
          				data.check = false;
          				window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode+"&jurisdiction="+this.jurisdiction;
          			}else{
          				//$(".startBox").show().delay(2000).fadeOut();
          				window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode+"&jurisdiction="+this.jurisdiction+"&systemId="+systemId;
          				
          			}
          		}
          		if(data.enterprise){
          			if(boo){
          				data.check = false;
          				window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
          			}else{
          				window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode+"&systemId="+systemId;
          			}
          		}
          	}else{
          		//提示系统已经存在
          		$("#startBoxExistSystem").show().delay(2000).fadeOut();
          	}
          },
          removeSuccess:function(){
          },
          //下一页
          nextBtn:function(formName) {
            bus.$emit('addNextSystemName',formName);
          },
          // 获取系统信息成功
          nextBtnSuccessMethod : function(_self, responseData) {
          	if(responseData.data){
          		ajaxMethod(this, 'post',
          				'main/removeSystemSession', true,JSON.stringify(''), 'json',
          				'application/json;charset=UTF-8',this.removeSuccess);
          		window.location.href = originUrl+"page/addCompanyGradPage?systemId="+responseData.data+"&fkCompanyCode="+companyCode+"&companyId="+companyId;
          	}else{
          		//提示系统已经存在
          		$("#startBoxExistSystem").show().delay(2000).fadeOut();
          	}
          },
          
          saveSystemInfoSuccessMethod :function(_self, responseData){
            window.location.href = originUrl + "page/mainCompanyInfoPage?activeName=second";
          },
          saveSystemInfo:function (formName){
          	this.closeDialog();
          	data.check=false;
            bus.$emit('mainSaveSystem',formName);
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
          	bus.$emit('returnIndexPage',"");
          	if(this.returnIndexFlag){
          		//页面的值没有改变
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
          
          //确定保存
          returnSave:function(formName){
          	bus.$emit('returnSave',formName);
          	this.check = false;
          },
          returnSaveAjaxSuccess:function(_self, responseData){
          	window.location.href = originUrl+"page/indexPage";
          },
          //取消保存
          cancelSave:function(){
          	this.check = false;
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
          //功能权限
          $.ajax({
            type: "get",
            url : originUrl+"/jurisdiction/queryMenuJurisdictionApi", 
            async: true,
            data: "",
            dataType: "json",
            cache: false,
            processData: false,
            contentType: false,
            success: function(response) {
            	//维护单位信息功能权限
            	data.maintain = getJurisdictionMethod(response,'0102010106');
            	//审核管理权限
            	data.auditManagement = getJurisdictionMethod(response,'0102010105');
            	//一键导出权限
            	data.oneKeyExportJurisdiction = getJurisdictionMethod(response,'0102010104');
            	//新建权限
            	if(getJurisdictionMethod(response,'0102010101') == true || getJurisdictionMethod(response,'0102010108') ==true){
            		data.newlyBuild = true;
            	}
            	//定级模板导入权限
            	data.templateImport = getJurisdictionMethod(response,'0102010103');
            	//定级模板导出权限
            	data.templateExport = getJurisdictionMethod(response,'0102010102');	
            	//总部新建
            	data.headquarters = getJurisdictionMethod(response,'0102010101');
            	//企业新建
            	data.enterprise = getJurisdictionMethod(response,'0102010108');
            },
            error: function(err) {
            }
          });
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
          bus.$on('returnSaveAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              if(systemId!=''&&systemId!=null){
                ajaxMethod(_self, 'post',
                    'system/editSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.returnSaveAjaxSuccess);
              }else{
                ajaxMethod(_self, 'post',
                    'system/saveSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.returnSaveAjaxSuccess);
              }
            }
          });
          
          bus.$on('mainSaveSystemAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              if(systemId!=''&&systemId!=null){
                ajaxMethod(_self, 'post',
                    'system/editSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.saveSystemInfoSuccessMethod);
              }else{
                ajaxMethod(_self, 'post',
                    'system/saveSystem', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.saveSystemInfoSuccessMethod);
              }
            }
          });
        }
    })
}