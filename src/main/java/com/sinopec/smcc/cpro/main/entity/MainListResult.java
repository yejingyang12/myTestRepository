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
  private int appIsInternet;
  private int gradingStatus;
  private int examineStatus;
  private int recordStatus;
  private int evaluationStatus;
  private int examinationStatus;
  
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
  public int getAppIsInternet() {
    return appIsInternet;
  }
  public void setAppIsInternet(int appIsInternet) {
    this.appIsInternet = appIsInternet;
  }
  public int getGradingStatus() {
    return gradingStatus;
  }
  public void setGradingStatus(int gradingStatus) {
    this.gradingStatus = gradingStatus;
  }
  public int getExamineStatus() {
    return examineStatus;
  }
  public void setExamineStatus(int examineStatus) {
    this.examineStatus = examineStatus;
  }
  public int getRecordStatus() {
    return recordStatus;
  }
  public void setRecordStatus(int recordStatus) {
    this.recordStatus = recordStatus;
  }
  public int getEvaluationStatus() {
    return evaluationStatus;
  }
  public void setEvaluationStatus(int evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
  }
  public int getExaminationStatus() {
    return examinationStatus;
  }
  public void setExaminationStatus(int examinationStatus) {
    this.examinationStatus = examinationStatus;
  }
}
