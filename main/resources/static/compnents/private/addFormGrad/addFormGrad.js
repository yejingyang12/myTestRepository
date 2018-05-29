/**
 * Created by timha on 2018/5/21.
 */
(function () {
    var data={
        sysName:'',//系统名称
        safetyPro:'',//确定安全保护等级
        competent:[],//主管部门
        competentName:'',//主管部门名称
        competentGrad:[],//主管部门审批定级情况
        gradPresen:'',//定级报告
        gradPresenName:'',//定级报告名称
        expertReview:[],//专家评审
        expertReviewName:'',//专家评审情况
        fillFormPerson:'',//填表人
        fillData:{}//填表时间
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

    (function sysCheck() {
        var all=document.getElementById('selectAll');//获取到点击全选的那个复选框的id
        var all1=document.getElementById('onlycheck');//获取到点击全选的那个复选框的id
        var one= document.getElementsByClassName('checkName');//获取到复选框的名称;获得的是数组
        //定义一个数组存储每个checkbox选中状态；默认都不选中
        var arr=[];
        //全选
        function checkAll(e) {
            for(var i=0;i<one.length;i++){
                one[i].index=i;
                arr.splice(one[i].index,1,all.checked);
                one[i].checked=all.checked;
            }
            all1.checked=all.checked
        }
        //反选
        for (var k = 0; k < one.length; k++) {
            arr.push(false);//默认有多少列，就添加多少个false
            one[k].a=k;
            one[k].onclick=function () {
                // console.log(arr);
                arr.splice(this.a,1,this.checked);
                all.checked=arr.every(function (item) {
                    return item});
                all1.checked=arr.some(function (item) {
                    return item});
            };
        }

    }());
}())