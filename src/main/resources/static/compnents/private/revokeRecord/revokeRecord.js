/**
 * Created by timha on 2018/5/30.
 */
    function closes() {
        $('.revokeBg').addClass('cover');
    }

    //描述
    $('#textArea').on("keyup",function(){
        $('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
        if($('#textArea').val().length > 200){
            $('#textNum').text(200);//长度大于200时0处显示的也只是200
            $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
        }
    });