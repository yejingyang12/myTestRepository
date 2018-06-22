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
      result:{},
      rowOne:null,//列表表头第一行的tr
	    imgList:null,//列表表头第一行的排序箭头
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
                //表格排序需要获取的元素
                  var rowOne=document.getElementsByClassName('rowOne')[0];
                  var imgList=rowOne.getElementsByTagName('img');
                  data.imgList=imgList;
                	this.listsort();
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
                  //测评信息列表排序
                  listsort: function () {
                    var imgArrow = data.imgList;
                    var flagOne = 1;
                    // console.log(data.result.data);
                    for (var i = 0; i < imgArrow.length; i++) {
                      imgArrow[i].myindex = i;
                      imgArrow[i].onclick = function () {
                        flagOne *= -1;
                        // //对每个数组也就是对应表格的每一列进行排序
                        // console.log( data.result.data[0].systemName);
                        switch (this.myindex){
                          case 0://项目测评名称
                            data.result.data.sort(function (a, b) {
                              return (a.examName.localeCompare(b.examName)) * flagOne
                            });
                            break;
                          case 1://测评时间
                            data.result.data.sort(function (a, b) {
                            	return (new Date(a.examTime.split('-').join('/')).getTime()-new Date(b.examTime.split('-').join('/')).getTime()) * flagOne
                            });
                            break;
                          case 2://测评机构
                            data.result.data.sort(function (a, b) {
                              return (a.examOrg.localeCompare(b.examOrg)) * flagOne
                            });
                            break;
                          case 3://测评状态
                            data.result.data.sort(function (a, b) {
                              return (a.fkExamStatus - b.fkExamStatus) * flagOne
                            });
                            break;
                          case 4://测评结果
                            data.result.data.sort(function (a, b) {
                              return (a.fkExamResult - b.fkExamResult) * flagOne
                            });
                            break;
                          case 5://整改结果
                            data.result.data.sort(function (a, b) {
                              return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                            });
                            break;
                          case 6://整改时间
                            data.result.data.sort(function (a, b) {
                            	return (new Date(a.rectificationDate.split('-').join('/')).getTime()-new Date(b.rectificationDate.split('-').join('/')).getTime()) * flagOne
                            });
                            break;
                        }
                      };
                    }
                  },
              }
            })
        })
    })
})();