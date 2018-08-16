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

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

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
  private String systemName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String selfInspectionName;

  private String examinationReportId;
  private String examinationReportName;
  private String examinationRectificationReportId;
  private String examinationRectificationReportName;
  
  private String inspectionStatusName;
  private String inspectionReuName;
  private String rectificationReuName;
  
  
  /**
   * @return the inspectionStatusName
   */
  public String getInspectionStatusName() {
    return inspectionStatusName;
  }
  /**
   * @param inspectionStatusName the inspectionStatusName to set
   */
  public void setInspectionStatusName(String inspectionStatusName) {
    this.inspectionStatusName = inspectionStatusName;
  }
  /**
   * @return the inspectionReuName
   */
  public String getInspectionReuName() {
    return inspectionReuName;
  }
  /**
   * @param inspectionReuName the inspectionReuName to set
   */
  public void setInspectionReuName(String inspectionReuName) {
    this.inspectionReuName = inspectionReuName;
  }
  /**
   * @return the rectificationReuName
   */
  public String getRectificationReuName() {
    return rectificationReuName;
  }
  /**
   * @param rectificationReuName the rectificationReuName to set
   */
  public void setRectificationReuName(String rectificationReuName) {
    this.rectificationReuName = rectificationReuName;
  }
  /**
   * @return the inspectionName
   */
  public String getSystemName() {
    return systemName;
  }
  /**
   * @param inspectionName the inspectionName to set
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
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
  @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
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
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }
  /**
   * @param updateTime the updateTime to set
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  public String getSelfInspectionName() {
    return selfInspectionName;
  }
  public void setSelfInspectionName(String selfInspectionName) {
    this.selfInspectionName = selfInspectionName;
  }
  public String getExaminationReportId() {
    return examinationReportId;
  }
  public void setExaminationReportId(String examinationReportId) {
    this.examinationReportId = examinationReportId;
  }
  public String getExaminationReportName() {
    return examinationReportName;
  }
  public void setExaminationReportName(String examinationReportName) {
    this.examinationReportName = examinationReportName;
  }
  public String getExaminationRectificationReportId() {
    return examinationRectificationReportId;
  }
  public void setExaminationRectificationReportId(String examinationRectificationReportId) {
    this.examinationRectificationReportId = examinationRectificationReportId;
  }
  public String getExaminationRectificationReportName() {
    return examinationRectificationReportName;
  }
  public void setExaminationRectificationReportName(String examinationRectificationReportName) {
    this.examinationRectificationReportName = examinationRectificationReportName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
