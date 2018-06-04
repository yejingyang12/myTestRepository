/**
* 2018. 
* @Title SystemCode.java
* @Package com.sinopec.smcc.cpro.systemcode.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年6月3日下午7:49:52
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title SystemCode.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:49:52
 * @version V1.0
 */
public class SystemCode {
  
  private String SystemCodeId;
  private String fkCodeType;
  private String codeOrder;
  private String systemCode;
  private Integer deleteStatus;
  private String codeName;
  private String systemFatherCode;
  public String getSystemCodeId() {
    return SystemCodeId;
  }
  public void setSystemCodeId(String systemCodeId) {
    SystemCodeId = systemCodeId;
  }
  public String getFkCodeType() {
    return fkCodeType;
  }
  public void setFkCodeType(String fkCodeType) {
    this.fkCodeType = fkCodeType;
  }
  public String getCodeOrder() {
    return codeOrder;
  }
  public void setCodeOrder(String codeOrder) {
    this.codeOrder = codeOrder;
  }
  public String getSystemCode() {
    return systemCode;
  }
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  public String getSystemFatherCode() {
    return systemFatherCode;
  }
  public void setSystemFatherCode(String systemFatherCode) {
    this.systemFatherCode = systemFatherCode;
  }
  
}
