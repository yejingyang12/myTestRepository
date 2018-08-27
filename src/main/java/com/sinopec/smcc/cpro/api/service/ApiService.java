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

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.BatchCheckHandleParam;
import com.sinopec.smcc.cpro.api.entity.GetSystemRelationResult;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.api.entity.UsmgParams;
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
  PageInfo<CheckListResult> getStayHandle(UsmgParams usmgParams,String userId) 
      throws BusinessException;
  
  /**
   * @Descrption 批量审批
   * @author dongxu
   * @date 2018年8月8日下午12:11:19
   * @param executeContextList
   * @return
   * @throws BusinessException
   */
  Integer batchApproval(BatchCheckHandleParam batchCheckHandleParam) throws BusinessException;
  
  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月6日上午11:53:47
   * @param systemParam
   * @return
   */
  PageInfo<SystemRelationResult> getSystemRelationInfo(SystemRelationParam systemRelationParam)
      throws BusinessException ;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午9:42:18
   * @param systemRelationParam
   * @return
   */
  GetSystemRelationResult editGetSystemRelationInfo(
      SystemRelationParam systemRelationParam)throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午11:47:14
   * @param systemRelationEditParam
   */
  void editSystemRelationInfo(GetSystemRelationResult getSystemRelationResult)
      throws BusinessException;

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月27日上午11:51:37
   * @param systemRelationParam
   */
  void deleteSystemRelationInfo(SystemRelationParam systemRelationParam)
      throws BusinessException;
}
