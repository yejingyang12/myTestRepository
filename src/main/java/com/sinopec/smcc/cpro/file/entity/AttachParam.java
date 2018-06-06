/**
* @Title AttachFileParam.java
* @Package com.sinopec.smcc.cpro.file.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月30日下午7:03:43
* @version V1.0
*/
package com.sinopec.smcc.cpro.file.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title AttachFileParam.java
 * @Package com.sinopec.smcc.cpro.file.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月30日下午7:03:43
 * @version V1.0
 */
public class AttachParam {

  private String fileId;
  private String systemId;
  private String syssonId;
  private String attachType;
  private String attachName;
  private String mongoFileId;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private String remark;
  
  private String uploadUrl;
  
  
  public String getFileId() {
    return fileId;
  }
  public void setFileId(String fileId) {
    this.fileId = fileId;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getSyssonId() {
    return syssonId;
  }
  public void setSyssonId(String syssonId) {
    this.syssonId = syssonId;
  }
  public String getAttachType() {
    return attachType;
  }
  public void setAttachType(String attachType) {
    this.attachType = attachType;
  }
  public String getAttachName() {
    return attachName;
  }
  public void setAttachName(String attachName) {
    this.attachName = attachName;
  }
  public String getMongoFileId() {
    return mongoFileId;
  }
  public void setMongoFileId(String mongoFileId) {
    this.mongoFileId = mongoFileId;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getUploadUrl() {
    return uploadUrl;
  }
  public void setUploadUrl(String uploadUrl) {
    this.uploadUrl = uploadUrl;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
