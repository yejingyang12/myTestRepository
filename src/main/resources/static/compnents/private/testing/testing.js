/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={};
  Vue.component('testing',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/testing/testing.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return {
            activeName: 'record'
          }
        },
        methods:{
          handleClick(tab, event) {
            console.log(tab, event);
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