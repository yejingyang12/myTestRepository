
//描述
    $('#textArea').on("keyup",function(){
        $('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
        if($('#textArea').val().length > 200){
            $('#textNum').text(200);//长度大于200时0处显示的也只是200
            $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
        }
    });
(function () {
    var data={};
    Vue.component('revokeRecord',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/revokeRecord/revokeRecord.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return {

                    }
                },
                methods:{
                    closes:function () {
                        $('.wrap').addClass('cover');
                    }
                },
                created: function() {

                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())