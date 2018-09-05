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
  },
  result1:{},
  option:'',
  fkChangeContent:"",
  fkChangeReason:"",
  fkSysChangeMatter:"",
  topOrBottom: '',//节点在上还是在下
  fishboneType: "2",//鱼骨图类型，1：审核，2：详情
  lastNodeShowTop: false,//是否显示最后一个虚假节点
  lastNodeShowBottom: false,//是否显示最后一个虚假节点
  lastNode: {
  	operation: "",//操作
  	operator: "",//操作人
  },
  };
  Vue.component('fishbone',function (resolve, reject) {
    $.get(comp_src+'/compnents/private/fishbone/fishbone.html').then(function (res) {
      resolve({
        template:res,
        data:function () {
          return data;
        },
        created: function() {
        	if(fishboneType == 'auditGrad'){
        		this.fishboneType = "1";
        		this.queryNodeList(this);
        	}else{
        		this.fishboneType = "2";
        		this.queryNodeList(this);
        	}
        },
        methods:{
        	queryNodeList: function(_self){
        		var url="";//鱼骨图
        		if(_self.fishboneType == "2"){
        			//获取申请处理信息的鱼骨图
        			url="node/queryNodeList";
        		}else if(_self.fishboneType == "1"){
        			//获取审核鱼骨图
        			url="node/queryExamineNodeList";//鱼骨图
        		}
        		var dataparmars={
        				"systemId": systemId,//鱼骨图的系统id
        		};
        		ajaxMethod(_self,"post",url,false,JSON.stringify(dataparmars),"json",'application/json;charset=UTF-8', _self.listSuccess);
        	},
          listSuccess:function(_self,dataList){
            data.result=dataList;
            //如果是审核页签，判断是否显示最后的虚假节点
            if(_self.fishboneType == "1"){
            	_self.whetherQueryNextOperators(_self);
            	if(data.result.data.length%2 == 0){
            		_self.lastNodeShowTop = true;
            		_self.lastNodeShowBottom = false;
            	}else{
            		_self.lastNodeShowTop = false;
            		_self.lastNodeShowBottom = true;
            	}
            }else{
            	_self.lastNodeShowTop = false;
          		_self.lastNodeShowBottom = false;
            }
          },
          whetherQueryNextOperators: function(_self){
          	_self.lastNodeShow = true;
          },
          //获取下一步操作人信息
          queryNextOperators: function(_self){
          	var url="node/queryNextNode";//鱼骨图
        		var _self=this;
        		var dataparmars={
        				"systemId": systemId,//鱼骨图的系统id
        		};
        		ajaxMethod(_self,"post",url,false,JSON.stringify(dataparmars),"json",'application/json;charset=UTF-8', _self.queryNextOperatorsSuccess);
          },
          //获取下一步操作人信息成功
          queryNextOperatorsSuccess: function(_self,responseData){
          	_self.lastNode = responseData.data;
          	if(_self.lastNode.operation == ""){
          		_self.lastNodeShowTop = false;
          		_self.lastNodeShowBottom = false;
          	}
          },
          closesDir:function(){
        	  $('.revokeBgs').css('display','none');
          },
          
          //鱼骨图弹窗：隐藏弹窗
         /* closes:function (msg) {
          	if(msg == 1 || msg == "1"){
          		var evaluationAlert=document.getElementsByClassName("evaluationAlert")[0];
          	}
          	if(msg == 2 || msg == "2"){
          		var evaluationAlert=document.getElementsByClassName("evaluationAlert")[1];
          	}
            evaluationAlert.style.display="none";
          },*/
          //弹窗：显示弹窗
          showDialog:function(itemdata){
          	var url = "node/queryNode";
          	var _self=this;
          	var dataparmars={
          			"nodeId":itemdata
          	};
          	if(itemdata!=null){
          		ajaxMethod(_self,"post",url,false,JSON.stringify(dataparmars),"json",'application/json;charset=UTF-8', _self.querySuccess)
          	}
          },
          querySuccess:function(_self,responseData){
          	data.result1=responseData.data;
          	if(responseData.data.operation == '申请变更'){
          		$("#dialogFishBone").css('display','block');
          		}
          	if(responseData.data.operation == '撤销备案'){
          		this.queryRevokeRecordsInfo(responseData.data.systemId);
          	}
          },
          //查询撤销备案
          queryRevokeRecordsInfo:function(msg){
          	var url = "records/queryRevokeRecords";
          	var _self=this;
          	var dataparmars={
          			"fkSystemId":msg
          	};
          	if(msg!=null){
          		ajaxMethod(_self,"post",url,false,JSON.stringify(dataparmars),"json",'application/json;charset=UTF-8', _self.queryRevokeRecordsInfoSuccess)
          	}
          },
          queryRevokeRecordsInfoSuccess : function(_self,responseData){
          	data.result1 = responseData.data;
          	if(responseData.data.fkrevokematter == "4"){
          		responseData.data.fkrevokematter = "撤销备案";
          	}
          	$("#dialog1").css("display","block");
          	
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
        xuanfu: function(meg){
        	this.option = meg;
        }

        },

        mounted: function() {
        	var _self = this;
        	bus.$on('fishbone',function(meg){
            if(meg == 'audit'){
            	//为审核页签获取鱼骨图信息
            	_self.fishboneType = "1";
            }else{
            	//为处理信息详情获取鱼骨图信息
            	_self.fishboneType = "2";
            }
            _self.queryNodeList(_self);
          });
        }
      })
    })
  })
}());