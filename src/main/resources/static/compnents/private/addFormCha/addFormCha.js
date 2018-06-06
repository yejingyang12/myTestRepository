/**
 * Created by timha on 2018/5/25.
 */
(function () {
    var data = {};
    Vue.component('addFormCha',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addFormCha/addFormCha.html').then(function (res) {
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
                            }]
                    }
                },
                methods:{
                    getCover:function () {
                        $("#cover").removeClass('cover');
                    }
                },
                created: function() {

                },
                mounted: function() {
                    new Ctor().$mount('#add');
                }
            })
        })
    })
}())