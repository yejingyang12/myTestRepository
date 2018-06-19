var data = {
    companyForm:{
      pagesize:'',
      currentPage:'',
      total:'',
      totalPages:'',
      formData:{
        companyId:'',
        companyName:'',
        fkCompanyType:'',
        ldContactName:''
      },
      queryData:{
        companyName:'',
        ldContactName:'',
        currentPage:''
      },
      companyIds:[],
      txt:''
    }
};
(function () {
  
  Vue.component('mainCompanyInfo',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/mainCompanyInfo/mainCompanyInfo.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          //上一页下一页点击事件
          clickPage: function (page) {
            if (page <= 0) {
              //alert("当前页面已经是第一页")
            } else if (page > this.companyForm.totalPages) {
              //alert("当前页面已经是最后一页")
            } else {
              this.companyForm.queryData.currentPage = page;
              this.queryCompanyListInfoMethod();
            }
          },
          hpageNum:function(_this){
            var a=this.companyForm.txt;
            if(a<=0||a>this.companyForm.totalPages){
              this.$message({
                message: '请输入正确页数',
                type: 'warning'
              });
            }else{
              this.companyForm.queryData.currentPage = a;
              this.queryCompanyListInfoMethod();
            }
          },
          checkboxAllMethod:function(e){
            if($("#checkboxAll").is(':checked')){
              $(".firstChecked input").prop("checked",true);
            }else{
              $(".firstChecked input").removeAttr("checked");
            }
          },
          
          deleteCompanyInfoMethod:function(){
            $(".firstChecked input").removeAttr("checked");
            ajaxMethod(this, 'post',
                'company/deleteCompany', true,
                '{"companyIds":'+JSON.stringify(this.companyForm.companyIds)+'}', 'json',
                'application/json;charset=UTF-8',
                this.deleteClickSuccessMethod);
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
              this.companyForm.companyIds.push(id);
            }else{
              var ids = [];
              for(d in this.companyForm.companyIds){
                if(this.companyForm.companyIds[d]==id){}else{
                  ids.push(this.companyForm.companyIds[d]);
                }
              }
              this.companyForm.companyIds=ids;
            }
          },
          queryCompanyListInfoMethod:function() {
            this.getCompanyListInfoMethod(this,this.companyForm.queryData);
         },
          
          getCompanyListInfoMethod:function(_self,data) {
            ajaxMethod(_self, 'post',
                'company/queryCompanyList', true,
                JSON.stringify(data), 'json',
                'application/json;charset=UTF-8',
                _self.getCompanyListInfoSuccessMethod);
         },
         // 获取成功
         getCompanyListInfoSuccessMethod : function(_self, responseData) {
            _self.companyForm.formData = responseData.data;
            _self.companyForm.totalPages = responseData.totalPages;
            _self.companyForm.pagesize = responseData.pagesize;
            _self.companyForm.currentPage = responseData.currentPage;
            _self.companyForm.total = responseData.total;
         },
         handleClick:function(id){
           window.location.href = "/page/changeUnitInformationPage?companyId="+id;
         },
         newCompanyInfoMethod:function(){
           window.location.href = "/page/newUnitInformationPage";
         },
         //删除
         deleteClick:function(id){
           var _self=this;
           _self.$alert('确定删除此单位信息？', '删除', {
             confirmButtonText: '确定',
             callback: function callback(action) {
               ajaxMethod(_self, 'post',
                   'company/deleteCompany', true,
                   '{"companyIds":["'+id+'"]}', 'json',
                   'application/json;charset=UTF-8',
                   _self.deleteClickSuccessMethod);
             }
           });
           
         },
         deleteClickSuccessMethod:function(_self, responseData) {
           this.$message({
             message: '删除成功！',
             type: 'success'
           });
           _self.getCompanyListInfoMethod(_self,{});
         },
         //清空
         clearHeadle:function(){
           this.companyForm.queryData.companyName = '';
           this.companyForm.queryData.ldContactName = '';
         }
        },
        created: function() {
          this.getCompanyListInfoMethod(this,{});
        },
        mounted: function() {
          // this.selectChange()
        }
      })
    })
  })
}())