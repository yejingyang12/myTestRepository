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
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTimeEnd;
  private String remark;
  private String selfInspectionName;
  
  private String examinationReportPath;
  private String examinationReportName;
  private String examinationRectificationReportPath;
  private String examinationRectificationReportName;
  
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
  
  public String getSelfInspectionName() {
    return selfInspectionName;
  }
  public void setSelfInspectionName(String selfInspectionName) {
    this.selfInspectionName = selfInspectionName;
  }
  
  public String getExaminationReportPath() {
    return examinationReportPath;
  }
  public void setExaminationReportPath(String examinationReportPath) {
    this.examinationReportPath = examinationReportPath;
  }
  public String getExaminationReportName() {
    return examinationReportName;
  }
  public void setExaminationReportName(String examinationReportName) {
    this.examinationReportName = examinationReportName;
  }
  public String getExaminationRectificationReportPath() {
    return examinationRectificationReportPath;
  }
  public void setExaminationRectificationReportPath(String examinationRectificationReportPath) {
    this.examinationRectificationReportPath = examinationRectificationReportPath;
  }
  public String getExaminationRectificationReportName() {
    return examinationRectificationReportName;
  }
  public void setExaminationRectificationReportName(String examinationRectificationReportName) {
    this.examinationRectificationReportName = examinationRectificationReportName;
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
