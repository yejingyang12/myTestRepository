/**
 * Created by timha on 2018/6/1.
 */
window.onload = function () {

    var app = new Vue({
        el:"#app",
        data:function () {
            return data;
        },
        methods:{
        	//判断定级列表是否被改变
        	judgeChange:function(judgeChange){
        		var flag = true;
        		var beginContent = this.beginContent;
        		var currentContent = _self.formData;
        		if(beginContent.competentIsExisting != currentContent.competentIsExisting){//是否有主管
        			flag = false;
        		}
        		if(beginContent.competentName != currentContent.competentName){//主管部门名称
        			flag = false;
        		}
        		if(beginContent.competentView != currentContent.competentView){//主管部门审批定级情况
        			flag = false;
        		}
        		if(beginContent.directorOpinionName != currentContent.directorOpinionName){//上级主管部门审批意见
        			flag = false;
        		}
        		if(beginContent.expertReviewName != currentContent.expertReviewName){//评审附件名称
        			flag = false;
        		}
        		if(beginContent.expertView != currentContent.expertView){//专家评审情况
        			flag = false;
        		}
        		if(beginContent.fillDate != currentContent.fillDate){//填表时间
        			flag = false;
        		}
        		if(beginContent.filler != currentContent.filler){//填表人
        			flag = false;
        		}	
        		if(beginContent.fkBizSPRankDegree != currentContent.fkBizSPRankDegree){
        			flag = false;
        		}
        		if(beginContent.fkBizSPRankLevel != currentContent.fkBizSPRankLevel){//业务信息等级
        			flag = false;
        		}
        		if(beginContent.fkBizSystemDegree != currentContent.fkBizSystemDegree){
        			flag = false;
        		}
        		if(beginContent.fkBizSystemLevel != currentContent.fkBizSystemLevel){//系统信息等级
        			flag = false;
        		}
        		if(beginContent.fkSpRanklevel != currentContent.fkSpRanklevel){//信息系统安全保护等级
        			flag = false;
        		}
        		if(beginContent.gradingReportName != currentContent.gradingReportName){//定级报告
        			flag = false;
        		}
        		if(beginContent.rankTime != currentContent.rankTime){//定级时间
        			flag = false;
        		}
        		if(flag){
        			this.preBtnSuccessMethod('', '',true);
        		}else{
        			$(".inquiryBoxShow1").show();
        		}
        	},
        	
          //保存
          saveBtn:function(formName) {
            bus.$emit('addGradName',formName);
          },
          // 获取系统信息成功
          saveBtnSuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            $(".save").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //提交
          submitBtn:function(formName) {
            data.submitCheck = false;
            bus.$emit('addSubmitGradName',formName);
          },
          // 成功
          submitBtnSuccessMethod : function(_self, responseData) {
            $(".submit").show().delay(2000).fadeOut();
            window.setTimeout(function () {
              window.location.href = originUrl+"page/indexPage";
            }, 2300);
          },
          //上一页
          preBtn:function(formName) {
            data.check = false;
            bus.$emit('addPreGradName',formName);
          },
          // 获取系统信息成功
          preBtnSuccessMethod : function(_self, responseData,boo) {
            if(boo){
              data.check = false;
              window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId;
            }else{
              $(".startBox").show().delay(2000).fadeOut();
              window.setTimeout(function () {
                data.formData.gradingId = responseData.data;
                window.location.href = originUrl+"page/addCompanySystemPage?systemId="+systemId+"&companyId="+companyId+"&companyCode="+companyCode;
              }, 2300);
            }
          },
          //下一页
          next1Btn:function(formName) {
            bus.$emit('addNextGradName',formName);
          },
          // 获取成功
          nextBtn2SuccessMethod : function(_self, responseData) {
            data.formData.gradingId = responseData.data;
            window.location.href = originUrl+"page/addCompanyMaterialPage?systemId="+systemId+"&companyId="+companyId+"&fkCompanyCode="+companyCode;
          },
          //返回
          returnBtn:function() {
          	ajaxMethod(_self, 'post',
                'main/removeSession', true,JSON.stringify(''), 'json',
                'application/json;charset=UTF-8',_self.removeSessionSuccess);
            window.location.href = originUrl+"page/indexPage";
          },
          removeSessionSuccess:function(){
          	
          },
        },
        mounted : function() {
          var _self = this;
          bus.$on('addGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.saveBtnSuccessMethod);
            }
          });
          bus.$on('addSubmitGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              if(data.jurisdictionType==1){
                ajaxMethod(_self, 'post',
                    'grading/submitGrading', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }else if(data.jurisdictionType==2){
                ajaxMethod(_self, 'post',
                    'grading/submitGradingForHeadquarters', true,
                    JSON.stringify(data.formData), 'json',
                    'application/json;charset=UTF-8',
                    _self.submitBtnSuccessMethod);
              }
            }
          });
          bus.$on('addPreGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.preBtnSuccessMethod);
            }
          });
          bus.$on('addNextGradAjax',function(meg){
            if(meg!=null){
              data.formData.changeType = "2";
              ajaxMethod(_self, 'post',
                  'grading/saveGrading', true,
                  JSON.stringify(data.formData), 'json',
                  'application/json;charset=UTF-8',
                  _self.nextBtn2SuccessMethod);
            }
          });
        }
    })
}