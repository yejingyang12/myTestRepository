/**
 * Created by timha on 2018/5/29.
 */
(function () {
  Vue.component('gradingNav',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/gradingNav/gradingNav.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return {
            activeName: 'third',
          }
        },
        methods:{
          returnBtn:function(){
            window.location.href = originUrl+"page/indexPage";
          },
          handleClick:function(tab, event) {
            
            /*if(this.activeName=='fourth'){
              window.location.href = originUrl+"page/applicationGradingInfoPage?systemId="+systemId;
            }*/
          },
          //下一页
          next2Btn:function() {
            ajaxMethod(this, 'post','grading/saveGrading', true,
                JSON.stringify(data.formData), 'json',
                'application/json;charset=UTF-8',
                this.nextBtnSuccessMethod);
          },
          // 获取成功
          nextBtnSuccessMethod : function(_self, responseData) {
              window.location.href = originUrl+"page/applicationGradingInfoPage?systemId="+systemId+"&companyId="+companyId;
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