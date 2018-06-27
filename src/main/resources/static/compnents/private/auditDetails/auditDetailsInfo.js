(function () {
  var data = {
  };
  Vue.component('auditDetailsInfo',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditDetails/auditDetailsInfo.html').then(function (res) {
      resolve({
        template:res,
        data: function () {
          return data;
        },
        created: function(){
        	
        },
        methods:{
        	
        },
        
        mounted: function() {
        	
        }
      })
    })
  })
}())