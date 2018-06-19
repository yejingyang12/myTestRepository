//描述
$('#textArea').on("keyup", function() {
	$('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
	if ($('#textArea').val().length > 200) {
		$('#textNum').text(200);//长度大于200时0处显示的也只是200
		$('#textArea').val($('#textArea').val().substring(0, 200));//长度大于100时截取钱100个字符
	}
});
var revokeRecordData={
	formData:{
		recordsId: "",
		fkSystemId: "",
		fkrevokematter: 4,
		revokereason: "",
		revokeAttachPath: "",
		revokeAttachName: ""
	},
	fileDelete: null,
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
					closes: function() {
						$('.wrap').addClass('cover');
						this.formData.revokereason = '';
					},
					onUpload: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
					},
					onUploadSuccessMethod: function(_self,responseData){
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
					submitRevokeRecord: function(){
						var _self = this;
						_self.formData.fkSystemId=systemId;
						ajaxMethod(_self, 'post',
                'records/saveRevokeRecordsInfo', true,JSON.stringify(_self.formData), 'json',
                'application/json;charset=UTF-8',_self.submitRevokeRecordSuccessMethod);
					},
					submitRevokeRecordSuccessMethod: function(_self, responseData){
						_self.formData.revokereason = '';
						window.location.href= originUrl + "/page/indexPage"
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
					onUpload: function(e){
						var uploadData = new FormData(); 
						uploadData.append('file', e.target.files[0]);
						uploadData.append('type', 'test');
						ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
					},
					onUploadSuccessMethod: function(_self,responseData){
						this.formData.revokeAttachName=responseData.data.attachName;
						this.formData.revokeAttachPath=responseData.data.uploadUrl;
					}
				},
				created : function() {
					this.formData.fkSystemId = systemId;
				},
				mounted : function() {
					// this.selectChange()
				}
			})
		})
	})
}())