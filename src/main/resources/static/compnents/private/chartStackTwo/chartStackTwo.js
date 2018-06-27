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
            data: ['备案单位1', '备案单位2','备案单位3','备案单位4','备案单位5','备案单位6', '备案单位7','备案单位8','备案单位9','备案单位10']
        },
        xAxis: {
            type: 'category',
            data: ['备案单位11111111', '备案单位2','备案单位3','备案单位4','备案单位5','备案单位6', '备案单位7','备案单位8','备案单位9','备案单位10'],
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
                type: 'bar',
                stack: '总量',
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,1)'//颜色编码，透明度
                    }
                }
            },
            {
                name: '二级',
                type: 'bar',
                stack: '总量',
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.8)'
                    }
                }
            },
            {
                name: '三级',
                type: 'bar',
                stack: '总量',
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.6)'
                    }
                }
            },
            {
                name: '四级',
                type: 'bar',
                stack: '总量',
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.4)'
                    }
                }
            },
            {
                name: '五级',
                type: 'bar',
                stack: '总量',
                data: [320, 302, 301, 334, 390, 330, 320, 100, 200, 300],
                itemStyle: {
                    normal: {
                        color:'rgba(0,101,186,0.2)'
                    }
                }
            }
        ]
    }, 

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
              '{"systemType":"1"}', 'json',
              'application/json;charset=UTF-8',
              this.getRecordsCompanyNumSuccess);
          } ,
          getRecordsCompanyNumSuccess : function(_self,result){
	        	for(var i = 0; i < result.data.length; i++){
	        		this.option.xAxis.data[i] = result.data[i].companyName;
	        		this.option.series[0].data[i] = result.data[i].level1;
	        		this.option.series[1].data[i] = result.data[i].level2;	
	        		this.option.series[2].data[i] = result.data[i].level3;	
	        		this.option.series[3].data[i] = result.data[i].level4;	
	        		this.option.series[4].data[i] = result.data[i].level5;	
	        	}
	        }
        },
        created: function() {
        	 //受理备案单位数量Top10
          this.getRecordsCompanyNum(this);
        },
        mounted: function() {
            data.dom = document.getElementById("container-stack-two");
            data.myChart = echarts.init(data.dom);
            /*console.log(data.dom)*/
            var _self = this;
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 bus.$on("acceptRecordEnd",function(meg){
            	 ajaxMethod(_self, 'post',
                 'diagram/queryRecordCompanyTop10', false,
                  meg, 'json',
                 'application/json;charset=UTF-8',
                 function(_self,result){
	            		 if(result.data != null && result.data !=''){
	              		 _self.option.series[0].data=[];
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
	  	    	        	 data.myChart.setOption(data.option, true);
	            		 	}else{
	            		 		_self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	                     dangerouslyUseHTMLString: true
	            		 	 });
	            		 	} 
            	 	 });
            	 }); 
            	 bus.$on("acceptRecordBegin",function(meg){
              	 ajaxMethod(_self, 'post',
                   'diagram/queryRecordCompanyTop10', false,
                    meg, 'json',
                   'application/json;charset=UTF-8',
                   function(_self,result){
              		 if(result.data != null && result.data !=''){
              			 _self.option.series[0].data=[];
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
  	    	        	 data.myChart.setOption(data.option, true);
              		 }else{
              			 _self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	                     dangerouslyUseHTMLString: true
              			 });
              		 }
              	 	 });
               });  
          }
     
        }
      })
    })
  })
}())
