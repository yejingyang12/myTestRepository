
(function () {
    var data={};
    Vue.component('record',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/record/record.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data
                },
                methods:{
                    downloadFile:function(str) {
                        if (!str) {//没有值
                            str = "";//默认接口
                        }
                        var $eleForm = $("<form method='post'></form>");
                        $eleForm.attr("action", str);
                        $(document.body).append($eleForm);
                        //提交表单，实现下载
                        $eleForm.submit();
                    },
                    changeLi:function(e) {
                        var index = -($(e.currentTarget).index()*100+8)+"px"+" -190px";
                        // console.log($(e.currentTarget).children("i").css('background-position',index));
                        $(e.currentTarget).children('i').css('background-position',index);
                        $(e.currentTarget).prevAll().children('i').css('background-position','-8px -190px');
                        if($(e.currentTarget).index() == 2){
                            $('.comitBtm').show();
                        }
                        //改div 显示和隐藏
                        $(".recordPro>div").eq($(e.currentTarget).index()).css("display","block").siblings("div").css("display","none");
                    },
                    getFile:function(obj, ele, elm){
                        var btn = $(obj);
                        var oInup = $(ele);
                        var fileName = $(elm);
                        var btnValue = btn.val();
                        var arr = [];
                        var str = btnValue.split("\\");
                        arr.push(str[str.length-1]);
                        //        console.log(str);
                        oInup.val(str[str.length-1]);
                        fileName.val(str[str.length-1]);
                        //        console.log(arr);
                        // 返回 KB，保留小数点后两位
                        var file = obj.value;
                        //        console.log(file);
                        if(!/.(word|pdf|exl|zip|rar|sep)$/.test(file)){
                            obj.value = '';
                            alert("文件类型必须是.word、pdf、exl、zip、rar、sep中的一种");
                            return false;
                        }else{
                            //返回Byte(B),保留小数点后两位
                            if(((obj.files[0].size).toFixed(2))>=(30*1024*1024)){

                                alert("请上传小于30M的文件");
                                return false;
                            }
                        }
                    }
                },
                created: function() {

                },
                mounted: function() {

                }
            })
        })
    })
}())