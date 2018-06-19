
(function () {
    Vue.component('addFromalert', function (resolve, reject) {
        var data = {
                form: {
                    resource: '',
                },
                radios: [],
                message: '1',
                messageParam:'',
                industry:'',
                industryShow:false
            }
        $.get(comp_src + '/compnents/private/addFromalert/addFromalert.html').then(function (res) {
            resolve({
                template: res,
                data: function () {
                    return data;
                },
                methods : {
                  // 获取行业类别
                  getIndustryCategoryMethod : function(_self) {
                    ajaxMethod(_self, 'post',
                        'systemCode/querySystemCodeForKeyCodeName', true,
                        '{"codeType":"8"}', 'json',
                        'application/json;charset=UTF-8',
                        _self.getIndustryCategorySuccessMethod);
                  },
                  // 获取行业类别成功
                  getIndustryCategorySuccessMethod : function(_self, responseData) {
                    
                    for (var i = 0; i < responseData.data.length; i++) {
                      _self.radios.push({
                        "lable" : responseData.data[i].codeName,
                      });
                    }
                  },
                  getIndustryCategory:function(_self,meg){
                    if(meg=='1'){
                      _self.industryShow=true;
                    }
                  },
                  addRadion:function(form){
                    
                    if(form.resource=='1'){
                      console.log(this.messageParam)
                      if(this.messageParam!=null&&this.messageParam!=''){
                        this.industryShow = false;
                        bus.$emit("resource",this.messageParam);
                      }else{
                        this.$message({
                          message: '选择其它时，请在输入框输入信息！',
                          type: 'warning'
                        });
                      }
                    }else{
                      this.industryShow = false;
                      bus.$emit("resource",form.resource);
                    }
                  }
                },
                created : function() {
                  // 获取行业类别
                  this.getIndustryCategoryMethod(this);
                },
                
                mounted : function() {
                  // this.selectChange()
                  // new Ctor().$mount('#wrap');
                  var _self = this;
                  bus.$on("industryCategory",function(meg){
                    if(meg!=null){
                      // 获取点击事件
                      _self.industry = meg;
                      _self.getIndustryCategory(_self,meg);
                    }
                  });
                }
            })
        })
    })
})()

