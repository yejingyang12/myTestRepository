/**
 * @Title EvaluationListResult.java
 * @Package com.sinopec.smcc.cpro.evaluation.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午9:57:52
 * @version V1.0
 */
package com.sinopec.smcc.cpro.evaluation.entity;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * @Title EvaluationListResult.java
 * @Package com.sinopec.smcc.cpro.evaluation.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午9:57:52
 * @version V1.0
 */
public class EvaluationListResult {

  private String evaluationId;
  private String systemName;
  private String examName;
  private Date examTime;
  private String examYear;
  private String examOrg;
  private String examReport;
  private Integer fkExamStatus;
  private String fkExamResult;
  private Integer fkRectificationReu;
  private Date rectificationDate;
  private String rectificationReport;
  private String examReportName;
  private String rectificationReportName;

 
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


  /**
   * @return the systemName
   */
  public String getSystemName() {
    return systemName;
  }


  /**
   * @param systemName the systemName to set
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
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
  public String getFkExamResult() {
    return fkExamResult;
  }


  /**
   * @param fkExamResult the fkExamResult to set
   */
  public void setFkExamResult(String fkExamResult) {
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
