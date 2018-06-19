/**
* Copyright 2018. 
* @Title SystemGradingInfoListResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午4:18:03
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemGradingInfoListResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午4:18:03
 * @version V1.0
 */
public class SystemGradingInfoOneResult {
  private String systemCode;
  private String codeName;
  private List<SystemGradingInfoTwoResult> systemOneInfo;
  
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
  public List<SystemGradingInfoTwoResult> getSystemOneInfo() {
    return systemOneInfo;
  }
  public void setSystemOneInfo(List<SystemGradingInfoTwoResult> systemOneInfo) {
    this.systemOneInfo = systemOneInfo;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
