/**
 * @Title SystemApiResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:31:38
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemApiResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午2:31:38
 * @version V1.0
 */
public class SystemApiResult {

  private String systemCode;
  private String systemName;
  public String getSystemCode() {
    return systemCode;
  }
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
