var data={
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
	deleteFileId: null,
};
(function () {
	Vue.component('record',function (resolve,reject) {
		$.get(comp_src+'/compnents/private/record/record.html').then(function(res){
			resolve({
				template:res,
				data:function () {
					return data;
				},
				methods:{
					submitRecord:function(_self){
						_self.sureDelFile(_self);
						ajaxMethod(_self, 'post',
                'records/saveRecords', true,JSON.stringify(_self.formData), 'json',
                'application/json;charset=UTF-8',_self.submitRecordSuccessMethod);
					},
					submitRecordSuccessMethod:function(_self,responseData){
						window.location.href= originUrl + "/page/indexPage"
					},
					getRecord:function(_self){
						ajaxMethod(_self, 'post',
                'records/queryRecords', true, JSON.stringify(_self.formData), 'json',
                'application/json;charset=UTF-8',_self.getRecordSuccessMethod);
					},
					getRecordSuccessMethod:function(_self,responseData){
						if(responseData.data!=null){
							_self.formData = responseData.data;
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
						$(e.currentTarget).prevAll().children('i').css('background-position','-8px -260px');
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
            	_self.submitRecord(_self);
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