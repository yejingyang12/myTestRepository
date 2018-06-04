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

import java.util.Date;

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
  private String attachPath;
  private Integer deleteStatus;
  private String createUserName;
  private Date createTime;
  private Date updateTime;
  private String remark;
  
  
  /**
   * @return the attachPath
   */
  public String getAttachPath() {
    return attachPath;
  }
  /**
   * @param attachPath the attachPath to set
   */
  public void setAttachPath(String attachPath) {
    this.attachPath = attachPath;
  }
  /**
   * @return the deleteStatus
   */
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  /**
   * @param deleteStatus the deleteStatus to set
   */
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  /**
   * @return the createUserName
   */
  public String getCreateUserName() {
    return createUserName;
  }
  /**
   * @param createUserName the createUserName to set
   */
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }
  /**
   * @return the createTime
   */
  public Date getCreateTime() {
    return createTime;
  }
  /**
   * @param createTime the createTime to set
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  /**
   * @return the updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }
  /**
   * @param updateTime the updateTime to set
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  /**
   * @return the remark
   */
  public String getRemark() {
    return remark;
  }
  /**
   * @param remark the remark to set
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }
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