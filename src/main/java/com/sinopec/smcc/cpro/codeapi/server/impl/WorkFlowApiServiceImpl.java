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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppExtendsData;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppRunState;
import com.pcitc.ssc.dps.inte.workflow.AppTaskOpinionData;
import com.pcitc.ssc.dps.inte.workflow.AppWorkflowData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.ssc.dps.inte.workflow.StartContext;
import com.sinopec.smcc.base.consts.SmccConsts;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.dps.util.DpsConfig;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.soap.SoapApiService;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

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
  @Value("${appId}")
  private String appId;
  @Autowired
  private UserApiService userApiServiceImpl;
  @Autowired
  SoapApiService soapApiService;
  @Autowired
  private DpsConfig dpsConfig;
  
  /**
   * 流程发起
   */
  @Override
  public AppCallResult initStart(String businessName,String dataValue,String systemId) 
      throws BusinessException {
    AppCallResult appCallResult = new AppCallResult();
    try {
      // 获取流程模板信息
      final List<AppWorkflowData> appWorkflowPublic = dpsTemplate.appWorkflowPublic(WorkFlowConsts.CATEGORY_CODE_CPRO);
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
      AppExtendsData appExtendsData = new AppExtendsData();
      appExtendsData.setBusinessId(businessId);
      appExtendsData.setExt001(systemId);
      startContext.setExtendsData(appExtendsData);
      appCallResult = dpsTemplate.initStart(startContext);
//      
   // 当前人待办信息 获取业务id集合
//    final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(String.valueOf(userDTO.getUserId()), "");
//    final List<ExecuteTaskData> executeTaskList = appPagedTODOTask.getExecuteTaskList();
//    final Map<String, ExecuteTaskData> resultMap = new HashMap<String, ExecuteTaskData>();
//    if (CollectionUtils.isNotEmpty(executeTaskList)) {
//      for (final ExecuteTaskData executeTaskData : executeTaskList) {
//        resultMap.put(executeTaskData.getBusinessId(), executeTaskData);
//      }
//    }
//    String ad = "";
// 
//    //已办列表
//    final PagedList appPagedTODOTask1 = dpsTemplate.appDoneTask(String.valueOf(userDTO.getUserId()), "");
////      
//    String a = "";
//    //业务状态
//    List<AppRunState> appRunStateList = dpsTemplate.appRunState("79693b1bb45642a1b7be75d5da9d9766");
//    
//    String c = "";
//    //提交通过流程
//    final ExecuteContext executeContext = new ExecuteContext();
//    executeContext.setAppId(dpsConfig.getAppId());
//    executeContext.setExecutorId(String.valueOf(userDTO.getUserId()));
//    executeContext.setExecutorName(userDTO.getUserName());
//    executeContext.setTaskId("BA33EB8870A546A2A55577B1D82519E8");
//    executeContext.setExecuteDate(new Date());
//    AppCallResult appCallResult1  =  dpsTemplate.approveComplete(executeContext);
//    
//    String b = "";
    
    //退回流程
//    String UserId=String.valueOf(userDTO.getUserId());
//    String businessId = "fde0c1ed872c445aa363a721041f89ff";
//    final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(UserId,"");
//    if((appPagedTODOTask.getExecuteTaskList())!=null){
//      List<ExecuteTaskData> list= appPagedTODOTask.getExecuteTaskList();
//      String taskid=null;
//      for(ExecuteTaskData executeTaskList:list){
//        if(businessId.equals(executeTaskList.getBusinessId())){
//          taskid=executeTaskList.getTaskId();
//          ExecuteContext executeContext=new ExecuteContext();
//          executeContext.setAppId(SmccConsts.APPID);
//          executeContext.setExecutorId(UserId);
//          executeContext.setExecutorName(userDTO.getUserName());
//          executeContext.setTaskId(taskid);
//          executeContext.setExecuteDate(new Date());
//          dpsTemplate.approveRevert(executeContext);
//          break;
//        }
//      }
//    }
    
     //退回选择
//    String UserId=String.valueOf(userDTO.getUserId());
//    String businessId = "fde0c1ed872c445aa363a721041f89ff";
//    final PagedList appPagedTODOTask6 = dpsTemplate.appPagedTODOTask(UserId,"");
//    if((appPagedTODOTask6.getExecuteTaskList())!=null){
//      List<ExecuteTaskData> list= appPagedTODOTask6.getExecuteTaskList();
//      String taskid=null;
//      for(ExecuteTaskData executeTaskList6:list){
//        if(businessId.equals(executeTaskList6.getBusinessId())){
//          taskid=executeTaskList6.getTaskId();
//          ExecuteContext executeContext=new ExecuteContext();
//          executeContext.setAppId(SmccConsts.APPID);
//          executeContext.setExecutorId(UserId);
//          executeContext.setExecutorName(userDTO.getUserName());
//          executeContext.setTaskId(taskid);
//          executeContext.setExecuteDate(new Date());
//          dpsTemplate.approveRevertSelect(executeContext);
//          break;
//        }
//      }
//    }
          
      //审批历史
//      List<AppTaskOpinionData> appTaskOpinionDataList= dpsTemplate.appOpinion("fde0c1ed872c445aa363a721041f89ff");
//      String aas = "";
      
//      //跳过
//      ExecuteContext executeContext1 = new ExecuteContext();
//      executeContext1.setAppId(SmccConsts.APPID);
//      executeContext1.setExecutorId(String.valueOf(userDTO.getUserId()));
//      executeContext1.setExecutorName(userDTO.getUserName());
//      executeContext1.setTaskId("930082717429484A92404DE6AD422087");
//      //总部  021FDB473482479FB83FD7B176AAB31B
//      //C284B7A7BC1140A09A571016EF724C7F
//      executeContext1.setExecuteDate(new Date());
//      AppCallResult cppCallResult = dpsTemplate.approveExitComplete(executeContext1);
//      String aer = "";
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
  public void reviewPass(String taskId) throws BusinessException {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    //提交通过流程
    final ExecuteContext executeContext = new ExecuteContext();
    executeContext.setAppId(dpsConfig.getAppId());
    executeContext.setExecutorId(String.valueOf(userDTO.getUserId()));
    executeContext.setExecutorName(userDTO.getUserName());
    executeContext.setTaskId(taskId);
    executeContext.setExecuteDate(new Date());dpsTemplate.approveComplete(executeContext);
  }

  /**
   * 审核未通过
   */
  @Override
  public void reviewNotThrough(String businessId) throws BusinessException {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(String.valueOf(userDTO.getUserId()),"");
    if((appPagedTODOTask.getExecuteTaskList())!=null){
      List<ExecuteTaskData> list= appPagedTODOTask.getExecuteTaskList();
      String taskid=null;
      for(ExecuteTaskData executeTaskList:list){
        if(businessId.equals(executeTaskList.getBusinessId())){
          taskid=executeTaskList.getTaskId();
          ExecuteContext executeContext=new ExecuteContext();
          executeContext.setAppId(SmccConsts.APPID);
          executeContext.setExecutorId(String.valueOf(userDTO.getUserId()));
          executeContext.setExecutorName(userDTO.getUserName());
          executeContext.setTaskId(taskid);
          executeContext.setExecuteDate(new Date());
          dpsTemplate.approveRevert(executeContext);
          break;
        }
      }
    }
  }

}
