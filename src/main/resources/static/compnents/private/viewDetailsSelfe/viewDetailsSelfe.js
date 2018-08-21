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
    		tishi:"",
        selfList:[],//自查List
        resultBy:{},
        rowOne:null,//列表表头第一行的tr
   	    imgList:null//列表表头第一行的排序箭头
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
                  //表格排序需要获取的元素
                  var rowOne=document.getElementsByClassName('rowOneSelf')[0];
                  var imgList=rowOne.getElementsByTagName('img');
                  data.imgList=imgList;
                	this.listsort();
                },
                methods:{
                	xuanfu:function(data){
                		this.tishi=data;
                	},
                  download:function(fileId){
                  	window.location.href=originUrl+"fileHandle/downloadFile?fileId="+fileId;
                  },
                  listSuccess : function(_self,data){
                  },
                  //上一页下一页点击事件
                  clickPage: function (page) {
                    if (page <= 0) {
                    } else if (page > data.result.totalPages) {
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
                  	_self.selfList = responseData.data.list;
                  	_self.result.currentPage = responseData.data.currPage;
                  	_self.result.pagesize = responseData.data.pageSize;
                  	_self.result.total = responseData.data.totalCount;
                  	_self.result.totalPages = responseData.data.totalPage;
                  	_self.resultBy = responseData.data;
                  	var selfListLength = _self.selfList.length;
                  	for(var i=0;i<selfListLength;i++){
                  		if(_self.selfList[i].inspectionDate == '1970-01-01'){
                  			_self.selfList[i].inspectionDate = "";
                  		}
                  		if(_self.selfList[i].rectificationDate == '1970-01-01'){
                  			_self.selfList[i].rectificationDate = "";
                  		}
                  	}
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
                  //自查信息列表排序
                  listsort: function () {
                    var imgArrow = data.imgList;
                    var flagOne = 1;
                    // console.log(data.result.data);
                    for (var i = 0; i < imgArrow.length; i++) {
                      imgArrow[i].myindex = i;
                      imgArrow[i].onclick = function () {
                        flagOne *= -1;
                        //对每个数组也就是对应表格的每一列进行排序
                        switch (this.myindex){
                          case 0://系统名称
                            data.result.list.sort(function (a, b) {
                              return (a.selfInspectionName.localeCompare(b.selfInspectionName)) * flagOne
                            });
                            break;
                          case 1://自查时间
                            data.resultBy.list.sort(function (a, b) {
                            	return (new Date(a.inspectionDate.split('-').join('/')).getTime()-new Date(b.inspectionDate.split('-').join('/')).getTime()) * flagOne
                            });
                            break;
                          case 2://自查状态
                            data.resultBy.list.sort(function (a, b) {
                              return (a.fkInspectionStatus - b.fkInspectionStatus) * flagOne
                            });
                            break;
                          case 3://自查结果
                            data.resultBy.list.sort(function (a, b) {
                              return (a.fkInspectionReu - b.fkInspectionReu) * flagOne
                            });
                            break;
                          case 4://整改结果
                            data.resultBy.list.sort(function (a, b) {
                              return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                            });
                            break;
                          case 5://整改时间
                            data.resultBy.list.sort(function (a, b) {
                              return (new Date(a.rectificationDate.split('-').join('/')).getTime()-new Date(b.rectificationDate.split('-').join('/')).getTime()) * flagOne
                            });
                            break;
                        }
                      };
                    }
                  },
                },
            })
        })
    })
})();