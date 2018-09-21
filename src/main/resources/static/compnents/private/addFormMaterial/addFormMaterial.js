/**
 * Created by timha on 2018/5/24.
 */
var data={
		changeFlag:false,
		yesOrNotSubmit:false,
		returnIndex:false,
		saveSuccess:false,//保存成功弹窗
		saveThePrompt:false,//提示保存弹窗
		saveYesOrNo:false,//判断提交时是否保存
		fileId1:"",
		fileId2:"",
		fileId3:"",
		fileId4:"",
		retuenCheck:false,
		substitute:"",
		companyBySession:"",
		systemBySession:"",
		gradingBySession:"",
		flag:false,
		flag1:false,
    check : false,
    ceshi:false,
    ceshi1:false,
    ceshi2:false,
    ceshi3:false,
/*    dialogVisibles:false,
    dialogVisible:false,*/
    dialogVisibled:false,
/*    dialogVisibleds:false,*/
    jurisdictionType:0,
    submitCheck:false,
        formData:{
      		systemMaterialsId: '',
      		fkSystemId: '',
      		topologyDescriptionList: [],
          topologyDescriptionName:'',
      		organizationManagementList: [],
          organizationManagementName:'',
      		implementationPlanList: [],
          implementationPlanName:'',
      		licenseCertificateList: [],
          licenseCertificateName:'',
      		evaluationPresentationId: '',
      		evaluationPresentationName: '',
      		evaluationPresentationPath: '',
      		expertReviewId: '',
      		expertReviewName: '',
      		expertReviewPath: '',
      		directorOpinionId: '',
      		directorOpinionName: '',
      		directorOpinionPath: '',
          changeType:'',
          saveType:''
      	},
        rules:{
            topologyDescriptionName:[
                {required: true, message: '请上传系统拓扑结构及说明', trigger: 'change' }
            ],
            organizationManagementName:[
                {required: true, message: '请上传系统安全组织机构及管理制度', trigger: 'change' }
            ],
            implementationPlanName:[
                {required: true, message: '请上传系统安全保护设施设计实施方案或改建实施方案', trigger: 'change' }
            ],
            licenseCertificateName:[
                {required: true, message: '请上传系统使用的安全产品清单及认证、销售许可证明', trigger: 'change' }
            ]
        },
        beginContent:{
      		systemMaterialsId: '',
      		fkSystemId: '',
      		topologyDescriptionList: [],
          topologyDescriptionName:'',
      		organizationManagementList: [],
          organizationManagementName:'',
      		implementationPlanList: [],
          implementationPlanName:'',
      		licenseCertificateList: [],
          licenseCertificateName:'',
      		evaluationPresentationId: '',
      		evaluationPresentationName: '',
      		evaluationPresentationPath: '',
      		expertReviewId: '',
      		expertReviewName: '',
      		expertReviewPath: '',
      		directorOpinionId: '',
      		directorOpinionName: '',
      		directorOpinionPath: '',
          changeType:'',
          saveType:''
      	},
        tishi:"",
        causeFailure:""
    };
(function () {
    Vue.component('addFormMaterial',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addFormMaterial/addFormMaterial.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                  xuanfu:function(data){
                    this.tishi=data;
                  },
                  onUpload: function(e){
                  	var fileSize;
                  	if(e.target.files.length!=0){
                  		fileSize = e.target.files[0].size;//文件大小（字节）                 		
	                  	var fimeMax = 1048576 *30;
	                  	if(fileSize > fimeMax){
	                  		/*this.$alert('文件不能大于30M！', '信息提示', {
	                        confirmButtonText: '确定',
	                        callback: function callback(action) {
	                        }
	                      });*/
	                  	  this.causeFailure="文件过大";
	                  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
	                  		return;
	                  	}
	                  	var fileFormat = e.target.value.split(".");//文件后缀
	                  	var fileFormatLength = fileFormat.length;
	                  	if(fileFormatLength){
	                  		var i = fileFormatLength - 1;
	                  		if(fileFormat[i] != 'pdf' && fileFormat[i] !='sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip'){
	                  			/*this.$alert('不接受此文件类型！', '信息提示', {
		                  			confirmButtonText: '确定',
		                  			callback: function callback(action) {
		                  			}
		                  		});*/
	                  		  this.causeFailure="格式不正确";
	                  		  $("#startBoxImportFailed").show().delay(2000).fadeOut();
		                  		return;
	                  		}
	                  		var uploadData = new FormData(); 
	                  		uploadData.append('file', e.target.files[0]);
	                  		uploadData.append('type', 'test');
	                  		ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
	                  		e.target.value = "";
	                  		$("#startBoxImporting").css('display', 'block');
	                  	}
                  	}
                  },
                  onUploadSuccessMethod: function(_self,responseData){
                    $("#startBoxImporting").css('display', 'none');
                  	var submitDescName=false;
                  	var topologyDescription = {fileId: '',attachName: '',uploadUrl: ''};
                  	if(_self.formData.topologyDescriptionList.length==0){
                  		topologyDescription.attachName = responseData.data.attachName;
                    	topologyDescription.uploadUrl = responseData.data.uploadUrl;
                    	_self.formData.topologyDescriptionList.push(topologyDescription);
                    	_self.setShowAttachName(_self);
                   	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                   	 	$('#fileList').append(fileHtml);
                  	}else{
                  		for(var t=0;t<_self.formData.topologyDescriptionList.length;t++){
              			  	if(_self.formData.topologyDescriptionList[t].attachName==responseData.data.attachName){
              			  		/*_self.$alert('<strong>文件相同！</strong>', '提示', {
	           			          dangerouslyUseHTMLString: true
	                  				});*/
              			  	  this.causeFailure="文件相同";
              			  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
              			  		submitDescName = true ; 
                  				break;
                  			}
                    	}
                  		if(!submitDescName){
                  			topologyDescription.attachName = responseData.data.attachName;
                      	topologyDescription.uploadUrl = responseData.data.uploadUrl;
                      	_self.formData.topologyDescriptionList.push(topologyDescription);
                      	_self.setShowAttachName(_self);
                     	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                     	 	$('#fileList').append(fileHtml);
                  		}
                  	}
                  	$(".del").click(function(){
                      	var uploadUrl = $(this).parent("li").find("input").val();
                      	uploadUrl = uploadUrl.split("&");
                        var len = _self.formData.topologyDescriptionList.length;
                        for(var i=0;i<len;i++){
                        	if(_self.formData.topologyDescriptionList[i].uploadUrl == uploadUrl[0]){
                        		_self.formData.topologyDescriptionList.splice(i,1);
                        		$(this).parent("li").remove();
                        		_self.$refs.refOnUpload.value = null;
                        		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                        		break;
                        	}
                        }
                        _self.setShowAttachName(_self);
                    });
                  	//这是在新建表四页面，点击附件就可下载的代码
                   /* $(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });*/
                    _self.$refs.topologyDescriptionName.clearValidate();
                    _self.$refs.formData.validateField('topologyDescriptionName');
                  },
                  onUpload2: function(e){
                  	var fileSize;
                  	if(e.target.files.length!=0){
                  		fileSize = e.target.files[0].size;//文件大小（字节）                 		
	                  	var fimeMax = 1048576 *30;
	                  	if(fileSize > fimeMax){
	                  		/*this.$alert('文件不能大于30M！', '信息提示', {
	                        confirmButtonText: '确定',
	                        callback: function callback(action) {
	                        }
	                      });*/
	                  	  this.causeFailure="文件过大";
	                  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
	                  		return;
	                  	}
	                  	var fileFormat = e.target.value.split(".");//文件后缀
	                  	var fileFormatLength = fileFormat.length;
	                  	if(fileFormatLength){
	                  		var i = fileFormatLength - 1;
	                  		if(fileFormat[i] != 'pdf' && fileFormat[i] !='sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip'){
	                  			/*this.$alert('不接受此文件类型！', '信息提示', {
		                  			confirmButtonText: '确定',
		                  			callback: function callback(action) {
		                  			}
		                  		});*/
	                  		  this.causeFailure="格式不正确";
	                  		  $("#startBoxImportFailed").show().delay(2000).fadeOut();
		                  		return;
	                  		}
	                  		var uploadData = new FormData(); 
	                  		uploadData.append('file', e.target.files[0]);
	                  		uploadData.append('type', 'test');
	                  		ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
	                  		e.target.value = "";
	                  		$("#startBoxImporting").css('display', 'block');
	                  	}
                  	}
                  },
                  onUploadSuccessMethod2: function(_self,responseData){
                    $("#startBoxImporting").css('display', 'none');
                    var submitManagName=false;
                  	var organizationManagement = {fileId: '',attachName: '',uploadUrl: ''};
                    if(_self.formData.organizationManagementList.length==0){
                    	organizationManagement.attachName = responseData.data.attachName;
                    	organizationManagement.uploadUrl = responseData.data.uploadUrl;
                    	_self.formData.organizationManagementList.push(organizationManagement);
                    	_self.setShowAttachName(_self);
                   	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                   	 	$('#fileList2').append(fileHtml);
                  	}else{
                  		for(var t=0;t<_self.formData.organizationManagementList.length;t++){
              			  	if(_self.formData.organizationManagementList[t].attachName==responseData.data.attachName){
              			  		/*_self.$alert('<strong>文件相同！</strong>', '提示', {
	           			          dangerouslyUseHTMLString: true
	                  				});*/
              			  	  this.causeFailure="文件相同";
              			  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
              			  		submitManagName = true ; 
                  				break;
                  			}
                    	}
                  		if(!submitManagName){
                  			organizationManagement.attachName = responseData.data.attachName;
                      	organizationManagement.uploadUrl = responseData.data.uploadUrl;
                      	_self.formData.organizationManagementList.push(organizationManagement);
                      	_self.setShowAttachName(_self);
                     	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                     	 	$('#fileList2').append(fileHtml);
                  		}
                  	}
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.organizationManagementList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.organizationManagementList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.organizationManagementList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.$refs.refOnUpload2.value = null;
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.organizationManagementName='';
                      _self.formData.organizationManagementPath='';*/
                    });
                    /*$(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });*/
                    _self.$refs.organizationManagementName.clearValidate();
                    _self.$refs.formData.validateField('organizationManagementName');
                  },
                  onUpload3: function(e){
                  	var fileSize;
                  	if(e.target.files.length!=0){
                  		fileSize = e.target.files[0].size;//文件大小（字节）                 		
	                  	var fimeMax = 1048576 *30;
	                  	if(fileSize > fimeMax){
	                  		/*this.$alert('文件不能大于30M！', '信息提示', {
	                        confirmButtonText: '确定',
	                        callback: function callback(action) {
	                        }
	                      });*/
	                  	  this.causeFailure="文件过大";
	                  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
	                  		return;
	                  	}
	                  	var fileFormat = e.target.value.split(".");//文件后缀
	                  	var fileFormatLength = fileFormat.length;
	                  	if(fileFormatLength){
	                  		var i = fileFormatLength - 1;
	                  		if(fileFormat[i] != 'pdf' && fileFormat[i] !='sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip'){
	                  			/*this.$alert('不接受此文件类型！', '信息提示', {
		                  			confirmButtonText: '确定',
		                  			callback: function callback(action) {
		                  			}
		                  		});*/
	                  		  this.causeFailure="文件过大";this.causeFailure="格式不正确";
	                  		  $("#startBoxImportFailed").show().delay(2000).fadeOut();
		                  		return;
	                  		}
	                  		var uploadData = new FormData(); 
	                  		uploadData.append('file', e.target.files[0]);
	                  		uploadData.append('type', 'test');
	                  		ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod3);
	                  		e.target.value = "";
	                  		$("#startBoxImporting").css('display', 'block');
	                  	}
                  	}
                  },
                  onUploadSuccessMethod3: function(_self,responseData){
                    $("#startBoxImporting").css('display', 'none');
                    var submitPlanName=false;
                  	var implementationPlan = {fileId: '',attachName: '',uploadUrl: ''};
                    if(_self.formData.implementationPlanList.length==0){
                    	implementationPlan.attachName = responseData.data.attachName;
                    	implementationPlan.uploadUrl = responseData.data.uploadUrl;
                    	_self.formData.implementationPlanList.push(implementationPlan);
                    	_self.setShowAttachName(_self);
                   	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                   	 	$('#fileList3').append(fileHtml);
                  	}else{
                  		for(var t=0;t<_self.formData.implementationPlanList.length;t++){
              			  	if(_self.formData.implementationPlanList[t].attachName==responseData.data.attachName){
              			  		_/*self.$alert('<strong>文件相同！</strong>', '提示', {
	           			          dangerouslyUseHTMLString: true
	                  				});*/
              			  		this.causeFailure="文件相同";
              			  		$("#startBoxImportFailed").show().delay(2000).fadeOut();
              			  		submitPlanName = true ; 
                  				break;
                  			}
                    	}
                  		if(!submitPlanName){
                  			implementationPlan.attachName = responseData.data.attachName;
                      	implementationPlan.uploadUrl = responseData.data.uploadUrl;
                      	_self.formData.implementationPlanList.push(implementationPlan);
                      	_self.setShowAttachName(_self);
                     	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                     	 	$('#fileList3').append(fileHtml);
                  		}
                  	}
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.implementationPlanList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.implementationPlanList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.implementationPlanList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.$refs.refOnUpload3.value = null;
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.implementationPlanName='';
                      _self.formData.implementationPlanPath='';*/
                    });
                    /*$(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });*/
                    _self.$refs.implementationPlanName.clearValidate();
                    _self.$refs.formData.validateField('implementationPlanName');
                  },
                  onUpload4: function(e){
                  	var fileSize = e.target.files[0].size;//文件大小（字节）
                  	var fimeMax = 1048576 *30;
                  	if(fileSize > fimeMax){
                  		/*this.$alert('文件不能大于30M！', '信息提示', {
                        confirmButtonText: '确定',
                        callback: function callback(action) {
                        }
                      });*/
                  	  this.causeFailure="文件过大";
                  	  $("#startBoxImportFailed").show().delay(2000).fadeOut();
                  		return;
                  	}
                  	var fileFormat = e.target.value.split(".");//文件后缀
                  	var fileFormatLength = fileFormat.length;
                  	if(fileFormatLength){
                  		var i = fileFormatLength - 1;
                  		if(fileFormat[i] != 'pdf' && fileFormat[i] !='sep' && fileFormat[i] != 'xls' && fileFormat[i] != 'xlsm'&& fileFormat[i] != 'xlsx'  && fileFormat[i] != 'rar' && fileFormat[i] !='doc' && fileFormat[i] !='docx' && fileFormat[i] !='zip'){
                  			/*this.$alert('不接受此文件类型！', '信息提示', {
	                  			confirmButtonText: '确定',
	                  			callback: function callback(action) {
	                  			}
	                  		});*/
                  		  this.causeFailure="格式不正确";
                  		  $("#startBoxImportFailed").show().delay(2000).fadeOut();
	                  		return;
                  		}
                  		var uploadData = new FormData(); 
                  		uploadData.append('file', e.target.files[0]);
                  		uploadData.append('type', 'test');
                  		ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod4);
                  		e.target.value = "";
                  		$("#startBoxImporting").css('display', 'block');
                  	}
                  },
                  onUploadSuccessMethod4: function(_self,responseData){
                    $("#startBoxImporting").css('display', 'none');
                  	var submitCateName=false;
                  	var licenseCertificate = {fileId: '',attachName: '',uploadUrl: ''};
                  	if(_self.formData.licenseCertificateList.length==0){
                  		licenseCertificate.attachName = responseData.data.attachName;
                  		licenseCertificate.uploadUrl = responseData.data.uploadUrl;
                    	_self.formData.licenseCertificateList.push(licenseCertificate);
                    	_self.setShowAttachName(_self);
                   	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                   	 	$('#fileList4').append(fileHtml);
                  	}else{
                  		for(var t=0;t<_self.formData.licenseCertificateList.length;t++){
              			  	if(_self.formData.licenseCertificateList[t].attachName==responseData.data.attachName){
              			  		_/*self.$alert('<strong>文件相同！</strong>', '提示', {
	           			          dangerouslyUseHTMLString: true
	                  				});*/
              			  		this.causeFailure="文件相同";
              			  		$("#startBoxImportFailed").show().delay(2000).fadeOut();
              			  		submitCateName = true;
                  				break;
                  			}
                    	}
                  		if(!submitCateName){
                  			licenseCertificate.attachName = responseData.data.attachName;
                    		licenseCertificate.uploadUrl = responseData.data.uploadUrl;
                      	_self.formData.licenseCertificateList.push(licenseCertificate);
                      	_self.setShowAttachName(_self);
                     	 	var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                     	 	$('#fileList4').append(fileHtml);
                  		}
                  	}
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.licenseCertificateList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.licenseCertificateList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.licenseCertificateList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.$refs.refOnUpload4.value = null;
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.licenseCertificateName='';
                      _self.formData.licenseCertificatePath='';*/
                    });
                    /*$(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });*/
                    _self.$refs.licenseCertificateName.clearValidate();
                    _self.$refs.formData.validateField('licenseCertificateName');
                  },
                  fileDel:function(path,type){
                    var url='';
                    if(type==1){
                      url = '{"uploadUrl":"'+path+'"}';
                    }else if(type==2){
                      url = '{"fileId":"'+path+'"}'
                    }else{
                    	url = '{"fileId":""}'
                    }
                    ajaxMethod(this, 'post',
                        'fileHandle/deleteFile', false,url, 'json',
                        'application/json;charset=UTF-8',this.fileDelSuccessMethod);
                  },
                  fileDelSuccessMethod:function(_self,responseData){
                      $(this).parent("li").remove();
                  },
                  fileDownload:function(path,type,name){
                    
                    if(type=='1'){
                      //下载路径
                      window.location.href = originUrl+encodeURI("fileHandle/downloadFile?uploadUrl="+path+"&attachName="+name);
                    }else{
                      //下载路径
                      window.location.href = originUrl+encodeURI("fileHandle/downloadFile?fileId="+path);
                    }
                      
                  },
                  setShowAttachName: function(_self) {
                  	if(_self.formData.topologyDescriptionList == null){
                  		_self.formData.topologyDescriptionList = [];
                  	}
                  	_self.formData.topologyDescriptionName = '';
                  	var len_topologyDescriptionList = _self.formData.topologyDescriptionList.length;
                  	if(len_topologyDescriptionList > 0){
                  		for(var i=0;i<len_topologyDescriptionList;i++){
                  			if(i==0){
                  				_self.formData.topologyDescriptionName = _self.formData.topologyDescriptionList[i].attachName;
                  			}else{
                  				_self.formData.topologyDescriptionName = _self.formData.topologyDescriptionName +'  ,  '+ _self.formData.topologyDescriptionList[i].attachName;
                  			}
                  		}
                  	}
                  	if(_self.formData.organizationManagementList == null){
                  		_self.formData.organizationManagementList = [];
                  	}
                  	_self.formData.organizationManagementName = '';
                  	var len_organizationManagementList = _self.formData.organizationManagementList.length;
                  	if(len_organizationManagementList > 0){
                  		for(var i=0;i<len_organizationManagementList;i++){
                  			if(i==0){
                  				_self.formData.organizationManagementName = _self.formData.organizationManagementList[i].attachName;
                  			}else{
                  				_self.formData.organizationManagementName = _self.formData.organizationManagementName +'  ,  '+ _self.formData.organizationManagementList[i].attachName;
                  			}
                  		}
                  	}
                  	if(_self.formData.implementationPlanList == null){
                  		_self.formData.implementationPlanList = [];
                  	}
                  	_self.formData.implementationPlanName = '';
                  	var len_implementationPlanList = _self.formData.implementationPlanList.length;
                  	if(len_implementationPlanList > 0){
                  		for(var i=0;i<len_implementationPlanList;i++){
                  			if(i==0){
                  				_self.formData.implementationPlanName = _self.formData.implementationPlanList[i].attachName;
                  			}else{
                  				_self.formData.implementationPlanName = _self.formData.implementationPlanName +'  ,  '+ _self.formData.implementationPlanList[i].attachName;
                  			}
                  		}
                  	}
                  	if(_self.formData.licenseCertificateList == null){
                  		_self.formData.licenseCertificateList = [];
                  	}
                  	_self.formData.licenseCertificateName = '';
                  	var len_licenseCertificateList = _self.formData.licenseCertificateList.length;
                  	if(len_licenseCertificateList > 0){
                  		for(var i=0;i<len_licenseCertificateList;i++){
                  			if(i==0){
                  				_self.formData.licenseCertificateName = _self.formData.licenseCertificateList[i].attachName;
                  			}else{
                  				_self.formData.licenseCertificateName = _self.formData.licenseCertificateName +'  ,  '+ _self.formData.licenseCertificateList[i].attachName;
                  			}
                  		}
                  	}
                  },
                  getMaterialsInfo: function(_self,id) {
                    ajaxMethod(_self, 'post','grading/queryEditSystemMaterialsInfo', true,
                        '{"fkSystemId":"'+id+'"}', 'json',
                        'application/json;charset=UTF-8',
                        _self.getMaterialsInfoSuccessMethod);
                  },
                  placeBeginContent:function(_self){
                  	_self.beginContent.topologyDescriptionName = _self.formData.topologyDescriptionName;//系统拓扑结构及说明
                    _self.beginContent.organizationManagementName = _self.formData.organizationManagementName;//系统安全组织机构及管理制度
                    _self.beginContent.implementationPlanName = _self.formData.implementationPlanName; //系统安全保护设施设计实施方案或改建实施方案
                    _self.beginContent.licenseCertificateName = _self.formData.licenseCertificateName;//系统使用的安全产品清单及认证、销售许可证明
                    _self.beginContent.evaluationPresentationName = _self.formData.evaluationPresentationName;//系统等级测评报告
                    _self.beginContent.expertReviewName = _self.formData.expertReviewName;//专家评审情况
                    _self.beginContent.directorOpinionName = _self.formData.directorOpinionName;//上级主管部门审批意见
                  },
                  // 获取回显材料信息成功
                  getMaterialsInfoSuccessMethod : function(_self, responseData) {
                    if(responseData.data!=null){
                    	
                      _self.formData = responseData.data;
                      _self.setShowAttachName(_self);
                      //第一次进来，将本页面内容存入beginContent中，离开页面时进行判断，如果修改过就弹窗
                      //因为后台传过来的数据是数组，所以放置处理后的数据
                      _self.placeBeginContent(_self);
                    }
                    
                    if(responseData.data.topologyDescriptionName!=null){
                    	var fileHtml='';
                    	var len_topologyDescriptionList = _self.formData.topologyDescriptionList.length;
                      for(var i=0;i<len_topologyDescriptionList;i++){
                      	fileHtml = fileHtml +'<li><input type="hidden" value="'+responseData.data.topologyDescriptionList[i].fileId+'"/><div class="fl updwon">'+responseData.data.topologyDescriptionList[i].attachName+'</div><i class="el-icon-close fl del1"></i></li>'
                      };
                     
                      $("#fileList").html(fileHtml);
                      $(".del1").click(function(){
                      	var fileId1 = $(this).parent("li").find("input").val();
                      	_self.fileId1 = fileId1+","+_self.fileId1;
                      	$(this).parent("li").hide();
                      	var fileName1 = $(this).parent("li").find("div").html();
                    		var arr1 = _self.formData.topologyDescriptionName.split(",");
                    		if(arr1.length==1){
                    			_self.formData.topologyDescriptionName = _self.formData.topologyDescriptionName.replace(fileName1,"");
                    		}else{
                    			if(arr1[arr1.length-1].trim() == fileName1){
                    				_self.formData.topologyDescriptionName = _self.formData.topologyDescriptionName.replace('  ,  '+fileName1,"");
                    			}else{
                    				_self.formData.topologyDescriptionName = _self.formData.topologyDescriptionName.replace(fileName1+'  ,  ',""); 
                    			}
                    		}
                        for(var i=0;i<len_topologyDescriptionList;i++){
                        	if(fileName1 == _self.formData.topologyDescriptionList[i].attachName){
                        		_self.formData.topologyDescriptionList.splice(i,1);
                        	}
                        }
                    		
                      });
                      /*$(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        _self.fileDownload(fileId,2);
                      });*/
                    }
                    
                    if(responseData.data.organizationManagementName!=null){
                    	var fileHtml='';
                      var len_organizationManagementList = _self.formData.organizationManagementList.length;
                      for(var i=0;i<len_organizationManagementList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.organizationManagementList[i].fileId+'"/><div class="fl updwon">'+responseData.data.organizationManagementList[i].attachName+'</div><i class="el-icon-close fl del2"></i></li>'
                      }
                      $("#fileList2").html(fileHtml);
                      $(".del2").click(function(){
                      	var fileId2 = $(this).parent("li").find("input").val();
                      	_self.fileId2 = fileId2+","+_self.fileId2;
                      	$(this).parent("li").hide();
                      	var fileName2 = $(this).parent("li").find("div").html();
                      	var arr2 = _self.formData.organizationManagementName.split(",");
                      	if(arr2.length==1){
                    			_self.formData.organizationManagementName = _self.formData.organizationManagementName.replace(fileName2,"");
                      	}else{
                      		if(arr2[arr2.length-1].trim() == fileName2){
                      			_self.formData.organizationManagementName = _self.formData.organizationManagementName.replace('  ,  '+fileName2,"");
                      		}else{
                      			_self.formData.organizationManagementName = _self.formData.organizationManagementName.replace(fileName2+'  ,  ',""); 
                      		}
                      	}
                      	for(var i=0;i<len_organizationManagementList;i++){
                        	if(fileName2 == _self.formData.organizationManagementList[i].attachName){
                        		_self.formData.organizationManagementList.splice(i,1);
                        	}
                        }
                      });
                      /*$(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });*/
                    }
                    
                    if(responseData.data.implementationPlanName!=null){
                    	var fileHtml='';
                      var len_implementationPlanList = _self.formData.implementationPlanList.length;
                      for(var i=0;i<len_implementationPlanList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.implementationPlanList[i].fileId+'"/><div class="fl updwon">'+responseData.data.implementationPlanList[i].attachName+'</div><i class="el-icon-close fl del3"></i></li>'
                      }
                      $("#fileList3").html(fileHtml);
                      $(".del3").click(function(){
                      	var fileId3 = $(this).parent("li").find("input").val();
                      	_self.fileId3 = fileId3+","+_self.fileId3;
                      	$(this).parent("li").hide();
                      	var fileName3 = $(this).parent("li").find("div").html();
                      	var arr3 = _self.formData.implementationPlanName.split(",");
                      	if(arr3.length==1){
                    			_self.formData.implementationPlanName = _self.formData.implementationPlanName.replace(fileName3,"");
                      	}else{
                      		if(arr3[arr3.length-1].trim() == fileName3){
                      			_self.formData.implementationPlanName = _self.formData.implementationPlanName.replace('  ,  '+fileName3,"");
                      		}else{
                      			_self.formData.implementationPlanName = _self.formData.implementationPlanName.replace(fileName3+'  ,  ',""); 
                      		}
                      	}
                      	for(var i=0;i<len_implementationPlanList;i++){
                        	if(fileName3 == _self.formData.implementationPlanList[i].attachName){
                        		_self.formData.implementationPlanList.splice(i,1);
                        	}
                        }
                      });
                      /*$(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });*/
                    }
                    
                    if(responseData.data.licenseCertificateName!=null){
                    	var fileHtml='';
                      var len_licenseCertificateList = _self.formData.licenseCertificateList.length;
                      for(var i=0;i<len_licenseCertificateList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.licenseCertificateList[i].fileId+'"/><div class="fl updwon">'+responseData.data.licenseCertificateList[i].attachName+'</div><i class="el-icon-close fl del4"></i></li>'
                      }
                      $("#fileList4").html(fileHtml);
                      $(".del4").click(function(){
                      	var fileId4 = $(this).parent("li").find("input").val();
                      	_self.fileId4 = fileId4+","+_self.fileId4;
                      	$(this).parent("li").hide();
                      	var fileName4 = $(this).parent("li").find("div").html();
                      	var arr4 = _self.formData.licenseCertificateName.split(",");
                      	if(arr4.length==1){
                    			_self.formData.licenseCertificateName = _self.formData.licenseCertificateName.replace(fileName4,"");
                      	}else{
                      		if(arr4[arr4.length-1].trim() == fileName4){
                      			_self.formData.licenseCertificateName = _self.formData.licenseCertificateName.replace('  ,  '+fileName4,"");
                      		}else{
                      			_self.formData.licenseCertificateName = _self.formData.licenseCertificateName.replace(fileName4+'  ,  ',""); 
                      		}
                      	}
                      	for(var i=0;i<len_licenseCertificateList;i++){
                        	if(fileName4 == _self.formData.licenseCertificateList[i].attachName){
                        		_self.formData.licenseCertificateList.splice(i,1);
                        	}
                        }
                      });
                      /*$(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });*/
                    }
                  },
                  downloadFile:function(str) {
                    window.location.href = originUrl+encodeURI("fileHandle/downloadFile?fileId="+str);
                  },
                  submitGradeMaterialsInfo: function() {
                  	this.formData.changeType = "2";
                  	this.formData.saveType = "2";
                    ajaxMethod(this, 'post',
                    'grading/saveSystemMaterialsInfo', true,
                    JSON.stringify(this.formData), 'json',
                    'application/json;charset=UTF-8',
                    this.submitGradeMaterialsInfoSuccessMethod);
                  },
                  // 获取材料信息成功
                  submitGradeMaterialsInfoSuccessMethod : function(_self, responseData) {
                    //window.location.href = originUrl+encodeURI("page/addCompanyMaterialPage?systemId=f7821c57865d4983b9db6f8db08efb3c");
                    
                     },
                  getPermitJurisdictionInfo: function(_self){
                    ajaxMethod(_self,"post",
                        "jurisdiction/queryDataJurisdictionApi",false,
                        JSON.stringify(""),"json",
                        'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
                  },
                  getPermitJurisdictionSuccess: function(_self,response){
                    for (var i = 0; i < response.data.permssions.length; i++) {
                      var permssions = response.data.permssions[i];
                      if(permssions==S_STR_PERMIT_PARAM_ENTERPRISE_CREATE){
                        _self.jurisdictionType = 1;
                      }
                      if(permssions==S_STR_PERMIT_PARAM_HEADQUARTERS_CREATE){
                        _self.jurisdictionType = 2;
                      }
                    }
                  },
                  getMaterialsInfoBySession:function(_self){
                  	ajaxMethod(_self,"post",
                        "grading/quertSystemMaterialsSession",false,
                        JSON.stringify(""),"json",
                        'application/json;charset=UTF-8', _self.quertSystemMaterialsSessionSuccess);
                  },
                  quertSystemMaterialsSessionSuccess : function(_self,responseData){
                  	if(responseData.data!=null){
                  		_self.substitute = responseData;
                  		_self.formData = responseData.data;
                  		//从数据库中查询底部5，6，7情况的数据，因为再上一步定级中用户可能会改变这些数据
                  		_self.queryBottomData(systemId);
                  		_self.flag = true;
                  		_self.setShowAttachName(_self);
                  	}
                  },
                  //从数据库中查询5，6，7的情况，因为有可能存到缓存，但是用户点击上一步到定级改变了一些数据，但是从缓存中是取不到的
                  queryBottomData:function(id){
                  	ajaxMethod(this, 'post','grading/queryEditSystemMaterialsInfo', false,
                  			'{"fkSystemId":"'+id+'"}', 'json',
                  			'application/json;charset=UTF-8',
                  			this.getBottomDataSuccess);
                  },
                  getBottomDataSuccess:function(_self,responseData){
                  	_self.formData.evaluationPresentationName = responseData.data.evaluationPresentationName;
                  	_self.formData.expertReviewName = responseData.data.expertReviewName;
                  	_self.formData.directorOpinionName = responseData.data.directorOpinionName;
                  },
                  
                  //删除表list1的方法
                  delList1:function(meg){
                  	var fileId = meg;
                  	var len = _self.formData.topologyDescriptionList.length;
                    for(var i=0;i<len;i++){
                    	if(_self.formData.topologyDescriptionList[i].fileId == fileId){
                    		_self.formData.topologyDescriptionList.splice(i,1);
                    		$(this).parent("li").remove();
                    		_self.$refs.refOnUpload.value = null;
                    		_self.fileDel(fileId,2);
                    		break;
                    	}
                    }
                    _self.setShowAttachName(_self);
                    /*_self.formData.topologyDescriptionName = '';
                    _self.formData.topologyDescriptionId = '';*/
                  },
                //删除表list2的方法
                  delList2:function(meg){
                  	var fileId = meg;
                  	var len = _self.formData.organizationManagementList.length;
                    for(var i=0;i<len;i++){
                    	if(_self.formData.organizationManagementList[i].fileId == fileId){
                    		_self.formData.organizationManagementList.splice(i,1);
                    		$(this).parent("li").remove();
                    		_self.$refs.refOnUpload2.value = null;
                    		_self.fileDel(fileId,2);
                    		break;
                    	}
                    }
                    _self.setShowAttachName(_self);
                    /*_self.formData.organizationManagementName = '';
                    _self.formData.organizationManagementId = '';*/
                  },
                  //删除表list3的方法
                  delList3:function(meg){
                  	var fileId = meg;
                  	var len = _self.formData.implementationPlanList.length;
                    for(var i=0;i<len;i++){
                    	if(_self.formData.implementationPlanList[i].fileId == fileId){
                    		_self.formData.implementationPlanList.splice(i,1);
                    		$(this).parent("li").remove();
                    		_self.$refs.refOnUpload3.value = null;
                    		_self.fileDel(fileId,2);
                    		break;
                    	}
                    }
                    _self.setShowAttachName(_self);
                    /*_self.formData.implementationPlanName = '';
                    _self.formData.implementationPlanId = '';*/
                  },
                //删除表list4的方法
                  delList4:function(meg){
                  	var fileId = meg;
                  	var len = _self.formData.licenseCertificateList.length;
                    for(var i=0;i<len;i++){
                    	if(_self.formData.licenseCertificateList[i].fileId == fileId){
                    		_self.formData.licenseCertificateList.splice(i,1);
                    		$(this).parent("li").remove();
                    		_self.$refs.refOnUpload4.value = null;
                    		_self.fileDel(fileId,2);
                    		break;
                    	}
                    }
                    _self.setShowAttachName(_self);
                    /*_self.formData.licenseCertificateName = '';
                    _self.formData.licenseCertificateId = '';*/
                  },
                },
                created: function() {
                  //this.getPermitJurisdictionInfo(this);
                   //获取资料信息
                	this.getMaterialsInfoBySession(this);
                  _self = this;
                  //功能权限
                  $.ajax({
                    type: "get",
                    url : originUrl+"/jurisdiction/queryMenuJurisdictionApi", 
                    async: true,
                    data: "",
                    dataType: "json",
                    cache: false,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                    	if(getJurisdictionMethod(response,S_STR_PERMIT_PARAM_ENTERPRISE_CREATE)){
                    		_self.jurisdictionType = 1;
                    	}
                    	if(getJurisdictionMethod(response,S_STR_PERMIT_PARAM_HEADQUARTERS_CREATE)){
                    		_self.jurisdictionType = 2;
                    	}
                    },
                    error: function(err) {
                    }
                  });
                },
                mounted: function() {
                  var _self = this;
                  if(!_self.flag){
                		if(systemId!=null&&systemId!=''){
                			_self.formData.fkSystemId = systemId;
                			_self.getMaterialsInfo(this,systemId);
                		}
                	}else{
                		_self.getMaterialsInfoSuccessMethod(_self,_self.substitute);
                	}
                  bus.$on('addMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addMaterialFormAjax',"add");
                        } else {
                          /*_self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                        	return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('addSubmitMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addSubmitMaterialFormAjax',"add");
                        } else {
                        /*  _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('addPreMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addPreMaterialFormAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                          _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('retuenSaveAttach',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('retuenSaveAttachAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('changeMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('changeMaterialFormAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('changeSubmitMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('changeSubmitMaterialFormAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('changePreMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('changePreMaterialFormAjax',"add");
                        } else {
                        /*  _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('changePreMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('changePreMaterialFormAjax',"add");
                        } else {
                          /*_self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('gradSubmitMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('gradSubmitMaterialFormAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('gradPreMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('gradPreMaterialFormAjax',"add");
                        } else {
                          /*_self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('gradMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('gradMaterialFormAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                 
                  bus.$on('deleteConfirm',function(meg){
                  	if(_self.fileId1!=''){
                  		var fileIds = _self.fileId1.split(",");
                  		for(var i=0;i<fileIds.length;i++){
                  			if(fileIds[i]!=''){
                  				_self.delList1(fileIds[i]);
                  			}
                  		}
                  	}
                  	if(_self.fileId2!=''){
                  		var fileIds = _self.fileId2.split(",");
                  		for(var i=0;i<fileIds.length;i++){
                  			if(fileIds[i]!=''){
                  				_self.delList2(fileIds[i]);
                  			}
                  		}
                  	}
                  	if(_self.fileId3!=''){
                  		var fileIds = _self.fileId3.split(",");
                  		for(var i=0;i<fileIds.length;i++){
                  			if(fileIds[i]!=''){
                  				_self.delList3(fileIds[i]);
                  			}
                  		}
                  	}
                  	if(_self.fileId4!=''){
                  		var fileIds = _self.fileId4.split(",");
                  		for(var i=0;i<fileIds.length;i++){
                  			if(fileIds[i]!=''){
                  				_self.delList4(fileIds[i]);
                  			}
                  		}
                  	}
                  });
                  
                  bus.$on('placeContent',function(meg){
                    if(meg!=null){
                    	_self.placeBeginContent(meg);
                    }
                  });
                  bus.$on('judgeChange',function(meg){

                  	var flag = true;
                  	var beginContent = _self.beginContent;
                  	var currentContent = _self.formData;
                  	if(beginContent.topologyDescriptionName == null){
                  		beginContent.topologyDescriptionName = '';
                  	}
                  	if(beginContent.organizationManagementName == null){
                  		beginContent.organizationManagementName = '';
                  	}
                  	if(beginContent.implementationPlanName == null){
                  		beginContent.implementationPlanName = '';
                  	}
                  	if(beginContent.licenseCertificateName == null){
                  		beginContent.licenseCertificateName = '';
                  	}
                  	if(beginContent.topologyDescriptionName != currentContent.topologyDescriptionName){//系统拓扑结构及说明
                  		flag = false;
                  	}
                  	if(beginContent.organizationManagementName != currentContent.organizationManagementName){//系统安全组织机构及管理制度
                  		flag = false;
                  	}
                  	if(beginContent.implementationPlanName != currentContent.implementationPlanName){//系统安全保护设施设计实施方案或改建实施方案
                  		flag = false;
                  	}
                  	if(beginContent.licenseCertificateName != currentContent.licenseCertificateName){//系统使用的安全产品清单及认证、销售许可证明
                  		flag = false;
                  	}
                  	if(beginContent.evaluationPresentationName != currentContent.evaluationPresentationName){//系统等级测评报告
                  		flag = false;
                  	}
                  	if(beginContent.expertReviewName != currentContent.expertReviewName){//专家评审情况
                  		flag = false;
                  	}
                  	if(beginContent.directorOpinionName != currentContent.directorOpinionName){//上级主管部门审批意见
                  		flag = false;
                  	}
                  	_self.flag1 = flag;
                  
                  });
                }
            })
        })
    })
}())