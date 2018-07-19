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

import com.sinopec.smcc.cpro.review.entity.CheckParam;

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
   * @date 2018年7月19日下午3:01:46
   * @param checkParam
   * @return
   */
  public List<String> saveCheck(CheckParam checkParam) ;
}
