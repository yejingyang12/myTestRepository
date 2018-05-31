/**
* 2018. 
* @Title MaterialsService.java
* @Package com.sinopec.smcc.cpro.grading.server
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日下午3:25:00
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server;

import java.util.List;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.grading.entity.AttachListResult;
import com.sinopec.smcc.cpro.grading.entity.AttachParam;


/**
 * @Title MaterialsService.java
 * @Package com.sinopec.smcc.cpro.grading.server
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日下午3:25:00
 * @version V1.0
 */
public interface AttachService {

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午4:49:07
   * @param attachParam
   * @return
   * @throws BusinessException 
   */
  List<AttachListResult> queryDetailsAttach(AttachParam attachParam) throws BusinessException;

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年5月29日下午4:49:29
   * @param attachParam
   * @return
   * @throws BusinessException 
   */
  List<AttachListResult> queryEditAttach(AttachParam attachParam) throws BusinessException;

}
