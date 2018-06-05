/**
* 208. 
* @Title SystemResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 208年5月30日下午:2:8
* @version V.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title SystemResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 208年5月30日下午:2:8
 * @version V.0
 */
public class SystemResult {
  
  private String systemId;
  private String systemName;
  private String standardizedCode;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date whenInvestmentUse;
  private String executiveOfficeName;
  private String sysBusDescription;
  private Integer subIsSystem;
  private String executiveDireCon;
  private String gradeRecordSysName;
  private String fkResponsibleType; 
  private Integer fkProductsType;
  private Integer serviceIsUse;
  private Integer fkExaminStatus;
  private String productsNumber;
  private Integer nUseProbability;
  private String fkNationalIsProducts;
  private String fkCompanyCode;
  private String executiveDireConTel;
  private String ConstructionTypeName;
  private String CombinedName;
  private String BusinessName;
  private String ServiceScopeName;
  private String ServiceObjectName;
  private String CoverScopeName;
  private String NewWorkNatureName;
  private String SystemInterName;
  
  
  
  /**
   * @return the nUseProbability
   */
  public Integer getnUseProbability() {
    return nUseProbability;
  }


  /**
   * @param nUseProbability the nUseProbability to set
   */
  public void setnUseProbability(Integer nUseProbability) {
    this.nUseProbability = nUseProbability;
  }


  /**
   * @return the fkNationalIsProducts
   */
  public String getFkNationalIsProducts() {
    return fkNationalIsProducts;
  }


  /**
   * @param fkNationalIsProducts the fkNationalIsProducts to set
   */
  public void setFkNationalIsProducts(String fkNationalIsProducts) {
    this.fkNationalIsProducts = fkNationalIsProducts;
  }


  /**
   * @return the constructionTypeName
   */
  public String getConstructionTypeName() {
    return ConstructionTypeName;
  }


  /**
   * @param constructionTypeName the constructionTypeName to set
   */
  public void setConstructionTypeName(String constructionTypeName) {
    ConstructionTypeName = constructionTypeName;
  }


  /**
   * @return the combinedName
   */
  public String getCombinedName() {
    return CombinedName;
  }


  /**
   * @param combinedName the combinedName to set
   */
  public void setCombinedName(String combinedName) {
    CombinedName = combinedName;
  }


  /**
   * @return the businessName
   */
  public String getBusinessName() {
    return BusinessName;
  }


  /**
   * @param businessName the businessName to set
   */
  public void setBusinessName(String businessName) {
    BusinessName = businessName;
  }


  /**
   * @return the serviceScopeName
   */
  public String getServiceScopeName() {
    return ServiceScopeName;
  }


  /**
   * @param serviceScopeName the serviceScopeName to set
   */
  public void setServiceScopeName(String serviceScopeName) {
    ServiceScopeName = serviceScopeName;
  }


  /**
   * @return the serviceObjectName
   */
  public String getServiceObjectName() {
    return ServiceObjectName;
  }


  /**
   * @param serviceObjectName the serviceObjectName to set
   */
  public void setServiceObjectName(String serviceObjectName) {
    ServiceObjectName = serviceObjectName;
  }


  /**
   * @return the coverScopeName
   */
  public String getCoverScopeName() {
    return CoverScopeName;
  }


  /**
   * @param coverScopeName the coverScopeName to set
   */
  public void setCoverScopeName(String coverScopeName) {
    CoverScopeName = coverScopeName;
  }


  /**
   * @return the newWorkNatureName
   */
  public String getNewWorkNatureName() {
    return NewWorkNatureName;
  }


  /**
   * @param newWorkNatureName the newWorkNatureName to set
   */
  public void setNewWorkNatureName(String newWorkNatureName) {
    NewWorkNatureName = newWorkNatureName;
  }


  /**
   * @return the systemInterName
   */
  public String getSystemInterName() {
    return SystemInterName;
  }


  /**
   * @param systemInterName the systemInterName to set
   */
  public void setSystemInterName(String systemInterName) {
    SystemInterName = systemInterName;
  }

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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
