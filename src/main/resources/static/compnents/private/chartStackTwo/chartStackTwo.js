(function () {
  var data={
  	stackTwoShow:false,
    dom:null,
    myChart:null,
    option :null,
  
    option : {
        backgroundColor :'#fff',
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'none'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: []
        },
        xAxis: {
            type: 'category',
            data: [],
            axisLine: {
                lineStyle: {
                    type: 'solid',
                    color: '#ededed'//左边线的颜色
                },
            },
            axisLabel: {
            	interval:0,
            	rotate:40,
                textStyle: {
                   color: '#000',
                   fontSize: 12
                },
                //切分文字数量
                formatter : function(params){
                    var newParamsName = "";
                    var paramsNameNumber = params.length;
                    var provideNumber = 6; //每行显示文字个数
                    var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                    if (paramsNameNumber > provideNumber) {
                        for (var p = 0; p < rowNumber; p++) {
                            var tempStr = "";
                            var start = p * provideNumber;
                            var end = start + provideNumber;
                            if (p == rowNumber - 1) {
                                tempStr = params.substring(start, paramsNameNumber);
                            } else {
                                tempStr = params.substring(start, end) + "\n";
                            }
                            newParamsName += tempStr;
                        }

                    } else {
                        newParamsName = params;
                    }
                    return newParamsName;
                }

            }
        },
        yAxis:  {
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
                },
               /* formatter: function (value) {
                	console.log(value);
                	var texts = [];
                	if(value=0){
                       texts.push('0');
                    }
                	if(0<value<=1){
                	   texts.push('1');
                	}
                	else if (1<value<=2) { 
                	   texts.push('2');
                	} 
                	else if (2<value<=3) { 
                       texts.push('2');
                    }
                	else if (3<value<=4) { 
                    	texts.push('4');
                    }
                	else{
                		texts.push('5');
                	}
                	return texts;
             }*/
            }
        },
        series: [
            {
                name: '一级',
                barWidth : 40,//柱图宽度
                max:20, //关键第一句
                type: 'bar',
                stack: '总量',
                barCateGoryGap:"1",
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,1)'//颜色编码，透明度
                         	
                    }
                }
            },
            {
                name: '二级',
                barWidth : 40,//柱图宽度
                type: 'bar',
                stack: '总量',
                barCateGoryGap:"1",
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.8)'
                    }
                }
            },
            {
                name: '三级',
                barWidth : 40,//柱图宽度
                type: 'bar',
                stack: '总量',
                barCateGoryGap:"1",
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.6)'
                    }
                }
            },
            {
                name: '四级',
                barWidth : 40,//柱图宽度
                type: 'bar',
                stack: '总量',
                barCateGoryGap:"1",
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.4)'
                    }
                }
            },
            {
                name: '五级',
                barWidth : 40,//柱图宽度
                type: 'bar',
                stack: '总量',
                barCateGoryGap:"1",
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.2)'
                    }
                }
            }
        ]
    },
    queryDataparmars: {},//关联图表查询条件

  };
  Vue.component('chartStackTwo',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartStackTwo/chartStackTwo.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
        	// 受理备案单位数量Top10
        	getRecordsCompanyNum : function(_self) {
          	ajaxMethod(_self, 'post',
              'diagram/queryAcceptCompanyTop10', false,
              JSON.stringify(_self.queryDataparmars), 'json',
              'application/json;charset=UTF-8',
              this.getRecordsCompanyNumSuccess);
          } ,
          getRecordsCompanyNumSuccess : function(_self,result){
          	this.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
          	if(result.data != null && result.data !=''){
          		for(var i = 0; i < result.data.length; i++){
  	        		this.option.xAxis.data[i] = result.data[i].companyName;
  	        		this.option.series[0].data[i] = result.data[i].level1;
  	        		this.option.series[1].data[i] = result.data[i].level2;	
  	        		this.option.series[2].data[i] = result.data[i].level3;	
  	        		this.option.series[3].data[i] = result.data[i].level4;	
  	        		this.option.series[4].data[i] = result.data[i].level5;	
  	        	}
          		_self.stackTwoShow=true;
          	}else{
          		_self.stackTwoShow=false;
          	}
	        }
        },
        created: function() {
        	this.queryDataparmars.systemType = "1";
        	 //受理备案单位数量Top10
          this.getRecordsCompanyNum(this);
        },
        mounted: function() {
        	data.dom = document.getElementById("container-stack-two");
        	data.myChart = echarts.init(data.dom);
        	var myChart = data.myChart;
        	/*console.log(data.dom)*/
        	var _self = this;
        	//获取查询中的条件
      	  bus.$on("queryDataParams",function(data){
      	  	var systemType = _self.queryDataparmars.systemType;
      	  	var dateBegin = _self.queryDataparmars.dateBegin
      	  	var dateEnd = _self.queryDataparmars.dateEnd
      	  	_self.queryDataparmars = JSON.parse(data);
      	  	_self.queryDataparmars.systemType = systemType;
      	  	_self.queryDataparmars.dateBegin = dateBegin;
      	  	_self.queryDataparmars.dateEnd = dateEnd;
      	  	ajaxMethod(_self, 'post',
                'diagram/queryAcceptCompanyTop10', false,
                JSON.stringify(_self.queryDataparmars), 'json',
                'application/json;charset=UTF-8',
                function(_self,result){
         		 if(result.data != null && result.data !=''){
         			 _self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
         			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
         			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
         			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
         			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
         			 _self.option.xAxis.data = [];
	    	         for(var i = 0; i < result.data.length; i++){
   	        		 //赋值
	    	        	 _self.option.xAxis.data[i] = result.data[i].companyName;
   	        		 _self.option.series[0].data[i] = result.data[i].level1;
   	        		 _self.option.series[1].data[i] = result.data[i].level2;	
   	        		 _self.option.series[2].data[i] = result.data[i].level3;	
   	        		 _self.option.series[3].data[i] = result.data[i].level4;	
   	        		 _self.option.series[4].data[i] = result.data[i].level5;
	    	         }
	    	        	 //重绘
	    	         $('#container-stack-two').css('display','block')
	    	        	 myChart.setOption(_self.option, true);
         		 	}else{
         		 		  $('#container-stack-two').css('display','none')
           			 /*_self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
           			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
           			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
           			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
           			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
           			 _self.option.xAxis.data = [];
           			 data.myChart.setOption(data.option, true);*/
         		 	} 
       	 	 });
					});
      	  
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 if(_self.stackTwoShow){
               	$('#container-stack-two').css('display','block')
               }else{
               	$('#container-stack-two').css('display','none')
               }
            	 bus.$on("acceptRecordEnd",function(meg){
            		 var queryDataParamsTemp = JSON.parse(meg);
            		 if(queryDataParamsTemp.dateBegin){
            			 _self.queryDataparmars.dateBegin = queryDataParamsTemp.dateBegin;
            		 }
            		 if(queryDataParamsTemp.dateEnd){
            			 _self.queryDataparmars.dateEnd = queryDataParamsTemp.dateEnd;
            		 }
            		 if(queryDataParamsTemp.systemType){
            			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
            		 }else{
            			 _self.queryDataparmars.systemType = '';
            		 }
            		 
            	 ajaxMethod(_self, 'post',
                 'diagram/queryAcceptCompanyTop10', false,
                 JSON.stringify(_self.queryDataparmars), 'json',
                 'application/json;charset=UTF-8',
                 function(_self,result){
	            		 if(result.data != null && result.data !=''){
	            			 _self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.xAxis.data = [];
	  	    	         for(var i = 0; i < result.data.length; i++){
		    	        		 //赋值
	  	    	        	 _self.option.xAxis.data[i] = result.data[i].companyName;
  	    	        		 _self.option.series[0].data[i] = result.data[i].level1;
  	    	        		 _self.option.series[1].data[i] = result.data[i].level2;	
  	    	        		 _self.option.series[2].data[i] = result.data[i].level3;	
  	    	        		 _self.option.series[3].data[i] = result.data[i].level4;	
  	    	        		 _self.option.series[4].data[i] = result.data[i].level5;
	  	    	         }
	  	    	        	 //重绘
	  	    	         $('#container-stack-two').css('display','block')
	  	    	        	 data.myChart.setOption(data.option, true);
	            		 	}else{
	            		 		  $('#container-stack-two').css('display','none')
	              			 /*_self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
                			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
                			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
                			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
                			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
                			 _self.option.xAxis.data = [];
                			 data.myChart.setOption(data.option, true);*/
	            		 	} 
            	 	 });
            	 }); 
            	 bus.$on("acceptRecordBegin",function(meg){
            		 var queryDataParamsTemp = JSON.parse(meg);
            		 if(queryDataParamsTemp.dateBegin){
            			 _self.queryDataparmars.dateBegin = queryDataParamsTemp.dateBegin;
            		 }
            		 if(queryDataParamsTemp.dateEnd){
            			 _self.queryDataparmars.dateEnd = queryDataParamsTemp.dateEnd;
            		 }
            		 if(queryDataParamsTemp.systemType){
            			 _self.queryDataparmars.systemType = queryDataParamsTemp.systemType;
            		 }else{
            			 _self.queryDataparmars.systemType = '';
            		 }
              	 ajaxMethod(_self, 'post',
                   'diagram/queryAcceptCompanyTop10', false,
                   JSON.stringify(_self.queryDataparmars), 'json',
                   'application/json;charset=UTF-8',
                   function(_self,result){
              		 if(result.data != null && result.data !=''){
              			 _self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.xAxis.data = [];
  	    	        	 for(var i = 0; i < result.data.length; i++){
  	    	        		 //赋值    		 
  	    	        		 _self.option.xAxis.data[i] = result.data[i].companyName;
  	    	        		 _self.option.series[0].data[i] = result.data[i].level1;
  	    	        		 _self.option.series[1].data[i] = result.data[i].level2;	
  	    	        		 _self.option.series[2].data[i] = result.data[i].level3;	
  	    	        		 _self.option.series[3].data[i] = result.data[i].level4;	
  	    	        		 _self.option.series[4].data[i] = result.data[i].level5;
  	    	        	 }
  	    	        	 //重绘
  	    	        	  $('#container-stack-two').css('display','block')
  	    	        	 data.myChart.setOption(data.option, true);
              		 }else{
              			  $('#container-stack-two').css('display','none')
              			 /*_self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.xAxis.data = [];
              			 data.myChart.setOption(data.option, true);*/
              		 }
              	 	 });
               });  
          }
     
        }
      })
    })
  })
}())
