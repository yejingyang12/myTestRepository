/**
* 2018. 
* @Title SystemListResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月27日下午23:16:46
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;


/**
 * @Title SystemListResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月27日下午23:16:46
 * @version V1.0
 */
public class SystemCodeParam {
  private String systemCodeId;
  private String fkCodeType;
  private String codeName;
  private String systemCode;
  /**
   * @return the systemCodeId
   */
  public String getSystemCodeId() {
    return systemCodeId;
  }
  /**
   * @param systemCodeId the systemCodeId to set
   */
  public void setSystemCodeId(String systemCodeId) {
    this.systemCodeId = systemCodeId;
  }
  /**
   * @return the fkCodeType
   */
  public String getFkCodeType() {
    return fkCodeType;
  }
  /**
   * @param fkCodeType the fkCodeType to set
   */
  public void setFkCodeType(String fkCodeType) {
    this.fkCodeType = fkCodeType;
  }
  /**
   * @return the codeName
   */
  public String getCodeName() {
    return codeName;
  }
  /**
   * @param codeName the codeName to set
   */
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  /**
   * @return the systemCode
   */
  public String getSystemCode() {
    return systemCode;
  }
  /**
   * @param systemCode the systemCode to set
   */
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }
  
}
