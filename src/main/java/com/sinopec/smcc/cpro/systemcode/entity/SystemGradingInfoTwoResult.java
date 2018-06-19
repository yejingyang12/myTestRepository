/**
* Copyright 2018. 
* @Title SystemGradingInfoTwoResult.java
* @Package com.sinopec.smcc.cpro.systemcode.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午5:29:59
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemGradingInfoTwoResult.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午5:29:59
 * @version V1.0
 */
public class SystemGradingInfoTwoResult {
  private String systemCode;
  private String codeName;
  private List<SystemGradingInfoThreeResult> systemTwoInfo;
  
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
  public List<SystemGradingInfoThreeResult> getSystemTwoInfo() {
    return systemTwoInfo;
  }
  public void setSystemTwoInfo(List<SystemGradingInfoThreeResult> systemTwoInfo) {
    this.systemTwoInfo = systemTwoInfo;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
