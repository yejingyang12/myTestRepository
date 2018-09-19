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
						sanjianIndex: 0,
						nowDate:'',
						userToken: false,
						selectIndex: 0,
						HeaderData: null,
						headerTime: '',
						userData: {
							data: {},
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
						this.isShowSearchForm = true
					},
					showSilder: function(ind) {
						this.sanjianIndex = ind
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
							this.toggleOneData = [].concat(arr)
						}
						this.isShowToggle = data
					},
					FnNowDate: function() {
						var date = new Date(),
							zhou = ['日', '一', '二', '三', '四', '五', '六']
						y = date.getFullYear(),
							m = date.getMonth() + 1
						d = date.getDate()
						w = date.getDay()
						h = date.getHours()
						min = date.getMinutes()
						se = date.getSeconds()						
						this.nowDate = y + '年' + (m * 1 > 10 ? m : '0' + m) + '月' + d + '日 ' + '周' + zhou[w] + ' ' + 
						(h*1 >= 10? h : '0' + h) + ':' + 
						(min*1 >= 10? min : '0' + min)+ ':' + 
						(se*1 >= 10? se : '0' + se)
					},
					FnSetParam: function(param) {
						return param
					},
					moreSetupMenuRemove: function() {
						this.userToken = false
						this.isShowSearchForm = false
					},
					FnGetData: FnGetData
				},
				beforeCreate : function(){
					this.$nextTick(function () {
				     	this.FnNowDate()
				   })					
				},
				created: function(){
					setInterval(function() {
						this.FnNowDate()
					}.bind(this), 1000)
				},
				mounted: function() {					
/*					this.globalClick(this.moreSetupMenuRemove);*/
					this.FnGetData('/base/sysMenu', 'HeaderData');
					this.FnGetData('/base/sysUser', 'userData');
					this.$emit('userdata', this.userData);
				}
			})
		});
	})
}())