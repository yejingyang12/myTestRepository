
$(function () {

    Vue.directive('prompt', function (el, binding) {
        if(binding.value.type == 'textnull'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");

                    $(el).parent().find(".prompttext").css({
                        "position": "absolute",
                        "top": "130px",
                        "left": "0px",
                        "line-height": "15px",
                        "color": "red",
                        "width": "100px",
                        "text-align": "left",
                        "font-size":"12px"
                    });
                }
                $(el).blur(function () {
                    if(!$(el).val()){
                        $(el).parent().find(".prompttext").show(0);
                    }else {
                        $(el).parent().find(".prompttext").hide(0);
                    }
                });
            },100);
        }else if(binding.value.type == 'null'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
                }
                $(el).parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "35px",
                    "left": "0px",
                    "line-height": "15px",
                    "color": "red",
                    "width": "100px",
                    "text-align": "left",
                    "font-size":"12px"
                });
                $(el).blur(function () {
                    if(!$(el).val()){
                        $(el).parent().find(".prompttext").show(0);
                    }else {
                        $(el).parent().find(".prompttext").hide(0);
                    }
                });
            },100);
        }else if(binding.value.type == 'file'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>上传文件不能为空</span>");
                }
                $(el).parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "41px",
                    "left": binding.value.left,
                    "line-height": "15px",
                    "color": "red",
                    "width": "100px",
                    "text-align": "left",
                    "font-size":"12px"
                });
            },100);
        }else if(binding.value.type == 'files'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>上传文件不能为空</span>");
                }
                $(el).parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "35px",
                    "left": binding.value.left,
                    "line-height": "15px",
                    "color": "red",
                    "width": "200px",
                    "text-align": "left",
                    "font-size":"12px"
                });
            },100);
        }else if(binding.value.type == 'choice'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>选择状态</span>");
                }
                $(el).parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "36px",
                    "left": "200px",
                    "line-height": "15px",
                    "color": "red",
                    "width": "100px",
                    "text-align": "left",
                    "font-size":"12px"
                });
            },100);
        }else if(binding.value.type == 'nulls'){
            setTimeout(function () {
                if(!$(el).parent().find(".prompttext")[0]){
                    $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");
                }
                $(el).parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "35px",
                    "left": "0px",
                    "line-height": "15px",
                    "color": "red",
                    "width": "100px",
                    "text-align": "left",
                    "font-size":"12px"
                });
            },100);
        }else if(binding.value.type == 'radio'){
            setTimeout(function () {
                if(!$(el).find(".prompttexts")[0]){
                    $(el).append('<span class="prompttexts"></span>');
                    $(el).find(".prompttexts").css({
                        "position": "absolute",
                        "top": "-15px",
                        "right": "128px",
                        "line-height": "15px",
                        "color": "red",
                        "width": "100px",
                        "text-align": "left",
                        "font-size":"12px"
                    });
                    var inputnamecheckox = $(el).find(':input[name=' + binding.value.name0 + ']')
                    var inputnamecheckox1 = $(el).find(':input[name=' + binding.value.name1 + ']')
                    $(el).find(':input[name=' + binding.value.name0 + ']').change(function () {
                        if(!inputnamecheckox[0].checked && !inputnamecheckox[1].checked && !inputnamecheckox[2].checked && !inputnamecheckox[3].checked &&　!inputnamecheckox[4].checked){
                            $(".prompttexts").html("选择业务信息");
                        }else if(!inputnamecheckox1[0].checked && !inputnamecheckox1[1].checked && !inputnamecheckox1[2].checked && !inputnamecheckox1[3].checked　&&　 !inputnamecheckox[4].checked){
                            $(".prompttexts").html("选择系统信息");
                        }else {
                            $(".prompttexts").html("");
                        }
                    });
                    $(el).find(':input[name=' + binding.value.name1 + ']').change(function () {
                        if(!inputnamecheckox[0].checked && !inputnamecheckox[1].checked && !inputnamecheckox[2].checked && !inputnamecheckox[3].checked &&　!inputnamecheckox[4].checked){
                            $(".prompttexts").html("选择业务信息");
                        }else if(!inputnamecheckox1[0].checked && !inputnamecheckox1[1].checked && !inputnamecheckox1[2].checked && !inputnamecheckox1[3].checked　&&　 !inputnamecheckox[4].checked){
                            $(".prompttexts").html("选择系统信息");
                        }else {
                            $(".prompttexts").html("");
                        }
                    });

                }

            },100);




            // $(el).append('<span class="prompttexts"></span>')

            // console.log($(el).find(':input[name=' + binding.value.name0 + ']'));
            // console.log($(el).find(':input[name=' + binding.value.name1 + ']'));

        }
    });
    Vue.directive('updatas', function (el, binding) {
        $(el).click(function () {
            if(!$("#textArea").val()){
                $("#textArea").parent().find(".prompttext").show(0);
            }
            if(!$("#rankTime").val()){
                $("#rankTime").parent().find(".prompttext").show(0);
            }
            if(!$($("#baseMes1").find(".col-md-6")[0]).hasClass("btnColor") && !$($("#baseMes1").find(".col-md-6")[1]).hasClass("btnColor")){
                $("#baseMes1").parent().find(".prompttext").show(0);
            }
            if(!$($("#baseMes2").find(".col-md-6")[0]).hasClass("btnColor") && !$($("#baseMes2").find(".col-md-6")[1]).hasClass("btnColor")){
                $("#baseMes2").parent().find(".prompttext").show(0);
            }
            $("input[type='file']").each(function (index,ele) {
                if(!$(ele).val()){
                    $(ele).parent().find(".prompttext").show(0);
                }
            });
            $(".promptinput").each(function (index,ele) {
                if(!$(ele).val()){
                    $(ele).parent().find(".prompttext").show(0);
                }
            });
            if(!$("#competentNameinput").val()){
                $("#competentNameinput").parent().find(".prompttext").show(0);
            }
            var inputnamecheckox = $('input[name=inform]')
            console.log(inputnamecheckox);
            var inputnamecheckox1 = $('input[name=inorm]')
            if(!inputnamecheckox[0].checked && !inputnamecheckox[1].checked && !inputnamecheckox[2].checked && !inputnamecheckox[3].checked &&　!inputnamecheckox[4].checked){
                $(".prompttexts").html("选择业务信息");
            }else if(!inputnamecheckox1[0].checked && !inputnamecheckox1[1].checked && !inputnamecheckox1[2].checked && !inputnamecheckox1[3].checked　&&　 !inputnamecheckox[4].checked){
                $(".prompttexts").html("选择系统信息");
            }else {
                $(".prompttexts").html("");
            }
        });
    });

    var setintervaltim=setInterval(function () {
        if($("#rankTime")[0]){
            clearInterval(setintervaltim);
            $("#rankTime").parent().append("<span style='display: none' class='prompttext'>不能为空</span>")
            $("#rankTime").parent().find(".prompttext").css({
                "position": "absolute",
                "top": "35px",
                "left": "0px",
                "line-height": "15px",
                "color": "red",
                "width": "100px",
                "text-align": "left",
                "font-size":"12px"
            });
            $("#rankTime").blur(function () {
                setTimeout(function () {
                    if(!$("#rankTime").val()){
                        $("#rankTime").parent().find(".prompttext").show(0);
                    }else {
                        $("#rankTime").parent().find(".prompttext").hide(0);
                    }
                },200);
            });
        }
    },300);
    setTimeout(function () {
        $($("#baseMes2").find(".col-md-6")[0]).click(function () {
            $("#baseMes2").parent().find(".prompttext").hide(0);
        });
        $($("#baseMes2").find(".col-md-6")[1]).click(function () {
            $("#baseMes2").parent().find(".prompttext").hide(0);
        });
        $($("#baseMes1").find(".col-md-6")[0]).click(function () {
            $("#baseMes1").parent().find(".prompttext").hide(0);
        });
        $($("#baseMes1").find(".col-md-6")[1]).click(function () {
            $("#baseMes1").parent().find(".prompttext").hide(0);
        });
        $("input[type='file']").change(function () {
            if($(this).val()){
                $(this).parent().find(".prompttext").hide(0);
            }
        });
        $(".promptinput").parent().click(function () {
            // if()
            $(this).parent().find(".prompttext").hide(0);

        });
    },300);




})