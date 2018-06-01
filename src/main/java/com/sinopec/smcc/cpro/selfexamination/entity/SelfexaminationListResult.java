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
  private Integer fkInspectionStatus;
  private Integer fkInspectionReu;
  private Integer fkRectificationReu;
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
  /**
   * @return the selfexaminationId
   */
  public String getSelfexaminationId() {
    return selfexaminationId;
  }
  /**
   * @param selfexaminationId the selfexaminationId to set
   */
  public void setSelfexaminationId(String selfexaminationId) {
    this.selfexaminationId = selfexaminationId;
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
   * @return the fkInspectionStatus
   */
  public Integer getFkInspectionStatus() {
    return fkInspectionStatus;
  }
  /**
   * @param fkInspectionStatus the fkInspectionStatus to set
   */
  public void setFkInspectionStatus(Integer fkInspectionStatus) {
    this.fkInspectionStatus = fkInspectionStatus;
  }
  /**
   * @return the fkInspectionReu
   */
  public Integer getFkInspectionReu() {
    return fkInspectionReu;
  }
  /**
   * @param fkInspectionReu the fkInspectionReu to set
   */
  public void setFkInspectionReu(Integer fkInspectionReu) {
    this.fkInspectionReu = fkInspectionReu;
  }
  /**
   * @return the fkRectificationReu
   */
  public Integer getFkRectificationReu() {
    return fkRectificationReu;
  }
  /**
   * @param fkRectificationReu the fkRectificationReu to set
   */
  public void setFkRectificationReu(Integer fkRectificationReu) {
    this.fkRectificationReu = fkRectificationReu;
  }
  /**
   * @return the inspectionDate
   */
  public Date getInspectionDate() {
    return inspectionDate;
  }
  /**
   * @param inspectionDate the inspectionDate to set
   */
  public void setInspectionDate(Date inspectionDate) {
    this.inspectionDate = inspectionDate;
  }
  /**
   * @return the rectificationDate
   */
  public Date getRectificationDate() {
    return rectificationDate;
  }
  /**
   * @param rectificationDate the rectificationDate to set
   */
  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
  }
  /**
   * @return the deleteStatus
   */
  public int getDeleteStatus() {
    return deleteStatus;
  }
  /**
   * @param deleteStatus the deleteStatus to set
   */
  public void setDeleteStatus(int deleteStatus) {
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
   * @return the reviewReportName
   */
  public String getReviewReportName() {
    return reviewReportName;
  }
  /**
   * @param reviewReportName the reviewReportName to set
   */
  public void setReviewReportName(String reviewReportName) {
    this.reviewReportName = reviewReportName;
  }
  /**
   * @return the reviewReportPath
   */
  public String getReviewReportPath() {
    return reviewReportPath;
  }
  /**
   * @param reviewReportPath the reviewReportPath to set
   */
  public void setReviewReportPath(String reviewReportPath) {
    this.reviewReportPath = reviewReportPath;
  }
  /**
   * @return the rectificationReportName
   */
  public String getRectificationReportName() {
    return rectificationReportName;
  }
  /**
   * @param rectificationReportName the rectificationReportName to set
   */
  public void setRectificationReportName(String rectificationReportName) {
    this.rectificationReportName = rectificationReportName;
  }
  /**
   * @return the rectificationReportPath
   */
  public String getRectificationReportPath() {
    return rectificationReportPath;
  }
  /**
   * @param rectificationReportPath the rectificationReportPath to set
   */
  public void setRectificationReportPath(String rectificationReportPath) {
    this.rectificationReportPath = rectificationReportPath;
  }
  
  
}
