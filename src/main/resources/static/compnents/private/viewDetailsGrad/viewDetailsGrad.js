var data={
			materialViewShow:false,
      formData:{
      	systemName:'',
        gradingId:'',
        fkSystemId:'',
        fkBizSPRankDegree:'',
        gradeRecordSysName:'',
        fkBizSPRankLevel:'',
        fkBizSystemDegree:'',
        fkBizSystemLevel:'',
        fkSpRanklevel:0,
        expertView:'',
        rankExplainDesc:'',
        rankTime:'',
        competentIsExisting:'',
        competentName:'',
        competentView:'',
        filler:'',
        fillDate:'',
        gradingReportName:'',
        gradingReportPath:'',
        expertReviewName:'',
        expertReviewPath:'',
        directorOpinionName:'',
        directorOpinionPath:''
      }
    };

(function () {
    Vue.component('viewDetailsGrad',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/viewDetailsGrad/viewDetailsGrad.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                watch:{
                },
                methods:{
	                	// 获取系统信息
	                  getSystem : function(_self) {
	                  	ajaxMethod(this, 'post',
	                      'system/querySystemInformationBySystemId', true,
	                      '{"systemId":"'+systemId+'"}', 'json',
	                      'application/json;charset=UTF-8',
	                      this.getSystemSuccess);
	                  } ,
	                  getSystemSuccess : function(_self,result){
	                  	this.formData.systemName = result.data.systemName;
	                  	if(result.data.gradeRecordSysName==''||result.data.gradeRecordSysName==null||result.data.gradeRecordSysName=='undefind'){
	                  		this.formData.gradeRecordSysName = result.data.systemName;
	                  	}else{
	                  		this.formData.gradeRecordSysName = result.data.gradeRecordSysName;
	                  	}
	                  },
                    getGradeMethod: function(_self,id) {
                      ajaxMethod(_self, 'post','grading/queryEditGrading', true,
                      '{"fkSystemId":"'+systemId+'"}', 'json',
                      'application/json;charset=UTF-8',
                      _self.getGradeSuccessMethod);
                    },
                    // 获取回显安全按等级成功
                    getGradeSuccessMethod : function(_self, responseData) {
                      if(responseData.data!=null){
                      	_self.formData = responseData.data;
                      	if(! this.isEmpty(responseData.data.competentIsExisting)){
                      		this.formData.competentIsExisting = responseData.data.competentIsExisting;
                  			}
                      	if(! this.isEmpty(responseData.data.competentName)){
                      		 this.formData.competentName = responseData.data.competentName;
                  			}
                      	if(! this.isEmpty(responseData.data.gradeRecordSysName)){
                     		 this.formData.gradeRecordSysName = responseData.data.gradeRecordSysName;
                      	}else{
                      		this.formData.gradeRecordSysName = responseData.data.systemName;
                      	}
                      	if(! this.isEmpty(responseData.data.competentView)){
                      		this.formData.competentView = responseData.data.competentView;
                      	}
                      	if(! this.isEmpty(responseData.data.gradingReportId)){
                      		this.formData.gradingReportId = responseData.data.gradingReportId;
                      	}
                      	if(! this.isEmpty(responseData.data.gradingReportName)){
                      		this.formData.gradingReportName = responseData.data.gradingReportName;
                      	}
                      	if(! this.isEmpty(responseData.data.competentView)){
                      		this.formData.competentView = responseData.data.competentView;
                      	}
                      	if(! this.isEmpty(responseData.data.expertReviewId)){
                      		this.formData.expertReviewId = responseData.data.expertReviewId;
                      	}
                      	if(! this.isEmpty(responseData.data.expertReviewName)){
                      		this.formData.expertReviewName = responseData.data.expertReviewName;
                      	}
                      	if(! this.isEmpty(responseData.data.filler)){
                      		this.formData.filler = responseData.data.filler;
                      	}
                      	if(! this.isEmpty(responseData.data.fillDate)){
                      		this.formData.fillDate = responseData.data.fillDate;
                      	}
                      	if(! this.isEmpty(responseData.data.rankExplainDesc)){
                      		this.formData.rankExplainDesc = responseData.data.rankExplainDesc;
                      	}
                      	if(! this.isEmpty(responseData.data.rankTime)){
                      		this.formData.rankTime = responseData.data.rankTime;
                      	}
                      	if(! this.isEmpty(responseData.data.systemName)){
                      		this.formData.systemName = responseData.data.systemName;
                      	}
                      }else{
                        //获取系统信息
                        this.getSystem(this);
                      }
                      if(_self.formData.fkSpRanklevel<303 || _self.formData.fkSpRanklevel<'303'){
                    		this.materialViewShow = false;
                    	}else{
                    		this.materialViewShow = true;
                    	}
                      if(_self.formData.fkBizSPRankDegree!=''){
                        _self.formData.fkBizSPRankDegree = _self.formData.fkBizSPRankDegree+",";
                        var bizSPRankDegree = _self.formData.fkBizSPRankDegree.split(",");
                        for(var i=0;i<bizSPRankDegree.length;i++){
                          if(bizSPRankDegree[i]!=''){
                            $("#"+bizSPRankDegree[i]+"").prop("checked",true);
                          }
                        }
                      }
                      if(_self.formData.fkBizSystemDegree!=''){
                        _self.formData.fkBizSystemDegree = _self.formData.fkBizSystemDegree+",";
                        var bizSystemDegree = _self.formData.fkBizSystemDegree.split(",");
                        for(var i=0;i<bizSystemDegree.length;i++){
                          if(bizSystemDegree[i]!=''){
                            $("#"+bizSystemDegree[i]+"").prop("checked",true);
                          }
                        }
                      }
                      //时间显示问题
                      if(_self.formData.fillDate == '1970-01-01'){
                      	_self.formData.fillDate = '';
                      }
                      bus.$emit('materialView',this.materialViewShow);
                      bus.$emit("showMaterialView",this.materialViewShow);
                    },
                    // 获取安全按等级成功
                    submitGradeInfoSuccessMethod : function(_self, responseData) {
                      //window.location.href = originUrl+encodeURI("/page/addCompanyMaterialPage?systemId=f7821c57865d4983b9db6f8db08efb3c");
                    },
                    download: function (fileId) {
                   	 	window.location.href=originUrl+encodeURI("/fileHandle/downloadFile?fileId="+fileId);
                    },
                    //判断字符是否为空的方法
                    isEmpty: function (obj){
                         if(typeof obj == "undefined" || obj == null || obj == ""){
                             return true;
                         }else{
                             return false;
                         }
                     },
                     thingMove:function(){
                       var value = $(".thingName").html();
                       $(".thingName").attr('title',value);
                     },
                     thingMove1:function(){
                       var value = $(".thingName1").html();
                       $(".thingName1").attr('title',value);
                     },
                     thingMove2:function(){
                       var value = $(".thingName2").html();
                       $(".thingName2").attr('title',value);
                     }
                },
                created: function() {
                    //安全等级信息
                    this.getGradeMethod(this,systemId);
                    this.formData.fkSystemId = systemId;
                }
            })
        })
    })
}())