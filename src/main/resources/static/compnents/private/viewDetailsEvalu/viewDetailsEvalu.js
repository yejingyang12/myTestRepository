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
  		tishi:"",
  		showItem:10,
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
                computed:{
                	totalPages:function(){
                		var page = []; //显示页码
                        //用当前激活页面驱动页面显示的分别
                        if (this.result.currentPage < this.showItem) { //当前页小于最大页码数（showItem），区分总页数是否达到最大页码数
                            //获取总页数和最大页码数较小的值
                            var i = Math.min(this.result.totalPages, this.showItem);
                            while (i) {
                                //通过page的数组值显示页码
                                page.unshift(i--);
                            }
                        } else { //当前页面大于最大页码数（showItem）时，区分显示的页码规则
                            var pagestart = this.result.currentPage - Math.floor(this.showItem / 2); //获取显示的页码第一位页码（默认当前页居中）
                            var i = this.showItem; //用来显示多少（i）个页码
                            if (pagestart > (this.result.totalPages - this.showItem)) { //第一个页码如果大于总页数减去展示的页码数，则当前页不能居中
                                pagestart = (this.result.totalPages - this.showItem) + 1; //应该显示的第一个页码数
                            }
                            while (i--) {
                                //通过page的数组值显示页码
                                page.push(pagestart++);
                            }
                        }
                        return page;
                    }
                	
                },
                mounted: function() {
                //表格排序需要获取的元素
                  var rowOne=document.getElementsByClassName('rowOneTesing')[0];
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
                  	_self.evaluList = responseData.data.list;
                  	_self.result = responseData;
                  	var evaluListLength = responseData.data.list.length;
                  	for(var i=0;i<evaluListLength;i++){
                  		if(_self.evaluList[i].rectificationDate == '1970-01-01'){
                  			_self.evaluList[i].rectificationDate = "";
                  		}
                  		if(_self.evaluList[i].examTime == '1970-01-01'){
                  			_self.evaluList[i].examTime = "";
                  		}
                  	}
                  	
                  	_self.result.totalPages = responseData.data.totalPage;
                  	_self.result.pagesize = responseData.data.pageSize;
                  	_self.result.currentPage = responseData.data.currPage;
                  	_self.result.total = responseData.data.totalCount;

                  	
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
                            data.result.data.list.sort(function (a, b) {
                              return (a.examName.localeCompare(b.examName)) * flagOne
                            });
                            break;
                          case 1://测评时间
                            data.result.data.list.sort(function (a, b) {
                            	return (new Date(a.examTime.split('-').join('/')).getTime()-new Date(b.examTime.split('-').join('/')).getTime()) * flagOne
                            });
                            break;
                          case 2://测评机构
                            data.result.data.list.sort(function (a, b) {
                              return (a.examOrg.localeCompare(b.examOrg)) * flagOne
                            });
                            break;
                          case 3://测评状态
                            data.result.data.list.sort(function (a, b) {
                              return (a.fkExamStatus - b.fkExamStatus) * flagOne
                            });
                            break;
                          case 4://测评结果
                            data.result.data.list.sort(function (a, b) {
                              return (a.fkExamResult - b.fkExamResult) * flagOne
                            });
                            break;
                          case 5://整改结果
                            data.result.data.list.sort(function (a, b) {
                              return (a.fkRectificationReu - b.fkRectificationReu) * flagOne
                            });
                            break;
                          case 6://整改时间
                            data.result.data.list.sort(function (a, b) {
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