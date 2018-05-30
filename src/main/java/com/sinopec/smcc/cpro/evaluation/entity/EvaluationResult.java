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
  private String examName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date examTime;
  private String examYear;
  private String examOrg;
  private String examReport;
  private String examReportName;
  private Integer examStatus;
  private Integer examResult;
  private Integer rectificationReutle;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rectificationDate;
  private Integer rectificationReport;
  private String rectificationReportName;
  public String getEvaluationId() {
    return evaluationId;
  }
  public void setEvaluationId(String evaluationId) {
    this.evaluationId = evaluationId;
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
  public String getExamReportName() {
    return examReportName;
  }
  public void setExamReportName(String examReportName) {
    this.examReportName = examReportName;
  }
  public Integer getExamStatus() {
    return examStatus;
  }
  public void setExamStatus(Integer examStatus) {
    this.examStatus = examStatus;
  }
  public Integer getExamResult() {
    return examResult;
  }
  public void setExamResult(Integer examResult) {
    this.examResult = examResult;
  }
  public Integer getRectificationReutle() {
    return rectificationReutle;
  }
  public void setRectificationReutle(Integer rectificationReutle) {
    this.rectificationReutle = rectificationReutle;
  }
  public Date getRectificationDate() {
    return rectificationDate;
  }
  public void setRectificationDate(Date rectificationDate) {
    this.rectificationDate = rectificationDate;
  }
  public Integer getRectificationReport() {
    return rectificationReport;
  }
  public void setRectificationReport(Integer rectificationReport) {
    this.rectificationReport = rectificationReport;
  }
  public String getRectificationReportName() {
    return rectificationReportName;
  }
  public void setRectificationReportName(String rectificationReportName) {
    this.rectificationReportName = rectificationReportName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
