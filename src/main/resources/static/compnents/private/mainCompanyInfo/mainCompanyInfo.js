var data = {
    check:false,
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
      txt:'',
	  	rowOne:null,//列表表头第一行的tr
	    imgList:null,//列表表头第一行的排序箭头
	    result:{},
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
            this.check = false;
            if(this.companyForm.companyIds.length>0){
              $(".firstChecked input").removeAttr("checked");
              ajaxMethod(this, 'post',
                  'company/deleteCompany', true,
                  '{"companyIds":'+JSON.stringify(this.companyForm.companyIds)+'}', 'json',
                  'application/json;charset=UTF-8',
                  this.deleteClickSuccessMethod);
            }else{
              $(".startBoxError").show().delay(2000).fadeOut();
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
            _self.companyForm.result = responseData;
         },
         handleClick:function(id){
           window.location.href = "/page/changeUnitInformationPage?companyId="+id;
         },
         newCompanyInfoMethod:function(){
           window.location.href = "/page/newUnitInformationPage";
         },
       //点击 "取消" 关闭弹框
         closes:function () {
             $(".inquiryDelete").css("display","none");
             $(".dialogShaw").css("display","none");
           },
       //点击“删除”显示弹窗
         deleteClickDialog:function(){
        	 $(".inquiryDelete").css("display","block");
        	 $(".dialogShaw").css("display","block");
         },
         //点击“确认” 删除数据 并 关闭弹窗
         deleteClick:function(id){
           var _self=this;
           ajaxMethod(_self, 'post',
                   'company/deleteCompany', true,
                   '{"companyIds":["'+id+'"]}', 'json',
                   'application/json;charset=UTF-8',
                   _self.deleteClickSuccessMethod);
           _self.closes();
          },
         deleteClickSuccessMethod:function(_self, responseData) {
           _self.getCompanyListInfoMethod(_self,{});
           $(".startBoxSuccess").show().delay(2000).fadeOut();
         },
         //清空
         clearHeadle:function(){
           this.companyForm.queryData.companyName = '';
           this.companyForm.queryData.ldContactName = '';
         },
         //单位信息列表排序
         listsort: function () {
           var imgArrow = data.imgList;
           var flagOne = 1;
           // console.log(data.result.data);
           for (var i = 0; i < imgArrow.length; i++) {
             imgArrow[i].myindex = i;
             imgArrow[i].onclick = function () {
               flagOne *= -1;
               // //对每个数组也就是对应表格的每一列进行排序
               // console.log( data.result.data[0].systemName);
               switch (this.myindex){
                 case 0://单位名称
                   data.companyForm.result.data.sort(function (a, b) {
                     return (a.companyName.localeCompare(b.companyName)) * flagOne
                   });
                   break;
                 case 1://单位类型
                   data.companyForm.result.data.sort(function (a, b) {
                     return (a.fkCompanyType.localeCompare(b.fkCompanyType)) * flagOne
                   });
                   break;
                 case 2://责任部门负责人
                   data.companyForm.result.data.sort(function (a, b) {
                     return (a.ldContactName.localeCompare(b.ldContactName)) * flagOne
                   });
                   break;
               }
             };
           }
         },
        },
        created: function() {
          this.getCompanyListInfoMethod(this,{});
        },
        mounted: function() {
          //表格排序需要获取的元素
          var rowOne=document.getElementsByClassName('rowOne')[0];
          var imgList=rowOne.getElementsByTagName('img');
          data.imgList=imgList;
        	this.listsort();
        }
      })
    })
  })
}())