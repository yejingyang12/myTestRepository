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

import com.alibaba.fastjson.JSON;
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

  private String examinationReportId;
  private String examinationReportName;
  private String examinationRectificationReportId;
  private String examinationRectificationReportName;
  
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Date updateTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String remark;
  private String selfInspectionName;
  
  
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
  public String getSelfInspectionName() {
    return selfInspectionName;
  }
  public void setSelfInspectionName(String selfInspectionName) {
    this.selfInspectionName = selfInspectionName;
  }
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
  public Integer getFkInspectionStatus() {
    return fkInspectionStatus;
  }
  public void setFkInspectionStatus(Integer fkInspectionStatus) {
    this.fkInspectionStatus = fkInspectionStatus;
  }
  public Integer getFkInspectionReu() {
    return fkInspectionReu;
  }
  public void setFkInspectionReu(Integer fkInspectionReu) {
    this.fkInspectionReu = fkInspectionReu;
  }
  public Integer getFkRectificationReu() {
    return fkRectificationReu;
  }
  public void setFkRectificationReu(Integer fkRectificationReu) {
    this.fkRectificationReu = fkRectificationReu;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDate() {
    return inspectionDate;
  }
  public void setInspectionDate(Date inspectionDate) {
    this.inspectionDate = inspectionDate;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRectificationDate() {
    return rectificationDate;
  }
  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
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
