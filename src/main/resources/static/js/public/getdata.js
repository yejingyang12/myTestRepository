var src_ele = document.querySelector('#src-qianhou');
var comp_src = "";
if(src_ele.dataset) {
	comp_src = src_ele.dataset.src
} else {
	comp_src = $('#src-qianhou').data('src')
}
var originUrl = 'http://cpro.smcc.sinopec.com:8082/';

// ajax 请求
/**
 * @param _self: 本页this，
 * @param type: 请求方式，get or post
 * @param url: 请求地址
 * @param async: 同步，异步，异步为true，同步为false，默认为true
 * @param data: 数据参数
 * @param dataType: 要求为String类型的参数，预期服务器返回的数据类型。如果不指定，JQuery将自动根据http包mime信息返回responseXML或responseText，并作为回调函数参数传递。
 * @param contentType:
 * @param successMethod: 回调的成功方法
 * @param errorMethod: 回调的失败方法
 * @param completeMethod: 回调的完成方法
 */
function ajaxMethod(_self, type, url, async ,data, dataType, contentType, successMethod) {
  $.ajax({
    type : type,
    url : originUrl+url,
    async : async,
    data : data,
    dataType : dataType,
    contentType : contentType,
    success : function(responsedata) {
      successMethod(_self, responsedata);
    },
    // error : function(XMLHttpRequest, textStatus, errorThrown) {
    //   errorMethod(_self, XMLHttpRequest, textStatus, errorThrown);
    // },
    // complete : function(XMLHttpRequest, textStatus) {
    //   //ajax处理完成时回调
    //   completeMethod(_self, XMLHttpRequest, textStatus);
    // }
  });
}

/**
 * @param _self: 本页this，
 * @param type: 请求方式，get or post
 * @param url: 请求地址
 * @param async: 同步，异步，异步为true，同步为false，默认为true
 * @param data: 数据参数
 * @param dataType: JSON。
 * @param successMethod: 回调的成功方法
 */
function ajaxUploadMethod(_self, type, url, async ,data, dataType, successMethod){
	
	$.ajax({
    type: type,
    url : originUrl+url, 
    async: async,
    data: data,
    dataType: dataType,
    cache: false,
    processData: false,
    contentType: false,
    success: function(response) {
    	successMethod(_self,response);
    },
    error: function(err) {
    }
  });
}

/**
 * @param returnJson: 功能权限json，
 * @param code: 功能code
 */
function getJurisdictionMethod(returnData,code){
  var boo = false;
//  code="0102010105"
//  var returnData = JSON.parse('{"code":null,"msg":null,"pagesize":0,"currentPage":0,"total":0,"totalPages":0,"data":[{"resourceId":"634648ee0725c579c3d0b367eb18de91","resourceUrl":"","resourceCode":"01","children":[{"resourceId":"9a1e9b69f2810d9256e791d9a95b3fca","resourceUrl":"","resourceCode":"0101","children":[{"resourceId":"e1e34a196a99b60b0b945346796a6c1b","resourceUrl":"","resourceCode":"010101","children":[{"resourceId":"d742e76ae715d85554fe0f42b080cdb4","resourceUrl":"http://secb.smcc.sinopec.com:8080/SecbNotice/desgin","resourceCode":"01010101","children":[{"resourceId":"98bd922ac9cd4af7bc843c57fd123c33","resourceUrl":"","resourceCode":"0101010108","hasChild":0,"resourceName":"列表地址","parentId":"d742e76ae715d85554fe0f42b080cdb4","resourceType":3}],"hasChild":1,"resourceName":"安全通报列表","parentId":"e1e34a196a99b60b0b945346796a6c1b","resourceType":2}],"hasChild":1,"resourceName":"安全通报","parentId":"9a1e9b69f2810d9256e791d9a95b3fca","resourceType":1},{"resourceId":"c2b3af0b9fde565e449a8a47484a9350","resourceUrl":"","resourceCode":"010102","children":[{"resourceId":"fe71064d146bcfeb610f34125db276d4","resourceUrl":"http://secb.smcc.sinopec.com:8080/SecbReport/unitDesgin","resourceCode":"01010201","children":[{"resourceId":"e8b63cf299be41b0ad441218aae3d6b2","resourceUrl":"","resourceCode":"0101020102","hasChild":0,"resourceName":"企业报告地址","parentId":"fe71064d146bcfeb610f34125db276d4","resourceType":3}],"hasChild":1,"resourceName":"企业安全报告","parentId":"c2b3af0b9fde565e449a8a47484a9350","resourceType":2}],"hasChild":1,"resourceName":"安全报告","parentId":"9a1e9b69f2810d9256e791d9a95b3fca","resourceType":1}],"hasChild":1,"resourceName":"通报管理","parentId":"634648ee0725c579c3d0b367eb18de91","resourceType":1},{"resourceId":"6a60c456cbdd29ab5a6cd67296d6fd65","resourceUrl":"","resourceCode":"0103","children":[{"resourceId":"428bece08386174e10447a7452113a95","resourceUrl":"","resourceCode":"010301","children":[{"resourceId":"0dea4a44922c4810babeb6c4275eae61","resourceUrl":"http://seci.smcc.sinopec.com:8081/seci/enterprise/list","resourceCode":"01030103","children":[{"resourceId":"330e1252a2104a57a4677fc95de57c37","resourceUrl":"","resourceCode":"0103010303","hasChild":0,"resourceName":"企业领导审批","parentId":"0dea4a44922c4810babeb6c4275eae61","resourceType":3},{"resourceId":"dcc3e6a701174f74af742771e3565e5e","resourceUrl":"","resourceCode":"0103010301","hasChild":0,"resourceName":"企业检查企业地址","parentId":"0dea4a44922c4810babeb6c4275eae61","resourceType":3}],"hasChild":1,"resourceName":"企业检查企业列表","parentId":"428bece08386174e10447a7452113a95","resourceType":2}],"hasChild":1,"resourceName":"企业检查","parentId":"6a60c456cbdd29ab5a6cd67296d6fd65","resourceType":1}],"hasChild":1,"resourceName":"安全检查","parentId":"634648ee0725c579c3d0b367eb18de91","resourceType":1},{"resourceId":"4193206312e221450260b59f6459d290","resourceUrl":"","resourceCode":"0102","children":[{"resourceId":"53257576e715f8dd8987b2166a46176b","resourceUrl":"","resourceCode":"010201","children":[{"resourceId":"23714bfbefc2adb18dbc571e5490c89d","resourceUrl":"http://cpro.smcc.sinopec.com:8082","resourceCode":"01020101","children":[{"resourceId":"8aa899747bb5d5ff08288ac2d1e8f4a1","resourceUrl":"","resourceCode":"0102010105","hasChild":0,"resourceName":"审核管理","parentId":"23714bfbefc2adb18dbc571e5490c89d","resourceType":3},{"resourceId":"7e38fbc78bf24369983dcc997e4e4b5b","resourceUrl":"","resourceCode":"0102010107","hasChild":0,"resourceName":"等级保护定级备案地址","parentId":"23714bfbefc2adb18dbc571e5490c89d","resourceType":3}],"hasChild":1,"resourceName":"等级保护定级备案列表","parentId":"53257576e715f8dd8987b2166a46176b","resourceType":2},{"resourceId":"3a64e2c4bda1c321f5003fbb75c8d6df","resourceUrl":"http://cpro.smcc.sinopec.com:8082/page/auditPage","resourceCode":"01020102","children":null,"hasChild":1,"resourceName":"等级保护审核管理","parentId":"53257576e715f8dd8987b2166a46176b","resourceType":2}],"hasChild":1,"resourceName":"等级保护","parentId":"4193206312e221450260b59f6459d290","resourceType":1}],"hasChild":1,"resourceName":"三同步管理","parentId":"634648ee0725c579c3d0b367eb18de91","resourceType":1}],"hasChild":1,"resourceName":"信息安全管理","parentId":"","resourceType":1}]}')
  var childrenOne = returnData.data[0].children;
  if(childrenOne==null){
    return false;
  }
  for(var j=0;j<childrenOne.length;j++){
    if(childrenOne[j].hasChild==0){
      if(childrenOne[j].resourceCode==code){
        boo = true;
        break;
      }
    }else{
      var childrenTwo = childrenOne[j].children;
      if(childrenTwo==null){
        return false;
      }
      for(var k=0;k<childrenTwo.length;k++){
        if(childrenTwo[k].hasChild==0){
          if(childrenTwo[k].resourceCode==code){
            break;
          }
        }else{
          var childrenThree = childrenTwo[k].children;
          if(childrenThree==null){
            break;
          }
          for(var l=0;l<childrenThree.length;l++){
            if(childrenThree[l].hasChild==0){
              if(childrenThree[l].resourceCode==code){
                boo = true;
                break;
              }
            }else{
              var childrenFour = childrenThree[l].children;
              if(childrenFour==null){
                break;
              }
              for(var a=0;a<childrenFour.length;a++){
                if(childrenFour[a].hasChild==0){
                  if(childrenFour[a].resourceCode==code){
                    boo = true;
                    break;
                  }
                }else{
                  var childrenFive = childrenFour[a].children;
                  if(childrenFive==null){
                    break;
                  }
                  for(var b=0;b<childrenFive.length;b++){
                    if(childrenFive[b].hasChild==0){
                      if(childrenFive[b].resourceCode==code){
                        boo = true;
                        break;
                      }
                    }else{
                      
                    }
                  }
                }
              }
              if(boo){
                break;
              }
            }
            if(boo){
              break;
            }
          }
        }
        if(boo){
          break;
        }
      }
    }
    if(boo){
      break;
    }
  }
  return boo;
}





// get 请求
function FnGetData(url, type) {
	var param = {};
	var that = this;
	this.FnSetParam(param)
	that.$nextTick(function() {
		$.get(origin + url, param, function(data) {
			if(type) that[type] = data
		})
	})
}

// post 请求
function FnPostData(url, type) {
	var param = {};
	var that = this;
	this.FnSetParam(param)
	$.ajax({
		url: origin + url,
		type: "POST",
		datType: "JSON",
		contentType: "application/json;charset=utf-8",
		data: JSON.stringify(param),
		success: function(data) {
			that.$nextTick(function() {
				if(type) that[type] = data
			})
		}
	})

}

// 上传 文件

function FnPostFile(url, formData, type) {
	var that = this;
	$.ajax({
		url: origin + url,
		type: 'POST',
		cache: false,
		data: formData,
		processData: false,
		contentType: false
	}).then(function(data) {
		that.$nextTick(function() {
			if(type) that[type] = data
		})
	})
}