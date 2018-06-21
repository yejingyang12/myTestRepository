  var data = {
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
        systemId:""
      },
      msgName : [],// 单位名称
      msgProvince : [],// 所属省份
      msgaAffi : [],// 隶属关系
      msgUintType : [],// 单位类型
      msgPlate : [],// 板块类型
      selectedOptions:[],
      transmitParam:[],
      nameReadonly:false
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
              },
              getAffiliation : function() {
                this.formData.fkAffiliation = $("#affiliation").val();
              },
              
              // 点击切换 添加class名
              getUintTypeClass : function(e) {
                $("#baseMes2 div div").click(function(){
                  $("#baseMes2 div div").removeClass("btnColor");
                  $(this).addClass("btnColor");
                })
                if(e.target.innerHTML.indexOf("其它") != -1){
                  this.formData.fkCompanyType = $("#companyType").val();
                }else{
                  this.formData.fkCompanyType = e.target.innerHTML;
                }
              },
              getCompanyType : function() {
                this.formData.fkCompanyType = $("#companyType").val();
              },
              // 点击切换 添加class名
              getPlateClass : function(e) {
                $("#baseMes3 div div").click(function(){
                  $("#baseMes3 div div").removeClass("btnColor");
                  $(this).addClass("btnColor");
                })
                if(e.target.innerHTML.indexOf("其它") != -1){
                  this.formData.fkPlateType = $("#plateType").val();
                }else{
                  this.formData.fkPlateType = e.target.innerHTML;
                }
              },
              getPlateType : function() {
                this.formData.fkPlateType = $("#plateType").val();
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
                 ajaxMethod(_self, 'post',
                     'systemCode/querySystemCodeForKeySystemCode', true,
                     '{"codeType":"3"}', 'json',
                     'application/json;charset=UTF-8',
                     _self.getAffiliationSuccessMethod);
              },
              // 获取隶属关系成功
              getAffiliationSuccessMethod : function(_self, responseData) {
                 _self.msgaAffi = responseData.data;
              },
              // 获取单位类别
              getUnitTypeMethod : function(_self) {
                ajaxMethod(_self, 'post',
                    'systemCode/querySystemCodeForKeySystemCode', true,
                    '{"codeType":"5"}', 'json',
                    'application/json;charset=UTF-8',
                    _self.getUnitTypeSuccessMethod);
              },
              // 获取单位类别成功
              getUnitTypeSuccessMethod : function(_self, responseData) {
                _self.msgUintType = responseData.data;
                
              },
              // 获取板块类型
              getPlateTypeMethod : function(_self) {
                 ajaxMethod(_self, 'post',
                     'systemCode/querySystemCodeForKeySystemCode', true,
                     '{"codeType":"6"}', 'json',
                     'application/json;charset=UTF-8',
                     _self.getPlateTypeSuccessMethod);
              },
              // 获取板块类型成功
              getPlateTypeSuccessMethod : function(_self, responseData) {
                  _self.msgPlate = responseData.data;
                  
                if(_self.msgUintType.length > 5){
                  $("#baseMes2").addClass("baseMes2");
                }
                if(_self.msgaAffi.length > 5){
                  $("#baseMes1").addClass("baseMes2");
                }
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
                    'organizationapi/queryOrganizationForKeyOrganizationCode', true,'{}', 'json',
                    'application/json;charset=UTF-8',_self.getCompanySuccessMethod);
              },
              getCompanySuccessMethod: function(_self,data){
                if(data.data!=null){
                  _self.msgName = data.data;
                }
              },
              submitHandler:function(){
                ajaxMethod(this, 'post',
                    'company/saveCompany', true,JSON.stringify(this.formData), 'json',
                    'application/json;charset=UTF-8',this.submitHandlerSuccessMethod);
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
                var val = $("#companyName").val();
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
            },
            mounted : function() {
              // this.selectChange()
              // new Ctor().$mount('#wrap');
              var _self=this;
              if(readonly=='update'){
                _self.getCompanyInfoMethod(_self,companyCode);
                _self.nameReadonly = true;
              }
              if(type=='update'){
                _self.getCompanyInfoByIdMethod(_self,companyId);
                _self.nameReadonly = true;
              }
              bus.$on("selectedOptions",function(meg){
                
                if(meg!=null){
                  // 获取单位回显
                  _self.transmitParam = meg;
                  _self.getCompanyInfoMethod(_self,meg[1]);
                  //单位名称不可修改
                  _self.nameReadonly = true;
                }
                _self.formData.fkIndustryCategory = "6666666666666666666";
                console.log(_self.formData.fkIndustryCategory)
              });
              bus.$on("resource",function(meg){
                if(meg!=null){
                  _self.formData.fkIndustryCategory = meg;
                }
              });
              
              var coverH = $(".mcAddMessge").height();
              $(".cover").css("height",coverH + parseInt(54));
            }
          })
        })
  })
})();
