var all=document.getElementById('all');//获取到点击全选的那个复选框的id
var one= document.getElementsByClassName('checkName');//获取到复选框的名称;获得的是数组
//定义一个数组存储每个checkbox选中状态；默认都不选中
var arr=[];
//全选
function checkAll() {
  for(var i=0;i<one.length;i++){
    one[i].index=i;
    arr.splice(one[i].index,1,all.checked);
    one[i].checked=all.checked;
  }
}
//反选
for (var k = 0; k < one.length; k++) {
  arr.push(false);//默认有多少列，就添加多少个false
  one[k].a=k;
  one[k].onclick=function () {
    // console.log(arr);
    arr.splice(this.a,1,this.checked);
    all.checked=arr.every(function (item) {
      return item})
  };
}


//下载和一键导出
function downloadFile(str) {
  if (!str) {//没有值
    str = "https://codeload.github.com/douban/douban-client/legacy.zip/master";//默认接口
  }
  var $eleForm = $("<form method='get'></form>");
  $eleForm.attr("action", str);
  $(document.body).append($eleForm);
  //提交表单，实现下载
  $eleForm.submit();
}
//一键下载显示列表（1／2／3／4）
new Vue().$mount('.app1');
new Vue().$mount('.app2');
new Vue().$mount('.hide1');
new Vue().$mount('.hide2');
// new Vue().$mount('.hide3');
// new Vue().$mount('.hide4');
// new Vue().$mount('.hide5');
// new Vue().$mount('.hide6');
// new Vue().$mount('.hide7');
// new Vue().$mount('.hide8');
// new Vue().$mount('.hide9');
// new Vue().$mount('.hide10');
// new Vue().$mount('.hide11');
// new Vue().$mount('.hide12');
// new Vue().$mount('.hide13');
// new Vue().$mount('.hide14');

//首页表格的删除弹框
var Main = {
  data() {
    return {
      visible2: false,
    };
  }
};
var Ctor = Vue.extend(Main);
new Ctor().$mount('#app2');
//首页表格点击删除（删除一整行表格）
 function  homeListDelete (_this){
   // console.log(_this);
   //找到按钮所在行的节点，然后删掉这一行

   _this.parentNode.parentNode.parentNode.removeChild(_this.parentNode.parentNode);
    //btnDel - td - tr - tbody - 删除(tr)
    //刷新网页还原。实际操作中，还要删除数据库中数据，实现真正删除
}

