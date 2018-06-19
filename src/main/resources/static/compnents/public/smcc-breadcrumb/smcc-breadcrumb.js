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
                hReturn(){
                  bus.$emit("gradReturn","b");
                  console.log(1);
                },
                submit(){
                  bus.$emit("gradSubmit","a")
                  // console.log(a);
                }
              }
            })
        });
    })
}())
