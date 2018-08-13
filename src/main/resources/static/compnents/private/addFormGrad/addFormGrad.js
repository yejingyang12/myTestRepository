var data={
	pickerOptions0: {
	      disabledDate: function(time) {
	        return time.getTime() < Date.now() - 8.64e7;
	      }
	},
    check : false,
    jurisdictionType:0,
		submitCheck:false,
		systemName:"",
        formData:{
          gradingId:'',
          fkSystemId:'',
      		gradeRecordSysName:'',
          fkBizSPRankDegree:'',
          fkBizSPRankLevel:'',
          fkBizSystemDegree:'',
          fkBizSystemLevel:'',
          fkSpRanklevel:0,
          expertView:'',
          rankExplainDesc:'',
          rankTime:'',
          competentIsExisting:'',
          competentName:'',
          competentView:'',
          filler:'',
          fillDate:'',
          gradingReportId:'',
          gradingReportName:'',
          gradingReportPath:'',
          expertReviewId:'',
          expertReviewName:'',
          expertReviewPath:'',
          directorOpinionId:'',
          directorOpinionName:'',
          directorOpinionPath:'',
          changeType:''
        },
        bizSPRankLevel:false,
        bizSystemLevel:false,
        expertType:false,
        safetyPro:{//确定安全保护等级
          busInform:[//业务信息
            {"radioId":"selectAll","disRadioId":"onlyCheck"},
            {"radioId":"selAll","disRadioId":"onCheck"},
          ],
          sysInform:{//系统信息

          }
        },
        protectionGrade:[],//安全保护等级
        competent:[],//主管部门
        competentGrad:[],//主管部门审批定级情况
        expertReview:[],//专家评审
        fillData:{},//填表时间
        smccAll:'',
        smccChecdArr:[[],[],[],[],[]],
        smccAllMaxNum:[],
        smccindpar:0,
        sAll:'',
        sChecdArr:[[],[],[],[],[]],
        sAllMaxNum:[],
        sindpar:0,
        all:'',
        nextBtn:true,
        rules:{
          fkBizSPRankDegree:[  //安全保护等级
              {required: true, message: '请选择业务信息损害客体及损害程度', trigger: 'change' },
          ],
          fkBizSPRankLevel:[  //安全保护等级
              {required: true, message: '请选择业务信息等级', trigger: 'change' },
          ],
          fkBizSystemDegree:[  //安全保护等级
              {required: true, message: '请选择系统信息损害客体及损害程度', trigger: 'change' },
          ],
          fkBizSystemLevel:[  //安全保护等级
              {required: true, message: '请选择系统信息等级', trigger: 'change' },
          ],
          fkSpRanklevel:[  //安全保护等级
              {required: true, message: '请选择安全保护等级', trigger: 'change' },
              { pattern: /^[1-9]\d*$/, message: '请选择安全保护等级', trigger: 'change'}
          ],
          /*rankExplainDesc:[  //定级说明描述
              {required: true, message: '请输入定级说明', trigger: 'blur' },
              { min: 0, max: 200, message: '长度在 0 到 200个字符', trigger: 'blur' },
          ],*/
          rankTime:[  //定级时间
              {required: true, message: '请选择定级时间', trigger: 'blur' },
          ],
          competentIsExisting:[  //是否有主管部门
              {required: true, message: '请选择是否有主管部门', trigger: 'change' },
          ],
          competentName:[  //主管部门名称
              {required: false, message: '请输入主管部门名称', trigger: 'blur' },
              { min: 0, max: 60, message: '长度在 0 到 60个字符', trigger: 'blur' },
          ],
          expertView:[  //专家评审情况
              {required: true, message: '请选择专家评审情况', trigger: 'blur' },
          ],
          competentView:[  //主管部门审批定级情况
              {required: false, message: '请选择主管部门审批定级情况', trigger: 'blur' },
          ],
          directorOpinionName:[  //上级主管部门审批意见
              {required: false, message: '请上传上级主管部门审批意见', trigger: 'blur' },
          ],
          gradingReportName:[  //定级报告
              {required: true, message: '请上传定级报告', trigger: 'change' },
          ],
          expertReviewName:[  //专家评审情况
              {required: true, message: '请上传专家评审附件', trigger: 'blur' },
          ],
          filler:[  //填表人
              {required: false, message: '请输入填表人', trigger: 'blur' },
              { min: 0, max: 40, message: '长度在 0 到 40个字符', trigger: 'blur' },
          ],
          fillDate:[  //填表时间
              /*{required: false, message: '请输入填表时间', trigger: 'blur' },*/
          ],
        }
    };

(function () {
    Vue.component('addFormGrad',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormGrad/addFormGrad.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                watch:{
                },
                methods:{
                    text:function(){
                        $('#textArea').on("keyup",function(){
                            $('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
                            if($('#textArea').val().length > 200){
                                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                                $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
                            }
                        })
                    },
                    getClass:function(e,param){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                        this.formData.competentView = param;
                    },
                    getExpertClass:function(e,param){
                      $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                      this.formData.expertView = param;
                      this.rules.expertReviewName[0].required = false;
                    },
                    btnBoolen:function(e,param){
                      $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                      if(e.target.innerHTML == '是'){
                        $("#direHide1").show();
                        $("#approval").show();
                        this.rules.competentName[0].required = true;
                        this.rules.competentName[1].min = 1;
                        this.rules.competentView[0].required = true;
                        this.rules.directorOpinionName[0].required = true;
                      }else{
                          $("#direHide1").hide();
                          $("#approval").hide();
                          this.formData.competentName = "";
                          this.formData.competentView = "";
                          this.formData.directorOpinionName = "";
                          this.formData.directorOpinionId = "";
                          this.rules.competentName[0].required = false;
                          this.rules.competentName[1].min = 0;
                          this.rules.competentView[0].required = false;
                          this.rules.directorOpinionName[0].required = false;
                      }
                      this.formData.competentIsExisting = param;
                    },
                    getFile:function(obj, ele, elm){
                        var btn = $(obj);
                        var oInup = $(ele);
                        var fileName = $(elm);
                        var btnValue = btn.val();
                        var arr = [];
                        var str = btnValue.split("\\");
                        arr.push(str[str.length-1]);
                        oInup.val(str[str.length-1]);
                        fileName.val(str[str.length-1]);
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
	                    	if(fileFormat[1] != 'pdf' && fileFormat[1] != 'xls' && fileFormat[1] != 'xlsm'&& fileFormat[1] != 'xlsx'  && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx' && fileFormat[1] !='zip' ){
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
                    	}
                    },
                    onUploadSuccessMethod: function(_self,responseData){
//                    	this.$refs.refOnUpload.value = null;
                      this.formData.directorOpinionName=responseData.data.attachName;
                      this.formData.directorOpinionPath=responseData.data.uploadUrl;
                      var fileHtml='<li><div class="fl updwon1">'+responseData.data.attachName+'</div><i class="el-icon-close fl del1"></i></li>'
                      $("#fileList").html(fileHtml);
                      $(".del1").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                        _self.formData.directorOpinionName='';
                        _self.formData.directorOpinionPath='';
                      });
                      $(".updwon1").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                      });
                    },
                    onUpload1: function(e){
                    	
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
	                    	if(fileFormat[1] != 'pdf' && fileFormat[1] != 'xls' && fileFormat[1] != 'xlsm'&& fileFormat[1] != 'xlsx'  && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx' && fileFormat[1] !='zip'){
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
	                      ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod1);
                    	}
                    },
                    onUploadSuccessMethod1: function(_self,responseData){
//                    	this.$refs.refOnUpload1.value = null;
                      this.formData.gradingReportName=responseData.data.attachName;
                      this.formData.gradingReportPath=responseData.data.uploadUrl;
                      
                      var fileHtml='<li><div class="fl updwon2">'+responseData.data.attachName+'</div><i class="el-icon-close fl del2"></i></li>'
                      $("#fileList1").html(fileHtml);
                      $(".del2").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                        _self.formData.gradingReportName='';
                        _self.formData.gradingReportName='';
                      });
                      $(".updwon2").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                      });
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
	                    	if(fileFormat[1] != 'pdf' && fileFormat[1] != 'xls' && fileFormat[1] != 'xlsm'&& fileFormat[1] != 'xlsx'  && fileFormat[1] != 'rar' && fileFormat[1] !='doc' && fileFormat[1] !='docx' && fileFormat[1] !='zip'){                    		this.$alert('不接受此文件类型！', '信息提示', {
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
                    	}
                    },
                    onUploadSuccessMethod2: function(_self,responseData){
//                    	this.$refs.refOnUpload2.value = null;
                      this.formData.expertReviewName=responseData.data.attachName;
                      this.formData.expertReviewPath=responseData.data.uploadUrl;
                      var fileHtml='<li><div class="fl updwon3">'+responseData.data.attachName+'</div><i class="el-icon-close fl del3"></i></li>'
                      $("#fileList2").html(fileHtml);
                      $(".del3").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                        _self.formData.expertReviewName='';
                        _self.formData.expertReviewPath='';
                      });
                      $(".updwon3").click(function(){
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
                        window.location.href = originUrl+"/fileHandle/downloadFile?uploadUrl="+path+"&attachName="+name;
                      }else{
                        //下载路径
                        window.location.href = originUrl+ "/fileHandle/downloadFile?fileId="+path;
                      }
                        
                    },
                    getUpFile:function(obj, ele, elm,target){
                        var btn = $(obj);
                        var oInup = $(ele);
                        var fileuse = $(elm);
                        var btnValue = btn.val();
                        var arr = [];
                        var str = btnValue.split("\\");
                        arr.push(str[str.length-1]);
                        oInup.val(str[str.length-1]);
                        fileuse.val(str[str.length-1]);
                        var strh = '';
                        for(var i =0;i<arr.length;i++){
                            strh += '<span class="active">'+arr[i]+'<i>X</i></span>';
                        }
                        var upList = document.getElementById('upLise');
                        upList.innerHTML = strh;
                        upList.getElementsByTagName('i')[0].onclick = function () {
                            this.parentNode.parentNode.removeChild(this.parentNode);
                        };
                        var fileSize = 0;
                        // jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
                        var bro=$.browser;
                        var binfo="";
                        if(bro.msie){//获取浏览器及版本号
                            var isIE = true;
                        }
                        if(isIE && !target.files){
                            var filePath = target.value;
                            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                            var file = fileSystem.GetFile (filePath);
                            fileSize = file.Size;
                        }else{
                            fileSize = target.files[0].size;
                        }
                        var size = fileSize / 1024;
                        //这里限制大小
                        if(size>(30*1024*1024)){
                            alert("附件不能大于2M");
                            target.value="";
                            return
                        }
                        var name=target.value;
                        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
                        //这里限制类型
                        if(fileName !="word" && fileName !="pdf" && fileName != "exl" && fileName != "zip" && fileName != "rar" && fileName != "sep" ){
                            alert("请选择文件类型必须是.word、pdf、exl、zip、rar、sep中的一种格式文档！");
                            target.value="";
                            return
                        }
                    },
                    checkFnAll:function (index) {
                      this.formData.fkBizSPRankLevel = index;
                      switch (index) {
                      case 101:
                        $("#10101").prop("checked",true);
                        
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        this.formData.fkBizSPRankDegree ="10101"; 
                        
                        break;
                      case 102:
                        $("#10201").prop("checked",true);
                        $("#10202").prop("checked",true);
                        
                        $("#10101").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        this.formData.fkBizSPRankDegree ="10201,10201"; 
                        
                        break;
                      case 103:
                        $("#10301").prop("checked",true);
                        $("#10302").prop("checked",true);
                        $("#10303").prop("checked",true);
                        
                        $("#10101").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        this.formData.fkBizSPRankDegree ="10301,10302,10303"; 
                        
                        break;
                      case 104:
                        $("#10401").prop("checked",true);
                        $("#10402").prop("checked",true);
                        
                        $("#10101").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        
                        this.formData.fkBizSPRankDegree ="10401,10402"; 
                        
                        break;
                      case 105:
                        $("#10501").prop("checked",true);
                        
                        $("#10101").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        
                        this.formData.fkBizSPRankDegree ="10501"; 
                        
                        break;
                      default:
                        break;
                      }
                      switch (this.formData.fkBizSPRankLevel) {
                      case 101:
                        $("#01").prop("checked",true);
                        break;
                      case 102:
                        $("#02").prop("checked",true);
                        break;
                      case 103:
                        $("#03").prop("checked",true);
                        break;
                      case 104:
                        $("#04").prop("checked",true);
                        break;
                      case 105:
                        $("#05").prop("checked",true);
                        break;
                      default:
                        this.formData.fkBizSPRankLevel=''
                        this.formData.fkBizSPRankDegree=''
                        break;
                      }
                      if(this.formData.fkBizSPRankLevel==''&&this.formData.fkBizSystemLevel==''){
                        this.formData.fkSpRanklevel = 0;
                      }

                      if(parseInt(this.formData.fkBizSPRankLevel)+100<parseInt(this.formData.fkBizSystemLevel)){
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSystemLevel)+100;
                      }else{
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSPRankLevel)+200;
                      }
                      if(this.formData.fkSpRanklevel>=303){
                        this.nextBtn = true;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = true;
	                        }
                        }
                      }else{
                        this.nextBtn = false;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = false;
	                        }
                        }
                      }
                      if(this.formData.fkSpRanklevel==301){
                      	console.log('fkSpRanklevel=========='+this.formData.fkSpRanklevel);
                        this.expertType = true;
                      }else{
                      	console.log('fkSpRanklevel=========='+this.formData.fkSpRanklevel);
                        this.expertType = false;
                        this.formData.expertView = 1;
                      }
                    },
                    
                    fnChecked:function (index,num) {
                      this.formData.fkBizSPRankLevel = index;
                      switch (index) {
                      case 101:
                        
                        if($("#"+num+"").is(':checked')){
                          $("#101").prop("checked",true);
                          this.formData.fkBizSPRankDegree ="10101"; 
                        }else{
                          $("#101").removeAttr("checked");
                          this.formData.fkBizSPRankLevel = '';
                        }
                        
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        break;
                      case 102:
                        if($("#10201").is(':checked')||$("#10202").is(':checked')){
                          $("#102").prop("checked",true);
                          if($("#10201").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10201"; 
                          }else if($("#10202").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10202"; 
                          }else{
                            this.formData.fkBizSPRankDegree ="10201,10202"; 
                          }
                        }else{
                          $("#102").removeAttr("checked");
                          this.formData.fkBizSPRankLevel = '';
                        }
                        $("#10101").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        
                        break;
                      case 103:
                        if($("#10301").is(':checked')||$("#10302").is(':checked')||$("#10303").is(':checked')){
                          $("#103").prop("checked",true);
                          if($("#10301").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10301"; 
                          }else if($("#10302").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10302"; 
                          }else if($("#10303").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10303"; 
                          }else{
                            this.formData.fkBizSPRankDegree ="10301,10302,10303"; 
                          }
                        }else{
                          $("#103").removeAttr("checked");
                          this.formData.fkBizSPRankLevel = '';
                        }
                        $("#10101").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        
                        break;
                      case 104:
                        if($("#10401").is(':checked')||$("#10402").is(':checked')){
                          $("#104").prop("checked",true);
                          if($("#10401").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10401"; 
                          }else if($("#10402").is(':checked')){
                            this.formData.fkBizSPRankDegree ="10402"; 
                          }else{
                            this.formData.fkBizSPRankDegree ="10401,10402"; 
                          }
                        }else{
                          $("#104").removeAttr("checked");
                          this.formData.fkBizSPRankLevel = '';
                        }
                        $("#10101").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10501").removeAttr("checked");
                        
                        break;
                      case 105:
                        if($("#"+num+"").is(':checked')){
                          $("#105").prop("checked",true);
                        }else{
                          $("#105").removeAttr("checked");
                          this.formData.fkBizSPRankLevel = '';
                        }
                        
                        $("#10101").removeAttr("checked");
                        $("#10201").removeAttr("checked");
                        $("#10202").removeAttr("checked");
                        $("#10301").removeAttr("checked");
                        $("#10302").removeAttr("checked");
                        $("#10303").removeAttr("checked");
                        $("#10401").removeAttr("checked");
                        $("#10402").removeAttr("checked");
                        
                        break;
                      default:
                        break;
                      }
                      if(this.formData.fkBizSPRankLevel == ''&&this.formData.fkBizSystemLevel == ''){
                        this.formData.fkSpRanklevel = 0;
                      }
                      switch (this.formData.fkBizSPRankLevel) {
                      case 101:
                        $("#01").prop("checked",true);
                        break;
                      case 102:
                        $("#02").prop("checked",true);
                        break;
                      case 103:
                        $("#03").prop("checked",true);
                        break;
                      case 104:
                        $("#04").prop("checked",true);
                        break;
                      case 105:
                        $("#05").prop("checked",true);
                        break;
                      default:
                        $("#01").removeAttr("checked");
                        $("#02").removeAttr("checked");
                        $("#03").removeAttr("checked");
                        $("#04").removeAttr("checked");
                        $("#05").removeAttr("checked");
                        this.formData.fkBizSPRankLevel=''
                        this.formData.fkBizSPRankDegree=''
                        break;
                      }
                      if(this.formData.fkBizSPRankLevel==''&&this.formData.fkBizSystemLevel==''){
                        this.formData.fkSpRanklevel = 0;
                      }

                      if(parseInt(this.formData.fkBizSPRankLevel)+100<parseInt(this.formData.fkBizSystemLevel)){
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSystemLevel)+100;
                      }else{
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSPRankLevel)+200;
                      }
                      if(this.formData.fkSpRanklevel>=303){
                        this.nextBtn = true;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = true;
	                        }
                        }
                      }else{
                        this.nextBtn = false;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = false;
	                        }
                        }
                      }
                      if(this.formData.fkSpRanklevel==301){
                        this.expertType = true;
                      }else{
                        this.expertType = false;
                        this.formData.expertView = 1;
                      }
                    },
                    fnCheckAll:function (index) {
                      this.formData.fkBizSystemLevel = index;
                      switch (index) {
                      case 201:
                        $("#20101").prop("checked",true);
                        
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        this.formData.fkBizSystemDegree ="20101"; 
                        
                        break;
                      case 202:
                        $("#20201").prop("checked",true);
                        $("#20202").prop("checked",true);
                        
                        $("#20101").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        this.formData.fkBizSystemDegree ="20201,20202"; 
                        
                        break;
                      case 203:
                        $("#20301").prop("checked",true);
                        $("#20302").prop("checked",true);
                        $("#20303").prop("checked",true);
                        $("#20101").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        this.formData.fkBizSystemDegree ="20301,20302,20303"; 
                        
                        break;
                      case 204:
                        $("#20401").prop("checked",true);
                        $("#20402").prop("checked",true);
                        $("#20101").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        this.formData.fkBizSystemDegree ="20401,20402"; 
                        
                        break;
                      case 205:
                        $("#20501").prop("checked",true);
                        
                        $("#20101").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        
                        this.formData.fkBizSystemDegree ="20501"; 
                        
                        break;
                      default:
                        break;
                      }
                      switch (this.formData.fkBizSystemLevel) {
                        case 201:
                          $("#021").prop("checked",true);
                          break;
                        case 202:
                          $("#022").prop("checked",true);
                          break;
                        case 203:
                          $("#023").prop("checked",true);
                          break;
                        case 204:
                          $("#024").prop("checked",true);
                          break;
                        case 205:
                          $("#025").prop("checked",true);
                          break;
                        default:
                          this.formData.fkBizSystemLevel = "";
                          this.formData.fkBizSystemDegree = "";
                          break;
                      }
                      if(this.formData.fkBizSPRankLevel==''&&this.formData.fkBizSystemLevel==''){
                        this.formData.fkSpRanklevel = 0;
                      }

                      if(parseInt(this.formData.fkBizSPRankLevel)+100<parseInt(this.formData.fkBizSystemLevel)){
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSystemLevel)+100;
                      }else{
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSPRankLevel)+200;
                      }
                      if(this.formData.fkSpRanklevel>=303){
                        this.nextBtn = true;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = true;
	                        }
                        }
                      }else{
                        this.nextBtn = false;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = false;
	                        }
                        }
                      }
                      if(this.formData.fkSpRanklevel==301){
                        this.expertType = true;
                      }else{
                        this.expertType = false;
                        this.formData.expertView = 1;
                      }
                    },
                    checkAll:function (index) {
                      this.formData.fkBizSystemLevel = index;
                      switch (index) {
                      case 201:
                        if($("#20101").is(':checked')){
                          $("#201").prop("checked",true);
                          this.formData.fkBizSystemDegree ="20101"; 
                        }else{
                          $("#201").removeAttr("checked");
                          this.formData.fkBizSystemLevel = '';
                        }
                        
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        break;
                      case 202:
                        if($("#20201").is(':checked')||$("#20202").is(':checked')){
                          $("#202").prop("checked",true);
                          if($("#20201").is(':checked')){
                            this.formData.fkBizSystemDegree ="20201"; 
                          }else if($("#20202").is(':checked')){
                            this.formData.fkBizSystemDegree ="20202"; 
                          }else{
                            this.formData.fkBizSystemDegree ="20201,20202"; 
                          }
                        }else{
                          $("#202").removeAttr("checked");
                          this.formData.fkBizSystemLevel = '';
                        }
                        $("#20101").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        break;
                      case 203:
                        if($("#20301").is(':checked')||$("#20302").is(':checked')||$("#20303").is(':checked')){
                          $("#203").prop("checked",true);
                          if($("#20301").is(':checked')){
                            this.formData.fkBizSystemDegree ="20301"; 
                          }else if($("#20302").is(':checked')){
                            this.formData.fkBizSystemDegree ="20302"; 
                          }else if($("#20303").is(':checked')){
                            this.formData.fkBizSystemDegree ="20303"; 
                          }else{
                            this.formData.fkBizSystemDegree ="20301,20202,20303"; 
                          }
                        }else{
                          $("#203").removeAttr("checked");
                          this.formData.fkBizSystemLevel = '';
                        }
                        $("#20101").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        break;
                      case 204:
                        if($("#20401").is(':checked')||$("#20402").is(':checked')){
                          $("#204").prop("checked",true);
                          if($("#20401").is(':checked')){
                            this.formData.fkBizSystemDegree ="20401"; 
                          }else if($("#20402").is(':checked')){
                            this.formData.fkBizSystemDegree ="20402"; 
                          }else{
                            this.formData.fkBizSystemDegree ="20401,20402"; 
                          }
                        }else{
                          $("#204").removeAttr("checked");
                          this.formData.fkBizSystemLevel = '';
                        }
                        
                        $("#20101").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20501").removeAttr("checked");
                        
                        break;
                      case 205:
                        if($("#20501").is(':checked')){
                          $("#205").prop("checked",true);
                          this.formData.fkBizSystemDegree ="20501"; 
                        }else{
                          $("#205").removeAttr("checked");
                          this.formData.fkBizSystemLevel = '';
                        }
                        $("#20101").removeAttr("checked");
                        $("#20201").removeAttr("checked");
                        $("#20202").removeAttr("checked");
                        $("#20301").removeAttr("checked");
                        $("#20302").removeAttr("checked");
                        $("#20303").removeAttr("checked");
                        $("#20401").removeAttr("checked");
                        $("#20402").removeAttr("checked");
                        
                        break;
                      default:
                        break;
                      }
                      switch (this.formData.fkBizSystemLevel) {
                      case 201:
                        $("#021").prop("checked",true);
                        break;
                      case 202:
                        $("#022").prop("checked",true);
                        break;
                      case 203:
                        $("#023").prop("checked",true);
                        break;
                      case 204:
                        $("#024").prop("checked",true);
                        break;
                      case 205:
                        $("#025").prop("checked",true);
                        break;
                      default:
                        $("#021").removeAttr("checked");
                        $("#022").removeAttr("checked");
                        $("#023").removeAttr("checked");
                        $("#024").removeAttr("checked");
                        $("#025").removeAttr("checked");
                        this.formData.fkBizSystemLevel = "";
                        this.formData.fkBizSystemDegree = "";
                        break;
                      }
                      if(this.formData.fkBizSPRankLevel==''&&this.formData.fkBizSystemLevel==''){
                        this.formData.fkSpRanklevel = 0;
                      }
                      
                      if(parseInt(this.formData.fkBizSPRankLevel)+100<parseInt(this.formData.fkBizSystemLevel)){
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSystemLevel)+100;
                      }else{
                        this.formData.fkSpRanklevel = parseInt(this.formData.fkBizSPRankLevel)+200;
                      }
                      if(this.formData.fkSpRanklevel>=303){
                        this.nextBtn = true;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = true;
	                        }
                        }
                      }else{
                        this.nextBtn = false;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = false;
	                        }
                        }
                      }
                      if(this.formData.fkSpRanklevel==301){
                        this.expertType = true;
                      }else{
                        this.expertType = false;
                        this.formData.expertView = 1;
                      }
                    },
                    // 获取安全等级信息
                    getProtectionGradeInfoMethod : function(_self) {
                      /*ajaxMethod(_self, 'post',
                          'systemCode/querySystemCodeForKeySystemCode', true,
                          '{"codeType":"11"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getProtectionGradeInfoSuccessMethod);*/
                    },
                    // 获取安全按等级成功 三级
                    getProtectionGradeInfoSuccessMethod : function(_self, responseData) {
                      //_self.protectionGrade = responseData.data;
                      //var a=JSON.parse('{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"systemCode":"1","codeName":"业务信息","systemOneInfo":[{"systemCode":"101","codeName":"Ⅰ级","systemTwoInfo":[{"systemCode":"10101","codeName":"仅对公民、法人和其他组织的合法权益造成损害"}]},{"systemCode":"102","codeName":"Ⅱ级","systemTwoInfo":[{"systemCode":"10201","codeName":"对公民、法人和其他组织的合法权益造成严重损害"},{"systemCode":"10202","codeName":"对社会秩序和公共利益造成损害"}]},{"systemCode":"103","codeName":"Ⅲ级","systemTwoInfo":[{"systemCode":"10301","codeName":"对社会秩序和公共利益造成严重损害"},{"systemCode":"10302","codeName":"对国家安全造成严重损害"},{"systemCode":"10303","codeName":"对公民、法人和其他组织的合法权益造成特别严重损害"}]},{"systemCode":"104","codeName":"Ⅳ级","systemTwoInfo":[{"systemCode":"10401","codeName":"对社会秩序和公共利益造成特别严重损害"},{"systemCode":"10402","codeName":"对国家安全造成严重损害"}]},{"systemCode":"105","codeName":"Ⅴ级","systemTwoInfo":[{"systemCode":"10501","codeName":"对国家安全造成特别严重损害"}]}]},{"systemCode":"2","codeName":"系统服务","systemOneInfo":[{"systemCode":"201","codeName":"Ⅰ级","systemTwoInfo":[{"systemCode":"20101","codeName":"仅对公民、法人和其他组织的合法权益造成损害"}]},{"systemCode":"202","codeName":"Ⅱ级","systemTwoInfo":[{"systemCode":"20201","codeName":"对公民、法人和其他组织的合法权益造成严重损害"},{"systemCode":"20202","codeName":"对社会秩序和公共利益造成损害"}]},{"systemCode":"203","codeName":"Ⅲ级","systemTwoInfo":[{"systemCode":"20301","codeName":"对社会秩序和公共利益造成严重损害"},{"systemCode":"20302","codeName":"对国家安全造成严重损害"},{"systemCode":"20303","codeName":"对公民、法人和其他组织的合法权益造成特别严重损害"}]},{"systemCode":"204","codeName":"Ⅳ级","systemTwoInfo":[{"systemCode":"20401","codeName":"对社会秩序和公共利益造成特别严重损害"},{"systemCode":"20402","codeName":"对国家安全造成严重损害"}]},{"systemCode":"205","codeName":"Ⅴ级","systemTwoInfo":[{"systemCode":"20501","codeName":"对国家安全造成特别严重损害"}]}]},{"systemCode":"3","codeName":"信息系统安全保护等级","systemOneInfo":[{"systemCode":"301","codeName":"Ⅰ级","systemTwoInfo":[]},{"systemCode":"302","codeName":"Ⅱ级","systemTwoInfo":[]},{"systemCode":"303","codeName":"Ⅲ级","systemTwoInfo":[]},{"systemCode":"304","codeName":"Ⅳ级","systemTwoInfo":[]},{"systemCode":"305","codeName":"Ⅴ级","systemTwoInfo":[]}]}]}');
                      //_self.protectionGrade = a.data;
                      
                      
//                      $("#grade li").each(function (index, ele) {
//                        console.log(ele)
//                      return "";
//                    }).get();
//                    for(var i=0;i<array.length;i++){
//                      if(array[i]!=''){
//                        array[i].classList.add("btnColor");
//                        other=false;
//                      }
//                    }
                    },
                    getGradeMethod: function(_self,id) {
                      ajaxMethod(_self, 'post','grading/queryEditGrading', true,
                      '{"fkSystemId":"'+id+'"}', 'json',
                      'application/json;charset=UTF-8',
                      _self.getGradeSuccessMethod);
                    },
                    // 获取回显安全按等级成功
                    getGradeSuccessMethod : function(_self, responseData) {
                      if(responseData.data!=null){
                        _self.formData = responseData.data;
                        if(_self.formData.competentIsExisting == '1'){
                          $("#direHide1").show();
                          $("#approval").show();
                          if(responseData.data.directorOpinionName!=''&&responseData.data.directorOpinionName!=null){
                            var fileHtml='<li><div class="fl updwon1">'+responseData.data.directorOpinionName+'</div><i class="el-icon-close fl del1"></i></li>'
                            $("#fileList").html(fileHtml);
                            $(".del1").click(function(){
                              $(this).parent("li").remove();
                              _self.fileDel(responseData.data.directorOpinionId,2);
                              _self.formData.directorOpinionName = '';
                              _self.formData.directorOpinionId = '';
                            });
                            $(".updwon1").click(function(){
                              //$(this).parent("li").remove();
                              _self.fileDownload(responseData.data.directorOpinionId,2);
                            });
                          }
                          _self.rules.competentName[0].required = true;
                          _self.rules.competentName[1].min = 1;
                          _self.rules.competentView[0].required = true;
                          _self.rules.directorOpinionName[0].required = true;
                        }else{
                          $("#direHide1").hide();
                          $("#approval").hide();
                          _self.formData.competentName = "";
                          _self.formData.competentView = "";
                          _self.formData.directorOpinionName = "";
                          _self.formData.directorOpinionId = "";
                          _self.rules.competentName[0].required = false;
                          _self.rules.competentName[1].min = 0;
                          _self.rules.competentView[0].required = false;
                          _self.rules.directorOpinionName[0].required = false;
                        }
                        
                        if(responseData.data.gradingReportName!=''&&responseData.data.gradingReportName!=null){
                          var fileHtml='<li><div class="fl updwon2">'+responseData.data.gradingReportName+'</div><i class="el-icon-close fl del2"></i></li>'
                          $("#fileList1").html(fileHtml);
                          $(".del2").click(function(){
                            $(this).parent("li").remove();
                            _self.formData.gradingReportName = '';
                            _self.formData.gradingReportId = '';
                            _self.fileDel(responseData.data.gradingReportId,2);
                          });
                          $(".updwon2").click(function(){
                            //$(this).parent("li").remove();
                            _self.fileDownload(responseData.data.gradingReportId,2);
                          });
                        }
                        if(responseData.data.expertReviewName!=''&&responseData.data.expertReviewName!=null){
                          var fileHtml='<li><div class="fl updwon3">'+responseData.data.expertReviewName+'</div><i class="el-icon-close fl del3"></i></li>'
                          $("#fileList2").html(fileHtml);
                          $(".del3").click(function(){
                            $(this).parent("li").remove();
                            _self.fileDel(responseData.data.expertReviewId,2);
                            _self.formData.expertReviewName = '';
                            _self.formData.expertReviewId = '';
                          });
                          $(".updwon3").click(function(){
                            //$(this).parent("li").remove();
                            _self.fileDownload(responseData.data.expertReviewId,2);
                          });
                        }
                        
                        if(_self.formData.competentIsExisting!=''){
                          var array = $('#baseMes1').find('div').map(function (index, ele) {
                            if(_self.formData.competentIsExisting==1&&ele.innerHTML=='是'){
                              return ele;
                            }else if(_self.formData.competentIsExisting==2&&ele.innerHTML=='否'){
                              return ele;
                            }else{
                               return "";
                            }
                          }).get();
                          for(var i=0;i<array.length;i++){
                            if(array[i]!=''){
                              array[i].classList.add("btnColor");
                            }
                          }
                        }
                        if(_self.formData.competentView!=''){
                          var array = $('#baseMes2').find('div').map(function (index, ele) {
                            if(_self.formData.competentView==1&&ele.innerHTML=='已审批'){
                              return ele;
                            }else if(_self.formData.competentView==2&&ele.innerHTML=='未审批'){
                              return ele;
                            }else{
                               return "";
                            }
                          }).get();
                          $(array).addClass('btnColor');
                        }
                        if(_self.formData.expertView!=''){
                          var array = $('#baseMes3').find('div').map(function (index, ele) {
                            if(_self.formData.expertView==1&&ele.innerHTML=='已审批'){
                              return ele;
                            }else if(_self.formData.expertView==2&&ele.innerHTML=='未审批'){
                              return ele;
                            }else{
                               return "";
                            }
                          }).get();
                          $(array).addClass('btnColor');
                        }
                      }
                      if(_self.formData.fkBizSPRankDegree!=''){
                        _self.formData.fkBizSPRankDegree = _self.formData.fkBizSPRankDegree+",";
                        var bizSPRankDegree = _self.formData.fkBizSPRankDegree.split(",");
                        for(var i=0;i<bizSPRankDegree.length;i++){
                          if(bizSPRankDegree[i]!=''){
                            $("#"+bizSPRankDegree[i]+"").prop("checked",true);
                          }
                        }
                      }
                      if(_self.formData.fkBizSystemDegree!=''){
                        _self.formData.fkBizSystemDegree = _self.formData.fkBizSystemDegree+",";
                        var bizSystemDegree = _self.formData.fkBizSystemDegree.split(",");
                        for(var i=0;i<bizSystemDegree.length;i++){
                          if(bizSystemDegree[i]!=''){
                            $("#"+bizSystemDegree[i]+"").prop("checked",true);
                          }
                        }
                      }
                      $("#"+_self.formData.fkBizSPRankLevel+"").prop("checked",true);
                      switch (parseInt(_self.formData.fkBizSPRankLevel)) {
                      case 101:
                        $("#01").prop("checked",true);
                        break;
                      case 102:
                        $("#02").prop("checked",true);
                        break;
                      case 103:
                        $("#03").prop("checked",true);
                        break;
                      case 104:
                        $("#04").prop("checked",true);
                        break;
                      case 105:
                        $("#05").prop("checked",true);
                        break;
                      default:
                        break;
                      }
                      $("#"+_self.formData.fkBizSystemLevel+"").prop("checked",true);
                      switch (parseInt(_self.formData.fkBizSystemLevel)) {
                      case 201:
                        $("#021").prop("checked",true);
                        break;
                      case 202:
                        $("#022").prop("checked",true);
                        break;
                      case 203:
                        $("#023").prop("checked",true);
                        break;
                      case 204:
                        $("#024").prop("checked",true);
                        break;
                      case 205:
                        $("#025").prop("checked",true);
                        break;
                      default:
                        break;
                      }
                      
                      if(this.formData.fkSpRanklevel>=303){
                      	console.log(11111);
                        this.nextBtn = true;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = true;
	                        }
                        }
                      }else{
                        this.nextBtn = false;
                        if(type!="new"&&type!="change"){
	                        if(data1!=null){
	                          data1.materialShow = false;
	                        }
                        }
                      }
                      if(this.formData.fkSpRanklevel==301){
                        this.expertType = true;
                      }else{
                        if(parseInt(this.formData.fkSpRanklevel)>0){
                          this.expertType = false;
                          this.formData.expertView = 1;
                        }
                      }
                      
                      //时间显示问题
                      if(this.formData.fillDate == '1970-01-01'){
                      	this.formData.fillDate = '';
                      }
                    },
                    
                    submitGradeInfo: function() {
                      ajaxMethod(this, 'post',
                      'grading/saveGrading', true,
                      JSON.stringify(this.formData), 'json',
                      'application/json;charset=UTF-8',
                      this.submitGradeInfoSuccessMethod);
                    },
                    // 获取安全按等级成功
                    submitGradeInfoSuccessMethod : function(_self, responseData) {
                      if(type=="change"){
                        window.location.href = "applicationChangeMaterialPage?systemId="+systemId;
                      }else if(type=="grading"){
                        window.location.href = "applicationGradingInfoPage?systemId="+systemId;
                      }else{
                        window.location.href = "addCompanyMaterialPage?systemId="+systemId;
                      }
                    },
                    // 获取系统信息
                    getSystem : function(_self) {
                    	ajaxMethod(this, 'post',
                        'system/querySystemInformationBySystemId', true,
                        '{"systemId":"'+systemId+'"}', 'json',
                        'application/json;charset=UTF-8',
                        this.getSystemSuccess);
                    } ,
                    getSystemSuccess : function(_self,result){
                    	this.formData.systemName = result.systemName;
                    	this.formData.gradeRecordSysName = result.data.gradeRecordSysName;                    	
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
                    }
                },
                created: function() {
                    //this.getPermitJurisdictionInfo(this);
                    // 设置默认长度
                    this.smccChecdArr[0].length = 1;
                    this.smccChecdArr[1].length = 2;
                    //安全等级信息
                    this.getProtectionGradeInfoMethod(this);
                    //获取系统信息
                    this.getSystem(this);
                    if(systemId!=null&&systemId!=''){
                      this.getGradeMethod(this,systemId);
                      this.formData.fkSystemId = systemId;
                    }
                    $("#direHide1").hide();
                    $("#approval").hide();
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
                   var _self=this;
                   bus.$on('addGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('addGradAjax',"add");
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
                   bus.$on('addSubmitGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('addSubmitGradAjax',"add");
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
                   bus.$on('addPreGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('addPreGradAjax',"add");
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
                   bus.$on('addNextGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('addNextGradAjax',"add");
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
                   
                   bus.$on('changeGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('changeGradAjax',"add");
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
                   bus.$on('changeSubmitGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('changeSubmitGradAjax',"add");
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
                   bus.$on('changePreGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('changePreGradAjax',"add");
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
                   bus.$on('changeNextGradName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('changeNextGradAjax',"add");
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
                   
                   bus.$on('gradGradingName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('gradGradingAjax',"add");
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
                   bus.$on('gradSubmitGradingName',function(meg){
                     if(meg!=null){
                       _self.$refs[meg].validate(function (valid) {
                         if (valid) {
                           bus.$emit('gradSubmitGradingAjax',"add");
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
                   bus.$on('materialFormName',function(meg){
                  	 if(meg!=null){
                  		 _self.$refs[meg].validate(function (valid) {
                  			 if (valid) {
                  				 bus.$emit('materialFormAjax',"add");
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