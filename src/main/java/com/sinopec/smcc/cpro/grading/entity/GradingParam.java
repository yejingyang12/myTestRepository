/**
* 2018. 
* @Title GradingParam.java
* @Package com.sinopec.smcc.cpro.grading.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:03:39
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title GradingParam.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:03:39
 * @version V1.0
 */
public class GradingParam {

  private String gradingId;
  private String fkSystemId;
  private String fkBizSPRankDegree;
  private String fkBizSPRankLevel;
  private String fkBizSystemDegree;
  private String fkBizSystemLevel;
  private String fkSpRanklevel;
  private Integer expertView;
  private String rankExplainDesc;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date rankTime;
  private Integer competentIsExisting;
  private String competentName;
  private Integer competentView;
  private String filler;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fillDate;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  /**
   * @return the gradingId
   */
  public String getGradingId() {
    return gradingId;
  }
  /**
   * @param gradingId the gradingId to set
   */
  public void setGradingId(String gradingId) {
    this.gradingId = gradingId;
  }
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
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
