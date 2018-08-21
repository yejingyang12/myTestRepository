(function () { 
	var data={ 
			 dialogShow:2,
			 weizhi:null,
			 formDialogData:{
			   searchSystem:'',
			   systemIds:[]
			 },
		     input5: '', 
		  /*   multipleSelection: [],*/
		     tableData2:[],
		     returnParam: []
		  }; 
  Vue.component('mainSystemInfoDialog',function (resolve,reject) {
    $.get(comp_src+'/compnents/private/mainSystemInfoDialog/mainSystemInfoDialog.html').then(function(res){
      resolve({
        template:res,
        data:function () {
          return data;
        },
        methods:{ 
        	indexMethod:function(index) {
                return index+1;
              },
        	 handleSelectionChange:function(val) {
            	 /* var checkbox= document.getElementsByClassName('el-checkbox__original');
	           	  for(var i=0;i<checkbox.length;i++){
	           		  var cur=checkbox[i];
	           	  }
	        	  console.dir(cur);*/
            	/*console.log(val);*/
        	    this.tableData2=val; 
        	  },
           toggleSelection:function(rows) {
        	        if (rows) {
        	        	var _self=this;
        	          rows.forEach(function(row){
        	        	  _self.$refs.multipleTable.toggleRowSelection(row);
        	          }); 
        	        } else {
        	        	_self.$refs.multipleTable.clearSelection();
        	        }
            },  
	        //点击 已选项里的每一项的“x”;
	        itemClose:function(event,e){  
	        	  this.tableData2=(this.tableData2).filter(function(item,index){ 
	        		 return item!==e;
	        	 }); 
	        	  data.weizhi=this.returnParam.indexOf(e);
	        	  this.toggleSelection([data.returnParam[data.weizhi]]); 
	         },
	        //点击“X”关闭弹窗
	          closes:function () {  
	        	this.dialogShow="2";
	          },
	        //点击“取消”关闭弹窗；
	          buttonCloses:function(){
	        	  this.closes();
	        },
	          querySystemInfoEchoListMethod:function(_self) {
	            if(_self==null||_self==undefined){
	              _self = this;
	            }
	            ajaxMethod(_self, 'post',
	                'system/querySystemInfoEchoList', true,
	                JSON.stringify(_self.formDialogData), 'json',
	                'application/json;charset=UTF-8',
	                _self.getSystemListInfoSuccessMethod);
	           },
	           // 获取成功
	           getSystemListInfoSuccessMethod : function(_self, responseData) {
	              _self.returnParam = responseData.data;
	           },
	           exportSystemInfoMethod:function(){
	             for(var i=0;i<this.tableData2.length;i++){
	               this.formDialogData.systemIds[i] = this.tableData2[i].systemId;
	             }
	             if(this.formDialogData.systemIds.length>0){
	               ajaxMethod(this, 'post',
	                   'system/exportExcelForSystemTemplate', true,
	                   JSON.stringify(this.formDialogData), 'json',
	                   'application/json;charset=UTF-8',
	                   this.exportSystemInfoSuccessMethod);
	             }else{
	               $(".startBox").show().delay(2000).fadeOut();
	             }
	           },
	           exportSystemInfoSuccessMethod:function(_self, responseData) {
//	             $(".startBox").show().delay(2000).fadeOut();
	             _self.dialogShow=2; 
	             _self.formDialogData.systemIds=[];
	             if(responseData.data){
	            	 window.location.href=originUrl+encodeURI("fileHandle/downloadFile?uploadUrl="+responseData.data.uploadUrl+"&attachName="+responseData.data.attachName);
	             }
             }
        },
        created: function() {
          this.querySystemInfoEchoListMethod(this);
        },
        mounted: function() {  
        	var _self=this;
        	bus.$on('dialog',function(msg){ 
        		_self.dialogShow=1; 
        		_self.tableData2 = [];
        	})
        }
      })
    })
  })

}())