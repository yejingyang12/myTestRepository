var emitdata = new Vue();

(function () {
    var data = {
        origin:originUrl,
        isShowSearchForm: false,
        isShowSilder: false,
        isShowToggle: false,
        isShowToggleTwo: [false, false, false],
        isShowToggleTwoUl: [false, false, false],
        HeaderData: null,
        mainNav: [],
        titleList: [],
        toggleOneData: [],
        toggleTwoData: []
    }
    Vue.component('smccHeader',function (resolve, reject) {
        $.get(comp_src+'/compnents/public/smcc_header/smcc_header.html').then(function (res) {
            resolve({
                template:res,
                data:function () {
                    return data;
                },
                methods: {
                  toggle: function() {
                    this.isShowSearchForm = !this.isShowSearchForm
                  },
                  showSilder: function() {
                    this.isShowSilder = true
                  },
                  hideSilder: function() {
                    this.isShowSilder = false
                  },
                  FnShowToggle: function(data, ind) {
                    if(ind !== undefined) {
                      var arr = [];
                      this.titleList = [].concat(this.HeaderData.data[ind].childList)
                      this.titleList.forEach(function(item, ind) {
                        item.childList.forEach(function(subitem, subind) {
                          if(!arr[subind]) arr[subind] = [];
                          arr[subind].push(subitem)
                        })
                      })
                      this.toggleOneData=[].concat(arr)
                    }
                    this.isShowToggle = data
                  },
                  FnToggleTwo: function(ind1, ind2, token1,arr) {
                    if(arr) this.toggleTwoData=[].concat(arr)
                    if(!this.isShowToggleTwo[ind1]) token1 = this.isShowToggleTwo[ind1]
                    this.isShowToggleTwo = [false, false, false]
                    this.isShowToggleTwoUl = [false, false, false]
                    this.isShowToggleTwo[ind1] = !token1
                    this.isShowToggleTwoUl[ind2] = !token1
                  },
                  FnSetParam: function(param) {
                    return param
                  },
                  FnHref: function() {

                  },
                  FnGetData: FnGetData,
                  getMenuInfoMethod:function(_self){
                    ajaxMethod(_self, 'get',
                        'base/sysMenu', true,'', 'json',
                        'application/json;charset=UTF-8',
                        _self.getMenuInfoSuccessMethod);
                  },
                  getMenuInfoSuccessMethod: function(_self, responseData) {
                    responseData.data.forEach(function(item, ind) {
                      this.$set(this.mainNav, ind, {
                        name: item.resourceName,
                        path: item.resourceUrl
                      })
                    }.bind(this))
                    _self.HeaderData = responseData;
                  },
                },
                created: function() {
                  this.getMenuInfoMethod(this);
                },
                mounted: function() {
                    // this.selectChange()
                }
            })
        })
    })
}())