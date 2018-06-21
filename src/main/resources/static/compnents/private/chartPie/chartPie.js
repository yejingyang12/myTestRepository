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
        data:['一级','二级','三级','四级','五级']
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
            {value:335, name:'五级'},
            {value:310, name:'四级'},
            {value:234, name:'三级'},
            {value:135, name:'二级'},
            {value:1048, name:'一级'}
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
          return data
        },
        methods:{

        },
        created: function() { },
        mounted: function() {
            data.dom = document.getElementById("container-pie");
            data.myChart = echarts.init(data.dom);
            /*console.log(data.dom)*/
             if (data.option && typeof data.option === "object") {
            data.myChart.setOption(data.option, true);
          }
        }
      })
    })
  })
}())
