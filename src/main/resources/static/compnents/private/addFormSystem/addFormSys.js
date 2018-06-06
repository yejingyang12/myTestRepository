/**
 * Created by timha on 2018/5/21.
 */
(function () {
    var data={
        sysName:[],//系统名称
        sysCode:'',//系统标准化代码
        sysMsgBuild:[],//信息系统建设类型
        sysMerge:[],//是否为合并系统
        sysRecord:'',//等保备案系统名称
        sysInter:[],//是否为互联网应用
        sysType:[],//业务类型
        sysArea:'',//业务描述
        sysServiceType:[],//服务类型
        sysServiceObject:[],//服务对象
        sysCoverage:[],//覆盖范围
        sysNetwork:[],//网络性质
        sysIntercon:[],//系统互联情况
        sysComName:'',//所属单位名称
        useData:'',//何时投入使用
        sysOfficeName:'',//主管处室名称
        sysSubsystem:[],//系统是否为分系统
        sysPerson:'',//主管联系人
        sysPersonTel:'',//主管联系人电话
        num : 2 //定义一个变量
    };
    /*$("#AddformSystem").validate({
        rules: {
            sysName: {
                required: true,
                maxlength: 50,
                sysName:true
            },
            sysCode: {
                required: true,
                sysCode:true
            },
            sysMsgBuild: {
                required: true,
                minlength: 2,
                sysMsgBuild:true,
            },
            sysRecord: {
                required: true,
                maxlength: 100,
                sysRecord:true
            },
            sysInter: {
                required: true,
                sysInter:true
            },
            sysType: {
                required: false,
                sysType:true
            },
            sysArea: {
                required: true,
                rangelength: [1, 100],
                sysArea:true
            },
            sysServiceType: {
                required: true,
                email: true,
                sysServiceType:true
            },
            sysServiceObject: {
                required: true,
                sysServiceObject:true
            },
            sysCoverage: {
                required: true,
                maxlength: 11,
                isMobile: true,
                sysCoverage:true
            },
            sysNetwork: {
                required: true,
                sysNetwork:true
            },
            sysIntercon: {
                required: true,
                sysIntercon:true
            },
            sysComName: {
                required: true,
                sysComName:true
            },
            useData: {
                required: true,
                useData:true
            },
            sysOfficeName: {
                required: true,
                sysOfficeName:true
            },
            sysSubsystem: {
                required: true,
                email: true,
                sysSubsystem:true
            },
            sysPerson: {
                required: true,
                sysPerson:true
            },
            sysPersonTel: {
                required: true,
                sysPersonTel:true
            }
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
                    $(".confirm_btn").click(function () {
                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                    })
                }
            })
        },
    });
    //以下为自定义验证
    $.validator.addMethod("sysName", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的系统名称");

    $.validator.addMethod("sysRecord", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{50}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的等保备案系统名称");

    $.validator.addMethod("sysArea", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{200}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的业务描述");*/

    Vue.component('addFormSystem',function (resolve,reject) {
        $.get(comp_src+'/compnents/private/addFormSystem/addFormSystem.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return {
                        options: [
                            {
                            value: 'zhinan',
                            label: '指南',
                            children: [{
                                value: 'shejiyuanze',
                                label: '设计原则'
                            }, {
                                value: 'daohang',
                                label: '导航'
                            }]
                        }, {
                            value: 'zujian',
                            label: '组件',
                            children: [{
                                value: 'basic',
                                label: 'Basic'
                            }, {
                                value: 'form',
                                label: 'Form'
                            }, {
                                value: 'data',
                                label: 'Data'
                            }, {
                                value: 'notice',
                                label: 'Notice'
                            }, {
                                value: 'navigation',
                                label: 'Navigation'
                            }, {
                                value: 'others',
                                label: 'Others'
                            }]
                        }, {
                            value: 'ziyuan',
                            label: '资源',
                            children: [{
                                value: 'axure',
                                label: 'Axure Components'
                            }, {
                                value: 'sketch',
                                label: 'Sketch Templates'
                            }, {
                                value: 'jiaohu',
                                label: '组件交互文档'
                            }]
                        }],
                        data
                    };
                },
                methods:{
                    //点击切换 添加class名
                    getClass:function(e){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    },
                    btnBoolen(e){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                        if($(e.target).val() == '是'){
                            $("#falseSys").show();
                            $("#trueSys").hide();
                        }else{
                            $("#trueSys").show();
                            $("#falseSys").hide();
                        }
                    },
                    addSys:function (e) {
                        data.num ++;
                        var str = `<li class="row">
                                <div class="col-lg-6 col-md-6">
                                    <label class="col-lg-3 col-md-3 text-right">
                                        <span class="xing">*</span>
                                        子系统 ${data.num}系统名称:
                                    </label>
                                    <div class="col-lg-6 col-md-6 childSystem">
                                        <input type="text" placeholder="请输入">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-sm-3">
                                    <label for="" class="col-lg-3 col-md-3 text-right">
                                        子系统${data.num}标准化代码:
                                    </label>
                                    <div  class="col-md-6 col-sm-3">
                                        <input type="text" disabled>
                                    </div>
                                </div>
                            </li>`;
                        $('#add').before(str);
                    },
                    text:function(){
                        $('#sysArea').on("keyup",function(){
                            $('#textNum').text($('#sysArea').val().length);//这句是在键盘按下时，实时的显示字数
                            if($('#sysArea').val().length > 200){
                                $('#textNum').text(200);//长度大于200时0处显示的也只是200
                                $('#sysArea').val($('#sysArea').val().substring(0,200));//长度大于100时截取钱100个字符
                            }
                        })
                    },
                    clickForm:function () {
                        $("#AddformSystem").validate({
                         rules: {
                         sysName: {
                         required: true,
                         maxlength: 50,
                         sysName:true
                         },
                         sysCode: {
                         required: true,
                         sysCode:true
                         },
                         sysMsgBuild: {
                         required: true,
                         minlength: 2,
                         sysMsgBuild:true,
                         },
                         sysRecord: {
                         required: true,
                         maxlength: 100,
                         sysRecord:true
                         },
                         sysInter: {
                         required: true,
                         sysInter:true
                         },
                         sysType: {
                         required: false,
                         sysType:true
                         },
                         sysArea: {
                         required: true,
                         rangelength: [1, 100],
                         sysArea:true
                         },
                         sysServiceType: {
                         required: true,
                         email: true,
                         sysServiceType:true
                         },
                         sysServiceObject: {
                         required: true,
                         sysServiceObject:true
                         },
                         sysCoverage: {
                         required: true,
                         maxlength: 11,
                         isMobile: true,
                         sysCoverage:true
                         },
                         sysNetwork: {
                         required: true,
                         sysNetwork:true
                         },
                         sysIntercon: {
                         required: true,
                         sysIntercon:true
                         },
                         sysComName: {
                         required: true,
                         sysComName:true
                         },
                         useData: {
                         required: true,
                         useData:true
                         },
                         sysOfficeName: {
                         required: true,
                         sysOfficeName:true
                         },
                         sysSubsystem: {
                         required: true,
                         email: true,
                         sysSubsystem:true
                         },
                         sysPerson: {
                         required: true,
                         sysPerson:true
                         },
                         sysPersonTel: {
                         required: true,
                         sysPersonTel:true
                         }
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
                         $(".confirm_btn").click(function () {
                         window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                         })
                         }
                         })
                         },
                         });
                         //以下为自定义验证
                         $.validator.addMethod("sysName", function (value, element) {
                         var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
                         return this.optional(element) || (re.test(value));
                         }, "请正确填写您的系统名称");

                         $.validator.addMethod("sysRecord", function (value, element) {
                         var re = /^\w[()-[]:.{},;@，；。《》、（）]{50}$/;
                         return this.optional(element) || (re.test(value));
                         }, "请正确填写您的等保备案系统名称");

                         $.validator.addMethod("sysArea", function (value, element) {
                         var re = /^\w[()-[]:.{},;@，；。《》、（）]{200}$/;
                         return this.optional(element) || (re.test(value));
                         }, "请正确填写您的业务描述");
                    }
                },
                created: function() {
                    /*$("#AddformSystem").validate({
                        rules: {
                            sysName: {
                                required: true,
                                maxlength: 50,
                                sysName:true
                            },
                            sysCode: {
                                required: true,
                                sysCode:true
                            },
                            sysMsgBuild: {
                                required: true,
                                minlength: 2,
                                sysMsgBuild:true,
                            },
                            sysRecord: {
                                required: true,
                                maxlength: 100,
                                sysRecord:true
                            },
                            sysInter: {
                                required: true,
                                sysInter:true
                            },
                            sysType: {
                                required: false,
                                sysType:true
                            },
                            sysArea: {
                                required: true,
                                rangelength: [1, 100],
                                sysArea:true
                            },
                            sysServiceType: {
                                required: true,
                                email: true,
                                sysServiceType:true
                            },
                            sysServiceObject: {
                                required: true,
                                sysServiceObject:true
                            },
                            sysCoverage: {
                                required: true,
                                maxlength: 11,
                                isMobile: true,
                                sysCoverage:true
                            },
                            sysNetwork: {
                                required: true,
                                sysNetwork:true
                            },
                            sysIntercon: {
                                required: true,
                                sysIntercon:true
                            },
                            sysComName: {
                                required: true,
                                sysComName:true
                            },
                            useData: {
                                required: true,
                                useData:true
                            },
                            sysOfficeName: {
                                required: true,
                                sysOfficeName:true
                            },
                            sysSubsystem: {
                                required: true,
                                email: true,
                                sysSubsystem:true
                            },
                            sysPerson: {
                                required: true,
                                sysPerson:true
                            },
                            sysPersonTel: {
                                required: true,
                                sysPersonTel:true
                            }
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
                                    $(".confirm_btn").click(function () {
                                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                                    })
                                }
                            })
                        },
                    });
                    //以下为自定义验证
                    $.validator.addMethod("sysName", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的系统名称");

                    $.validator.addMethod("sysRecord", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{50}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的等保备案系统名称");

                    $.validator.addMethod("sysArea", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{200}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的业务描述");*/
                },
                mounted: function() {
                    // this.selectChange()
                    laydate.render({
                        elem: '#useData'
                    });
                    new Ctor().$mount('#app');
                    new Ctor().$mount('#wrap');
                    /*$("#AddformSystem").validate({
                        rules: {
                            sysName: {
                                required: true,
                                maxlength: 50,
                                sysName:true
                            },
                            sysCode: {
                                required: true,
                                sysCode:true
                            },
                            sysMsgBuild: {
                                required: true,
                                minlength: 2,
                                sysMsgBuild:true,
                            },
                            sysRecord: {
                                required: true,
                                maxlength: 100,
                                sysRecord:true
                            },
                            sysInter: {
                                required: true,
                                sysInter:true
                            },
                            sysType: {
                                required: false,
                                sysType:true
                            },
                            sysArea: {
                                required: true,
                                rangelength: [1, 100],
                                sysArea:true
                            },
                            sysServiceType: {
                                required: true,
                                email: true,
                                sysServiceType:true
                            },
                            sysServiceObject: {
                                required: true,
                                sysServiceObject:true
                            },
                            sysCoverage: {
                                required: true,
                                maxlength: 11,
                                isMobile: true,
                                sysCoverage:true
                            },
                            sysNetwork: {
                                required: true,
                                sysNetwork:true
                            },
                            sysIntercon: {
                                required: true,
                                sysIntercon:true
                            },
                            sysComName: {
                                required: true,
                                sysComName:true
                            },
                            useData: {
                                required: true,
                                useData:true
                            },
                            sysOfficeName: {
                                required: true,
                                sysOfficeName:true
                            },
                            sysSubsystem: {
                                required: true,
                                email: true,
                                sysSubsystem:true
                            },
                            sysPerson: {
                                required: true,
                                sysPerson:true
                            },
                            sysPersonTel: {
                                required: true,
                                sysPersonTel:true
                            }
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
                                    $(".confirm_btn").click(function () {
                                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                                    })
                                }
                            })
                        },
                    });
                    //以下为自定义验证
                    $.validator.addMethod("sysName", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的系统名称");

                    $.validator.addMethod("sysRecord", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{50}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的等保备案系统名称");

                    $.validator.addMethod("sysArea", function (value, element) {
                        var re = /^\w[()-[]:.{},;@，；。《》、（）]{200}$/;
                        return this.optional(element) || (re.test(value));
                    }, "请正确填写您的业务描述");*/
                }
            })
        })
    })
}())
