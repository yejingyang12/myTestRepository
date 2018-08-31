(function () {
  var data={
  pieShow:false,
	hSystemType:1,	  
    val1:"",
    val3:"",
    dom:null,
    myChart:null,
    option :null,
    option :{
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        left:'-6',
        top:'10',
        orient: 'vertical',
//        x: 'left',
        data: ['一级', '二级', '三级', '四级', '五级']
      },
      series : [
        {
          name: '等级分布',
          type: 'pie',
          radius : '55%',
          center: ['60%', '50%'],
          radius: ['20%', '35%'],
          label: {
            /*position:'center',*/
            normal: {
              formatter: '{col|{b}}{col|{d}%}',
              rich: {
                col: {
                  color: '#000',
                  fontSize : 10,
                }
              }
            }
          },
          data:[
            {value:'0', name:'一级'},
            {value:'0', name:'二级'},
            {value:'0', name:'三级'},
            {value:'0', name:'四级'},
            {value:'0', name:'五级'}
          ]
        }
      ],
      color: ['#ff9933','#0065ba','#028bff','#12cbf6','#50e3c2']
    },
    queryDataparmars: {},//关联图表查询条件
  };
  
  Vue.component('chartPie',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartPie/chartPie.html').then(function(res){
      resolve({ 
        template:res,
        data:function () {
          return data;
        }, 
        methods:{
        	// 获取系统等保等级分布统计图数据
        	getGradingStatistics : function(_self) {
        		_self.queryDataparmars.systemType = "1";
          	ajaxMethod(_self, 'post',
              'main/queryGradingStatistics', false,
              JSON.stringify(_self.queryDataparmars), 'json',
              'application/json;charset=UTF-8',
              this.getGradingStatisticsSuccess);
          } ,
	        getGradingStatisticsSuccess : function(_self,result){
	        	 this.option.series[0].data[0].value = 0;
		         this.option.series[0].data[1].value = 0;
		         this.option.series[0].data[2].value = 0;
		         this.option.series[0].data[3].value = 0;
		         this.option.series[0].data[4].value = 0;
		         if(result.data != null && result.data !=''){
		        	 for(var i = 0; i < result.data.length; i++){
		        		 if(result.data[i].spRanklevelName == '一级'){
		        			 this.option.series[0].data[0].value = result.data[i].spRanklevelCount;
		 	        	 }
		 	        	 if(result.data[i].spRanklevelName == '二级'){
		 	        		this.option.series[0].data[1].value = result.data[i].spRanklevelCount;
		 	        	 }
		 	        	 if(result.data[i].spRanklevelName == '三级'){
		 	        		this.option.series[0].data[2].value = result.data[i].spRanklevelCount;
		 	        	 }
		 	        	 if(result.data[i].spRanklevelName == '四级'){
		 	        		this.option.series[0].data[3].value = result.data[i].spRanklevelCount;
		 	        	 }
		 	        	 if(result.data[i].spRanklevelName == '五级'){
		 	        		this.option.series[0].data[4].value = result.data[i].spRanklevelCount;
		 	        	 }
//		        		this.option.series[0].data[i].value = result.data[i].spRanklevelCount;
//		          	this.option.series[0].data[i].name = result.data[i].spRanklevelName;	
//		          	this.option.legend.data[i] = result.data[i].spRanklevelName;
		           }
		        	 _self.pieShow=true;
		         }else{
		        	 _self.pieShow=false;
		         }
	        },
        },
        created: function() { 
          //获取系统等保等级分布统计图数据
          this.getGradingStatistics(this);
        },
        mounted: function() {  
        	data.dom = document.getElementById("container-pie");
        	data.myChart = echarts.init(data.dom);
          var myChart = data.myChart;
        	/*console.log(data.dom)*/
        	var _self = this;
        	//获取查询中的条件
      	  bus.$on("queryDataParams",function(data){
      	  	_self.queryDataparmars = JSON.parse(data);
						ajaxMethod(_self, 'post',
	              'main/queryGradingStatistics', false,
	              JSON.stringify(_self.queryDataparmars), 'json',
	              'application/json;charset=UTF-8',
	              function(_self,result){
		       		 if(result.data != null && result.data !=''){
		      		 	 _self.option.series[0].data[0].value = 0;
		      			 _self.option.series[0].data[1].value = 0;
		      			 _self.option.series[0].data[2].value = 0;
		      			 _self.option.series[0].data[3].value = 0;
		      			 _self.option.series[0].data[4].value = 0;
		      			 
		  	         for(var i = 0; i < result.data.length; i++){
			        		 //赋值
		  	        	 if(result.data[i].spRanklevelName == '一级'){
		  	        		 _self.option.series[0].data[0].value = result.data[i].spRanklevelCount;
		  	        	 }
		  	        	 if(result.data[i].spRanklevelName == '二级'){
		  	        		 _self.option.series[0].data[1].value = result.data[i].spRanklevelCount;
		  	        	 }
		  	        	 if(result.data[i].spRanklevelName == '三级'){
		  	        		 _self.option.series[0].data[2].value = result.data[i].spRanklevelCount;
		  	        	 }
		  	        	 if(result.data[i].spRanklevelName == '四级'){
		  	        		 _self.option.series[0].data[3].value = result.data[i].spRanklevelCount;
		  	        	 }
		  	        	 if(result.data[i].spRanklevelName == '五级'){
		  	        		 _self.option.series[0].data[4].value = result.data[i].spRanklevelCount;
		  	        	 }
//			        		 _self.option.series[0].data.push({value:'', name:''})
//			        		 
//			        		 _self.option.series[0].data[i].name = result.data[i].spRanklevelName;
//			        		 _self.option.legend.data[i] = result.data[i].spRanklevelName;
		  	         }
		  	        	 //重绘
		  	        	  $('#container-pie').css('display','block');
		  	        	 myChart.setOption(_self.option, true);
		      		 	}else{
		        			  $('#container-pie').css('display','none');
		        			 /*_self.option.series[0].data[0].value = 0;
		        			 _self.option.series[0].data[1].value = 0;
		        			 _self.option.series[0].data[2].value = 0;
		        			 _self.option.series[0].data[3].value = 0;
		        			 _self.option.series[0].data[4].value = 0;
		        			 data.myChart.setOption(data.option, true);*/
		      		 	}
			        });
					});
      	  
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 if(_self.pieShow){
            		 $('#container-pie').css('display','block');
            	 }else{
            		 $('#container-pie').css('display','none');
            	 }
            	 bus.$on("gradingStatisticsEnd",function(meg){
            		 var queryDataParamsTemp = JSON.parse(meg);
            		 if(queryDataParamsTemp.gradingBeginTime){
            			 _self.queryDataparmars.gradingBeginTime = queryDataParamsTemp.gradingBeginTime;
            		 }
            		 if(queryDataParamsTemp.gradingEndTime){
            			 _self.queryDataparmars.gradingEndTime = queryDataParamsTemp.gradingEndTime;
            		 }
            		 if(queryDataParamsTemp.systemType){
            			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
            		 }else{
            			 _self.queryDataparmars.systemType = '';
            		 }
            		 
            		 var a=JSON.parse(meg) 
            		 _self.hSystemType=a.systemType;  
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                 JSON.stringify(_self.queryDataparmars), 'json',
                 'application/json;charset=UTF-8',
                 function(_self,result){
	            		 if(result.data != null && result.data !=''){
	            		 	 _self.option.series[0].data[0].value = 0;
	            			 _self.option.series[0].data[1].value = 0;
	            			 _self.option.series[0].data[2].value = 0;
	            			 _self.option.series[0].data[3].value = 0;
	            			 _self.option.series[0].data[4].value = 0;
	            			 
	  	    	         for(var i = 0; i < result.data.length; i++){
		    	        		 //赋值
	  	    	        	 if(result.data[i].spRanklevelName == '一级'){
	  	    	        		 _self.option.series[0].data[0].value = result.data[i].spRanklevelCount;
	  	    	        	 }
	  	    	        	 if(result.data[i].spRanklevelName == '二级'){
	  	    	        		 _self.option.series[0].data[1].value = result.data[i].spRanklevelCount;
	  	    	        	 }
	  	    	        	 if(result.data[i].spRanklevelName == '三级'){
	  	    	        		 _self.option.series[0].data[2].value = result.data[i].spRanklevelCount;
	  	    	        	 }
	  	    	        	 if(result.data[i].spRanklevelName == '四级'){
	  	    	        		 _self.option.series[0].data[3].value = result.data[i].spRanklevelCount;
	  	    	        	 }
	  	    	        	 if(result.data[i].spRanklevelName == '五级'){
	  	    	        		 _self.option.series[0].data[4].value = result.data[i].spRanklevelCount;
	  	    	        	 }
//		    	        		 _self.option.series[0].data.push({value:'', name:''})
//		    	        		 
//		    	        		 _self.option.series[0].data[i].name = result.data[i].spRanklevelName;
//		    	        		 _self.option.legend.data[i] = result.data[i].spRanklevelName;
	  	    	         }
	  	    	        	 //重绘
	  	    	        	  $('#container-pie').css('display','block');
	  	    	        	 data.myChart.setOption(data.option, true);
	            		 	}else{
	              			  $('#container-pie').css('display','none');
	              			 /*_self.option.series[0].data[0].value = 0;
	              			 _self.option.series[0].data[1].value = 0;
	              			 _self.option.series[0].data[2].value = 0;
	              			 _self.option.series[0].data[3].value = 0;
	              			 _self.option.series[0].data[4].value = 0;
	              			 data.myChart.setOption(data.option, true);*/
	            		 	}
            	 	 });
            	 }); 
            	 bus.$on("gradingStatisticsBegin",function(meg){ 
            		 var queryDataParamsTemp = JSON.parse(meg);
            		 if(queryDataParamsTemp.gradingBeginTime){
            			 _self.queryDataparmars.gradingBeginTime = queryDataParamsTemp.gradingBeginTime;
            		 }
            		 if(queryDataParamsTemp.gradingEndTime){
            			 _self.queryDataparmars.gradingEndTime = queryDataParamsTemp.gradingEndTime;
            		 }
            		 if(queryDataParamsTemp.systemType){
            			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
            		 }else{
            			 _self.queryDataparmars.systemType = '';
            		 }
              	 ajaxMethod(_self, 'post',
                   'main/queryGradingStatistics', false,
                   JSON.stringify(_self.queryDataparmars), 'json',
                   'application/json;charset=UTF-8',
                   function(_self,result){
              		 if(result.data != null && result.data !=''){
              			 _self.option.series[0].data=[];
  	    	        	 for(var i = 0; i < result.data.length; i++){
  	    	        		 //赋值
  	    	        		 _self.option.series[0].data.push({value:'', name:''})
  	    	        		 _self.option.series[0].data[i].value = result.data[i].spRanklevelCount;
  	    	        		 _self.option.series[0].data[i].name = result.data[i].spRanklevelName;	
  	    	        		 _self.option.legend.data[i] = result.data[i].spRanklevelName;
  	    	        	 }
  	    	        	 //重绘
  	    	        	  $('#container-pie').css('display','block');
  	    	        	 data.myChart.setOption(data.option, true);
              		 }else{
              			  $('#container-pie').css('display','none');
              			 /*_self.option.series[0].data=[];
              			 data.myChart.setOption(data.option, true);*/
              		 }
              	 	 });
               });  
          }
             bus.$on("pie",function(val1,val3){ 
            	 data.val1=val1;
            	 data.val3=val3; 
             });
             var that=this; 
          data.myChart.on('click', function (params) {  
        	 var sprankLevel ="",
        	     gradingBeginTimeStamp="",
        	     gradingEndTimeStamp="",
        	     systemType="",
        	     gradingShapeType="";    
              if(params.name== "一级"){ 
            	  sprankLevel="301"; 
	             }else if(params.name== "二级"){
	            	 sprankLevel="302";
	             }else if(params.name== "三级"){
	            	 sprankLevel="303";
	             }else if(params.name== "四级"){
	            	 sprankLevel="304";
	             }else if(params.name== "五级"){
	            	 sprankLevel="305";
	             } 
              //gradingBeginTimeStamp=that.val1;
     	      gradingEndTimeStamp=that.val3;
     	      systemType=that.hSystemType;
     	      gradingShapeType="";
     	    /*  console.log(gradingBeginTimeStamp)
     	      console.log(gradingEndTimeStamp )
     	      console.log(systemType)*/
     	      gradingBeginTimeStamp = new Date("1970-01-01 00:00:00").getTime();
              if(that.val1){
                gradingBeginTimeStamp = that.val1.getTime();//时间戳
              }  
              gradingEndTimeStamp = new Date("9999-12-31 00:00:00").getTime();
              if(that.val3){
            	  gradingEndTimeStamp = that.val3.getTime();//时间戳
              }
              var params = "page/showChartDataListPage?sprankLevel="+sprankLevel
              		+"&gradingBeginTimeStamp="+gradingBeginTimeStamp+"&gradingEndTimeStamp="+gradingEndTimeStamp
              		+"&systemType="+systemType+"&gradingShapeType="+gradingShapeType+"&titleType=1";
              if(_self.queryDataparmars.systemName){
              	params = params + "&systemName=" + _self.queryDataparmars.systemName;
              }
              if(_self.queryDataparmars.appIsInternet){
              	params = params + "&appIsInternet=" + _self.queryDataparmars.appIsInternet;
              }
              if(_self.queryDataparmars.companyName){
              	params = params + "&companyName=" + _self.queryDataparmars.companyName;
              }
              if(_self.queryDataparmars.auditTimeBegin){
              	var date = null;
              	if(!(_self.queryDataparmars.auditTimeBegin instanceof Date)){
              		date = new Date(_self.queryDataparmars.auditTimeBegin);
              	}
              	params = params + "&auditTimeBeginTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.auditTimeEnd){
              	var date = null;
              	if(!(_self.queryDataparmars.auditTimeEnd instanceof Date)){
              		date = new Date(_self.queryDataparmars.auditTimeEnd);
              	}
              	params = params + "&auditTimeEndTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.acceptCompany){
              	params = params + "&acceptCompany=" + _self.queryDataparmars.acceptCompany;
              }
              if(_self.queryDataparmars.examOrg){
              	params = params + "&examOrg=" + _self.queryDataparmars.examOrg;
              }
              if(_self.queryDataparmars.recordDateBegin){
              	var date = null;
              	if(!(_self.queryDataparmars.recordDateBegin instanceof Date)){
              		date = new Date(_self.queryDataparmars.recordDateBegin);
              	}
              	params = params + "&recordDateBeginTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.recordDateEnd){
              	var date = null;
              	if(!(_self.queryDataparmars.recordDateEnd instanceof Date)){
              		date = new Date(_self.queryDataparmars.recordDateEnd);
              	}
              	params = params + "&recordDateEndTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.examTimeBegin){
              	var date = null;
              	if(!(_self.queryDataparmars.examTimeBegin instanceof Date)){
              		date = new Date(_self.queryDataparmars.examTimeBegin);
              	}
              	params = params + "&examTimeBeginTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.examTimeEnd){
              	var date = null;
              	if(!(_self.queryDataparmars.examTimeEnd instanceof Date)){
              		date = new Date(_self.queryDataparmars.examTimeEnd);
              	}
              	params = params + "&examTimeEndTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.rankTimeBegin){
              	var date = null;
              	if(!(_self.queryDataparmars.rankTimeBegin instanceof Date)){
              		date = new Date(_self.queryDataparmars.rankTimeBegin);
              	}
              	params = params + "&rankTimeBeginTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.rankTimeEnd){
              	var date = null;
              	if(!(_self.queryDataparmars.rankTimeEnd instanceof Date)){
              		date = new Date(_self.queryDataparmars.rankTimeEnd);
              	}
              	params = params + "&rankTimeEndTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.inspectionDateBegin){
              	var date = null;
              	if(!(_self.queryDataparmars.inspectionDateBegin instanceof Date)){
              		date = new Date(_self.queryDataparmars.inspectionDateBegin);
              	}
              	params = params + "&inspectionDateBeginTimeStamp=" + date.getTime();
              }
              if(_self.queryDataparmars.inspectionDateEnd){
              	var date = null;
              	if(!(_self.queryDataparmars.inspectionDateEnd instanceof Date)){
              		date = new Date(_self.queryDataparmars.inspectionDateEnd);
              	}
              	params = params + "&inspectionDateEndTimeStamp=" + date.getTime();
              }
              //传数组
              if(_self.queryDataparmars.plTypeArray){
             	 for(var i=0;i<_self.queryDataparmars.plTypeArray.length;i++){
             		 params = params + "&plTypeArray=" + _self.queryDataparmars.plTypeArray[i];
             	 }
              }
              if(_self.queryDataparmars.statusArray){
             	 for(var i=0;i<_self.queryDataparmars.statusArray.length;i++){
             		 params = params + "&statusArray=" + _self.queryDataparmars.statusArray[i];
             	 }
              }
              if(_self.queryDataparmars.sprankLevelArray){
             	 for(var i=0;i<_self.queryDataparmars.sprankLevelArray.length;i++){
             		 params = params + "&sprankLevelArray=" + _self.queryDataparmars.sprankLevelArray[i];
             	 }
              }
              if(_self.queryDataparmars.subordinateProvincesArray){
             	 for(var i=0;i<_self.queryDataparmars.subordinateProvincesArray.length;i++){
             		 params = params + "&subordinateProvincesArray=" + _self.queryDataparmars.subordinateProvincesArray[i];
             	 }
              }
              if(_self.queryDataparmars.customFiltering){
             	 params = params + "&customFiltering=" + _self.queryDataparmars.customFiltering;
              }
              
            window.location.href=originUrl+encodeURI(params);
          /*  window.location.href = "http://echarts.baidu.com/examples/editor.html";*/
         }) 
        },
      })
    })
  })
}())
