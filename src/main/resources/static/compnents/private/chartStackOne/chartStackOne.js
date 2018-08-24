(function () {
  var data={
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
        	  rotate:40,
              textStyle: {
                 color: '#000',
                 fontSize: 12
              },
              //切分文字数量
              formatter : function(params){
                  var newParamsName = "";
                  var paramsNameNumber = params.length;
                  var provideNumber = 4; //每行显示文字个数
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
              }
          }
      },
      series: [
               
          {
              name: '一级',
              barWidth : 40,//柱图宽度
              type: 'bar',
              stack: '总量',
              data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
              itemStyle: {
                  normal: {
                      color:'rgba(93,174,81,1)'//颜色编码，透明度
                  }
              }
          },
          {
              name: '二级',
              barWidth : 40,//柱图宽度
              type: 'bar',
              stack: '总量',
              data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
              itemStyle: {
                  normal: {
                      color:'rgba(93,174,81,0.8)'
                  }
              }
          },
          {
              name: '三级',
              barWidth : 40,//柱图宽度
              type: 'bar',
              stack: '总量',
              data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
              itemStyle: {
                  normal: {
                      color:'rgba(93,174,81,0.6)'
                  }
              }
          },
          {
              name: '四级',
              barWidth : 40,//柱图宽度
              type: 'bar',
              stack: '总量',
              data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
              itemStyle: {
                  normal: {
                      color:'rgba(93,174,81,0.4)'
                  }
              }
          },
          {
              name: '五级',
              barWidth : 40,//柱图宽度
              type: 'bar',
              stack: '总量',
              data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
              itemStyle: {
                  normal: {
                      color:'rgba(93,174,81,0.2)'
                  }
              }
          }
      ]
  },

  };
  Vue.component('chartStackOne',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartStackOne/chartStackOne.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
        	// 获取系统等保等级分布统计图数据
        	getRecordsCompanyNum : function(_self) {
          	ajaxMethod(_self, 'post',
              'diagram/queryRecordCompanyTop10', false,
              '{"systemType":"1"}', 'json',
              'application/json;charset=UTF-8',
              this.getRecordsCompanyNumSuccess);
          } ,
          getRecordsCompanyNumSuccess : function(_self,result){
          	this.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
          	this.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
          	if(result.data != null){
          		for(var i = 0; i < result.data.length; i++){
  	        		this.option.xAxis.data[i] = result.data[i].companyName;
  	        		this.option.series[0].data[i] = result.data[i].level1;
  	        		this.option.series[1].data[i] = result.data[i].level2;	
  	        		this.option.series[2].data[i] = result.data[i].level3;	
  	        		this.option.series[3].data[i] = result.data[i].level4;	
  	        		this.option.series[4].data[i] = result.data[i].level5;	
  	        	}
          	}
	        }
        },
        created: function() {
          //获取备案单位数量统计图数据
          this.getRecordsCompanyNum(this);
        },
        mounted: function() {
            data.dom = document.getElementById("container-stack-one");
            data.myChart = echarts.init(data.dom);
            /*console.log(data.dom)*/
            var _self = this;
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 bus.$on("recordEnd",function(meg){
            	 ajaxMethod(_self, 'post',
                 'diagram/queryRecordCompanyTop10', false,
                  meg, 'json',
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
	  	    	        	 $('#container-stack-one').css('display','block');
	  	    	        	 data.myChart.setOption(data.option, true);
	            		 	}else{
	            		 	 $('#container-stack-one').css('display','none');
	            		 		_self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
	              			_self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
	              			_self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
	              			_self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
	              			_self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
	              			_self.option.xAxis.data = [];
	              			data.myChart.setOption(data.option, true);
	            		 	} 
            	 	 });
            	 }); 
            	 bus.$on("recordBegin",function(meg){
              	 ajaxMethod(_self, 'post',
                   'diagram/queryRecordCompanyTop10', false,
                    meg, 'json',
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
  	    	        	 $('#container-stack-one').css('display','block');
  	    	        	 data.myChart.setOption(data.option, true);
              		 }else{
              			 $('#container-stack-one').css('display','none');
              			 _self.option.series[0].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[1].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[2].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[3].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.series[4].data=[0,0,0,0,0,0,0,0,0,0];
              			 _self.option.xAxis.data = [];
              			 data.myChart.setOption(data.option, true);
              		 }
              	 });
               });  
          }
            
        }
      })
    })
  })
}())
