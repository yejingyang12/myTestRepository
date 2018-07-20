(function () {
  var data = {
    result:{
    "code": "0",
      "msg": "成功",
      "pagesize": 0,
      "currentPage": 0,
      "total": 0,
      "totalPages": 0,
      "data": [
      {
        "nodeId": "429c8170541a4d6585e8a4e5f289a583",//鱼骨图结点id
        "systemId": "9f3c348b5e36464981fcee87533b4139",//显示鱼骨图的系统id
        "operation": "备案",//操作：1.创建2.定级审核3.变更4.变更审批5.撤销备案6.撤销备案审批7.备案8.添加测评9.修改测评10.删除测评11.添加自查12.修改自查13.删除自查
        "operator": "admin1",//操作人
        "operationResult": "已创建",//操作结果
        "operationOpinion": "",//操作意见
        "createTime": "2018-06-09 17:32:03.0"//建立时间
      },
      {
        "nodeId": "9f431dde797443c4af6a9f3c75ea6fc6",
        "systemId": "9f3c348b5e36464981fcee87533b4139",
        "operation": "撤销备案审批",
        "operator": "admin2",
        "operationResult": "未通过",
        "operationOpinion": "没有理由",
        "createTime": "2018-06-08 19:52:56.0"
      }
    ]
  }
  };
  Vue.component('fishbone',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/fishbone/fishbone.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
          var url="node/queryNodeList";//鱼骨图
          var _self=this;
          var dataparmars={
            "systemId": systemId,//鱼骨图的系统id
          };
          ajaxMethod(_self,"post",url,false,JSON.stringify(dataparmars),"json",'application/json;charset=UTF-8', _self.listSuccess)
        },
        methods:{
          listSuccess:function(_self,dataList){
            data.result=dataList;
          },
          //鱼骨图弹窗：隐藏弹窗
          closes:function () {
            var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
            evaluationAlert.style.display="none";
          },
          //弹窗：显示弹窗
          showDialog:function(itemdata){
            $("#dialog").css("display","block");
          },
          //时间格式化
         formatDate:function (millinSeconds){
        	var millinSeconds=millinSeconds.replace(/-/g,"/").split('.');
            var date = new Date(millinSeconds);
            var monthArr = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Spt","Oct","Nov","Dec");
            var suffix = new Array("st","nd","rd","th");

            var year = date.getFullYear(); //年
            var month = monthArr[date.getMonth()]; //月
            var ddate = date.getDate(); //日

            if(ddate % 10 < 1 || ddate % 10 > 3) {
              ddate = ddate + suffix[3];
            }else if(ddate % 10 == 1) {
              ddate = ddate + suffix[0];
            } else if(ddate % 10 == 2) {
              ddate = ddate + suffix[1];
            }else {
              ddate = ddate + suffix[2];
            }
            return  month+ " "+  ddate+ " " + year;
        },


        },

        mounted: function() {
        }
      })
    })
  })
}());