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
      },
      txt:'',
      systemIds:[]
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
        //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
              //alert("当前页面已经是第一页")
            } else if (page > this.systemForm.totalPages) {
              //alert("当前页面已经是最后一页")
            } else {
              this.systemForm.queryData.currentPage = page;
              this.getSystemListInfoMethod(this,this.systemForm.queryData);
            }
          },
          hpageNum:function(_this){
            var a=this.systemForm.txt;
            if(a<=0||a>this.systemForm.totalPages){
              this.$message({
                message: '请输入正确页数',
                type: 'warning'
              });
            }else{
              this.systemForm.queryData.currentPage = a;
              this.getSystemListInfoMethod(this,this.systemForm.queryData);
            }
          },
          checkboxAllMethod:function(){
            if($("#checkboxAll1").is(':checked')){
              $(".firstChecked input").prop("checked",true);
            }else{
              $(".firstChecked input").removeAttr("checked");
            }
          },
          
          checkboxMethod:function(e,id){
            var value=10;
            $(".ids[type='checkbox']").each(function(i){
              if(!$(this).is(':checked')){
                value--;
              }
            });
            if(value==10){
              $("#checkboxAll").prop("checked",true);
            }else{
              $("#checkboxAll").removeAttr("checked");
            }
            if($(e.target).is(':checked')){
              this.systemForm.systemIds.push(id);
            }else{
              var ids = [];
              for(d in this.systemForm.systemIds){
                if(this.systemForm.systemIds[d]==id){}else{
                  ids.push(this.systemForm.systemIds[d]);
                }
              }
              this.systemForm.systemIds=ids;
            }
          },
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
            _self.systemForm.totalPages = responseData.totalPages;
            _self.systemForm.pagesize = responseData.pagesize;
            _self.systemForm.currentPage = responseData.currentPage;
            _self.systemForm.total = responseData.total;
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