/**
 * Created by timha on 2018/5/29.
 */
var data1={
    activeName: 'first',
    inputs:null,
    tr:null,
    systemForm:{
      pagesize:'',
      currentPage:'',
      total:'',
      totalPages:'',
      formData:{
        systemId:'',
        systemName:'',
        sysBusDescription:'',
        sysBusSituationType:'',
        whenInvestmentUse:'',
      },
      queryData:{
        systemName:'',
        currentPage:''
      }
    }
  };
(function () {
  
  Vue.component('mainSystemInfo',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/mainSystemInfo/mainSystemInfo.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data1;
        },
        methods:{
          querySystemListInfoMethod:function() {
            this.getSystemListInfoMethod(this,this.systemForm.queryData);
          },
          handleClick:function(id){
            window.location.href = "/page/changeSystemInformationPage?systemId="+id;
          },
          newSystemInfoMethod:function(){
            window.location.href = "/page/newSystemInformationPage";
          },
          //清空
          clearHeadle:function(){
            this.systemForm.queryData.systemName = '';
          },

          jinyong:function(){
            // for (var i = 0; i < data.inputs.length; i++) {
            //   console.log(data.inputs);
            //
            // }


          },
          getSystemListInfoMethod:function(_self,data) {
            ajaxMethod(_self, 'post',
                'system/querySystemList', true,
                JSON.stringify(data), 'json',
                'application/json;charset=UTF-8',
                _self.getSystemListInfoSuccessMethod);
         },
         // 获取成功
         getSystemListInfoSuccessMethod : function(_self, responseData) {
            _self.systemForm.formData = responseData.data;
         }
        },
        created: function() {
          this.getSystemListInfoMethod(this,{});
        },
        mounted: function() {
          var tr=document.getElementsByTagName('tr');
          var inputs=document.getElementsByClassName('checkName');
          data.tr=tr;
          data.inputs=inputs;
           this.jinyong()
        }
      })
    })
  })

}())