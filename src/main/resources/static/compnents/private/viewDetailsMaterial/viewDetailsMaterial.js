/**
 * Created by timha on 2018/6/7.
 */
(function() {
    var data ={
    		topologyDescriptionId:"",//系统拓扑结构及说明路径
    		topologyDescriptionName:"",//系统拓扑结构及说明名称
    		organizationManagementId:"",//系统安全组织机构及管理制度路径
    		organizationManagementName:"",//系统安全组织机构及管理制度名称
    		implementationPlanId:"",//系统安全保护设施设计;实施方案或改建实施方案路径
    		implementationPlanName:"",//系统安全保护设施设计;实施方案或改建实施方案名称
    		licenseCertificateId:"",//系统使用的安全产品清单及认证、销售许可证明路径
    		licenseCertificateName:"",//系统使用的安全产品清单及认证、销售许可证明名称
    		evaluationPresentationId:"",//测评报告路径
    		evaluationPresentationName:"",//测评报告名称
    		expertReviewId:"",//专家评审路径
    		expertReviewName:"",//专家评审名称
    		directorOpinionId:"",//上级主管部门审批意见路径
    		directorOpinionName:"",//上级主管部门审批意见名称
    };
    Vue.component('viewDetailsMaterial',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsMaterial/viewDetailsMaterial.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                created: function() {
                  var url="grading/querySystemMaterials";
                  var _self=this;
                  //	 列表请求数据
                  ajaxMethod(_self, "post", url, false ,'{"fkSystemId":"'+systemId+'"}', "json", 'application/json;charset=UTF-8', _self.createdSuccess);		
                },
                mounted: function() {
                },
                methods:{
                	 // 获取单位信息详情成功
                  createdSuccess : function(_self, attchResult) {
                  	if(attchResult.data != null){
                  		if(!this.isEmpty(attchResult.data.topologyDescriptionId)){
                    		this.topologyDescriptionId = attchResult.data.topologyDescriptionId;
                    	}
                    	if(!this.isEmpty(attchResult.data.topologyDescriptionName)){
                    		this.topologyDescriptionName = attchResult.data.topologyDescriptionName;
                    	}
                    	if(!this.isEmpty(attchResult.data.organizationManagementId)){
                    		this.organizationManagementId = attchResult.data.organizationManagementId;
                    	}
                    	if(!this.isEmpty(attchResult.data.organizationManagementName)){
                    		this.organizationManagementName = attchResult.data.organizationManagementName;
                    	}
                    	if(!this.isEmpty(attchResult.data.implementationPlanId)){
                    		this.implementationPlanId = attchResult.data.implementationPlanId;
                    	}
                    	if(!this.isEmpty(attchResult.data.implementationPlanName)){
                    		this.implementationPlanName = attchResult.data.implementationPlanName;
                    	}
                    	if(!this.isEmpty(attchResult.data.licenseCertificateId)){
                    		this.licenseCertificateId = attchResult.data.licenseCertificateId;
                    	}
                    	if(!this.isEmpty(attchResult.data.licenseCertificateName)){
                    		this.licenseCertificateName = attchResult.data.licenseCertificateName;
                    	}
                    	if(!this.isEmpty(attchResult.data.evaluationPresentationId)){
                    		this.evaluationPresentationId = attchResult.data.evaluationPresentationId;
                    	}
                    	if(!this.isEmpty(attchResult.data.evaluationPresentationName)){
                    		this.evaluationPresentationName = attchResult.data.evaluationPresentationName;
                    	}
                    	if(!this.isEmpty(attchResult.data.expertReviewId)){
                    		this.expertReviewId = attchResult.data.expertReviewId;
                    	}
                    	if(!this.isEmpty(attchResult.data.expertReviewName)){
                    		this.expertReviewName = attchResult.data.expertReviewName;
                    	}
                    	if(!this.isEmpty(attchResult.data.directorOpinionId)){
                    		this.directorOpinionId = attchResult.data.directorOpinionId;
                    	}
                    	if(!this.isEmpty(attchResult.data.directorOpinionName)){
                    		this.directorOpinionName = attchResult.data.directorOpinionName;
                    	}
                  	}	
                  },
                  download:function(fileId){
                  	window.location.href=originUrl+"fileHandle/downloadFile?fileId="+fileId;
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