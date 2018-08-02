/**
 * Created by timha on 2018/5/29.
 */
var data1={
	dialogShow:2,
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
      systemIds:[],
    	rowOne:null,//列表表头第一行的tr
	    imgList:null,//列表表头第一行的排序箭头
	    result:{},
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
        	//点击“模板导出”显示弹窗
           systemInfoExport:function(){
         	   this.dialogShow=1;  
         	   bus.$emit("dialog",this.dialogShow); 
            },
        	 closes:function () {
               	this.$refs['systemForm.formData'].resetFields();
                 var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
                 evaluationAlert.style.display="none";
                 $(".inquiry").css("display","none");
                 $(".dialogShaw").css("display","none");
               },
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
            _self.systemForm.result = responseData;
         },
         //系统信息列表排序
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
                 case 0://系统名称
                   data1.systemForm.result.data.sort(function (a, b) {
                     return (a.systemName.localeCompare(b.systemName)) * flagOne
                   });
                   break;
                 case 1://业务类型
                   data1.systemForm.result.data.sort(function (a, b) {
                     return (a.sysBusDescription.localeCompare(b.sysBusDescription)) * flagOne
                   });
                   break;
                 case 2://业务描述
                   data1.systemForm.result.data.sort(function (a, b) {
                     return (a.sysBusSituationType.localeCompare(b.sysBusSituationType)) * flagOne
                   });
                   break;
                 case 3://投入使用时间
                   data1.systemForm.result.data.sort(function (a, b) {
                  	 return (new Date(a.whenInvestmentUse.split('-').join('/')).getTime()-new Date(b.whenInvestmentUse.split('-').join('/')).getTime()) * flagOne
                   });
                   break;
               }
             };
           }
         },
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