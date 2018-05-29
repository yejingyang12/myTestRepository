/**
 * Created by timha on 2018/5/24.
 */
(function () {
    var data={
        systemTopology:"",//系统拓扑结构及说明
        systemSecurity:"",//系统安全组织机构及管理制度
        systemSafety:[],//系统安全保护设施设计实施方案或改建实施方案
        systemUse:"",//系统使用的安全产品清单及认证、销售许可证明
        competentGrad:[],//主管部门审批定级情况
        gradPresen:'',//定级报告
        gradPresenName:'',//定级报告名称
        expertReview:[],//专家评审
        expertReviewName:'',//专家评审情况
        fillFormPerson:'',//填表人
        fillData:{}//填表时间
    };
    $("#addFormGrad").validate({
        systemTopology:{
            required:true,
            systemTopology:true
        },
        systemSecurity:{
            required:true,
            systemSecurity:true
        },
        systemSafety:{
            required:true,
            systemSafety:true
        },
        systemUse:{
            required:true,
            systemUse:true
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
                    $("#comit").click(function () {
                        window.location.href = "http://55927461.m.weimob.com/vshop/55927461/Index?PageId=513198&IsPre=1";
                    })
                }
            })
        }
    })
}())