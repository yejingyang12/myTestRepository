/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={
    change:false,
		evaluationId:"",
    change:false,
  	years:[],
  	yearType:'',
		tishi:"",
  	systemName: null,
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
			/*"code": "0",
			"msg": "成功",
			"pagesize": 1,
			"currentPage": 1,
			"total": 5,
			"totalPages": 5,
			"data": [],*/
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
      rules:{
          examName:[  //测评项目名称
              {required: true, message: '请输入测评项目名称', trigger: 'blur' },
              { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
          ],
          examTime:[  //测评时间
              {required: true, message: '请输入测评时间', trigger: 'blur' },
          ],
          examOrg:[  //测评机构
              {required: true, message: '请输入测评机构', trigger: 'blur' },
              { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
          ],
          examYear:[  //测评年度
              {required: true, message: '请选择测评年度', trigger: 'change' },
          ],
          fkExamStatus:[  //测评状态
              {required: true, message: '请选择测评状态', trigger: 'change' },
          ],
          fkExamResult:[  //测评结果
              {required: true, message: '请选择测评结果', trigger: 'change' },
          ],
          fkRectificationReu:[  //整改结果
              {required: true, message: '请选择整改结果', trigger: 'change' },
          ],
          rectificationDate:[  //整改时间
              {required: true, message: '请输入整改时间', trigger: 'blur' },
          ],
          examReportName:[  //测评报告
              {required: true, message: '请上传测评报告', trigger: 'blur' },
          ],
          rectificationReportName:[  //整改报告
              {required: true, message: '请上传整改报告', trigger: 'blur' },
          ],
	  },
	  examStatus: [{
    	value: '',
    	label: '请选择'
    },{
    	value: 2,
    	label: '已测评'
    }],
    examResult: [{
    	value: '',
    	label: '请选择'
    },{
    	value: 1,
    	label: '符合'
    }, {
    	value: 2,
    	label: '基本符合'
    }, {
    	value: 3,
    	label: '不符合'
    }],
    rectificationReu: [{
    	value: '',
    	label: '请选择'
    }, {
    	value: 1,
    	label: '已整改'
    }, {
    	value: 2,
    	label: '未整改'
    }],
  };
  Vue.component('testing',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/testing/testing.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
        	xuanfu:function(data){
        		this.tishi=data;
        	},
        	changeStatus : function(){
        		if(this.editData.fkExamStatus == 1){//未测评
          		this.rules.fkExamResult[0].required = false;
          		this.rules.examReportName[0].required = false;
          	}else{
          		this.rules.fkExamResult[0].required = true;
          		this.rules.examReportName[0].required = true;
          	}
        	},
        	changeResult:function(){
        		if(this.editData.fkRectificationReu == 2){
          		this.rules.rectificationReportName[0].required = false;
          		this.rules.rectificationDate[0].required = false;
          	}else{
          		this.rules.rectificationReportName[0].required = true ;
          		this.rules.rectificationDate[0].required = true;
          	}
        	},
        	//获取系统名称
        	querySystemName: function(_self) {
        		var querySystemNameParam = {systemId:systemId,};
        		ajaxMethod(_self, 'post',
                '/system/querySystemInformationBySystemId', true,
                JSON.stringify(querySystemNameParam), 'json',
                'application/json;charset=UTF-8',
                _self.querySystemNameSuccessMethod);
        	},
        	querySystemNameSuccessMethod: function(_self,data){
        		_self.systemName = data.data.systemName;
        	},
        	
        	//点击 "X" 关闭弹框
          closes:function () {
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none";
            $(".inquiry").css("display","none");
            $(".dialogShaw").css("display","none");
            this.$refs['editData'].resetFields();
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
          	if(_self.editData.examTime == '1970-01-01'){
          		_self.editData.examTime = null;
          	}
          	if(_self.editData.rectificationDate == '1970-01-01'){
          		_self.editData.rectificationDate = null;
          	}
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
          	_self.result = data.data.list;
          	
          	_self.result.totalPages = data.data.totalPage;
          	_self.result.pagesize = data.data.pageSize;
          	_self.result.currentPage = data.data.currPage;
          	_self.result.total = data.data.totalCount;
          },
        //点击“删除”显示弹窗
          deleteClick:function(evaluationId){
          	this.evaluationId=evaluationId;
         	 $(".inquiry").css("display","block");
         	 $(".dialogShaw").css("display","block");
          },
          
          //删除数据
          deleteEvaluation: function() {
          	var _self = this;
          	this.show.visible2 = -1;
          	var deleteParam = {"evaluationId":this.evaluationId,"fkSystemId":systemId};
          	ajaxMethod(_self, 'post',
              '/evaluation/deleteEvaluation', true,
              JSON.stringify(deleteParam), 'json',
              'application/json;charset=UTF-8',
              _self.deleteEvaluationSuccessMethod);
          	_self.closes();
          	
          },
          deleteEvaluationSuccessMethod: function(_self,data){
          	_self.queryEvaluationList(_self);
          },
          //添加或编辑确定保存数据
          saveEvaluation: function(formName){
          	var _self = this;
          	_self.$refs[formName].validate(function (valid) {
              if (valid) {
              	ajaxMethod(_self, 'post',
                    '/evaluation/saveEvaluation', true,
                    JSON.stringify(_self.editData), 'json',
                    'application/json;charset=UTF-8',
                    _self.saveEvaluationSuccessMethod);
              } else {
                _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                  confirmButtonText: '确定',
                  callback: function callback(action) {
                  }
                });
                return false;
              }
            });
          },
          saveEvaluationSuccessMethod: function(_self,data){
          	$("#startBoxTest").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              //window.location.href = originUrl+encodeURI("page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode);
            }, 2300);
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
          	var fileSize;
          	if(e.target.files.length!=0){
          		fileSize = e.target.files[0].size;//文件大小（字节）                 		
	          	var fimeMax = 1048576 *30;
	          	if(fileSize > fimeMax){
	          		this.$alert('文件不能大于30M！', '信息提示', {
	                confirmButtonText: '确定',
	                callback: function callback(action) {
	                }
	              });
	          		return;
	          	}
	          	var fileFormat = e.target.value.split(".");//文件后缀
            	var fileFormatLength = fileFormat.length;
            	if(fileFormatLength){
            		var i = fileFormatLength - 1;
            		if(fileFormat[i] != 'pdf' && fileFormat[i] != 'sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip' && fileFormat[i] !='sep'){
            			this.$alert('不接受此文件类型！', '信息提示', {
	            			confirmButtonText: '确定',
	            			callback: function callback(action) {
	            			}
	            		});
	            		return;
            		}
            		var uploadData = new FormData(); 
            		uploadData.append('file', e.target.files[0]);
            		uploadData.append('type', 'test');
            		ajaxUploadMethod(this, 'POST','/fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
            	}
          	}
					},
					onUploadSuccessMethod: function(_self,responseData){
//						this.$refs.refOnUpload.value = null;
						_self.editData.examReportName=responseData.data.attachName;
						_self.editData.examReportPath=responseData.data.uploadUrl;
						this.$refs.examReportName.clearValidate();
					},
					onUpload2: function(e){
						var fileSize;
          	if(e.target.files.length!=0){
          		fileSize = e.target.files[0].size;//文件大小（字节）                 		
	          	var fimeMax = 1048576 *30;
	          	if(fileSize > fimeMax){
	          		this.$alert('文件不能大于30M！', '信息提示', {
	                confirmButtonText: '确定',
	                callback: function callback(action) {
	                }
	              });
	          		return;
	          	}
	          	var fileFormat = e.target.value.split(".");//文件后缀
            	var fileFormatLength = fileFormat.length;
            	if(fileFormatLength){
            		var i = fileFormatLength - 1;
            		if(fileFormat[i] != 'pdf' && fileFormat[i] != 'sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip'){
            			this.$alert('不接受此文件类型！', '信息提示', {
	            			confirmButtonText: '确定',
	            			callback: function callback(action) {
	            			}
	            		});
	            		return;
            		}
            		var uploadData = new FormData(); 
            		uploadData.append('file', e.target.files[0]);
            		uploadData.append('type', 'test');
            		ajaxUploadMethod(this, 'POST','/fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
            	}
          	}
					},
					onUploadSuccessMethod2: function(_self,responseData){
//						this.$refs.refOnUpload2.value = null;
						_self.editData.rectificationReportName=responseData.data.attachName;
						_self.editData.rectificationReportPath=responseData.data.uploadUrl;
						this.$refs.rectificationReportName.clearValidate();
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
          	window.location.href = originUrl+encodeURI("/fileHandle/downloadFile?uploadUrl="+uploadUrl+"&attachName="+attachName+"&fileId="+fileId);
					},
					
		       //排序
          listsort1: function () {
            var imgArrow = this.imgList;
            var flagOne = 1;
            for (var i = 0; i < imgArrow.length; i++) {
              imgArrow[i].myindex = i;
              imgArrow[i].onclick = function () {
                flagOne *= -1;
                //对每个数组也就是对应表格的每一列进行排序
                switch (this.myindex){
                  case 0://测评项目名称
                    data.result.sort(function (a, b) {
                      return (a.examName.localeCompare(b.examName)) * flagOne
                    });
                    break;
                  case 1://测评时间
                    data.result.sort(function (a, b) {
                      return (new Date(a.examTime.split('-').join('/')).getTime()-new Date(b.examTime.split('-').join('/')).getTime()) * flagOne
                    });
                    break;
                  case 2://测评机构
                    data.result.sort(function (a, b) {
                      return (a.examOrg.localeCompare(b.examOrg)) * flagOne
                    });
                    break;
                  case 3://测评状态
                    data.result.sort(function (a, b) {
                      return (a.fkExamStatus - b.fkExamStatus) * flagOne
                    });
                    break;
                  case 4://测评结果
                    data.result.sort(function (a, b) {
                      return (a.fkExamResult - b.fkExamResult) * flagOne
                    });
                    break;
                  case 5://整改结果
                    data.result.sort(function (a, b) {
                      return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                    });
                    break;
                  case 6://整改时间
                    data.result.sort(function (a, b) {
                      return (new Date(a.rectificationDate.split('-').join('/')).getTime()-new Date(b.rectificationDate.split('-').join('/')).getTime()) * flagOne
                    });
                    break;
                }
              };

            }
          },
          
        },
        created: function() {
        	this.queryParam.fkSystemId = systemId;
        	this.querySystemName(this);
        	this.queryEvaluationList(this);
        	
        	 var date=new Date;
           var year=date.getFullYear(); 
           for(var i = year ; i>= 2013 ; i--){
           	this.years.push({
               "value":i,
               "label":i
             })
           } 
        },
        mounted: function() {
        	var rowOne=document.getElementsByClassName('rowOne1')[0];
          var imgList=rowOne.getElementsByTagName('img');
          this.imgList = imgList;
          this.listsort1();
          //点击返回按钮 发送请求
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
            	window.location.href = originUrl+encodeURI("/page/indexPage");
            }
          });
        }
      })
    })
  })
}())