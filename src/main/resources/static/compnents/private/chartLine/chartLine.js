(function () {
  var data={
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
          data:['预定级数','审核定级数','备案数','测评数','自查数']
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
              name:'预定级数',
              type:'line',
              symbolSize: 9,
              symbol:'circle',//拐点样式
              data:[150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150, 150],
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
              data:[250, 250, 250, 250, 250, 250, 250, 250, 250, 250, 250, 250],
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
              data:[350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350, 350],
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
              data:[450, 450, 450, 450, 450, 450, 450, 450, 450, 450, 450, 450],
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
              data:[550, 550, 550, 550, 550, 550, 550, 550, 550, 550, 550, 550],
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

  };
  Vue.component('chartLine',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chartLine/chartLine.html').then(function(res){
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
            data.dom = document.getElementById("container-stack");
            data.myChart = echarts.init(data.dom);
            console.log(data.dom)
             if (data.option && typeof data.option === "object") {
            data.myChart.setOption(data.option, true);
             }
        }
      })
    })
  })
}())
