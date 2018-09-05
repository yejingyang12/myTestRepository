/**
 * Created by timha on 2018/6/7.
 */
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
    Vue.component('viewDetailsExamine1',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/viewDetailsExamine1/viewDetailsExamine1.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data
                },
                methods:{

                },
                created: function() {

                },
                mounted: function() {

                }
            })
        })
    })
})();