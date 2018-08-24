var emitdata = new Vue();
(function() {
	Vue.component('smccHeader', function(resolve, reject) {
		$.get(comp_src + "/compnents/public/smcc-header/smcc-header.html").then(function(res) {
			resolve({
				template: res,
				data: function() {
					return {
						origin: originUrl,
						isShowSearchForm: false,
						isShowSilder: false,
						isShowToggle: false,
						userToken:false,
						sanjianIndex: 0,
						userToken: false,
                        selectIndex: 0,
						isShowToggleTwo: [false, false, false],
						isShowToggleTwoUl: [false, false, false],
						HeaderData: null,
						userData:{
							data:{},
						},
						mainNav: [],
						titleList: [],
						toggleOneData: [],
						toggleTwoData: [],
					}
				},
				watch: {
					HeaderData: function(val) {
						val.data.forEach(function(item, ind) {
							this.$set(this.mainNav, ind, {
								name: item.resourceName,
								path: item.resourceUrl
							})
						}.bind(this))
					}
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
					signOutMethod: function() {
					    window.location.href = originUrl + "SSO/GLO/Redirect";
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
							this.toggleOneData = [].concat(arr)
						}
						this.isShowToggle = data
					},
					FnToggleTwo: function(ind1, ind2, token1, arr) {
						if(arr) this.toggleTwoData = [].concat(arr)
						if(!this.isShowToggleTwo[ind1]) token1 = this.isShowToggleTwo[ind1]
						this.isShowToggleTwo = [false, false, false]
						this.isShowToggleTwoUl = [false, false, false]
						this.isShowToggleTwo[ind1] = !token1
						this.isShowToggleTwoUl[ind2] = !token1
					},
					FnSetParam: function(param) {
						return param
					},
					  moreSetupMenuRemove: function() {
	                        this.userToken = false
	                        this.isShowSearchForm = false
	                    },
				
					FnHref: function() {

					},
					FnGetData: FnGetData
				},
				mounted: function() {
					this.FnGetData('/base/sysMenu', 'HeaderData');
					this.FnGetData('/base/sysUser', 'userData');
					this.$emit('userdata',this.userData);
				}
			})
		});
	})
}())