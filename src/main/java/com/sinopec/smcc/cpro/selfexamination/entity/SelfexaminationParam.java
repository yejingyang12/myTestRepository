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
  private Integer fkInspectionStatusBegin;
  private Integer fkInspectionStatusEnd;
  private Integer fkInspectionReu;
  private Integer fkInspectionReuBegin;
  private Integer fkInspectionReuEnd;
  private Integer fkRectificationReu;
  private Integer fkRectificationReuBegin;
  private Integer fkRectificationReuEnd;
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

  public String getInspectionName() {
    return inspectionName;
  }
  public void setInspectionName(String inspectionName) {
    this.inspectionName = inspectionName;
  }

  public Integer getFkInspectionStatus() {
    return fkInspectionStatus;
  }
  public void setFkInspectionStatus(Integer fkInspectionStatus) {
    this.fkInspectionStatus = fkInspectionStatus;
  }

  public Integer getFkInspectionStatusBegin() {
    return fkInspectionStatusBegin;
  }
  public void setFkInspectionStatusBegin(Integer fkInspectionStatusBegin) {
    this.fkInspectionStatusBegin = fkInspectionStatusBegin;
  }

  public Integer getFkInspectionStatusEnd() {
    return fkInspectionStatusEnd;
  }
  public void setFkInspectionStatusEnd(Integer fkInspectionStatusEnd) {
    this.fkInspectionStatusEnd = fkInspectionStatusEnd;
  }

  public Integer getFkInspectionReu() {
    return fkInspectionReu;
  }
  public void setFkInspectionReu(Integer fkInspectionReu) {
    this.fkInspectionReu = fkInspectionReu;
  }

  public Integer getFkInspectionReuBegin() {
    return fkInspectionReuBegin;
  }
  public void setFkInspectionReuBegin(Integer fkInspectionReuBegin) {
    this.fkInspectionReuBegin = fkInspectionReuBegin;
  }

  public Integer getFkInspectionReuEnd() {
    return fkInspectionReuEnd;
  }
  public void setFkInspectionReuEnd(Integer fkInspectionReuEnd) {
    this.fkInspectionReuEnd = fkInspectionReuEnd;
  }

  public Integer getFkRectificationReu() {
    return fkRectificationReu;
  }
  public void setFkRectificationReu(Integer fkRectificationReu) {
    this.fkRectificationReu = fkRectificationReu;
  }

  public Integer getFkRectificationReuBegin() {
    return fkRectificationReuBegin;
  }
  public void setFkRectificationReuBegin(Integer fkRectificationReuBegin) {
    this.fkRectificationReuBegin = fkRectificationReuBegin;
  }

  public Integer getFkRectificationReuEnd() {
    return fkRectificationReuEnd;
  }
  public void setFkRectificationReuEnd(Integer fkRectificationReuEnd) {
    this.fkRectificationReuEnd = fkRectificationReuEnd;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDate() {
    return inspectionDate;
  }
  public void setInspectionDate(Date inspectionDate) {
    this.inspectionDate = inspectionDate;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDateBegin() {
    return inspectionDateBegin;
  }
  public void setInspectionDateBegin(Date inspectionDateBegin) {
    this.inspectionDateBegin = inspectionDateBegin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRectificationDateBegin() {
    return rectificationDateBegin;
  }
  public void setRectificationDateBegin(Date rectificationDateBegin) {
    this.rectificationDateBegin = rectificationDateBegin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getCreateTimeBegin() {
    return createTimeBegin;
  }
  public void setCreateTimeBegin(Date createTimeBegin) {
    this.createTimeBegin = createTimeBegin;
  }
  
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getCreateTimeEnd() {
    return createTimeEnd;
  }
  public void setCreateTimeEnd(Date createTimeEnd) {
    this.createTimeEnd = createTimeEnd;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getUpdateTimeBegin() {
    return updateTimeBegin;
  }
  public void setUpdateTimeBegin(Date updateTimeBegin) {
    this.updateTimeBegin = updateTimeBegin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
