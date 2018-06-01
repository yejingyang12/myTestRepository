/**
* 2018. 
* @Title SysAttachFile.java
* @Package com.sinopec.smcc.cpro.file.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午1:59:39
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SysAttachFile.java
 * @Package com.sinopec.smcc.cpro.file.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月30日下午1:59:39
 * @version V1.0
 */
public class SysAttachFile {
  
  private String attachPath;
  private String attachId;
  private String fkSyssonId;
  private String attachType;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Integer deleteStatus;
  private String attachName;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  private String fkSystemId;
  public String getAttachPath() {
    return attachPath;
  }
  public void setAttachPath(String attachPath) {
    this.attachPath = attachPath;
  }
  public String getAttachId() {
    return attachId;
  }
  public void setAttachId(String attachId) {
    this.attachId = attachId;
  }
  public String getFkSyssonId() {
    return fkSyssonId;
  }
  public void setFkSyssonId(String fkSyssonId) {
    this.fkSyssonId = fkSyssonId;
  }
  public String getAttachType() {
    return attachType;
  }
  public void setAttachType(String attachType) {
    this.attachType = attachType;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public String getAttachName() {
    return attachName;
  }
  public void setAttachName(String attachName) {
    this.attachName = attachName;
  }
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  
  public String toString() {
    return JSON.toJSONString(this);
  }

}
