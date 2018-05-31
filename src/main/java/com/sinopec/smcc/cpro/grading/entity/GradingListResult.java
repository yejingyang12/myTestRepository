/**
* 2018. 
* @Title GradingListResult.java
* @Package com.sinopec.smcc.cpro.grading.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:07:27
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title GradingListResult.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:07:27
 * @version V1.0
 */
public class GradingListResult {

  private String fkSystemId;
  private String systemCodeId;
  private String codeName;
  private String fkBizSPRankDegree;
  private String fkBizSPRankLevel;
  private String fkBizSystemDegree;
  private String fkBizSystemLevel;
  private String fkSpRanklevel;
  private String rankExplainDesc;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date rankTime;
  private Integer competentIsExisting;
  private String competentName;
  private Integer competentView;
  private Integer expertView;
  private String filler;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fillDate;
  private String GradingReportName;
  private String GradingReportId;
  private String ExpertReviewName;
  private String ExpertReviewId;
  private String EvaluationPresentationName;
  private String EvaluationPresentationId;
  
  
  

  /**
   * @return the fkSystemId
   */
  public String getFkSystemId() {
    return fkSystemId;
  }
  /**
   * @param fkSystemId the fkSystemId to set
   */
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  /**
   * @return the rankTime
   */
  public Date getRankTime() {
    return rankTime;
  }
  /**
   * @param rankTime the rankTime to set
   */
  public void setRankTime(Date rankTime) {
    this.rankTime = rankTime;
  }
  /**
   * @return the competentIsExisting
   */
  public Integer getCompetentIsExisting() {
    return competentIsExisting;
  }
  /**
   * @param competentIsExisting the competentIsExisting to set
   */
  public void setCompetentIsExisting(Integer competentIsExisting) {
    this.competentIsExisting = competentIsExisting;
  }
  /**
   * @return the competentView
   */
  public Integer getCompetentView() {
    return competentView;
  }
  /**
   * @param competentView the competentView to set
   */
  public void setCompetentView(Integer competentView) {
    this.competentView = competentView;
  }
  /**
   * @return the expertView
   */
  public Integer getExpertView() {
    return expertView;
  }
  /**
   * @param expertView the expertView to set
   */
  public void setExpertView(Integer expertView) {
    this.expertView = expertView;
  }
  /**
   * @return the fillDate
   */
  public Date getFillDate() {
    return fillDate;
  }
  /**
   * @param fillDate the fillDate to set
   */
  public void setFillDate(Date fillDate) {
    this.fillDate = fillDate;
  }
  /**
   * @return the gradingReportName
   */
  public String getGradingReportName() {
    return GradingReportName;
  }
  /**
   * @param gradingReportName the gradingReportName to set
   */
  public void setGradingReportName(String gradingReportName) {
    GradingReportName = gradingReportName;
  }
  /**
   * @return the gradingReportId
   */
  public String getGradingReportId() {
    return GradingReportId;
  }
  /**
   * @param gradingReportId the gradingReportId to set
   */
  public void setGradingReportId(String gradingReportId) {
    GradingReportId = gradingReportId;
  }
  /**
   * @return the expertReviewName
   */
  public String getExpertReviewName() {
    return ExpertReviewName;
  }
  /**
   * @param expertReviewName the expertReviewName to set
   */
  public void setExpertReviewName(String expertReviewName) {
    ExpertReviewName = expertReviewName;
  }
  /**
   * @return the expertReviewId
   */
  public String getExpertReviewId() {
    return ExpertReviewId;
  }
  /**
   * @param expertReviewId the expertReviewId to set
   */
  public void setExpertReviewId(String expertReviewId) {
    ExpertReviewId = expertReviewId;
  }
  /**
   * @return the evaluationPresentationName
   */
  public String getEvaluationPresentationName() {
    return EvaluationPresentationName;
  }
  /**
   * @param evaluationPresentationName the evaluationPresentationName to set
   */
  public void setEvaluationPresentationName(String evaluationPresentationName) {
    EvaluationPresentationName = evaluationPresentationName;
  }
  /**
   * @return the evaluationPresentationId
   */
  public String getEvaluationPresentationId() {
    return EvaluationPresentationId;
  }
  /**
   * @param evaluationPresentationId the evaluationPresentationId to set
   */
  public void setEvaluationPresentationId(String evaluationPresentationId) {
    EvaluationPresentationId = evaluationPresentationId;
  }

  /**
   * @return the fkBizSPRankDegree
   */
  public String getFkBizSPRankDegree() {
    return fkBizSPRankDegree;
  }
  /**
   * @param fkBizSPRankDegree the fkBizSPRankDegree to set
   */
  public void setFkBizSPRankDegree(String fkBizSPRankDegree) {
    this.fkBizSPRankDegree = fkBizSPRankDegree;
  }
  /**
   * @return the fkBizSPRankLevel
   */
  public String getFkBizSPRankLevel() {
    return fkBizSPRankLevel;
  }
  /**
   * @param fkBizSPRankLevel the fkBizSPRankLevel to set
   */
  public void setFkBizSPRankLevel(String fkBizSPRankLevel) {
    this.fkBizSPRankLevel = fkBizSPRankLevel;
  }
  /**
   * @return the fkBizSystemDegree
   */
  public String getFkBizSystemDegree() {
    return fkBizSystemDegree;
  }
  /**
   * @param fkBizSystemDegree the fkBizSystemDegree to set
   */
  public void setFkBizSystemDegree(String fkBizSystemDegree) {
    this.fkBizSystemDegree = fkBizSystemDegree;
  }
  /**
   * @return the fkBizSystemLevel
   */
  public String getFkBizSystemLevel() {
    return fkBizSystemLevel;
  }
  /**
   * @param fkBizSystemLevel the fkBizSystemLevel to set
   */
  public void setFkBizSystemLevel(String fkBizSystemLevel) {
    this.fkBizSystemLevel = fkBizSystemLevel;
  }
  /**
   * @return the fkSpRanklevel
   */
  public String getFkSpRanklevel() {
    return fkSpRanklevel;
  }
  /**
   * @param fkSpRanklevel the fkSpRanklevel to set
   */
  public void setFkSpRanklevel(String fkSpRanklevel) {
    this.fkSpRanklevel = fkSpRanklevel;
  }
  /**
   * @return the rankExplainDesc
   */
  public String getRankExplainDesc() {
    return rankExplainDesc;
  }
  /**
   * @param rankExplainDesc the rankExplainDesc to set
   */
  public void setRankExplainDesc(String rankExplainDesc) {
    this.rankExplainDesc = rankExplainDesc;
  }
  /**
   * @return the competentName
   */
  public String getCompetentName() {
    return competentName;
  }
  /**
   * @param competentName the competentName to set
   */
  public void setCompetentName(String competentName) {
    this.competentName = competentName;
  }
  /**
   * @return the filler
   */
  public String getFiller() {
    return filler;
  }
  /**
   * @param filler the filler to set
   */
  public void setFiller(String filler) {
    this.filler = filler;
  }
  /**
   * @return the codeName
   */
  public String getCodeName() {
    return codeName;
  }
  /**
   * @param codeName the codeName to set
   */
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
  /**
   * @return the systemCodeId
   */
  public String getSystemCodeId() {
    return systemCodeId;
  }
  /**
   * @param systemCodeId the systemCodeId to set
   */
  public void setSystemCodeId(String systemCodeId) {
    this.systemCodeId = systemCodeId;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
