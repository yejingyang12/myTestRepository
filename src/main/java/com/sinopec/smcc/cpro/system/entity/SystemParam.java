/**
* 2018. 
* @Title SystemParam.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午2:40:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemParam.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午2:40:13
 * @version V1.0
 */
public class SystemParam {
	
	private String systemId;
  private String interconnectionSit;
  private String fkFatherSystemId;
  private String standardizedCode;
  private String createUserName;
  private String remark;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date whenInvestmentUse;
  private Integer examineStatus;
  private String executiveOfficeName;
  private Integer examinationStatus;
  private String sysBusDescription;
  private Integer subIsSystem;
  private String executiveDireCon;
  private Integer fkChangeMatter;
  private String systemName;
  private Integer deleteStatus;
  private Integer fkSystemType;
  private Integer appIsInternet;
  private String gradeRecordSysName;
  private Integer fkSystemIsMerge;
  private String changeReason;
	private String sysServiceSitObject;
  private String sysServiceSitScope;
  private String fkCompanyCode;
  private Integer recordStatus;
  private Integer gradingStatus;
  private String changeContent;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Integer fkInfoSysTypeCon;
  private String npNetworkProperties;
  private Integer evaluationStatus;
  private String executiveDireConTel;
  private String npCoverageRange;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String sysBusSituationType;
  
  private String systemIds;
  
  private List<SystemKeyProducts> SystemKeyProducts;
  private List<SystemUseServices> SystemUseServices;
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序
  
  
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
   * @return the examineStatus
   */
  public Integer getExamineStatus() {
    return examineStatus;
  }


  /**
   * @param examineStatus the examineStatus to set
   */
  public void setExamineStatus(Integer examineStatus) {
    this.examineStatus = examineStatus;
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
   * @return the examinationStatus
   */
  public Integer getExaminationStatus() {
    return examinationStatus;
  }


  /**
   * @param examinationStatus the examinationStatus to set
   */
  public void setExaminationStatus(Integer examinationStatus) {
    this.examinationStatus = examinationStatus;
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
   * @return the fkChangeMatter
   */
  public Integer getFkChangeMatter() {
    return fkChangeMatter;
  }


  /**
   * @param fkChangeMatter the fkChangeMatter to set
   */
  public void setFkChangeMatter(Integer fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
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
   * @return the recordStatus
   */
  public Integer getRecordStatus() {
    return recordStatus;
  }


  /**
   * @param recordStatus the recordStatus to set
   */
  public void setRecordStatus(Integer recordStatus) {
    this.recordStatus = recordStatus;
  }


  /**
   * @return the gradingStatus
   */
  public Integer getGradingStatus() {
    return gradingStatus;
  }


  /**
   * @param gradingStatus the gradingStatus to set
   */
  public void setGradingStatus(Integer gradingStatus) {
    this.gradingStatus = gradingStatus;
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
   * @return the evaluationStatus
   */
  public Integer getEvaluationStatus() {
    return evaluationStatus;
  }


  /**
   * @param evaluationStatus the evaluationStatus to set
   */
  public void setEvaluationStatus(Integer evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
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
   * @return the systemIds
   */
  public String getSystemIds() {
    return systemIds;
  }


  /**
   * @param systemIds the systemIds to set
   */
  public void setSystemIds(String systemIds) {
    this.systemIds = systemIds;
  }


  /**
   * @return the systemKeyProducts
   */
  public List<SystemKeyProducts> getSystemKeyProducts() {
    return SystemKeyProducts;
  }


  /**
   * @param systemKeyProducts the systemKeyProducts to set
   */
  public void setSystemKeyProducts(List<SystemKeyProducts> systemKeyProducts) {
    SystemKeyProducts = systemKeyProducts;
  }


  /**
   * @return the systemUseServices
   */
  public List<SystemUseServices> getSystemUseServices() {
    return SystemUseServices;
  }


  /**
   * @param systemUseServices the systemUseServices to set
   */
  public void setSystemUseServices(List<SystemUseServices> systemUseServices) {
    SystemUseServices = systemUseServices;
  }


  /**
   * @return the pageSize
   */
  public int getPageSize() {
    return pageSize;
  }


  /**
   * @param pageSize the pageSize to set
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }


  /**
   * @return the currentPage
   */
  public int getCurrentPage() {
    return currentPage;
  }


  /**
   * @param currentPage the currentPage to set
   */
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }


  /**
   * @return the field
   */
  public String getField() {
    return field;
  }


  /**
   * @param field the field to set
   */
  public void setField(String field) {
    this.field = field;
  }


  /**
   * @return the sort
   */
  public String getSort() {
    return sort;
  }


  /**
   * @param sort the sort to set
   */
  public void setSort(String sort) {
    this.sort = sort;
  }


  public String toString(){
    return JSON.toJSONString(this);
  }
}
