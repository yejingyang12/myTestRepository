(function () {
  var data={
  barShow:false,
	hgradingShapeType:"",	  
	hSystemType:1,
	val1:"",
    val3:"",
    dom:null,
    myChart:null,
    option :null,
    option:{
        backgroundColor :'#fff', 
        tooltip : {
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['一级', '二级', '三级', '四级', '五级'],
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#ededed'//左边线的颜色
                    },
                },
                axisLabel: {
                	rotate:50,
                    textStyle: {
                       color: '#000',
                       fontSize: 12
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                minInterval : 1,
                splitLine: {lineStyle:{color:'#ededed'}},//网格线颜色
                axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#ededed'//左边线的颜色
                    },
                },
                axisLabel: {
                    textStyle: {
                       color: '#000',
                       fontSize: 12
                    }
                }
            }
        ],
        series : [
            {
                name:'等保级别',
                type:'bar',
                barWidth: '60%',
                data:[0, 0, 0, 0, 0],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#8dc7f7'
                        }, {
                            offset: 1,
                            color: '#3e95df'
                        }]),
                    }
                }
            }
        ]
    },
    queryDataparmars: {},//关联图表查询条件
  };
  
  Vue.component('chartBar',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartBar/chartBar.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
	      	// 不同等保级别系统在不同管理状态下详情
	      	getGradingShape : function(_self) {
	      		_self.queryDataparmars.systemType = "1";
	        	ajaxMethod(_self, 'post',
	            'main/queryGradingStatistics', false,
	            JSON.stringify(_self.queryDataparmars), 'json',
	            'application/json;charset=UTF-8',
	            this.getGradingShapeSuccess);
	        } ,
	        getGradingShapeSuccess : function(_self,result){
	        	 _self.option.series[0].data = ([0,0,0,0,0]);
	        	if(result.data != null && result.data !=''){
	        		for(var i = 0; i < result.data.length; i++){
	        			if(result.data[i].spRanklevelName == '一级'){
	        				this.option.series[0].data[0] = result.data[i].spRanklevelCount;
	        			}
	        			if(result.data[i].spRanklevelName == '二级'){
	        				this.option.series[0].data[1] = result.data[i].spRanklevelCount; 
	        			}
	        			if(result.data[i].spRanklevelName == '三级'){
	        				this.option.series[0].data[2] = result.data[i].spRanklevelCount;
	        			}
	        			if(result.data[i].spRanklevelName == '四级'){
	        				this.option.series[0].data[3] = result.data[i].spRanklevelCount;
	        			}
	        			if(result.data[i].spRanklevelName == '五级'){
	        				this.option.series[0].data[4] = result.data[i].spRanklevelCount;
	        			}
//	        		this.option.series[0].data[i] = result.data[i].spRanklevelCount;
//	          	this.option.xAxis[0].data[i] = result.data[i].spRanklevelName;	
	        		}
	        		_self.barShow=true;
	        	}else{
	        		_self.barShow=false;
	        	}
	        },
        },
        created: function() {
        	//不同等保级别系统在不同管理状态下详情
        	this.getGradingShape(this);
        },
        mounted: function() {
          data.dom = document.getElementById("container-bar");
          data.myChart = echarts.init(data.dom);
          var myChart = data.myChart;
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
	       			 _self.option.series[0].data = ([0,0,0,0,0]);
	 	        	 for(var i = 0; i < result.data.length; i++){
	 	        		 //赋值
	                if(result.data[i].spRanklevelName == '一级'){
	                 _self.option.series[0].data[0] = result.data[i].spRanklevelCount;
	                }
	 	             if(result.data[i].spRanklevelName == '二级'){
	 	              _self.option.series[0].data[1] = result.data[i].spRanklevelCount; 
	 	             }
	 	             if(result.data[i].spRanklevelName == '三级'){
	 	              _self.option.series[0].data[2] = result.data[i].spRanklevelCount;
	 	             }
	 	             if(result.data[i].spRanklevelName == '四级'){
	 	              _self.option.series[0].data[3] = result.data[i].spRanklevelCount;
	 	             }
	 	             if(result.data[i].spRanklevelName == '五级'){
	 	              _self.option.series[0].data[4] = result.data[i].spRanklevelCount;
	 	             }
	 	        	 }
	   	         //重绘
	   	         $('#container-bar').css('display','block');
	   	         myChart.setOption(_self.option, true);
	       		 }else{
	       				$('#container-bar').css('display','none');
	        		 		/*_self.option.series[0].data = ([0,0,0,0,0]);
	        		 		data.myChart.setOption(data.option, true);*/
	       		 }
	 	        });
          });
          
           if (data.option && typeof data.option === "object") {
          	 data.myChart.setOption(data.option, true);
          	 if(_self.barShow){
          		 $('#container-bar').css('display','block');
          	 }else{
          		 $('#container-bar').css('display','none');
          	 }
          	 bus.$on("gradingShapeEnd",function(meg){
          		 var queryDataParamsTemp = JSON.parse(meg);
          		 if(queryDataParamsTemp.gradingBeginTime){
          			 _self.queryDataparmars.gradingBeginTime = queryDataParamsTemp.gradingBeginTime;
          		 }
          		 if(queryDataParamsTemp.gradingEndTime){
          			 _self.queryDataparmars.gradingEndTime = queryDataParamsTemp.gradingEndTime;
          		 }
          		 if(queryDataParamsTemp.gradingShapeType){
          			 _self.queryDataparmars.gradingShapeType = queryDataParamsTemp.gradingShapeType;
          		 }
          		 if(queryDataParamsTemp.systemType){
          			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
          		 }
          	 ajaxMethod(_self, 'post',
               'main/queryGradingStatistics', false,
               JSON.stringify(_self.queryDataparmars), 'json',
               'application/json;charset=UTF-8',
               function(_self,result){
            		 if(result.data != null && result.data !=''){
            			 _self.option.series[0].data = ([0,0,0,0,0]);
	    	        	 for(var i = 0; i < result.data.length; i++){
	    	        		 //赋值
	                   if(result.data[i].spRanklevelName == '一级'){
	                    _self.option.series[0].data[0] = result.data[i].spRanklevelCount;
	                   }
				             if(result.data[i].spRanklevelName == '二级'){
				              _self.option.series[0].data[1] = result.data[i].spRanklevelCount; 
				             }
				             if(result.data[i].spRanklevelName == '三级'){
				              _self.option.series[0].data[2] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '四级'){
				              _self.option.series[0].data[3] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '五级'){
				              _self.option.series[0].data[4] = result.data[i].spRanklevelCount;
				             }
	    	        	 }
  	    	         //重绘
  	    	         $('#container-bar').css('display','block');
  	    	         data.myChart.setOption(data.option, true);
            		 }else{
	          				$('#container-bar').css('display','none');
	           		 		/*_self.option.series[0].data = ([0,0,0,0,0]);
	           		 		data.myChart.setOption(data.option, true);*/
            		 } 
          	 	 });
          	 }); 
          	 bus.$on("gradingShapeBegin",function(meg){
          		 var queryDataParamsTemp = JSON.parse(meg);
          		 if(queryDataParamsTemp.gradingBeginTime){
          			 _self.queryDataparmars.gradingBeginTime = queryDataParamsTemp.gradingBeginTime;
          		 }
          		 if(queryDataParamsTemp.gradingEndTime){
          			 _self.queryDataparmars.gradingEndTime = queryDataParamsTemp.gradingEndTime;
          		 }
          		 if(queryDataParamsTemp.gradingShapeType){
          			 _self.queryDataparmars.gradingShapeType = queryDataParamsTemp.gradingShapeType;
          		 }
          		 if(queryDataParamsTemp.systemType){
          			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
          		 }
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                  JSON.stringify(_self.queryDataparmars), 'json',
                 'application/json;charset=UTF-8',
                 function(_self,result){
            		 if(result.data != null && result.data !=''){
            			 _self.option.series[0].data = ([0,0,0,0,0]);
	    	        	 for(var i = 0; i < result.data.length; i++){
	    	        		 //赋值
	                   if(result.data[i].spRanklevelName == '一级'){
	                    _self.option.series[0].data[0] = result.data[i].spRanklevelCount;
	                   }
				             if(result.data[i].spRanklevelName == '二级'){
				              _self.option.series[0].data[1] = result.data[i].spRanklevelCount; 
				             }
				             if(result.data[i].spRanklevelName == '三级'){
				              _self.option.series[0].data[2] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '四级'){
				              _self.option.series[0].data[3] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '五级'){
				              _self.option.series[0].data[4] = result.data[i].spRanklevelCount;
				             }
	    	        	 }
	    	        	 //重绘
	    	        	 $('#container-bar').css('display','block');
	    	        	 data.myChart.setOption(data.option, true);
            		 }else{
            		 	 $('#container-bar').css('display','none');
          				 /*_self.option.series[0].data = ([0,0,0,0,0]);
          				 data.myChart.setOption(data.option, true);*/
            		 }
            	 });
             }); 
          	 bus.$on("gradingShapeType",function(meg){
          		 var queryDataParamsTemp = JSON.parse(meg);
          		 if(queryDataParamsTemp.gradingBeginTime){
          			 _self.queryDataparmars.gradingBeginTime = queryDataParamsTemp.gradingBeginTime;
          		 }
          		 if(queryDataParamsTemp.gradingEndTime){
          			 _self.queryDataparmars.gradingEndTime = queryDataParamsTemp.gradingEndTime;
          		 }
          		 if(queryDataParamsTemp.gradingShapeType){
          			 _self.queryDataparmars.gradingShapeType = queryDataParamsTemp.gradingShapeType;
          		 }
          		 if(queryDataParamsTemp.systemType){
          			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
          		 }
          		 var a=JSON.parse(meg); 
        		 _self.hSystemType=a.systemType;  
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                 JSON.stringify(_self.queryDataparmars), 'json',
                 'application/json;charset=UTF-8',
                 function(_self,result){
            		 if(result.data != null && result.data !=''){
            			 _self.option.series[0].data = ([0,0,0,0,0]);
	    	        	 for(var i = 0; i < result.data.length; i++){
	    	        		 //赋值
	                   if(result.data[i].spRanklevelName == '一级'){
	                    _self.option.series[0].data[0] = result.data[i].spRanklevelCount;
	                   }
				             if(result.data[i].spRanklevelName == '二级'){
				              _self.option.series[0].data[1] = result.data[i].spRanklevelCount; 
				             }
				             if(result.data[i].spRanklevelName == '三级'){
				              _self.option.series[0].data[2] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '四级'){
				              _self.option.series[0].data[3] = result.data[i].spRanklevelCount;
				             }
				             if(result.data[i].spRanklevelName == '五级'){
				              _self.option.series[0].data[4] = result.data[i].spRanklevelCount;
				             }
	    	        	 }
	    	        	 //重绘
	    	        	 $('#container-bar').css('display','block');
	    	        	 data.myChart.setOption(data.option, true);
            		 }else{
            			 $('#container-bar').css('display','none');
            			/* _self.option.series[0].data = ([0,0,0,0,0]);
            			 data.myChart.setOption(data.option, true);*/
            		 }
            	 });
             }); 
           }
           bus.$on("bar",function(val1,val3,hgradingShapeType){ 
      /*       console.log(val1,val3);*/          	 
          	 _self.val1=val1;
          	 _self.val3=val3; 
          	 _self.hgradingShapeType=hgradingShapeType; 
          /*	console.log(this.val1,this.val3);*/
           });
           data.myChart.on('click', function (params) {  
          	 //var that=this;
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
              /* gradingBeginTimeStamp=that.val1;*/
       	     /* gradingEndTimeStamp=that.val3;*/
       	      systemType=_self.hSystemType;
       	      gradingShapeType=_self.hgradingShapeType;
       	  /* console.log(gradingBeginTimeStamp)
  	         console.log(gradingEndTimeStamp )
  	         console.log(systemType)
  	         console.log(gradingShapeType)*/
       	     gradingBeginTimeStamp = new Date("1970-01-01 00:00:00").getTime();
           if(_self.val1){
          	 //因为时间戳与json存到数据库中数据有8小时时差，所以查询的时候加8个小时的时间
             gradingBeginTimeStamp = _self.val1.getTime();//时间戳
           }  
           gradingEndTimeStamp = new Date("9999-12-31 00:00:00").getTime();
           if(_self.val3){
         	  gradingEndTimeStamp = _self.val3.getTime();//时间戳
           }
           if(gradingShapeType == 'undefined' || gradingShapeType == null){
          	 gradingShapeType = "";
           }
           var params = "page/showChartDataListPage?sprankLevel="+sprankLevel
           			+"&gradingBeginTimeStamp="+gradingBeginTimeStamp+"&gradingEndTimeStamp="+gradingEndTimeStamp
           			+"&systemType="+systemType+"&gradingShapeType="+gradingShapeType;
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
 /*               window.location.href = "http://echarts.baidu.com/examples/editor.html";
*/           
          }) 
          
        }
      })
    })
  })
}())
