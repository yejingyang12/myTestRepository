var src_ele = document.querySelector('#src-qianhou');
var comp_src = "";
if(src_ele.dataset) {
	comp_src = src_ele.dataset.src
} else {
	comp_src = $('#src-qianhou').data('src')
}
console.log(comp_src);
var origin = 'http://shuju:8083/';
//var origin = window.location.origin+"/";

//var origin = ''+"/";

// ajax 请求

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
	this.FnSetParam(param);
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