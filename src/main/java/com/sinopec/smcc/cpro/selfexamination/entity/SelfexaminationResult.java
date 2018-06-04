/**
* Copyright 2018. 
* @Title SelfexaminationListResult.java
* @Package com.sinopec.smcc.cpro.selfexamination.entity
* @Description: TODO:
* @author dongxu
* @date 2018年6月2日下午11:39:40
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title SelfexaminationListResult.java
 * @Package com.sinopec.smcc.cpro.selfexamination.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年6月2日下午11:39:40
 * @version V1.0
 */
public class SelfexaminationResult {
  private String selfexaminationId;
  private String fkSystemId;
  private Integer fkInspectionStatus;
  private Integer fkInspectionReu;
  private Integer fkRectificationReu;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;

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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
