/**
* Copyright 2018. 
* @Title SelfexaminationListResult.java
* @Package com.sinopec.smcc.cpro.selfexamination.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午1:39:40
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title SelfexaminationListResult.java
 * @Package com.sinopec.smcc.cpro.selfexamination.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午1:39:40
 * @version V1.0
 */
public class SelfexaminationListResult {
  private String selfexaminationId;
  private String fkSystemId;
  private int inspectionStatus;
  private int inspectionReutle;
  private int rectificationReutle;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;
  private int deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updateTime;
  private String remark;

  private String reviewReportName;
  private String reviewReportPath;
  private String rectificationReportName;
  private String rectificationReportPath;
  
  public String getSelfexaminationId() {
    return selfexaminationId;
  }
  public void setSelfexaminationId(String selfexaminationId) {
    this.selfexaminationId = selfexaminationId;
  }
  
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  
  public int getInspectionStatus() {
    return inspectionStatus;
  }
  public void setInspectionStatus(int inspectionStatus) {
    this.inspectionStatus = inspectionStatus;
  }
  
  public int getInspectionReutle() {
    return inspectionReutle;
  }
  public void setInspectionReutle(int inspectionReutle) {
    this.inspectionReutle = inspectionReutle;
  }
  
  public int getRectificationReutle() {
    return rectificationReutle;
  }
  public void setRectificationReutle(int rectificationReutle) {
    this.rectificationReutle = rectificationReutle;
  }

  public Date getInspectionDate() {
    return inspectionDate;
  }
  public void setInspectionDate(Date inspectionDate) {
    this.inspectionDate = inspectionDate;
  }

  public Date getRectificationDate() {
    return rectificationDate;
  }
  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
  }

  public int getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(int deleteStatus) {
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

  public String getReviewReportName() {
    return reviewReportName;
  }
  public void setReviewReportName(String reviewReportName) {
    this.reviewReportName = reviewReportName;
  }
  
  public String getReviewReportPath() {
    return reviewReportPath;
  }
  public void setReviewReportPath(String reviewReportPath) {
    this.reviewReportPath = reviewReportPath;
  }
  
  public String getRectificationReportName() {
    return rectificationReportName;
  }
  public void setRectificationReportName(String rectificationReportName) {
    this.rectificationReportName = rectificationReportName;
  }
  
  public String getRectificationReportPath() {
    return rectificationReportPath;
  }
  public void setRectificationReportPath(String rectificationReportPath) {
    this.rectificationReportPath = rectificationReportPath;
  }
  @Override
  public String toString() {
    return "{"
        + "\"selfexaminationId\":\""+selfexaminationId+"\","
        + "\"fkSystemId\":\""+fkSystemId+"\","
        + "\"inspectionStatus\":\""+inspectionStatus+"\""
        + "\"inspectionReutle\":\""+inspectionReutle+"\""
        + "\"rectificationReutle\":\""+rectificationReutle+"\""
        + "\"inspectionDate\":\""+inspectionDate+"\""
        + "\"rectificationDate\":\""+rectificationDate+"\""
        + "\"deleteStatus\":\""+deleteStatus+"\""
        + "\"createUserName\":\""+createUserName+"\""
        + "\"createTime\":\""+createTime+"\""
        + "\"updateTime\":\""+updateTime+"\""
        + "\"remark\":\""+remark+"\""
        
        + "\"reviewReportName\":\""+reviewReportName+"\""
        + "\"reviewReportPath\":\""+reviewReportPath+"\""
        + "\"rectificationReportName\":\""+rectificationReportName+"\""
        + "\"rectificationReportPath\":\""+rectificationReportPath+"\""
      + "}";
  }
  
}
