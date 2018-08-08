/**
 * Created by timha on 2018/5/29.
 */
var bus = new Vue();
var detailsData={
	materialView:false,
	revokeRecordShow: false,
	activeName: 'record',
	systemName: '',
	examineStatus:'',
	submitShowLabel: '备案',
	submitShow: '',
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
						//console.log(tab.label, event);
						this.submitShowLabel = tab.label;
						if(this.submitShowLabel == "备案"){
							bus.$emit("showComitBtm","comitBtm");
						}
					},
					submitRecord:function (){
						bus.$emit("submitRecord","formData");
					},
					revokeRecord:function () {
						$('.wrap').removeClass('cover');
//						bus.$emit("revokeRecord","1");
					},
					backRecord:function(){
						window.location.href="/page/indexPage"
					},
					// 获取系统信息
          getSystem : function(_self) {
          	ajaxMethod(this, 'post',
              'system/querySystemInformationBySystemId', true,
              '{"systemId":"'+systemId+'"}', 'json',
              'application/json;charset=UTF-8',
              this.getSystemSuccess);
          } ,
          getSystemSuccess : function(_self,result){
          	this.systemName = result.data.systemName;
          }
				},
				created: function() {
				//获取系统信息
          this.getSystem(this);
          this.examineStatus= examineStatus;
          this.submitShowLabel= '备案';
          this.submitShow= true;
				},
				mounted: function() {
					var _self=this;
					bus.$on("changeLi",function(meg){
						if(meg!=null){
							if(meg == "record"){
								_self.revokeRecordShow = false;
								_self.submitShow = true;
							}else{
								_self.revokeRecordShow = meg;
								_self.submitShow = meg;
							}
						}
          });
					bus.$on("showRevokeRecord",function(meg){
            _self.revokeRecordShow = meg;
          });
					bus.$on('materialView',function(meg){
            if(meg!=null){
            	_self.materialView = meg;
            }
          });
				}
			})
		})
	})
}())