/**
 * Created by timha on 2018/5/29.
 */
var bus = new Vue();
var detailsData={
	revokeRecordShow: false,
	activeName: 'record'
};
(function () {
	Vue.component('detailsNav',function (resolve,reject) {
		$.get(comp_src+'/compnents/private/detailsNav/detailsNav.html').then(function(res){
			resolve({
				template:res,
				data:function () {
					return detailsData;
				},
				methods:{
					handleClick: function(tab, event) {
						console.log(tab, event);
					},
					submitRecord:function (){
						bus.$emit("submitRecord","1");
					},
					revokeRecord:function () {
						$('.wrap').removeClass('cover');
//						bus.$emit("revokeRecord","1");
					},
					backRecord:function(){
						window.location.href="/page/indexPage"
					}
				},
				created: function() {
				
				},
				mounted: function() {
					var _self=this;
					bus.$on("changeLi",function(meg){
						console.log("meg:"+meg);
            if(meg!=null){
            	_self.revokeRecordShow = meg;
            }
          });
				}
			})
		})
	})
}())