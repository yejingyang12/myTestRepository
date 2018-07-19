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
   * @date 2018年7月11日下午3:19:54
   * @return
   * @throws BusinessException
   */
  AppCallResult initStart() throws BusinessException ;
}
