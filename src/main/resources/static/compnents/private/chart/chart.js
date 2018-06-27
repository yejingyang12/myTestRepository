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
    value1: '',
    value2: '',
    options:'',
    value3: '',
    value8: '',
    gradingShapeBegin: '',
    gradingShapeEnd: '',
    
    recordNum: '',
    noEvaluationNum: '',
    evaluationedNum: '',
    noSelfNum: '',
    selfedNum: '',
    enterpriseNum: '',
    headquartersNum: '',
    gradingShapeType:'',
    recordDateEnd:'',
    recordDateBegin:'',
    acceptRecordDateBegin:'',
    acceptRecordDateEnd:'',
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
  };
  Vue.component('chart',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chart/chart.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data
        },
        methods:{
          //系统等保等级分布开始时间
        	getGradingStatisticsBegin:function(){
        		var begin;
        		var end;
        		if(this.value1 != null){
        			begin = new Date(this.value1);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.value3 != null){
        			end = new Date(this.value3);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.value1 != null && this.value3 != null){
        			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'"}';
              bus.$emit("gradingStatisticsBegin",dataparmars);
        		}
          },
          //系统等保等级分布结束时间
        	getGradingStatisticsEnd:function(){
        		var begin;
        		var end;
        		if(this.value1 != null){
        			begin = new Date(this.value1);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.value3 != null){
        			end = new Date(this.value3);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.value1 != null && this.value3 != null){
        			var dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'"}';
              bus.$emit("gradingStatisticsEnd",dataparmars);
        		}
          },
          //不同等保级别系统在不同等保管理状态下详情-开始时间
        	getGradingShapeBegin:function(){
        		var begin;
        		var end;
        		if(this.gradingShapeBegin != null){
        			begin = new Date(this.gradingShapeBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.gradingShapeEnd != null){
        			end = new Date(this.gradingShapeEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		var dataparmars ;
        		if(this.gradingShapeBegin != null && this.gradingShapeEnd != null){
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'"}';
              
        		}
        		if(this.gradingShapeType != null){
        			dataparmars = '{"gradingShapeType":"'+this.gradingShapeType+'"}';
        		}
        		bus.$emit("gradingShapeBegin",dataparmars);
          },
          //不同等保级别系统在不同等保管理状态下详情-结束时间
          getGradingShapeEnd:function(){
        		var begin;
        		var end;
        		if(this.gradingShapeBegin != null){
        			begin = new Date(this.gradingShapeBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.gradingShapeEnd != null){
        			end = new Date(this.gradingShapeEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		var dataparmars;
        		if(this.gradingShapeBegin != null && this.gradingShapeEnd != null){
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'"}';
        		}
        		if(this.gradingShapeType != null && this.gradingShapeBegin != null && this.gradingShapeEnd != null){
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'"}';
        		}
        		bus.$emit("gradingShapeEnd",dataparmars);
          },
          //不同等保级别系统在不同等保管理状态下详情-类型
          getGradingShapeType:function(){
          	var begin;
        		var end;
        		if(this.gradingShapeBegin != null){
        			begin = new Date(this.gradingShapeBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.gradingShapeEnd != null){
        			end = new Date(this.gradingShapeEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
          	var dataparmars;
        		if(this.gradingShapeType != null){
        			var dataparmars = '{"gradingShapeType":"'+this.gradingShapeType+'"}';
        		}
        		if(this.gradingShapeBegin != null && this.gradingShapeEnd != null && this.gradingShapeType != null){
        			dataparmars = '{"gradingBeginTime":"'+begin+'","gradingEndTime":"'+end+'","gradingShapeType":"'+this.gradingShapeType+'"}';
        		}
        		bus.$emit("gradingShapeType",dataparmars);
          },
          //备案单位数量Top10-开始时间
          getRecordDateBegin:function(type){
        		var begin;
        		var end;
        		if(this.recordDateBegin != null){
        			begin = new Date(this.recordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.recordDateEnd != null){
        			end = new Date(this.recordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.recordDateBegin != null && this.recordDateEnd != null){
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+type+'"}';
              bus.$emit("recordBegin",dataparmars);
        		}
          },
          //备案单位数量Top10-结束时间
          getRecordDateEnd:function(type){
        		var begin;
        		var end;
        		if(this.recordDateBegin != null){
        			begin = new Date(this.recordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.recordDateEnd != null){
        			end = new Date(this.recordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.recordDateBegin != null && this.recordDateEnd != null){
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+type+'"}';
              bus.$emit("recordEnd",dataparmars);
        		}
          },
          //受理备案单位数量Top10-开始时间
          getAcceptRecordDateBegin:function(type){
        		var begin;
        		var end;
        		if(this.acceptRecordDateBegin != null){
        			begin = new Date(this.acceptRecordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.acceptRecordDateEnd != null){
        			end = new Date(this.acceptRecordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.acceptRecordDateBegin != null && this.acceptRecordDateEnd != null){
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+type+'"}';
              bus.$emit("acceptRecordBegin",dataparmars);
        		}
          },
          //受理备案单位数量Top10-结束时间
          getAcceptRecordDateEnd:function(type){
        		var begin;
        		var end;
        		if(this.acceptRecordDateBegin != null){
        			begin = new Date(this.acceptRecordDateBegin);
        			begin=begin.getFullYear() + '-' + (begin.getMonth() + 1) + '-' + begin.getDate(); 
        		}
        		if(this.acceptRecordDateEnd != null){
        			end = new Date(this.acceptRecordDateEnd);
        			end=end.getFullYear() + '-' + (end.getMonth() + 1) + '-' + end.getDate(); 
        		}
        		if(this.acceptRecordDateBegin != null && this.acceptRecordDateEnd != null){
        			var dataparmars = '{"dateBegin":"'+begin+'","dateEnd":"'+end+'","systemType":"'+type+'"}';
              bus.$emit("acceptRecordEnd",dataparmars);
        		}
          },
        },
        created: function() {

        },
        mounted: function() {
        }
      })
    })
  })
}())