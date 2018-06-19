/**
 * @Title GetEvaluationResult.java
 * @Package com.sinopec.smcc.cpro.evaluation.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月26日下午5:24:37
 * @version V1.0
 */
package com.sinopec.smcc.cpro.evaluation.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title GetEvaluationResult.java
 * @Package com.sinopec.smcc.cpro.evaluation.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月26日下午5:24:37
 * @version V1.0
 */
public class EvaluationResult {

  private String evaluationId;
  private String fkSystemId;
  private String examName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date examTime;
  private String examYear;
  private String examOrg;
  private Integer fkExamStatus;
  private Integer fkExamResult;
  private Integer fkRectificationReu;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;
  private String examReport;
  private String examReportName;
  private String rectificationReport;
  private String rectificationReportName;
  
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  
  
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
   * @return the evaluationId
   */
  public String getEvaluationId() {
    return evaluationId;
  }


  /**
   * @param evaluationId the evaluationId to set
   */
  public void setEvaluationId(String evaluationId) {
    this.evaluationId = evaluationId;
  }


  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }


  /**
   * @return the examName
   */
  public String getExamName() {
    return examName;
  }


  /**
   * @param examName the examName to set
   */
  public void setExamName(String examName) {
    this.examName = examName;
  }


  /**
   * @return the examTime
   */
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getExamTime() {
    return examTime;
  }


  /**
   * @param examTime the examTime to set
   */
  public void setExamTime(Date examTime) {
    this.examTime = examTime;
  }


  /**
   * @return the examYear
   */
  public String getExamYear() {
    return examYear;
  }


  /**
   * @param examYear the examYear to set
   */
  public void setExamYear(String examYear) {
    this.examYear = examYear;
  }


  /**
   * @return the examOrg
   */
  public String getExamOrg() {
    return examOrg;
  }


  /**
   * @param examOrg the examOrg to set
   */
  public void setExamOrg(String examOrg) {
    this.examOrg = examOrg;
  }


  /**
   * @return the examReport
   */
  public String getExamReport() {
    return examReport;
  }


  /**
   * @param examReport the examReport to set
   */
  public void setExamReport(String examReport) {
    this.examReport = examReport;
  }


  /**
   * @return the examReportName
   */
  public String getExamReportName() {
    return examReportName;
  }


  /**
   * @param examReportName the examReportName to set
   */
  public void setExamReportName(String examReportName) {
    this.examReportName = examReportName;
  }


  /**
   * @return the fkExamStatus
   */
  public Integer getFkExamStatus() {
    return fkExamStatus;
  }


  /**
   * @param fkExamStatus the fkExamStatus to set
   */
  public void setFkExamStatus(Integer fkExamStatus) {
    this.fkExamStatus = fkExamStatus;
  }


  /**
   * @return the fkExamResult
   */
  public Integer getFkExamResult() {
    return fkExamResult;
  }


  /**
   * @param fkExamResult the fkExamResult to set
   */
  public void setFkExamResult(Integer fkExamResult) {
    this.fkExamResult = fkExamResult;
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
   * @return the rectificationReport
   */
  public String getRectificationReport() {
    return rectificationReport;
  }


  /**
   * @param rectificationReport the rectificationReport to set
   */
  public void setRectificationReport(String rectificationReport) {
    this.rectificationReport = rectificationReport;
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


  public String toString(){
    return JSON.toJSONString(this);
  }
}
