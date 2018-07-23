var data1={
	formData:{
		recordsId: null,
		fkSystemId: systemId,
		fkrevokematter: null,
		recordCode: null,
		recordCompany: null,
		recordDate: null,
		acceptCompany: null,
		acceptDate: null,
		acceptReason : null,
		recordReportPath : null, 
		recordReportName : null
	},
//删除弹窗的
  deleteDialog:false,
  deleteSuccessDialog:false,
	deleteFailDialog:false,
	deleteFileId: null,
  rules:{
      recordCode:[//备案编号
          {required: true, message: '请输入备案编号', trigger: 'change' },
          { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
      ],
      recordCompany:[//备案单位
          {required: true, message: '请输入备案单位', trigger: 'change' },
          { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
      ],
      recordDate:[//备案时间
          {required: true, message: '请输入备案时间', trigger: 'change' }
      ],
      acceptCompany:[//受理备案单位
          {required: true, message: '请输入受理备案单位', trigger: 'change' },
          { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'blur' },
      ],
      acceptDate:[//受理日期
          {required: true, message: '请输入受理日期', trigger: 'change' }
      ],
      recordReportName:[//信息系统安全等级保护备案证明
          {required: true, message: '请上传信息系统安全等级保护备案证明', trigger: 'change' }
      ],
	}
};
(function () {
	Vue.component('record',function (resolve,reject) {
		$.get(comp_src+'/compnents/private/record/record.html').then(function(res){
			resolve({
				template:res,
				data:function () {
					return data1;
				},
				methods:{
					//提交弹窗
					submitRecordDia:function(formData){
						var _self = this;
						_self.sureDelFile(_self);
						_self.$refs[formData].validate(function (valid) {
	            if (valid) {
	            	ajaxMethod(_self, 'post',
	                  'records/saveRecords', true,JSON.stringify(_self.formData), 'json',
	                  'application/json;charset=UTF-8',_self.submitRecordSuccessMethod);
	            } else {
	            	$("#recordInquiry").css("display","none");
	              $("#recordDialogShaw").css("display","none");
	              _self.$alert('验证有误，请检查填写信息！', '验证提示', {
	                confirmButtonText: '确定',
	                callback: function callback(action) {
	                }
	              });
	              return false;
	            }
	          });
					},
					//提交
					submitRecord:function(formData){
						$("#recordInquiry").css("display","block");
	         	$("#recordDialogShaw").css("display","block");
					},
					submitRecordSuccessMethod:function(_self,responseData){
						 $("#recordInquiry").css("display","none");
	           $("#recordDialogShaw").css("display","none");
						$("#startBoxRecord").show().delay(2000).fadeOut();
            window.setTimeout(function () {
            	window.location.href= originUrl + "page/indexPage"           
            }, 2300);
					},
					//关闭弹窗
					closes:function () {
            $("#recordInquiry").css("display","none");
            $("#recordDialogShaw").css("display","none");
          },
					getRecord:function(_self){
						ajaxMethod(_self, 'post',
                'records/queryRecords', true, JSON.stringify(_self.formData), 'json',
                'application/json;charset=UTF-8',_self.getRecordSuccessMethod);
					},
					getRecordSuccessMethod:function(_self,responseData){
						if(responseData.data!=null){
							_self.formData = responseData.data;
							
							
							var index = -(2*100+8)+"px"+" -190px";
							$(".process>li").eq(2).children('i').css('background-position',index);
							$(".process>li").eq(2).prevAll().children('i').next('span').css('background-color','#3d95df');
							$(".process>li").eq(2).prevAll().children('i').css('background-position-y','-190px');
							$(".process>li").eq(2).children('i').next('span').css('background-color','#cecece');
							$(".process>li").eq(2).nextAll().children('i').next('span').css('background-color','#cecece');
							$(".process>li").eq(2).nextAll().children('i').css('background-position','');
							
							$('.comitBtm').show();
							if (this.formData.recordsId!=null && this.formData.recordsId!="" && typeof(this.formData.recordsId)!= undefined) {
								bus.$emit("changeLi",true);
							}else{
								bus.$emit("changeLi",false);
							}
							//改div 显示和隐藏
							$(".recordPro>div").eq(2).css("display","block").siblings("div").css("display","none");
							
						}else{
							_self.formData = {
									recordsId: null,
									fkSystemId: systemId,
									fkrevokematter: null,
									recordCode: null,
									recordCompany: null,
									recordDate: null,
									acceptCompany: null,
									acceptDate: null,
									acceptReason : null,
									recordReportPath : null,
									recordReportName : null
								};
						}
					},
					changeLi:function(e) {
						var index = -($(e.currentTarget).index()*100+8)+"px"+" -190px";
						$(e.currentTarget).children('i').css('background-position',index);
						$(e.currentTarget).prevAll().children('i').next('span').css('background-color','#3d95df');
						$(e.currentTarget).prevAll().children('i').css('background-position-y','-190px');
						$(e.currentTarget).children('i').next('span').css('background-color','#cecece');
						$(e.currentTarget).nextAll().children('i').next('span').css('background-color','#cecece');
						$(e.currentTarget).nextAll().children('i').css('background-position','');
						if($(e.currentTarget).index() == 2){
							$('.comitBtm').show();
							if (this.formData.recordsId!=null && this.formData.recordsId!="" && typeof(this.formData.recordsId)!= undefined) {
								bus.$emit("changeLi",true);
							}else{
								bus.$emit("changeLi",false);
							}
						}else{
							bus.$emit("changeLi",false);
							$('.comitBtm').hide();
						}
						//改div 显示和隐藏
						$(".recordPro>div").eq($(e.currentTarget).index()).css("display","block").siblings("div").css("display","none");
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
						_self.formData.recordReportName=responseData.data.attachName;
						_self.formData.recordReportPath=responseData.data.uploadUrl;
						/*var fileHtml='<li><div class="fl"  @click="fileDownload("\'+responseData.data.uploadUrl+\'")" >'+responseData.data.attachName+'</div><i @click="fileDel("'+responseData.data.uploadUrl+'")" class="el-icon-close fl"></i></li>'
						$("#fileList").html(fileHtml);*/
					},
					fileDel:function(uploadUrl,attachName,fileId){
						this.formData.recordReportName='';
						this.formData.recordReportPath='';
						if(fileId != ''&&fileId!=null&&fileId!='undefined'){
							this.deleteFileId = fileId;
						}
          },
          sureDelFile:function(_self){
          	if(_self.deleteFileId != null){
          		ajaxMethod(_self, 'post',
          				'fileHandle/deleteFile', true,'{"fileId":"'+_self.deleteFileId+'"}', 'json',
          				'application/json;charset=UTF-8',_self.fileDelSuccessMethod);
          	}
          },
          fileDelSuccessMethod:function(_self,responseData){
						/*_self.formData.recordReportName='';
						_self.formData.recordReportPath='';
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
					//获取表1-表4下载路径
					downloadFile:function(num){
						switch (num) {
						case 1:
							this.downloadList1(companyId);
							break;
						case 2:
							this.downloadList2(systemId);
							break;
						case 3:
							this.downloadList3(systemId);
							break;
						case 4:
							this.downloadList4(systemId);
							break;
						default:
							break;
						}
					},
					//表1
          downloadList1: function (companyId) {
          	var _self = this;
          	ajaxMethod(_self, 'post',
          			'main/tableCompany', true,
          			'{"companyId":"'+companyId+'"}', 'json',
          			'application/json;charset=UTF-8',
          			_self.downloadListSuccess);
          },
					//表2
          downloadList2: function (systemId) {
          	var _self = this;
          	ajaxMethod(_self, 'post',
          			'main/tableSystem', true,
          			'{"systemId":"'+systemId+'"}', 'json',
          			'application/json;charset=UTF-8',
          			_self.downloadListSuccess);
          },
					//表3
          downloadList3: function (systemId) {
          	var _self = this;
            ajaxMethod(_self, 'post',
                 'main/tableGrading', true,
                 '{"systemId":"'+systemId+'"}', 'json',
                 'application/json;charset=UTF-8',
                 _self.downloadListSuccess);
          },
					//表4
          downloadList4: function (systemId) {
          	var _self = this;
          	ajaxMethod(_self, 'post',
          			'main/tableAttach', true,
          			'{"systemId":"'+systemId+'"}', 'json',
          			'application/json;charset=UTF-8',
          			_self.downloadListSuccess);
          },
          //获取成功
          downloadListSuccess: function (_self,responseData) {
          	var url = responseData.data;
          	var name = url.substring(url.lastIndexOf("/")+1,url.length);
          	window.location.href=originUrl+"/fileHandle/downloadFile?uploadUrl="+name+"&attachName="+name;
          },
					
				},
				created: function() {
					this.formData.fkSystemId=systemId;
					this.getRecord(this);
				},
				mounted: function() {
					var _self = this;
					bus.$on("submitRecord",function(meg){
            if(meg!=null){
            	_self.submitRecord(meg);
            }
          });
					bus.$on("revokeRecord",function(meg){
            if(meg!=null){
            	_self.revokeRecord(_self);
            }
          });
				}
			})
		})
	})
}())