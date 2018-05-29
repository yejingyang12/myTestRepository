//  日历（精确到日）
function getCalender(target) {
  target.datetimepicker({
    format: "yyyy-mm-dd",
    language:  'zh-CN',
    minView: 'month',
    /*    bootcssVer:3,*/
    showMeridian: true,
    autoclose: true,
    todayBtn: false,
    pickerPosition: "bottom-left"
  });
}

//日历（精确到月）
function getMonthCalender(target) {
  target.datetimepicker({
    format: "yyyy-mm",
    language:  'zh-CN',
    startView: 'year',
    minView: 'year',
    /*    bootcssVer:3,*/
    showMeridian: true,
    autoclose: true,
    todayBtn: false,
    pickerPosition: "bottom-left"
  });
}