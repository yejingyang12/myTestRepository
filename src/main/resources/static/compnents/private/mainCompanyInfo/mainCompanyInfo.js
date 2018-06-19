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
      }
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