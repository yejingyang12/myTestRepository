/**
 * @Title JurisdictionApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:20:52
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server;

import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;

/**
 * @Title JurisdictionApiService.java
 * @Package com.sinopec.smcc.cpro.codeapi.server
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:20:52
 * @version V1.0
 */
public interface JurisdictionApiService {

  JurisdictionDataResult queryDataJurisdictionApi1();
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月20日下午3:22:33
   * @param organizationApiParam
   * @return
   */
  JurisdictionDataResult queryDataJurisdictionApi();
  
  /**
   * @Descrption 获取单位Code
   * @author dongxu
   * @date 2018年7月18日下午8:18:41
   * @return
   */
  String getCompanyCode();

}
