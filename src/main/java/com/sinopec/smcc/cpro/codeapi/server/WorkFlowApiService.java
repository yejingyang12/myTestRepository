/**
* 2018. 
* @Title WorkFlowApiService.java
* @Package com.sinopec.smcc.cpro.codeapi.server
* @Description: TODO:
* @author dongxu
* @date 2018年7月11日下午3:15:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.server;

import java.util.List;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.AppMetasData;
import com.pcitc.ssc.dps.inte.workflow.AppVariableData;
import com.sinopec.smcc.base.exception.classify.BusinessException;

/**
 * @Title WorkFlowApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月11日下午3:15:32
 * @version V1.0
 */
public interface WorkFlowApiService {
  /**
   * @Descrption 流程发起
   * @author dongxu
   * @param businessName 业务节点名称
   * @param dataValue 1企业主联络员审核 2 总部管理员审核
   * @param systemId 系统ID
   * @param checkResult 审核结果 :
                      0，企业发起审核
                      1，总部发起审核
                      2，企业审核通过
                      3，企业审核未通过
                      4，总部审核通过
                      5，总部审核未通过
   * @date 2018年7月11日下午3:19:54
   * @return
   * @throws BusinessException
   */
  AppCallResult initStart(String businessName,String dataValue,String systemId,String checkResult) 
      throws BusinessException ;
  
  /**
   * @Descrption 审核通过
   * @author dongxu
   * @date 2018年8月1日下午4:22:10
   * @param taskId 待办ID
   * @param userName 用户名称
   * @param checkResult 审核结果 :
                      0，企业发起审核
                      1，总部发起审核
                      2，企业审核通过
                      3，企业审核未通过
                      4，总部审核通过
                      5，总部审核未通过
   * @param userName 用户名称
   * @param businessId 业务Id
   * @param businessName 业务名称
   * 
   * @throws BusinessException
   */
  void reviewPass(String taskId,String userId,String userName,String checkResult,String businessId,
      String businessName) throws BusinessException;
  
  /**
   * @Descrption 审核未通过
   * @author dongxu
   * @date 2018年8月1日下午4:22:10
   * @param businessId 业务ID
   * @param userId 用户ID
   * @param userName 用户名称
   * @param checkResult 审核结果 :
                      0，企业发起审核
                      1，总部发起审核
                      2，企业审核通过
                      3，企业审核未通过
                      4，总部审核通过
                      5，总部审核未通过
   * @param businessName 业务名称
   * @throws BusinessException
   */
  void reviewNotThrough(String businessId,String userId,String userName,String checkResult,
      String businessName,String auditReasons) throws BusinessException;
  
  /**
   * @Descrption 发送待办回调
   * @author dongxu
   * @date 2018年9月3日上午11:22:31
   * @param businessId 业务ID
   * @param activityId 活动Id
   * @param activityName 活动名称
   * @param taskIdList 发送待办Id列表
   * @param executorIdList 发送待办执行人Id列表
   * @param categoryCode 流程分类编码
   * @param result 执行结果，1：成功，0：失败
   * @param message 如执行结果为0, 返回异常信
   * @param metasList 流程字段列表
   * @param variableList 流程变量列表
   * @param appId 分配的所属应用Id
   * @return
   * @throws BusinessException
   */
  public boolean sendTask(String businessId, String activityId, String activityName, 
      List<String> taskIdList,  List<String> executorIdList,  String categoryCode, Integer result, 
      String message, List<AppMetasData> metasList, List<AppVariableData> variableList,
      String appId) throws BusinessException;
  
}
