/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title UserServiceImpl.java
* @Package com.sinopec.smcc.cpro.webservice.service.impl
* @Description: TODO:
* @author Aran
* @date 2018年9月10日下午8:26:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.webservice.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.pcitc.ssc.dps.inte.ISFMQExecResult;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import com.sinopec.smcc.common.rabbitmq.MessageSender;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowParam;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowResult;
import com.sinopec.smcc.cpro.codeapi.mapper.WorkFlowMapper;
import com.sinopec.smcc.cpro.codeapi.server.MessageService;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

/**
 * @Title UserServiceImpl.java
 * @Package com.sinopec.smcc.cpro.webservice.service.impl
 * @Description: TODO:
 * @author Aran
 * @date 2018年9月10日下午8:26:12
 * @version V1.0
 */
@WebService(targetNamespace = "http://ssc.dps.pcitc.com/mqexecresultservice", serviceName = "MQExecResultService")
public class WsMQExecResultService implements ISFMQExecResult {
  
  @Autowired
  private WorkFlowMapper workFlowMapperImpl;  
  @Autowired
  private UbsTemplate ubsTemplate;
  @Autowired
  private MessageService messageServiceImpl;
  @Autowired
  private MessageSender messageSenderImpl;  
  
    /**
     * 流程发起，指令发送到流程系统后，执行完成后，流程系统调用该接口方法，通知应用系统执行结果
     *
     * @param businessId 对应的业务Id
     * @param categoryCode 流程分类
     * @param result     执行结果
     * @param message    信息
     * @param appId      应用Id
     */
    @Override
  public boolean start(String businessId, String categoryCode,String executorId, Integer result, String message, String appId) {
    return true;
  }

    /**
     * 处理人待办处理结果提交后，指令发送到流程系统，执行完成后，流程系统调用该接口，通知应用系统待办处理的结果
     *
     * @param businessId 对应的业务Id
     * @param taskId     对用的待办Id
     * @param executorId 待办处理人Id
     * @param categoryCode 流程分类
     * @param result     执行结果
     * @param message    信息
     * @param appId      应用Id
     */
    @Override
  public boolean forward(String businessId, String taskId, String executorId, String categoryCode, Integer result,
      String message, String appId) {
    return true;
  }

    /**
     * 活动被激活前，待办发送之前， 指令发送到流程系统，流程系统调用该接口，通知应用系统。
     * @param businessId 对应的业务Id
     * @param activityId 被激活的活动Id
     * @param activityName 被激活的活动名称
     * @param categoryCode 流程分类
     * @param result 执行结果
     * @param message 信息
     * @param variableList 流程变量列表
     * @param appId 应用Id
     */
  @Override
  public boolean beforeActivate(String businessId, String activityId, String activityName, String categoryCode,
      Integer result, String message, List<AppVariableData> variableList, String appId) {
    return true;
  }

  /**
   * 活动下待办都审批完成，活动处于完成状态， 指令发送到流程系统，流程系统调用该接口，通知应用系统活动完成。
   * @param businessId 对应的业务Id
   * @param activityId 当前完成的活动Id
   * @param activityName 当前完成的活动名称
   * @param taskId 对应的待办Id
   * @param taskIdList 当前活动待办Id的集合
   * @param executorId 待办处理人Id
   * @param categoryCode 流程分类编码
   * @param result 执行结果
   * @param message 信息
   * @param variableList 流程变量列表
   * @param appId 应用Id
   */
  @Override
  public boolean afterComplete(String businessId, String activityId, String activityName, String taskId,
      List<String> taskIdList, String executorId, String categoryCode, Integer result, String message,
      List<AppVariableData> variableList, String appId) {
    return true;
  }

  /**
     * 活动下待办生成回调
     * @param businessId 对应的业务Id
     * @param activityId 当前运行中的活动Id
     * @param activityName 当前运行中的活动名称
     * @param taskIdList 生成待办Id的集合
     * @param executorIdList 待办处理人Id的集合
     * @param categoryCode 流程分类编码
     * @param result 执行结果
     * @param message 信息
     * @param metasList 流程字段列表
     * @param variableList 流程变量列表
     * @param appId 应用Id
     */
  @Override
  public boolean sendTask(String businessId, String activityId, String activityName, List<String> taskIdList,
      List<String> executorIdList, String categoryCode, Integer result, String message,
      List<AppMetasData> metasList, List<AppVariableData> variableList, String appId) {
    WorkFlowParam workFlowParam = new WorkFlowParam();
    workFlowParam.setBusinessId(businessId);
    WorkFlowResult workFlowResult
      = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
    
    workFlowParam.setNextApprover("回调开始");
    workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
    Integer checkType = 0;
    String auditReasons = "";
    if(workFlowResult != null){
      try {
        if(workFlowResult.getBusinessName().equals("定级")){
          checkType = 1;
        }else if(workFlowResult.getBusinessName().equals("申请变更")){
          checkType = 3;
        }else{
          checkType = 2;
        }
        //如果是企业未通过或总部未通过则添加审核原因
        if(workFlowResult.getCheckResult() == 3 || workFlowResult.getCheckResult() == 5){
          auditReasons = workFlowResult.getAuditReasons();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      workFlowParam.setNextApprover("测试1"+executorIdList.toString());
      workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
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
        
        workFlowParam.setNextApprover("测试2"+userIds+"--"+email);
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }else{
        //如果审核结果为总部通过或总部未通过，则获取始发人邮箱
        if(workFlowResult.getCheckResult() == 4 || workFlowResult.getCheckResult() ==5 || 
            checkType ==2 || workFlowResult.getCheckResult() == 3) { 
          UserDTO userDTO  = ubsTemplate.getUserByUserId(workFlowResult.getUserId());
          email = userDTO.getEmail() + ",";
        }
      }
      workFlowParam.setNextApprover(email+"____"+userIds+"Before1");
      workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        workFlowParam.setNextApprover(emailArr+"____"+email+"Before2" +checkType+"--"+workFlowResult.getCheckResult().toString()+"--"+auditReasons);
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,checkType,
            workFlowResult.getCheckResult().toString(), auditReasons);
        
        workFlowParam.setNextApprover(userIds+"____"+email+"Before3");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
        //添加下一步审批人
        if(StringUtils.isNotBlank(userIds)){
          workFlowParam.setNextApprover(userIds);
          workFlowParam.setAuditReasons("下一步审批人"+userIds);
          workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
        }
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
    }
    return true;
  }
}