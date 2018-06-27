
    $(function () {
        Vue.directive('prompt', function (el, binding) {
            if(binding.value.type == 'file'){
                setTimeout(function () {
                    if(!$(el).parent().find(".prompttext")[0]){
                        $(el).parent().append("<span style='display: none' class='prompttext'>上传文件不能为空</span>");
                        $(el).parent().find(".prompttext").css({
                            "position": "absolute",
                            "bottom": "12px",
                            "right": "273px",
                            "line-height": "15px",
                            "color": "red",
                            "width": "100px",
                            "text-align": "left",
                            "font-size":"12px"
                        });
                    }
                    $(el).change(function () {
                        if($(el).val()){
                            $(el).parent().find(".prompttext").hide(0);
                        }else {
                            $(el).parent().find(".prompttext").show(0);
                        }
                    });
                },100);

            }
        });
        Vue.directive('updatas', function (el, binding) {
            $(el).click(function () {
               $(".prompttext").parent().find("input[type=file]").each(function (index,ele) {
                   if(!$(ele).val()){
                       $(ele).parent().find(".prompttext").show(0);
                   }
               });

            });

        });
    });