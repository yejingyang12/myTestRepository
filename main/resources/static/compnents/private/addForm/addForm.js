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
    // console.log(data);

    $.ajax({
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
    })
    /*Vue.component('mc-addFormOne',function (resolve, reject) {
        $.get('./addForm.html').then(function (res) {
            console.log(res);
            resolve:({
                template:res,
                data:function () {
                    return data;
                },
                methods:{
                    checkname:function () {
                        if(this.name =="" || this.name == "请选择"){
                            this.msgName = "用户名不能为空";
                        }else{
                            this.msgName ="";
                        }
                    },
                    checkdress:function () {
                        if(this.name == "" || this.name == "请输入"){
                            this.msgDress = "单位地址不能为空";
                        }else{
                            this.msgDress = "";
                        }
                    },
                    checkemail:function(){
                        var regEmail=/^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/;
                        if(this.email ==""){
                            this.msgEmail="请输入邮箱";
                        }else if(!regEmail.test(this.email)){
                            this.msgEmail="邮箱格式不正确";
                        }
                    }
                }
            })
        })
    })*/
})();
