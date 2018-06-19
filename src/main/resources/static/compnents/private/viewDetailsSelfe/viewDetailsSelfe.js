/**
 * Created by timha on 2018/6/7.
 */
(function() {
    var data ={
    		selfParam:{
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
        selfList:[],//自查List
        result:{}
    };
    Vue.component('viewDetailsSelfe',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsSelfe/viewDetailsSelfe.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                created: function(page) {
                	this.selfParam.fkSystemId = systemId;
                	this.querySelfexaminationList(this);
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
                  querySelfexaminationList: function (_self) {
                  	ajaxMethod(_self, "post", 
                  			"selfexamination/querySelfexaminationList", true ,
                  			JSON.stringify(_self.selfParam), "json", 
                  			'application/json;charset=UTF-8', 
                  			_self.querySelfexaminationListSuccess);
                  },
                  querySelfexaminationListSuccess :function (_self,responseData) {
                  	_self.selfList = responseData.data;
                  	_self.result = responseData;
                  },    
                  //分页跳转
                  hpageNum:function(_this){
                    var a=$("#txt").val();
                    if(a<=0){
                      ("请输入正确页数");
                    	this.selfParam.currentPage = 1;
                    	this.querySelfexaminationList(this);
                    }else if(a>data.result.totalPages){
                    	this.selfParam.currentPage = data.result.totalPages;
                    	this.querySelfexaminationList(this);
                    }else{
                    	this.selfParam.currentPage = a;
                    	this.querySelfexaminationList(this);
                    }
                  },
                },
            })
        })
    })
})();