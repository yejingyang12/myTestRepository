/**
 * Created by timha on 2018/5/29.
 */
(function () {
  Vue.component('materialNav',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/materialNav/materialNav.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return {
            activeName: 'fourth',
          }
        },
        methods:{
          returnBtn:function(){
            window.location.href = originUrl+"page/indexPage";
          },
          handleClick:function(tab, event) {
            
            if(this.activeName=='third'){
              window.location.href = originUrl+"page/applicationGradingPage?systemId="+systemId+"&companyId="+companyId;
            }
          }
        },
        created: function() {

        },
        mounted: function() {
          // this.selectChange()
        }
      })
    })
  })
}())