//描述
$('#textArea').on("keyup", function() {
	$('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
	if ($('#textArea').val().length > 200) {
		$('#textNum').text(200);//长度大于200时0处显示的也只是200
		$('#textArea').val($('#textArea').val().substring(0, 200));//长度大于100时截取钱100个字符
	}
});
var revokeRecordData={
  paramHeadquarters : false,
  paramEnterprise : false,
	formData:{
		recordsId: "",
		fkSystemId: "",
		fkrevokematter: 4,
		revokereason: "",
		revokeAttachPath: "",
		revokeAttachName: ""
	},
//删除弹窗的
  deleteDialog:false,
  deleteSuccessDialog:false,
	deleteFailDialog:false,
	fileDelete: null,
  rules:{
      revokeAttachName:[//撤销证明
          {required: true, message: '请上传撤销证明', trigger: 'change' }
      ],
      revokereason:[//撤销原因
          {required: true, message: '请输入撤销原因', trigger: 'change' },
          { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
      ]
	}
};
(function() {
	Vue.component('revokeRecord', function(resolve, reject) {
		$.get(comp_src + '/compnents/private/revokeRecord/revokeRecord.html').then(
		function(res) {
			resolve({
				template : res,
				data : function() {
					return revokeRecordData;
				},
				methods : {
					closesDir: function() {
						$('.wrap').addClass('cover');
						this.formData.revokereason = '';
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
          	if(fileFormat[1] != 'pdf' && fileFormat[1] != 'xls' && fileFormat[1] != 'xlsm'&& fileFormat[1] != 'xlsx'  && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx'){          		this.$alert('不接受此文件类型！', '信息提示', {
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
						this.$refs.refOnUpload.value = null;
						_self.formData.revokeAttachName=responseData.data.attachName;
						_self.formData.revokeAttachPath=responseData.data.uploadUrl;
						/*var fileHtml='<li><div class="fl"  @click="fileDownload("\'+responseData.data.uploadUrl+\'")" >'+responseData.data.attachName+'</div><i @click="fileDel("'+responseData.data.uploadUrl+'")" class="el-icon-close fl"></i></li>'
						$("#fileList").html(fileHtml);*/
					},
					fileDel:function(uploadUrl,attachName,fileId){
						this.formData.revokeAttachName='';
						this.formData.revokeAttachPath='';
						if(fileId != ''&&fileId!=null&&fileId!='undefined'){
							this.fileDelete = fileId;
						}
          },
          sureDelFile:function(_self){
          	if(_self.fileDelete != null){
          		ajaxMethod(_self, 'post',
          				'fileHandle/deleteFile', true,'{"fileId":"'+_self.fileDelete+'"}', 'json',
          				'application/json;charset=UTF-8',_self.fileDelSuccessMethod);
          	}
          },
          fileDelSuccessMethod:function(_self,responseData){
						/*_self.formData.revokeAttachName='';
						_self.formData.revokeAttachPath='';
						_self.formData.recordReportId='';*/
          	//$(this).parent("li").remove();
          },
          //根据URL和附件名或者根据fileId下载附件
          fileDownload:function(uploadUrl,attachName,fileId){
          	if(uploadUrl == "undefined"){
          		uploadUrl = null;
          	}
          	if(attachName == "undefined"){
          		attachName = null;
          	}
          	if(fileId == "undefined"){
          		fileId = null;
          	}
          	//下载路径
          	window.location.href = originUrl + "fileHandle/downloadFile?uploadUrl="+uploadUrl+"&attachName="+attachName+"&fileId="+fileId;
					},
					submitRevokeRecord: function(formData){
						var _self = this;
						_self.formData.fkSystemId=systemId;
						_self.sureDelFile(_self);
						_self.$refs[formData].validate(function (valid) {
              if (valid) {
                if(revokeRecordData.paramHeadquarters){
                  ajaxMethod(_self, 'post',
                      'records/saveHeadRevokeRecordsInfo', true,JSON.stringify(_self.formData), 'json',
                      'application/json;charset=UTF-8',_self.submitRevokeRecordSuccessMethod);
                }else if(revokeRecordData.paramEnterprise){
                  ajaxMethod(_self, 'post',
                      'records/saveRevokeRecordsInfo', true,JSON.stringify(_self.formData), 'json',
                      'application/json;charset=UTF-8',_self.submitRevokeRecordSuccessMethod);
                }
              } else {
              	$("#revokeRecordDialogShaw").css("display","none");
                $("#revokeRecordInquiry").css("display","none");
                _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                  confirmButtonText: '确定',
                  callback: function callback(action) {
                  }
                });
                return false;
              }
            });
					},
					submitRevokeRecordDir: function(formData){
						$("#revokeRecordDialogShaw").css("display","block");
	         	$("#revokeRecordInquiry").css("display","block");
					},
					submitRevokeRecordSuccessMethod: function(_self, responseData){
						$('.wrap').addClass('cover');
						_self.formData.revokereason = '';
						$("#revokeRecordDialogShaw").css("display","none");
            $("#revokeRecordInquiry").css("display","none");
						$(".startBox").show().delay(2000).fadeOut();
            window.setTimeout(function () {
            	window.location.href= originUrl + "page/indexPage"           
            }, 2300);
					},
				  //关闭弹窗
					closes:function () {
            $("#revokeRecordDialogShaw").css("display","none");
            $("#revokeRecordInquiry").css("display","none");
          },
					text : function() {
						$('#textArea').on("keyup", function() {
							$('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
							if ($('#textArea').val().length > 200) {
								$('#textNum').text(200);//长度大于200时0处显示的也只是200
								$('#textArea').val($('#textArea').val().substring(0, 200));//长度大于100时截取钱100个字符
							}
						})
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
              //总部撤销备案
              if(permssions==S_STR_PERMIT_PARAM_HEADQUARTERS_REVOKE_RECORD){
                _self.paramHeadquarters = true;
                bus.$emit("showRevokeRecord",true);
                break;
              }
              //企业撤销备案
              if(permssions==S_STR_PERMIT_PARAM_ENTERPRISE_REVOKE_RECORD){
                _self.paramEnterprise = true;
                bus.$emit("showRevokeRecord",true);
                break;
              }
            }
          }
				},
				created : function() {
					this.formData.fkSystemId = systemId;
					this.getPermitJurisdictionInfo(this);
				},
				mounted : function() {
					// this.selectChange()
				}
			})
		})
	})
}())