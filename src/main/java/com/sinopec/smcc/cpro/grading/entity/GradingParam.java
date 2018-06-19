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
import com.fasterxml.jackson.annotation.JsonFormat;

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
  private String [] fkSystemIds;
  private String fkBizSPRankDegree;
  private String fkBizSPRankLevel;
  private String fkBizSystemDegree;
  private String fkBizSystemLevel;
  private String fkSpRanklevel;
  private Integer expertView;
  private String rankExplainDesc;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")  
  private Date rankTime;
  private Integer competentIsExisting;
  private String competentName;
  private Integer competentView;
  private String filler;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")  
  private Date fillDate;
  
  private String gradingReportName;
  private String gradingReportPath;
  private String expertReviewName;
  private String expertReviewPath;
  //上级主管部门审批意见
  private String directorOpinionPath;
  private String directorOpinionName;

  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  public String getGradingId() {
    return gradingId;
  }
  public void setGradingId(String gradingId) {
    this.gradingId = gradingId;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  public String[] getFkSystemIds() {
    return fkSystemIds;
  }
  public void setFkSystemIds(String[] fkSystemIds) {
    this.fkSystemIds = fkSystemIds;
  }
  public String getFkBizSPRankDegree() {
    return fkBizSPRankDegree;
  }
  public void setFkBizSPRankDegree(String fkBizSPRankDegree) {
    this.fkBizSPRankDegree = fkBizSPRankDegree;
  }
  public String getFkBizSPRankLevel() {
    return fkBizSPRankLevel;
  }
  public void setFkBizSPRankLevel(String fkBizSPRankLevel) {
    this.fkBizSPRankLevel = fkBizSPRankLevel;
  }
  public String getFkBizSystemDegree() {
    return fkBizSystemDegree;
  }
  public void setFkBizSystemDegree(String fkBizSystemDegree) {
    this.fkBizSystemDegree = fkBizSystemDegree;
  }
  public String getFkBizSystemLevel() {
    return fkBizSystemLevel;
  }
  public void setFkBizSystemLevel(String fkBizSystemLevel) {
    this.fkBizSystemLevel = fkBizSystemLevel;
  }
  public String getFkSpRanklevel() {
    return fkSpRanklevel;
  }
  public void setFkSpRanklevel(String fkSpRanklevel) {
    this.fkSpRanklevel = fkSpRanklevel;
  }
  public Integer getExpertView() {
    return expertView;
  }
  public void setExpertView(Integer expertView) {
    this.expertView = expertView;
  }
  public String getRankExplainDesc() {
    return rankExplainDesc;
  }
  public void setRankExplainDesc(String rankExplainDesc) {
    this.rankExplainDesc = rankExplainDesc;
  }
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getRankTime() {
    return rankTime;
  }
  public void setRankTime(Date rankTime) {
    this.rankTime = rankTime;
  }
  public Integer getCompetentIsExisting() {
    return competentIsExisting;
  }
  public void setCompetentIsExisting(Integer competentIsExisting) {
    this.competentIsExisting = competentIsExisting;
  }
  public String getCompetentName() {
    return competentName;
  }
  public void setCompetentName(String competentName) {
    this.competentName = competentName;
  }
  public Integer getCompetentView() {
    return competentView;
  }
  public void setCompetentView(Integer competentView) {
    this.competentView = competentView;
  }
  public String getFiller() {
    return filler;
  }
  public void setFiller(String filler) {
    this.filler = filler;
  }
//  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getFillDate() {
    return fillDate;
  }
  public void setFillDate(Date fillDate) {
    this.fillDate = fillDate;
  }
  public String getGradingReportName() {
    return gradingReportName;
  }
  public void setGradingReportName(String gradingReportName) {
    this.gradingReportName = gradingReportName;
  }
  public String getExpertReviewName() {
    return expertReviewName;
  }
  public void setExpertReviewName(String expertReviewName) {
    this.expertReviewName = expertReviewName;
  }
  public String getGradingReportPath() {
    return gradingReportPath;
  }
  public void setGradingReportPath(String gradingReportPath) {
    this.gradingReportPath = gradingReportPath;
  }
  public String getExpertReviewPath() {
    return expertReviewPath;
  }
  public void setExpertReviewPath(String expertReviewPath) {
    this.expertReviewPath = expertReviewPath;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getDirectorOpinionPath() {
    return directorOpinionPath;
  }
  public void setDirectorOpinionPath(String directorOpinionPath) {
    this.directorOpinionPath = directorOpinionPath;
  }
  public String getDirectorOpinionName() {
    return directorOpinionName;
  }
  public void setDirectorOpinionName(String directorOpinionName) {
    this.directorOpinionName = directorOpinionName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
