(function(){
    var data ={
        str:"http://cpro.smcc.sinopec.com:8082",		
        breadcrumb:[
          {
            name:"首页",
            href:'http://cpro.smcc.sinopec.com:8082//page/indexPage'
        },
          {
            name:"信息安全管理",
            href:'http://cpro.smcc.sinopec.com:8082//page/indexPage'
        },
          {
            name:'等级保护',
            href:'http://cpro.smcc.sinopec.com:8082//page/indexPage'
        },
        ]
    }; 
    Vue.component('smccBreadcrumb',function(resolve,reject){
        $.get(comp_src+"/compnents/public/smcc-breadcrumb/smcc-breadcrumb.html").then(function (res) {
            resolve({
                template: res,
                data:function(){
                    return data;
                },
                created:function(){
                	this.str=window.location.href;
                	console.log(this.str); 
                },
              methods:{ 
            	  submitForm:function(formName) {
    			      this.$refs[formName].validate(function(valid){
    			          if (valid) {
    			            alert('submit!');
    			          } else {
    			            console.log('error submit!!');
    			            return false;
    			          }
    			        });
    			    
    			     },
                hReturn:function(){
                  bus.$emit("gradReturn","b");
                },
                submit:function(ruleForm){
                  bus.$emit("gradSubmit",ruleForm);
                  // console.log(a);
                },
                returnHome:function(){
                  window.location.href = originUrl+"page/auditPage";
                }
              },
              mounted:function(){  
            		   var str = this.str;  
             		  if(str.match(RegExp(/indexPage/))||!str.match(RegExp(/page/))){//首页 
             			  /*console.log(1);*/
            			  this.breadcrumb.push({ name:"系统定级备案测评列表", href:'http://cpro.smcc.sinopec.com:8082//page/indexPage' }) 
            		  }else if(str.match( RegExp(/companyRecordPage/))){//备案
            			  this.breadcrumb.push({  name:"备案", href:'#' })
            		  }else if(str.match( RegExp(/selfCheckPage/))){//自查
            			  this.breadcrumb.push({ name:"自查", href:'#' })
              		  }else if(str.match( RegExp(/testingPage/))){//测评 
            			  this.breadcrumb.push({ name:"测评 ", href:'#' })
              		  }else if(str.match( RegExp(/viewDetailsPage/))){//详情 
            			  this.breadcrumb.push({ name:"详情 ", href:'#' })
              		  }else if(str.match( RegExp(/auditDetailsPage/))){//审核详情
            			  this.breadcrumb.push({ name:"详情", href:'#'})
              		  }else if(str.match( RegExp(/auditPage/))){//审核管理页面
            			  this.breadcrumb.push({ name:"审核管理", href:'#' })
              		  }else if(str.match( RegExp(/auditGradPage/))){//审核管理 子页面
            			  this.breadcrumb.push(
            					  { name:"审核管理", href:'http://cpro.smcc.sinopec.com:8082/page/auditPage'},
            					  { name:"审核", href:'#'})
              		  }else if(str.match( RegExp(/mainCompanyInfoPage/))){//维护单位系统信息
            			  this.breadcrumb.push({ name:"维护单位系统信息", href:'http://cpro.smcc.sinopec.com:8082/page/mainCompanyInfoPage'})
              		  }else if(str.match( RegExp(/changeUnitInformationPage/))){//维护单位--修改单位信息
            			  this.breadcrumb.push(
            					  {name:"维护单位系统信息",href:'http://cpro.smcc.sinopec.com:8082/page/mainCompanyInfoPage'  },
			            		  {name:"修改单位信息",href:'#'})
              		  }else if(str.match( RegExp(/newUnitInformationPage/))){//维护单位--新建单位信息
            			  this.breadcrumb.push(
            					  { name:"维护单位系统信息",href:'http://cpro.smcc.sinopec.com:8082/page/mainCompanyInfoPage'},
          			              { name:"新建单位信息", href:'#' })
              		  }else if(str.match( RegExp(/newSystemInformationPage/))){//维护单位--新建系统信息
            			  this.breadcrumb.push(
            					  { name:"维护单位系统信息",href:'http://cpro.smcc.sinopec.com:8082/page/mainCompanyInfoPage'},
            					  { name:"新建系统信息", href:'#' })
              		  }else if(str.match( RegExp(/changeSystemInformationPage/))){//维护单位--修改系统信息
            			  this.breadcrumb.push(
            					  { name:"维护单位系统信息",href:'http://cpro.smcc.sinopec.com:8082/page/mainCompanyInfoPage'},
            					  { name:"修改系统信息", href:'#' })
              		  }else if(str.match( RegExp(/addCompanyInfoPage/))){//新建单位信息填写
            			  this.breadcrumb.push({ name:"新建单位信息", href:'#' })
              		  }else if(str.match( RegExp(/addCompanySystemPage/))){//新建系统信息填写
            			  this.breadcrumb.push({ name:"新建系统信息", href:'#' })
              		  }else if(str.match( RegExp(/addCompanyGradPage/))){//新建定级信息填写
            			  this.breadcrumb.push({ name:"新建定级信息", href:'#'})
              		  }else if(str.match( RegExp(/addCompanyMaterialPage/))){//新建材料信息填写
            			  this.breadcrumb.push({ name:"新建材料信息", href:'#'})
              		  } else if(str.match( RegExp(/applicationChangePage/))){//申请变更单位信息填写
            			  this.breadcrumb.push({ name:"申请变更单位信息", href:'#'})
              		  }else if(str.match( RegExp(/applicatuibChangSystemPage/))){//申请变更系统信息填写
            			  this.breadcrumb.push({ name:"申请变更系统信息", href:'#'})
              		  }else if(str.match( RegExp(/applicationChangeGradPage/))){//申请变更定级信息填写
            			  this.breadcrumb.push({ name:"申请变更定级信息", href:'#'})
              		  }else if(str.match( RegExp(/applicationChangeMaterialPage/))){//申请变更材料信息填写
            			  this.breadcrumb.push({ name:"申请变更材料信息", href:'#'})
              		  } else if(str.match( RegExp(/applicationGradingPage/))){//申请定级信息
            			  this.breadcrumb.push({ name:"申请定级信息", href:'#'})
              		  }else if(str.match( RegExp(/applicationGradingInfoPage/))){//填写材料信息
            			  this.breadcrumb.push({ name:"填写材料信息", href:'#'})
              		  } 
              } 
            })
        });
    })
}())
