/**
* 2018. 
* @Title TypeInfoParam.java
* @Package com.sinopec.smcc.cpro.systemcode.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年6月3日下午11:15:35
* @version V1.0
*/
package com.sinopec.smcc.cpro.systemcode.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title TypeInfoParam.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午11:15:35
 * @version V1.0
 */
public class SystemCodeParam {
  
  private String typeInfoId;
  private Integer fkCodeType;
  private String codeOrder;
  private String systemCode;
  private Integer deleteStatus;
  private String codeName;
  private String systemFatherCode;
  public String getTypeInfoId() {
    return typeInfoId;
  }
  public void setTypeInfoId(String typeInfoId) {
    this.typeInfoId = typeInfoId;
  }
  public Integer getFkCodeType() {
    return fkCodeType;
  }
  public void setFkCodeType(Integer fkCodeType) {
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
  public String toString(){
    return JSON.toJSONString(this);
  }
}
