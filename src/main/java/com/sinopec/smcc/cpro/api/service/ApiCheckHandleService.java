/**
 * @Title ApiCheckHandleService.java
 * @Package com.sinopec.smcc.cpro.api.service
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午2:41:34
 * @version V1.0
 */
package com.sinopec.smcc.cpro.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.BatchCheckHandleParam;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;

/**
 * @Title ApiCheckHandleService.java
 * @Package com.sinopec.smcc.cpro.api.service
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午2:41:34
 * @version V1.0
 */
public interface ApiCheckHandleService {

  /**
   * @Descrption
   * @author eric
   * @param request 
   * @param userId 
   * @date 2018年7月19日下午4:41:01
   * @param checkParam
   * @return 
   */
  public List<CheckListResult> queryCheckList(HttpServletRequest request, 
      String userId) throws BusinessException;
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年7月19日下午3:01:46
   * @param batchCheckHandleParam
   * @param userId 
   * @param request 
   * @return
   */
  public List<String> saveCheck(BatchCheckHandleParam batchCheckHandleParam, 
      HttpServletRequest request) throws BusinessException;

}
