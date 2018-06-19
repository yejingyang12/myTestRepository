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
                      companyOptions: [],
                      selectedOptions: []
                    }
                },
                methods:{
                    getCover:function () {
                        $("#cover").removeClass('cover');
                    },
                    handleChange:function(value) {
                      if(this.selectedOptions.length>0){
                        $("#cover").removeClass('cover');
                        bus.$emit("selectedOptions",this.selectedOptions);
                      }
                    },
                    getCompanyNameMethod: function(_self){
                      ajaxMethod(_self, 'post',
                          'organizationapi/queryOrganizationApi', true,
                          '{}', 'json','application/json;charset=UTF-8',
                          _self.getCompanyNameSuccessMethod);
                    },
                    getCompanyNameSuccessMethod:function(_self,data){
                      _self.companyOptions = data.data;
                    }
                },
                created: function() {
                  this.getCompanyNameMethod(this);
                },
                mounted: function() {
                    //new Ctor().$mount('#add');
                }
            })
        })
    })
}())