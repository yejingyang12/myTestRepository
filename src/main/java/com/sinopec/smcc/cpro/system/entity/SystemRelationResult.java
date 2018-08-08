/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title SystemRelationResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author Aran
* @date 2018年8月6日下午12:13:14
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title SystemRelationResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年8月6日下午12:13:14
 * @version V1.0
 */
public class SystemRelationResult {

  private String systemRelationId;
  private String systemId;
  private String systemName;
  private String systemSmccCode;
  private String standardizedName;
  private String standardizedCode;
  private String systemIsMerge;
  private String systemSource;
  
  public String getSystemRelationId() {
    return systemRelationId;
  }
  public void setSystemRelationId(String systemRelationId) {
    this.systemRelationId = systemRelationId;
  }
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
  public String getSystemSmccCode() {
    return systemSmccCode;
  }
  public void setSystemSmccCode(String systemSmccCode) {
    this.systemSmccCode = systemSmccCode;
  }
  public String getStandardizedName() {
    return standardizedName;
  }
  public void setStandardizedName(String standardizedName) {
    this.standardizedName = standardizedName;
  }
  public String getStandardizedCode() {
    return standardizedCode;
  }
  public void setStandardizedCode(String standardizedCode) {
    this.standardizedCode = standardizedCode;
  }
  public String getSystemIsMerge() {
    return systemIsMerge;
  }
  public void setSystemIsMerge(String systemIsMerge) {
    this.systemIsMerge = systemIsMerge;
  }
  public String getSystemSource() {
    return systemSource;
  }
  public void setSystemSource(String systemSource) {
    this.systemSource = systemSource;
  }
}
