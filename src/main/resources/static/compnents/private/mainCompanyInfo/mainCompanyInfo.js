var data = {
		headquarters:false,
    check:false,
    showItem:10,
    companyForm:{
      pagesize:'',
      currentPage:'',
      total:'',
      totalPages:'',
      formData:[{
        companyId:'',
        companyName:'',
        fkCompanyType:'',
        ldContactName:''
      }],
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
    },
    delCompanyId:'',
    existSysCompanyNum: '',
};
(function () {
  
  Vue.component('mainCompanyInfo',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/mainCompanyInfo/mainCompanyInfo.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        }, 
        computed:{
        	totalPages:function(){
        		var page = []; //显示页码
                //用当前激活页面驱动页面显示的分别
                if (this.companyForm.currentPage < this.showItem) { //当前页小于最大页码数（showItem），区分总页数是否达到最大页码数
                    //获取总页数和最大页码数较小的值
                    var i = Math.min(this.companyForm.totalPages, this.showItem);
                    while (i) {
                        //通过page的数组值显示页码
                        page.unshift(i--);
                    }
                } else { //当前页面大于最大页码数（showItem）时，区分显示的页码规则
                    var pagestart = this.companyFormt.currentPage - Math.floor(this.showItem / 2); //获取显示的页码第一位页码（默认当前页居中）
                    var i = this.showItem; //用来显示多少（i）个页码
                    if (pagestart > (this.companyForm.totalPages - this.showItem)) { //第一个页码如果大于总页数减去展示的页码数，则当前页不能居中
                        pagestart = (this.companyForm.totalPages - this.showItem) + 1; //应该显示的第一个页码数
                    }
                    while (i--) {
                        //通过page的数组值显示页码
                        page.push(pagestart++);
                    }
                }
                return page;
            }
        	
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
            _self.companyForm.formData = responseData.data.list;
            _self.companyForm.totalPages = responseData.data.totalPage;
            _self.companyForm.pagesize = responseData.data.pageSize;
            _self.companyForm.currentPage = responseData.data.currPage;
            _self.companyForm.total = responseData.data.totalCount;
            _self.companyForm.result = responseData.data;
            
            /*var ids = [];
            for(var i=0;i<_self.companyForm.formData.length;i++){
            	var checked = false;
            	for(var j=0;j<_self.companyForm.companyIds.length;j++){
            		if(_self.companyForm.formData[i].companyId == _self.companyForm.companyIds[j]){
            			checked = true;
            			ids.push(_self.companyForm.companyIds[j]);
            			break;
            		}
            	}
            	_self.companyForm.formData[i].checked = checked;
            }
            _self.companyForm.companyIds = ids;*/
            
         },
         handleClick:function(id){
        	 if(this.headquarters){
        		 window.location.href = originUrl+encodeURI("/page/changeUnitInformationPage?jurisdiction=headquarters&companyId="+id);
        	 }else{
        		 window.location.href = originUrl+encodeURI("/page/changeUnitInformationPage?companyId="+id);
        	 }
         },
         newCompanyInfoMethod:function(){
        	 if(this.headquarters){
        		 window.location.href = originUrl+encodeURI("/page/newUnitInformationPage?jurisdiction=headquarters");
        	 }else{
        		 window.location.href = originUrl+encodeURI("/page/newUnitInformationPage");
        	 }
         },
         //点击 "取消" 关闭弹框
         closes:function () {
             $(".inquiryDelete").css("display","none");
             $(".dialogShaw").css("display","none");
           },
         //点击“删除”显示弹窗
         deleteClickDialog:function(id){
           this.delCompanyId=id;
        	 $(".inquiryDelete").css("display","block");
        	 $(".dialogShaw").css("display","block");
         },
         //点击“确认” 删除数据 并 关闭弹窗
         deleteClick:function(){
           var _self=this;
           _self.closes();
           if(_self.delCompanyId==''||_self.delCompanyId==null){
               $(".startBoxError").show().delay(2000).fadeOut();
               return ;
           }
           ajaxMethod(_self, 'post',
                   'company/deleteCompany', true,
                   '{"companyIds":["'+_self.delCompanyId+'"]}', 'json',
                   'application/json;charset=UTF-8',
                   _self.deleteClickSuccessMethod);
          },
         deleteClickSuccessMethod:function(_self, responseData) {
           _self.delCompanyId = "";
           //有单位下存在系统的数据
           if(responseData.data != '' && responseData.data != null){
          	 _self.existSysCompanyNum = responseData.data.length;
          	 var companyIds = [];
          	 for(var i=0;i<responseData.data.length;i++){
          		 companyIds.push(responseData.data.companyId);
          	 }
          	 _self.companyForm.companyIds = companyIds;
          	 $(".startBoxExistSys").show().delay(2000).fadeOut();
           }else{
          	 $(".startBoxSuccess").show().delay(2000).fadeOut();
           }
           _self.getCompanyListInfoMethod(_self,{});
         },
         //清空
         clearHeadle:function(){
           this.companyForm.queryData.companyName = '';
           this.companyForm.queryData.ldContactName = '';
           this.getCompanyListInfoMethod(this,{});
         },
         //单位信息列表排序
         listsort: function () {
           var imgArrow = data.imgList;
           var flagOne = 1;
           console.log(data.companyForm.result);
            for (var i = 0; i < imgArrow.length; i++) {
             imgArrow[i].myindex = i;
             imgArrow[i].onclick = function () {
               flagOne *= -1;
               // //对每个数组也就是对应表格的每一列进行排序
               // console.log( data.result.data[0].systemName);
               switch (this.myindex){
                 case 0://单位名称
                   data.companyForm.result.list.sort(function (a, b) {
                     return (a.companyName.localeCompare(b.companyName)) * flagOne
                   });
                   break;
                 case 1://单位类型
                   data.companyForm.result.list.sort(function (a, b) {
                     return (a.fkCompanyType.localeCompare(b.fkCompanyType)) * flagOne
                   });
                   break;
                 case 2://责任部门负责人
                   data.companyForm.result.list.sort(function (a, b) {
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
          //功能权限
          $.ajax({
            type: "get",
            url : originUrl+"/jurisdiction/queryMenuJurisdictionApi", 
            async: true,
            data: "",
            dataType: "json",
            cache: false,
            processData: false,
            contentType: false,
            success: function(response) {
            	data.headquarters = getJurisdictionMethod(response,'0102010101');
            	
            },
            error: function(err) {
            }
          });
          
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