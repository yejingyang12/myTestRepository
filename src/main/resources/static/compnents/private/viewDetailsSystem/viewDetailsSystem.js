/**
 * Created by timha on 2018/6/7.
 */
(function() {
    var data ={
    		constructionTypeName:"",//系统信息建设类型
    		combinedName:"",//是否为合并系统的定级
    		systemName:"",//系统名称
    		standardizedCode:"",//标准化代码
    		gradeRecordSysName:"",//等保备案系统名称
    		sysBusSituationType:"",//业务类型
    		sysBusDescription:"",//业务描述
    		sysServiceSitScope:"",//服务范围
    		sysServiceSitObject:"",//服务对象
    		npCoverageRange:"",//覆盖范围
    		interconnectionSit:"",//系统互联情况
    		systemKeyProducts :[],//关键产品使用情况
    		systemUseServices :[],//系统采用服务情况	
    		companyName:"",//所属单位名称
    		whenInvestmentUse:"",//何时投入使用
    		executiveOfficeName:"",//主管处室名称
    		subIsSystem:"",//系统是否为分系统
    		executiveDireCon:"",//主管联系人
    		executiveDireConTel:"",//主管联系人电话	
    };
    Vue.component('viewDetailsSystem',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsSystem/viewDetailsSystem.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                created: function() {
                  var url="system/queryDetailsSystem";
                  var _self=this;
                  //	 列表请求数据
                  ajaxMethod(_self, "post", url, false ,'{"systemId":"'+systemId+'"}', "json", 'application/json;charset=UTF-8', _self.createdSuccess);
                },
                mounted: function() {

                },
                methods:{
                	// 获取系统信息详情
                  createdSuccess : function(_self, systemResult) {
                  		if(systemResult.data != null){
                  			if(! this.isEmpty(systemResult.data.constructionTypeName)){
                  				this.constructionTypeName = systemResult.data.constructionTypeName;
                  			}
                  			if(! this.isEmpty(systemResult.data.combinedName)){
                  				this.combinedName = systemResult.data.combinedName;
                  			}
                  			if(! this.isEmpty(systemResult.data.systemName)){
                  				this.systemName = systemResult.data.systemName;
                  			}
                  			if(! this.isEmpty(systemResult.data.standardizedCode)){
                  				this.standardizedCode = systemResult.data.standardizedCode;
                  			}
                  			if(! this.isEmpty(systemResult.data.gradeRecordSysName)){
                  				this.gradeRecordSysName = systemResult.data.gradeRecordSysName;
                  			}
                  			if(! this.isEmpty(systemResult.data.sysBusSituationType)){
                  				this.sysBusSituationType = systemResult.data.sysBusSituationType;
                  			}
                  			if(! this.isEmpty(systemResult.data.sysBusDescription)){
                  				 this.sysBusDescription = systemResult.data.sysBusDescription;
                  			}
                  			if(! this.isEmpty(systemResult.data.sysServiceSitScope)){
                  				 var sysServiceSitScope = systemResult.data.sysServiceSitScope;
     			                if(sysServiceSitScope.indexOf("^") != -1){
     			                	var sysArr = sysServiceSitScope.split("^");
     			                	if(sysArr[0] == '跨地（区、市）跨个'){
     			                		this.sysServiceSitScope = "跨地（区、市）跨"+sysArr[1]+"个";
     			                	}else{
     			                		this.sysServiceSitScope = "跨省（区、市） 跨"+sysArr[1]+"个";
     			                	}
     			                }else{
     			                	this.sysServiceSitScope = systemResult.data.sysServiceSitScope;
     			                }
                  			}
                  			if(! this.isEmpty(systemResult.data.sysServiceSitObject)){
                  				this.sysServiceSitObject = systemResult.data.sysServiceSitObject;
                  			}
                  			if(! this.isEmpty(systemResult.data.npCoverageRange)){
                  				this.npCoverageRange = systemResult.data.npCoverageRange;
                  			}
                  			if(! this.isEmpty(systemResult.data.interconnectionSit)){
                  				this.interconnectionSit = systemResult.data.interconnectionSit;
                  			}
                  			if(! this.isEmpty(systemResult.data.systemKeyProducts)){
                  				this.systemKeyProducts = systemResult.data.systemKeyProducts;
                  			}
                  			if(! this.isEmpty(systemResult.data.systemUseServices)){
                  				 this.systemUseServices = systemResult.data.systemUseServices;
                  			}
                  			if(! this.isEmpty(systemResult.data.companyName)){
                  				this.companyName = systemResult.data.companyName;
                  			}	
                  			if(! this.isEmpty(systemResult.data.whenInvestmentUse)){
                  				this.whenInvestmentUse = systemResult.data.whenInvestmentUse;
                  			}
                  			if(! this.isEmpty(systemResult.data.executiveOfficeName)){
                  				this.executiveOfficeName = systemResult.data.executiveOfficeName;
                  			}
                  			if(! this.isEmpty(systemResult.data.subIsSystem)){
                  				this.subIsSystem = systemResult.data.subIsSystem;
                  			}
                  			if(! this.isEmpty(systemResult.data.executiveDireCon)){
                  				this.executiveDireCon = systemResult.data.executiveDireCon;
                  			}
                  			if(! this.isEmpty(systemResult.data.executiveDireConTel)){
                  				this.executiveDireConTel = systemResult.data.executiveDireConTel;
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