/**
 * Created by timha on 2018/5/29.
 */
(function () {
  var data={

  };
  Vue.component('selfAlert',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/selfalert/selfalert.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return {
            activeName: 'record',
            data
          }
        },
        created: function() {

        },
        methods:{
          getFile:function (obj, ele, elm,target){
            var btn = $(obj);
            var oInup = $(ele);
            var fileuse = $(elm);
            var btnValue = btn.val();
            var arr = [];
            var str = btnValue.split("\\");
            arr.push(str[str.length-1]);
            oInup.val(str[str.length-1]);
            fileuse.val(str[str.length-1]);
            var strh = '';
            for(var i =0;i<arr.length;i++){
              strh += '<span class="active">'+arr[i]+'<i>X</i></span>';
            }
            var upList = document.getElementById('upLise1');
            upList.innerHTML = strh;
            upList.getElementsByTagName('i')[0].onclick = function () {
              this.parentNode.parentNode.removeChild(this.parentNode);
            };
            var fileSize = 0;
            var bro=$.browser;
            var binfo="";
            if(bro.msie){//获取浏览器及版本号
              var isIE = true;
            }
            if(isIE && !target.files){
              var filePath = target.value;
              var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
              var file = fileSystem.GetFile (filePath);
              fileSize = file.Size;
            }else{
              fileSize = target.files[0].size;
            }
            var size = fileSize / 1024;
            //这里限制大小
            if(size>(30*1024*1024)){
              alert("附件不能大于2M");
              target.value="";
              return
            }
            var name=target.value;
            var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
            //这里限制类型
            if(fileName !="word" && fileName !="pdf" && fileName != "exl" && fileName != "zip" && fileName != "rar" && fileName != "sep" ){
              alert("请选择文件类型必须是.word、pdf、exl、zip、rar、sep中的一种格式文档！");
              target.value="";
              return
            }
      },
          getFile2:function (obj, ele, elm,target){
            var btn = $(obj);
            var oInup = $(ele);
            var fileuse = $(elm);
            var btnValue = btn.val();
            var arr = [];
            var str = btnValue.split("\\");
            arr.push(str[str.length-1]);
            oInup.val(str[str.length-1]);
            fileuse.val(str[str.length-1]);
            var strh = '';
            for(var i =0;i<arr.length;i++){
              strh += '<span class="active">'+arr[i]+'<i>X</i></span>';
            }
            var upList = document.getElementById('upLise2');
            upList.innerHTML = strh;
            upList.getElementsByTagName('i')[0].onclick = function () {
              this.parentNode.parentNode.removeChild(this.parentNode);
            };
            var fileSize = 0;
            var bro=$.browser;
            var binfo="";
            if(bro.msie){//获取浏览器及版本号
              var isIE = true;
            }
            if(isIE && !target.files){
              var filePath = target.value;
              var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
              var file = fileSystem.GetFile (filePath);
              fileSize = file.Size;
            }else{
              fileSize = target.files[0].size;
            }
            var size = fileSize / 1024;
            //这里限制大小
            if(size>(30*1024*1024)){
              alert("附件不能大于2M");
              target.value="";
              return
            }
            var name=target.value;
            var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
            //这里限制类型
            if(fileName !="word" && fileName !="pdf" && fileName != "exl" && fileName != "zip" && fileName != "rar" && fileName != "sep" ){
              alert("请选择文件类型必须是.word、pdf、exl、zip、rar、sep中的一种格式文档！");
              target.value="";
              return
            }
      },
          //点击 "X" 关闭弹框
          closes:function () {
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none"
          },
        },

        mounted: function() {

        }
      })
    })
  })

}())