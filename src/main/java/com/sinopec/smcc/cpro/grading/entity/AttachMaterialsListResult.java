/**
* 2018. 
* @Title AttachListResult.java
* @Package com.sinopec.smcc.cpro.grading.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月30日上午10:28:02
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.entity;

import com.alibaba.fastjson.JSON;


/**
 * @Title AttachListResult.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月30日上午10:28:02
 * @version V1.0
 */
public class AttachMaterialsListResult {

  private String attachId;
  private String fkSystemId;
  private String fkSyssonId;
  private String fkAttachType;
  private String attachName;

  /**
   * @return the attachId
   */
  public String getAttachId() {
    return attachId;
  }
  /**
   * @param attachId the attachId to set
   */
  public void setAttachId(String attachId) {
    this.attachId = attachId;
  }
  /**
   * @return the fkSystemId
   */
  public String getFkSystemId() {
    return fkSystemId;
  }
  /**
   * @param fkSystemId the fkSystemId to set
   */
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  /**
   * @return the fkSyssonId
   */
  public String getFkSyssonId() {
    return fkSyssonId;
  }
  /**
   * @param fkSyssonId the fkSyssonId to set
   */
  public void setFkSyssonId(String fkSyssonId) {
    this.fkSyssonId = fkSyssonId;
  }
  /**
   * @return the fkAttachType
   */
  public String getFkAttachType() {
    return fkAttachType;
  }
  /**
   * @param fkAttachType the fkAttachType to set
   */
  public void setFkAttachType(String fkAttachType) {
    this.fkAttachType = fkAttachType;
  }
  /**
   * @return the attachName
   */
  public String getAttachName() {
    return attachName;
  }
  /**
   * @param attachName the attachName to set
   */
  public void setAttachName(String attachName) {
    this.attachName = attachName;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}