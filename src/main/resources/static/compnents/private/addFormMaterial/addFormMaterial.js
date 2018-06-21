/**
 * Created by timha on 2018/5/24.
 */
var data={
        formData:{
          systemMaterialsId:'',
          fkSystemId:'',
          topologyDescriptionId:'',
          topologyDescriptionPath:'',
          topologyDescriptionName:'',
          organizationManagementId:'',
          organizationManagementPath:'',
          organizationManagementName:'',
          implementationPlanId:'',
          implementationPlanPath:'',
          implementationPlanName:'',
          licenseCertificateId:'',
          licenseCertificatePath:'',
          licenseCertificateName:'',
          evaluationPresentationId:'',
          evaluationPresentationPath:'',
          evaluationPresentationName:'',
          expertReviewId:'',
          expertReviewPath:'',
          expertReviewName:'',
          directorOpinionId:'',
          directorOpinionPath:'',
          directorOpinionName:'',
          changeType:''
        }
    };
(function () {
    
    $("#addFormGrad").validate({
        systemTopology:{
            required:true,
            systemTopology:true
        },
        systemSecurity:{
            required:true,
            systemSecurity:true
        },
        systemSafety:{
            required:true,
            systemSafety:true
        },
        systemUse:{
            required:true,
            systemUse:true
        },
        onkeyup: false,
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
            //忽略自定义方法的错误提示
            if (error.text() == "ignore") {
                return '';
            }
        },
        errorElement: "span",
        submitHandler: function (form) {
            console.log(form);
            $(form).ajaxSubmit({
                url: "php/order.php",
                type: "post",
                success: function () {
                    alert("提交成功！");
                    $(".shadow").show();
                    $("#comit").click(function () {
                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                    })
                }
            })
        }
    })
    Vue.component('addFormMaterial',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addFormMaterial/addFormMaterial.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{

                  onUpload(e){
                    var uploadData = new FormData(); 
                    uploadData.append('file', e.target.files[0]);
                    uploadData.append('type', 'test');
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
                  },
                  onUploadSuccessMethod: function(_self,responseData){
                    this.formData.topologyDescriptionName=responseData.data.attachName;
                    this.formData.topologyDescriptionPath=responseData.data.uploadUrl;
                    var fileHtml='<li><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>'
                    $("#fileList").html(fileHtml);
                    $(".del").click(function(){
                      $(this).parent("li").remove();
                      _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                      _self.formData.topologyDescriptionName='';
                      _self.formData.topologyDescriptionPath='';
                    });
                    $(".updwon").click(function(){
                      //$(this).parent("li").remove();
                      _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                    });
                  },
                  onUpload2(e){
                    var uploadData = new FormData(); 
                    uploadData.append('file', e.target.files[0]);
                    uploadData.append('type', 'test');
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
                  },
                  onUploadSuccessMethod2: function(_self,responseData){
                    this.formData.organizationManagementName=responseData.data.attachName;
                    this.formData.organizationManagementPath=responseData.data.uploadUrl;
                    var fileHtml='<li><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>'
                    $("#fileList2").html(fileHtml);
                    $(".del").click(function(){
                      $(this).parent("li").remove();
                      _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                      _self.formData.organizationManagementName='';
                      _self.formData.organizationManagementPath='';
                    });
                    $(".updwon").click(function(){
                      //$(this).parent("li").remove();
                      _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                    });
                  },
                  onUpload3(e){
                    var uploadData = new FormData(); 
                    uploadData.append('file', e.target.files[0]);
                    uploadData.append('type', 'test');
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod3);
                  },
                  onUploadSuccessMethod3: function(_self,responseData){
                    this.formData.implementationPlanName=responseData.data.attachName;
                    this.formData.implementationPlanPath=responseData.data.uploadUrl;
                    var fileHtml='<li><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>'
                    $("#fileList3").html(fileHtml);
                    $(".del").click(function(){
                      $(this).parent("li").remove();
                      _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                      _self.formData.implementationPlanName='';
                      _self.formData.implementationPlanPath='';
                    });
                    $(".updwon").click(function(){
                      //$(this).parent("li").remove();
                      _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                    });
                  },
                  onUpload4(e){
                    var uploadData = new FormData(); 
                    uploadData.append('file', e.target.files[0]);
                    uploadData.append('type', 'test');
                    ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod4);
                  },
                  onUploadSuccessMethod4: function(_self,responseData){
                    this.formData.licenseCertificateName=responseData.data.attachName;
                    this.formData.licenseCertificatePath=responseData.data.uploadUrl;
                    var fileHtml='<li><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>'
                    $("#fileList4").html(fileHtml);
                    $(".del").click(function(){
                      $(this).parent("li").remove();
                      _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                      _self.formData.licenseCertificateName='';
                      _self.formData.licenseCertificatePath='';
                    });
                    $(".updwon").click(function(){
                      //$(this).parent("li").remove();
                      _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                    });
                  },
                  fileDel:function(path,type){
                    var url='';
                    if(type==1){
                      url = '{"downloadFile":"'+path+'"}';
                    }else{
                      url = '{"downloadId":"'+path+'"}'
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
                      window.location.href = "http://localhost:8082/fileHandle/downloadFile?uploadUrl="+path+"&attachName="+name;
                    }else{
                      //下载路径
                      window.location.href = "http://localhost:8082/fileHandle/downloadFile?fileId="+path;
                    }
                      
                  },
                  getMaterialsInfo: function(_self,id) {
                    ajaxMethod(_self, 'post','grading/queryEditSystemMaterials', true,
                        '{"fkSystemId":"'+id+'"}', 'json',
                        'application/json;charset=UTF-8',
                        _self.getMaterialsInfoSuccessMethod);
                  },
                  // 获取回显安全按等级成功
                  getMaterialsInfoSuccessMethod : function(_self, responseData) {
                    if(responseData.data!=null){
                      _self.formData = responseData.data;
                    }
                    
                    if(responseData.data.topologyDescriptionName!=null){
                      var fileHtml='<li><div class="fl updwon">'+responseData.data.topologyDescriptionName+'</div><i class="el-icon-close fl del"></i></li>'
                      $("#fileList").html(fileHtml);
                      $(".del").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.topologyDescriptionId,2);
                        _self.formData.topologyDescriptionName = '';
                        _self.formData.topologyDescriptionId = '';
                      });
                      $(".updwon").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.topologyDescriptionId,2);
                      });
                    }
                    
                    if(responseData.data.organizationManagementName!=null){
                      var fileHtml='<li><div class="fl updwon">'+responseData.data.organizationManagementName+'</div><i class="el-icon-close fl del"></i></li>'
                      $("#fileList2").html(fileHtml);
                      $(".del").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.organizationManagementId,2);
                        _self.formData.organizationManagementName = '';
                        _self.formData.organizationManagementId = '';
                      });
                      $(".updwon").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.organizationManagementId,2);
                      });
                    }
                    
                    if(responseData.data.implementationPlanName!=null){
                      var fileHtml='<li><div class="fl updwon">'+responseData.data.implementationPlanName+'</div><i class="el-icon-close fl del"></i></li>'
                      $("#fileList3").html(fileHtml);
                      $(".del").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.implementationPlanId,2);
                        _self.formData.implementationPlanName = '';
                        _self.formData.implementationPlanId = '';
                      });
                      $(".updwon").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.implementationPlanId,2);
                      });
                    }
                    
                    if(responseData.data.licenseCertificateName!=null){
                      var fileHtml='<li><div class="fl updwon">'+responseData.data.licenseCertificateName+'</div><i class="el-icon-close fl del"></i></li>'
                      $("#fileList4").html(fileHtml);
                      $(".del").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.licenseCertificateId,2);
                        _self.formData.licenseCertificateName = '';
                        _self.formData.licenseCertificateId = '';
                      });
                      $(".updwon").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.licenseCertificateId,2);
                      });
                    }
                  },
                  downloadFile:function(str) {
                    window.location.href = "fileHandle/downloadFile?fileId="+str;
                  },
                  submitGradeMaterialsInfo: function() {
                    
                    ajaxMethod(this, 'post',
                    'grading/saveSystemMaterials', true,
                    JSON.stringify(this.formData), 'json',
                    'application/json;charset=UTF-8',
                    this.submitGradeMaterialsInfoSuccessMethod);
                  },
                  // 获取安全按等级成功
                  submitGradeMaterialsInfoSuccessMethod : function(_self, responseData) {
                    //window.location.href = "page/addCompanyMaterialPage?systemId=f7821c57865d4983b9db6f8db08efb3c";
                    
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
                    // this.selectChange()
                }
            })
        })
    })
}())