window.onload = function () {

  var app = new Vue({
    el:"#app",
    data:function () {
      return{

      }
    }
  })

};

// var all = document.getElementById('all');//获取到点击全选的那个复选框的id
// var one = document.getElementsByClassName('checkName');//获取到复选框的名称;获得的是数组
// var arr = [];//定义一个数组存储每个checkbox选中状态；默认都不选中
// //点击 全选 让下面的选框都选中
// function checkAll() {
//   for (var i = 0; i < one.length; i++) {
//     one[i].index = i;
//     arr.splice(one[i].index, 1, all.checked);
//     one[i].checked = all.checked;
//   }
// }
//  //点击 下面的选框 反选全选框
// for (var k = 0; k < one.length; k++) {
//   arr.push(false);//默认有多少列，就添加多少个false
//   one[k].a = k;
//   one[k].onclick = function () {
//     // console.log(arr);
//     arr.splice(this.a, 1, this.checked);
//     all.checked = arr.every(function (item) {
//       return item
//     })
//   };
// }

// //下载和一键导出
//   function downloadFile(str) {
//     if (!str) {//没有值
//       str = "https://codeload.github.com/douban/douban-client/legacy.zip/master";//默认接口
//     }
//     var $eleForm = $("<form method='get'></form>");
//     $eleForm.attr("action", str);
//     $(document.body).append($eleForm);
//     //提交表单，实现下载
//     $eleForm.submit();
//   }

//点击定级模版导出 导出选中的表格数据
// var checked_system=[];//选中的系统
// var check_status=[];//选中状态
// function list(_this) {
//   var firstChecked=document.getElementsByClassName('firstChecked');
//   var tr_row=document.getElementsByClassName('tr-row');
//   if (!_this.getAttribute("hascheck")){//还没有被点击开，准备点击,让checkbox显示
//     _this.style.background='#1e91e2';//定级模版导出按钮变蓝色
//     _this.style.color='#fff';//定级模版导出按钮字体变白色
//     _this.setAttribute("hascheck","has");//设置自定义的标识
//     //让checkbox显示
//     for (let i = 0; i < firstChecked.length; i++) {
//       let cur = firstChecked[i];
//       console.log(cur);
//       cur.style.display='block';
//       var checkbox=cur.getElementsByClassName("checkName")[0];
//       if (i==0){//表示第一列，全选按钮
//         checkbox.onclick=function () {
//           if (this.checked){//第一次选中
//             console.log(1);
//             for (let j = 1; j < firstChecked.length; j++) {//从第二行开始
//               var child_check=firstChecked[j].getElementsByClassName("checkName")[0];
//               child_check.checked=this.checked;
//               checked_system.push(tr_row[j].getAttribute("data-td"));
//               child_check.index=j-1;
//               check_status.splice(child_check.index,1,this.checked);
//             }
//           }else {//清除选中
//             for (let j = 1; j < firstChecked.length; j++) {//从第二行开始
//               var child_check=firstChecked[j].getElementsByClassName("checkName")[0];
//               child_check.checked=this.checked;
//               child_check.index=j-1;
//               check_status.splice(child_check.index,1,this.checked);
//             }
//             checked_system=[];
//           }
//         }
//       }else {//下面的单选按钮
//         check_status.push(false);
//         checkbox.index=i-1;
//         checkbox.onclick=function () {
//           console.log(checked_system);
//           if (this.checked){//当前checkbox没选中
//             checked_system.push(this.parentNode.parentNode.getAttribute("data-td"))
//           }else {
//             checked_system.splice(this.parentNode.parentNode.getAttribute("data-td"),1);
//           }
//           console.log(checked_system);
//
//           check_status.splice(this.index,1,this.checked);
//           console.log(check_status);
//           document.getElementsByClassName("frist")[0].checked=check_status.every(function (item) {
//             return item})
//         }
//       }
//     }
//   }else {//已经点击，准备恢复原样，让checkbox隐藏
//     //让checkbox隐藏
//     for (var i = 0; i < firstChecked.length; i++) {
//       var cur = firstChecked[i];
//       var checkbox=cur.getElementsByClassName("checkName")[0];
//       checkbox.checked=false;
//       cur.style.display='none';
//     }
//     console.log(checked_system);
//     if (checked_system.length>0){//有选中的数据
//       for (var i = 0; i < checked_system.length; i++) {
//         console.log(checked_system[i]);
//         downloadFile(checked_system[i]);//下载数据
//       }
//     }else {
//
//     }
//     _this.style.background='#ededed';
//     _this.style.color='black';
//     _this.setAttribute("hascheck","");
//     checked_system=[];
//     check_status=[];
//   }
//
// }


//一键下载显示列表（1／2／3／4）
//   new Vue().$mount('.app1');
//   new Vue().$mount('#app2');
//   new Vue().$mount('.hide1');
//   new Vue().$mount('.hide2');
//   new Vue().$mount('.hide3');
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



//首页表格点击删除（删除一整行表格）
  function homeListDelete(_this) {
    // console.log(_this);
    //找到按钮所在行的节点，然后删掉这一行
    _this.parentNode.parentNode.parentNode.removeChild(_this.parentNode.parentNode);
    //btnDel - td - tr - tbody - 删除(tr)
    //刷新网页还原。实际操作中，还要删除数据库中数据，实现真正删除
  }

