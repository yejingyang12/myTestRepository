/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={
  		materialView:false,
  		systemName:'',
  		activeName: 'examination'
  };
  Vue.component('tabSelfCheck',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/tabSelfCheck/tabSelfCheck.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          handleClick:function(tab, event) {
            //console.log(tab, event);
          },
       // 获取系统信息
          getSystem : function(_self) {
          	ajaxMethod(this, 'post',
              'system/querySystemInformationBySystemId', true,
              '{"systemId":"'+systemId+'"}', 'json',
              'application/json;charset=UTF-8',
              this.getSystemSuccess);
          } ,
          getSystemSuccess : function(_self,result){
          	this.systemName = result.data.systemName;
          }
        },
        created: function() {
          //获取系统信息
          this.getSystem(this);
        },
        mounted: function() {
          // this.selectChange()
        	bus.$on('materialView',function(meg){
            if(meg!=null){
            	_self.materialView = meg;
            }
          });
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