(function () {
  var data = {};
  Vue.component('auditCancel',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/auditCancel/auditCancel.html').then(function (res) {
      console.log(res);
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          text:function(){
            $('#textArea').on("keyup",function(){
              $('#textNum').text($('#textArea').val().length);//键盘按下时，实时的显示字数
              if($('#textArea').val().length > 200){
                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
              }
            })
          },
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