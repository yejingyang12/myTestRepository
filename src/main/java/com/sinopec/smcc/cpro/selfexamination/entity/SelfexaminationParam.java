/**
* Copyright 2018. 
* @Title SelfexaminationParam.java
* @Package com.sinopec.smcc.cpro.selfexamination.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午1:39:23
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title SelfexaminationParam.java
 * @Package com.sinopec.smcc.cpro.selfexamination.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午1:39:23
 * @version V1.0
 */
public class SelfexaminationParam {
  private String selfexaminationId;
  private String fkSystemId;
  private String inspectionName;
  private Integer fkInspectionStatus;
  private Integer inspectionStatusBegin;
  private Integer inspectionStatusEnd;
  private Integer fkInspectionReu;
  private Integer inspectionReutleBegin;
  private Integer inspectionReutleEnd;
  private Integer fkRectificationReu;
  private Integer rectificationReutleBegin;
  private Integer rectificationReutleEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDateEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDateEnd;
  private Integer deleteStatus;
  private Integer deleteStatusBegin;
  private Integer deleteStatusEnd;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createTimeEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updateTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updateTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updateTimeEnd;
  private String remark;
  
  //测评报告
  private String reviewReportName;
  private String reviewReportPath;
  //整改报告
  private String rectificationReportName;
  private String rectificationReportPath;
  
  private int pageSize;
  private int currentPage;
  private String field;
  private String sort;
  
  
  
  /**
   * @return the inspectionName
   */
  public String getInspectionName() {
    return inspectionName;
  }
  /**
   * @param inspectionName the inspectionName to set
   */
  public void setInspectionName(String inspectionName) {
    this.inspectionName = inspectionName;
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

  public Integer getInspectionStatusBegin() {
    return inspectionStatusBegin;
  }
  public void setInspectionStatusBegin(Integer inspectionStatusBegin) {
    this.inspectionStatusBegin = inspectionStatusBegin;
  }

  public Integer getInspectionStatusEnd() {
    return inspectionStatusEnd;
  }
  public void setInspectionStatusEnd(Integer inspectionStatusEnd) {
    this.inspectionStatusEnd = inspectionStatusEnd;
  }

  public Integer getInspectionReutleBegin() {
    return inspectionReutleBegin;
  }
  public void setInspectionReutleBegin(Integer inspectionReutleBegin) {
    this.inspectionReutleBegin = inspectionReutleBegin;
  }

  public Integer getInspectionReutleEnd() {
    return inspectionReutleEnd;
  }
  public void setInspectionReutleEnd(Integer inspectionReutleEnd) {
    this.inspectionReutleEnd = inspectionReutleEnd;
  }

  public Integer getRectificationReutleBegin() {
    return rectificationReutleBegin;
  }
  public void setRectificationReutleBegin(Integer rectificationReutleBegin) {
    this.rectificationReutleBegin = rectificationReutleBegin;
  }

  public Integer getRectificationReutleEnd() {
    return rectificationReutleEnd;
  }
  public void setRectificationReutleEnd(Integer rectificationReutleEnd) {
    this.rectificationReutleEnd = rectificationReutleEnd;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDate() {
    return inspectionDate;
  }
  public void setInspectionDate(Date inspectionDate) {
    this.inspectionDate = inspectionDate;
  }

  public Date getInspectionDateBegin() {
    return inspectionDateBegin;
  }
  public void setInspectionDateBegin(Date inspectionDateBegin) {
    this.inspectionDateBegin = inspectionDateBegin;
  }

  public Date getInspectionDateEnd() {
    return inspectionDateEnd;
  }
  public void setInspectionDateEnd(Date inspectionDateEnd) {
    this.inspectionDateEnd = inspectionDateEnd;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRectificationDate() {
    return rectificationDate;
  }
  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
  }

  public Date getRectificationDateBegin() {
    return rectificationDateBegin;
  }
  public void setRectificationDateBegin(Date rectificationDateBegin) {
    this.rectificationDateBegin = rectificationDateBegin;
  }

  public Date getRectificationDateEnd() {
    return rectificationDateEnd;
  }
  public void setRectificationDateEnd(Date rectificationDateEnd) {
    this.rectificationDateEnd = rectificationDateEnd;
  }

  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public Integer getDeleteStatusBegin() {
    return deleteStatusBegin;
  }
  public void setDeleteStatusBegin(Integer deleteStatusBegin) {
    this.deleteStatusBegin = deleteStatusBegin;
  }

  public Integer getDeleteStatusEnd() {
    return deleteStatusEnd;
  }
  public void setDeleteStatusEnd(Integer deleteStatusEnd) {
    this.deleteStatusEnd = deleteStatusEnd;
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

  public Date getCreateTimeBegin() {
    return createTimeBegin;
  }
  public void setCreateTimeBegin(Date createTimeBegin) {
    this.createTimeBegin = createTimeBegin;
  }

  public Date getCreateTimeEnd() {
    return createTimeEnd;
  }
  public void setCreateTimeEnd(Date createTimeEnd) {
    this.createTimeEnd = createTimeEnd;
  }

  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Date getUpdateTimeBegin() {
    return updateTimeBegin;
  }
  public void setUpdateTimeBegin(Date updateTimeBegin) {
    this.updateTimeBegin = updateTimeBegin;
  }

  public Date getUpdateTimeEnd() {
    return updateTimeEnd;
  }
  public void setUpdateTimeEnd(Date updateTimeEnd) {
    this.updateTimeEnd = updateTimeEnd;
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
  
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {
    return currentPage;
  }
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public String getField() {
    return field;
  }
  public void setField(String field) {
    this.field = field;
  }

  public String getSort() {
    return sort;
  }
  public void setSort(String sort) {
    this.sort = sort;
  }

  public String toString() {
    return JSON.toJSONString(this);
  }
}
