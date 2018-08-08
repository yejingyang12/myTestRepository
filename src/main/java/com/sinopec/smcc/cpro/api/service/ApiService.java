/**
* 2018. 
* @Title ApiService.java
* @Package com.sinopec.smcc.cpro.api.service
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:12:45
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.service;

import java.util.List;

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
import com.pcitc.ssc.dps.inte.workflow.ExecuteContext;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;

/**
 * @Title ApiService.java
 * @Package com.sinopec.smcc.cpro.api.service
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:12:45
 * @version V1.0
 */
public interface ApiService {
  
  GradingApiResult getGradingInformation(String systemId) throws BusinessException;
  
  /**
   * @Descrption 通过userId获取待办列表
   * @author dongxu
   * @date 2018年8月6日下午5:21:04
   * @param userId
   * @return
   * @throws BusinessException
   */
  List<CheckListResult> getStayHandle(String userId) throws BusinessException;
  
  /**
   * @Descrption 批量审批
   * @author dongxu
   * @date 2018年8月8日下午12:11:19
   * @param executeContextList
   * @return
   * @throws BusinessException
   */
  AppCallResult batchApproval(List<ExecuteContext> executeContextList) throws BusinessException;
  
  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月6日上午11:53:47
   * @param systemParam
   * @return
   */
  List<SystemRelationResult> getSystemRelationInfo(SystemRelationParam systemRelationParam)
      throws BusinessException ;
}
