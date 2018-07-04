/**
 * Created by timha on 2018/5/25.
 */
(function () {
    var data1 = {companyOptions: [],
        selectedOptions: []};
    Vue.component('addFormCha',function (resolve, reject) {
        $.get(comp_src+'/compnents/private/addFormCha/addFormCha.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data1;
                },
                methods:{
                    getCover:function () {
                        $("#cover").removeClass('cover');
                        data.formData = {
                          companyName:"",
                          companyTypeName:"",
                          ldContactName:"",
                          companyCode:"",
                          companyAddress:"",
                          postalCode:"",
                          administrativeNum:"",
                          compPrincipalName:"",
                          compPrincipalPost:"",
                          compPrincipalWorkTel:"",
                          compPrincipalEm:"",
                          compPrincipalPhone:"",
                          ldContactPost:"",
                          ldContactWorkTel:"",
                          ldContactEmail:"",
                          ldContactPhone:"",
                          rDepartment:"",
                          gpReportingComp:"",
                          fkSubordinatePro:"",
                          fkIndustryCategory:"",
                          fkAffiliation:"",
                          fkPlateType:"",
                          fkCompanyType:"",
                          companyId:"",
                          changeType:"",
                          systemId:""
                        };
                        $("#baseMes1 div div").removeClass("btnColor");
                        $("#baseMes2 div div").removeClass("btnColor");
                        $("#baseMes3 div div").removeClass("btnColor");
                        data.nameReadonly = false;
                    },
                    handleChange:function(value) {
                      if(this.selectedOptions.length>0){
                        $("#cover").removeClass('cover');
                        bus.$emit("selectedOptions",this.selectedOptions);
                      }
                    },
                    getCompanyNameMethod: function(_self){
                      ajaxMethod(_self, 'post',
                          'company/queryCompanyListByName', true,
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