/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={
  		systemName:'',
  		activeName: 'examine',
  		materialView: false
  };
  Vue.component('tabNav',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/tabNav/tabNav.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          handleClick: function(tab, event) {
            console.log(tab, event);
            if(tab.label!='审核信息'){
            	bus.$emit('jurisdictionShow','1');
            }else{
            	bus.$emit('jurisdictionShow',null);
            }
            //获取鱼骨图
            if(tab.label=='审核信息'){
            	bus.$emit('fishbone','audit');
            }
            if(tab.label=='处理信息'){
            	bus.$emit('fishbone','');
            }
          },
          getFishbone: function(){
            bus.$emit('fishbone','audit');
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
          },
          getSystemMaterialsInfo:function(systemId){
          	var _self = this;
              ajaxMethod(_self, 'post','grading/queryEditSystemMaterialsInfo', true,
                  '{"fkSystemId":"'+systemId+'"}', 'json',
                  'application/json;charset=UTF-8',
                  _self.getMaterialsInfoSuccessMethod);
          },
          getMaterialsInfoSuccessMethod:function(_self,responseData){
          	if(responseData.data.systemMaterialsId != null){
          		data.materialView = true;
          	}
          }
        },
        created: function() {
          //获取系统信息
          this.getSystem(this);
          this.getSystemMaterialsInfo(systemId);
          this.getFishbone();
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