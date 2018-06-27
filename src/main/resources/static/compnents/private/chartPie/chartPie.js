(function () {
  var data={
    dom:null,
    myChart:null,
    option :null,
    option :{
      tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        left:'-2',
        top:'10',
        orient: 'vertical',
//        x: 'left',
        data:[]
      },
      series : [
        {
          name: '等级分布',
          type: 'pie',
          radius : '55%',
          center: ['55%', '50%'],
          radius: ['35%', '50%'],
          label: {
            /*position:'center',*/
            normal: {
              formatter: '{col|{b}}{col|{d}%}',
              rich: {
                col: {
                  color: '#000',
                  fontSize : 14,
                }
              }
            }
          },
          data:[
            {value:'', name:''},
            {value:'', name:''},
            {value:'', name:''},
            {value:'', name:''},
            {value:'', name:''}
          ]
        }
      ],
      color: ['#ff9933','#0065ba','#028bff','#12cbf6','#50e3c2']
    },
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
          	ajaxMethod(_self, 'post',
              'main/queryGradingStatistics', false,
              "{}", 'json',
              'application/json;charset=UTF-8',
              this.getGradingStatisticsSuccess);
          } ,
	        getGradingStatisticsSuccess : function(_self,result){
	        	for(var i = 0; i < result.data.length; i++){
	        		this.option.series[0].data[i].value = result.data[i].spRanklevelCount;
	          	this.option.series[0].data[i].name = result.data[i].spRanklevelName;	
	          	this.option.legend.data[i] = result.data[i].spRanklevelName;
	        	}
	        }
        },
        created: function() { 
        
          //获取系统等保等级分布统计图数据
          this.getGradingStatistics(this);
        },
        mounted: function() {
            data.dom = document.getElementById("container-pie");
            data.myChart = echarts.init(data.dom);
            /*console.log(data.dom)*/
            var _self = this;
             if (data.option && typeof data.option === "object") {
            	 data.myChart.setOption(data.option, true);
            	 bus.$on("gradingStatisticsEnd",function(meg){
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                  meg, 'json',
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
	  	    	        	 data.myChart.setOption(data.option, true);
	            		 	}else{
	            		 		_self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	                     dangerouslyUseHTMLString: true
	            		 	 });
	            		 	} 
            	 	 });
            	 }); 
            	 bus.$on("gradingStatisticsBegin",function(meg){
              	 ajaxMethod(_self, 'post',
                   'main/queryGradingStatistics', false,
                    meg, 'json',
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
