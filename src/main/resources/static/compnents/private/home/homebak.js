(function () {
  var data = {
    change:9,//默认变量，大于4 即可
    arr1:[],//定义一个数组存储每个checkbox选中状态；默认都不选中
    arr2:[],//定义一个数组存储每个checkbox选中状态；默认都不选中
    arr3:[],//定义一个数组存储每个checkbox选中状态；默认都不选中
    // checked:false,
    all1:null,
    all2:null,
    all3:null,
    one1:null,
    one2:null,
    one3:null,
    list:null,
    //定级导出的
    firstcheck :null,
    tr_row:null,
    checked_system:[],
    check_status:[],
    h_score_list2:null,//定级模版导出按钮的id
    h_score_list3:null,//定级模版导出按钮的id

  };
  Vue.component('home',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/home/home.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{
          /*首页帮助中心点击帮助中心让其显示隐藏*/
          helpCenter:function  () {
            var help=document.getElementsByClassName("float-ri")[1];
            if(help.getAttribute('openHelp')){
              // console.log(1,help.getAttribute( 'openHelp'));
              $('#arrowHelp').css('transform','rotate(180deg)');
              help.setAttribute('openHelp','')
            }else {
              console.log(2,help.getAttribute( 'openHelp'));
              console.log($('#arrowHelp').css('transform'));
              $('#arrowHelp').css('transform','rotate(360deg)');
              console.log($('#arrowHelp').css('transform'));
              help.setAttribute('openHelp','open')
            }
            var h=document.getElementById('h-bread');
            h.offsetHeight==331?h.style.height="44px":h.style.height="331px";
            $("#h-help-center").toggle();
            $("#h-help-bottom2").toggle();
          },
          /*首页点击"帮助中心"显示背景图片*/
          // var change=9,//默认变量，大于4 即可
          help:function (_this,num) {
            var content=document.getElementsByClassName('content');
            var text=document.getElementsByClassName('text');
            var box3ItemContent=document.getElementsByClassName('box3-item-content');
            var box3Text=document.getElementsByClassName('box3-text');
            if (num==this.change){//点击同一个
              if(num==0){//box1
                //        bgItem=document.getElementById('bg-item1');
                $('#bg-item1').css('background','#ededed');
                content[num].style.display="block";
                text[num].style.display="none";
              } else if(num==1){//box2
                $('#bg-item2').css('background','#ededed');
                content[num].style.display="block";
                text[num].style.display="none";
              }else if(num==2){//box4
                $('#bg-item4').css('background','#ededed');
                content[num].style.display="block";
                text[num].style.display="none";
              }else if(num=4){//box3
                box3ItemContent[0].style.display='block';
                box3ItemContent[1].style.display='block';
                box3ItemContent[2].style.display='block';
                box3ItemContent[3].style.display='block';
                $('#bg-item3').css('background','#ededed');
                box3Text[0].style.display='none';
                box3Text[1].style.display='none';
                box3Text[2].style.display='none';
                box3Text[3].style.display='none';
              }
              this.change=9;
            } else {//点击的不是同一个
              console.log("CHANGEx小于"+this.change);
              if (this.change<=4){//说明已经点过了
                if(this.change==0){//box1
                  console.log("CHANGE"+this.change);
                  //        bgItem=document.getElementById('bg-item1');
                  $('#bg-item1').css('background','#ededed');
                  content[this.change].style.display="block";
                  text[this.change].style.display="none";
                } else if(this.change==1){//box2
                  $('#bg-item2').css('background','#ededed');
                  content[this.change].style.display="block";
                  text[this.change].style.display="none";
                }else if(this.change==2){//box4
                  $('#bg-item4').css('background','#ededed');
                  content[this.change].style.display="block";
                  text[this.change].style.display="none";
                }else if(this.change==4){//box3
                  box3ItemContent[0].style.display='block';
                  box3ItemContent[1].style.display='block';
                  box3ItemContent[2].style.display='block';
                  box3ItemContent[3].style.display='block';
                  $('#bg-item3').css('background','#ededed');
                  box3Text[0].style.display='none';
                  box3Text[1].style.display='none';
                  box3Text[2].style.display='none';
                  box3Text[3].style.display='none';
                }
              }else {

              }
              if(num==0){//box1
                $('#bg-item1').css('background','url("../../static/images/home/dianjidanweixitong.png") no-repeat  center');
                content[num].style.display="none";
                text[num].style.display="block";
              } else if(num==1){//box2
                $('#bg-item2').css('background','url("../../static/images/home/dianjidanweixitong.png") no-repeat  center');
                content[num].style.display="none";
                text[num].style.display="block";
              }else if(num==2){//box4
                $('#bg-item4').css('background','url("../../static/images/home/dianjishenqing.png") no-repeat  center');
                content[num].style.display="none";
                text[num].style.display="block";
              }else if(num=4){//box3
                box3ItemContent[0].style.display='none';
                box3ItemContent[1].style.display='none';
                box3ItemContent[2].style.display='none';
                box3ItemContent[3].style.display='none';
                $('#bg-item3').css('background','url("../../static/images/home/dianjishenhecepzicha.png") no-repeat  center');
                box3Text[0].style.display='block';
                box3Text[1].style.display='block';
                box3Text[2].style.display='block';
                box3Text[3].style.display='block';
              }
              this.change=num;
            }
          },
          /*点击首页"高级查询"让其显示和隐藏*/
          search:function  () {
            $("#h-height-search").toggle();
          },
          //下载和一键导出
          downloadFile:function (str) {
            console.log(1);
            if (!str) {//没有值
              str = "https://codeload.github.com/douban/douban-client/legacy.zip/master";//默认接口
            }
            var $eleForm = $("<form method='get'></form>");
            $eleForm.attr("action", str);
            $(document.body).append($eleForm);
            //提交表单，实现下载
            $eleForm.submit();
          },
          //表1、表2、表3、表4下载
          downloadList:function () {
            var that=this;
            var list=data.list;
            for (let i = 0; i <list.length; i++) {
              list[i].onclick=function () {
                // console.log(list[i]);
                that.downloadFile()
              }
            }
          },
          //定级信息导入
          getFile:function(obj){
            console.log(1);
            var btn = $(obj);
            var btnValue = btn.val();
            var arr = [];
            var str = btnValue.split("\\");
            arr.push(str[str.length-1]);
            // 返回 KB，保留小数点后两位
            var file = obj.value;
            if(!/.(word|pdf|exl|zip|rar|sep)$/.test(file)){
              console.log(2);
              obj.value = '';
              alert("文件类型必须是.word、pdf、exl、zip、rar、sep中的一种");
              return false;
            }else{
              console.log(3);
              //返回Byte(B),保留小数点后两位
              if(((obj.files[0].size).toFixed(2))>=(30*1024*1024)){

                alert("请上传小于30M的文件");
                return false;
              }
            }
          },
          changeColor:function () {
            var _this=data.h_score_list3;
            console.log(1,_this);
            _this.style.background='#1489e6';//定级模版导出按钮变蓝色
            _this.style.color='#fff';//定级模版导出按钮字体变白色
            _this.style.border='none';//定级模版导出按钮字体变白色
            $(_this).prev().css('background','#ededed');
            $(_this).prev().css('color','black');
          },
          /*首页"高级查询"下的复选框*/
          checkAllHeightSearch:function () {
            for(var i=0;i<data.one1.length;i++){
              data.one1[i].index=i;
              data.arr1.splice(data.one1[i].index,1,data.all1.checked);
              data.one1[i].checked=data.all1.checked;
            }
          },
          /*状态复选框*/
          checkAllHeightSearch2:function () {
            for(var i=0;i<data.one2.length;i++){
              data.one2[i].index=i;
              data.arr2.splice(data.one2[i].index,1,data.all2.checked);
              data.one2[i].checked=data.all2.checked;
            }
          },
          /*板块复选框*/
          checkAllHeightSearch3:function () {
            for(var i=0;i<data.one3.length;i++){
              data.one3[i].index=i;
              data.arr3.splice(data.one3[i].index,1,data.all3.checked);
              data.one3[i].checked=data.all3.checked;
            }

          },
          //初始化各个子选框
          checkitem:function (all) {
            // var one1= document.getElementsByClassName('checkName1');//获取到复选框的名称;获得的是数组
            //反选类型第一个
            // console.log(data.one1.length);
            for (var k = 0; k <data.one1.length ; k++) {
              data.arr1.push(false);//默认有多少列，就添加多少个false
              data.one1[k].a=k;
              data.one1[k].onclick=function () {
                data.arr1.splice(this.a,1,this.checked);
                data.all1.checked=data.arr1.every(function (item) {
                  return item
                })
              };
            }
            //反选第二个
            for (var k = 0; k < data.one2.length; k++) {
              data.arr2.push(false);//默认有多少列，就添加多少个false
              data.one2[k].a=k;
              data.one2[k].onclick=function () {
                data.arr2.splice(this.a,1,this.checked);
                data.all2.checked=data.arr2.every(function (item) {
                  return item
                })
              };
            }
            //反选第三个
            for (var j = 0; j < data.one3.length; j++) {
              data.arr3.push(false);//默认有多少列，就添加多少个false
              data.one3[j].a=j;
              data.one3[j].onclick=function () {
                data.arr3.splice(this.a,1,this.checked);
                data.all3.checked=data.arr3.every(function (item) {
                  return item
                })
              };
            }

            //定级模版导出让checkbox显示
            for (let i = 0; i < data.firstChecked.length; i++) {
              let cur = data.firstChecked[i];
              var checkbox=cur.getElementsByClassName("checkName")[0];
              if (i==0){//表示第一列，全选按钮
                checkbox.onclick=function () {
                  if (this.checked){//第一次选中
                    console.log(1);
                    for (let j = 1; j < data.firstChecked.length; j++) {//从第二行开始
                      var child_check=data.firstChecked[j].getElementsByClassName("checkName")[0];
                      child_check.checked=this.checked;
                      data.checked_system.push(data.tr_row[j].getAttribute("data-td"));
                      child_check.index=j-1;
                      data.check_status.splice(child_check.index,1,this.checked);
                    }
                  }else {//清除选中
                    for (let j = 1; j < data.firstChecked.length; j++) {//从第二行开始
                      var child_check=data.firstChecked[j].getElementsByClassName("checkName")[0];
                      child_check.checked=this.checked;
                      child_check.index=j-1;
                      data.check_status.splice(child_check.index,1,this.checked);
                    }
                    data.checked_system=[];
                  }
                }
              }else {//下面的单选按钮
                data.check_status.push(false);
                checkbox.index=i-1;
                checkbox.onclick=function () {
                  console.log(data.checked_system);
                  if (this.checked){//当前checkbox没选中
                    data.checked_system.push(this.parentNode.parentNode.getAttribute("data-td"))
                  }else {
                    data.checked_system.splice(this.parentNode.parentNode.getAttribute("data-td"),1);
                  }
                  console.log(data.checked_system);

                  data.check_status.splice(this.index,1,this.checked);
                  console.log(data.check_status);
                  document.getElementsByClassName("frist")[0].checked=data.check_status.every(function (item) {
                    return item})
                }
              }
            }
          },

          //定级模版导出按钮
          checkallexport:function () {
            var _this=data.h_score_list2;
            if (!_this.getAttribute("hascheck")){//还没有被点击开，准备点击,让checkbox显示
              _this.style.background='#1489e6';//定级模版导出按钮变蓝色
              _this.style.color='#fff';//定级模版导出按钮字体变白色
              _this.style.border='none';//定级模版导出按钮字体变白色
              _this.setAttribute("hascheck","has");//设置自定义的标识
              //让checkbox显示
              for (let i = 0; i < data.firstChecked.length; i++) {
                let cur = data.firstChecked[i];
                // console.log(cur);
                cur.style.display='block';
              }
            }else {//已经点击，准备恢复原样，让checkbox隐藏
              //让checkbox隐藏
              for (var i = 0; i < data.firstChecked.length; i++) {
                var cur = data.firstChecked[i];
                var checkbox=cur.getElementsByClassName("checkName")[0];
                checkbox.checked=false;
                cur.style.display='none';
                //恢复都是false
                if(i<data.firstChecked.length-1){
                  data.check_status.splice(i,1,false);
                }
              }
              console.log(data.checked_system);
              if (data.checked_system.length>0){//有选中的数据
                for (var i = 0; i < data.checked_system.length; i++) {
                  console.log(data.checked_system[i]);
                  this.downloadFile(data.checked_system[i]);//下载数据
                }
              }else {

              }
              _this.style.background='#ededed';
              _this.style.color='black';
              _this.setAttribute("hascheck","");
              data.checked_system=[];

            }
          },
          //列表排序

        },
        created: function() {

        },
        mounted: function() {
          var all=document.getElementById('all-type');//获取到点击全选的那个复选框的id
          var all2=document.getElementById('all2');
          var all3=document.getElementById('all3');
          var h_score_list2=document.getElementById('h-score-list2');
          var h_score_list3=document.getElementById('h-score-list3');
          var one1= document.getElementsByClassName('checkName1');//获取到复选框的名称;获得的是数组
          var one2= document.getElementsByClassName('checkName2');
          var one3= document.getElementsByClassName('checkName3');
          var list= document.getElementsByClassName('list');

          //定级模版导出
          data.firstChecked=document.getElementsByClassName('firstChecked');
          data.tr_row=document.getElementsByClassName('tr-row');

          data.all1=all;
          data.one1=one1;
          data.all2=all2;
          data.one2=one2;
          data.all3=all3;
          data.one3=one3;
          data.h_score_list2=h_score_list2;
          data.h_score_list3=h_score_list3;
          data.list=list;
          // this.selectChange()
          //初始化操作
          this.checkitem();
          this.downloadList('');

        }
      })
    })
  })
}());