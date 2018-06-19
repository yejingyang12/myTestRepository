/**
 * Created by timha on 2018/6/7.
 */
(function() {
    var data ={
        msgName:"",//单位名称
        msgPwd:"",//单位编码
        msgDress:"",//单位地址
        msgPostal:"",//邮政编码
        msgProvince:[],//所属省份
        msgAdc:"",//行政区划编码
        msgPersoname:"",//单位负责人姓名
        msgComPhone:"",//办公电话
        msgTelphone:"",//移动电话
        msgOfficial:"",//职务/职称
        msgEmail:"",//电子邮件
        msgLdc:"",//责任部门联系人
        msgLdcPhone:"",//责任部门联系人->办公电话
        msgLdcTelphone:"",//责任部门联系人->移动电话
        msgLdcOfficial:"",//责任部门联系人->职务职称
        msgLdcEmail:"",//责任部门联系人->电子邮件
        msgRespon:"",//责任部门
        msgUpName:"",//等保上报单位名称
        msgInCate:"",//行业类别
        msgaAffi:"",//隶属关系
        msgUintType:"",//单位类型
        msgPlate:"",//板块类型
    		companyId:''//单位ID
    };
    Vue.component('viewDetailsInfor',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsInfor/viewDetailsInfor.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                // 获取单位信息详情
                created: function() {
                   var url="company/queryDetailsCompany";
                   var _self=this;
                   //	 列表请求数据
                   ajaxMethod(_self, "post", url, false ,'{"companyId":"'+companyId+'"}', "json", 'application/json;charset=UTF-8', _self.createdSuccess);
                },
                mounted: function() {
                },
                methods:{
                  // 获取单位信息详情成功
                  createdSuccess : function(_self, companyResult) {
                  	if(companyResult.data != null){
                  		if(!this.isEmpty(companyResult.data.companyName)){
                    		this.msgName = companyResult.data.companyName;
                    	}
	                  	if(!this.isEmpty(companyResult.data.companyCode)){
	                  		this.msgPwd = companyResult.data.companyCode;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.companyAddress)){
	                  		this.msgDress = companyResult.data.companyAddress;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.postalCode)){
	                  		this.msgPostal = companyResult.data.postalCode;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.provincesName)){
	                  		this.msgProvince = companyResult.data.provincesName;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.administrativeNum)){
	                  		this.msgAdc = companyResult.data.administrativeNum;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.compPrincipalName)){
	                  		this.msgPersoname = companyResult.data.compPrincipalName;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.compPrincipalWorkTel)){
	                  		this.msgComPhone = companyResult.data.compPrincipalWorkTel;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.compPrincipalPhone)){
	                  		this.msgTelphone = companyResult.data.compPrincipalPhone;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.compPrincipalPost)){
	                  		this.msgOfficial = companyResult.data.compPrincipalPost;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.compPrincipalEm)){
	                  		this.msgEmail = companyResult.data.compPrincipalEm;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.ldContactName)){
	                  		this.msgLdc = companyResult.data.ldContactName;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.ldContactWorkTel)){
	                  		this.msgLdcPhone = companyResult.data.ldContactWorkTel;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.ldContactPhone)){
	                  		this.msgLdcTelphone = companyResult.data.ldContactPhone;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.ldContactPost)){
	                  		this.msgLdcOfficial = companyResult.data.ldContactPost;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.ldContactEmail)){
	                  		this.msgLdcEmail = companyResult.data.ldContactEmail;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.rDepartment)){
	                  		this.msgRespon = companyResult.data.rDepartment;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.gpReportingComp)){
	                  		this.msgUpName = companyResult.data.gpReportingComp;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.fkIndustryCategory)){
	                  		this.msgInCate = companyResult.data.fkIndustryCategory;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.fkAffiliation)){
	                  		this.msgaAffi = companyResult.data.fkAffiliation;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.fkCompanyType)){
	                  		this.msgUintType = companyResult.data.fkCompanyType;
	                  	}
	                  	if(!this.isEmpty(companyResult.data.fkCompanyType)){
	                  		this.msgPlate = companyResult.data.fkPlateType;
	                  	}
                  	}
                  },
                  //判断字符是否为空的方法
                  isEmpty: function (obj){
                       if(typeof obj == "undefined" || obj == null || obj == ""){
                           return true;
                       }else{
                           return false;
                       }
                   }
                },
            })
        })
    })
})();