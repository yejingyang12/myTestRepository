Vue.directive('prompt', function (el, binding) {

    if(binding.value.type == 'null'){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
            }
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "100px",
                "text-align": "left",
                "font-size":"12px"
            })
            $(el).blur(function () {
                if(!$(el).val()){
                    $(el).parent().find(".prompttext").show(0);
                }else {
                    $(el).parent().find(".prompttext").hide(0);
                }
            });

        },200);

    }else if(binding.value.type == 'nulls'){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
            }
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "100px",
                "text-align": "left",
                "font-size":"12px"
            })
        },200);
    }else if(binding.value.type == "tel"){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>座机电话格式不正确</span>");
            }
            // $(el).parent().find(".prompttext").remove();
            // $(el).parent().append("<span style='display: none' class='prompttext'>座机电话格式不正确</span>");
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "150px",
                "text-align": "left",
                "font-size":"12px"
            })
            $(el).blur(function () {
                var telZZ = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
                if(!telZZ.test($(el).val())){
                    $(el).parent().find(".prompttext").show(0);
                }else {
                    $(el).parent().find(".prompttext").hide(0);
                }
            });
        },200);

    }else if(binding.value.type == "phone"){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>手机格式不正确</span>");
            }
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "150px",
                "text-align": "left",
                "font-size":"12px"
            })
            $(el).blur(function () {
                var phoneZZ = /^1[3|4|5|8][0-9]\d{4,8}$/;
                if(!phoneZZ.test($(el).val())){
                    $(el).parent().find(".prompttext").show(0);
                }else {
                    $(el).parent().find(".prompttext").hide(0);
                }
            });
        },200);
    }else if(binding.value.type == "postalCode"){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>邮编格式不正确</span>");
            }
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "150px",
                "text-align": "left",
                "font-size":"12px"
            })
            $(el).blur(function () {
                var postalCodeZZ = /^[a-zA-Z0-9 ]{3,12}$/;
                if(!postalCodeZZ.test($(el).val())){
                    $(el).parent().find(".prompttext").show(0);
                }else {
                    $(el).parent().find(".prompttext").hide(0);
                }
            });
        },200);

    }else if(binding.value.type == "compPrincipalEm"){
        setTimeout(function () {
            if(!$(el).parent().find(".prompttext")[0]){
                $(el).parent().append("<span style='display: none' class='prompttext'>电子邮件格式不正确</span>");
            }
            $(el).parent().find(".prompttext").css({
                "position": "absolute",
                "top": "32px",
                "left": "15px",
                "line-height": "15px",
                "color": "red",
                "width": "150px",
                "text-align": "left",
                "font-size":"12px"
            })
            $(el).blur(function () {
                var postalCodeZZ = /^[a-zA-Z0-9._%+-]+@(?!.*\.\..*)[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                if(!postalCodeZZ.test($(el).val())){
                    $(el).parent().find(".prompttext").show(0);
                }else {
                    $(el).parent().find(".prompttext").hide(0);
                }
            });
        },200);
    }

});
Vue.directive('updatas', function (el, binding) {
    $(el).click(function () {
        var prompttexts=$("form").find(".prompttext")
        prompttexts.each(function (index,ele) {
            if(!$(ele).parent().find("input").val()){
                $(ele).show(0);
            }
        });
    });
})
// jq部分抓取input clearInterval()
//
var setintervaltims=setInterval(function () {
    if($(".el-input__inner")[1]){
        clearInterval(setintervaltims);
        if(!$(".el-input__inner").parent().find(".prompttext")[1]){
            $(".el-input__inner").eq(1).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
            $(".el-input__inner").eq(2).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
        }
        $(".el-input__inner").parent().find(".prompttext").css({
            "position": "absolute",
            "top": "33px",
            "left": "0px",
            "line-height": "15px",
            "color": "red",
            "width": "100px",
            "text-align": "left",
            "font-size":"12px"
        });
        $(".el-input__inner").click(function () {
            $(this).parent().find(".prompttext").hide(0);
        });
        $("#baseMes2").click(function () {
            $("#baseMes2").find(".prompttext").hide(0);
        });
        $("#baseMes1").click(function () {
            $("#baseMes1").find(".prompttext").hide(0);
        })
        $("#baseMes3").click(function () {
            $("#baseMes3").find(".prompttext").hide(0);
        })
        // $(".el-input__inner").blur(function () {
        //
        //     if(!$(this).val()){
        //         $(this).parent().find(".prompttext").show(0);
        //     }else {
        //         $(this).parent().find(".prompttext").hide(0);
        //     }
        // });
    }
},500);