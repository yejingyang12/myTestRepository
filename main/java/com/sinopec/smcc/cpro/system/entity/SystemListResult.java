/**
* 2018. 
* @Title SystemListResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午2:42:46
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemListResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午2:42:46
 * @version V1.0
 */
public class SystemListResult {
	
	private String systemId;
	private String systemName;
	private String interconnectionSituation;
	private String standardizedCode;
	private Date whenInvestmentUse;
	private String executiveOfficeName;
	private String sysBusinessDescription;
	private Integer subIsSystem;
	private String executiveDirectorContacts;
	private Integer fkInfoSysTypeConstruction;
	private String gradeRecordSysName;
	private String sysBusinessDescriptionType;
	private String sysServiceSituationScope;
	private String sysServiceSituationObject;
	private String networkPlatformCoverageRange;
	private String networkPlatformNetworkProperties;
	private String sysBusinessSituationType;
	private String fkResponsibleType; 
	private Integer fkProductsType;
	private Integer serviceIsUse;
	private Integer fkExaminStatus;
	private String productsNumber;
	private int nationalUseProbability;
	private String fknationalIsProducts;
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
	 * @return the sysBusinessSituationType
	 */
	public String getSysBusinessSituationType() {
		return sysBusinessSituationType;
	}
	/**
	 * @param sysBusinessSituationType the sysBusinessSituationType to set
	 */
	public void setSysBusinessSituationType(String sysBusinessSituationType) {
		this.sysBusinessSituationType = sysBusinessSituationType;
	}
	/**
	 * @return the fkInfoSysTypeConstruction
	 */
	public Integer getFkInfoSysTypeConstruction() {
		return fkInfoSysTypeConstruction;
	}
	/**
	 * @param fkInfoSysTypeConstruction the fkInfoSysTypeConstruction to set
	 */
	public void setFkInfoSysTypeConstruction(Integer fkInfoSysTypeConstruction) {
		this.fkInfoSysTypeConstruction = fkInfoSysTypeConstruction;
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
	 * @return the sysServiceSituationScope
	 */
	public String getSysServiceSituationScope() {
		return sysServiceSituationScope;
	}
	/**
	 * @param sysServiceSituationScope the sysServiceSituationScope to set
	 */
	public void setSysServiceSituationScope(String sysServiceSituationScope) {
		this.sysServiceSituationScope = sysServiceSituationScope;
	}
	/**
	 * @return the sysServiceSituationObject
	 */
	public String getSysServiceSituationObject() {
		return sysServiceSituationObject;
	}
	/**
	 * @param sysServiceSituationObject the sysServiceSituationObject to set
	 */
	public void setSysServiceSituationObject(String sysServiceSituationObject) {
		this.sysServiceSituationObject = sysServiceSituationObject;
	}
	/**
	 * @return the networkPlatformCoverageRange
	 */
	public String getNetworkPlatformCoverageRange() {
		return networkPlatformCoverageRange;
	}
	/**
	 * @param networkPlatformCoverageRange the networkPlatformCoverageRange to set
	 */
	public void setNetworkPlatformCoverageRange(String networkPlatformCoverageRange) {
		this.networkPlatformCoverageRange = networkPlatformCoverageRange;
	}
	/**
	 * @return the networkPlatformNetworkProperties
	 */
	public String getNetworkPlatformNetworkProperties() {
		return networkPlatformNetworkProperties;
	}
	/**
	 * @param networkPlatformNetworkProperties the networkPlatformNetworkProperties to set
	 */
	public void setNetworkPlatformNetworkProperties(
			String networkPlatformNetworkProperties) {
		this.networkPlatformNetworkProperties = networkPlatformNetworkProperties;
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
	 * @return the executiveDirectorContactsTel
	 */
	public String getExecutiveDirectorContactsTel() {
		return executiveDirectorContactsTel;
	}
	/**
	 * @param executiveDirectorContactsTel the executiveDirectorContactsTel to set
	 */
	public void setExecutiveDirectorContactsTel(String executiveDirectorContactsTel) {
		this.executiveDirectorContactsTel = executiveDirectorContactsTel;
	}
	private String fkCompanyCode;
	private String executiveDirectorContactsTel;
	/**
	 * @return the executiveDirectorContacts
	 */
	public String getExecutiveDirectorContacts() {
		return executiveDirectorContacts;
	}
	/**
	 * @param executiveDirectorContacts the executiveDirectorContacts to set
	 */
	public void setExecutiveDirectorContacts(String executiveDirectorContacts) {
		this.executiveDirectorContacts = executiveDirectorContacts;
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
	 * @return the sysBusinessDescription
	 */
	public String getSysBusinessDescription() {
		return sysBusinessDescription;
	}
	/**
	 * @param sysBusinessDescription the sysBusinessDescription to set
	 */
	public void setSysBusinessDescription(String sysBusinessDescription) {
		this.sysBusinessDescription = sysBusinessDescription;
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
	 * @return the interconnectionSituation
	 */
	public String getInterconnectionSituation() {
		return interconnectionSituation;
	}
	/**
	 * @param interconnectionSituation the interconnectionSituation to set
	 */
	public void setInterconnectionSituation(String interconnectionSituation) {
		this.interconnectionSituation = interconnectionSituation;
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

	public String toString(){
    return JSON.toJSONString(this);
  }
}
