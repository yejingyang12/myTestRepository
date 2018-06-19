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
import java.util.List;

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
  private String constructionTypeName;
  private String combinedName;
  private String systemName;
  private Integer appIsInternet;
  private String standardizedCode;
  private String gradeRecordSysName;
  private String sysBusSituationType;
  private String sysBusDescription;
  private String sysServiceSitScope;
  private String sysServiceSitObject;
  private String npCoverageRange;
  private String npNetworkProperties;
  private String interconnectionSit;
  private String companyName;
  private String executiveOfficeName;
  private String executiveDireCon;
  private String executiveDireConTel;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date whenInvestmentUse;
  private Integer subIsSystem;
  private String fkFatherSystemId;
  private Integer fkSystemType;
  
  private List<SystemKeyResult> systemKeyProducts;
  private List<SystemUseResult> systemUseServices;
  private List<SystemSubResult> addSystemSub;
  
  
  private String fkResponsibleType; 
  private String fkProductsType;
  private Integer serviceIsUse;
  private String fkExaminStatus;
  private String productsNumber;
  private Integer nUseProbability;
  private String fkNationalIsProducts;
  

  private String fkCompanyCode;
  private Integer fkInfoSysTypeCon;
  private Integer fkSystemIsMerge;

  
  /**
   * @return the fkFatherSystemId
   */
  public String getFkFatherSystemId() {
    return fkFatherSystemId;
  }


  /**
   * @param fkFatherSystemId the fkFatherSystemId to set
   */
  public void setFkFatherSystemId(String fkFatherSystemId) {
    this.fkFatherSystemId = fkFatherSystemId;
  }


  /**
   * @return the fkSystemType
   */
  public Integer getFkSystemType() {
    return fkSystemType;
  }


  /**
   * @param fkSystemType the fkSystemType to set
   */
  public void setFkSystemType(Integer fkSystemType) {
    this.fkSystemType = fkSystemType;
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
   * @return the addSystemSub
   */
  public List<SystemSubResult> getAddSystemSub() {
    return addSystemSub;
  }


  /**
   * @param addSystemSub the addSystemSub to set
   */
  public void setAddSystemSub(List<SystemSubResult> addSystemSub) {
    this.addSystemSub = addSystemSub;
  }



  /**
   * @return the systemKeyProducts
   */
  public List<SystemKeyResult> getSystemKeyProducts() {
    return systemKeyProducts;
  }


  /**
   * @param systemKeyProducts the systemKeyProducts to set
   */
  public void setSystemKeyProducts(List<SystemKeyResult> systemKeyProducts) {
    this.systemKeyProducts = systemKeyProducts;
  }




  /**
   * @return the systemUseServices
   */
  public List<SystemUseResult> getSystemUseServices() {
    return systemUseServices;
  }


  /**
   * @param systemUseServices the systemUseServices to set
   */
  public void setSystemUseServices(List<SystemUseResult> systemUseServices) {
    this.systemUseServices = systemUseServices;
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
   * @return the companyName
   */
  public String getCompanyName() {
    return companyName;
  }


  /**
   * @param companyName the companyName to set
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }




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
    return constructionTypeName;
  }




  /**
   * @return the appIsInternet
   */
  public Integer getAppIsInternet() {
    return appIsInternet;
  }


  /**
   * @param appIsInternet the appIsInternet to set
   */
  public void setAppIsInternet(Integer appIsInternet) {
    this.appIsInternet = appIsInternet;
  }


  /**
   * @param constructionTypeName the constructionTypeName to set
   */
  public void setConstructionTypeName(String constructionTypeName) {
    this.constructionTypeName = constructionTypeName;
  }




  /**
   * @return the combinedName
   */
  public String getCombinedName() {
    return combinedName;
  }


  /**
   * @param combinedName the combinedName to set
   */
  public void setCombinedName(String combinedName) {
    this.combinedName = combinedName;
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
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
  public String getFkProductsType() {
    return fkProductsType;
  }


  /**
   * @param fkProductsType the fkProductsType to set
   */
  public void setFkProductsType(String fkProductsType) {
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
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }


  /**
   * @param fkExaminStatus the fkExaminStatus to set
   */
  public void setFkExaminStatus(String fkExaminStatus) {
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
