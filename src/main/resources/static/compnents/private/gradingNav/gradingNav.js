/**
 * Created by timha on 2018/5/29.
 */
var data1 = {
    activeName: 'third',
    materialShow:false
};
(function () {
  Vue.component('gradingNav',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/gradingNav/gradingNav.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data1;
        },
        methods:{
          returnBtn:function(){
            window.location.href = originUrl+"page/indexPage";
          },
          handleClick:function(tab, event) {
            if(this.activeName=='fourth'){
              window.location.href = originUrl+"page/applicationGradingInfoPage?systemId="+systemId+"&companyId="+companyId;
            }
          },
          //下一页
          next2Btn:function(formName) {
            bus.$emit('materialFormName',formName);
          },
          // 获取成功
          nextBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationGradingInfoPage?systemId="+systemId+"&companyId="+companyId;
          }
        },
        created: function() {

        },
        mounted: function() {
          var _self = this;
          bus.$on('materialFormAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post','grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtnSuccessMethod);
            }
          });
        }
      })
    })
  })
}())