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
  private int examStatus;
  private String examResult;
  private int rectificationReutle;
  private Date rectificationDate;
  private String rectificationReport;
  private String examReportName;
  private String rectificationReportName;

  public String getEvaluationId() {
    return evaluationId;
  }

  public void setEvaluationId(String evaluationId) {
    this.evaluationId = evaluationId;
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getExamName() {
    return examName;
  }

  public void setExamName(String examName) {
    this.examName = examName;
  }

  public Date getExamTime() {
    return examTime;
  }

  public void setExamTime(Date examTime) {
    this.examTime = examTime;
  }

  public String getExamYear() {
    return examYear;
  }

  public void setExamYear(String examYear) {
    this.examYear = examYear;
  }

  public String getExamOrg() {
    return examOrg;
  }

  public void setExamOrg(String examOrg) {
    this.examOrg = examOrg;
  }

  public String getExamReport() {
    return examReport;
  }

  public void setExamReport(String examReport) {
    this.examReport = examReport;
  }

  public int getExamStatus() {
    return examStatus;
  }

  public void setExamStatus(int examStatus) {
    this.examStatus = examStatus;
  }

  public String getExamResult() {
    return examResult;
  }

  public void setExamResult(String examResult) {
    this.examResult = examResult;
  }

  public int getRectificationReutle() {
    return rectificationReutle;
  }

  public void setRectificationReutle(int rectificationReutle) {
    this.rectificationReutle = rectificationReutle;
  }

  public Date getRectificationDate() {
    return rectificationDate;
  }

  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
  }

  public String getRectificationReport() {
    return rectificationReport;
  }

  public void setRectificationReport(String rectificationReport) {
    this.rectificationReport = rectificationReport;
  }

  public String getExamReportName() {
    return examReportName;
  }

  public void setExamReportName(String examReportName) {
    this.examReportName = examReportName;
  }

  public String getRectificationReportName() {
    return rectificationReportName;
  }

  public void setRectificationReportName(String rectificationReportName) {
    this.rectificationReportName = rectificationReportName;
  }
}
