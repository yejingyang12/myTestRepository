 (function () {
  var data = {};
  Vue.component('audit',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/audit/audit.html').then(function (res) {
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