/**
 * Created by timha on 2018/5/24.
 */
var data={
    check : false,
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
        }
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
                    console.log(JSON.stringify(responseData))
                  	var topologyDescription = {fileId: '',attachName: '',uploadUrl: ''};
                  	topologyDescription.attachName = responseData.data.attachName;
                  	topologyDescription.uploadUrl = responseData.data.uploadUrl;
                  	_self.formData.topologyDescriptionList.push(topologyDescription);
                  	_self.setShowAttachName(_self);
                    /*if(this.formData.topologyDescriptionName!=null&&this.formData.topologyDescriptionName!=''){
                      this.formData.topologyDescriptionName=responseData.data.attachName;
                      this.formData.topologyDescriptionPath=responseData.data.uploadUrl;
                    }*/
                    
                    var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                    $('#fileList').append(fileHtml);
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.topologyDescriptionList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.topologyDescriptionList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.topologyDescriptionList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.topologyDescriptionName='';
                      _self.formData.topologyDescriptionPath='';*/
                    });
                    $(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });
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
                  	var organizationManagement = {fileId: '',attachName: '',uploadUrl: ''};
                  	organizationManagement.attachName = responseData.data.attachName;
                  	organizationManagement.uploadUrl = responseData.data.uploadUrl;
                  	_self.formData.organizationManagementList.push(organizationManagement);
                  	_self.setShowAttachName(_self);
                  	/*this.formData.organizationManagementName=responseData.data.attachName;
                    this.formData.organizationManagementPath=responseData.data.uploadUrl;*/
                    var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                    $('#fileList2').append(fileHtml);
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.organizationManagementList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.organizationManagementList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.organizationManagementList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.organizationManagementName='';
                      _self.formData.organizationManagementPath='';*/
                    });
                    $(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });
                  },
                  onUpload3: function(e){
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
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod3);
                  },
                  onUploadSuccessMethod3: function(_self,responseData){
                  	var implementationPlan = {fileId: '',attachName: '',uploadUrl: ''};
                  	implementationPlan.attachName = responseData.data.attachName;
                  	implementationPlan.uploadUrl = responseData.data.uploadUrl;
                  	_self.formData.implementationPlanList.push(implementationPlan);
                  	_self.setShowAttachName(_self);
                    /*this.formData.implementationPlanName=responseData.data.attachName;
                    this.formData.implementationPlanPath=responseData.data.uploadUrl;*/
                    var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                    $("#fileList3").append(fileHtml);
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.implementationPlanList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.implementationPlanList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.implementationPlanList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.implementationPlanName='';
                      _self.formData.implementationPlanPath='';*/
                    });
                    $(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });
                  },
                  onUpload4: function(e){
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
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod4);
                  },
                  onUploadSuccessMethod4: function(_self,responseData){
                  	var licenseCertificate = {fileId: '',attachName: '',uploadUrl: ''};
                  	licenseCertificate.attachName = responseData.data.attachName;
                  	licenseCertificate.uploadUrl = responseData.data.uploadUrl;
                  	_self.formData.licenseCertificateList.push(licenseCertificate);
                  	_self.setShowAttachName(_self);
                    /*this.formData.licenseCertificateName=responseData.data.attachName;
                    this.formData.licenseCertificatePath=responseData.data.uploadUrl;*/
                    var fileHtml='<li><input type="hidden" value="'+responseData.data.uploadUrl+'&'+responseData.data.attachName+'"/><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>';
                    $("#fileList4").append(fileHtml);
                    $(".del").click(function(){
                    	var uploadUrl = $(this).parent("li").find("input").val();
                    	uploadUrl = uploadUrl.split("&");
                      var len = _self.formData.licenseCertificateList.length;
                      for(var i=0;i<len;i++){
                      	if(_self.formData.licenseCertificateList[i].uploadUrl == uploadUrl[0]){
                      		_self.formData.licenseCertificateList.splice(i,1);
                      		$(this).parent("li").remove();
                      		_self.fileDel(uploadUrl[0],1,uploadUrl[1]);
                      		break;
                      	}
                      }
                      _self.setShowAttachName(_self);
                      /*_self.formData.licenseCertificateName='';
                      _self.formData.licenseCertificatePath='';*/
                    });
                    $(".updwon").click(function(){
                    	var fileId = $(this).parent("li").find("input").val();
                    	fileId = fileId.split("&");
                      _self.fileDownload(fileId[0],1,fileId[1]);
                    });
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
                        'fileHandle/deleteFile', true,url, 'json',
                        'application/json;charset=UTF-8',this.fileDelSuccessMethod);
                  },
                  fileDelSuccessMethod:function(_self,responseData){
                      $(this).parent("li").remove();
                  },
                  fileDownload:function(path,type,name){
                    
                    if(type=='1'){
                      //下载路径
                      window.location.href = originUrl + "fileHandle/downloadFile?uploadUrl="+path+"&attachName="+name;
                    }else{
                      //下载路径
                      window.location.href = originUrl + "fileHandle/downloadFile?fileId="+path;
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
                  // 获取回显材料信息成功
                  getMaterialsInfoSuccessMethod : function(_self, responseData) {
                    if(responseData.data!=null){
                      _self.formData = responseData.data;
                      _self.setShowAttachName(_self);
                    }
                    
                    if(responseData.data.topologyDescriptionName!=null){
                    	var fileHtml='';
                    	var len_topologyDescriptionList = _self.formData.topologyDescriptionList.length;
                      for(var i=0;i<len_topologyDescriptionList;i++){
                      	fileHtml = fileHtml +'<li><input type="hidden" value="'+responseData.data.topologyDescriptionList[i].fileId+'"/><div class="fl updwon">'+responseData.data.topologyDescriptionList[i].attachName+'</div><i class="el-icon-close fl del"></i></li>'
                      };
                     
                      $("#fileList").html(fileHtml);
                      $(".del").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        var len = _self.formData.topologyDescriptionList.length;
                        for(var i=0;i<len;i++){
                        	if(_self.formData.topologyDescriptionList[i].fileId == fileId){
                        		_self.formData.topologyDescriptionList.splice(i,1);
                        		$(this).parent("li").remove();
                        		_self.fileDel(fileId,2);
                        		break;
                        	}
                        }
                        _self.setShowAttachName(_self);
                        /*_self.formData.topologyDescriptionName = '';
                        _self.formData.topologyDescriptionId = '';*/
                      });
                      $(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        _self.fileDownload(fileId,2);
                      });
                    }
                    
                    if(responseData.data.organizationManagementName!=null){
                    	var fileHtml='';
                      var len_organizationManagementList = _self.formData.organizationManagementList.length;
                      for(var i=0;i<len_organizationManagementList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.organizationManagementList[i].fileId+'"/><div class="fl updwon">'+responseData.data.organizationManagementList[i].attachName+'</div><i class="el-icon-close fl del"></i></li>'
                      }
                      $("#fileList2").html(fileHtml);
                      $(".del").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        var len = _self.formData.organizationManagementList.length;
                        for(var i=0;i<len;i++){
                        	if(_self.formData.organizationManagementList[i].fileId == fileId){
                        		_self.formData.organizationManagementList.splice(i,1);
                        		$(this).parent("li").remove();
                        		_self.fileDel(fileId,2);
                        		break;
                        	}
                        }
                        _self.setShowAttachName(_self);
                        /*_self.formData.organizationManagementName = '';
                        _self.formData.organizationManagementId = '';*/
                      });
                      $(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });
                    }
                    
                    if(responseData.data.implementationPlanName!=null){
                    	var fileHtml='';
                      var len_implementationPlanList = _self.formData.implementationPlanList.length;
                      for(var i=0;i<len_implementationPlanList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.implementationPlanList[i].fileId+'"/><div class="fl updwon">'+responseData.data.implementationPlanList[i].attachName+'</div><i class="el-icon-close fl del"></i></li>'
                      }
                      $("#fileList3").html(fileHtml);
                      $(".del").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        var len = _self.formData.implementationPlanList.length;
                        for(var i=0;i<len;i++){
                        	if(_self.formData.implementationPlanList[i].fileId == fileId){
                        		_self.formData.implementationPlanList.splice(i,1);
                        		$(this).parent("li").remove();
                        		_self.fileDel(fileId,2);
                        		break;
                        	}
                        }
                        _self.setShowAttachName(_self);
                        /*_self.formData.implementationPlanName = '';
                        _self.formData.implementationPlanId = '';*/
                      });
                      $(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });
                    }
                    
                    if(responseData.data.licenseCertificateName!=null){
                    	var fileHtml='';
                      var len_licenseCertificateList = _self.formData.licenseCertificateList.length;
                      for(var i=0;i<len_licenseCertificateList;i++){
                      	fileHtml=fileHtml+'<li><input type="hidden" value="'+responseData.data.licenseCertificateList[i].fileId+'"/><div class="fl updwon">'+responseData.data.licenseCertificateList[i].attachName+'</div><i class="el-icon-close fl del"></i></li>'
                      }
                      $("#fileList4").html(fileHtml);
                      $(".del").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                        var len = _self.formData.licenseCertificateList.length;
                        for(var i=0;i<len;i++){
                        	if(_self.formData.licenseCertificateList[i].fileId == fileId){
                        		_self.formData.licenseCertificateList.splice(i,1);
                        		$(this).parent("li").remove();
                        		_self.fileDel(fileId,2);
                        		break;
                        	}
                        }
                        _self.setShowAttachName(_self);
                        /*_self.formData.licenseCertificateName = '';
                        _self.formData.licenseCertificateId = '';*/
                      });
                      $(".updwon").click(function(){
                      	var fileId = $(this).parent("li").find("input").val();
                      	_self.fileDownload(fileId,2);
                      });
                    }
                  },
                  downloadFile:function(str) {
                    window.location.href = originUrl + "fileHandle/downloadFile?fileId="+str;
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
                    //window.location.href = originUrl + "page/addCompanyMaterialPage?systemId=f7821c57865d4983b9db6f8db08efb3c";
                    
                  }
                },
                created: function() {
                   //获取资料信息
                  if(systemId!=null&&systemId!=''){
                    this.formData.fkSystemId = systemId;
                    this.getMaterialsInfo(this,systemId);
                  }
                },
                mounted: function() {
                  var _self = this;
                  bus.$on('addMaterialFormName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addMaterialFormAjax',"add");
                        } else {
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });
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
                          _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                              
                            }
                          });
                          return false;
                        }
                      });
                    }
                  });
                }
            })
        })
    })
}())