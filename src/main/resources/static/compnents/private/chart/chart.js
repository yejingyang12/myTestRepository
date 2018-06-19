(function () {
  var data={
    pickerOptions1: {
      disabledDate(time) {
        return time.getTime() > Date.now();
      },
      shortcuts: [{
        text: '今天',
        onClick(picker) {
          picker.$emit('pick', new Date());
        }
      }, {
        text: '昨天',
        onClick(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24);
          picker.$emit('pick', date);
        }
      }, {
        text: '一周前',
        onClick(picker) {
          const date = new Date();
          date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
          picker.$emit('pick', date);
        }
      }]
    },
    value1: '',
    value2: '',
  };
  Vue.component('chart',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/chart/chart.html').then(function(res){
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
          console.log(1);
        }
      })
    })
  })
}())