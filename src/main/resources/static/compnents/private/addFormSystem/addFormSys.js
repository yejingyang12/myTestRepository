/**
 * Created by timha on 2018/5/21.
 */
var  data={
		returnIndexFlag:true,
		indexFlag:"",
		 pickerOptions0: {
	          disabledDate:function(time) {
	            //return time.getTime() < Date.now() - 8.64e7;
	          }
	        },
	        returnIndex:false,
	        flag:false,
	        substitute:"",
	        ceshi2:false,
	        dialogVisibled:false,
	        allFatherSystemName:"",
		jurisdiction: false,
		nUsePro:[true,true,true,true,true,true],
		btnId:"",
    check:false,
    delIndex:0,
    paramNan:true,
		systemNameSon1:[true],
		systemNameSon12:[false],
		systemNameSon13:[true],
		addSystemSubSonT:[],
		promptCount:false,
		count:0,
		companyNameDisabled:true,
		executiveDisabled:true,
    deleteBtn:[true],
      formData:{
        systemId:"",
        companyId:"",
        fkInfoSysTypeCon:"",
        fkSystemIsMerge:"",
        systemName:"",
        fatherSystemName:"",
        standardizedCode:"",
        gradeRecordSysName:"",
        sysBusSituationType:"",
        sysBusDescription:"",
        sysServiceSitScope:"",
        sysServiceSitObject:"",
        npCoverageRange:"",
        npNetworkProperties:"",
        interconnectionSit:"",
        productsNumber:"",
        fkNationalIsProducts:"",
        fkResponsibleType:"",
        fkCompanyCode:"",
        executiveOfficeName:"",
        executiveDireCon:"",
        executiveDireConTel:"",
        whenInvestmentUse:"",
        subIsSystem:"",
        companyName:"",
        fatherCompanyName:"",
        fkComCode:"",
        changeType:"",
        stars:"1",
        aa:"1",
        checkCount:"1",
        systemNameSon:"",
        systemCodeSon:"",
        addSystemSubSon:[],
        addSystemSub:[
//        {
//          label:"子系统1系统名称：",
//          labelCode:"子系统1标准化代码：",
//          systemName:"",
//          standardizedCode:""
//        },
//        {
//          label:"子系统2系统名称：",
//          labelCode:"子系统2标准化代码：",
//          systemName:"",
//          standardizedCode:""
//        }
        ],
        systemKeyProducts:[{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        }],
        systemUseServices:[{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        }],
        systemKeyProductsNumber:'',
        systemKeyFkNationalIsProducts:'',
        systemKeyNUseProbability:'',
        systemKeyOtherName:'',
        systemProServices:''
      },
      systemInfo2:[false],
      addSub:true,
      sysName:[],//系统名称
      sysNameExecutive:[],//主管处室
      sysType:[],//业务类型
      sysServiceScope:[],//服务范围
      sysServiceObject:[],//服务对象
      sysCoverage:[],//覆盖范围
      sysNetwork:[],//网络性质
      sysIntercon:[],//系统互联情况
      msgName : [],//所属单位名称
      parentMsgName : [],//上级系统所属单位名称
      sysSubsystem:[],//系统是否为分系统
      sysIs:[],
      num : 2, //定义一个变量
      systemInfo:true,
      systemSonInfo:false,
      change:false,
      BeSonSystem:false,
      sysProductType:[],
      //获取数量
      sysNumber:[],
      //获取是否使用国产品
      sysNationalProducts:[],
      //获取服务类型
      sysServiceType:[],
      //获取服务责任方类型
      sysResponsible:[],
      headquarters:false,
      enterprise:false,
      rules:{
          fkInfoSysTypeCon:[  // 信息系统建设类型
              { required: true, message: '请选择系统建设类型', trigger: 'blur' },
          ],
          fkSystemIsMerge:[  // 是否为合并系统
              { required: true, message: '请选择是否为合并系统', trigger: 'blur' },
          ],
          systemName:[  // 系统名称
              { required: true, message: '请输入系统名称', trigger: 'change' },
              { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'change' },
          ],
          systemNameSon:[  // 子系统名称
              { required: true, message: '请输入子系统名称', trigger: 'change' },
              { min: 1, max: 60, message: '长度在 1 到 60个字符', trigger: 'change' },
          ],
          systemCodeSon:[  // 子系统Code
             { required: true, message: '请输入子系统标准化代码', trigger: 'change' }
          ],
          stars:[  // 子系统Code
             { required: true, message: '1', trigger: 'change' }
          ],
          aa:[  // 子系统Code
             { required: true, message: '该系统已选择,请勿重复选择', trigger: 'change' }
          ],
          checkCount:[  //总条数
             { required: true, message: '至少创建两条子系统', trigger: 'change' }
          ],
          standardizedCode:[  // 标准化代码
              { required: true, message: '请输入标准化代码', trigger: 'change' }
          ],
          gradeRecordSysName:[  // 等保备案系统名称
              { required: false, message: '请输入等保备案系统名称', trigger: 'blur' },
              { min: 0, max: 60, message: '长度在 0 到60个字符', trigger: 'blur' },
          ],
          appIsInternet:[  // 是否为互联网应用
              { required: true, message: '请选择是否为互联网应用', trigger: 'blur' },
          ],
          sysBusSituationType:[  // 业务类型
              { required: true, message: '请选择业务类型', trigger: 'blur' },
          ],
          sysBusDescription:[  // 业务描述
              { required: true, message: '请填写业务描述', trigger: 'blur' },
              { min: 0, max: 200, message: '长度在 0 到200个字符', trigger: 'blur' },
          ],
          sysServiceSitScope:[  // 服务范围
              { required: true, message: '请选择服务范围', trigger: 'blur' },
          ],
          sysServiceSitObject:[  // 服务对象
              { required: true, message: '请选择服务对象', trigger: 'blur' },
          ],
          npCoverageRange:[  // 覆盖范围
              { required: true, message: '请选择覆盖范围', trigger: 'blur' },
          ],
          npNetworkProperties:[  // 网络性质
              { required: true, message: '请选择网络性质', trigger: 'blur' },
          ],
          interconnectionSit:[  // 系统互联情况
              { required: true, message: '请选择系统互联情况', trigger: 'blur' },
          ],
          companyName:[  // 所属单位名称
              { required: true, message: '请选择所属单位名称', trigger: 'change' },
          ],
          whenInvestmentUse:[  // 何时投入使用
              { required: true, message: '请输入投入使用时间', trigger: 'blur' },
          ],
          executiveOfficeName:[  // 主管处室名称
              { required: false, message: '请输入主管处室名称', trigger: 'blur' },
              { min: 0, max: 60, message: '长度在 0 到 60个字符', trigger: 'blur' },
          ],
          subIsSystem:[  // 系统是否为分系统
              { required: true, message: '请选择系统是否为分系统', trigger: 'blur' },
          ],
          executiveDireCon:[  // 主管联系人
              { required: false, message: '请输入主管联系人', trigger: 'blur' },
              { min: 0, max: 40, message: '长度在 0 到 40个字符', trigger: 'blur' },
          ],
          executiveDireConTel:[  // 主管联系人电话
              { required: false, message: '请输入主管联系人电话', trigger: 'blur' },
              { pattern: /^([\d-+]*)$/, message: '负责人联系电话输入有误', trigger: 'blur'}
          ],
          systemKeyProducts:[  // 关键产品
              { required: true, message: '关键产品使用情况', trigger: 'blur' }
          ],
          systemKeyProductsNumber:[  // 关键产品
              { required: true, message: '请选择关键产品使用情况-数量', trigger: 'change' }
          ],
          systemKeyFkNationalIsProducts:[  // 关键产品
              { required: true, message: '请选择关键产品使用情况-是否使用国产品', trigger: 'change' }
          ],
          systemKeyNUseProbability:[  // 关键产品
              { required: true, message: '请选择关键产品使用情况-使用国产品率', trigger: 'change' },
          ],
          systemKeyOtherName:[  // 关键产品
              { required: true, message: '请输入关键产品使用情况-其它名称', trigger: 'blur' }
          ],
          systemUseServices:[  // 关键产品
              { required: false, message: '选择关键产品', trigger: 'blur' }
          ],
          systemProServices:[
              { required: false, message: '填写国产品使用率有误', trigger: 'blur' }
          ],
          fatherSystemName:[
              {required: false, message: '请选择父系统', trigger: 'change'}
          ],
          fatherCompanyName:[
              {required: false, message: '请选择父系统所属单位', trigger: 'change'}
          ]
      },
      beginContent:{
        systemId:"",
        companyId:"",
        fkInfoSysTypeCon:"",
        fkSystemIsMerge:"",
        systemName:"",
        fatherSystemName:"",
        standardizedCode:"",
        gradeRecordSysName:"",
        sysBusSituationType:"",
        sysBusDescription:"",
        sysServiceSitScope:"",
        sysServiceSitObject:"",
        npCoverageRange:"",
        npNetworkProperties:"",
        interconnectionSit:"",
        productsNumber:"",
        fkNationalIsProducts:"",
        fkResponsibleType:"",
        fkCompanyCode:"",
        executiveOfficeName:"",
        executiveDireCon:"",
        executiveDireConTel:"",
        whenInvestmentUse:"",
        subIsSystem:"",
        companyName:"",
        fatherCompanyName:"",
        fkComCode:"",
        changeType:"",
        stars:"1",
        aa:"1",
        checkCount:"1",
        systemNameSon:"",
        systemCodeSon:"",
        addSystemSubSon:[],
        addSystemSub:[
//        {
//          label:"子系统1系统名称：",
//          labelCode:"子系统1标准化代码：",
//          systemName:"",
//          standardizedCode:""
//        },
//        {
//          label:"子系统2系统名称：",
//          labelCode:"子系统2标准化代码：",
//          systemName:"",
//          standardizedCode:""
//        }
        ],
        systemKeyProducts:[{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        },{
          fkNationalIsProducts:"",
          fkExaminStatus:"",
          productsNumber:"",
          nUseProbability:0,
          otherName:"",
          allHave:"",
          number:0
        }],
        systemUseServices:[{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        },{
          fkResponsibleType:"",
          fkProductsType:"",
          fkSystemId:"",
          serviceIsUse:"",
          otherName:""
        }],
        systemKeyProductsNumber:'',
        systemKeyFkNationalIsProducts:'',
        systemKeyNUseProbability:'',
        systemKeyOtherName:'',
        systemProServices:''
      },
      loading:false
    };

(function () {

    Vue.component('addFormSystem',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormSystem/addFormSystem.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                watch:{

                },
                methods:{
                   sysNameBlur:function () {
                  	var sysName=$("#systemInfo1").val();
                  	if(sysName!='' || sysName!=""){
                  		this.$refs.systemName.clearValidate();
                  	}else{
                  		this.$refs.formData.validateField("systemName");
                  	}
                   },
                	 closes:function () { 
                         $(".shawLast2").css("display","none");
                         $(".shawLast").css("display","none");
                	 },
                	 deleteSys:function(type,delIndex){
                		 if(type == 1){
                			 $(".delLast2").css("display","block");
                       $(".shawLast2").css("display","block");
                		 }else{
                			 $(".delLast").css("display","block");
                       $(".shawLast").css("display","block");
                		 }
                		 if(delIndex != null){
                			 this.delIndex = delIndex;
                		 }
                	},
                	delSonSystemLast:function(){
                	  if(this.formData.fkSystemIsMerge!='1'||this.formData.fkSystemIsMerge==null||this.formData.fkSystemIsMerge==''){
                	    return;
                	  }
                		var sonLength = this.formData.addSystemSub.length -1;
                		if(this.formData.addSystemSub.length > 1){
                			$("#count_"+sonLength).remove();
                  		this.formData.addSystemSub.splice(sonLength,1);
                		}
                		if(sonLength <2){
                			this.formData.checkCount='';
                			this.promptCount=true;
                			this.$refs.formData.validateField('checkCount');
                		}
              			this.systemNameSon1[sonLength] = false;
              			this.systemNameSon12[sonLength] = false;
                  	this.formData.systemNameSon="1";
                  	this.formData.systemCodeSon="1";
                  	this.formData.stars="1";
                  	this.formData.aa = '1';
              			this.$refs.formData.validateField('aa');
                  	this.closes();
                	},
                	delCount: function (index){
                		return "count_" +index;
                	},
                	allSysName: function (index){
                		return "allSysCount_" +index;
                	},
                	allSysCode: function (index){
                		return "allSysCode_" +index;
                	},
                	delSonSystem:function(){
                		var index = this.delIndex;
                		this.systemInfo2[this.formData.addSystemSub.length-1] = false;
                		this.formData.addSystemSub.splice(index,1);
                		this.formData.addSystemSubSon.splice(index,1);
                		$("#count_"+index+1).remove();
                		
                		for(var i=0;i<this.formData.addSystemSub.length;i++){
                			$("#allSysCount_"+(i)).find("label").text('子系统'+(i+1)+'系统名称：');
                			$("#allSysCode_"+(i)).find("label").text('子系统'+(i+1)+'标准化代码：');
                		}
                		
                		if(this.formData.addSystemSub.length <2){
                			this.formData.checkCount = '';
                			this.promptCount=true;
                			this.$refs.formData.validateField('checkCount');
                		}else{
                			this.promptCount=false;
                			this.formData.checkCount = '1';
                		}
              			this.systemNameSon1[0] = true;
              			this.systemNameSon12[0] = true;
                  	this.formData.systemNameSon="1";
                  	this.formData.systemCodeSon="1";
                  	this.formData.stars="1";
                  	this.formData.aa = '1';
              			this.$refs.formData.validateField('aa');
                  	this.closes();
                	},
                	gernerateId: function (index){
                		return "person_" +index
                	},
                	dataDel:function(i){
                		if(i==(this.formData.addSystemSub.length-1)){
                			$("#lastDel").show();
                			
                		}else{
                			$("#person_"+i).show();
                		}
                	},
                	hiddenDel:function(i){
                	    if(i==(this.formData.addSystemSub.length-1)){
                			$("#lastDel").hide();
                		}
                		$("#person_"+i).hide();
                	},
                	systemSonNameDis: function (index){
                		return "dis_" +index
                	},
                    //点击切换 添加class名
                  getTypeClass:function(e,param){
                    $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    this.formData.fkInfoSysTypeCon = param;
                    this.$refs.formData.validateField('fkInfoSysTypeCon');
                  },
                  getMergeClass:function(e,param){
                  	this.paramNan = true;
                  	this.rules.standardizedCode[0].required=true;
                    this.systemInfo = true;
                    this.systemSonInfo = false;
                    this.formData.addSystemSub=[];
                    this.promptCount=false;
              			this.formData.checkCount = '1';
              			this.systemNameSon1 = [];
              			this.systemNameSon12= [];
              			Vue.set(this.systemNameSon12, 0, false);
              			Vue.set(this.systemNameSon1, 0, true);
              			this.formData.addSystemSubSon = [];
                  	this.formData.systemNameSon="1";
                  	this.formData.systemCodeSon="1";
                  	this.formData.stars="1";
                  	this.formData.aa = '1';
                  	
                    $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    this.formData.fkSystemIsMerge = param;
                    if(param==1 && this.paramNan == true){
                      this.formData.addSystemSub.push({
                        "label":"子系统"+(this.formData.addSystemSub.length+1)+"系统名称：",
                        "labelCode":"子系统"+(this.formData.addSystemSub.length+1)+"标准化代码：",
                        "systemNameSon":"",
                        "standardizedCode":""
                      });
                      this.formData.addSystemSub.push({
                        "label":"子系统"+(this.formData.addSystemSub.length+1)+"系统名称：",
                        "labelCode":"子系统"+(this.formData.addSystemSub.length+1)+"标准化代码：",
                        "systemNameSon":"",
                        "standardizedCode":""
                      });
                      this.systemInfo = false;
                      this.systemSonInfo = true;
                      this.formData.systemName = "";
                      this.formData.standardizedCode = "";
                      this.rules.standardizedCode[0].required=false;
                      this.paramNan = false;
                    }
                    if(param == 2){
                    	this.paramNan = true;
                    	this.rules.standardizedCode[0].required=true;
                      this.systemInfo = true;
                      this.systemSonInfo = false;
                      this.formData.addSystemSub=[];
                      this.promptCount=false;
                			this.formData.checkCount = '1';
                			this.formData.systemName = "";
                			this.formData.standardizedCode = "";
                			this.systemNameSon1 = [];
                			this.systemNameSon12= [];
                			Vue.set(this.systemNameSon12, 0, false);
                			Vue.set(this.systemNameSon1, 0, true);
                			this.formData.addSystemSubSon = [];
                    	this.formData.systemNameSon="1";
                    	this.formData.systemCodeSon="1";
                    	this.formData.stars="1";
                    	this.formData.aa = '1';
                    }
                    this.$refs.formData.validateField('fkSystemIsMerge');
                  },
                  getRecordClass:function(e,param){
                    $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    this.formData.appIsInternet = param;
                    this.$refs.formData.validateField('appIsInternet');
                  },
                  getClass:function(e){
                    $("#sysType div div").click(function(){
                      $("#sysType div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      //$("#sysBusSituationType").val($("#busSituationType").val());
                      this.formData.sysBusSituationType = $("#busSituationType").val();
                    }else{
                      //$("#sysBusSituationType").val(e.target.innerHTML);
                      this.formData.sysBusSituationType = e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('sysBusSituationType');
                  },
                  getBus:function(){
                    this.formData.sysBusSituationType = $("#busSituationType").val();
                    this.$refs.formData.validateField('sysBusSituationType');
                  },
                  getScopeClass:function(e){
                    $("#sysSerScope div div").click(function(){
                      $("#sysSerScope div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      //$("#sysServiceSitScope").val($("#serviceSitScope").val());
                      //this.formData.sysServiceSitScope = $("#serviceSitScope").val();
                    }else if(e.target.innerHTML.indexOf("跨省（区、市）跨") != -1){
                      //$("#sysServiceSitScope").val("跨省（区、市）跨个^"+$("#serviceSitScope2").val());
                      //this.formData.sysServiceSitScope = "跨省（区、市）跨个^"+$("#serviceSitScope2").val();
                    }else if(e.target.innerHTML.indexOf("跨地（区、市） 跨") != -1){
                      //$("#sysServiceSitScope").val("跨地（区、市） 跨个^"+$("#serviceSitScope3").val());
                      //this.formData.sysServiceSitScope = "跨地（区、市） 跨个^"+$("#serviceSitScope3").val();
                    }else{
                      //$("#sysServiceSitScope").val(e.target.innerHTML);
                      this.formData.sysServiceSitScope = e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('sysServiceSitScope');
                  },
                  getScope1:function(){
                    this.formData.sysServiceSitScope = $("#serviceSitScope").val();
                    this.$refs.formData.validateField('sysServiceSitScope');
                  },
                  getScope2:function(){
                    this.formData.sysServiceSitScope = "跨省（区、市）跨个^"+$("#serviceSitScope2").val();
                    this.$refs.formData.validateField('sysServiceSitScope');
                  },
                  getScope3:function(){
                    this.formData.sysServiceSitScope = "跨地（区、市） 跨个^"+$("#serviceSitScope3").val();
                    this.$refs.formData.validateField('sysServiceSitScope');
                  },
                  getObjectClass:function(e){
                    $("#sysSerObject div div").click(function(){
                      $("#sysSerObject div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      //$("#sysServiceSitObject").val($("#serviceSitObject").val());
                      this.formData.sysServiceSitObject = $("#serviceSitObject").val();
                    }else{
                      //$("#sysServiceSitObject").val(e.target.innerHTML);
                      this.formData.sysServiceSitObject = e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('sysServiceSitObject');
                  },
                  getObject:function(){
                    this.formData.sysServiceSitObject = $("#serviceSitObject").val();
                    this.$refs.formData.validateField('sysServiceSitObject');
                  },
                  getCoverageClass:function(e){
                    $("#sysCoverage div div").click(function(){
                      $("#sysCoverage div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      this.formData.npCoverageRange = $("#coverageRange").val();
                    }else{
                      this.formData.npCoverageRange = e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('npCoverageRange');
                  },
                  getCoverage:function(){
                    this.formData.npCoverageRange = $("#coverageRange").val();
                    this.$refs.formData.validateField('npCoverageRange');
                  },
                  getNetworkClass:function(e){
                    $("#sysNetwork div div").click(function(){
                      $("#sysNetwork div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      this.formData.npNetworkProperties = $("#networkProperties").val();
                    }else{
                      this.formData.npNetworkProperties = e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('npNetworkProperties');
                  },
                  getNetwork:function(){
                    this.formData.npNetworkProperties = $("#networkProperties").val();
                    this.$refs.formData.validateField('npNetworkProperties');
                  },
                  getSitClass:function(e){
                    $("#sysIntercon div div").click(function(){
                      $("#sysIntercon div div").removeClass("btnColor");
                      $(this).addClass("btnColor");
                    });
                    if(e.target.innerHTML.indexOf("其它") != -1){
                      this.formData.interconnectionSit = $("#interconnection").val();
                    }else{
                      $("#interconnectionSit").val(e.target.innerHTML);
                      this.formData.interconnectionSit =e.target.innerHTML;
                    }
                    this.$refs.formData.validateField('interconnectionSit');
                  },
                  getSit:function(){
                    this.formData.interconnectionSit = $("#interconnection").val();
                    this.$refs.formData.validateField('interconnectionSit');
                  },
                  getSubClass:function(e,param){
                    $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    this.formData.subIsSystem = param;
                    this.$refs.formData.validateField('subIsSystem');
                    if(param == 1 || param == "1"){
                    	this.BeSonSystem = true;
                    	this.rules.fatherSystemName[0].required = true;
                    	this.rules.fatherCompanyName[0].required = true;
                    }else{
                    	this.BeSonSystem = false;
                    	this.rules.fatherSystemName[0].required = false;
                    	this.rules.fatherCompanyName[0].required = false;
                    	this.formData.fatherSystemName = "";
                    	this.formData.fatherCompanyName = "";
                    }
                  },
                  btnBoolen:function(e){
                      $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                      if($(e.target).val() == '是'){
                          $("#trueSys").show();
                          $("#falseSys").hide();
                      }else{
                          $("#falseSys").show();
                          $("#trueSys").hide();
                      }
                  },
                  addSysInfo:function (e) {
                      if(this.formData.addSystemSub.length>=this.sysName.length){
                        this.addSub = false;
                      }else{
                      	this.systemNameSon1.push(true);
                      	this.systemNameSon12.push(false);
                      	this.formData.systemNameSon="";
                      	this.formData.systemCodeSon="";
                    		this.formData.addSystemSub.push({
                          "label":"子系统"+(this.formData.addSystemSub.length+1)+"系统名称：",
                          "labelCode":"子系统"+(this.formData.addSystemSub.length+1)+"标准化代码：",
                          "systemName":"",
                          "standardizedCode":""
                        });
                      }
                    },
                    text:function(){
                        $('#sysBusDescription').on("keyup",function(){
                            $('#textNum').text($('#sysBusDescription').val().length);//这句是在键盘按下时，实时的显示字数
                            if($('#sysBusDescription').val().length > 200){
                                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                                $('#sysBusDescription').val($('#sysBusDescription').val().substring(0,200));//长度大于100时截取钱100个字符
                            }
                        })
                    },
                    fnSave:function() {
                      ajaxMethod(this, 'post',
                          'system/saveSystem', true,
                          JSON.stringify(this.formData), 'json',
                          'application/json;charset=UTF-8',
                          this.fnSaveSuccessMethod);
                    },
                    // 获取系统信息成功
                    fnSaveSuccessMethod : function(_self, responseData) {
                    	if(responseData.data){
                    		if(type=="change"){
                    			window.location.href = originUrl+encodeURI("applicationChangeGradPage?systemId="+responseData.data);
                    		}else{
                    			window.location.href = originUrl+encodeURI("addCompanyGradPage?systemId="+responseData.data);
                    		}
                    	}else{
                    		//提示系统已存在
                    		
                    	}
                    },
                    //获取全部系统
                    getAllSystemInfoMethod : function(_self){
                    	ajaxMethod(_self, 'post',
                          'systemapi/querySystemApi', true,
                          '{"companyCode":"'+null+'"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getAllSystemInfoSuccessMethod);
                    },
                    getAllSystemInfoSuccessMethod:function(_self,responseData){
                    	_self.allFatherSystemName = responseData.data;
                    },
                    // 获取系统下拉列表
                    getSystemInfoMethod : function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemapi/querySystemApi', true,
                          '{"companyCode":"'+companyCode+'"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getSystemInfoSuccessMethod);
                    },
                    // 获取系统信息成功
                    getSystemInfoSuccessMethod : function(_self, responseData) {
                      _self.sysName = responseData.data;
                      _self.systemId = responseData.data;
                    },
                    // 获取业务承受信息
                    getBearInfoMethod : function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"22"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getBearInfoSuccessMethod);
                      
                      var data = '{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"生产作业","systemCode":"1","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"指挥调度","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"管理控制","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"内部办公","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"公众服务","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
                      data = JSON.parse(data);
                      _self.getBearInfoSuccessMethod(_self, data);
                    },
                    // 获取业务承受信息成功
                    getBearInfoSuccessMethod : function(_self, responseData) {
                      _self.sysType = responseData.data;
                      
                      if(_self.sysType.length > 5){
                        $("#sysType").addClass("baseMes2");
                      }
                    },
                    // 获取服务对象信息
                    getServiceObjectMethod : function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"16"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getServiceObjectSuccessMethod);
                      var data = '{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"单位内部人员","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"社会公众人员","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"两者均包括","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
                      data = JSON.parse(data);
                      _self.getServiceObjectSuccessMethod(_self, data);
                    },
                    // 获取服务对象成功
                    getServiceObjectSuccessMethod : function(_self, responseData) {
                      _self.sysServiceObject = responseData.data;
                    },
                    // 获取覆盖范围信息
                    getCoverageRangeMethod : function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemCode/querySystemCodeForKeySystemCode', false,
                          '{"codeType":"17"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getCoverageRangeSuccessMethod);
                    },
                    // 获取覆盖范围成功
                    getCoverageRangeSuccessMethod : function(_self, responseData) {
                      _self.sysCoverage = responseData.data;
                    },
                    // 获取网络性质信息
                    getNetworkMethod : function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"18"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getNetworkSuccessMethod);
                      var data = '{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"业务专网","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"互联网","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"0","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';                      
                      data = JSON.parse(data);
                      _self.getNetworkSuccessMethod(_self, data);
                    },
                    // 获取网络性质成功
                    getNetworkSuccessMethod : function(_self, responseData) {
                      _self.sysNetwork = responseData.data;
                    },
                    //获取系统互连信息
                    getInterconnectionMethod: function(_self) {
//                      ajaxMethod(_self, 'post',
//                          'systemCode/querySystemCodeForKeySystemCode', true,
//                          '{"codeType":"13"}', 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getInterconnectionSuccessMethod);
                      
                      var data = '{"code":"0","msg":"成功","pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"codeId":null,"codeType":null,"codeName":"与其他行业系统连接","systemCode":"1","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"与本行业其他单位系统连接","systemCode":"2","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"与本单位其他系统连接","systemCode":"3","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null},{"codeId":null,"codeType":null,"codeName":"其它","systemCode":"4","codeOrder":null,"systemFatherCode":null,"handleDate":null,"handleResult":null,"deleteStatus":null,"createUserName":null,"createTime":null,"updateTime":null,"remark":null}]}';
                      data = JSON.parse(data);
                      _self.getInterconnectionSuccessMethod(_self, data);
                    },
                    // 获取系统互连成功
                    getInterconnectionSuccessMethod : function(_self, responseData) {
                      _self.sysIntercon = responseData.data;
                    },
                    //获取服务范围信息
                    getServiceRangeMethod: function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemCode/querySystemCodeForKeySystemCode', false,
                          '{"codeType":"20"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getServiceRangeSuccessMethod);
                    },
                    // 获取服务范围信息成功
                    getServiceRangeSuccessMethod : function(_self, responseData) {
                      _self.sysServiceScope = responseData.data;
                      if(_self.sysServiceScope.length > 5){
                        $("#sysSerScope").addClass("baseMes2");
                      }
                    },
                    //获取单位信息
                    getCompanyMethod:function(_self){
                      ajaxMethod(_self, 'post',
                          'company/queryCompanyName', true,'{}', 'json',
                          'application/json;charset=UTF-8',_self.getCompanySuccessMethod);
                    },
                    //获取单位信息
                    getCompanySuccessMethod:function(_self,responseData){
                      _self.msgName = responseData.data;
                      if(companyCode!=''&&companyCode!=null){
                        for(var i=0;i<_self.msgName.length;i++){
                          if(companyCode==_self.msgName[i].companyCode){
                            _self.formData.companyName = _self.msgName[i].companyName;
                            _self.formData.fkCompanyCode = _self.msgName[i].companyCode;
                            //为了保证一进入页面就保证beginContent与一开始的formData一致
                            _self.beginContent.companyName = _self.msgName[i].companyName;
                            _self.beginContent.fkCompanyCode = _self.msgName[i].companyCode;
                            break;
                          }
                        }
                      }
                    },
                    //获取上级系统所属单位信息
                    getParentCompanyMethod:function(_self){
                      ajaxMethod(_self, 'post',
                          'organizationapi/queryOrganizationForKeyOrganizationName', true,'{}', 'json',
                          'application/json;charset=UTF-8',_self.getParentCompanySuccessMethod);
                    },
                    //获取上级系统所属单位信息
                    getParentCompanySuccessMethod:function(_self,responseData){
                      _self.parentMsgName = responseData.data;
                      
                    },
                    setStandardizedCode:function(e,val){
                  		if(e!=null){
                  			for(var i=0;i<this.sysName.length;i++){
                  				if(e==this.sysName[i].systemName){
                  					if(val==1){
                  						this.formData.standardizedCode = this.sysName[i].systemCode;
                  						this.formData.executiveDireCon = this.sysName[i].bcdCpname;
                              this.formData.executiveDireConTel = this.sysName[i].bcdCptel;
                              this.formData.executiveOfficeName = this.sysName[i].bcdName;
                              this.executiveDisabled = true;
                  					}else{
                  						this.systemNameSon1[val-2] = false;
                  						this.formData.systemNameSon=this.sysName[i].systemName;
                  						this.formData.systemCodeSon=this.sysName[i].systemCode;
                  						this.formData.addSystemSub[val-2].standardizedCode = this.sysName[i].systemCode;
                  					}
                  				}
                  			}
                  		}
                  		if(this.formData.fkSystemIsMerge!='1'||this.formData.fkSystemIsMerge==null||this.formData.fkSystemIsMerge==''){
                        return;
                      }
                  		if(this.formData.addSystemSubSon != null){
                        if(this.formData.addSystemSubSon.length >= val-1){
//                          this.formData.addSystemSubSon[val-2] = e;
                          Vue.set(this.formData.addSystemSubSon, val-2, e);
                        }else{
                          this.formData.addSystemSubSon.push(e);
                        }
                      }
                  		var isNan = true;
                  		for(var i = 0 ; i < this.formData.addSystemSubSon.length; i++){
                  			var boo = true;
                  			var count = 0;
                  			for(var j = 0 ; j < this.formData.addSystemSubSon.length; j++){
	                  			 if(this.formData.addSystemSubSon[i] == this.formData.addSystemSubSon[j]){
	                  				  boo = false;
	 	                    			count ++ ;
		                    	 }
                  			}
                  			if(count > 1){
                  				if(boo){
//                    				this.systemNameSon12[i] = false;                  				
                    				Vue.set(this.systemNameSon12, i, false);
                    			}else{
                    				Vue.set(this.systemNameSon12, i, true);
                    				this.formData.aa = '';
	                    			this.$refs.formData.validateField('aa');
	                    			isNan = false;
                    			}
                  			}
                    	}
                  		if(isNan){
                  			this.formData.aa = '1';
                  			this.$refs.formData.validateField('aa');
                  		}
                  		if(val-1 <2){
                  			this.formData.checkCount = '';
                  			this.promptCount=true;
                  		}else{
                  			this.promptCount=false;
                  			this.formData.checkCount = '1';
                  			this.$refs.formData.validateField('checkCount');
                  		}
                    },
                    getGetSystemMethod:function(_self,systemId){
                      ajaxMethod(_self, 'post',
                          'system/queryEditSystem', true,JSON.stringify(_self.formData), 'json',
                          'application/json;charset=UTF-8',_self.getGetSystemSuccessMethod);
                    },
                    getGetSystemSuccessMethod: function(_self, responseData) {
                      //var response='{"fkInfoSysTypeCon":"1","appIsInternet":"1","fkSystemIsMerge":"1","systemName":"胜利时刻","standardizedCode":"271","gradeRecordSysName":"等保时刻","sysBusSituationType":"公众服务","sysBusDescription":"公众描述","sysServiceSitScope":"全国","sysServiceSitObject":"单位内部人员","npCoverageRange":"局域网","npNetworkProperties":"互联网","interconnectionSit":"与其他行业系统连接","fkExaminStatus":"数据库","productsNumber":"无","fkNationalIsProducts":"全部使用","fkProductsType":"等级测评","fkResponsibleType":"国外服务商","fkCompanyCode":"100","executiveOfficeName":"主管室时刻","executiveDireCon":"主管联系人","executiveDireConTel":"2710000","whenInvestmentUseL":"2018-05-24 11:13:17","subIsSystem":"1","addSystemSub":[{"systemName":"子1时刻","standardizedCode":"27101"},{"systemName":"子2时刻","standardizedCode":"27102"}],"systemKeyProducts":[{"fkNationalIsProducts":"1","fkExaminStatus":"2","productsNumber":"无","nUseProbability":1,"otherName":""}],"systemUseServices":[{"fkResponsibleType":"1","fkProductsType":"2","serviceIsUse":"1","otherName":"暂无"}]}';
                      //response = JSON.parse(response);
                    	var response = responseData.data;
                      _self.formData = response;
                      if(response.addSystemSub!=null){
                      	for(var i=0;i<response.addSystemSub.length;i++){
                      		this.systemInfo2[i] = true;
 	                   		 _self.formData.addSystemSub[i] = {
 	                          "label":"子系统"+(i+1)+"系统名称：",
 	                          "labelCode":"子系统"+(i+1)+"标准化代码：",
 	                          "systemName":response.addSystemSub[i].systemName,
 	                          "standardizedCode":response.addSystemSub[i].standardizedCode
 	                        };
 	                   		}
                      }
                      for(var i=0;i<10;i++){
                        if(response.addSystemSub!=null){
                          if(response.addSystemSub.length<_self.formData.addSystemSub.length){
                            response.addSystemSub.push({
                              "label":"子系统"+(response.addSystemSub.length+1)+"系统名称：",
                              "labelCode":"子系统"+(response.addSystemSub.length+1)+"标准化代码：",
                              "systemName":"",
                              "standardizedCode":""
                            });
                          }else if(response.addSystemSub.length>_self.formData.addSystemSub.length){
                            _self.formData.addSystemSub.push({
                              "label":"子系统"+(response.addSystemSub.length+1)+"系统名称：",
                              "labelCode":"子系统"+(response.addSystemSub.length+1)+"标准化代码：",
                              "systemName":"",
                              "standardizedCode":""
                            });
                          }
                          if(response.addSystemSub.length <2){
                      			this.formData.checkCount = '';
                      			this.promptCount=true;
//                      			this.$refs.formData.validateField('checkCount');
                      		}else{
                      			this.promptCount=false;
                      			this.formData.checkCount = '1';
                      		}
                          this.systemNameSon1[0] = true;
                    			this.systemNameSon12[0] = true;
                        	this.formData.systemNameSon="1";
                        	this.formData.systemCodeSon="1";
                        	this.formData.stars="1";
                        	this.formData.aa="1";
                        	this.formData.addSystemSubSon=[];
                        }
                        if(response.systemKeyProducts==null){
                          response.systemKeyProducts = []
                          response.systemKeyProducts.push({
                            "fkNationalIsProducts":"",
                            "fkExaminStatus":"",
                            "productsNumber":"",
                            "nUseProbability":0,
                            "otherName":""
                          });
                        }else{
                          
                          if(response.systemKeyProducts.length<_self.formData.systemKeyProducts.length){
                            response.systemKeyProducts.push({
                              "fkNationalIsProducts":"",
                              "fkExaminStatus":"",
                              "productsNumber":"",
                              "nUseProbability":0,
                              "otherName":""
                            });
                          }else if(response.systemKeyProducts.length>_self.formData.systemKeyProducts.length){

                            _self.formData.systemKeyProducts.push({
                              "fkNationalIsProducts":"",
                              "fkExaminStatus":"",
                              "productsNumber":"",
                              "nUseProbability":0,
                              "otherName":""
                            });
                          }
                        }
                        if(response.systemUseServices==null){
                          response.systemUseServices = []
                          response.systemUseServices.push({
                            "fkResponsibleType":"",
                            "fkProductsType":"",
                            "fkSystemId":"",
                            "serviceIsUse":"",
                            "otherName":""
                          });
                        }else{
                          if(response.systemUseServices.length<_self.formData.systemUseServices.length){
                            response.systemUseServices.push({
                              "fkResponsibleType":"",
                              "fkProductsType":"",
                              "fkSystemId":"",
                              "serviceIsUse":"",
                              "otherName":""
                            });
                          }else if(response.systemUseServices.length>_self.formData.systemUseServices.length){
                            _self.formData.systemUseServices.push({
                              "fkResponsibleType":"",
                              "fkProductsType":"",
                              "fkSystemId":"",
                              "serviceIsUse":"",
                              "otherName":""
                            });
                          }
                        }
                      }
                      if(response.addSystemSub==null){
                        _self.formData.addSystemSub = [
//                        {
//                          label:"子系统1系统名称：",
//                          labelCode:"子系统1标准化代码：",
//                          systemName:"",
//                          standardizedCode:""
//                        },
//                        {
//                          label:"子系统2系统名称：",
//                          labelCode:"子系统2标准化代码：",
//                          systemName:"",
//                          standardizedCode:""
//                        }
                        ];
                      }
                      if(responseData.data!=null){
                    		var beginContent = _self.beginContent;
                      	var currentContent = responseData.data;
                    		beginContent.fkInfoSysTypeCon = currentContent.fkInfoSysTypeCon;//信息系统建设类型
                    		beginContent.fkSystemIsMerge = currentContent.fkSystemIsMerge;//是否为合并系统
                    		beginContent.systemName = currentContent.systemName;//系统名称
                    		beginContent.standardizedCode = currentContent.standardizedCode;//标准化代码
                    		//子系统
                    		if(currentContent.addSystemSub!=null){
                        	for(var i=0;i<currentContent.addSystemSub.length;i++){
                        		beginContent.addSystemSub[i] = {
     	                          "label":currentContent.addSystemSub[i].label,
     	                          "labelCode":currentContent.addSystemSub[i].labelCode,
     	                          "systemName":currentContent.addSystemSub[i].systemName,
     	                          "standardizedCode":currentContent.addSystemSub[i].standardizedCode
     	                        };
   	                   		}
                        }
                    		beginContent.gradeRecordSysName = currentContent.gradeRecordSysName;//等保备案系统名称
                    		beginContent.appIsInternet = currentContent.appIsInternet;//是否为互联网应用
                    		beginContent.sysBusSituationType = currentContent.sysBusSituationType;//业务类型
                    		beginContent.sysBusDescription = currentContent.sysBusDescription;//业务描述
                    		beginContent.sysServiceSitScope = currentContent.sysServiceSitScope;//服务范围
                    		beginContent.sysServiceSitObject = currentContent.sysServiceSitObject;//服务对象
                    		beginContent.npCoverageRange = currentContent.npCoverageRange;//覆盖范围
                    		beginContent.npNetworkProperties = currentContent.npNetworkProperties;//网络性质
                    		beginContent.interconnectionSit = currentContent.interconnectionSit;//系统互联情况
                    		//关键产品使用情况
                    		for(var i=0;i<currentContent.systemKeyProducts.length;i++){
                      		beginContent.systemKeyProducts[i].fkNationalIsProducts = currentContent.systemKeyProducts[i].fkNationalIsProducts+"";//是否使用国产品
                      		beginContent.systemKeyProducts[i].fkExaminStatus = currentContent.systemKeyProducts[i].fkExaminStatus;//数字1234，对应产品类型，
                      		beginContent.systemKeyProducts[i].productsNumber = currentContent.systemKeyProducts[i].productsNumber;//数量
                      		beginContent.systemKeyProducts[i].nUseProbability = currentContent.systemKeyProducts[i].nUseProbability;//使用国产品率
                      		beginContent.systemKeyProducts[i].otherName = currentContent.systemKeyProducts[i].otherName;//其他
                      	}
                    	//系统采用服务情况
                      	for(var i=0;i<currentContent.systemUseServices.length;i++){
                          beginContent.systemUseServices[i].fkResponsibleType = parseInt(currentContent.systemUseServices[i].fkResponsibleType);//服务责任方类型
            	          	beginContent.systemUseServices[i].fkProductsType = currentContent.systemUseServices[i].fkProductsType;//产品类型
            	          	beginContent.systemUseServices[i].fkSystemId = currentContent.systemUseServices[i].fkSystemId;
            	          	beginContent.systemUseServices[i].serviceIsUse = currentContent.systemUseServices[i].serviceIsUse;//是否采用
            	          	beginContent.systemUseServices[i].otherName = currentContent.systemUseServices[i].otherName;//其他
                      	}
                      	beginContent.companyName = currentContent.companyName;//所属单位名称
                      	beginContent.whenInvestmentUse = currentContent.whenInvestmentUse;//何时投入使用
                      	beginContent.executiveOfficeName = currentContent.executiveOfficeName;//主管处室名称
                      	beginContent.subIsSystem = currentContent.subIsSystem;//系统是否为分系统
                      	beginContent.fatherSystemName = currentContent.fatherSystemName;//上级系统名称
                      	beginContent.fatherCompanyName = currentContent.fatherCompanyName;//请选择上级系统所属单位名称
                      	beginContent.executiveDireCon = currentContent.executiveDireCon;//主管联系人
                      	beginContent.executiveDireConTel = currentContent.executiveDireConTel;//主管联系人电话
                    	}
                    	if(_self.formData.systemKeyProducts!=null){
                      	  for(var k=0;k<currentContent.systemKeyProducts.length;k++){
                      		  if(!response.systemKeyProducts[k].productsNumber){//没填
                      		  	_self.formData.systemKeyProducts[k].allHave="";
                      		  	_self.formData.systemKeyProducts[k].number = 0;
                      		  	_self.formData.systemKeyProducts[k].fkNationalIsProducts = currentContent.systemKeyProducts[k].fkNationalIsProducts+"";//是否使用国产品
                      		  	_self.formData.systemKeyProducts[k].fkExaminStatus = currentContent.systemKeyProducts[k].fkExaminStatus;//数字1234，对应产品类型，
                      		  	_self.formData.systemKeyProducts[k].productsNumber = currentContent.systemKeyProducts[k].productsNumber;//数量
                      		  	_self.formData.systemKeyProducts[k].nUseProbability = currentContent.systemKeyProducts[k].nUseProbability;//使用国产品率
                      		  	_self.formData.systemKeyProducts[k].otherName = currentContent.systemKeyProducts[k].otherName;//其他
                      		  }else if(response.systemKeyProducts[k].productsNumber == "共有"){
                      		  	_self.formData.systemKeyProducts[k].allHave=1
                      		  	_self.formData.systemKeyProducts[k].number = 0;
                      		  	_self.formData.systemKeyProducts[k].fkNationalIsProducts = currentContent.systemKeyProducts[k].fkNationalIsProducts+"";//是否使用国产品
                      		  	_self.formData.systemKeyProducts[k].fkExaminStatus = currentContent.systemKeyProducts[k].fkExaminStatus;//数字1234，对应产品类型，
                      		  	_self.formData.systemKeyProducts[k].productsNumber = currentContent.systemKeyProducts[k].productsNumber;//数量
                      		  	_self.formData.systemKeyProducts[k].nUseProbability = currentContent.systemKeyProducts[k].nUseProbability;//使用国产品率
                      		  	_self.formData.systemKeyProducts[k].otherName = currentContent.systemKeyProducts[k].otherName;//其他

                      		  }else{//其他
                      		  	_self.formData.systemKeyProducts[k].allHave=2
                      		  	_self.formData.systemKeyProducts[k].number = response.systemKeyProducts[k].productsNumber;
                      		  	_self.formData.systemKeyProducts[k].fkNationalIsProducts = currentContent.systemKeyProducts[k].fkNationalIsProducts+"";//是否使用国产品
                      		  	_self.formData.systemKeyProducts[k].fkExaminStatus = currentContent.systemKeyProducts[k].fkExaminStatus;//数字1234，对应产品类型，
                      		  	_self.formData.systemKeyProducts[k].productsNumber = currentContent.systemKeyProducts[k].productsNumber;//数量
                      		  	_self.formData.systemKeyProducts[k].nUseProbability = currentContent.systemKeyProducts[k].nUseProbability;//使用国产品率
                      		  	_self.formData.systemKeyProducts[k].otherName = currentContent.systemKeyProducts[k].otherName;//其他
                      		  }
                      	  }
                        
                    	}
                      
                      if(response.fkSystemIsMerge == '1'){
                        this.systemSonInfo = true;
                        this.systemInfo = false;
                        if(type=="create"){
                        	$("#systemInfo1").removeAttr("disabled");
                        	for(var i=0;i<response.addSystemSub.length;i++){
                        		this.systemInfo2[i] = false;
                        	}
                        }else if(type="change"){
                        	$("#systemInfo1").attr("disabled","true");
                        	for(var i=0;i<response.addSystemSub.length;i++){
                        		this.systemInfo2[i] = true;
                        	}
                        	this.rules.standardizedCode[0].required=false;
                        }
                      }else{
                      	this.rules.standardizedCode[0].required=true;
                        this.systemSonInfo = false;
                        this.systemInfo = true;
                      }
                      if(_self.formData.fkInfoSysTypeCon!=''){
                        var array = $('#baseMes1').find('div').map(function (index, ele) {
                          if(_self.formData.fkInfoSysTypeCon==1&&ele.innerHTML=='自建'){
                            return ele;
                          }else if(_self.formData.fkInfoSysTypeCon==2&&ele.innerHTML=='统建'){
                            return ele;
                          }else if(_self.formData.fkInfoSysTypeCon==3&&ele.innerHTML=='总部系统'){
                            return ele;
                          }else{
                             return "";
                          }
                        }).get();
                        $(array).addClass('btnColor')
                      }
                      if(_self.formData.fkSystemIsMerge!=''){
                        var array = $('#baseMes2').find('div').map(function (index, ele) {
                          if(_self.formData.fkSystemIsMerge==1&&ele.innerHTML=='是'){
                          	_self.rules.standardizedCode[0].required=false;
                            return ele;
                          }else if(_self.formData.fkSystemIsMerge==2&&ele.innerHTML=='否'){
                          	_self.rules.standardizedCode[0].required=true;
                            return ele;
                          }else{
                             return "";
                          }
                        }).get();
                        $(array).addClass('btnColor')
                      }
                      if(_self.formData.appIsInternet!=''){
                        var array = $('#baseMes3').find('div').map(function (index, ele) {
                          if(_self.formData.appIsInternet==1&&ele.innerHTML=='是'){
                            return ele;
                          }else if(_self.formData.appIsInternet==2&&ele.innerHTML=='否'){
                            return ele;
                          }else{
                             return "";
                          }
                        }).get();
                        $(array).addClass('btnColor')
                      }
                      if(_self.formData.sysBusSituationType!=''){
                        var array = $('#sysType').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.sysBusSituationType){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          $("#sysType1").addClass('btnColor');
                          $("#busSituationType").val(_self.formData.sysBusSituationType);
                        }
                      }
                      if(_self.formData.sysServiceSitScope!=''){
                        var array = $('#sysSerScope').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.sysServiceSitScope){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          if(_self.formData.sysServiceSitScope.indexOf("跨省（区、市）跨个") != -1){
                            $("#sysSerScope2").addClass('btnColor');
                            var num = _self.formData.sysServiceSitScope.split("^");
                            $("#serviceSitScope2").val(num[1]);
                          }else if(_self.formData.sysServiceSitScope.indexOf("跨地（区、市） 跨个") != -1){
                            $("#sysSerScope3").addClass('btnColor');
                            var num = _self.formData.sysServiceSitScope.split("^");
                            $("#serviceSitScope3").val(num[1]);
                          }else{
                            $("#sysSerScope1").addClass('btnColor');
                            $("#serviceSitScope").val(_self.formData.sysServiceSitScope);
                          }
                        }
                      }
                      if(_self.formData.sysServiceSitObject!=''){
                        var array = $('#sysSerObject').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.sysServiceSitObject){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          $("#sysSerObject1").addClass('btnColor');
                          $("#serviceSitObject").val(_self.formData.sysServiceSitObject);
                        }
                      }

                      if(_self.formData.npCoverageRange!=''){
                        var array = $('#sysCoverage').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.npCoverageRange){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          $("#sysCoverage1").addClass('btnColor');
                          $("#coverageRange").val(_self.formData.npCoverageRange);
                        }
                      }

                      if(_self.formData.npNetworkProperties!=''){
                        var array = $('#sysNetwork').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.npNetworkProperties){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          $("#sysNetwork1").addClass('btnColor');
                          $("#networkProperties").val(_self.formData.npNetworkProperties);
                        }
                      }

                      if(_self.formData.interconnectionSit!=''){
                        var array = $('#sysIntercon').find('div').map(function (index, ele) {
                          if(ele.innerHTML==_self.formData.interconnectionSit){
                            return ele;
                          }else{
                             return "";
                          }
                       }).get();
                        var other=true;
                        for(var i=0;i<array.length;i++){
                          if(array[i]!=''){
                            array[i].classList.add("btnColor");
                            other=false;
                          }
                        }
                        if(other){
                          $("#sysIntercon1").addClass('btnColor');
                          $("#interconnection").val(_self.formData.interconnectionSit);
                        }
                      }
                      if(_self.formData.subIsSystem!=''){
                        var array = $('#sysSubsystem').find('div').map(function (index, ele) {
                          if(_self.formData.subIsSystem==1&&ele.innerHTML=='是'){
                            return ele;
                          }else if(_self.formData.subIsSystem==2&&ele.innerHTML=='否'){
                            return ele;
                          }else{
                             return "";
                          }
                        }).get();
                        $(array).addClass('btnColor')
                      }
                      if(_self.formData.subIsSystem==1){
                      	_self.BeSonSystem = true;
                      	_self.rules.fatherSystemName[0].required = true;
                      	_self.rules.fatherCompanyName[0].required = true;
                      }else if(_self.formData.subIsSystem==2){
                      	_self.BeSonSystem = false;
                      	_self.rules.fatherSystemName[0].required = false;
                      	_self.rules.fatherCompanyName[0].required = false;
                      	_self.formData.fatherSystemName = "";
                      	_self.formData.fatherCompanyName = "";
                      }
                    },
                    headleArrayMethod:function(_self){
                    	for(var i=0;i<_self.nUsePro.length;i++){
                    		Vue.set(data.nUsePro, i, false);
                    	}
                    },
                    headleRelation:function(){
                      
                    },
                    executiveClear:function(){
                      this.formData.executiveDireCon = '';
                      this.formData.executiveDireConTel = '';
                      this.executiveDisabled = true;
                    },
                    headleCompanyName:function(){
                      for(var i=0;i<this.msgName.length;i++){
                        if(this.formData.companyName==this.msgName[i].companyName){
//                          this.formData.companyName = this.msgName[i].companyName;
                          this.formData.fkCompanyCode = this.msgName[i].companyCode;
                          this.paramNan = true;
                        	this.rules.standardizedCode[0].required=true;
                          this.systemInfo = true;
                          this.systemSonInfo = false;
                          this.formData.addSystemSub=[];
                          this.formData.systemName = "";
                          this.formData.standardizedCode = "";
                          $("#mes1").removeClass("btnColor");
                          $("#mes2").removeClass("btnColor");
                          this.getSystemInfoMethod(this);
                          break;
                        }
                      }
                    },
                    getSystemExecutiveApi: function(_self) {
                      ajaxMethod(_self, 'post',
                          'systemapi/querySystemExecutiveApi', true,
                          '{"companyCode":"'+companyCode+'"}', 'json',
                          'application/json;charset=UTF-8',
                          _self.getSystemExecutiveApiSuccessMethod);
                    },
                    // 获取系统信息成功
                    getSystemExecutiveApiSuccessMethod : function(_self, responseData) {
                      _self.sysNameExecutive = responseData.data;
//                      console.log(JSON.stringify(responseData.data))
                    },
                    //获取权限
          					getPermitJurisdictionInfo: function(_self){
                      ajaxMethod(_self,"get",
                          "jurisdiction/queryMenuJurisdictionApi",true,
                          JSON.stringify(""),"json",
                          'application/json;charset=UTF-8', _self.getPermitJurisdictionSuccess);
                    },
                    getPermitJurisdictionSuccess: function(_self,response){
                      _self.jurisdiction = getJurisdictionMethod(response,S_STR_PERMIT_PARAM_HEADQUARTERS_CREATE);
                    },
                    querySessionSuccess:function(_self,responseData){
                    	_self.formData = responseData.data;
                    },
                    getSystemSession:function(_self){
                    	ajaxMethod(_self, 'post',
                          'system/querySystemSession', false,
                          JSON.stringify(''), 'json',
                          'application/json;charset=UTF-8',
                          _self.querySystemSessionSuccess);
                    },
                    querySystemSessionSuccess:function(_self,responseData){
                    	if(responseData.data!=null){
                    		_self.substitute = responseData;
                    		_self.flag = true;
                    	}
                    },
//                    getMenuJurisdictionApi:function(_self){
//                    	ajaxMethod(_self, 'get',
//                          'jurisdiction/queryMenuJurisdictionApi', false,
//                          JSON.stringify(''), 'json',
//                          'application/json;charset=UTF-8',
//                          _self.getMenuJurisdictionApiSuccess);
//                    },
//                    getMenuJurisdictionApiSuccess:function(_self,responseData){
//                    //总部新建
//                    	_self.headquarters = getJurisdictionMethod(responseData,'0102010101');
//                    	//企业新建
//                    	_self.enterprise = getJurisdictionMethod(responseData,'0102010108');
//                    },
                },
                created: function() {
                	this.loading = false;
                	//获取权限
                	this.getPermitJurisdictionInfo(this);
                	if(type == "new" || type == "create"){
                		if(this.jurisdiction){
                			this.companyNameDisabled = false;
                		}
                	}
                	//获取全部系统信息
                	this.getAllSystemInfoMethod(this);
                  // 获取系统信息
                  this.getSystemInfoMethod(this);
                  // 获取业务承受业务类型
                  this.getBearInfoMethod(this);
                  // 获取服务范围
                  this.getServiceRangeMethod(this);
                  // 获取服务对象
                  this.getServiceObjectMethod(this);
                  // 获取覆盖范围
                  this.getCoverageRangeMethod(this);
                  // 获取网络性质
                  this.getNetworkMethod(this);
                  //系统互联情况
                  this.getInterconnectionMethod(this);
                  //获取单位信息
                  this.getCompanyMethod(this);
                  //获取上级系统所属单位信息
                  this.getParentCompanyMethod(this);
                  //获取系统主管处室
//                  this.getSystemExecutiveApi(this);
//                  this.getMenuJurisdictionApi(this);
                  this.loading = false;
                },
                mounted: function() {
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
                    	//总部新建
                    	data.headquarters = getJurisdictionMethod(response,'0102010101');
                    	//企业新建
                    	data.enterprise = getJurisdictionMethod(response,'0102010108');
                    },
                    error: function(err) {
                    }
                  });
                
                  this.formData.companyId = companyId;
                  this.formData.fkCompanyCode = companyCode;
                  //让开始内容与开始的formData一致
                  this.beginContent.companyId = companyId;
                  this.beginContent.fkCompanyCode = companyCode;
                  if(companyCode==''||companyCode==null){
                    this.companyNameDisabled = false;
                  }
                  if(companyCode!=''&&companyCode!=null){
                    this.formData.fkComCode = "1";
                    this.beginContent.fkComCode = "1";
                  }else{
                    this.formData.fkComCode = "";
                    this.beginContent.fkComCode = "";
                  }
                //从session中获取数据
                  this.getSystemSession(this);
                  if(!this.flag){
                  	if(systemId!=''&&systemId!=null){
                  	  if(type=="create"){
                  	  	this.change = false;
                      }else if(type="change"){
                      	this.change = true;
                      }
                  		this.formData.systemId = systemId;
                  		this.getGetSystemMethod(this,systemId);
                  	}
                  }else{
                  	if(type=="create"){
                  		this.change = false;
                    }else if(type="change"){
                    	this.change = true;
                    }
                		var arr = this.substitute.data.systemKeyProducts;
                		for(var i=0;i<arr.length;i++){
                			if(arr[i].fkNationalIsProducts != null && arr[i].fkNationalIsProducts != '' && arr[i].fkNationalIsProducts != 'undefind'){
                				arr[i].fkNationalIsProducts = arr[i].fkNationalIsProducts+"";
                			}
                		}
                		var arr2 = this.substitute.data.systemUseServices;
                		for(var i=0;i<arr2.length;i++){
                			if(arr2[i].fkResponsibleType != null && arr2[i].fkResponsibleType != '' && arr2[i].fkResponsibleType != 'undefind'){
                				arr2[i].fkResponsibleType = parseInt(arr2[i].fkResponsibleType);
                			}
                		}
                  	this.getGetSystemSuccessMethod(this,this.substitute);
                  	//_self.formData = responseData.data;
                  	setTimeout(function(){
                  		if(_self.formData.fkInfoSysTypeCon!=''){
                  			var array = $('#baseMes1').find('div').map(function (index, ele) {
                  				if(_self.formData.fkInfoSysTypeCon==1&&ele.innerHTML=='自建'){
                  					return ele;
                  				}else if(_self.formData.fkInfoSysTypeCon==2&&ele.innerHTML=='统建'){
                  					return ele;
                  				}else if(_self.formData.fkInfoSysTypeCon==3&&ele.innerHTML=='总部系统'){
                  					return ele;
                  				}else{
                  					return "";
                  				}
                  			}).get();
                  			$(array).addClass('btnColor')
                  		}
                  	},1);
                  }
                  
                  var _self=this;
                  bus.$on('addPreSystemName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addPreSystemAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('addNextSystemName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('addNextSystemAjax',"add");
                        } else {
                          /*_self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	_self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('returnSave',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('returnSaveAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	 _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('changePreSystemName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                        if (valid) {
                          bus.$emit('changePreSystemAjax',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	 _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('changeNextSystemName',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                      	var i=0;
                      	console.log(valid+"+"+i)
                      	i++;
                        if (valid) {
                          bus.$emit('toGradPage',"add");
                        } else {
                         /* _self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	 _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  bus.$on('mainSaveSystem',function(meg){
                    if(meg!=null){
                      _self.$refs[meg].validate(function (valid) {
                      	var i=0;
                      	console.log(valid+"+"+i)
                      	i++;
                        if (valid) {
                          bus.$emit('mainSaveSystemAjax',"add");
                        } else {
                          /*_self.$alert('验证有误，请检查填写信息！', '验证提示', {
                            confirmButtonText: '确定',
                            callback: function callback(action) {
                            }
                          });*/
                        	 _self.dialogVisibled=true;
                          return false;
                        }
                      });
                    }
                  });
                  
                  bus.$on('changeNumber',function(meg){
                    if(meg){
                    	_self.rules.systemProServices[0].required = false;
                    	_self.rules.systemKeyFkNationalIsProducts[0].required = false;
                    	_self.rules.systemKeyNUseProbability[0].required = false;
                    	//_self.$refs.formData.validate(function (valid) {});
                    }else{
                    	_self.rules.systemProServices[0].required = true;
                    	_self.rules.systemKeyFkNationalIsProducts[0].required = true;
                    	_self.rules.systemKeyNUseProbability[0].required = true;
                    }
                  });
                  
//                  bus.$on('aaa',function(meg){
//                    if(meg!=null){
//                      _self.$refs['formData'].validate(function (valid) {
//                        
//                      });
//                    }
//                  });
									//获取数组信息
                  this.headleArrayMethod(this);
                  setTimeout(function(){
                    _self.loading=false;
                  },1);
                  
                  bus.$on('returnIndexPage',function(meg){

                  	var beginContent = _self.beginContent;
                  	var currentContent = _self.formData;
                  	if(beginContent.fkInfoSysTypeCon != currentContent.fkInfoSysTypeCon){//信息系统建设类型
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.fkSystemIsMerge != currentContent.fkSystemIsMerge){//是否为合并系统
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.systemName != currentContent.systemName){//系统名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.standardizedCode != currentContent.standardizedCode){//标准化代码
                  		_self.returnIndexFlag = false;
                  	}
                  	//子系统
                  	if(currentContent.addSystemSub!=null){//当前页面有子系统
                  		if(beginContent.addSystemSub == null){//页面开始没有
                  			_self.returnIndexFlag = false;
                  		}
                  	}
                  	if(beginContent.addSystemSub!=null){//页面开始有子系统
                  		if(currentContent.addSystemSub == null){//当前页面没有
                  			_self.returnIndexFlag = false;
                  		}
                  	}
                  	if(currentContent.addSystemSub!=null && beginContent.addSystemSub == null){//当前页面与开始页面都有子系统
                  		for(var i=0;i<currentContent.addSystemSub.length;i++){
                  			if(beginContent.addSystemSub[i].label != currentContent.addSystemSub[i].label){
                  				_self.returnIndexFlag = false;
                  			}
                  			if(beginContent.addSystemSub[i].labelCode != currentContent.addSystemSub[i].labelCode){
                  				_self.returnIndexFlag = false;
                  			}
                  			if(beginContent.addSystemSub[i].systemName != currentContent.addSystemSub[i].systemName){//子系统名称
                  				_self.returnIndexFlag = false;
                  			}
                  			if(beginContent.addSystemSub[i].standardizedCode != currentContent.addSystemSub[i].standardizedCode){//子系统标准化代码
                  				_self.returnIndexFlag = false;
                  			}
                  		}
                  	}
                  	if(beginContent.gradeRecordSysName != currentContent.gradeRecordSysName){//等保备案系统名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.appIsInternet != currentContent.appIsInternet){//是否为互联网应用
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.sysBusSituationType != currentContent.sysBusSituationType){//业务类型
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.sysBusDescription != currentContent.sysBusDescription){//业务描述
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.sysServiceSitScope != currentContent.sysServiceSitScope){//服务范围
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.sysServiceSitObject != currentContent.sysServiceSitObject){//服务对象
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.npCoverageRange != currentContent.npCoverageRange){//覆盖范围
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.npNetworkProperties != currentContent.npNetworkProperties){//网络性质
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.interconnectionSit != currentContent.interconnectionSit){//系统互联情况
                  		_self.returnIndexFlag = false;
                  	}
                  	//关键产品使用情况
                  	for(var i=0;i<currentContent.systemKeyProducts.length;i++){
                  		if(beginContent.systemKeyProducts[i].fkNationalIsProducts != currentContent.systemKeyProducts[i].fkNationalIsProducts){//是否使用国产品
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemKeyProducts[i].fkExaminStatus != currentContent.systemKeyProducts[i].fkExaminStatus){//数字1234，对应产品类型，
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemKeyProducts[i].productsNumber != currentContent.systemKeyProducts[i].productsNumber){//数量
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemKeyProducts[i].nUseProbability != currentContent.systemKeyProducts[i].nUseProbability){//使用国产品率
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemKeyProducts[i].otherName != currentContent.systemKeyProducts[i].otherName){//其他
                  			_self.returnIndexFlag = false;
                  		}
                  	}
                  	//系统采用服务情况
                  	for(var i=0;i<currentContent.systemUseServices.length;i++){
                  		if(beginContent.systemUseServices[i].fkResponsibleType != currentContent.systemUseServices[i].fkResponsibleType){//服务责任方类型
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemUseServices[i].fkProductsType != currentContent.systemUseServices[i].fkProductsType){//产品类型
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemUseServices[i].fkSystemId != currentContent.systemUseServices[i].fkSystemId){
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemUseServices[i].serviceIsUse != currentContent.systemUseServices[i].serviceIsUse){//是否采用
                  			_self.returnIndexFlag = false;
                  		}
                  		if(beginContent.systemUseServices[i].otherName != currentContent.systemUseServices[i].otherName){//其他
                  			_self.returnIndexFlag = false;
                  		}
                  	}	
                  	if(beginContent.companyName != currentContent.companyName){//所属单位名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.whenInvestmentUse != currentContent.whenInvestmentUse){//何时投入使用
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.executiveOfficeName != currentContent.executiveOfficeName){//主管处室名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.subIsSystem != currentContent.subIsSystem){//系统是否为分系统
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.fatherSystemName != currentContent.fatherSystemName){//上级系统名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.fatherCompanyName != currentContent.fatherCompanyName){//请选择上级系统所属单位名称
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.executiveDireCon != currentContent.executiveDireCon){//主管联系人
                  		_self.returnIndexFlag = false;
                  	}
                  	if(beginContent.executiveDireConTel != currentContent.executiveDireConTel){//主管联系人电话
                  		_self.returnIndexFlag = false;
                  	}
                  });
                }
            })
        })
    })
}())
