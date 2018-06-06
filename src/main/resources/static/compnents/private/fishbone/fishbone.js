(function () {
  var data = {};
  Vue.component('fishbone',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/fishbone/fishbone.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{

        },
        created: function() {

        },
        mounted: function() {
          // this.selectChange()
        }
      })
    })
  })
}())