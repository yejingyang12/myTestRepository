/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={
		  activeName: 'first',
  };
  Vue.component('tabMainCompanyInfo',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/tabMainCompanyInfo/tabMainCompanyInfo.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          handleClick:function(tab, event) {
//            console.log(tab, event);
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
  /*var Main = {
      data() {
          return {
              activeName: 'first'
          };
      },
      methods: {
          handleClick(tab, event) {
              console.log(tab, event);
          }
      }
  };
  var Ctor = Vue.extend(Main)
  new Ctor().$mount('#main')*/
}())