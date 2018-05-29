(function() {
	var data = {
		page_now: 1,
		page_sum: 0,
		act_bol: [true,false,false,false,false]
	};

	Vue.component('smccPagebox', function(resolve, reject) {
		$.get(comp_src+"/compents/public/smcc-pagebox/smcc-pagebox.html").then(function(res) {
			resolve({
				template: res,
				data: function() {
					return data;
				},
				props: ['page_num', 'page_len'],
				computed: {
					pagearr: function() {
						var page_arr = [];
						this.page_sum = Math.ceil(this.page_len / this.page_num)

						for(var i = 0; i < this.page_sum; i++) {
							page_arr.push(i + 1)
						}
						return page_arr
					},
					ban_bol_start: function() {
						if(this.page_sum) {
							if(this.page_now * 1 === 1) return true
							return false
						}
					},
					ban_bol_end: function() {
						if(this.page_sum) {
							if(this.page_now >= this.page_sum) return true
							return false
						}
					}
				},
				methods: {
					FnPage: function(page) {
						switch(page) {
							case '+':
								this.page_now -= 1
								if(this.page_now <= 1) this.page_now = 1
								break;
							case '-':
								this.page_now += 1

								if(this.page_now >= this.page_sum) this.page_now = this.page_sum
								break;
							case 'enter':
								if(this.page_now <= 1) this.page_now = 1
								if(this.page_now >= this.page_sum) this.page_now = this.page_sum
								break;
							default:
								this.page_now = page
						}
						this.act_bol=[false,false,false,false,false]
						this.act_bol[this.page_now-1]=true
						this.$emit('FnSmccPagebox', this.page_now);
					}
				},
				moubted: function() {}
			})
		});
	})
}())