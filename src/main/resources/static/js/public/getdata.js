var src_ele = document.querySelector('#src-qianhou');
var comp_src = "";
if(src_ele.dataset) {
	comp_src = src_ele.dataset.src
} else {
	comp_src = $('#src-qianhou').data('src')
}
// console.log(comp_src);
var originUrl = 'http://cpro.smcc.sinopec.com:8082/';
//var origin = window.location.origin+"/";

//var origin = ''+"/";

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