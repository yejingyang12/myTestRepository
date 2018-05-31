/**
* 2018. 
* @Title SystemResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月30日下午1:12:18
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月30日下午1:12:18
 * @version V1.0
 */
public class SystemResult {
  
  private String systemId;
  private String systemName;
  private String interconnectionSit;
  private String standardizedCode;
  private Date whenInvestmentUse;
  private String executiveOfficeName;
  private String sysBusDescription;
  private Integer subIsSystem;
  private String executiveDireCon;
  private Integer fkInfoSysTypeCon;
  private String gradeRecordSysName;
  private String sysBusinessDescriptionType;
  private String sysServiceSitScope;
  private String sysServiceSitObject;
  private String npCoverageRange;
  private String npNetworkProperties;
  private String sysBusSituationType;
  private String fkResponsibleType; 
  private Integer fkProductsType;
  private Integer serviceIsUse;
  private Integer fkExaminStatus;
  private String productsNumber;
  private Integer nationalUseProbability;
  private String fknationalIsProducts;
  private String fkCompanyCode;
  private String executiveDireConTel;
 
  
  /**
   * @return the systemId
   */
  public String getSystemId() {
    return systemId;
  }


  /**
   * @param systemId the systemId to set
   */
  public void setSystemId(String systemId) {
    this.systemId = systemId;
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
   * @return the interconnectionSit
   */
  public String getInterconnectionSit() {
    return interconnectionSit;
  }


  /**
   * @param interconnectionSit the interconnectionSit to set
   */
  public void setInterconnectionSit(String interconnectionSit) {
    this.interconnectionSit = interconnectionSit;
  }


  /**
   * @return the standardizedCode
   */
  public String getStandardizedCode() {
    return standardizedCode;
  }


  /**
   * @param standardizedCode the standardizedCode to set
   */
  public void setStandardizedCode(String standardizedCode) {
    this.standardizedCode = standardizedCode;
  }


  /**
   * @return the whenInvestmentUse
   */
  public Date getWhenInvestmentUse() {
    return whenInvestmentUse;
  }


  /**
   * @param whenInvestmentUse the whenInvestmentUse to set
   */
  public void setWhenInvestmentUse(Date whenInvestmentUse) {
    this.whenInvestmentUse = whenInvestmentUse;
  }


  /**
   * @return the executiveOfficeName
   */
  public String getExecutiveOfficeName() {
    return executiveOfficeName;
  }


  /**
   * @param executiveOfficeName the executiveOfficeName to set
   */
  public void setExecutiveOfficeName(String executiveOfficeName) {
    this.executiveOfficeName = executiveOfficeName;
  }


  /**
   * @return the sysBusDescription
   */
  public String getSysBusDescription() {
    return sysBusDescription;
  }


  /**
   * @param sysBusDescription the sysBusDescription to set
   */
  public void setSysBusDescription(String sysBusDescription) {
    this.sysBusDescription = sysBusDescription;
  }


  /**
   * @return the subIsSystem
   */
  public Integer getSubIsSystem() {
    return subIsSystem;
  }


  /**
   * @param subIsSystem the subIsSystem to set
   */
  public void setSubIsSystem(Integer subIsSystem) {
    this.subIsSystem = subIsSystem;
  }


  /**
   * @return the executiveDireCon
   */
  public String getExecutiveDireCon() {
    return executiveDireCon;
  }


  /**
   * @param executiveDireCon the executiveDireCon to set
   */
  public void setExecutiveDireCon(String executiveDireCon) {
    this.executiveDireCon = executiveDireCon;
  }


  /**
   * @return the fkInfoSysTypeCon
   */
  public Integer getFkInfoSysTypeCon() {
    return fkInfoSysTypeCon;
  }


  /**
   * @param fkInfoSysTypeCon the fkInfoSysTypeCon to set
   */
  public void setFkInfoSysTypeCon(Integer fkInfoSysTypeCon) {
    this.fkInfoSysTypeCon = fkInfoSysTypeCon;
  }


  /**
   * @return the gradeRecordSysName
   */
  public String getGradeRecordSysName() {
    return gradeRecordSysName;
  }


  /**
   * @param gradeRecordSysName the gradeRecordSysName to set
   */
  public void setGradeRecordSysName(String gradeRecordSysName) {
    this.gradeRecordSysName = gradeRecordSysName;
  }


  /**
   * @return the sysBusinessDescriptionType
   */
  public String getSysBusinessDescriptionType() {
    return sysBusinessDescriptionType;
  }


  /**
   * @param sysBusinessDescriptionType the sysBusinessDescriptionType to set
   */
  public void setSysBusinessDescriptionType(String sysBusinessDescriptionType) {
    this.sysBusinessDescriptionType = sysBusinessDescriptionType;
  }


  /**
   * @return the sysServiceSitScope
   */
  public String getSysServiceSitScope() {
    return sysServiceSitScope;
  }


  /**
   * @param sysServiceSitScope the sysServiceSitScope to set
   */
  public void setSysServiceSitScope(String sysServiceSitScope) {
    this.sysServiceSitScope = sysServiceSitScope;
  }


  /**
   * @return the sysServiceSitObject
   */
  public String getSysServiceSitObject() {
    return sysServiceSitObject;
  }


  /**
   * @param sysServiceSitObject the sysServiceSitObject to set
   */
  public void setSysServiceSitObject(String sysServiceSitObject) {
    this.sysServiceSitObject = sysServiceSitObject;
  }


  /**
   * @return the npCoverageRange
   */
  public String getNpCoverageRange() {
    return npCoverageRange;
  }


  /**
   * @param npCoverageRange the npCoverageRange to set
   */
  public void setNpCoverageRange(String npCoverageRange) {
    this.npCoverageRange = npCoverageRange;
  }


  /**
   * @return the npNetworkProperties
   */
  public String getNpNetworkProperties() {
    return npNetworkProperties;
  }


  /**
   * @param npNetworkProperties the npNetworkProperties to set
   */
  public void setNpNetworkProperties(String npNetworkProperties) {
    this.npNetworkProperties = npNetworkProperties;
  }


  /**
   * @return the sysBusSituationType
   */
  public String getSysBusSituationType() {
    return sysBusSituationType;
  }


  /**
   * @param sysBusSituationType the sysBusSituationType to set
   */
  public void setSysBusSituationType(String sysBusSituationType) {
    this.sysBusSituationType = sysBusSituationType;
  }


  /**
   * @return the fkResponsibleType
   */
  public String getFkResponsibleType() {
    return fkResponsibleType;
  }


  /**
   * @param fkResponsibleType the fkResponsibleType to set
   */
  public void setFkResponsibleType(String fkResponsibleType) {
    this.fkResponsibleType = fkResponsibleType;
  }


  /**
   * @return the fkProductsType
   */
  public Integer getFkProductsType() {
    return fkProductsType;
  }


  /**
   * @param fkProductsType the fkProductsType to set
   */
  public void setFkProductsType(Integer fkProductsType) {
    this.fkProductsType = fkProductsType;
  }


  /**
   * @return the serviceIsUse
   */
  public Integer getServiceIsUse() {
    return serviceIsUse;
  }


  /**
   * @param serviceIsUse the serviceIsUse to set
   */
  public void setServiceIsUse(Integer serviceIsUse) {
    this.serviceIsUse = serviceIsUse;
  }


  /**
   * @return the fkExaminStatus
   */
  public Integer getFkExaminStatus() {
    return fkExaminStatus;
  }


  /**
   * @param fkExaminStatus the fkExaminStatus to set
   */
  public void setFkExaminStatus(Integer fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }


  /**
   * @return the productsNumber
   */
  public String getProductsNumber() {
    return productsNumber;
  }


  /**
   * @param productsNumber the productsNumber to set
   */
  public void setProductsNumber(String productsNumber) {
    this.productsNumber = productsNumber;
  }


  /**
   * @return the nationalUseProbability
   */
  public int getNationalUseProbability() {
    return nationalUseProbability;
  }


  /**
   * @param nationalUseProbability the nationalUseProbability to set
   */
  public void setNationalUseProbability(int nationalUseProbability) {
    this.nationalUseProbability = nationalUseProbability;
  }


  /**
   * @return the fknationalIsProducts
   */
  public String getFknationalIsProducts() {
    return fknationalIsProducts;
  }


  /**
   * @param fknationalIsProducts the fknationalIsProducts to set
   */
  public void setFknationalIsProducts(String fknationalIsProducts) {
    this.fknationalIsProducts = fknationalIsProducts;
  }


  /**
   * @return the fkCompanyCode
   */
  public String getFkCompanyCode() {
    return fkCompanyCode;
  }


  /**
   * @param fkCompanyCode the fkCompanyCode to set
   */
  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }


  /**
   * @return the executiveDireConTel
   */
  public String getExecutiveDireConTel() {
    return executiveDireConTel;
  }


  /**
   * @param executiveDireConTel the executiveDireConTel to set
   */
  public void setExecutiveDireConTel(String executiveDireConTel) {
    this.executiveDireConTel = executiveDireConTel;
  }


  public String toString(){
    return JSON.toJSONString(this);
  }
}
