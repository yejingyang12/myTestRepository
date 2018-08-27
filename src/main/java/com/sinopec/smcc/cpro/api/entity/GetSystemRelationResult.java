/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title GetSystemRelationResult.java
* @Package com.sinopec.smcc.cpro.api.entity
* @Description: TODO:
* @author Aran
* @date 2018年8月27日上午9:41:43
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.entity;

import java.util.List;

import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;

/**
 * @Title GetSystemRelationResult.java
 * @Package com.sinopec.smcc.cpro.api.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年8月27日上午9:41:43
 * @version V1.0
 */
public class GetSystemRelationResult {

  private String systemId;
  private String systemName;
  private String systemSmccCode;
  private String systemIsMerge;
  private String fkCompanyCode;
  private List<SystemRelationResult> standardizedInfo;
  
  private List<SystemRelationParam> addSystemInfo;
  private List<SystemRelationParam> deleteSystemInfo;
  
  private String userId;

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

  public String getSystemIsMerge() {
    return systemIsMerge;
  }

  public void setSystemIsMerge(String systemIsMerge) {
    this.systemIsMerge = systemIsMerge;
  }

  public List<SystemRelationResult> getStandardizedInfo() {
    return standardizedInfo;
  }

  public void setStandardizedInfo(List<SystemRelationResult> standardizedInfo) {
    this.standardizedInfo = standardizedInfo;
  }

  public List<SystemRelationParam> getAddSystemInfo() {
    return addSystemInfo;
  }

  public void setAddSystemInfo(List<SystemRelationParam> addSystemInfo) {
    this.addSystemInfo = addSystemInfo;
  }

  public List<SystemRelationParam> getDeleteSystemInfo() {
    return deleteSystemInfo;
  }

  public void setDeleteSystemInfo(List<SystemRelationParam> deleteSystemInfo) {
    this.deleteSystemInfo = deleteSystemInfo;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFkCompanyCode() {
    return fkCompanyCode;
  }

  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }
}
