/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={
		visible2: -1,
		show:{
			visible2: -1,
		},
		deleteFile:{
			examReport:{fileId:null},
			rectificationReport:{fileId:null},
		},
		//查询参数
		queryParam: {
			fkSystemId: null,
			pageSize: 10,
			currentPage: 1,
		},
		imgList: null,
		result:{
			"code": "0",
			"msg": "成功",
			"pagesize": 1,
			"currentPage": 1,
			"total": 5,
			"totalPages": 5,
			"data": [],
		},
		editData:{
			"evaluationId": null,
			"fkSystemId":systemId,
			"examName": null,
			"examTime": null,
			"examYear": null,
			"examOrg": null,
			"fkExamStatus": null,
			"fkExamResult": null,
			"fkRectificationReu": null,
			"rectificationDate": null,
			"examReportPath": null,
			"examReportName": null,
			"rectificationReportPath": null,
			"rectificationReportName": null,
		},
  };
  Vue.component('testing',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/testing/testing.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
        	//点击 "X" 关闭弹框
          closes:function () {
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none";
          },
        	
          //点击加号和编辑 让弹框 显示并做相应处理
          showDialog: function(evaluationId){
          	var _self=this;
          	_self.cleanEditData(_self);
            $("#dialog").css("display","block");
            if (evaluationId){//说明是带数据过来的 点击的是编辑
              var dataparams = {
                "evaluationId": evaluationId,//要提交的自查信息id
              };
              ajaxMethod(_self, "post", 
              		"/evaluation/queryEditEvaluation", false ,
              		JSON.stringify(dataparams), "json", 
              		'application/json;charset=UTF-8', 
              		_self.dialogdata);
            }else {//说明是新建过来的
            	
            }
          },
          //获取回显数据成功
          dialogdata: function(_self,data){
          	_self.editData = data.data;
          },
          cleanEditData: function(_self) {
          	_self.editData={
	    				"evaluationId": null,
	    				"fkSystemId":systemId,
	    				"examName": null,
	    				"examTime": null,
	    				"examYear": null,
	    				"examOrg": null,
	    				"fkExamStatus": null,
	    				"fkExamResult": null,
	    				"fkRectificationReu": null,
	    				"rectificationDate": null,
	    				"examReportPath": null,
	    				"examReportName": null,
	    				"rectificationReportPath": null,
	    				"rectificationReportName": null,
          	}
    			},
          
          handleClick: function(tab, event) {
            //console.log(tab, event);
          },
          //查询列表
          queryEvaluationList: function(_self) {
            ajaxMethod(_self, 'post',
                '/evaluation/queryEvaluationList', true,
                JSON.stringify(_self.queryParam), 'json',
                'application/json;charset=UTF-8',
                _self.queryEvaluationListSuccessMethod);
          },
          queryEvaluationListSuccessMethod: function(_self,data){
          	_self.result = data;
          },
          //删除数据
          deleteEvaluation: function(evaluationId) {
          	var _self = this;
          	this.show.visible2 = -1;
          	var deleteParam = {"evaluationId":evaluationId,"fkSystemId":systemId};
          	ajaxMethod(_self, 'post',
              '/evaluation/deleteEvaluation', true,
              JSON.stringify(deleteParam), 'json',
              'application/json;charset=UTF-8',
              _self.deleteEvaluationSuccessMethod);
          },
          deleteEvaluationSuccessMethod: function(_self,data){
          	_self.queryEvaluationList(_self);
          },
          //添加或编辑确定保存数据
          saveEvaluation: function(){
          	var _self = this;
          	_self.sureDelFile(_self);
          	ajaxMethod(_self, 'post',
              '/evaluation/saveEvaluation', true,
              JSON.stringify(_self.editData), 'json',
              'application/json;charset=UTF-8',
              _self.saveEvaluationSuccessMethod);
          },
          saveEvaluationSuccessMethod: function(_self,data){
          	_self.queryEvaluationList(_self);
          	_self.closes();
          },
	        
          //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
              ("当前页面已经是第一页");
            	this.queryParam.currentPage = 1;
            	this.queryEvaluationList(this);
            } else if (page > this.result.totalPages) {
              ("当前页面已经是最后一页");
            	this.queryParam.currentPage = this.result.totalPages;
            	this.queryEvaluationList(this);
            } else {
            	this.queryParam.currentPage = page;
            	this.queryEvaluationList(this);
            }
          },
          hpageNum:function(_this){
            var a=$("#txt").val();
            if(a<=0){
              ("请输入正确页数");
            	this.queryParam.currentPage = 1;
            	this.queryEvaluationList(this);
            }else if(a>this.result.totalPages){
            	this.queryParam.currentPage = this.result.totalPages;
            	this.queryEvaluationList(this);
            }else{
            	this.queryParam.currentPage = a;
            	this.queryEvaluationList(this);
            }
          },
          
          onUpload: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','/fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
					},
					onUploadSuccessMethod: function(_self,responseData){
						_self.editData.examReportName=responseData.data.attachName;
						_self.editData.examReportPath=responseData.data.uploadUrl;
					},
					onUpload2: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','/fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
					},
					onUploadSuccessMethod2: function(_self,responseData){
						_self.editData.rectificationReportName=responseData.data.attachName;
						_self.editData.rectificationReportPath=responseData.data.uploadUrl;
					},
					fileDel:function(path,deleteFileType){
						if(deleteFileType == 1){
							this.editData.examReportName='';
							this.editData.examReportPath='';
							this.deleteFile.examReport.fileId = path;
						}else if(deleteFileType == 2){
							this.editData.rectificationReportName='';
							this.editData.rectificationReportPath='';
							this.deleteFile.rectificationReport.fileId = path;
						}
          },
          sureDelFile:function(_self){
          	var deleteFileParam = {fileId:null};
          	if(_self.deleteFile.rectificationReport.fileId != null){
          		deleteFileParam.fileId = _self.deleteFile.rectificationReport.fileId;
          		ajaxMethod(_self, 'post',
          				'fileHandle/deleteFile', true,JSON.stringify(deleteFileParam), 'json',
          				'application/json;charset=UTF-8',_self.fileDelSuccessMethod);
          	}
          	if(_self.deleteFile.rectificationReport.fileId != null){
          		deleteFileParam.fileId = _self.deleteFile.rectificationReport.fileId;
          		ajaxMethod(_self, 'post',
          				'fileHandle/deleteFile', true,JSON.stringify(deleteFileParam), 'json',
          				'application/json;charset=UTF-8',_self.fileDelSuccessMethod);
          	}
          },
          fileDelSuccessMethod:function(_self,responseData){
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
					
		       //排序
          listsort: function () {
            var imgArrow = this.imgList;
            var flagOne = 1;
            for (var i = 0; i < imgArrow.length; i++) {
              imgArrow[i].myindex = i;
              imgArrow[i].onclick = function () {
                flagOne *= -1;
                //对每个数组也就是对应表格的每一列进行排序
                switch (this.myindex){
                  case 0://测评项目名称
                    data.result.data.sort(function (a, b) {
                      return (a.examName.localeCompare(b.examName)) * flagOne
                    });
                    break;
                  case 1://测评时间
                    data.result.data.sort(function (a, b) {
                      return (a.examTime - b.examTime) * flagOne
                    });
                    break;
                  case 2://测评机构
                    data.result.data.sort(function (a, b) {
                      return (a.examOrg.localeCompare(b.examOrg)) * flagOne
                    });
                    break;
                  case 3://测评状态
                    data.result.data.sort(function (a, b) {
                      return (a.fkExamStatus - b.fkExamStatus) * flagOne
                    });
                    break;
                  case 4://测评结果
                    data.result.data.sort(function (a, b) {
                      return (a.fkExamResult - b.fkExamResult) * flagOne
                    });
                    break;
                  case 5://整改结果
                    data.result.data.sort(function (a, b) {
                      return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                    });
                    break;
                  case 6://整改时间
                    data.result.data.sort(function (a, b) {
                      return (a.rectificationDate - b.rectificationDate) * flagOne
                    });
                    break;
                }
              };

            }
          },
          
        },
        created: function() {
        	this.queryParam.fkSystemId = systemId;
        	this.queryEvaluationList(this);
        },
        mounted: function() {
        	var rowOne=document.getElementsByClassName('rowOne')[0];
          var imgList=rowOne.getElementsByTagName('img');
          this.imgList = imgList;
          this.listsort();
          //点击返回按钮 发送请求
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
            	window.location.href = originUrl + "/page/indexPage";
            }
          });
        }
      })
    })
  })
}())