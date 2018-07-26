/**
 * @Title SystemApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:29:26
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiParam;
import com.sinopec.smcc.cpro.codeapi.entity.SystemApiResult;

/**
 * @Title SystemApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:29:26
 * @version V1.0
 */
public interface SystemApiService {

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月9日下午2:33:14
   * @param systemApiParam
   * @return
   */
  List<SystemApiResult> querySystemApi(SystemApiParam systemApiParam) throws BusinessException;
}
