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

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;


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
}
