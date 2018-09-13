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
						
            //获取鱼骨图
            if(tab.label=='审核信息'){
            	bus.$emit('fishbone','audit');
            }
            if(tab.label=='处理信息'){
            	bus.$emit('fishbone','');
            }
          },
       // 获取系统信息
          getSystem : function(_self) {
          	ajaxMethod(this, 'post',
              'system/querySystemInformationBySystemId', false,
              '{"systemId":"'+systemId+'"}', 'json',
              'application/json;charset=UTF-8',
              this.getSystemSuccess);
          } ,
          getSystemSuccess : function(_self,result){
          	this.systemName = result.data.systemName;
          },
          //是否显示表4
          getSystemMaterialsInfo:function(systemId){
          	var _self = this;
              ajaxMethod(_self, 'post','grading/queryEditSystemMaterialsInfo', false,
                  '{"fkSystemId":"'+systemId+'"}', 'json',
                  'application/json;charset=UTF-8',
                  _self.getMaterialsInfoSuccessMethod);
          },
          getMaterialsInfoSuccessMethod:function(_self,responseData){
          	if(responseData.data.systemMaterialsId){
          		_self.materialView = true;
          	}
          }
        },
        created: function() {
          //获取系统信息
          this.getSystem(this);
          this.getSystemMaterialsInfo(systemId);
        },
        mounted: function() {
          // this.selectChange()
        	bus.$on('materialView',function(meg){
        		var _self = this;
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