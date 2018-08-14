  var data = {
  		disabledInput:false,
      directive:false,
      check : false,
      dtlCompanyCode:"",
      formData:{
        companyName:"",
        companyTypeName:"",
        ldContactName:"",
        companyCode:"",
        companyAddress:"",
        postalCode:"",
        administrativeNum:"",
        compPrincipalName:"",
        compPrincipalPost:"",
        compPrincipalWorkTel:"",
        compPrincipalEm:"",
        compPrincipalPhone:"",
        ldContactPost:"",
        ldContactWorkTel:"",
        ldContactEmail:"",
        ldContactPhone:"",
        rDepartment:"",
        gpReportingComp:"",
        fkSubordinatePro:"",
        fkIndustryCategory:"",
        fkAffiliation:"",
        fkPlateType:"",
        fkCompanyType:"",
        companyId:"",
        changeType:"",
        systemId:"",
        jurisdiction:""
      },
      msgName : [],// 单位名称
      msgProvince : [],// 所属省份
      msgaAffi : [],// 隶属关系
      msgUintType : [],// 单位类型
      msgPlate : [],// 板块类型
      selectedOptions:[],
      transmitParam:[],
      nameReadonly:false,
      rules:{
          companyName: [
              { required: true, message: '请选择单位名称', trigger: 'change' },
          ],
          companyCode: [
              { required: true, message: '请输入单位编码', trigger: 'blur' }
          ],
          companyAddress: [
              { required: false, message: '请输入单位地址', trigger: 'blur' },
              { min: 0, max: 400, message: '长度在 0 到 400 个字符', trigger: 'blur' }
          ],
          postalCode: [
              { required: false, message: '请输入邮政编码', trigger: 'blur' },
              { pattern: /^\d{6}$/, message: '邮政编码输入有误', trigger: 'blur'}
          ],
          fkSubordinatePro: [
              { required: true, message: '请选择所属省份', trigger: 'blur' }
          ],
          administrativeNum: [
              { required: false, message: '请输入行政区划编码', trigger: 'blur' },
              { pattern: /^\d{0,10}$/, message: '行政区划编码输入有误', trigger: 'blur'}
          ],
          compPrincipalName: [
              { required: false, message: '请输入单位负责人姓名', trigger: 'blur' },
              { min: 0, max: 40, message: '长度在 0 到 40 个字符', trigger: 'blur' }
          ],
          compPrincipalWorkTel: [
              { required: false, message: '请输入办公电话', trigger: 'blur' },
              { min: 7, max: 13, message: '长度在7 到 13个字符', trigger: 'blur' },
              { pattern: /^([\d-+]*)$/, message: '办公电话输入有误', trigger: 'blur'}
          ],
          compPrincipalPhone: [
              { required: false, message: '请输入移动电话', trigger: 'blur' },
              { pattern: /^\d{11}$/, message: '移动电话输入有误', trigger: 'blur'}
          ],
          compPrincipalPost: [
              { required: false, message: '请输入职务/职称', trigger: 'blur' },
              { min: 0, max: 40, message: '长度在 0 到40个字符', trigger: 'blur' }
          ],
          compPrincipalEm: [
              { required: false, message: '请输入电子邮件', trigger: 'blur' },
              { min: 0, max: 50, message: '长度在  1 到50个字符', trigger: 'blur' },
              { pattern: /^([a-zA-Z0-9._-])+\@+[0-9a-zA-Z]*\.(com|com.cn|edu|hk|cn|net)$/, message: '电子邮件输入有误', trigger: 'blur'}
          ],
          ldContactName: [
              { required: true, message: '请输入责任部门联系人姓名', trigger: 'blur' },
              { min: 1, max: 40, message: '长度在  1 到40个字符', trigger: 'blur' }
          ],
          ldContactWorkTel: [
              { required: true, message: '请输入办公电话', trigger: 'blur' },
              { min: 7, max: 13, message: '长度在7 到 13个字符', trigger: 'blur' },
              { pattern: /^([\d-+]*)$/, message: '办公电话输入有误', trigger: 'blur'}
          ],
          ldContactPhone: [
              { required: true, message: '请输入移动电话', trigger: 'blur' },
              { pattern: /^\d{11}$/, message: '移动电话输入有误', trigger: 'blur'}
          ],
          ldContactPost: [
              { required: true, message: '请输入职务/职称', trigger: 'blur' },
              { min: 1, max: 40, message: '长度在 1 到40个字符', trigger: 'blur' }
          ],
          ldContactEmail: [
              { required: true, message: '请输入电子邮件', trigger: 'blur' },
              { min: 1, max: 50, message: '长度在  1 到50个字符', trigger: 'blur' },
              { pattern: /^([a-zA-Z0-9._-])+\@+[0-9a-zA-Z]*\.(com|com.cn|edu|hk|cn|net)$/, message: '电子邮件输入有误', trigger: 'blur'}
          ],
          rDepartment: [
              { required: true, message: '请输入责任部门', trigger: 'blur' },
              { min: 1, max: 60, message: '长度在  1 到60个字符', trigger: 'blur' }
          ],
          gpReportingComp: [
              { required: true, message: '请输入等保上报单位名称', trigger: 'blur' },
              { min: 1, max: 60, message: '长度在  1 到60个字符', trigger: 'blur' }
          ],
          fkIndustryCategory: [
               { required: true, message: '请输入行业类别', trigger: 'change' }
          ],
          fkAffiliation: [
               { required: true, message: '请选择隶属关系', trigger: 'change' }
          ],
          fkCompanyType: [
               { required: true, message: '请选择单位类型', trigger: 'change' }
          ],
          fkPlateType: [
               { required: true, message: '请选择板块类型', trigger: 'change' }
          ]
      },
  };
(function() {
  Vue.component('addForm', function(resolve, reject) {
    $.get(comp_src + '/compnents/private/addForm/addForm.html').then(
        function(res) {
          resolve({
            template:res,
            data : function() {
              return data;
            },
            methods : {
              // 点击切换 添加class名
              getClass : function(e) {
                $("#baseMes1 div div").click(function(){
                  $("#baseMes1 div div").removeClass("btnColor");
                  $(this).addClass("btnColor");
                })
                if(e.target.innerHTML.indexOf("其它") != -1){
                  this.formData.fkAffiliation = $("#affiliation").val();
                }else{
                  this.formData.fkAffiliation = e.target.innerHTML;
                }
                this.$refs.formData.validateField('fkAffiliation');
              },
              getAffiliation : function() {
                this.formData.fkAffiliation = $("#affiliation").val();
                this.$refs.formData.validateField('fkAffiliation');
              },
              
              // 点击切换 添加class名
              getUintTypeClass : function(e) {
                $("#baseMes2 div div").click(function(){
                  $("#baseMes2 div div").removeClass("btnColor");
                  $(this).addClass("btnColor");
                })
                if(e.target.innerHTML.indexOf("其它") != -1){
                  this.formData.fkCompanyType = $("#companyType").val();
                  this.$refs.formData.validateField('fkCompanyType');
                }else{
                  this.formData.fkCompanyType = e.target.innerHTML;
                  this.$refs.formData.validateField('fkCompanyType');
                }
              },
              getCompanyType : function() {
                this.formData.fkCompanyType = $("#companyType").val();
                this.$refs.formData.validateField('fkCompanyType');
              },
              // 点击切换 添加class名
              getPlateClass : function(e) {
                $("#baseMes3 div div").click(function(){
                  $("#baseMes3 div div").removeClass("btnColor");
                  $(this).addClass("btnColor");
                })
                if(e.target.innerHTML.indexOf("其它") != -1){
                  this.formData.fkPlateType = $("#plateType").val();
                  this.$refs.formData.validateField('fkPlateType');
                }else{
                  this.formData.fkPlateType = e.target.innerHTML;
                  this.$refs.formData.validateField('fkPlateType');
                }
              },
              getPlateType : function() {
                this.formData.fkPlateType = $("#plateType").val();
                this.$refs.formData.validateField('fkPlateType');
              },
              // 获取省份
              getProvinceMethod : function(_self) {
                ajaxMethod(_self, 'post',
                    'systemCode/querySystemCodeForKeySystemCode', true,
                    '{"codeType":"21"}', 'json',
                    'application/json;charset=UTF-8',
                    _self.getProvinceSuccessMethod);
              },
              // 获取省份成功
              getProvinceSuccessMethod : function(_self, responseData) {
                for (var i = 0; i < responseData.data.length; i++) {
                  _self.msgProvince=responseData.data;
                }
              },
              // 获取隶属关系
              getAffiliationMethod : function(_self) {
//                 ajaxMethod(_self, 'post',
//                     'systemCode/querySystemCodeForKeySystemCode', true,
//                     '{"codeType":"3"}', 'json',
//                     'application/json;charset=UTF-8',
//                     _self.getAffiliationSuccessMethod);
                var responseData='{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"中央","systemCode":"1","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"省（自治区、直辖市）","systemCode":"2","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"地（区、市、州、盟）","systemCode":"3","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"县（区、市、旗）","systemCode":"4","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"5","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
                responseData = JSON.parse(responseData);
                _self.getAffiliationSuccessMethod(_self,responseData);
                 
              },
              // 获取隶属关系成功
              getAffiliationSuccessMethod : function(_self, responseData) {
                 _self.msgaAffi = responseData.data;
                 if(_self.msgaAffi.length > 5){
                   $("#baseMes1").addClass("baseMes2");
                 }
              },
              // 获取单位类别
              getUnitTypeMethod : function(_self) {
//                ajaxMethod(_self, 'post',
//                    'systemCode/querySystemCodeForKeySystemCode', true,
//                    '{"codeType":"5"}', 'json',
//                    'application/json;charset=UTF-8',
//                    _self.getUnitTypeSuccessMethod);
                var responseData='{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"党委机关","systemCode":"1","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"政府机关","systemCode":"2","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"事业单位","systemCode":"3","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"企业","systemCode":"4","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"5","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
                responseData = JSON.parse(responseData);
                _self.getUnitTypeSuccessMethod(_self,responseData);
              },
              // 获取单位类别成功
              getUnitTypeSuccessMethod : function(_self, responseData) {
                _self.msgUintType = responseData.data;

                if(_self.msgUintType.length > 5){
                  $("#baseMes2").addClass("baseMes2");
                }
              },
              // 获取板块类型
              getPlateTypeMethod : function(_self) {
                 ajaxMethod(_self, 'post',
                     'systemCode/querySystemCodeForKeySystemCode', true,
                     '{"codeType":"6"}', 'json',
                     'application/json;charset=UTF-8',
                     _self.getPlateTypeSuccessMethod);
//                 var responseData='{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"总部部门","systemCode":"1","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"油田板块","systemCode":"2","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"炼化板块","systemCode":"3","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"销售板块","systemCode":"4","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"石油工程","systemCode":"5","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"驻外机构","systemCode":"6","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"炼化工程","systemCode":"7","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"科研单位","systemCode":"8","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"专业公司及其他","systemCode":"9","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"10","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
//                 responseData = JSON.parse(responseData);
//                 _self.getPlateTypeSuccessMethod(_self,responseData);
              },
              // 获取板块类型成功
              getPlateTypeSuccessMethod : function(_self, responseData) {
                _self.msgPlate = responseData.data;
                if(_self.msgPlate.length > 5){
                  $("#baseMes3").addClass("baseMes2");
                }
              },
              //获取单位信息
              getCompanyInfoMethod:function(_self,meg){
                ajaxMethod(_self, 'post',
                    'company/queryCompanyByCode', true,'{"companyCode":"'+meg+'"}', 'json',
                    'application/json;charset=UTF-8',_self.getCompanyInfoSuccessMethod);
              },
              getCompanyInfoByIdMethod:function(_self,meg){
                ajaxMethod(_self, 'post',
                    'company/queryEditCompany', true,'{"companyId":"'+meg+'"}', 'json',
                    'application/json;charset=UTF-8',_self.getCompanyInfoSuccessMethod);
              },
              getCompanyInfoSuccessMethod: function(_self,data){
                if(data.data!=null){
                  _self.formData = data.data;
                  if(data.data.postalCode=='0'){
                  	_self.formData.postalCode='';
                  }
                  if(data.data.administrativeNum=='0'){
                  	_self.formData.administrativeNum='';
                  }
                  if(data.data.fkAffiliation!=''){
                    var array = $('#baseMes1').find('div').map(function (index, ele) {
                      if(ele.innerHTML==data.data.fkAffiliation){
                        return ele;
                      }else{
                         return "";
                      }
                   }).get();
                    var other=true;
                    for(var i=0;i<array.length;i++){
                      if(array[i]!=''){
                        array[i].classList.add("btnColor");
                        other=false;
                      }
                    }
                    if(other){
                      $("#other1").addClass('btnColor');
                      $("#affiliation").val(data.data.fkAffiliation);
                    }
                  }
                  if(data.data.fkPlateType!=''){
                    var array = $('#baseMes3').find('div').map(function (index, ele) {
                      if(ele.innerHTML==data.data.fkPlateType){
                        return ele;
                      }else{
                        return "";
                      }
                   }).get();
                    var other=true;
                    for(var i=0;i<array.length;i++){
                      if(array[i]!=''){
                        array[i].classList.add("btnColor");
                        other=false;
                      }
                    }
                    if(other){
                      $("#other3").addClass('btnColor');
                      $("#plateType").val(data.data.fkPlateType);
                    }
                  }
                  if(data.data.fkCompanyType!=''){
                    var array = $('#baseMes2').find('div').map(function (index, ele) {
                      if(ele.innerHTML==data.data.fkCompanyType){
                        return ele;
                      }else{
                        return "";
                      }
                   }).get();
                    var other=true;
                    for(var i=0;i<array.length;i++){
                      if(array[i]!=''){
                        array[i].classList.add("btnColor");
                        other=false;
                      }
                    }
                    if(other){
                      $("#companyType").val(data.data.fkCompanyType);
                      $("#other2").addClass('btnColor');
                    }
                  }
                }else{
                  this.formData={
                      companyName:"",
                      companyTypeName:"",
                      ldContactName:"",
                      companyCode:"",
                      companyAddress:"",
                      postalCode:"",
                      administrativeNum:"",
                      compPrincipalName:"",
                      compPrincipalPost:"",
                      compPrincipalWorkTel:"",
                      compPrincipalEm:"",
                      compPrincipalPhone:"",
                      ldContactPost:"",
                      ldContactWorkTel:"",
                      ldContactEmail:"",
                      ldContactPhone:"",
                      rDepartment:"",
                      gpReportingComp:"",
                      fkSubordinatePro:"",
                      fkIndustryCategory:"",
                      fkAffiliation:"",
                      fkPlateType:"",
                      fkCompanyType:"",
                      companyId:"",
                      changeType:"",
                      systemId:""
                   };
                  //this.formData.companyName = _self.transmitParam[1];
                  this.formData.companyCode = _self.transmitParam[1];
                  var val = _self.transmitParam[1];
                  if(val!=null){
                    for(var i=0;i<this.msgName.length;i++){
                      if(val==this.msgName[i].orgCode){
                        this.formData.companyName = this.msgName[i].orgName;
                        break;
                      }
                    }
                  }
                  $("#baseMes1 div div").removeClass("btnColor");
                  $("#baseMes2 div div").removeClass("btnColor");
                  $("#baseMes3 div div").removeClass("btnColor");
                  $("#affiliation").val("");
                  $("#companyType").val("");
                  $("#plateType").val("");
                }
              },
              //获取单位信息
              getCompanyMethod:function(_self){
                ajaxMethod(_self, 'post',
                    'organizationapi/queryOrgUnitForKeyOrganizationCode', true,'{}', 'json',
                    'application/json;charset=UTF-8',_self.getCompanySuccessMethod);
              },
              getCompanySuccessMethod: function(_self,data){
                if(data.data!=null){
                  _self.msgName = data.data;
                }
              },
              submitHandler:function(formData){
              	var _self = this;
              	if(formData!=null){
                  _self.$refs[formData].validate(function (valid) {
                    if (valid) {
                    	ajaxMethod(this, 'post',
                          'company/saveCompany', true,JSON.stringify(_self.formData), 'json',
                          'application/json;charset=UTF-8',this.submitHandlerSuccessMethod);
                    } else {
                      _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                        confirmButtonText: '确定',
                        callback: function callback(action) {
                          
                        }
                      });
                      return false;
                    }
                  });
                }  
              },
              submitHandlerSuccessMethod: function(_self,data){
//                console.log(companyCode!=''&&companyCode!=null)
//                if(type=='create'){
//                  window.location.href = "addCompanySystemPage?companyId=" + data.data;
//                }else if(type=='change'){
//                  window.location.href = "applicatuibChangSystemPage?companyId=" + data.data+"&systemId=" + systemId;
//                }else if(type=='new'){
//                  window.location.href = "mainCompanyInfoPage";
//                }else if(type=='update'){
//                  window.location.href = "mainCompanyInfoPage";
//                }
              },
              
              msgNameMethod:function(){
                var val =this.formData.companyName;
                if(val!=null){
                  for(var i=0;i<this.msgName.length;i++){
                    if(val==this.msgName[i].orgName){
                      this.formData.companyCode = this.msgName[i].orgCode;
                    }
                  }
                }
              },
              industryCategory: function(){
                bus.$emit("industryCategory","1");
              },
              
              saveCompanyInfo: function(formData){
              	this.submitHandler(formData);
              },
              cleanCompanyInfo: function(_self){
              	_self.formData={
                    companyName:_self.formData.companyName,
                    companyTypeName:"",
                    ldContactName:"",
                    companyCode:_self.formData.companyCode,
                    companyAddress:"",
                    postalCode:"",
                    administrativeNum:"",
                    compPrincipalName:"",
                    compPrincipalPost:"",
                    compPrincipalWorkTel:"",
                    compPrincipalEm:"",
                    compPrincipalPhone:"",
                    ldContactPost:"",
                    ldContactWorkTel:"",
                    ldContactEmail:"",
                    ldContactPhone:"",
                    rDepartment:"",
                    gpReportingComp:"",
                    fkSubordinatePro:"",
                    fkIndustryCategory:"",
                    fkAffiliation:"",
                    fkPlateType:"",
                    fkCompanyType:"",
                    companyId:_self.formData.companyId,
                    changeType:"",
                    systemId:_self.formData.systemId
                 };
              	$(".baseMes1").find(".btnColor").removeClass("btnColor");
              },
              returnCompanyList: function(_self){
              	window.location.href = originUrl + "page/mainCompanyInfoPage?activeName=first";
              },
              // 获取单位Code
              getCompanyCode : function(_self) {
                ajaxMethod(_self, 'post',
                    'jurisdiction/getCompanyCode', true,
                    '{}', 'json',
                    'application/json;charset=UTF-8',
                    _self.getCompanyCodeSuccessMethod);
              },
              // 获取单位Code成功
              getCompanyCodeSuccessMethod : function(_self, responseData) {
              	this.dtlCompanyCode=responseData.data;
              	if(type!= null && type != ''){
              		if(type == "new" || type == "create"){
              			this.getCompanyDetailsMethod(_self,responseData.data);
              		}else{
              			this.getCompanyDetailsMethod(_self,responseData);
              		}
              	}
              },
              getCompanyDetailsMethod:function(_self,meg){
                ajaxMethod(_self, 'post',
                    'company/queryCompanyByCode', true,'{"companyCode":"'+meg+'"}', 'json',
                    'application/json;charset=UTF-8',_self.getCompanyDetailsMethodSuccess);
              },
              getCompanyDetailsMethodSuccess:function(_self, responseData){
              	//如果有该单位信息则显示详情，没有则显示单位名称及code
              	if(responseData.data != null){
              		this.getCompanyInfoSuccessMethod(_self,responseData)
              		data.disabledInput=true;
              	}else{
              		for(var i = 0; i < this.msgName.length ; i++){
              			if(this.dtlCompanyCode == this.msgName[i].orgCode){
              				this.formData.companyCode = this.dtlCompanyCode;
              				this.formData.companyName=this.msgName[i].orgName;
              				data.disabledInput=true;
              			}
              		}
              	}
              },
              getCompanyDetailsByCompanyIdMethodSuccess:function(_self, companyCode){
              	this.getCompanyCodeSuccessMethod(_self,companyCode);
              }
            },
            created : function() {
              // 获取省份
              this.getProvinceMethod(this);
              // 获取隶属关系
              this.getAffiliationMethod(this);
              // 获取单位类别
              this.getUnitTypeMethod(this);
              // 获取板块类型
              this.getPlateTypeMethod(this);
              // 获取单位
              this.getCompanyMethod(this);
              //获取系统ID
              //getJurisdictionMethod(a,"0101010101");
              if(type == 'change'){
                this.getCompanyDetailsByCompanyIdMethodSuccess(this,companyCode);
                return;
              }
              if(jurisdiction == null || jurisdiction == ''){
            		this.getCompanyCode(this);
            	}
            },
            mounted : function() {
              // this.selectChange()
              // new Ctor().$mount('#wrap');
              var _self=this;
              // 获取单位
              _self.getCompanyMethod(_self);
              if(companyId!=''&&companyId!=null){
                _self.getCompanyInfoByIdMethod(_self,companyId);
              }else if(companyCode!=null){
                _self.getCompanyInfoMethod(_self,companyCode);
              }
              
              if(readonly=='update'){
                _self.nameReadonly = true;
                _self.disabledInput = true;
              }
              if(type=='update'){
                _self.nameReadonly = true;
              }
              bus.$on("selectedOptions",function(meg){
                if(meg!=null){
                	//清空验证
                	_self.$refs['formData'].resetFields();
                  // 获取单位回显
                  _self.transmitParam = meg;
                  _self.getCompanyInfoMethod(_self,meg[1]);
                  //单位名称不可修改
                  _self.nameReadonly = true;
                }
              });
              bus.$on("resource",function(meg){
                if(meg!=null){
                  _self.formData.fkIndustryCategory = meg;
                  _self.$refs.formData.validateField('fkIndustryCategory');
                }
              });
              
              bus.$on("saveCompanyInfo",function(meg){
                if(meg!=null){
                  _self.saveCompanyInfo(meg);
                }
              });
              bus.$on("cleanCompanyInfo",function(meg){
                if(meg!=null){
                  _self.cleanCompanyInfo(_self);
                }
              });
              bus.$on("returnCompanyList",function(meg){
                if(meg!=null){
                  _self.returnCompanyList(_self);
                }
              });
              bus.$on('formName',function(meg){
                if(meg!=null){
                  _self.$refs[meg].validate(function (valid) {
                    if (valid) {
                      bus.$emit('addCompany',"add");
                    } else {
                      _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                        confirmButtonText: '确定',
                        callback: function callback(action) {
                          
                        }
                      });
                      return false;
                    }
                  });
                }
              });
              
              bus.$on('changeFormName',function(meg){
                if(meg!=null){
                  _self.$refs[meg].validate(function (valid) {
                    if (valid) {
                      bus.$emit('changeFormAjax',"add");
                    } else {
                      _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                        confirmButtonText: '确定',
                        callback: function callback(action) {
                        }
                      });
                      return false;
                    }
                  });
                }
              });
              
              var coverH = $(".mcAddMessge").height();
              $(".cover").css("height",coverH + parseInt(54));
            }
          })
        })
  })
})();
