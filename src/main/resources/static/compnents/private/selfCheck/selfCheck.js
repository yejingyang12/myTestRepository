/**
 * selfCheck
 */
(function () {
  var data={
  		systemName: null,
  		show:{
  			visible2: -1,
  		},
    itemdata:null,
		imgList: null,
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
    rules:{
        inspectionDate:[ //自查时间
            {required: true, message: '请输入自查时间', trigger: 'blur' },
        ],
        fkInspectionStatus:[ //自查状态
            {required: true, message: '请选择自查状态', trigger: 'blur' },
        ],
        fkInspectionReu:[ //自查结果
            {required: true, message: '请选择自查结果', trigger: 'blur' },
        ],
        fkRectificationReu:[ //整改结果
            {required: true, message: '请选择整改结果', trigger: 'blur' },
        ],
        rectificationDate:[ //整改时间
            {required: true, message: '请输入整改时间', trigger: 'blur' },
        ],
        examinationReportName:[ //自查报告
            {required: true, message: '请上传自查报告', trigger: 'blur' },
        ],
        examinationRectificationReportName:[ //整改报告
            {required: true, message: '请上传整改报告', trigger: 'blur' },
        ],
    },
    inspectionReu: [{
      value: '',
      label: '请选择'
    }, {
      value: 1,
      label: '符合'
    }, {
      value: 2,
      label: '基本符合'
    }, {
      value: 3,
      label: '不符合'
    }],
    inspectionStatus: [{
    	value: '',
    	label: '请选择'
    }, {
    	value: 1,
    	label: '未自查'
    }, {
    	value: 2,
    	label: '已自查'
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
        	this.querySystemName(this);
        	this.querySelfCheckList(this);
        },
        methods:{
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
          	if(_self.editParam.inspectionDate == '1970-01-01'){
          		_self.editParam.inspectionDate = null;
          	}
          	if(_self.editParam.rectificationDate == '1970-01-01'){
          		_self.editParam.rectificationDate = null;
          	}
          },
          
          //保存自查信息
          saveSelfexamination: function (formName) {
          	var _self=this;
          	_self.$refs[formName].validate(function (valid) {
              if (valid) {
              	ajaxMethod(_self, "post", 
              			"/selfexamination/saveSelfexamination", false ,
              			JSON.stringify(_self.editParam), "json", 
              			'application/json;charset=UTF-8', _self.saveSelfexaminationSuccess);
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
          saveSelfexaminationSuccess: function(_self, responseData){
          	$("#startBoxSelf").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              //window.location.href = originUrl+"page/addCompanyInfoPage?companyId="+companyId+"&companyCode="+companyCode;
            }, 2300);
          	_self.querySelfCheckList(_self);
          	//点击确定后关闭弹窗
          	this.closes();
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
        //点击“删除”显示弹窗
          deleteClick:function(){
         	 $(".inquiry").css("display","block");
         	$(".dialogShaw").css("display","block");
          },
        //点击 "取消" 关闭弹框
          closes:function () {
          	 var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
             evaluationAlert.style.display="none"
          },
          //删除自查信息
          deleteSelfexamination: function(selfexaminationId){
          	var _self = this;
          	 
          	var deleteParam = {"selfexaminationId": selfexaminationId,"fkSystemId": systemId};
          	ajaxMethod(_self, "post", 
          			"/selfexamination/deleteSelfexaminationBySelfexaminationId", false ,
          			JSON.stringify(deleteParam), "json", 
          			'application/json;charset=UTF-8', _self.deleteSelfexaminationSuccess);
          	_self.closes();
          },
          deleteSelfexaminationSuccess: function(_self, responseData){
          	_self.querySelfCheckList(_self);
          },
          
          onUpload: function(e){
          	var fileSize = e.target.files[0].size;//文件大小（字节）
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
          	if(fileFormat[1] != 'word' && fileFormat[1] != 'pdf' && fileFormat[1] != 'exl' && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx'){
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
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
					},
					onUploadSuccessMethod: function(_self,responseData){
						_self.editParam.examinationReportName=responseData.data.attachName;
						_self.editParam.examinationReportPath=responseData.data.uploadUrl;
					},
          onUpload2: function(e){
          	var fileSize = e.target.files[0].size;//文件大小（字节）
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
          	if(fileFormat[1] != 'word' && fileFormat[1] != 'pdf' && fileFormat[1] != 'exl' && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx'){
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
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
					},
					onUploadSuccessMethod2: function(_self,responseData){
						_self.editParam.examinationRectificationReportName=responseData.data.attachName;
						_self.editParam.examinationRectificationReportPath=responseData.data.uploadUrl;
					},
					fileDel:function(path,deleteFileType){
						var _self = this;
						if(deleteFileType == 1){
  						_self.editParam.examinationReportName='';
  						_self.editParam.examinationReportPath='';
          	}else if(deleteFileType == 2){
          		_self.editParam.examinationRectificationReportName='';
          		_self.editParam.examinationRectificationReportPath='';
          	}
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
                  case 0://系统名称
                    data.result.data.sort(function (a, b) {
                      return (a.systemName.localeCompare(b.systemName)) * flagOne
                    });
                    break;
                  case 1://自查时间
                    data.result.data.sort(function (a, b) {
                      return (new Date(a.inspectionDate.split('-').join('/')).getTime()-new Date(b.inspectionDate.split('-').join('/')).getTime()) * flagOne
                    });
                    break;
                  case 2://自查状态
                    data.result.data.sort(function (a, b) {
                      return (a.fkInspectionStatus - b.fkInspectionStatus) * flagOne
                    });
                    break;
                  case 3://自查结果
                    data.result.data.sort(function (a, b) {
                      return (a.fkInspectionReu - b.fkInspectionReu) * flagOne
                    });
                    break;
                  case 4://整改结果
                    data.result.data.sort(function (a, b) {
                      return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                    });
                    break;
                  case 5://整改时间
                    data.result.data.sort(function (a, b) {
                      return (new Date(a.rectificationDate.split('-').join('/')).getTime()-new Date(b.rectificationDate.split('-').join('/')).getTime()) * flagOne
                    });
                    break;
                }
              };
            }
          },//listsort end
          
          
        },//methods


        mounted: function() {
        	var rowOne=document.getElementsByClassName('rowOne')[0];
          var imgList=rowOne.getElementsByTagName('img');
          this.imgList = imgList;
          this.listsort();
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