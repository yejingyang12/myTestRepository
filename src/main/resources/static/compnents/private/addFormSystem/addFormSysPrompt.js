
    $(function () {

        Vue.directive('prompt', function (el, binding) {
            if(binding.value.type == 'choice'){

                setTimeout(function () {
                    if(!$(el).parent().find(".prompttext")[0]){
                        $(el).parent().append("<span style='display: none' class='prompttext'>选择状态</span>");
                        $(el).parent().find(".prompttext").css({
                            "position": "absolute",
                            "top": "36px",
                            "left": "162px",
                            "line-height": "15px",
                            "color": "red",
                            "width": "100px",
                            "text-align": "left",
                            "font-size":"12px"
                        });
                        $(el).find(".baseMes").click(function () {
                            $(el).parent().find(".prompttext").hide(0);
                        });
                    }

                },100);
            }else if(binding.value.type == 'textnull'){
                setTimeout(function () {
                    if(!$(el).parent().find(".prompttext")[0]){
                        $(el).parent().append("<span style='display: none' class='prompttext'>不能为空</span>");

                        $(el).parent().find(".prompttext").css({
                            "position": "absolute",
                            "top": "160px",
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
                    if(!$(el).parent().find(".prompttextnull")[0]){
                        $(el).parent().append("<span style='display: none' class='prompttextnull'>不能为空</span>");
                        $(el).parent().find(".prompttextnull").css({
                            "position": "absolute",
                            "bottom": "-15px",
                            "left": "0px",
                            "line-height": "15px",
                            "color": "red",
                            "width": "100px",
                            "text-align": "left",
                            "font-size":"12px"
                        });
                        $(el).parent().click(function () {
                            $(el).parent().find(".prompttextnull").hide(0);
                        });
                    }

                },100);
            }else if(binding.value.type == 'nulls'){
                setTimeout(function () {
                    if(!$(el).parent().find(".prompttextnulls")[0]){
                        $(el).parent().append("<span style='display: none' class='prompttextnulls'>不能为空</span>");
                    }
                    $(el).parent().find(".prompttextnulls").css({
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
                        if(!$(el).val()){
                            $(el).parent().find(".prompttextnulls").show(0);
                        }else {
                            $(el).parent().find(".prompttextnulls").hide(0);
                        }
                    });
                },200);
            }else if(binding.value.type == "select"){
                setTimeout(function () {
                    if(!$(el).find(".prompttextselect")[0]){
                        $(el).append("<span style='display: none' class='prompttextselect'>不能为空</span>");
                        $(el).find(".prompttextselect").css({
                            "position": "absolute",
                            "top": "35px",
                            "left": "14px",
                            "line-height": "15px",
                            "color": "red",
                            "width": "100px",
                            "text-align": "left",
                            "font-size":"12px"
                        });
                        $(el).find(".el-input__inner").click(function () {
                            // console.log(11);
                            $(el).find(".prompttextselect").hide();
                        });
                    }


                },200);
            }else if(binding.value.type == "selectandinput"){

              setTimeout(function () {
                if(!$(el).find(".prompttextss")[0]){
                  var inputs = $(el).find("input");
                  var inputsnum=inputs.length;
                  var select = $(el).find("select");
                  console.log(inputs)
                  console.log(select)
                  var selectornum = select.length;
                  $(el).append("<span style='display: none' class='prompttextss'>不能为空</span>")
                  $(el).find(".prompttextss").css({
                      "position": "absolute",
                      "bottom": "-15px",
                      "left": "163px",
                      "line-height": "15px",
                      "color": "red",
                      "width": "150px",
                      "text-align": "left",
                      "font-size":"12px"
                  })
                  var inputsnum1=0;
                  var selectornum1=0;
                  // $(el).find(".prompttextss").show(0);
                  inputs.each(function (index,ele) {
                      if(!$(ele).val()){
                          inputsnum1++;
                      }
                  });
                  select.each(function (index,ele) {
                      if($(ele).val() != "请选择"){
                          selectornum1++;
                          
                      }
                  });
                  if(inputsnum != inputsnum1 || selectornum != selectornum1){
                      $(el).find(".prompttextss").show(0);
                  }else{
                    $(el).find(".prompttextss").show(0);
                  }
                  
                }
                
                
                
              },200);

          }

        });
        Vue.directive('updatas', function (el, binding) {

            $(el).click(function () {
                if(!$($("#baseMes1").find(".baseMes")[0]).hasClass("btnColor") && !$($("#baseMes1").find(".baseMes")[1]).hasClass("btnColor") && !$($("#baseMes1").find(".baseMes")[2]).hasClass("btnColor")){
                    $("#baseMes1").parent().find(".prompttext").show(0);
                }
                if(!$($("#baseMes2").find(".baseMes")[0]).hasClass("btnColor") && !$($("#baseMes2").find(".baseMes")[1]).hasClass("btnColor")){
                    $("#baseMes2").parent().find(".prompttext").show(0);
                }
                if(!$($("#baseMes3").find(".baseMes")[0]).hasClass("btnColor") && !$($("#baseMes3").find(".baseMes")[1]).hasClass("btnColor")){
                    $("#baseMes3").parent().find(".prompttext").show(0);
                }
                if(!$($("#sysSubsystem").find(".baseMes")[0]).hasClass("btnColor") && !$($("#sysSubsystem").find(".baseMes")[1]).hasClass("btnColor")){
                    $("#sysSubsystem").parent().find(".prompttext").show(0);
                }
                // $(".inputtextbutton").find(".el-input__inner").each(function (index,ele) {
                //     if(!$(ele).val()){
                //         $(ele).parent().find(".prompttext").show(0);
                //     }
                // });v-prompt="{type:'select'}"
                // console.log($(".prompttextselect").parent().find("input")[0]);
                // if(!$(".prompttextselect").parent().find("input").val()){
                //
                //     // console.log($(".prompttextselect").parent().val());
                //     //
                //     // $(".prompttextselect").show(0);
                // }
                $(".prompttextselect").parent().find("input").each(function (index,ele) {
                    if(!$(ele).val()){
                        $(ele).parent().parent().parent().find(".prompttextselect").show(0);
                    }
                })
                if(!$("#sysBusDescription").val()){
                    $("#sysBusDescription").parent().find(".prompttext").show();
                }
                if(!$("#whenInvestmentUseinput").val()){
                    $("#whenInvestmentUseinput").parent().find(".prompttext").show(0);
                }
                $(".prompttextnull").parent().each(function (index,ele) {
                   if(!$(ele).find("input").val()){
                       $(ele).find(".prompttextnull").show(0);
                   }
                });
                $(".prompttextnulls").parent().each(function (index,ele) {
                    if(!$(ele).find("input").val()){
                        $(ele).parent().find(".prompttextnulls").show(0);
                    }
                });
            });
        })

        var setintervaltim=setInterval(function () {

            if($("#whenInvestmentUseinput")[0]){
                clearInterval(setintervaltim);
                $("#whenInvestmentUseinput").parent().append("<span style='display: none' class='prompttext'>不能为空</span>")
                $("#whenInvestmentUseinput").parent().find(".prompttext").css({
                    "position": "absolute",
                    "top": "35px",
                    "left": "16px",
                    "line-height": "15px",
                    "color": "red",
                    "width": "100px",
                    "text-align": "left",
                    "font-size":"12px"
                });
                $("#whenInvestmentUseinput").blur(function () {
                    setTimeout(function () {
                        if(!$("#whenInvestmentUseinput").val()){
                            $("#whenInvestmentUseinput").parent().find(".prompttext").show(0);
                        }else {
                            $("#whenInvestmentUseinput").parent().find(".prompttext").hide(0);
                        }
                    },200);
                });
            }
        },300);


    });