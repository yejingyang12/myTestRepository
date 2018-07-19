/**
 * Created by timha on 2018/5/23.
 */
window.onload = function () {
  alert(5555)
    var app = new Vue({
        el:"#app",
        data:function () {
            return {};
        },
        methods : {
          
        },
        mounted:function(){
          //点击提交按钮 发送请求 
          bus.$on('jurisdictionShow',function(meg){
            if(meg!=null){
              $(".jurisdictionShow").hide();
            }
          });
        }
    })
}