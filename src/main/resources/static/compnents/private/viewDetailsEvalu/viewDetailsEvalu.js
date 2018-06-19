/**
 * Created by timha on 2018/6/7.
 */
(function() {
  var data ={
  		evaluParam:{
  			"fkSystemId": null,
  			"pageSize": 10,
  			"currentPage": 1
  		},
  		result:{
  			"code": "0",
  			"msg": "成功",
  			"pagesize": 1,
  			"currentPage": 1,
  			"total": 5,
  			"totalPages": 5,
  			"data": [],
  		},
      evaluList:[],//测评List
      result:{}
  };
    Vue.component('viewDetailsEvalu',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsEvalu/viewDetailsEvalu.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                created: function() {
                	this.evaluParam.fkSystemId = systemId;
                	this.queryEvaluationList(this);
                },
                mounted: function() {
                    
                },
                methods:{
                  download:function(fileId){
                  	window.location.href=originUrl+"fileHandle/downloadFile?fileId="+fileId;
                  },
                  listSuccess : function(_self,data){
                  },
                  //上一页下一页点击事件
                  clickPage: function (page) {
                    if (page <= 0) {
                      alert("当前页面已经是第一页")
                    } else if (page > data.result.totalPages) {
                      alert("当前页面已经是最后一页")
                    } else {
                      this.created(page);
                    }
                  },
                  queryEvaluationList: function (_self) {
                  	ajaxMethod(_self, "post", 
                  			"evaluation/queryEvaluationList", true ,
                  			JSON.stringify(_self.evaluParam), "json", 
                  			'application/json;charset=UTF-8', 
                  			_self.queryEvaluationListSuccess);
                  },
                  queryEvaluationListSuccess :function (_self,responseData) {
                  	_self.evaluList = responseData.data;
                  	_self.result = responseData;
                  },
                //分页跳转
                  hpageNum:function(_this){
                    var a=$("#txt").val();
                    if(a<=0){
                      ("请输入正确页数");
                    	this.evaluParam.currentPage = 1;
                    	this.queryEvaluationList(this);
                    }else if(a>data.result.totalPages){
                    	this.evaluParam.currentPage = data.result.totalPages;
                    	this.queryEvaluationList(this);
                    }else{
                    	this.evaluParam.currentPage = a;
                    	this.queryEvaluationList(this);
                    }
                  },
              }
            })
        })
    })
})();