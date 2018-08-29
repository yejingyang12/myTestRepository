(function () {
  var data={
      
    pickerOptions1: {
      disabledDate:function(time) {
        return time.getTime() > Date.now();
      },
      shortcuts: [{
        text: '今天',
        onClick:function(picker) {
          picker.$emit('pick', new Date());
        }
      }, {
        text: '昨天',
        onClick:function(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24);
          picker.$emit('pick', date);
        }
      }, {
        text: '一周前',
        onClick:function(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
          picker.$emit('pick', date);
        }
      }]
    },
    value1: '',//饼状图开始时间；
    value2: '',
    options:'',
    value3: '',//饼状图结束时间；
    value8: '',
    gradingShapeBegin: '',//小堆叠图的开始时间；
    gradingShapeEnd: '',//小堆叠图的结束时间；
    
    recordNum: '',
    noEvaluationNum: '',
    evaluationedNum: '',
    noSelfNum: '',
    selfedNum: '',
    enterpriseNum: '',
    headquartersNum: '',
    gradingShapeType:'',//小堆叠图的下拉类型
    recordDateEnd:'',
    recordDateBegin:'',
    acceptRecordDateBegin:'',
    acceptRecordDateEnd:'',
    yearType:'',
    shapeType: [{
      value: '1',
      label: '定级备案数'
    }, {
      value: '2',
      label: '未测评数'
    }, {
      value: '3',
      label: '完成测评数'
    }, {
      value: '4',
      label: '未自查数'
    }, {
      value: '5',
      label: '完成自查数'
    },{
      value: '6',
      label: '企业自建数'
    },{
      value: '7',
      label: '总部统建数'
    }],
    years:[],
    statisticsType:'1',
    queryDataparmars:{},//关联图表查询条件
  };
  Vue.component('chart',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chart/chart.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
        	getStatisticsType:function(type){
        		if(type==1){
        			this.value1='';
        			this.value3='';
        			this.gradingShapeBegin='';
        			this.gradingShapeEnd='';
        			this.gradingShapeType='';
        			this.yearType='';
        			this.recordDateBegin='';
        			this.recordDateEnd='';
        			this.acceptRecordDateBegin='';
        			this.acceptRecordDateEnd='';
        		  $('.hoverTwo').css("color","#3d95df");
          		$('.hoverTwo').css("border-bottom-style","solid");
          		$('.hoverOne').css("color","#333");
          		$('.hoverOne').css("border-bottom-style","none");
              bus.$emit("gradingStatisticsEnd",'{"systemType":"1","type":"1"}');
              bus.$emit("gradingShapeBegin",'{"systemType":"1","type":"1"}');
              bus.$emit("recordBegin",'{"systemType":"1","type":"1"}');
              bus.$emit("acceptRecordEnd",'{"systemType":"1","type":"1"}');
              bus.$emit("gradingShapeEnd",'{"systemType":"1","type":"1"}');
              bus.$emit("yearType",'{"systemType":"1","type":"1"}');
              this.statisticsType = 1;
        		}else{
        			this.value1='';
        			this.value3='';
        			this.gradingShapeBegin='';
        			this.gradingShapeType='';
        			this.yearType='';
        			this.gradingShapeEnd='';
        			this.recordDateBegin='';
        			this.recordDateEnd='';
        			this.acceptRecordDateBegin='';
        			this.acceptRecordDateEnd='';
        			$('.hoverOne').css("color","#3d95df");
              		$('.hoverOne').css("border-bottom-style","solid");
              		$('.hoverTwo').css("color","#333");
              		$('.hoverTwo').css("border-bottom-style","none");
        			bus.$emit("gradingStatisticsEnd",'{"systemType":"2","type":"1"}');
              bus.$emit("gradingShapeBegin",'{"systemType":"2","type":"1"}');
              bus.$emit("recordBegin",'{"systemType":"2","type":"1"}');
              bus.$emit("gradingShapeEnd",'{"systemType":"2","type":"1"}');
              bus.$emit("acceptRecordEnd",'{"systemType":"2","type":"1"}');
              bus.$emit("yearType",'{"systemType":"2","type":"1"}');
              this.statisticsType = 2;
        		}
        	},
          //系统等保等级分布开始时间
        	getGradingStatisticsBegin:function(){
        		bus.$emit("pie",this.value1,this.value3);
        		if(this.value1 != null && this.value1 != "" && this.value1 != 'undefined' &&this.value3 != null && this.value3 != "" && this.value3 != 'undefined'){
        			var begin = new Date(this.value1);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.value3);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate();       			
        			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingStatisticsBegin",dataparmars); 
        		}else if(this.value1==null&&this.value3==null){
        		//如果两个时间都是空
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","systemType":"'+this.statisticsType+'"}';
      	  			bus.$emit("gradingStatisticsBegin",dataparmars);
        		}else{
        		}
          },
          //系统等保等级分布结束时间
        	getGradingStatisticsEnd:function(){
        		bus.$emit("pie",this.value1,this.value3);
        		if(this.value1 != null && this.value1 != "" && this.value1 != 'undefined' &&this.value3 != null && this.value3 != "" && this.value3 != 'undefined'){
        			var begin = new Date(this.value1);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.value3);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingStatisticsEnd",dataparmars);
        		}else if(this.value1==null&&this.value3==null){
        			//如果两个时间都是空
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","systemType":"'+this.statisticsType+'"}';
      	  			bus.$emit("gradingStatisticsEnd",dataparmars);
        		}else{
        		}
          },
          //不同等保级别系统在不同等保管理状态下详情-开始时间
        	getGradingShapeBegin:function(){
        		bus.$emit("bar",this.gradingShapeBegin,this.gradingShapeEnd,this.gradingShapeType);
        		//如果两个时间不是空，但
        		if(this.gradingShapeBegin != null   && this.gradingShapeBegin !="" && this.gradingShapeBegin !='undefined' && this.gradingShapeEnd !='undefined'  && this.gradingShapeEnd != null && this.gradingShapeEnd != ""){
        			var begin = new Date(this.gradingShapeBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.gradingShapeEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars ;
        			if(this.gradingShapeType == null || this.gradingShapeType == 'undefined'){
        				this.gradingShapeType = "";
        			}
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingShapeBegin",dataparmars);           
        		}else if(this.gradingShapeBegin==null&&this.gradingShapeEnd==null){
        			//如果两个时间都是空
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars ;
        			if(this.gradingShapeType == null || this.gradingShapeType == 'undefined'){
        				this.gradingShapeType = "";
        			}
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingShapeBegin",dataparmars);
        		}else{
        		}
          },
          //不同等保级别系统在不同等保管理状态下详情-结束时间
          getGradingShapeEnd:function(){
        	    bus.$emit("bar",this.gradingShapeBegin,this.gradingShapeEnd,this.gradingShapeType);
        		if(this.gradingShapeBegin != null   && this.gradingShapeBegin !="" && this.gradingShapeBegin !='undefined' && this.gradingShapeEnd !='undefined'  && this.gradingShapeEnd != null && this.gradingShapeEnd != ""){
        			var begin = new Date(this.gradingShapeBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.gradingShapeEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			if(this.gradingShapeType == null || this.gradingShapeType == 'undefined'){
        				this.gradingShapeType = "";
        			}
        			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingShapeEnd",dataparmars);
        		}else if(this.gradingShapeBegin==null&&this.gradingShapeEnd==null){
        			//如果两个时间都是空
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars ;
        			if(this.gradingShapeType == null || this.gradingShapeType == 'undefined'){
        				this.gradingShapeType = "";
        			}
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("gradingShapeEnd",dataparmars);
        		}else{
        		}
          },
          //不同等保级别系统在不同等保管理状态下详情-类型
          getGradingShapeType:function(){
        	  bus.$emit("bar",this.gradingShapeBegin,this.gradingShapeEnd,this.gradingShapeType);
        	  if(this.gradingShapeType != null &&  this.gradingShapeType != 'undefined'){
        	  	if(this.gradingShapeBegin != null   && this.gradingShapeBegin !="" && this.gradingShapeBegin !='undefined' && this.gradingShapeEnd !='undefined'  && this.gradingShapeEnd != null && this.gradingShapeEnd != ""){
        	  		var begin = new Date(this.gradingShapeBegin);
          			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
          			var end = new Date(this.gradingShapeEnd);
          			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate();
        	  	}else{
        	  		var begin = "1970-01-01";
        	  		var end = "9999-12-31";
        	  	}
        	  	var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'","systemType":"'+this.statisticsType+'"}';
        	  	bus.$emit("gradingShapeEnd",dataparmars);        				
        	  }
          },
          //备案单位数量Top10-开始时间
          getRecordDateBegin:function(){
        		if(this.recordDateBegin != null && this.recordDateBegin !='' && this.recordDateBegin !='undefined' && this.recordDateEnd != null && this.recordDateEnd !='' && this.recordDateEnd !='undefined'){
        			var begin = new Date(this.recordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate();
        			var end = new Date(this.recordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("recordBegin",dataparmars);
        		}else if(this.recordDateBegin==null&&this.recordDateEnd==null){
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("recordBegin",dataparmars);
        		}else{
        		}
          },
          //备案单位数量Top10-结束时间
          getRecordDateEnd:function(){
        		if(this.recordDateBegin != null && this.recordDateBegin !='' && this.recordDateBegin !='undefined' && this.recordDateEnd != null && this.recordDateEnd !='' && this.recordDateEnd !='undefined'){
        			var begin = new Date(this.recordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.recordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("recordEnd",dataparmars);
        		}else if(this.recordDateBegin==null&&this.recordDateEnd==null){
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("recordEnd",dataparmars);
        		}else{
        		}
          },
          //受理备案单位数量Top10-开始时间
          getAcceptRecordDateBegin:function(){
        		if(this.acceptRecordDateBegin != null && this.acceptRecordDateBegin !='' && this.acceptRecordDateBegin !='undefined'&&this.acceptRecordDateEnd != null && this.acceptRecordDateEnd !='' && this.acceptRecordDateEnd !='undefined'){
        			var begin = new Date(this.acceptRecordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.acceptRecordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("acceptRecordBegin",dataparmars);
        		}else if(this.acceptRecordDateBegin==null&&this.acceptRecordDateEnd==null){
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("acceptRecordBegin",dataparmars);
        		}else{
        		}
          },
          //受理备案单位数量Top10-结束时间
          getAcceptRecordDateEnd:function(){
        		if(this.acceptRecordDateBegin != null && this.acceptRecordDateBegin !='' && this.acceptRecordDateBegin !='undefined'&&this.acceptRecordDateEnd != null && this.acceptRecordDateEnd !='' && this.acceptRecordDateEnd !='undefined'){
        			var begin = new Date(this.acceptRecordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        			var end = new Date(this.acceptRecordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("acceptRecordEnd",dataparmars);
        		}else if(this.acceptRecordDateBegin==null&&this.acceptRecordDateEnd==null){
        			var begin = "1970-01-01";
      	  			var end = "9999-12-31";
      	  			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("acceptRecordEnd",dataparmars);
        		}else{
        		}
          },
          //系统等保管理趋势 - 类型
          getYearType:function(){
//          	if(this.yearType==null||this.yearType=='undefind'||this.yearType==''){
//          		bus.$emit("yearEmptyType",this);
//          		return;
//          	}
        		if(this.yearType != null&&this.yearType != ''&&this.yearType!='undefined'){
        			var dataparmars = '{"year":"'+this.yearType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("yearType",dataparmars);
        		}else{
        			var dataparmars = '{"year":"'+this.yearType+'","systemType":"'+this.statisticsType+'"}';
        			bus.$emit("yearType",dataparmars);
        		}
          },
        },
        created: function() {
          var date=new Date;
          var year=date.getFullYear(); 
          for(var i = year ; i>= 2013 ; i--){
          	this.years.push({
              "value":i,
              "label":i
            })
          } 
        },
        mounted: function() {
        	this.getStatisticsType(1);
      	  bus.$emit("pie",this.value1,this.value3);
      	  bus.$emit("bar",this.gradingShapeBegin,this.gradingShapeEnd,this.gradingShapeType);
      	  
      	  bus.$on("queryDataParams",function(data){
						this.queryDataparmars = JSON.parse(data);
					});
        }
      })
    })
  })
}())