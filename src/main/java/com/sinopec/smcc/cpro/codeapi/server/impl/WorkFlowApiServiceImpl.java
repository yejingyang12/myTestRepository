/**
* 2018. 
* @Title WorkFlowApiServiceImpl.java
* @Package com.sinopec.smcc.cpro.codeapi.server.impl
* @Description: TODO:
* @author dongxu
* @date 2018年7月11日下午3:21:34
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import com.pcitc.ssc.dps.inte.workflow.AppWorkflowData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.ssc.dps.inte.workflow.StartContext;
import com.sinopec.smcc.base.consts.SmccConsts;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowParam;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowResult;
import com.sinopec.smcc.cpro.codeapi.mapper.WorkFlowMapper;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.MessageService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.dps.util.DpsConfig;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

/**
 * @Title WorkFlowApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月11日下午3:21:34
 * @version V1.0
 */
@Service
public class WorkFlowApiServiceImpl implements WorkFlowApiService{
  @Autowired
  private DpsTemplate dpsTemplate;
  @Autowired
  private UbsTemplate ubsTemplate;
  @Value("${appId}")
  private String appId;
  @Autowired
  private UserApiService userApiServiceImpl;
  @Autowired
  private DpsConfig dpsConfig;
  @Autowired
  private SystemMapper systemMapperImpl;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  @Autowired
  private MessageService messageServiceImpl;
  @Autowired
  private WorkFlowMapper workFlowMapperImpl;
  
  /**
   * 流程发起
   */
  @Override
  public AppCallResult initStart(String businessName,String dataValue,String systemId,
      String checkResult) throws BusinessException {
    AppCallResult appCallResult = new AppCallResult();
    try {
      // 获取流程模板信息
      final List<AppWorkflowData> appWorkflowPublic = 
          dpsTemplate.appWorkflowPublic(WorkFlowConsts.CATEGORY_CODE_CPRO);
      final AppWorkflowData workFlowData = appWorkflowPublic.get(0);
      //获得用户信息
      UserDTO userDTO = userApiServiceImpl.getUserInfo();
      String businessId = Utils.getUuidFor32();
      StartContext startContext = new StartContext();
      startContext.setUserId(String.valueOf(userDTO.getUserId()));
      startContext.setAppId("SMCC");
      startContext.setUserName(userDTO.getUserName());
      startContext.setOrganiseId("#templateorgId#");
      startContext.setOrganiseName(userDTO.getOrgName());
      startContext.setBusinessId(businessId);
      startContext.setBusinessName(businessName);
      startContext.setExecuteDate(new Date());
      startContext.setCategoryCode(workFlowData.getCategoryCode());
      startContext.setWorkflowId(workFlowData.getWorkflowId());
      List<AppMetasData> appMetasDataList = new ArrayList<>();
      AppMetasData appMetasData = new AppMetasData();
      appMetasData.setMetaCode("taskStatus");
      appMetasData.setMetaName("任务状态");
      appMetasData.setDataTypeId(3); 
      appMetasData.setDataValue(dataValue);
      appMetasData.setDataTypeName("字符串");
      appMetasDataList.add(appMetasData);
      startContext.setMetasList(appMetasDataList);
      //扩展数据
      SystemParam systemParam = new SystemParam();
      systemParam.setSystemId(systemId);
      SystemResult systemResult = this.systemMapperImpl.selectSystem(systemParam);
      AppExtendsData appExtendsData = new AppExtendsData();
      appExtendsData.setBusinessId(businessId);
      appExtendsData.setExt001(systemId);//系统ID
      appExtendsData.setExt002(userDTO.getUserName());//用户名称
      appExtendsData.setExt003(WorkFlowConsts.CATEGORY_VERSION_NUM);//版本号
      if(systemResult != null){
        appExtendsData.setExt004(systemResult.getSystemName());//系统名称
        appExtendsData.setExt005(systemResult.getFkInfoSysTypeCon().toString());//建设类型
      }
      appExtendsData.setExt006(businessName);//业务节点
      appExtendsData.setExt007(userDTO.getUserName());//发起人
      
      //权限
      JurisdictionDataResult organizationApiResult = 
          this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
      List<String> orgStr = organizationApiResult.getPermssions();
      //企业权限  
      if(orgStr.contains("0102010301")||orgStr.contains("0102010201")){
        //单位Code
        appExtendsData.setExt008(jurisdictionApiServiceImpl.getCompanyCode());
      }
      startContext.setExtendsData(appExtendsData);
      //发起流程
      dpsTemplate.initStart(startContext);
      
      //创建工作流信息
      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setWorkFlowId(Utils.getUuidFor32());
      workFlowParam.setBusinessId(businessId);
      workFlowParam.setBusinessName(businessName);
      workFlowParam.setCreateTime(new Date());
      workFlowParam.setCheckResult(Integer.parseInt(checkResult));
      workFlowParam.setUserId(String.valueOf(userDTO.getUserId()));
      workFlowParam.setSystemId(systemId);
      workFlowMapperImpl.insertWorkFlow(workFlowParam);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return appCallResult;
  }
  
  /**
   * @Descrption 审核通过
   * @author dongxu
   * @date 2018年8月1日下午4:19:40
   * @param businessName
   * @param dataValue
   * @param systemId
   * @return
   * @throws BusinessException
   */
  @Override
  public void reviewPass(String taskId,String userId,String userName,String checkResult,
      String businessId,String businessName) throws BusinessException {
    //提交通过流程
    final ExecuteContext executeContext = new ExecuteContext();
    executeContext.setAppId(dpsConfig.getAppId());
    executeContext.setExecutorId(userId);
    executeContext.setExecutorName(userName);
    executeContext.setTaskId(taskId);
    executeContext.setExecuteDate(new Date());
    dpsTemplate.approveComplete(executeContext);
    
    //修改工作流信息
    WorkFlowParam workFlowParam = new WorkFlowParam();
    workFlowParam.setBusinessId(businessId);
    workFlowParam.setCheckResult(Integer.parseInt(checkResult));
    workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
  }

  /**
   * 审核未通过
   */
  @Override
  public void reviewNotThrough(String businessId,String userId,String userName,String checkResult,
      String businessName,String auditReasons) throws BusinessException {
    final PagedList appPagedTODOTask = 
        dpsTemplate.appTODOTask(userId,"",WorkFlowConsts.CATEGORY_CODE_CPRO);
    if((appPagedTODOTask.getExecuteTaskList())!=null){
      List<ExecuteTaskData> list= appPagedTODOTask.getExecuteTaskList();
      String taskid=null;
      for(ExecuteTaskData executeTaskList:list){
        if(businessId.equals(executeTaskList.getBusinessId())){
          taskid=executeTaskList.getTaskId();
          ExecuteContext executeContext=new ExecuteContext();
          executeContext.setAppId(SmccConsts.APPID);
          executeContext.setExecutorId(userId);
          executeContext.setExecutorName(userName);
          executeContext.setTaskId(taskid);
          executeContext.setExecuteDate(new Date());
          dpsTemplate.approveRevert(executeContext);
          break;
        }
      }
    }
    //修改工作流信息
    WorkFlowParam workFlowParam = new WorkFlowParam();
    workFlowParam.setBusinessId(businessId);
    workFlowParam.setCheckResult(Integer.parseInt(checkResult));
    workFlowParam.setAuditReasons(auditReasons);
    workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
  }

  /**
   * 发送待办回调
   */
  @Override
  public boolean sendTask(String businessId, String activityId, String activityName,
      List<String> taskIdList, List<String> executorIdList, String categoryCode, Integer result,
      String message, List<AppMetasData> metasList, List<AppVariableData> variableList, String appId)
          throws BusinessException {
      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(businessId);
      WorkFlowResult WorkFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      WorkFlowResult workFlowResult = new WorkFlowResult();
      Integer checkType = 0;
      if(WorkFlowResult != null){
        if(workFlowResult.getBusinessName().equals("定级")){
          checkType = 1;
        }else if(workFlowResult.getBusinessName().equals("申请变更")){
          checkType = 3;
        }else{
          checkType = 2;
        }
        String auditReasons = "";
        //如果是企业未通过或总部未通过则添加审核原因
        if(workFlowResult.getCheckResult() == 3 || workFlowResult.getCheckResult() == 5){
          auditReasons = workFlowResult.getAuditReasons();
        }
        
        String email = "";
        String userIds = "";
        if(!ObjectUtils.isEmpty(executorIdList)){
          //通过用户id 拼接邮箱
          for(String userId : executorIdList){
            UserDTO userDTO = ubsTemplate.getUserByUserId(userId);
            if(StringUtils.isNotBlank(userDTO.getEmail())){
              email +=userDTO.getEmail() + ",";
            }
            userIds += userId + ",";
          }
        }else{
          //如果审核结果为总部通过或总部未通过，则获取始发人邮箱
          if(workFlowResult.getCheckResult() == 4 || workFlowResult.getCheckResult() ==5 || 
              checkType ==2 || workFlowResult.getCheckResult() == 3) { 
            UserDTO userDTO  = ubsTemplate.getUserByUserId(workFlowResult.getUserId());
            email = userDTO.getEmail() + ",";
          }
        }
        if(StringUtils.isNotBlank(email)){
          String [] emailArr = email.split(",");
          //发送邮件
          this.messageServiceImpl.sendMessageForCheck(emailArr, null,checkType,
              workFlowResult.getCheckResult().toString(), auditReasons);
          //添加下一步审批人
          if(StringUtils.isNotBlank(userIds)){
            workFlowParam.setNextApprover(userIds);
          }
          workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
          return true;
        }
      }
    return false;
  }
}
