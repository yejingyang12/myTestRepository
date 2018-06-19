/**
 * Created by timha on 2018/6/7.
 */
(function() {
    var data ={
    		recordCode:"",//备案编号
    		recordCompany:"",//备案单位
    		recordDate:"",//备案日期
    		acceptCompany:"",//受理备案单位
    		acceptDate:"",//受理日期
    		recordReportId:"",//信息系统安全等级保护备案证明Id
    		recordReportName:"",//信息系统安全等级保护备案证明名称
    		fkrevokematter :"",	//变更事项
    		revokereason:"",//撤销原因
    		revokeRecordsId:"",//撤销备案ID
    		revokeRecordsName:"",//撤销备案名称
    };
    Vue.component('viewDetailsRecord',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsRecord/viewDetailsRecord.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                created: function() {
                  var url="records/queryRecordsDetail";
                  var _self=this;
                  //	 列表请求数据
                  ajaxMethod(_self, "post", url, false ,'{"fkSystemId":"'+systemId+'"}', "json", 'application/json;charset=UTF-8', _self.createdSuccess);
                },
                mounted: function() {
                },
                methods:{
                	// 获取备案信息详情成功
                  createdSuccess : function(_self, recordsResult) {
                  	if(recordsResult.data != null){
                  		if(! this.isEmpty(recordsResult.data.recordCode)){
                    		this.recordCode = recordsResult.data.recordCode;
                    	}
                    	if(! this.isEmpty(recordsResult.data.recordCompany)){
                    		this.recordCompany = recordsResult.data.recordCompany;
                    	}
                    	if(! this.isEmpty(recordsResult.data.recordDate)){
                    		this.recordDate = recordsResult.data.recordDate;
                    	}
                    	if(! this.isEmpty(recordsResult.data.acceptCompany)){
                    		this.acceptCompany = recordsResult.data.acceptCompany;
                    	}
                    	if(! this.isEmpty(recordsResult.data.acceptDate)){
                    		this.acceptDate = recordsResult.data.acceptDate;
                    	}
                    	if(! this.isEmpty(recordsResult.data.recordReportId)){
                    		this.recordReportId = recordsResult.data.recordReportId;
                    	}
                    	if(! this.isEmpty(recordsResult.data.recordReportName)){
                    		this.recordReportName = recordsResult.data.recordReportName;
                    	}
                    	if(! this.isEmpty(recordsResult.data.fkrevokematter)){
                    		this.fkrevokematter = recordsResult.data.fkrevokematter;
                    	}
                    	if(! this.isEmpty(recordsResult.data.revokereason)){
                    		this.revokereason = recordsResult.data.revokereason;
                    	}
                    	if(! this.isEmpty(recordsResult.data.revokeRecordsId)){
                    		this.revokeRecordsId = recordsResult.data.revokeRecordsId;
                    	}
                    	if(! this.isEmpty(recordsResult.data.revokeRecordsName)){
                    		this.revokeRecordsName = recordsResult.data.revokeRecordsName;
                    	}
                  	}	
                  },
                  download:function(fileId){
                  	window.location.href=originUrl+"fileHandle/downloadFile?fileId="+fileId;
                  },
                  //判断字符是否为空的方法
                  isEmpty: function (obj){
                       if(typeof obj == "undefined" || obj == null || obj == ""){
                           return true;
                       }else{
                           return false;
                       }
                   }
                },
               
            })
        })
    })
})();