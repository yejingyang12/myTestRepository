/**
* Copyright 2018. 
* @Title SystemGradingInfoThreeResult.java
* @Package com.sinopec.smcc.cpro.systemcode.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午5:30:55
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemGradingInfoThreeResult.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午5:30:55
 * @version V1.0
 */
public class SystemGradingInfoThreeResult {
  private String systemCode;
  private String codeName;
  public String getSystemCode() {
    return systemCode;
  }
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }
  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
