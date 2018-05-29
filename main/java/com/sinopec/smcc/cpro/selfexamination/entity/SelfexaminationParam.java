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
  private int inspectionStatus;
  private int inspectionStatusBegin;
  private int inspectionStatusEnd;
  private int inspectionReutle;
  private int inspectionReutleBegin;
  private int inspectionReutleEnd;
  private int rectificationReutle;
  private int rectificationReutleBegin;
  private int rectificationReutleEnd;
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
  private int deleteStatus;
  private int deleteStatusBegin;
  private int deleteStatusEnd;
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

  public int getInspectionStatus() {
    return inspectionStatus;
  }
  public void setInspectionStatus(int inspectionStatus) {
    this.inspectionStatus = inspectionStatus;
  }

  public int getInspectionStatusBegin() {
    return inspectionStatusBegin;
  }
  public void setInspectionStatusBegin(int inspectionStatusBegin) {
    this.inspectionStatusBegin = inspectionStatusBegin;
  }

  public int getInspectionStatusEnd() {
    return inspectionStatusEnd;
  }
  public void setInspectionStatusEnd(int inspectionStatusEnd) {
    this.inspectionStatusEnd = inspectionStatusEnd;
  }

  public int getInspectionReutle() {
    return inspectionReutle;
  }
  public void setInspectionReutle(int inspectionReutle) {
    this.inspectionReutle = inspectionReutle;
  }

  public int getInspectionReutleBegin() {
    return inspectionReutleBegin;
  }
  public void setInspectionReutleBegin(int inspectionReutleBegin) {
    this.inspectionReutleBegin = inspectionReutleBegin;
  }

  public int getInspectionReutleEnd() {
    return inspectionReutleEnd;
  }
  public void setInspectionReutleEnd(int inspectionReutleEnd) {
    this.inspectionReutleEnd = inspectionReutleEnd;
  }

  public int getRectificationReutle() {
    return rectificationReutle;
  }
  public void setRectificationReutle(int rectificationReutle) {
    this.rectificationReutle = rectificationReutle;
  }

  public int getRectificationReutleBegin() {
    return rectificationReutleBegin;
  }
  public void setRectificationReutleBegin(int rectificationReutleBegin) {
    this.rectificationReutleBegin = rectificationReutleBegin;
  }

  public int getRectificationReutleEnd() {
    return rectificationReutleEnd;
  }
  public void setRectificationReutleEnd(int rectificationReutleEnd) {
    this.rectificationReutleEnd = rectificationReutleEnd;
  }

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

  public int getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(int deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public int getDeleteStatusBegin() {
    return deleteStatusBegin;
  }
  public void setDeleteStatusBegin(int deleteStatusBegin) {
    this.deleteStatusBegin = deleteStatusBegin;
  }

  public int getDeleteStatusEnd() {
    return deleteStatusEnd;
  }
  public void setDeleteStatusEnd(int deleteStatusEnd) {
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
    return "{"
        + "\"selfexaminationId\":\""+selfexaminationId+"\","
        + "\"fkSystemId\":\""+fkSystemId+"\","
        + "\"inspectionStatus\":\""+inspectionStatus+"\""
        + "\"inspectionStatusBegin\":\""+inspectionStatusBegin+"\""
        + "\"inspectionStatusEnd\":\""+inspectionStatusEnd+"\""
        + "\"inspectionReutle\":\""+inspectionReutle+"\""
        + "\"inspectionReutleBegin\":\""+inspectionReutleBegin+"\""
        + "\"inspectionReutleEnd\":\""+inspectionReutleEnd+"\""
        + "\"rectificationReutle\":\""+rectificationReutle+"\""
        + "\"rectificationReutleBegin\":\""+rectificationReutleBegin+"\""
        + "\"rectificationReutleEnd\":\""+rectificationReutleEnd+"\""
        + "\"inspectionDate\":\""+inspectionDate+"\""
        + "\"inspectionDateBegin\":\""+inspectionDateBegin+"\""
        + "\"inspectionDateEnd\":\""+inspectionDateEnd+"\""
        + "\"rectificationDate\":\""+rectificationDate+"\""
        + "\"rectificationDateBegin\":\""+rectificationDateBegin+"\""
        + "\"rectificationDateEnd\":\""+rectificationDateEnd+"\""
        + "\"deleteStatus\":\""+deleteStatus+"\""
        + "\"deleteStatusBegin\":\""+deleteStatusBegin+"\""
        + "\"deleteStatusEnd\":\""+deleteStatusEnd+"\""
        + "\"createUserName\":\""+createUserName+"\""
        + "\"createTime\":\""+createTime+"\""
        + "\"createTimeBegin\":\""+createTimeBegin+"\""
        + "\"createTimeEnd\":\""+createTimeEnd+"\""
        + "\"updateTime\":\""+updateTime+"\""
        + "\"updateTimeBegin\":\""+updateTimeBegin+"\""
        + "\"updateTimeEnd\":\""+updateTimeEnd+"\""
        + "\"remark\":\""+remark+"\""
        
        + "\"reviewReportName\":\""+reviewReportName+"\""
        + "\"reviewReportPath\":\""+reviewReportPath+"\""
        + "\"rectificationReportName\":\""+rectificationReportName+"\""
        + "\"rectificationReportPath\":\""+rectificationReportPath+"\""
        
        + "\"pageSize\":\""+pageSize+"\","
        + "\"currentPage\":\""+currentPage+"\","
        + "\"field\":\""+field+"\","
        + "\"sort\":\""+sort+"\""
      + "}";
  }
  
}
