window.onload = function () {

  var app = new Vue({
    el:"#app",
    data:function () {
      return{

      }
    }
  })
};



/* 请求url：http://www.impf2010.com/ea/android/sajax_ea_wfjLogin.jspa?user=18510953445&password=654321
    参数 user：18510953445
        password：654321
    * */
function getData() {
  // var url="http://219.142.195.43:8072/user/user_login/";
  var url="http://www.impf2010.com/ea/android/sajax_ea_wfjLogin.jspa";
  $.ajax({
    type: "get",//请求方式
    url:encodeURI(url) ,//路径
    data:{   //添加参数
      // "companyId":"bd4760fe10154fe696f6106f2d6db13c"
      user: "18510953445",
      password:"654321"
    },
    // dataType:"json",
    dataType: 'jsonp',
    contentType:"application/json;charset=utf-8",

    crossDomain: true,
    success: function(res) {
      console.log(res);
    },
    // error: function (e) {
    //   console.log('error');
    // }
  })
}
// getData();

function getData1() {
  $.ajax({
    type: "post",//请求方式
    url: "http://219.142.195.43:8071/user/user_login/",//路径
    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "token 87f83cf7f6ed43b0f60f08fac77148b005a93b22");
    },
    data: {                 //添加参数
      username: "caiguoqing",
      password: 123456,
    },
    //成功拿到数据
    success: function(res) {
      console.log(res);
    }
  })

}
// getData1();

//ajax跳页面
(function () {
  $("#h-btn-add").click(function () {
    Ajax('GET','./index.html','../../static/compnents/private/addForm/addForm.html');
  });
  $("#h-dingji").click(function () {
    Ajax('GET','./index.html','../../static/compnents/private/addFormGrad/addFormGrad.html');
  })
  $("#h-dingji1").click(function () {
    Ajax('GET','./index.html','../../static/compnents/private/addFormGrad/addFormGrad.html');
  })
  $("#beian").click(function () {
    Ajax('GET','./index.html','../../static/compnents/private/record/record.html');
  })
}());
function Ajax(type,url, href, data) {
  $.ajax({
    type:type,
    url:url,
    async:false,
    data:data,
    success:function (msg) {
      if(msg){
        window.location.href = href;
      }
    }
  })
}

// /*首页帮助中心点击帮助中心让其显示隐藏*/
// function helpCenter () {
//   var help=document.getElementsByClassName("float-ri")[1];
//   if(help.getAttribute('openHelp')){
//     console.log(1,help.getAttribute( 'openHelp'));
//     $('#arrowHelp').css('transform','rotate(180deg)');
//     help.setAttribute('openHelp','')
//   }else {
//     console.log(2,help.getAttribute( 'openHelp'));
//     console.log($('#arrowHelp').css('transform'));
//     $('#arrowHelp').css('transform','rotate(360deg)');
//     console.log($('#arrowHelp').css('transform'));
//     help.setAttribute('openHelp','open')
//   }
//   var h=document.getElementById('h-bread');
//   h.offsetHeight==331?h.style.height="44px":h.style.height="331px";
//   $("#h-help-center").toggle();
//   $("#h-help-bottom2").toggle();
// }

// /*首页点击"帮助中心"显示背景图片*/
// var change=9;//默认变量，大于4 即可
// function help(_this,num) {
//   var content=document.getElementsByClassName('content');
//   var text=document.getElementsByClassName('text');
//   var box3ItemContent=document.getElementsByClassName('box3-item-content');
//   var box3Text=document.getElementsByClassName('box3-text');
//   if (num==change){//点击同一个
//     if(num==0){//box1
//       //        bgItem=document.getElementById('bg-item1');
//       $('#bg-item1').css('background','#ededed');
//       content[num].style.display="block";
//       text[num].style.display="none";
//     } else if(num==1){//box2
//       $('#bg-item2').css('background','#ededed');
//       content[num].style.display="block";
//       text[num].style.display="none";
//     }else if(num==2){//box4
//       $('#bg-item4').css('background','#ededed');
//       content[num].style.display="block";
//       text[num].style.display="none";
//     }else if(num=4){//box3
//       box3ItemContent[0].style.display='block';
//       box3ItemContent[1].style.display='block';
//       box3ItemContent[2].style.display='block';
//       box3ItemContent[3].style.display='block';
//       $('#bg-item3').css('background','#ededed');
//       box3Text[0].style.display='none';
//       box3Text[1].style.display='none';
//       box3Text[2].style.display='none';
//       box3Text[3].style.display='none';
//     }
//     change=9;
//   } else {//点击的不是同一个
//     console.log("CHANGEx小于"+change);
//     if (change<=4){//说明已经点过了
//       if(change==0){//box1
//         console.log("CHANGE"+change);
//         //        bgItem=document.getElementById('bg-item1');
//         $('#bg-item1').css('background','#ededed');
//         content[change].style.display="block";
//         text[change].style.display="none";
//       } else if(change==1){//box2
//         $('#bg-item2').css('background','#ededed');
//         content[change].style.display="block";
//         text[change].style.display="none";
//       }else if(change==2){//box4
//         $('#bg-item4').css('background','#ededed');
//         content[change].style.display="block";
//         text[change].style.display="none";
//       }else if(change==4){//box3
//         box3ItemContent[0].style.display='block';
//         box3ItemContent[1].style.display='block';
//         box3ItemContent[2].style.display='block';
//         box3ItemContent[3].style.display='block';
//         $('#bg-item3').css('background','#ededed');
//         box3Text[0].style.display='none';
//         box3Text[1].style.display='none';
//         box3Text[2].style.display='none';
//         box3Text[3].style.display='none';
//       }
//     }else {
//
//     }
//     if(num==0){//box1
//       $('#bg-item1').css('background','url("../../static/images/home/dianjidanweixitong.png") no-repeat  center');
//       content[num].style.display="none";
//       text[num].style.display="block";
//     } else if(num==1){//box2
//       $('#bg-item2').css('background','url("../../static/images/home/dianjidanweixitong.png") no-repeat  center');
//       content[num].style.display="none";
//       text[num].style.display="block";
//     }else if(num==2){//box4
//       $('#bg-item4').css('background','url("../../static/images/home/dianjishenqing.png") no-repeat  center');
//       content[num].style.display="none";
//       text[num].style.display="block";
//     }else if(num=4){//box3
//       box3ItemContent[0].style.display='none';
//       box3ItemContent[1].style.display='none';
//       box3ItemContent[2].style.display='none';
//       box3ItemContent[3].style.display='none';
//       $('#bg-item3').css('background','url("../../static/images/home/dianjishenhecepzicha.png") no-repeat  center');
//       box3Text[0].style.display='block';
//       box3Text[1].style.display='block';
//       box3Text[2].style.display='block';
//       box3Text[3].style.display='block';
//     }
//     change=num;
//   }
// }

// /*点击首页"高级查询"让其显示和隐藏*/
// function search () {
//   $("#h-height-search").toggle();
// }

// /*首页"高级查询"下的复选框*/
// var all=document.getElementById('all');//获取到点击全选的那个复选框的id
// var all2=document.getElementById('all2');
// var all3=document.getElementById('all3');
// var one1= document.getElementsByClassName('checkName1');//获取到复选框的名称;获得的是数组
// var one2= document.getElementsByClassName('checkName2');
// var one3= document.getElementsByClassName('checkName3');
// /*类型的复选框*/
// function checkAllHeightSearch() {
//   var arr1=[];//定义一个数组存储每个checkbox选中状态；默认都不选中
//   //全选
//   for(var i=0;i<one1.length;i++){
//     one1[i].index=i;
//     arr.splice(one1[i].index,1,all.checked);
//     one1[i].checked=all.checked;
//   }
//   //反选
//   for (var k = 0; k < one1.length; k++) {
//     arr1.push(false);//默认有多少列，就添加多少个false
//     one1[k].a=k;
//     one1[k].onclick=function () {
//       // console.log(arr);
//       arr1.splice(this.a,1,this.checked);
//       all.checked=arr1.every(function (item) {
//         return item
//       })
//     };
//   }
// }
// /*状态复选框*/
// function checkAllHeightSearch2() {
//   var  arr2=[];
//   console.log(2);
//   for(var i=0;i<one2.length;i++){
//     one2[i].index=i;
//     arr2.splice(one2[i].index,1,all2.checked);
//     one2[i].checked=all2.checked;
//   }
//   for (var k = 0; k < one2.length; k++) {
//     arr2.push(false);//默认有多少列，就添加多少个false
//     one2[k].a=k;
//     one2[k].onclick=function () {
//       arr2.splice(this.a,1,this.checked);
//       all2.checked=arr2.every(function (item) {
//         return item
//       })
//     };
//   }
// }
// /*板块复选框*/
// function checkAllHeightSearch3() {
//   var arr3=[];
//   for(var i=0;i<one3.length;i++){
//     one3[i].index=i;
//     arr3.splice(one3[i].index,1,all3.checked);
//     one3[i].checked=all3.checked;
//   }
//   for (var k = 0; k < one3.length; k++) {
//     arr3.push(false);//默认有多少列，就添加多少个false
//     one3[k].a=k;
//     one3[k].onclick=function () {
//       arr3.splice(this.a,1,this.checked);
//       all3.checked=arr3.every(function (item) {
//         return item
//       })
//     };
//   }
// }

// function selectShow() {
//
//   select.style.display='block'
// }


