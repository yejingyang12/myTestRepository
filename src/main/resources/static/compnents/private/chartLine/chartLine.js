(function () {
  var data={
  	lineShow:false,
    dom:null,
    myChart:null,
    option :null,
    symbolSize:9,
    option : {
      backgroundColor :'#fff',
      title: { },
      tooltip: {
          trigger: 'axis'
      },
      legend: {
          data:['已定级数','审核定级数','备案数','测评数','自查数']
      },
      xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
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
          
      },
      yAxis: {
          type: 'value',
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
      },
      series: [
          {
              name:'已定级数',
              type:'line',
              symbolSize: 9,
              symbol:'circle',//拐点样式
              data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              itemStyle: {
                  normal: {
                      color: "#50e3c2", //圈颜色
                      lineStyle: {
                          width:1.5,//折线宽度
                          color: "#50e3c2" //线颜色
                      }
                  }
              }
          },
          {
              name:'审核定级数',
              type:'line',
              symbolSize:9,
              symbol:'circle',//拐点样式
              data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              itemStyle: {
                  normal: {
                      color: "#12cbf6", //圈颜色
                      lineStyle: {
                          width:1.5,//折线宽度
                          color: "#12cbf6" //线颜色
                      }
                  }
              },
          },
          {
              name:'备案数',
              type:'line',
              symbolSize: 9,
              symbol:'circle',//拐点样式
              data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              itemStyle: {
                  normal: {
                      color: "#9fe060", //圈颜色
                      lineStyle: {
                          width:1.5,//折线宽度
                          color: "#9fe060" //线颜色
                      }
                  }
              },
          },
          {
              name:'测评数',
              type:'line',
              symbolSize: 9,
              symbol:'circle',//拐点样式
              data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              itemStyle: {
                  normal: {
                      color: "#ff9933", //圈颜色
                      lineStyle: {
                          width:1.5,//折线宽度
                          color: "#ff9933" //线颜色
                      }
                  }
              },
          },
          {
              name:'自查数',
              type:'line',
              symbolSize: 9,
              symbol:'circle',//拐点样式
              data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
              itemStyle: {
                  normal: {
                      color: "#028bff", //圈颜色
                      lineStyle: {
                          width:1.5,//折线宽度
                          color: "#028bff" //线颜色
                      }
                  }
              },
          }
      ]
  },
  queryDataparmars: {},//关联图表查询条件
  };
  Vue.component('chartLine',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartLine/chartLine.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
        	// 获取系统等保等级分布统计图数据
        	getSystemTrendByYear : function(_self) {
          	ajaxMethod(_self, 'post',
              'diagram/querySystemTrendByYear', false,
              JSON.stringify(_self.queryDataparmars), 'json',
              'application/json;charset=UTF-8',
              this.getSystemTrendByYearSuccess);
          } ,
          getSystemTrendByYearSuccess : function(_self,result){
          	_self.option.series[0].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
     		    _self.option.series[1].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
     		    _self.option.series[2].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
     		    _self.option.series[3].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
     		    _self.option.series[4].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          	if(result.data != null && result.data !=''){
          		for(var i = 0; i < result.data.length; i++){
//          			this.option.xAxis.data[result.data[i].mouthCount -1] = result.data[i].month;
  	        		_self.option.series[0].data[result.data[i].mouthCount -1] = result.data[i].readyGradCount;
  	        		_self.option.series[1].data[result.data[i].mouthCount -1] = result.data[i].checkGradCount;	
  	        		_self.option.series[2].data[result.data[i].mouthCount -1] = result.data[i].recordsCount;	
  	        		_self.option.series[3].data[result.data[i].mouthCount -1] = result.data[i].evaluationCount;	
  	        		_self.option.series[4].data[result.data[i].mouthCount -1] = result.data[i].selfInspectionCount;	
  	        	}
          		_self.lineShow=true;
          	}else{
          		_self.lineShow=false;
          	}
	        }
        },
        created: function() {
        	this.queryDataparmars.systemType = "1";
        //获取系统等保管理阶段趋势
          this.getSystemTrendByYear(this);
        },
        mounted: function() {
        	data.dom = document.getElementById("container-stack");
        	data.myChart = echarts.init(data.dom);
        	var myChart = data.myChart;
        	/*console.log(data.dom)*/
        	var _self = this;
        	//获取查询中的条件
      	  bus.$on("queryDataParams",function(data){
      	  	var systemType = _self.queryDataparmars.systemType;
      	  	var year = _self.queryDataparmars.year;
      	  	_self.queryDataparmars = JSON.parse(data);
      	  	_self.queryDataparmars.systemType = systemType;
      	  	_self.queryDataparmars.year = year;
      	  	ajaxMethod(_self, 'post',
                'diagram/querySystemTrendByYear', false,
                JSON.stringify(_self.queryDataparmars), 'json',
                'application/json;charset=UTF-8',
                function(_self,result){
		         		 if(result.data != null && result.data !=''){
		         			 //清空所有值
		         			 for(var i=0;i<12;i++){
		         				 _self.option.series[0].data[i] = 0;
		   	        		 _self.option.series[1].data[i] = 0;
		   	        		 _self.option.series[2].data[i] = 0;
		   	        		 _self.option.series[3].data[i] = 0;
		   	        		 _self.option.series[4].data[i] = 0;
		         			 }
		   	        	 for(var i = 0; i < result.data.length; i++){
		   	        		 //赋值
		   	        		 /*_self.option.series[0].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
		   	        		 _self.option.series[1].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
		   	        		 _self.option.series[2].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
		   	        		 _self.option.series[3].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
		   	        		 _self.option.series[4].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);*/
		   	        		 _self.option.series[0].data[result.data[i].mouthCount -1] = result.data[i].readyGradCount;	
		   	        		 _self.option.series[1].data[result.data[i].mouthCount -1] = result.data[i].checkGradCount;	
		   	        		 _self.option.series[2].data[result.data[i].mouthCount -1] = result.data[i].recordsCount;	
		   	        		 _self.option.series[3].data[result.data[i].mouthCount -1] = result.data[i].evaluationCount;	
		   	        		 _self.option.series[4].data[result.data[i].mouthCount -1] = result.data[i].selfInspectionCount;	
		   	        	 }
		   	        	 //重绘
		   	        	 $('#container-stack').css('display','block');
		   	        	 myChart.setOption(_self.option, true);
		         		 }else{
		         				 /*_self.option.series[0].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		           		   _self.option.series[1].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		           		   _self.option.series[2].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		           		   _self.option.series[3].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		           		   _self.option.series[4].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		           		   data.myChart.setOption(data.option, true);*/
		         			 $('#container-stack').css('display','none');
		         		 }
		       	 });
					});
      	  
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 if(_self.lineShow){
            		 $('#container-stack').css('display','block');
            	 }else{
            		 $('#container-stack').css('display','none');
            	 }
            	 bus.$on("yearType",function(meg){
            		 var queryDataParamsTemp = JSON.parse(meg);
            		 if(queryDataParamsTemp.year){
            			 _self.queryDataparmars.year = queryDataParamsTemp.year;
            		 }else{
            			 _self.queryDataparmars.year = '';
            		 }
            		 if(queryDataParamsTemp.systemType){
            			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
            		 }else{
            			 _self.queryDataparmars.systemType = '';
            		 }
            		 
              	 ajaxMethod(_self, 'post',
                   'diagram/querySystemTrendByYear', false,
                   JSON.stringify(_self.queryDataparmars), 'json',
                   'application/json;charset=UTF-8',
                   function(_self,result){
	              		 if(result.data != null && result.data !=''){
	  		         			 //清空所有值
	  		         			 for(var i=0;i<12;i++){
	  		         				 _self.option.series[0].data[i] = 0;
	  		   	        		 _self.option.series[1].data[i] = 0;
	  		   	        		 _self.option.series[2].data[i] = 0;
	  		   	        		 _self.option.series[3].data[i] = 0;
	  		   	        		 _self.option.series[4].data[i] = 0;
	  		         			 }
	  	    	        	 for(var i = 0; i < result.data.length; i++){
	  	    	        		 //赋值
	  	    	        		 /*_self.option.series[0].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
	  	    	        		 _self.option.series[1].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
	  	    	        		 _self.option.series[2].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
	  	    	        		 _self.option.series[3].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
	  	    	        		 _self.option.series[4].data.push([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);*/
	  	    	        		 _self.option.series[0].data[result.data[i].mouthCount -1] = result.data[i].readyGradCount;	
	  	    	        		 _self.option.series[1].data[result.data[i].mouthCount -1] = result.data[i].checkGradCount;	
	  	    	        		 _self.option.series[2].data[result.data[i].mouthCount -1] = result.data[i].recordsCount;	
	  	    	        		 _self.option.series[3].data[result.data[i].mouthCount -1] = result.data[i].evaluationCount;	
	  	    	        		 _self.option.series[4].data[result.data[i].mouthCount -1] = result.data[i].selfInspectionCount;	
	  	    	        	 }
	  	    	        	 //重绘
	  	    	        	 $('#container-stack').css('display','block');
	  	    	        	 data.myChart.setOption(data.option, true);
	              		 }else{
	              				 /*_self.option.series[0].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	                		   _self.option.series[1].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	                		   _self.option.series[2].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	                		   _self.option.series[3].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	                		   _self.option.series[4].data=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	                		   data.myChart.setOption(data.option, true);*/
	              			 $('#container-stack').css('display','none');
	              		 }
              	 });
               }); 
             }
        }
      })
    })
  })
}())
