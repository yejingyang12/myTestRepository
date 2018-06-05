/**
* @Title MainListResult.java
* @Package com.sinopec.smcc.cpro.main.entity
* @Description: TODO:
* @author eric
* @date 2018年5月25日下午1:25:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.main.entity;

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
  private String fkBizSPRankLevel;
  private String fkBizSystemLevel;
  
  
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
