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
                name: '直接访问',
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
                name: '邮件营销',
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
                name: '联盟广告',
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
                name: '视频广告',
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
                name: '搜索引擎',
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

        },
        created: function() {
         
        },
        mounted: function() {
            data.dom = document.getElementById("container-stack-two");
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
