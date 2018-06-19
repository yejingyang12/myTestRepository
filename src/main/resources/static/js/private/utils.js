//  精确到日的日历
function getCalender(target) {
	target.datetimepicker({
		format: "yyyy-mm-dd",
		language: 'zh-CN',
		minView: 'month',
		/*    bootcssVer:3,*/
		showMeridian: true,
		autoclose: true,
		todayBtn: false,
		pickerPosition: "bottom-left"
	});
}

//精确到月的日历
function getMonthCalender(target) {
	target.datetimepicker({
		format: "yyyy-mm",
		language: 'zh-CN',
		startView: 'year',
		minView: 'year',
		/*    bootcssVer:3,*/
		showMeridian: true,
		autoclose: true,
		todayBtn: false,
		pickerPosition: "bottom-left"
	});
}

//select选中改变样式

function selectChange(target) {
	$(target).change(function() {
		$(this).addClass('dropdown-toggle')
	})
}

//新建基础信息
$.fn.extend({
	selectSegmentAt: function(selectedIndex) {
		var a_list = this.find('a')
		a_list.each(function(index, item) {
			if(index == selectedIndex) {
				$(item).addClass("hover").siblings().removeClass("hover")
			}
		})
	}
})

Array.prototype.remove = function(val) { 
	var index = this.indexOf(val); 
	if (index > -1) { 
		this.splice(index, 1); 
	} 
};

function getQueryObject(url) {
	url = url == null ? window.location.href : url;
	var search = url.substring(url.lastIndexOf("?") + 1);
	var obj = {};
	var reg = /([^?&=]+)=([^?&=]*)/g;
	search.replace(reg, function(rs, $1, $2) {
		var name = decodeURIComponent($1);
		var val = decodeURIComponent($2);
		val = String(val);
		obj[name] = val;
		return rs;
	});
	return obj;
}

