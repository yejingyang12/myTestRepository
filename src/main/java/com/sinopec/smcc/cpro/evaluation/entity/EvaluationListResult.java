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
  private int fkexamStatus;
  private String fkexamResult;
  private int fkrectificationReutle;
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
   * @return the fkexamStatus
   */
  public int getFkexamStatus() {
    return fkexamStatus;
  }
  /**
   * @param fkexamStatus the fkexamStatus to set
   */
  public void setFkexamStatus(int fkexamStatus) {
    this.fkexamStatus = fkexamStatus;
  }
  /**
   * @return the fkexamResult
   */
  public String getFkexamResult() {
    return fkexamResult;
  }
  /**
   * @param fkexamResult the fkexamResult to set
   */
  public void setFkexamResult(String fkexamResult) {
    this.fkexamResult = fkexamResult;
  }
  /**
   * @return the fkrectificationReutle
   */
  public int getFkrectificationReutle() {
    return fkrectificationReutle;
  }
  /**
   * @param fkrectificationReutle the fkrectificationReutle to set
   */
  public void setFkrectificationReutle(int fkrectificationReutle) {
    this.fkrectificationReutle = fkrectificationReutle;
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

  
}
