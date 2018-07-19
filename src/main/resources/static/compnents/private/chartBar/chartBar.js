(function () {
  var data={
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
	        	ajaxMethod(_self, 'post',
	            'main/queryGradingStatistics', false,
	            '{"systemType":"1"}', 'json',
	            'application/json;charset=UTF-8',
	            this.getGradingShapeSuccess);
	        } ,
	        getGradingShapeSuccess : function(_self,result){	
	        	this.option.series[0].data = ([0,0,0,0,0]);
	        	if(result.data != null){
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
	        	}
	        }
        },
        created: function() {
        	//不同等保级别系统在不同管理状态下详情
        	this.getGradingShape(this);
        },
        mounted: function() {
          data.dom = document.getElementById("container-bar");
          data.myChart = echarts.init(data.dom);
          /*console.log(data.dom)*/
          var _self = this;
           if (data.option && typeof data.option === "object") {
          	 data.myChart.setOption(data.option, true);
          	 bus.$on("gradingShapeEnd",function(meg){
          	 ajaxMethod(_self, 'post',
               'main/queryGradingStatistics', false,
                meg, 'json',
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
  	    	         data.myChart.setOption(data.option, true);
            		 }else{
            		 		_self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
            		 			dangerouslyUseHTMLString: true
            		 	  });
            		 } 
          	 	 });
          	 }); 
          	 bus.$on("gradingShapeBegin",function(meg){
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                  meg, 'json',
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
	    	        	 data.myChart.setOption(data.option, true);
            		 }else{
            			 var json = JSON.parse(meg);
            			 if(json.type == null || json.type == ''){
            				 _self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	                     dangerouslyUseHTMLString: true
	            		 	 });
            			 }
            		 }
            	 });
             }); 
          	 bus.$on("gradingShapeType",function(meg){
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                  meg, 'json',
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
