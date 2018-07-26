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
  private String bcdCode;
  private String bcdName;
  private String bcdCpname;
  private String bcdCptel;

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
  public String getBcdCode() {
    return bcdCode;
  }
  public void setBcdCode(String bcdCode) {
    this.bcdCode = bcdCode;
  }
  public String getBcdName() {
    return bcdName;
  }
  public void setBcdName(String bcdName) {
    this.bcdName = bcdName;
  }
  public String getBcdCpname() {
    return bcdCpname;
  }
  public void setBcdCpname(String bcdCpname) {
    this.bcdCpname = bcdCpname;
  }
  public String getBcdCptel() {
    return bcdCptel;
  }
  public void setBcdCptel(String bcdCptel) {
    this.bcdCptel = bcdCptel;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
