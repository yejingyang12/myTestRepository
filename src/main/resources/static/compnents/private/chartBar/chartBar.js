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
                data:[20, 18, 17, 16, 9],
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

        },
        created: function() {
         
        },
        mounted: function() {
         data.dom = document.getElementById("container-bar");
            data.myChart = echarts.init(data.dom);
         /*   console.log(data.dom)*/
            
            if (data.option && typeof data.option === "object") {
              data.myChart.setOption(data.option, true);
            }
          
        }
      })
    })
  })
}())
