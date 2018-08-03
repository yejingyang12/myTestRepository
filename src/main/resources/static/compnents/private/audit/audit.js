(function () {
  var data = {
  	//查询条件数据
		formData: {
			instanceName: null,
			initiator: null,
			fkInfoSysTypeCon: null,
			fkBusinessNode: null,
			fkExaminStatus: null,
			pageSize: 10,
	  	currentPage: 1,
	  	field: null,
	  	sort: null, 
	  	handlingState: 1, //办理状态
	  	role: null,//角色
		},
		jurisdictionType: null,
		//表单数据
		tableData: {},
		fkInfoSysTypeConList: [{codeId: '1',codeName: '自建'},{codeId: '2',codeName: '统建'},{codeId: '3',codeName: '总部统建'}],
		businessNodeList: [{codeId: '1',codeName: '定级'},{codeId: '2',codeName: '撤销备案'},{codeId: '3',codeName: '定级信息变更'}],
		examinStatusList: [
		                   {codeId: '1',codeName: '待企业业务审核'},
		                   {codeId: '2',codeName: '待总部安全审核'},
		                   {codeId: '3',codeName: '企业业务审核未通过'},
		                   {codeId: '4',codeName: '总部安全审核未通过'},
		                   {codeId: '5',codeName: '归档'}
		                  ],
		auditBtn:null,
		flag:true,
  };
  Vue.component('audit',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/audit/audit.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
        	this.getPermitJurisdictionInfo(this);
        	this.queryCheckList(this);
        },
        methods: {
        	getPermitJurisdictionInfo: function(_self){
            ajaxMethod(_self,"post",
                "jurisdiction/queryDataJurisdictionApi",false,
                JSON.stringify(this.formData),"json",
                'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
          },
          getPermitJurisdictionSuccess: function(_self,response){
            
            for (var i = 0; i < response.data.permssions.length; i++) {
              var permssions = response.data.permssions[i];
              if(permssions==S_STR_PERMIT_PARAM_ENTERPRISE_AUDIT){
                _self.jurisdictionType = 1;
                _self.formData.role = 1;
              }
              if(permssions==S_STR_PERMIT_PARAM_HEADQUARTERS_AUDIT){
                _self.jurisdictionType = 2;
                _self.formData.role = 2;
              }
            }
          },
        	queryCheckListMethods: function(){
        		this.queryCheckList(this);
        	},
        	//查询审核列表
        	queryCheckList: function(_self){
        		ajaxMethod(_self, "post", "/checkController/queryCheckList",
        				false,JSON.stringify(_self.formData),"json",
        				'application/json;charset=UTF-8', _self.queryCheckListSuccess);
        	},
        	queryCheckListSuccess: function(_self, responseData){
        		_self.tableData = responseData;
//         		_self.tableData.totalPages = responseData.data.totalPage;
//        		_self.tableData.pagesize = responseData.data.pageSize;
//        		_self.tableData.currentPage = responseData.data.currPage;
//        		_self.tableData.totalPages = responseData.data.totalPage;
//        		_self.tableData.result = responseData.data;
        	},
        	//点击待办、已办、全部
        	changeHandlingState: function(handlingState){
        		var _self = this;
        		_self.formData.handlingState = handlingState;
        		_self.queryCheckList(_self);
        	},
          //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
              alert("当前页面已经是第一页")
            } else if (page > this.tableData.totalPages) {
              alert("当前页面已经是最后一页")
            } else {
            	this.formData.currentPage = page;
            	this.queryCheckList(this);
            }
          },
          hpageNum:function(_this){
            var a=$("#txt").val();
            if(a<=0||a>this.tableData.totalPages){
              alert("请输入正确页数")
            }else{
            	this.formData.currentPage = a;
            	this.queryCheckList(this);
            }
          },
          
          //审核
          checkThisRow: function(fkBusinessNode,systemId,companyId,examinStatus,businessId,taskId){
						//定级：1
          	//撤销备案：2
          	//定级信息变更：3
						window.location.href=originUrl+"/page/auditGradPage?systemId="+systemId+"&fkBusinessNode="+fkBusinessNode+"&companyId="+companyId+"&fkExaminStatus="+examinStatus+"&businessId="+businessId+"&taskId="+taskId;
          },
          
          //专家评审意见和备案证明下载
          auditDownLoad:function(fileId){
          	window.location.href=originUrl+"fileHandle/downloadFile?fileId="+fileId;
          },
          
          /*点击全部 待办 已办 对应的样式*/
          changColor: function () {
            for (var i = 0; i < data.auditBtn.length; i++) {
              data.auditBtn[i].index = i;
              data.auditBtn[i].onclick = function () {
                this.style.color = "white";
                this.style.backgroundColor = "#3d95df";
                for (var j = 0; j < data.auditBtn.length; j++) {
                  if (this.index != j) {
                    const argument = data.auditBtn[j];
                    argument.style.color = "#333";
                    argument.style.backgroundColor = "white";
                  }
                }
              }
            }
          },
          //下载
          downloadFile:function (str) {
            var $eleForm = $("<form method='get'></form>");
            $eleForm.attr("action", str);
            $(document.body).append($eleForm);
            //提交表单，实现下载
            $eleForm.submit();
          },
          clearInfo:function () {
            this.formData.instanceName = null;
            this.formData.initiator = null;
            this.formData.fkInfoSysTypeCon = null;
            this.formData.fkBusinessNode = null;
            this.formData.fkExaminStatus = null;
            //this.formData.handlingState = null;
          },
          auditDetails:function (systemId,companyId) {
            window.location.href=originUrl+"/page/auditDetailsPage?systemId="+systemId+"&companyId="+companyId;
          }
        },
        mounted: function() {
          var auditBtn=document.getElementsByClassName('audit-btn');
          data.auditBtn=auditBtn;
          this.changColor();
          
          //点击返回按钮 发送请求
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
            	window.location.href=originUrl+"/page/indexPage";
            }
          });
        }
      })
    })
  })
}());