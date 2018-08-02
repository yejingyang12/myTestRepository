/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title SystemEchoResult.java
* @Package com.sinopec.smcc.cpro.systemcode.entity
* @Description: TODO:
* @author Aran
* @date 2018年7月31日下午7:27:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title SystemEchoResult.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年7月31日下午7:27:15
 * @version V1.0
 */
public class SystemEchoResult {

  private String systemId;
  private String systemName;
  private String systemCode;
  private String systemAlias;
  private String appRangecode;
  private String appRangeName;
  private String systemBranch;
  private String systemGroup;
  private String executiveOffice;
  
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public String getSystemCode() {
    return systemCode;
  }
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }
  public String getSystemAlias() {
    return systemAlias;
  }
  public void setSystemAlias(String systemAlias) {
    this.systemAlias = systemAlias;
  }
  public String getAppRangecode() {
    return appRangecode;
  }
  public void setAppRangecode(String appRangecode) {
    this.appRangecode = appRangecode;
  }
  public String getAppRangeName() {
    return appRangeName;
  }
  public void setAppRangeName(String appRangeName) {
    this.appRangeName = appRangeName;
  }
  public String getSystemBranch() {
    return systemBranch;
  }
  public void setSystemBranch(String systemBranch) {
    this.systemBranch = systemBranch;
  }
  public String getSystemGroup() {
    return systemGroup;
  }
  public void setSystemGroup(String systemGroup) {
    this.systemGroup = systemGroup;
  }
  public String getExecutiveOffice() {
    return executiveOffice;
  }
  public void setExecutiveOffice(String executiveOffice) {
    this.executiveOffice = executiveOffice;
  }
}
