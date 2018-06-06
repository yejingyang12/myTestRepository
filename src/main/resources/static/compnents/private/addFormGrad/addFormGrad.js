
(function () {
    var data={
        sysName:'',//系统名称
        safetyPro:{//确定安全保护等级
            busInform:[//业务信息
                {"radioId":"selectAll","disRadioId":"onlyCheck"},
                {"radioId":"selAll","disRadioId":"onCheck"},
            ],
            sysInform:{//系统信息

            }
        },
        competent:[],//主管部门
        competentName:'',//主管部门名称
        competentGrad:[],//主管部门审批定级情况
        gradPresen:'',//定级报告
        gradPresenName:'',//定级报告名称
        expertReview:[],//专家评审
        expertReviewName:'',//专家评审情况
        fillFormPerson:'',//填表人
        fillData:{},//填表时间
        index:0,
        tat:0
    };
    $("#addFormGrad").validate({
        sysName:{
            required:true,
            sysName:true
        },
        safetyPro:{
            required:true,
            safetyPro:true
        },
        competent:{
            required:true,
            competent:true
        },
        competentName:{
            required:true,
            competentName:true
        },
        competentGrad:{
            required:true,
            competentGrad:true
        },
        gradPresen:{
            required:true,
            gradPresen:true
        },
        gradPresenName:{
            required:true,
            gradPresenName:true
        },
        expertReview:{
            required:true,
            expertReview:true
        },
        expertReviewName:{
            required:true,
            expertReviewName:true
        },
        fillFormPerson:{
            required:false,
            fillFormPerson:true
        },
        fillData:{
            required:false,
            fillData:true
        },
        onkeyup: false,
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
            //忽略自定义方法的错误提示
            if (error.text() == "ignore") {
                return '';
            }
        },
        errorElement: "span",
        submitHandler: function (form) {
            console.log(form);
            $(form).ajaxSubmit({
                url: "php/order.php",
                type: "post",
                success: function () {
                    alert("提交成功！");
                    $(".shadow").show();
                    $("#submit").click(function () {
                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                    })
                }
            })
        }
    });


    Vue.component('addFormGrad',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormGrad/addFormGrad.html').then(function(res){
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                    text:function(){
                        $('#textArea').on("keyup",function(){
                            $('#textNum').text($('#textArea').val().length);//这句是在键盘按下时，实时的显示字数
                            if($('#textArea').val().length > 200){
                                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                                $('#textArea').val($('#textArea').val().substring(0,200));//长度大于100时截取钱100个字符
                            }
                        })
                    },
                    getClass:function(e){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    },
                    btnBoolen(e){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                        if($(e.target).val() == '是'){
                            $("#direHide").show();
                        }else{
                            $("#direHide").hide();
                        }
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
                    },
                    checkFnAll:function (e) {
                        $(e.target).parent().siblings().find('input').attr('checked',true);
                        if(data.index != 0 || data.tag != 0){
                            var checkBus = document.getElementsByName('business');
                            var checkSys = document.getElementsByName('busine');
                            var checkGra = document.getElementsByName('Choice');
                            //循环判断业务信息级别
                            for(var i=0;i<checkBus.length;i++){
                                if(checkBus[i].checked == true){
                                    data.index = i;
                                }
                            }
                            //循环判断系统信息级别
                            for(var j=0;j<checkSys.length;j++){
                                if(checkSys[j].checked == true){
                                    data.tag = j;
                                }
                            }
                            //根据业务信息和系统信息的级别判断信息系统安全保护等级的级别
                            for(var z=0;z<checkGra.length;z++){
                                if(data.index == data.tag){
                                    z = data.index = data.tag;
                                    checkGra[z].checked = true;
                                    return;
                                }else if(data.index <= data.tag){
                                    z = data.tag;
                                    checkGra[z].checked = true;
                                    return;
                                }else if(data.index >= data.tag){
                                    z = data.index;
                                    checkGra[z].checked = true;
                                    return;
                                }
                                if(z>=2){
                                    $('.file').removeClass('material');
                                    $('.nextBut').removeClass('material');
                                    $('.saveBnt').addClass('material');
                                    $('.exec').addClass('btnColor');
                                }
                                if(z>=1){
                                    $('.revie').addClass('btnColor');
                                }
                            }
                        }
                    },
                    /*sysCheck:function (obj,ele,elm) {
                        var all=document.getElementsByClassName(obj);//获取到点击全选的那个复选框的id
                        var all1=document.getElementsByClassName(ele);//获取到点击全选的那个复选框的id
                        var one= document.getElementsByClassName(elm);//获取到复选框的名称;获得的是数组
                        //定义一个数组存储每个checkbox选中状态；默认都不选中
                        var arr=[];
                        //反选
                        for (var k = 0; k < one.length; k++) {
                            arr.push(false);//默认有多少列，就添加多少个false
                            one[k].a=k;
                            one[k].onclick=function () {
                                console.log(arr);
                                arr.splice(this.a,1,this.checked);
                                all.checked=arr.every(function (item) {
                                    return item});
                                all1.checked=arr.some(function (item) {
                                    return item});
                            };
                        }

                    }*/
                },
                created: function() {

                },
                mounted: function() {
                    // this.selectChange()
                    laydate.render({
                        elem: '#fillData'
                    });
                    laydate.render({
                        elem: '#gradData'
                    });
                }
            })
        })
    })
}())