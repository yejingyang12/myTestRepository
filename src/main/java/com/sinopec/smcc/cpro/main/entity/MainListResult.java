/**
* @Title MainListResult.java
* @Package com.sinopec.smcc.cpro.main.entity
* @Description: TODO:
* @author eric
* @date 2018年5月25日下午1:25:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.entity;

import java.util.Date;

/**
 * @Title MainListResult.java
 * @Package com.sinopec.smcc.cpro.main.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年5月25日下午1:25:15
 * @version V1.0
 */
public class MainListResult {

  private String systemId;
  private String systemName;
  private String companyName;
  private String plateType;
  private String InfoSysTypeConstruction;
  private String SprankLevel;
  private Integer appIsInternet;
  private Integer gradingStatus;
  private Integer examineStatus;
  private Integer recordStatus;
  private Integer evaluationStatus;
  private Integer examinationStatus;
  private String companyId;
  private String fkSpRanklevel;
  private String gradingPkId; 
  private String checkPkId;
  private String evaluationPkId;
  private String selfPkId;
  private String companyCode;
  private Integer spRanklevelCount;
  private String spRanklevelName;
  private String recordCompany;
  private Integer fkSystemIsMerge;
  private Date scoreCreateTime;
  private Date recordCreateTime;
  private String recordCode;
  private String acceptCompany;
  private String fkChangeMatter;
  private String changeReason;
  private String changeContent;
  

  
  /**
   * @return the fkChangeMatter
   */
  public String getFkChangeMatter() {
    return fkChangeMatter;
  }
  /**
   * @param fkChangeMatter the fkChangeMatter to set
   */
  public void setFkChangeMatter(String fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
  }
  /**
   * @return the changeReason
   */
  public String getChangeReason() {
    return changeReason;
  }
  /**
   * @param changeReason the changeReason to set
   */
  public void setChangeReason(String changeReason) {
    this.changeReason = changeReason;
  }
  /**
   * @return the changeContent
   */
  public String getChangeContent() {
    return changeContent;
  }
  /**
   * @param changeContent the changeContent to set
   */
  public void setChangeContent(String changeContent) {
    this.changeContent = changeContent;
  }
  /**
   * @return the recordCode
   */
  public String getRecordCode() {
    return recordCode;
  }
  /**
   * @param recordCode the recordCode to set
   */
  public void setRecordCode(String recordCode) {
    this.recordCode = recordCode;
  }
  /**
   * @return the acceptCompany
   */
  public String getAcceptCompany() {
    return acceptCompany;
  }
  /**
   * @param acceptCompany the acceptCompany to set
   */
  public void setAcceptCompany(String acceptCompany) {
    this.acceptCompany = acceptCompany;
  }
  /**
   * @return the recordCreateTime
   */
  public Date getRecordCreateTime() {
    return recordCreateTime;
  }
  /**
   * @param recordCreateTime the recordCreateTime to set
   */
  public void setRecordCreateTime(Date recordCreateTime) {
    this.recordCreateTime = recordCreateTime;
  }
  /**
   * @return the scoreCreateTime
   */
  public Date getScoreCreateTime() {
    return scoreCreateTime;
  }
  /**
   * @param scoreCreateTime the scoreCreateTime to set
   */
  public void setScoreCreateTime(Date scoreCreateTime) {
    this.scoreCreateTime = scoreCreateTime;
  }
  /**
   * @return the fkSystemIsMerge
   */
  public Integer getFkSystemIsMerge() {
    return fkSystemIsMerge;
  }
  /**
   * @param fkSystemIsMerge the fkSystemIsMerge to set
   */
  public void setFkSystemIsMerge(Integer fkSystemIsMerge) {
    this.fkSystemIsMerge = fkSystemIsMerge;
  }
  /**
   * @return the recordCompany
   */
  public String getRecordCompany() {
    return recordCompany;
  }
  /**
   * @param recordCompany the recordCompany to set
   */
  public void setRecordCompany(String recordCompany) {
    this.recordCompany = recordCompany;
  }
  /**
   * @return the spRanklevelCount
   */
  public Integer getSpRanklevelCount() {
    return spRanklevelCount;
  }
  /**
   * @param spRanklevelCount the spRanklevelCount to set
   */
  public void setSpRanklevelCount(Integer spRanklevelCount) {
    this.spRanklevelCount = spRanklevelCount;
  }
  /**
   * @return the spRanklevelName
   */
  public String getSpRanklevelName() {
    return spRanklevelName;
  }
  /**
   * @param spRanklevelName the spRanklevelName to set
   */
  public void setSpRanklevelName(String spRanklevelName) {
    this.spRanklevelName = spRanklevelName;
  }
  /**
   * @return the companyCode
   */
  public String getCompanyCode() {
    return companyCode;
  }
  /**
   * @param companyCode the companyCode to set
   */
  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }
  /**
   * @return the gradingPkId
   */
  public String getGradingPkId() {
    return gradingPkId;
  }
  /**
   * @param gradingPkId the gradingPkId to set
   */
  public void setGradingPkId(String gradingPkId) {
    this.gradingPkId = gradingPkId;
  }
  /**
   * @return the checkPkId
   */
  public String getCheckPkId() {
    return checkPkId;
  }
  /**
   * @param checkPkId the checkPkId to set
   */
  public void setCheckPkId(String checkPkId) {
    this.checkPkId = checkPkId;
  }
  /**
   * @return the evaluationPkId
   */
  public String getEvaluationPkId() {
    return evaluationPkId;
  }
  /**
   * @param evaluationPkId the evaluationPkId to set
   */
  public void setEvaluationPkId(String evaluationPkId) {
    this.evaluationPkId = evaluationPkId;
  }
  /**
   * @return the selfPkId
   */
  public String getSelfPkId() {
    return selfPkId;
  }
  /**
   * @param selfPkId the selfPkId to set
   */
  public void setSelfPkId(String selfPkId) {
    this.selfPkId = selfPkId;
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
   * @return the companyId
   */
  public String getCompanyId() {
    return companyId;
  }
  /**
   * @param companyId the companyId to set
   */
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public String getPlateType() {
    return plateType;
  }
  public void setPlateType(String plateType) {
    this.plateType = plateType;
  }
  public String getInfoSysTypeConstruction() {
    return InfoSysTypeConstruction;
  }
  public void setInfoSysTypeConstruction(String infoSysTypeConstruction) {
    InfoSysTypeConstruction = infoSysTypeConstruction;
  }
  public String getSprankLevel() {
    return SprankLevel;
  }
  public void setSprankLevel(String sprankLevel) {
    SprankLevel = sprankLevel;
  }
  public Integer getAppIsInternet() {
    return appIsInternet;
  }
  public void setAppIsInternet(Integer appIsInternet) {
    this.appIsInternet = appIsInternet;
  }
  public Integer getGradingStatus() {
    return gradingStatus;
  }
  public void setGradingStatus(Integer gradingStatus) {
    this.gradingStatus = gradingStatus;
  }
  public Integer getExamineStatus() {
    return examineStatus;
  }
  public void setExamineStatus(Integer examineStatus) {
    this.examineStatus = examineStatus;
  }
  public Integer getRecordStatus() {
    return recordStatus;
  }
  public void setRecordStatus(Integer recordStatus) {
    this.recordStatus = recordStatus;
  }
  public Integer getEvaluationStatus() {
    return evaluationStatus;
  }
  public void setEvaluationStatus(Integer evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
  }
  public Integer getExaminationStatus() {
    return examinationStatus;
  }
  public void setExaminationStatus(Integer examinationStatus) {
    this.examinationStatus = examinationStatus;
  }
}
