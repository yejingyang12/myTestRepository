var data={
        formData:{
          gradingId:'',
          fkSystemId:'',
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
        expertType:true,
        sysName:'系统名称',//系统名称
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
        description:'定级说明描述',//定级说明描述
        competentName:'主管部门名称',//主管部门名称
        competentGrad:[],//主管部门审批定级情况
        gradPresen:'定级报告',//定级报告
        gradPresenName:'定级报告名称',//定级报告名称
        expertReview:[],//专家评审
        expertReviewName:'评审附件名称',//专家评审名称
        fillFormPerson:'填表人',//填表人
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
        nextBtn:true
    };

(function () {
    
    $("#addFormGrad").validate({
        sysName:{
            required:true,
            sysName:true
        },
        safetyPro:{
            required:true,
            safetyPro:true
        },
        competent:{
            required:true,
            competent:true
        },
        competentName:{
            required:true,
            competentName:true
        },
        competentGrad:{
            required:true,
            competentGrad:true
        },
        gradPresen:{
            required:true,
            gradPresen:true
        },
        gradPresenName:{
            required:true,
            gradPresenName:true
        },
        expertReview:{
            required:true,
            expertReview:true
        },
        expertReviewName:{
            required:true,
            expertReviewName:true
        },
        fillFormPerson:{
            required:false,
            fillFormPerson:true
        },
        fillData:{
            required:false,
            fillData:true
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
            $(form).ajaxSubmit({
                url: "php/order.php",
                type: "post",
                success: function () {
                    alert("提交成功！");
                    $(".shadow").show();
                    $("#submit").click(function () {
                       // window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                    })
                }
            })
        }
    });

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
                    },
                    btnBoolen:function(e,param){
                      $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                      if(e.target.innerHTML == '是'){
                          $("#direHide1").show();
                          $("#approval").show();
                      }else{
                          $("#direHide1").hide();
                          $("#approval").hide();
                          this.formData.competentName = "";
                          this.formData.competentView = "";
                          this.formData.directorOpinionName = "";
                          this.formData.directorOpinionId = "";
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
                      var uploadData = new FormData(); 
                      uploadData.append('file', e.target.files[0]);
                      uploadData.append('type', 'test');
                      ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod);
                    },
                    onUploadSuccessMethod: function(_self,responseData){
                      
                      this.formData.directorOpinionName=responseData.data.attachName;
                      this.formData.directorOpinionPath=responseData.data.uploadUrl;
                      var fileHtml='<li><div class="fl updwon">'+responseData.data.attachName+'</div><i class="el-icon-close fl del"></i></li>'
                      $("#fileList").html(fileHtml);
                      $(".del").click(function(){
                        $(this).parent("li").remove();
                        _self.fileDel(responseData.data.uploadUrl,1,responseData.data.attachName);
                      });
                      $(".updwon").click(function(){
                        //$(this).parent("li").remove();
                        _self.fileDownload(responseData.data.uploadUrl,1,responseData.data.attachName);
                      });
                    },
                    onUpload1(e){
                      var uploadData = new FormData(); 
                      uploadData.append('file', e.target.files[0]);
                      uploadData.append('type', 'test');
                      ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod1);
                    },
                    onUploadSuccessMethod1: function(_self,responseData){
                      this.formData.gradingReportName=responseData.data.attachName;
                      this.formData.gradingReportPath=responseData.data.uploadUrl;
                    },
                    onUpload2(e){
                      var uploadData = new FormData(); 
                      uploadData.append('file', e.target.files[0]);
                      uploadData.append('type', 'test');
                      ajaxUploadMethod(this, 'POST','fileHandle/uploadFile', true,uploadData, 'json',this.onUploadSuccessMethod2);
                    },
                    onUploadSuccessMethod2: function(_self,responseData){
                      this.formData.expertReviewName=responseData.data.attachName;
                      this.formData.expertReviewPath=responseData.data.uploadUrl;
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
                      }else{
                        if(this.formData.fkSpRanklevel==301){
                          this.expertType = false;
                        }else{
                          this.expertType = true;
                        }
                        this.nextBtn = false;
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
                      }else{
                        this.nextBtn = false;
                        if(this.formData.fkSpRanklevel==301){
                          this.expertType = false;
                        }else{
                          this.expertType = true;
                        }
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
                      }else{
                        this.nextBtn = false;
                        if(this.formData.fkSpRanklevel==301){
                          this.expertType = false;
                        }else{
                          this.expertType = true;
                        }
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
                      }else{
                        this.nextBtn = false;
                        if(this.formData.fkSpRanklevel==301){
                          this.expertType = false;
                        }else{
                          this.expertType = true;
                        }
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
                            var fileHtml='<li><div class="fl updwon">'+responseData.data.directorOpinionName+'</div><i class="el-icon-close fl del"></i></li>'
                            $("#fileList").html(fileHtml);
                            $(".del").click(function(){
                              $(this).parent("li").remove();
                              _self.fileDel(responseData.data.directorOpinionId,2);
                            });
                            $(".updwon").click(function(){
                              //$(this).parent("li").remove();
                              _self.fileDownload(responseData.data.directorOpinionId,2);
                            });
                          }
                        }else{
                          $("#direHide1").hide();
                          $("#approval").hide();
                          _self.formData.competentName = "";
                          _self.formData.competentView = "";
                          _self.formData.directorOpinionName = "";
                          _self.formData.directorOpinionId = "";
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
                        this.nextBtn = true;
//                        if(data1!=null){
//                          data1.materialShow = true;
//                        }
                      }else{
                        this.nextBtn = false;
//                        if(data1!=null){
//                          data1.materialShow = false;
//                        }
                        if(this.formData.fkSpRanklevel==301){
                          this.expertType = false;
                        }else{
                          this.expertType = true;
                        }
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
                    }
                    
                },
                created: function() {
                    // 设置默认长度
                    this.smccChecdArr[0].length = 1;
                    this.smccChecdArr[1].length = 2;
                    //安全等级信息
                    this.getProtectionGradeInfoMethod(this);
                    if(systemId!=null&&systemId!=''){
                      this.getGradeMethod(this,systemId);
                      this.formData.fkSystemId = systemId;
                    }
                    $("#direHide1").hide();
                    $("#approval").hide();
                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())