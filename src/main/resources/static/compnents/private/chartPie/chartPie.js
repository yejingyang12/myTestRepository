(function () {
  var data={
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
              '{"systemType":"1"}', 'json',
              'application/json;charset=UTF-8',
              this.getGradingStatisticsSuccess);
          } ,
	        getGradingStatisticsSuccess : function(_self,result){
		         this.option.series[0].data[0].value = 0;
		         this.option.series[0].data[1].value = 0;
		         this.option.series[0].data[2].value = 0;
		         this.option.series[0].data[3].value = 0;
		         this.option.series[0].data[4].value = 0;
		         if(result.data != null){
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
            		 var a=JSON.parse(meg) 
            		 _self.hSystemType=a.systemType;  
            	 ajaxMethod(_self, 'post',
                 'main/queryGradingStatistics', false,
                  meg, 'json',
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
	              			 var json = JSON.parse(meg);
	              			 if(json.type == null || json.type == ''){
	              				 _self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	  	                     dangerouslyUseHTMLString: true
	  	            		 	 });
	              			 }
	              			  $('#container-pie').css('display','block');
	              			 _self.option.series[0].data[0].value = 0;
	              			 _self.option.series[0].data[1].value = 0;
	              			 _self.option.series[0].data[2].value = 0;
	              			 _self.option.series[0].data[3].value = 0;
	              			 _self.option.series[0].data[4].value = 0;
	              			 data.myChart.setOption(data.option, true);
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
  	    	        	  $('#container-pie').css('display','block');
  	    	        	 data.myChart.setOption(data.option, true);
              		 }else{
              			 _self.$alert('<center><strong>暂无数据</strong></center>', '提示', {
	                     dangerouslyUseHTMLString: true
              			 });
              			  $('#container-pie').css('display','block');
              			 _self.option.series[0].data=[];
              			 data.myChart.setOption(data.option, true);
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
            window.location.href=originUrl+encodeURI("page/showChartDataListPage?sprankLevel="+sprankLevel+"&gradingBeginTimeStamp="+gradingBeginTimeStamp+"&gradingEndTimeStamp="+gradingEndTimeStamp+"&systemType="+systemType+"&gradingShapeType="+gradingShapeType);
          /*  window.location.href = "http://echarts.baidu.com/examples/editor.html";*/
         }) 
        },
      })
    })
  })
}())
