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

import com.pcitc.ssc.dps.inte.workflow.AppCallResult;
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
   * @date 2018年7月11日下午3:19:54
   * @return
   * @throws BusinessException
   */
  AppCallResult initStart(String businessName,String dataValue,String systemId) throws BusinessException ;
  
  /**
   * @Descrption 审核通过
   * @author dongxu
   * @date 2018年8月1日下午4:22:10
   * @param taskId 待办ID
   * @throws BusinessException
   */
  void reviewPass(String taskId) throws BusinessException;
  
  /**
   * @Descrption 审核未通过
   * @author dongxu
   * @date 2018年8月1日下午4:22:10
   * @param businessId 业务ID
   * @throws BusinessException
   */
  void reviewNotThrough(String businessId) throws BusinessException;
}
