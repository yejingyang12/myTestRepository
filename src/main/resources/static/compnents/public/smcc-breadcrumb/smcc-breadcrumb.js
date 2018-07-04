(function(){
    var data ={
        breadcrumb:[
          {
            name:"首页",
            href:'#'
        },
          {
            name:"信息安全管理",
            href:'#'
        },
          {
            name:'安全检测',
            href:'#'
        },
          {
            name:"系统检测",
            href:'#'
        }
        ]
    };

    Vue.component('smccBreadcrumb',function(resolve,reject){
        $.get(comp_src+"/compnents/public/smcc-breadcrumb/smcc-breadcrumb.html").then(function (res) {
            resolve({
                template: res,
                data:function(){
                    return data;
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
              }
            })
        });
    })
}())
