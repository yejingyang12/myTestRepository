(function () {
  var data = {
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
    value: ''
  };
  Vue.component('home',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/home/home.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
          // 获取系统名称
          this.getSystemName(this);
          // 获取单位名称
          this.getCompanyName(this);
          
           var url="main/queryMainList";
           var _self=this;
           //	 列表请求数据
           ajaxMethod(_self, "post", url, false ,"{}", "json", 'application/json;charset=UTF-8', _self.listSuccess);
        },
        methods: {
        	 submitForm:function(formName) {
			      this.$refs[formName].validate(function(valid){
			          if (valid) {
			            /*alert('submit!');*/
			          } else {
			          /* console.log('error submit!!');*/
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
        	transfer:function(value){
        		$("#plateType").val(value);
        	},
        	transferLevel:function(value){
        		var systemLevel = $("input[type='checkbox'][name='systemLevel']").is(':checked');
        		if(systemLevel == true){
        			this.systemLevel = value +",";
        		}
        	},
        	transferProvince:function(value){
        		$("#systemCodeProvince").val(value);
        	},
        	//清空
        	empty:function(){

        		this.value2 = '';
        		this.value1 = '';
        		$("#customFiltering").val("");
        		$("#acceptancecompanyName").val("");
        		$("#evaluationCompanyName").val("");
        		this.value3 = '';
        		this.value4 = '';
        		this.value17 = '';
        		this.value18 = '';
        		this.value7 = '';
        		this.value13 = '';
        		this.value15 = '';
        		this.value16 = '';
        		this.value5 = '';
        		this.value6 = '';
        		this.value8 = '';
        		this.value9 = '';
        		this.value12 = '';
        		this.value10 = '';
        		this.systemLevel = [];
        		this.systemProvince = [];
        		$(".checkName1").attr("checked",false);
        		$("#plateType").val("");
        		$(".checkName2").attr("checked",false);
        		$("#systemCodeLevel").val("");
        		$(".checkName3").attr("checked",false);
        		$("#systemCodeProvince").val("");
        		$(".checkName4").attr("checked",false);  		
        	},
          //ajax请求成功的方法
          listSuccess: function (_self, dataList) {
            data.result = dataList;
            
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
            var selectSystemName = $("#selectSystemName").val();
            var applyTime = $("#applyTime").val();
            var applytimeend =$("#applytimeend").val();
            var recordDateBegin = $("#recordDateBegin").val();
            var recordDateEnd = $("#recordDateEnd").val();
            var examTimeBegin = $("#examTimeBegin").val();
            var examTimeEnd = $("#examTimeEnd").val();
            var rankTimeBegin = $("#rankTimeBegin").val();
            var rankTimeEnd = $("#rankTimeEnd").val();
            var inspectionDateBegin = $("#inspectionDateBegin").val();
            var inspectionDateEnd = $("#inspectionDateEnd").val();
            var plateType = $("#plateType").val();
            var status ;
            $('input:radio[class="checkName2"]:checked').each(function () {
            	status = $(this).val();
            });	
            var systemCodeLevel = this.systemLevel;
            var systemCodeProvince = this.systemProvince;
            var customFiltering = $("#customFiltering").val();
            var url = "main/queryMainList";
            var _self=this;
            var dataparmars = {
              "systemName": selectSystemName,//系统名称
              "fkCompanyCode": this.value9,//所属单位
              "auditTimeBegin": applyTime,//申请开始时间
              "auditTimeEnd": applytimeend,//申请结束时间
              "acceptCompany": this.value12,//受理备案单位 
              "examOrg": this.value10,//测评单位
              "recordDateBegin":recordDateBegin,//备案开始时间
              "recordDateEnd": recordDateEnd,//备案结束时间
              "examTimeBegin": examTimeBegin,//测评开始时间
              "examTimeEnd": examTimeEnd,//测评结束时间
              "rankTimeBegin": rankTimeBegin,//定级开始时间
              "rankTimeEnd": rankTimeEnd,//定级结束时间
              "inspectionDateBegin": inspectionDateBegin,//自查开始时间
              "inspectionDateEnd": inspectionDateEnd,//自查结束时间
              "plateType": plateType,//所属板块
              "status": status,//状态
              "sprankLevelArray": systemCodeLevel,//等保级别
              "subordinateProvincesArray": systemCodeProvince,//地区
              "customFiltering": customFiltering,//自定义
              "currentPage": page,
            };
            ajaxMethod(_self, "post", url, false, JSON.stringify(dataparmars), "json", 'application/json;charset=UTF-8', _self.listSuccess);
          },
          
          //点击“删除”显示弹窗
          deleteClick:function(){
         	 $(".inquiry").css("display","block");
         	$(".dialogShaw").css("display","block");
          },
          //点击删除显示弹窗的“确定”；删除数据并隐藏弹窗；
          deleteClickSure:function(systemId,companyId){
        	  var _self=this;
              var dataparmars = {
                  "systemId":systemId,
                  "companyId":companyId
                };
              ajaxMethod(_self, 'post',
                      'main/deleteMainBySystemId', false,
                      JSON.stringify(dataparmars), 'json',
                      'application/json;charset=UTF-8',
                      _self.deleteClickSureMethod); 
        	 },
            deleteClickSureMethod:function(_self, responseData) {
            	 $(".inquiry").css("display","none");
            	if(!data.deleteSuccessDialog){
            		 console.log(11234578)
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
          // 获取系统名称下拉列表
          getSystemName : function(_self) {
            ajaxMethod(_self, 'post',
                'main/querySystemName', true,
                '{}', 'json',
                'application/json;charset=UTF-8',
                _self.getSystemNameSuccess);
          },
          // 获取系统名称下拉列表成功
          getSystemNameSuccess : function(_self, systemList) {
          		_self.system = systemList.data;
          },
          // 获取单位名称下拉列表
          getCompanyName : function(_self) {
            ajaxMethod(_self, 'post',
                'company/queryCompanyName', true,
                '{}', 'json',
                'application/json;charset=UTF-8',
                _self.getCompanyNameSuccess);
          },
          // 获取单位名称下拉列表成功
          getCompanyNameSuccess : function(_self, companyList) {
          		_self.company = companyList.data;
          },
          // 获取受理备案单位下拉列表
          getRecordCompany : function(_self) {
            ajaxMethod(_self, 'post',
                'records/queryRecordCompany', true,
                '{}', 'json',
                'application/json;charset=UTF-8',
                _self.getRecordCompanySuccess);
          },
          //获取受理备案单位下拉列表成功
          getRecordCompanySuccess : function(_self, recordCompanyList) {
          		_self.recordCompany = recordCompanyList.data;
          },
          // 获取测评单位下拉列表
          getExamOrgCompany : function(_self) {
            ajaxMethod(_self, 'post',
                'evaluation/queryExamOrgCompany', true,
                '{}', 'json',
                'application/json;charset=UTF-8',
                _self.getExamOrgCompanySuccess);
          },
          //获取测评单位下拉列表成功
          getExamOrgCompanySuccess : function(_self, examOrgCompanyList) {
          		_self.examOrgCompany = examOrgCompanyList.data;
          },
          // 获取板块类型
          getPlateTypeMethod : function(_self) {
            ajaxMethod(_self, 'post',
                'systemCode/querySystemCodeForKeySystemCode', true,
                '{"codeType":"6"}', 'json',
                'application/json;charset=UTF-8',
                _self.getPlateTypeSuccessMethod);
          },
          // 获取板块类型成功
          getPlateTypeSuccessMethod : function(_self, responseData) {
            _self.msgPlate = responseData.data;
          },
          // 获取等保级别
          getProtectionLevelMethod : function(_self) {
            ajaxMethod(_self, 'post',
                'systemCode/querySystemCodeForKeySystemCode', true,
                '{"codeType":"11","systemFatherCode":"3"}', 'json',
                'application/json;charset=UTF-8',
                _self.getProtectionLevelSuccessMethod);
          },
          // 获取等保级别成功
          getProtectionLevelSuccessMethod : function(_self, responseData) {
            _self.msgLevel = responseData.data;
          },
          // 获取省份
          getProvinceMethod : function(_self) {
            ajaxMethod(_self, 'post',
                'systemCode/querySystemCodeForKeySystemCode', true,
                '{"codeType":"21"}', 'json',
                'application/json;charset=UTF-8',
                _self.getProvinceSuccessMethod);
          },
          // 获取省份成功
          getProvinceSuccessMethod : function(_self, responseData) {
              _self.msgProvince = responseData.data;
          },
          //跳转到定级页面
          toAuditGradPage : function(systemId,companyId) {
          	window.location.href="/page/applicationGradingPage?systemId="+systemId+"&companyId="+companyId;
          },
          //申请变更弹窗
          toAuditChangePage : function(systemId,companyCode,companyId) {
          	$("#changeMattersSystemId").val(systemId);
          	$("#changeMattersCompanyCode").val(companyCode);
          	$("#changeMattersCompanyId").val(companyId);
          	this.getChangeMattersMethod(this,systemId);
          	this.showDialog();          	
/*          	window.location.href="/page/auditChangePage?systemId="+systemId;
*/          },
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
					  window.location.href="/page/applicationChangePage?systemId="+ responseData.data +"&fkCompanyCode="+companyCode+"&companyId="+companyId;
					},
          //跳转到备案
          toCompanyRecordPage : function(systemId,companyId) {
          	window.location.href="/page/companyRecordPage?systemId="+systemId+"&companyId=" + companyId;
          },
          //跳转到自查
          toSelfCheckPage : function(systemId,companyId) {
          	window.location.href="/page/selfCheckPage?systemId="+systemId+"&companyId=" + companyId;
          },
          //跳转到测评
          toTestingPage : function(systemId,companyId) {
          	window.location.href="/page/testingPage?systemId="+systemId+"&companyId=" + companyId;
          },
          //跳转到详情
          toDetailsPage : function(systemId,companyId){
          	window.location.href="/page/viewDetailsPage?systemId="+systemId+"&companyId="+companyId;
          },
          //审核管理
          toAuditPage : function() {
          	window.location.href="/page/auditPage";
          },
          //维护单位系统信息
          toMainCompanyInfoPage : function() {
          	window.location.href="/page/mainCompanyInfoPage";
          },
          //新建登保申请
          toAddCompanyInfoPagePage : function() {
          	window.location.href="/page/addCompanyInfoPage";
          },
          //申请变更弹窗和删除按钮弹出窗：隐藏弹窗；
          closes:function () {
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
            	this.search(this);
            }else if(a>data.resultPage.totalPages){
            	this.queryParam.currentPage = data.resultPage.totalPages;
            	this.search(this);
            }else{
            	this.queryParam.currentPage = a;
            	this.search(this);
            }
          },
 
          
          /*首页帮助中心点击帮助中心让其显示隐藏*/
          helpCenter: function () {
            var help = document.getElementsByClassName("float-ri")[1];
            if (help.getAttribute('openHelp')) {
              // console.log(1,help.getAttribute( 'openHelp'));
              $('#arrowHelp').css('transform', 'rotate(180deg)');
              help.setAttribute('openHelp', '')
            } else {
              console.log(2, help.getAttribute('openHelp'));
              console.log($('#arrowHelp').css('transform'));
              $('#arrowHelp').css('transform', 'rotate(360deg)');
              console.log($('#arrowHelp').css('transform'));
              help.setAttribute('openHelp', 'open')
            }
            var h = document.getElementById('h-bread');
//            $(h).toogle()
            h.style.display=h.style.display=="none"?"block":"none";
            $("#h-help-center").toggle();
            $("#h-help-bottom2").toggle();

          },
          /*首页鼠标滑入"帮助中心"*/
          mouseenter:function(isHuaru,num){
        	  var content = document.getElementsByClassName('content');
              var text = document.getElementsByClassName('text');
              var box3ItemContent = document.getElementsByClassName('box3-item-content');
              var box3Text = document.getElementsByClassName('box3-text');
              if (isHuaru) {//鼠标点向别处,恢复原来样式
            	 /* console.log("滑入")*/
            	  if (num == 0) {//box1
  	                 $('#bg-item1').css('background-size', '200% 200%');
  	                content[num].style.display = "none";
  	                text[num].style.display = "block";
  	              } else if (num == 1) {//box2
  	                $('#bg-item2').css('background-size', '200% 200%');
  	                content[num].style.display = "none";
  	                text[num].style.display = "block";
  	              } else if (num == 2) {//box4
  	                $('#bg-item4').css('background-size', '200% 200%');
  	                content[num].style.display = "none";
  	                text[num].style.display = "block";
  	              } else if (num = 4) {//box3
  	                box3ItemContent[0].style.display = 'none';
  	                box3ItemContent[1].style.display = 'none';
  	                box3ItemContent[2].style.display = 'none';
  	                box3ItemContent[3].style.display = 'none';
  	                $('#bg-item3').css('background-size', '200% 200%');
  	                box3Text[0].style.display = 'block';
  	                box3Text[1].style.display = 'block';
  	                box3Text[2].style.display = 'block';
  	                box3Text[3].style.display = 'block';
  	              }               
              }else{//鼠标点向别处,恢复原来样式
            	 /* console.log("滑出")*/
            	  if (num == 0) {//box1
                      //        bgItem=document.getElementById('bg-item1');
                      $('#bg-item1').css('background-color', '#ededed');
                      $('#bg-item1').css('background-size', '0% 0%');
                      content[num].style.display = "block";
                      text[num].style.display = "none";
                    } else if (num == 1) {//box2
                      $('#bg-item2').css('background-color', '#ededed');
                      $('#bg-item2').css('background-size', '0% 0%');
                      content[num].style.display = "block";
                      text[num].style.display = "none";
                    } else if (num == 2) {//box4
                      $('#bg-item4').css('background-color', '#ededed');
                      $('#bg-item4').css('background-size', '0% 0%');
                      content[num].style.display = "block";
                      text[num].style.display = "none";
                    } else if (num = 4) {//box3
                      box3ItemContent[0].style.display = 'block';
                      box3ItemContent[1].style.display = 'block';
                      box3ItemContent[2].style.display = 'block';
                      box3ItemContent[3].style.display = 'block';
                      $('#bg-item3').css('background-color', '#ededed');
                      $('#bg-item3').css('background-size', '0% 0%');
                      box3Text[0].style.display = 'none';
                      box3Text[1].style.display = 'none';
                      box3Text[2].style.display = 'none';
                      box3Text[3].style.display = 'none';
                    }
              }
          },
          /*首页点击"帮助中心"显示背景图片*/
          // var change=9,//默认变量，大于4 即可
          help: function (_this, num) {
        	  /*console.log("点击")*/
            var content = document.getElementsByClassName('content');
            var text = document.getElementsByClassName('text');
            var box3ItemContent = document.getElementsByClassName('box3-item-content');
            var box3Text = document.getElementsByClassName('box3-text');
            if (num == this.change) {//点击同一个
              if (num == 0) {//box1
                //        bgItem=document.getElementById('bg-item1');
                $('#bg-item1').css('background-color', '#ededed');
                $('#bg-item1').css('background-size', '0% 0%');
                content[num].style.display = "block";
                text[num].style.display = "none";
              } else if (num == 1) {//box2
                $('#bg-item2').css('background-color', '#ededed');
                $('#bg-item2').css('background-size', '0% 0%');
                content[num].style.display = "block";
                text[num].style.display = "none";
              } else if (num == 2) {//box4
                $('#bg-item4').css('background-color', '#ededed');
                $('#bg-item4').css('background-size', '0% 0%');
                content[num].style.display = "block";
                text[num].style.display = "none";
              } else if (num = 4) {//box3
                box3ItemContent[0].style.display = 'block';
                box3ItemContent[1].style.display = 'block';
                box3ItemContent[2].style.display = 'block';
                box3ItemContent[3].style.display = 'block';
                $('#bg-item3').css('background-color', '#ededed');
                $('#bg-item3').css('background-size', '0% 0%');
                box3Text[0].style.display = 'none';
                box3Text[1].style.display = 'none';
                box3Text[2].style.display = 'none';
                box3Text[3].style.display = 'none';
              }
              this.change = 9;
            } else {//点击的不是同一个
              if (this.change <= 4) {//说明已经点过了
	                if (this.change == 0) {//box1
	                  console.log("CHANGE" + this.change);
	                  //        bgItem=document.getElementById('bg-item1');
	                  $('#bg-item1').css('background-color', '#ededed');
	                  $('#bg-item1').css('background-size', '0% 0%');
	                  content[this.change].style.display = "block";
	                  text[this.change].style.display = "none";
	                } else if (this.change == 1) {//box2
	                  $('#bg-item2').css('background-color', '#ededed');
	                  $('#bg-item2').css('background-size', '0% 0%');
	                  content[this.change].style.display = "block";
	                  text[this.change].style.display = "none";
	                } else if (this.change == 2) {//box4
	                  $('#bg-item4').css('background-color', '#ededed');
	                  $('#bg-item4').css('background-size', '0% 0%');
	                  content[this.change].style.display = "block";
	                  text[this.change].style.display = "none";
	                } else if (this.change == 4) {//box3
	                  box3ItemContent[0].style.display = 'block';
	                  box3ItemContent[1].style.display = 'block';
	                  box3ItemContent[2].style.display = 'block';
	                  box3ItemContent[3].style.display = 'block';
	                  $('#bg-item3').css('background-color', '#ededed');
	                  $('#bg-item3').css('background-size', '0% 0%');
	                  box3Text[0].style.display = 'none';
	                  box3Text[1].style.display = 'none';
	                  box3Text[2].style.display = 'none';
	                  box3Text[3].style.display = 'none';
	                }
	              } else {
	
	              }
	              if (num == 0) {//box1
	                 $('#bg-item1').css('background-size', '200% 200%');
	                content[num].style.display = "none";
	                text[num].style.display = "block";
	              } else if (num == 1) {//box2
	                $('#bg-item2').css('background-size', '200% 200%');
	                content[num].style.display = "none";
	                text[num].style.display = "block";
	              } else if (num == 2) {//box4
	                $('#bg-item4').css('background-size', '200% 200%');
	                content[num].style.display = "none";
	                text[num].style.display = "block";
	              } else if (num = 4) {//box3
	                box3ItemContent[0].style.display = 'none';
	                box3ItemContent[1].style.display = 'none';
	                box3ItemContent[2].style.display = 'none';
	                box3ItemContent[3].style.display = 'none';
	                $('#bg-item3').css('background-size', '200% 200%');
	                box3Text[0].style.display = 'block';
	                box3Text[1].style.display = 'block';
	                box3Text[2].style.display = 'block';
	                box3Text[3].style.display = 'block';
	              }
	              this.change = num;
            }
          },
          /*点击首页"高级查询"让其显示和隐藏*/
          searchclick: function () {
            $("#h-height-search").toggle();
            // 获取受理备案单位
            this.getRecordCompany(this);
            // 获取测评单位
            this.getExamOrgCompany(this);
            //板块类型
            this.getPlateTypeMethod(this);
            //等保级别
            this.getProtectionLevelMethod(this);
            //地区
            this.getProvinceMethod(this);
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
          	window.location.href=originUrl+"/fileHandle/downloadFile?fileId="+fileId;
          },
          //定级信息导入
          getFile: function (obj) {
            console.log(1);
            var btn = $(obj);
            var btnValue = btn.val();
            var arr = [];
            var str = btnValue.split("\\");
            arr.push(str[str.length - 1]);
            // 返回 KB，保留小数点后两位
            var file = obj.value;
            if (!/.(word|pdf|exl|zip|rar|sep)$/.test(file)) {
              console.log(2);
              obj.value = '';
              alert("文件类型必须是.word、pdf、exl、zip、rar、sep中的一种");
              return false;
            } else {
              console.log(3);
              //返回Byte(B),保留小数点后两位
              if (((obj.files[0].size).toFixed(2)) >= (30 * 1024 * 1024)) {

                alert("请上传小于30M的文件");
                return false;
              }
            }
          },
          //一键导出
          oneKeyExport : function (){
          	var _self = this;
          	ajaxMethod(_self, 'post',
                'main/exportExcelForMain', true,
                "{}", 'json',
                'application/json;charset=UTF-8',
                _self.downloadSuccess);
          },
          //下载成功回调
          downloadSuccess: function (_self,responseData) {
          	var url = responseData.data;
          	var name = url.substring(url.lastIndexOf("/")+1,url.length);
         	 	window.location.href=originUrl+"fileHandle/downloadFile?uploadUrl="+name+"&attachName="+name;
          },
          changeColor: function () {
            var _this = data.h_score_list3;
            console.log(1, _this);
            _this.style.background = '#1489e6';//定级模版导出按钮变蓝色
            _this.style.color = '#fff';//定级模版导出按钮字体变白色
            _this.style.border = 'none';//定级模版导出按钮字体变白色
            $(_this).prev().css('background', '#ededed');
            $(_this).prev().css('color', 'black');
          },
          /*首页"高级查询"下的复选框--所属板块：*/
          checkAllHeightSearch: function () {
            for (var i = 0; i < data.one1.length; i++) {
              data.one1[i].index = i;
              data.arr1.splice(data.one1[i].index, 1, data.all1.checked);
              data.one1[i].checked = data.all1.checked;
            }
          },
          /*状态复选框*/
          checkAllHeightSearch2: function () {
            for (var i = 0; i < data.one2.length; i++) {
              data.one2[i].index = i;
              data.arr2.splice(data.one2[i].index, 1, data.all2.checked);
              data.one2[i].checked = data.all2.checked;
            }
          },

          /*等保复选框*/
          checkAllHeightSearch3: function () {
            for (var i = 0; i < data.one3.length; i++) {
              data.one3[i].index = i;
              data.arr3.splice(data.one3[i].index, 1, data.all3.checked);
              data.one3[i].checked = data.all3.checked;
            }

          },
          /*地区：复选框*/
          checkAllHeightSearch4: function () {
            for (var i = 0; i < data.one4.length; i++) {
              data.one4[i].index = i;
              data.arr4.splice(data.one4[i].index, 1, data.all4.checked);
              data.one4[i].checked = data.all4.checked;
            }
          },
          //初始化各个子选框
          checkitem: function (all) {
            // var one1= document.getElementsByClassName('checkName1');//获取到复选框的名称;获得的是数组
            //反选类型第一个所属模板
            for (var k = 0; k < data.one1.length; k++) {
              data.arr1.push(false);//默认有多少列，就添加多少个false
              data.one1[k].a = k;
              data.one1[k].onclick = function () {
                data.arr1.splice(this.a, 1, this.checked);
                data.all1.checked = data.arr1.every(function (item) {
                  return item
                })
              };
            }
//            //反选第二个--状态
//            for (var k = 0; k < data.one2.length; k++) {
//              data.arr2.push(false);//默认有多少列，就添加多少个false
//              data.one2[k].a = k;
//              data.one2[k].onclick = function () {
//                data.arr2.splice(this.a, 1, this.checked);
//                data.all2.checked = data.arr2.every(function (item) {
//                  return item
//                })
//              };
//            }
            //反选第三个--等保级别
            for (var j = 0; j < data.one3.length; j++) {
              data.arr3.push(false);//默认有多少列，就添加多少个false
              data.one3[j].a = j;
              data.one3[j].onclick = function () {
                data.arr3.splice(this.a, 1, this.checked);
                data.all3.checked = data.arr3.every(function (item) {
                  return item
                })
              };
            }
            //反选第四个--地区
            for (var j = 0; j < data.one4.length; j++) {
              data.arr4.push(false);//默认有多少列，就添加多少个false
              data.one4[j].a = j;
              data.one4[j].onclick = function () {
                data.arr4.splice(this.a, 1, this.checked);
                data.all4.checked = data.arr4.every(function (item) {
                  return item
                })
              };
            }

            //定级模版导出让checkbox显示
            for (let i = 0; i < data.firstChecked.length; i++) {
              let cur = data.firstChecked[i];
              var checkbox = cur.getElementsByClassName("checkName")[0];
              if (i == 0) {//表示第一列，全选按钮
                checkbox.onclick = function () {
                  if (this.checked) {//第一次选中
                    console.log(1);
                    for (let j = 1; j < data.firstChecked.length; j++) {//从第二行开始
                      var child_check = data.firstChecked[j].getElementsByClassName("checkName")[0];
                      child_check.checked = this.checked;
                      data.checked_system.push(data.tr_row[j].getAttribute("data-td"));
                      child_check.index = j - 1;
                      data.check_status.splice(child_check.index, 1, this.checked);
                    }
                  } else {//清除选中
                    for (let j = 1; j < data.firstChecked.length; j++) {//从第二行开始
                      var child_check = data.firstChecked[j].getElementsByClassName("checkName")[0];
                      child_check.checked = this.checked;
                      child_check.index = j - 1;
                      data.check_status.splice(child_check.index, 1, this.checked);
                    }
                    data.checked_system = [];
                  }
                }
              } else {//下面的单选按钮
                data.check_status.push(false);
                checkbox.index = i - 1;
                checkbox.onclick = function () {
                  console.log(data.checked_system);
                  if (this.checked) {//当前checkbox没选中
                    data.checked_system.push(this.parentNode.parentNode.getAttribute("data-td"))
                  } else {
                    data.checked_system.splice(this.parentNode.parentNode.getAttribute("data-td"), 1);
                  }
                  console.log(data.checked_system);

                  data.check_status.splice(this.index, 1, this.checked);
                  console.log(data.check_status);
                  document.getElementsByClassName("frist")[0].checked = data.check_status.every(function (item) {
                    return item
                  })
                }
              }
            }
          },

          //定级模版导出按钮
          checkallexport: function () {
         	 	window.location.href=originUrl+"fileHandle/downloadFile?uploadUrl=/excel/定级模板.xlsm&attachName=定级模板.xlsm";
          },
          //首页列表排序
          listsort: function () {
            var imgArrow = data.imgList;
            var flagOne = 1;
            // console.log(data.result.data);
            for (var i = 0; i < imgArrow.length; i++) {
              imgArrow[i].myindex = i;
              imgArrow[i].onclick = function () {
                flagOne *= -1;
                // //对每个数组也就是对应表格的每一列进行排序
                // console.log( data.result.data[0].systemName);
                switch (this.myindex){
                  case 0://系统名称排序
                    data.result.data.sort(function (a, b) {
                      return (a.systemName.localeCompare(b.systemName)) * flagOne
                    });
                    console.log( data.result.data[0].systemName);
                    break;
                  case 1://所属单位
                    data.result.data.sort(function (a, b) {
                      return (a.companyName.localeCompare(b.companyName)) * flagOne
                    });
                    break;
                  case 2://板块
                    data.result.data.sort(function (a, b) {
                      return (a.plateType.localeCompare(b.plateType)) * flagOne
                    });
                    break;
                  case 3://建设类型
                    data.result.data.sort(function (a, b) {
                      return (a.infoSysTypeConstruction.localeCompare(b.infoSysTypeConstruction)) * flagOne
                    });
                    break;
                  case 4://等保级别
                    data.result.data.sort(function (a, b) {
                      return (a.fkSpRanklevel - b.fkSpRanklevel) * flagOne
                    });
                    break;
                  case 5://是否未互联网应用
                    data.result.data.sort(function (a, b) {
                      return (a.appIsInternet - b.appIsInternet) * flagOne
                    });
                    break;

                }
                console.log( data.result.data[0].systemName);
              };

            }
          },
          //列表表格里的下载箭头 点击下载对应文件
          imgArrowDownload: function () {
            // console.log(1);
            var that = this;
            for (var i = 0; i < data.imgDownload.length; i++) {
              data.imgDownload[i].index = i;
              data.imgDownload[index].onclick = function () {
                that.downloadFile('');
              }
            }
          },

      },
        mounted: function() {
          var all=document.getElementById('all-bankuai-type');//获取到点击全选的那个复选框的id
          var all2=document.getElementById('all-status-type');
          var all3=document.getElementById('all-level-type');
          var all4=document.getElementById('all-local-type');
          var h_score_list2=document.getElementById('h-score-list2');
          var h_score_list3=document.getElementById('h-score-list3');
          var one1= document.getElementsByClassName('checkName1');//获取到复选框的名称;获得的是数组
          var one2= document.getElementsByClassName('checkName2');
          var one3= document.getElementsByClassName('checkName3');
          var one4= document.getElementsByClassName('checkName4');
          var list= document.getElementsByClassName('list');
          //表格排序需要获取的元素
          var rowOne=document.getElementsByClassName('rowOne')[0];
          var imgList=rowOne.getElementsByTagName('img');
          //表格下载箭头对应的img元素
          var imgDownload=document.getElementsByClassName('download');
          //定级模版导出
          data.firstChecked=document.getElementsByClassName('firstChecked');
          data.tr_row=document.getElementsByClassName('tr-row');

          data.all1=all;
          data.one1=one1;
          data.all2=all2;
          data.one2=one2;
          data.all3=all3;
          data.one3=one3;
          data.all4=all4;
          data.one4=one4;
          data.h_score_list2=h_score_list2;
          data.h_score_list3=h_score_list3;
          data.list=list;
          data.rowOne=rowOne;
          data.imgList=imgList;
          data.imgDownload=imgDownload;
          // this.selectChange()
          //初始化操作
          this.checkitem();
          this.listsort();
          this.imgArrowDownload();
        },
       /* destroyed () {
        	  data.timer=null;
        	}*/
      })
    })
  })
}());