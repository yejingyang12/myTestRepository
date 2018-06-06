(function() {
    var data ={
     msgName:[],//单位名称
     msgPwd:"",//单位编码
     msgDress:"",//单位地址
     msgPostal:"",//邮政编码
     msgProvince:[],//所属省份
     msgAdc:"",//行政区划编码
     msgPersoname:"",//单位负责人姓名
     msgComPhone:"",//办公电话
     msgTelphone:"",//移动电话
     msgOfficial:"",//职务/职称
     msgEmail:"",//电子邮件
     msgLdc:"",//责任部门联系人
     msgLdcPhone:"",//责任部门联系人->办公电话
     msgLdcTelphone:"",//责任部门联系人->移动电话
     msgLdcOfficial:"",//责任部门联系人->职务职称
     msgLdcEmail:"",//责任部门联系人->电子邮件
     msgRespon:"",//责任部门
     msgUpName:"",//等保上报单位名称
     msgInCate:[],//行业类别
     msgaAffi:[],//隶属关系
     msgUintType:[],//单位类型
     msgPlate:[]//板块类型
     };
    $("#Addform").validate({
        rules: {
            msgName: {
                required: true,
                maxlength: 50,
                msgName:true
            },
            msgPwd: {
                required: true,
                msgPwd:true
            },
            msgPostal: {
                required: true,
                minlength: 2,
                msgPostal:true,
            },
            msgDress: {
                required: true,
                maxlength: 100,
                msgDress:true
            },
            msgProvince: {
                required: true,
                msgProvince:true
            },
            msgAdc: {
                required: false,
                msgAdc:true
            },
            msgPersoname: {
                required: true,
                rangelength: [1, 100],
                msgPersoname:true
            },
            msgEmail: {
                required: true,
                email: true,
                msgEmail:true
            },
            msgComPhone: {
                required: true,
                msgComPhone:true
            },
            msgTelphone: {
                required: true,
                maxlength: 11,
                isMobile: true,
                msgTelphone:true
            },
            msgOfficial: {
                required: true,
                msgOfficial:true
            },
            msgLdc: {
                required: true,
                msgLdc:true
            },
            msgLdcPhone: {
                required: true,
                msgLdcPhone:true
            },
            msgLdcTelphone: {
                required: true,
                maxlength: 11,
                isMobile: true,
                msgLdcTelphone:true
            },
            msgLdcOfficial: {
                required: true,
                msgLdcOfficial:true
            },
            msgLdcEmail: {
                required: true,
                email: true,
                msgLdcEmail:true
            },
            msgRespon: {
                required: true,
                msgRespon:true
            },
            msgUpName: {
                required: true,
                msgUpName:true
            },
            msgInCate: {
                required: true,
                msgInCate:true
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
    $.validator.addMethod("msgName", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{50}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的单位名称");

    $.validator.addMethod("msgDress", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{1,100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的单位地址");

    $.validator.addMethod("msgPostal", function (value, element) {
        var re = /^[0-9]{6}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的邮政编码");

    $.validator.addMethod("msgPersoname", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的单位负责人姓名");

    $.validator.addMethod("msgOfficial", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的职务/职称");

    $.validator.addMethod("msgComPhone", function (value, element) {
        var re = /^0\d{2,3}-\d{6,7}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的办公电话");

    $.validator.addMethod("msgTelphone", function (value, element) {
        var re = /^0\d{2,3}-\d{6,7}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的移动电话");

    $.validator.addMethod("msgEmail", function (value, element) {
        var re = /^\w{3,10}@[0-9a-zA-Z]\.[a-zA-Z]{2,3}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的电子邮件");

    $.validator.addMethod("msgLdc", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的责任部门联系人姓名");

    $.validator.addMethod("msgLdcPhone", function (value, element) {
        var re =  /^0\d{2,3}-\d{6,7}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的责任部门联系人办公电话");

    $.validator.addMethod("msgLdcOfficial", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的职务/职称");

    $.validator.addMethod("msgLdcTelphone", function (value, element) {
        var re =/^0\d{2,3}-\d{6,7}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的责任部门联系人移动电话");

    $.validator.addMethod("msgLdcEmail", function (value, element) {
        var re = /^\w{3,10}@[0-9a-zA-Z]\.[a-zA-Z]{2,3}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的责任部门联系人 电子邮件");

    $.validator.addMethod("msgUpName", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的等保上报单位名称");

    $.validator.addMethod("msgProvince", function (value, element) {
        var re = /^\w[()-[]:.{},;@，；。《》、（）]{100}$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的所属省份");

    $.validator.addMethod("msgAdc", function (value, element) {
        var re = /^[0-9a-zA-Z]$/;
        return this.optional(element) || (re.test(value));
    }, "请正确填写您的行政区划编码");
    //ajax请求后台端口
    /*$.ajax({
     type:'POST',
     url:'192.168.0.208:8082/checkNodeController/queryheckNodeList',
     data:{"companyId":"bd4760fe10154fe696f6106f2d6db13c"},
     async:true,
     dataType: "jsonp",
     cache: false,
     error:function(data){},
     success:function (data) {
     // var obj=eval("(" + data + ")")
     console.log(data);
     // console.log(obj.msgName);
     },
     complete:{
     // 无论是否成功都执行该处的函数
     }
     })*/
    /*FnPostData('http://:63342/company/queryDetailsCompany');
    function FnPostData(url, type) {
        var param = {};
        var that = this;
        // this.FnSetParam(param)
        $.ajax({
            url: url,
            type: "POST",
            datType: "JSON",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(param),
            success: function(data) {
                console.log(data);
                /!*that.$nextTick(function() {
                    if(type) that[type] = data
                })*!/
            }
        })

    }*/

    Vue.component('addForm',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addForm/addForm.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return {
                        options: [{
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
                        }]
                    };
                },
                methods:{
                    Ajax:function(type,url, href, data){
                        $.ajax({
                            type:type,
                            url:url,
                            async:false,
                            data:data,
                            success:function (msg) {
                                if(msg){
                                    window.location.href = href;
                                }
                            }
                        });
                        $(".addBaseBg").css('background-position','0 -140px');
                        $(".addSysBg").css('background-position','-70px 0');
                    },
                    //点击切换 添加class名
                    getClass:function(e){
                        $(e.target).addClass('btnColor').siblings().removeClass("btnColor");
                    }
                },
                created: function() {

                },
                mounted: function() {
                    // this.selectChange()
                    new Ctor().$mount('#wrap');
                }
            })
        })
    })
})();
