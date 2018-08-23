/**
 * Created by timha on 2018/5/29.
 */
var data1={
	  dialogShow:2,
	  nameList:[],
	  showImprot:2,
	  showItem:10,
    activeName: 'first',
    inputs:null,
    tr:null,
    systemForm:"1",
    tishi:"",
    companyCode:"",
    systemForm:{
    	importSystemInfo:'',
      importSystemPath:'',
      pagesize:'',
      currentPage:'',
      total:'',
      totalPages:'',
      time:'',
      formData:{
        systemId:'',
        systemName:'',
        sysBusDescription:'',
        sysBusSituationType:'',
        whenInvestmentUse:'', 
      },
      queryData:{
        systemName:'',
        currentPage:''
      },
      txt:'',
      systemIds:[],
      rowOneSysInfo:null,//列表表头第一行的tr
	    imgList:null,//列表表头第一行的排序箭头
	    result:{},
	    rules:{
	    	importSystemInfo:[{required: true, message: '请选择导入文件', trigger: 'change'}],
	    }
    },
    
  };
(function () {
  
  Vue.component('mainSystemInfo',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/mainSystemInfo/mainSystemInfo.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data1;
        },
        computed:{
        	totalPages:function(){
        		var page = []; //显示页码
                //用当前激活页面驱动页面显示的分别
                if (this.systemForm.currentPage < this.showItem) { //当前页小于最大页码数（showItem），区分总页数是否达到最大页码数
                    //获取总页数和最大页码数较小的值
                    var i = Math.min(this.systemForm.totalPages, this.showItem);
                    while (i) {
                        //通过page的数组值显示页码
                        page.unshift(i--);
                    }
                } else { //当前页面大于最大页码数（showItem）时，区分显示的页码规则
                    var pagestart = this.systemForm.currentPage - Math.floor(this.showItem / 2); //获取显示的页码第一位页码（默认当前页居中）
                    var i = this.showItem; //用来显示多少（i）个页码
                    if (pagestart > (this.systemForm.totalPages - this.showItem)) { //第一个页码如果大于总页数减去展示的页码数，则当前页不能居中
                        pagestart = (this.systemForm.totalPages - this.showItem) + 1; //应该显示的第一个页码数
                    }
                    while (i--) {
                        //通过page的数组值显示页码
                        page.push(pagestart++);
                    }
                }
                return page;
            }
        	
        },        
        methods:{
        	xuanfu : function(data){
        		this.tishi = data; 
        	},
         //显示批量导入弹窗
          systemInfoImprot:function(){
          	this.showImprot=1;
          	$("#dialog").css("display","block");
          },
          submitForm:function() {
          	var _self=this;
			      this.$refs['systemForm'].validate(function(valid){
			          if (valid) {
			          	var uploadData = new FormData(); 
									uploadData.append('strFilePath', _self.systemForm.importSystemPath);
			          	ajaxUploadMethod(_self, 'POST','system/importForSystemTemplate', true,uploadData, 'json',_self.importForSystemSuccess);
			          } else {
			            _self.open5();
			            return false;
			          }
			        });
			    },
			    importForSystemSuccess:function(_self,responseData){
			    	if(responseData.msg=='成功'){
			    		$("#startBoxTest").show().delay(2000).fadeOut();
			    	}else{
			    		_self.$alert('<strong>导入失败！</strong>', '提示', {
			          dangerouslyUseHTMLString: true
			        });
			    	}
			    	_self.getSystemListInfoMethod(_self,{});
		    		_self.closes1();
			    },
			    open5:function() {
		        this.$alert('<strong>请填写信息</strong>', '提示', {
		          dangerouslyUseHTMLString: true
		        });
			    },			    
			    //文件上传
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
	          	if(fileFormat[1] != 'xls' && fileFormat[1] != 'xlsm'&& fileFormat[1] != 'xlsx' ){                  		this.$alert('不接受此文件类型！', '信息提示', {
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
          //回显上传文件
          onUploadSuccessMethod: function(_self,responseData){
          	this.$refs.refOnUpload.value = null;
          	_self.systemForm.importSystemInfo = responseData.data.attachName;
          	_self.systemForm.importSystemPath = responseData.data.uploadUrl;
          },
          fileDel:function(path){
						var _self = this;
						_self.systemForm.importSystemInfo = '';
						_self.systemForm.importSystemPath = '';
          },
          closes1:function () {
          	this.$refs['systemForm'].resetFields();
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none";
            $(".inquiry").css("display","none");
            $(".dialogShaw").css("display","none");
          },
         //点击“模板导出”显示弹窗
          systemInfoExport:function(){
         	   this.dialogShow=1;  
         	   bus.$emit("dialog",this.dialogShow); 
          },
        	closes:function () {
               	this.$refs['systemForm.formData'].resetFields();
                 var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
                 evaluationAlert.style.display="none";
                 $(".inquiry").css("display","none");
                 $(".dialogShaw").css("display","none");
          },
         //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
              //alert("当前页面已经是第一页")
            } else if (page > this.systemForm.totalPages) {
              //alert("当前页面已经是最后一页")
            } else {
              this.systemForm.queryData.currentPage = page;
              this.getSystemListInfoMethod(this,this.systemForm.queryData);
            }
          },
          hpageNum:function(_this){
            var a=this.systemForm.txt;
            if(a<=0||a>this.systemForm.totalPages){
              this.$message({
                message: '请输入正确页数',
                type: 'warning'
              });
            }else{
              this.systemForm.queryData.currentPage = a;
              this.getSystemListInfoMethod(this,this.systemForm.queryData);
            }
          },
          checkboxAllMethod:function(){
            if($("#checkboxAll1").is(':checked')){
              $(".firstChecked input").prop("checked",true);
            }else{
              $(".firstChecked input").removeAttr("checked");
            }
          },
          
          checkboxMethod:function(e,id){
            var value=10;
            $(".ids[type='checkbox']").each(function(i){
              if(!$(this).is(':checked')){
                value--;
              }
            });
            if(value==10){
              $("#checkboxAll").prop("checked",true);
            }else{
              $("#checkboxAll").removeAttr("checked");
            }
            if($(e.target).is(':checked')){
              this.systemForm.systemIds.push(id);
            }else{
              var ids = [];
              for(d in this.systemForm.systemIds){
                if(this.systemForm.systemIds[d]==id){}else{
                  ids.push(this.systemForm.systemIds[d]);
                }
              }
              this.systemForm.systemIds=ids;
            }
          },
          querySystemListInfoMethod:function() {
            this.getSystemListInfoMethod(this,this.systemForm.queryData);
          },
          handleClick:function(id){
            window.location.href = originUrl+encodeURI("/page/changeSystemInformationPage?systemId="+id);
          },
          newSystemInfoMethod:function(){
            window.location.href = originUrl+encodeURI("/page/newSystemInformationPage?companyCode="+this.companyCode);
          },
          //清空
          clearHeadle:function(){
            this.systemForm.queryData.systemName = '';
            this.getSystemListInfoMethod(this,{});
          },

          jinyong:function(){
            // for (var i = 0; i < data.inputs.length; i++) {
            //   console.log(data.inputs);
            //
            // }


          },
          getSystemListInfoMethod:function(_self,data) {
            ajaxMethod(_self, 'post',
                'system/querySystemList', true,
                JSON.stringify(data), 'json',
                'application/json;charset=UTF-8',
                _self.getSystemListInfoSuccessMethod);
         },
         // 获取成功
         getSystemListInfoSuccessMethod : function(_self, responseData) {
           _self.systemForm.formData = responseData.data.list;
           _self.systemForm.totalPages = responseData.data.totalPage;
           _self.systemForm.pagesize = responseData.data.pageSize;
           _self.systemForm.currentPage = responseData.data.currPage;
           _self.systemForm.total = responseData.data.totalCount;
           _self.systemForm.result = responseData.data;
           for(var i=0;i<responseData.data.list.length;i++){
          	 var array = responseData.data.list;
          	 var timeString = array[i].whenInvestmentUse;
          	 var time1 = timeString.split(" ");
          	 _self.systemForm.time = time1[0];
           }
         },
         //系统信息列表排序
         listsortInfo: function () {
           var imgArrow = data1.imgList;
           var flagOne = 1;
           // console.log(data.result.data);
           for (var i = 0; i < imgArrow.length; i++) {
             imgArrow[i].myindex = i;
             imgArrow[i].onclick = function () {
               flagOne *= -1;
               // //对每个数组也就是对应表格的每一列进行排序
               // console.log( data.result.data[0].systemName);
               switch (this.myindex){
                 case 0://系统名称
                   data1.systemForm.result.list.sort(function (a, b) {
                     return (a.systemName.localeCompare(b.systemName)) * flagOne
                   });
                   break;
                 case 1://业务类型
                   data1.systemForm.result.list.sort(function (a, b) {
                     return (a.sysBusDescription.localeCompare(b.sysBusDescription)) * flagOne
                   });
                   break;
                 case 2://业务描述
                   data1.systemForm.result.list.sort(function (a, b) {
                     return (a.sysBusSituationType.localeCompare(b.sysBusSituationType)) * flagOne
                   });
                   break;
                 case 3://投入使用时间
                   data1.systemForm.result.list.sort(function (a, b) {
                  	 return (new Date(a.whenInvestmentUse.split('-').join('/')).getTime()-new Date(b.whenInvestmentUse.split('-').join('/')).getTime()) * flagOne
                   });
                   break;
               }
             };
           }
         },
         // 获取单位Code
         getCompanyCode : function(_self) {
           ajaxMethod(_self, 'post',
               'jurisdiction/getCompanyCode', true,
               '{}', 'json',
               'application/json;charset=UTF-8',
               _self.getCompanyCodeSuccessMethod);
         },
         // 获取单位Code成功
         getCompanyCodeSuccessMethod : function(_self, responseData) {
         		this.companyCode = responseData.data;
         },
         getPermitJurisdictionInfo: function(_self){
           ajaxMethod(_self,"post",
               "jurisdiction/queryDataJurisdictionApi",false,
               JSON.stringify(""),"json",
               'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
         },
         getPermitJurisdictionSuccess: function(_self,response){
           _self.nameList = response.data.nameList;
         }
        },
        created: function() {
          this.getSystemListInfoMethod(this,{});
          this.getCompanyCode(this);
          this.getPermitJurisdictionInfo(this);
        },
        mounted: function() {
          var tr=document.getElementsByTagName('tr');
          var inputs=document.getElementsByClassName('checkName');
          data1.tr=tr;
          data1.inputs=inputs;
          this.jinyong()
          //表格排序需要获取的元素
          var rowOneSysInfo=document.getElementsByClassName('rowOneSysInfo')[0];
          var imgList=rowOneSysInfo.getElementsByTagName('img');
          data1.imgList=imgList;
        	this.listsortInfo();

        }
      })
    })
  })

}())