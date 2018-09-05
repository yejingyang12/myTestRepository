(function () {
  var data = {
  		title: "",
      paramGrading:false,
      paramEvaluation:false,
      paramDelete:false,
      paramRecord:false,
      paramSelfExamination:false,
      paramApplication:false,
      delSystemId:"",
      showItem:5,
      
		  ruleForm: {
	          name: '',
	          region:'',
	          date1: '',
	          date2: '',
	          delivery: false,
	          type: [],
	          resource: '',
	          desc: '',
	          desc1: '',
	        },
	        rules: { 
		          region: [
		            { required: true, message: '请选择变更事项', trigger: 'change' }
		          ],
		        
		          desc: [
		            { required: true, message: '请填写变更原因', trigger: 'blur' }
		          ],
		          desc1: [
				     { required: true, message: '请填写变更内容', trigger: 'blur' }
				  ]
		        },
		//查询参数
		queryParam: {
			fkSystemId: null,
			pageSize: 10,
			currentPage: 1
		},
		resultPage:{
			"code": "0",
			"msg": "成功",
			"pagesize": 1,
			"currentPage": 1,
			"total": 5,
			"totalPages": 5,
			"data": [],
		},
    change:9,//默认变量，大于4 即可
    arr1:[],//所属板块,定义一个数组存储每个checkbox选中状态；默认都不选中
    arr2:[],//状态：
    arr3:[],//等保级别：
    arr4:[],//地区：
    // checked:false,
    all1:null,//全选
    all2:null,
    all3:null,
    all4:null,
    one1:null,
    one2:null,
    one3:null,
    one4:null,
    list:null,
    //删除弹窗的
    deleteDialog:false,
	deleteSuccessDialog:false,
	deleteFailDialog:false, 
    //定级导出的
    firstcheck :null,
    tr_row:null,
    checked_system:[],
    check_status:[],
    h_score_list2:null,//定级模版导出按钮的id
    h_score_list3:null,//定级信息导入按钮的id
    rowOne:null,//列表表头第一行的tr
    imgList:null,//列表表头第一行的排序箭头
    imgDownload:null,//列表表格里的下载箭头
    system: [] ,//系统名称
    company: [] ,//所属单位
    recordCompany: [],//受理备案单位
    examOrgCompany: [],//测评单位
    msgPlate:[], //版块类型
    msgLevel:[],//等保级别
    msgProvince :[],//地区
    changeMatters:[{}],//变更事项
    value3: '',
    value4: '',
    value5: '',
    value6: '',
    value7: '',
    value8: '',
    value9: '',
    value10: '',
    value11: '',
    value12: '',
    value13: '',
    value14: '',
    value15: '',
    value16: '',
    value17: '',
    value18: '',
    value19: '',
    value20: '',
    systemLevel:[],
    systemProvince:[],
    changeMattersSystemId :'',
    
    pickerOptions1: {
      disabledDate :function(time) {
        return time.getTime() > Date.now();
      },
      shortcuts: [{
        text: '今天',
        onClick:function(picker) {
          picker.$emit('pick', new Date());
        }
      }, {
        text: '昨天',
        onClick:function(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24);
          picker.$emit('pick', date);
        }
      }, {
        text: '一周前',
        onClick:function(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
          picker.$emit('pick', date);
        }
      }]
    },
    value1: '',
    value2: '',
    xiala:null,
    result:{},
    value: '',
    maintain:false,
    auditManagement:false,
    oneKeyExportJurisdiction:false,
    templateImport:false,
    templateExport:false,
    newlyBuild:false,
    headquarters:false,
    enterprise:false,
    nameList:[]
//    sprankLevel:"",
//    gradingBeginTimeStamp : "",
//    gradingEndTimeStamp : "",
//    systemType : "",
//    gradingShapeType : ""
  };
  Vue.component('home',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/showChartDataList/showChartDataList.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
        	this.getTitle();
        	this.search(1);
        },
        computed:{
        	totalPages:function(){
        		var page = []; //显示页码
                //用当前激活页面驱动页面显示的分别
                if (this.result.currentPage < this.showItem) { //当前页小于最大页码数（showItem），区分总页数是否达到最大页码数
                    //获取总页数和最大页码数较小的值
                    var i = Math.min(this.result.totalPages, this.showItem);
                    while (i) {
                        //通过page的数组值显示页码
                        page.unshift(i--);
                    }
                } else { //当前页面大于最大页码数（showItem）时，区分显示的页码规则
                    var pagestart = this.result.currentPage - Math.floor(this.showItem / 2); //获取显示的页码第一位页码（默认当前页居中）
                    var i = this.showItem; //用来显示多少（i）个页码
                    if (pagestart > (this.result.totalPages - this.showItem)) { //第一个页码如果大于总页数减去展示的页码数，则当前页不能居中
                        pagestart = (this.result.totalPages - this.showItem) + 1; //应该显示的第一个页码数
                    }
                    while (i--) {
                        //通过page的数组值显示页码
                        page.push(pagestart++);
                    }
                }
                return page;
            }
        	
        },
        methods: {
        	getTitle: function(){ 
          	if(titleType == 1 || titleType == "1"){
          		this.title = "系统等保等级分布";
          	}else if(titleType == 2 || titleType == "2"){
          		this.title = "不同等保级别系统在不同管理状态下详情";
          	}
          	
          	if(sprankLevel == "301"){
          		this.title = this.title + " - 一级";
          	}else if(sprankLevel == "302"){
          		this.title = this.title + " - 二级";
          	}else if(sprankLevel == "303"){
          		this.title = this.title + " - 三级";
          	}else if(sprankLevel == "304"){
          		this.title = this.title + " - 四级";
          	}else if(sprankLevel == "305"){
          		this.title = this.title + " - 五级";
          	}
        	},
        	 submitForm:function(formName) {
			      this.$refs[formName].validate(function(valid){
			          if (valid) {
			          	
			          } else {
			            return false;
			          }
			        });
			      this.saveChangeMattersMethod(); 
			      },
			     
        	text:function(){
             $('#textArea').on("keyup",function(){
                 $('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
                 if($('#textArea').val().length > 200){
                     $('#textNum').text(200);//长度大于200时0处显示的也只是200
                     $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
                 }
             })
             $('.textArea1').on("keyup",function(){
                 $('#textNum1').text($('.textArea1').val().length);//这句是在键盘按下时，实时的显示字数
                 if($('.textArea1').val().length > 200){
                     $('#textNum1').text(200);//长度大于200时0处显示的也只是200
                     $('.textArea1').val($('.textArea1').val().substring(0,200));//长度大于100时截取钱100个字符
                 }
             })
          },
          //ajax请求成功的方法
          listSuccess: function (_self, dataList) {
            data.result = dataList.data.list;
          	data.result.totalPages = dataList.data.totalPage;
          	data.result.pagesize = dataList.data.pageSize;
          	data.result.currentPage = dataList.data.currPage;
          	data.result.total = dataList.data.totalCount;
          },
          //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
            
            } else if (page > data.result.totalPages) {
             
            } else {
              this.search(page);
            }
          },

          //查询 点击发送请求
          search:function(page){
          	var headquarters;
          	var enterprise;
          	//功能权限
            $.ajax({
              type: "get",
              url : originUrl+"/jurisdiction/queryMenuJurisdictionApi", 
              async: false,
              data: "",
              dataType: "json",
              cache: false,
              processData: false,
              contentType: false,
              success: function(response) {
              	headquarters = getJurisdictionMethod(response,'0102010101');
              	enterprise = getJurisdictionMethod(response,'0102010108');
              },
              error: function(err) {
              }
            });
            //var sprankLevelArray = [sprankLevel];
            var jurBind;
            if(headquarters){
            	jurBind='总部';
            }else{
            	jurBind='企业';
            }
          	var url = "main/queryMainList";
            var _self=this;
            var dataparmars = {
              //"sprankLevelArray": sprankLevelArray,//等级
            	"sprankLevel": sprankLevel,
              "gradingBeginTimeStamp": gradingBeginTimeStamp,//定级开始时间
              "gradingEndTimeStamp": gradingEndTimeStamp,//定级结束时间
              "systemType": systemType,//系统类型
              "gradingShapeType": gradingShapeType,//查询类型
              "currentPage": page,
              //连动查询条件
              "systemName": systemName,
              "appIsInternet": appIsInternet,
              "companyName": companyName,
              "auditTimeBeginTimeStamp": auditTimeBeginTimeStamp,
              "auditTimeEndTimeStamp": auditTimeEndTimeStamp,
              "acceptCompany": acceptCompany,
              "examOrg": examOrg,
              "recordDateBeginTimeStamp": recordDateBeginTimeStamp,
              "recordDateEndTimeStamp": recordDateEndTimeStamp,
              "examTimeBeginTimeStamp": examTimeBeginTimeStamp,
              "examTimeEndTimeStamp": examTimeEndTimeStamp,
              "rankTimeBeginTimeStamp": rankTimeBeginTimeStamp,
              "rankTimeEndTimeStamp": rankTimeEndTimeStamp,
              "inspectionDateBeginTimeStamp": inspectionDateBeginTimeStamp,
              "inspectionDateEndTimeStamp": inspectionDateEndTimeStamp,
              "plTypeArray": plTypeArray,
              "statusArray": statusArray,
              "sprankLevelArray": sprankLevelArray,
              "subordinateProvincesArray": subordinateProvincesArray,
              "customFiltering": customFiltering,
              "jurBind": jurBind,
            };
            ajaxMethod(_self, "post", url, false, JSON.stringify(dataparmars), "json", 'application/json;charset=UTF-8', _self.listSuccess);
          },
          
          //点击“删除”显示弹窗
         deleteClick:function(systemId){
          	this.delSystemId=systemId;
         	 $(".inquiry").css("display","block");
         	 $(".dialogShaw").css("display","block");
          },
          //点击删除显示弹窗的“确定”；删除数据并隐藏弹窗；
          deleteClickSure:function(){
        	  var _self=this;
              var dataparmars = {
                  "systemId":this.delSystemId
                };
              ajaxMethod(_self, 'post',
                      'main/deleteMainBySystemId', false,
                      JSON.stringify(dataparmars), 'json',
                      'application/json;charset=UTF-8',
                      _self.deleteClickSureMethod); 
        	 },
            deleteClickSureMethod:function(_self, responseData) {

            	$("#startBoxDelete").show().delay(2000).fadeOut();
              window.setTimeout(function () {
              }, 2300);
            	 $(".inquiry").css("display","none");
             	 $(".dialogShaw").css("display","none");
            	if(!data.deleteSuccessDialog){
            		data.deleteDialog=true;
             		data.deleteSuccessDialog=true; 
            	 }
             	_self.createdIndex(_self);
             	data.deleteDialog=false;
        		data.deleteSuccessDialog=false;
         }, 
         createdIndex: function(_self) {
        	 var url="main/queryMainList";
             //	 列表请求数据
             ajaxMethod(_self, "post", url, false ,JSON.stringify(_self.queryParam), "json", 'application/json;charset=UTF-8', _self.listSuccess);
         },
          //跳转到定级页面
          toAuditGradPage : function(systemId,companyId) {
          	window.location.href=originUrl+encodeURI("/page/applicationGradingPage?systemId="+systemId+"&companyId="+companyId);
          },
          //申请变更弹窗
          toAuditChangePage : function(systemId,companyCode,companyId) {
          	$("#changeMattersSystemId").val(systemId);
          	$("#changeMattersCompanyCode").val(companyCode);
          	$("#changeMattersCompanyId").val(companyId);
          	this.getChangeMattersMethod(this,systemId);
          	this.showDialog();          	
/*          	window.location.href=originUrl+encodeURI("/page/auditChangePage?systemId="+systemId);
*/        },
					// 获取变更事项
					getChangeMattersMethod : function(_self,systemId) {
					  ajaxMethod(_self, 'post',
					      'systemCode/querySystemCodeForKeySystemCode', true,
					      '{"codeType":"37"}', 'json',
					      'application/json;charset=UTF-8',
					      _self.getChangeMattersSuccessMethod);
					},
					// 获取变更事项成功
					getChangeMattersSuccessMethod : function(_self, responseData) {
					  responseData.data.splice(3,1);
					  _self.changeMatters = responseData.data;
					},
					// 申请变更提交
					saveChangeMattersMethod : function() {
						var _self = this; 
						var changeReason = this.ruleForm.desc;
						var changeContent = this.ruleForm.desc1;
						var fkChangeMatter = this.ruleForm.region;
						var systemId = $("#changeMattersSystemId").val();
						var companyCode = $("#changeMattersCompanyCode").val();
					  ajaxMethod(_self, 'post',
					      'main/queryApplicationChange', true,
					      '{"systemId":"'+systemId+'","changeReason":"'+changeReason+'","changeContent":"'+changeContent+'","fkChangeMatter":"'+fkChangeMatter+'","companyCode":"'+companyCode+'"}', 'json',
					      'application/json;charset=UTF-8',
					      _self.saveChangeMattersSuccessMethod);
					},
					// 申请变更提交成功
					saveChangeMattersSuccessMethod : function(_self, responseData) {
						this.closes();
					  //跳转到申请变更页面
						var companyCode = $("#changeMattersCompanyCode").val();
						var companyId = $("#changeMattersCompanyId").val();
					  window.location.href=originUrl+encodeURI("/page/applicationChangePage?systemId="+ responseData.data +"&fkCompanyCode="+companyCode+"&companyId="+companyId);
					},
          //跳转到备案
          toCompanyRecordPage : function(systemId,companyId) {
          	window.location.href=originUrl+encodeURI("/page/companyRecordPage?systemId="+systemId+"&companyId=" + companyId);
          },
          //跳转到自查
          toSelfCheckPage : function(systemId,companyId) {
          	window.location.href=originUrl+encodeURI("/page/selfCheckPage?systemId="+systemId+"&companyId=" + companyId);
          },
          //跳转到测评
          toTestingPage : function(systemId,companyId) {
          	window.location.href=originUrl+encodeURI("/page/testingPage?systemId="+systemId+"&companyId=" + companyId);
          },
          //跳转到详情
          toDetailsPage : function(systemId,companyId){
          	window.location.href=originUrl+encodeURI("/page/viewDetailsPage?systemId="+systemId+"&companyId="+companyId);
          },
          //申请变更弹窗和删除按钮弹出窗：隐藏弹窗；
          closes:function () {
          	this.$refs['ruleForm'].resetFields();
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none";
            $(".inquiry").css("display","none");
            $(".dialogShaw").css("display","none");
          },
          //申请变更弹窗：显示弹窗
          showDialog:function(itemdata){
            $("#dialog").css("display","block");
          },
          //分页跳转
          hpageNum:function(_this){
            var a=$("#txt").val();
            if(a<=0){
              ("请输入正确页数");
            	this.queryParam.currentPage = 1;
            	this.search(a);
            }else if(a>data.resultPage.totalPages){
            	this.queryParam.currentPage = data.resultPage.totalPages;
            	this.search(a);
            }else{
            	this.queryParam.currentPage = a;
            	this.search(a);
            }
          },
          //一键下载
          oneKeydownload: function (systemId,companyId) {
          	 var url = "main/oneButtonDownloading";
          	 var _self=this;
          	 var responsedata;
          	 var dataparmars = '{"systemId":"'+systemId+'","companyId": "'+companyId+'"}';
          	 ajaxMethod(_self, "post", url, false, dataparmars, "JSON", 'application/json;',_self.downloadSuccess);
          },
          //表1
          downloadList1: function (companyId) {
          	var _self = this;
          	 ajaxMethod(_self, 'post',
                 'main/tableCompany', true,
                 '{"companyId":"'+companyId+'"}', 'json',
                 'application/json;charset=UTF-8',
                 _self.downloadSuccess);
          },
          //表2
          downloadList2: function (systemId) {
          	 var _self = this;
	         	 ajaxMethod(_self, 'post',
	                'main/tableSystem', true,
	                '{"systemId":"'+systemId+'"}', 'json',
	                'application/json;charset=UTF-8',
	                _self.downloadSuccess);
          },
          //表3
          downloadList3: function (systemId) {
          	var _self = this;
          	ajaxMethod(_self, 'post',
                'main/tableGrading', true,
                '{"systemId":"'+systemId+'"}', 'json',
                'application/json;charset=UTF-8',
                _self.downloadSuccess);
          },
          //表4
          downloadList4: function (systemId) {
          	var _self = this;
          	ajaxMethod(_self, 'post',
                'main/tableAttach', true,
                '{"systemId":"'+systemId+'"}', 'json',
                'application/json;charset=UTF-8',
                _self.downloadSuccess);
          },
          //附件下载
          downloadFile : function (fileId){
          	window.location.href=originUrl+encodeURI("/fileHandle/downloadFile?fileId="+fileId);
          },
          //下载成功回调
          downloadSuccess: function (_self,responseData) {
          	var url = responseData.data;
          	var name = url.substring(url.lastIndexOf("/")+1,url.length);
         	 	window.location.href=originUrl+encodeURI("/fileHandle/downloadFile?uploadUrl="+name+"&attachName="+name);
          },
          changeColor: function () {
            var _this = data.h_score_list3;
            _this.style.background = '#1489e6';//定级模版导出按钮变蓝色
            _this.style.color = '#fff';//定级模版导出按钮字体变白色
            _this.style.border = 'none';//定级模版导出按钮字体变白色
            $(_this).prev().css('background', '#ededed');
            $(_this).prev().css('color', 'black');
          },
          //首页列表排序
          listsort: function () {
            var imgArrow = data.imgList;
            var flagOne = 1;
            for (var i = 0; i < imgArrow.length; i++) {
              imgArrow[i].myindex = i;
              imgArrow[i].onclick = function () {
                flagOne *= -1;
                // //对每个数组也就是对应表格的每一列进行排序
                switch (this.myindex){
                  case 0://系统名称排序
                    data.result.sort(function (a, b) {
                      return (a.systemName.localeCompare(b.systemName)) * flagOne
                    });
                    break;
                  case 1://所属单位
                    data.result.sort(function (a, b) {
                      return (a.companyName.localeCompare(b.companyName)) * flagOne
                    });
                    break;
                  case 2://板块
                    data.result.sort(function (a, b) {
                      return (a.plateType.localeCompare(b.plateType)) * flagOne
                    });
                    break;
                  case 3://建设类型
                    data.result.sort(function (a, b) {
                      return (a.infoSysTypeConstruction.localeCompare(b.infoSysTypeConstruction)) * flagOne
                    });
                    break;
                  case 4://等保级别
                    data.result.sort(function (a, b) {
                      return (a.fkSpRanklevel - b.fkSpRanklevel) * flagOne
                    });
                    break;
                  case 5://是否未互联网应用
                    data.result.sort(function (a, b) {
                      return (a.appIsInternet - b.appIsInternet) * flagOne
                    });
                    break;

                }
              };

            }
          },
          //列表表格里的下载箭头 点击下载对应文件
          imgArrowDownload: function () {
            var that = this;
            for (var i = 0; i < data.imgDownload.length; i++) {
              data.imgDownload[i].index = i;
              data.imgDownload[index].onclick = function () {
                that.downloadFile('');
              }
            }
          },
          getPermitJurisdictionInfo: function(_self){
            ajaxMethod(_self,"post",
                "jurisdiction/queryDataJurisdictionApi",false,
                JSON.stringify(""),"json",
                'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
          },
          getPermitJurisdictionSuccess: function(_self,response){
            _self.paramGrading = false;
            _self.paramEvaluation = false;
            _self.paramDelete = false;
            _self.paramRecord = false;
            _self.paramSelfExamination = false;
            _self.paramApplication = false;
          	_self.nameList = response.data.nameList;
            for (var i = 0; i < response.data.permssions.length; i++) {
              var permssions = response.data.permssions[i];
              
              if(permssions==S_STR_PERMIT_PARAM_GRADING){
                _self.paramGrading = true;
              }
              if(permssions==S_STR_PERMIT_PARAM_EVALUATION){
                _self.paramEvaluation = true;
              }
              if(permssions==S_STR_PERMIT_PARAM_DELETE){
                _self.paramDelete = true;
              }
              if(permssions==S_STR_PERMIT_PARAM_RECORD){
                _self.paramRecord = true;
              }
              if(permssions==S_STR_PERMIT_PARAM_SELF_EXAMINATION){
                _self.paramSelfExamination = true;
              }
              if(permssions==S_STR_PERMIT_PARAM_APPLICATION_CHANGE){
                _self.paramApplication = true;
              }
            }
          }
      },
      mounted: function() {
          this.getPermitJurisdictionInfo(this);
          //点击返回按钮 返回到首页
          bus.$on("gradReturn",function(meg){
            if(meg!=null){
            	window.location.href = originUrl+encodeURI("/page/indexPage");
            }
          });
          var list= document.getElementsByClassName('list');
          //表格排序需要获取的元素
          var rowOne=document.getElementsByClassName('rowOne')[0];
          var imgList=rowOne.getElementsByTagName('img');
          //表格下载箭头对应的img元素
          var imgDownload=document.getElementsByClassName('download');
          //定级模版导出
          data.firstChecked=document.getElementsByClassName('firstChecked');
          data.tr_row=document.getElementsByClassName('tr-row');
          
          data.list=list;
          data.rowOne=rowOne;
          data.imgList=imgList;
          data.imgDownload=imgDownload;
          //初始化操作
          this.listsort();
          this.imgArrowDownload();
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
            	
            	data.headquarters = getJurisdictionMethod(response,'0102010101');
            	data.enterprise = getJurisdictionMethod(response,'0102010108');
            },
            error: function(err) {
            }
          });
        },
      })
    })
  })
}());