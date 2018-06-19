/**
 * selfCheck
 */
(function () {
  var data={
  		show:{
  			visible2: -1,
  			deleteFileType: null,
  		},
    itemdata:null,
    queryParam: {
    	"fkSystemId": null,
    	"pageSize": 10,
    	"currentPage": 1,
    },
    editParam: {
    	"selfexaminationId": null,
    	"fkSystemId": null,
    	"inspectionDate": null,
    	"fkInspectionStatus": null,
    	"fkInspectionReu": null,
    	"examinationReportPath": null,
    	"examinationReportName": null,
    	"fkRectificationReu": null,
    	"rectificationDate": null,
    	"examinationRectificationReportPath": null,
    	"examinationRectificationReportName": null,
    },
    result:{
	    "code": "0",
	    "msg": "成功",
	    "pagesize": 1,
	    "currentPage": 1,
	    "total": 2,
	    "totalPages": 2,
	    "data": [],
	  },
  };
  Vue.component('selfCheck',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/selfCheck/selfCheck.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
        	this.queryParam.fkSystemId = systemId;
        	this.editParam.fkSystemId = systemId;
        	this.querySelfCheckList(this);
        },
        methods:{
        	//查询列表
        	querySelfCheckList: function(_self) {
            ajaxMethod(_self, 'post',
                '/selfexamination/querySelfexaminationList', true,
                JSON.stringify(_self.queryParam), 'json',
                'application/json;charset=UTF-8',
                _self.querySelfCheckListSuccessMethod);
          },
          querySelfCheckListSuccessMethod: function(_self,data){
          	_self.result = data;
          	_self.queryParam.pageSize = data.pagesize;
          	_self.queryParam.currentPage = data.currentPage;
          },
          //上一页下一页点击事件
          clickPage: function(page) {
            if (page <= 0) {
              ("当前页面已经是第一页");
              this.queryParam.currentPage = 1;
            	this.querySelfCheckList(this);
            } else if (page > data.result.totalPages) {
              ("当前页面已经是最后一页");
              this.queryParam.currentPage = data.result.totalPages;
            	this.querySelfCheckList(this);
            } else {
            	this.queryParam.currentPage = page;
            	this.querySelfCheckList(this);
            }
          },
          hpageNum:function(_this){
            var a=$("#txt").val();
            if(a<=0){
              ("请输入正确页数");
              this.queryParam.currentPage = 1;
            	this.querySelfCheckList(this);
            }else if(a>data.result.totalPages){
              this.queryParam.currentPage = data.result.totalPages;
            	this.querySelfCheckList(this);
            }else{
            	this.queryParam.currentPage = a;
            	this.querySelfCheckList(this);
            }
          },
          
          //点击添加和编辑自查
          showDialog: function(selfexaminationId){
          	var _self=this;
          	_self.cleanEditParam(_self);
            $("#dialog").css("display","block");
            if (selfexaminationId){
            	var queryEditParam = {"selfexaminationId":selfexaminationId};
            	ajaxMethod(_self, "post", 
            			"/selfexamination/queryEditSelfexamination", false ,
            			JSON.stringify(queryEditParam), "json", 
            			'application/json;charset=UTF-8', _self.dialogdata);
            }else {//说明是新建过来的
            	$("#h-add").css("display","block");
            }
          },
          dialogdata: function (_self, responseData) {
          	_self.editParam=responseData.data;
          },
          
          //保存自查信息
          saveSelfexamination: function () {
          	var _self=this;
          	ajaxMethod(_self, "post", 
          			"/selfexamination/saveSelfexamination", false ,
          			JSON.stringify(_self.editParam), "json", 
          			'application/json;charset=UTF-8', _self.saveSelfexaminationSuccess);

          },
          saveSelfexaminationSuccess: function(_self, responseData){
          	_self.querySelfCheckList(_self);
          	//点击确定后关闭弹窗
          	_self.closes();
          },
          //点击 "X" 关闭弹框
          closes:function () {
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none"
          },
          cleanEditParam: function (_self) {
          	_self.editParam={
            	"selfexaminationId": null,
            	"fkSystemId": systemId,
            	"inspectionDate": null,
            	"fkInspectionStatus": null,
            	"fkInspectionReu": null,
            	"examinationReportPath": null,
            	"examinationReportName": null,
            	"fkRectificationReu": null,
            	"rectificationDate": null,
            	"examinationRectificationReportPath": null,
            	"examinationRectificationReportName": null,
            };
          },
          
          //删除自查信息
          deleteSelfexamination: function(selfexaminationId){
          	var _self = this;
          	_self.show.visible2 = -1
          	var deleteParam = {"selfexaminationId": selfexaminationId,"fkSystemId": systemId};
          	ajaxMethod(_self, "post", 
          			"/selfexamination/deleteSelfexaminationBySelfexaminationId", false ,
          			JSON.stringify(deleteParam), "json", 
          			'application/json;charset=UTF-8', _self.deleteSelfexaminationSuccess);
          },
          deleteSelfexaminationSuccess: function(_self, responseData){
          	_self.querySelfCheckList(_self);
          },
          
          onUpload: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
					},
					onUploadSuccessMethod: function(_self,responseData){
						_self.editParam.examinationReportName=responseData.data.attachName;
						_self.editParam.examinationReportPath=responseData.data.uploadUrl;
					},
          onUpload2: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
					},
					onUploadSuccessMethod2: function(_self,responseData){
						_self.editParam.examinationRectificationReportName=responseData.data.attachName;
						_self.editParam.examinationRectificationReportPath=responseData.data.uploadUrl;
					},
					fileDel:function(path,deleteFileType){
						this.show.deleteFileType = deleteFileType;
						ajaxMethod(this, 'post',
								'fileHandle/deleteFile', true,'{"downloadFile":"'+path+'"}', 'json',
								'application/json;charset=UTF-8',this.fileDelSuccessMethod);
          },
          fileDelSuccessMethod:function(_self,responseData){
          	if(_self.show.deleteFileType == 1){
  						_self.editParam.examinationReportName='';
  						_self.editParam.examinationReportPath='';
          	}else if(_self.show.deleteFileType == 2){
          		_self.editParam.examinationRectificationReportName='';
          		_self.editParam.examinationRectificationReportPath='';
          	}
						_self.show.deleteFileType = null;
          },
          downloadReport: function(fileId){
          	this.fileDownload('','',fileId);
          },
          //根据URL和附件名或者根据fileId下载附件
          fileDownload:function(uploadUrl,attachName,fileId){
          	if(uploadUrl == "undefined"){
          		uploadUrl = '';
          	}
          	if(attachName == "undefined"){
          		attachName = '';
          	}
          	if(fileId == "undefined"){
          		fileId = '';
          	}
          	//下载路径
          	window.location.href = originUrl + "fileHandle/downloadFile?uploadUrl="+uploadUrl+"&attachName="+attachName+"&fileId="+fileId;
					},
        },//methods


        mounted: function() {
          //点击返回按钮 返回到首页
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
            	window.location.href = originUrl + "/page/indexPage";
            }
          })
        }

      })
    })
  })

}());