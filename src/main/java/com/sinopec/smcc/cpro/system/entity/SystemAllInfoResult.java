/**
* Copyright 2018. 
* @Title SystemAllInfoResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年8月1日下午8:16:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemAllInfoResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年8月1日下午8:16:15
 * @version V1.0
 */
public class SystemAllInfoResult {
  private String systemId;
  private String fkInfoSysTypeCon;
  private Integer fkSystemIsMerge;
  private Integer fkSystemType;
  private String fkFatherSystemId;
  private String fkCompanyCode;
  private String fkChangeMatter;
  private String companyName;
  private String fatherCompanyName;
  private String systemName;
  private String fatherSystemName;
  private String standardizedCode;
  private String gradeRecordSysName;
  private Integer appIsInternet;
  private String sysBusSituationType;
  private String sysBusDescription;
  private String sysServiceSitScope;
  private String sysServiceSitObject;
  private String npCoverageRange;
  private String npNetworkProperties;
  private String interconnectionSit;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date whenInvestmentUse;
  private String executiveOfficeName;
  private Integer subIsSystem;
  private String executiveDireCon;
  private String executiveDireConTel;
  private Integer gradingStatus;
  private Integer examineStatus;
  private Integer recordStatus;
  private Integer evaluationStatus;
  private Integer examinationStatus;
  private String changeReason;
  private String changeContent;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  
  private List<SystemKeyProducts> systemKeyProductsList;//关键产品
  private List<SystemUseServices> systemUseServicesList;//采用服务
  private List<SystemSubResult> systemSubResultList;//子系统
  
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getFkInfoSysTypeCon() {
    return fkInfoSysTypeCon;
  }
  public void setFkInfoSysTypeCon(String fkInfoSysTypeCon) {
    this.fkInfoSysTypeCon = fkInfoSysTypeCon;
  }
  public Integer getFkSystemIsMerge() {
    return fkSystemIsMerge;
  }
  public void setFkSystemIsMerge(Integer fkSystemIsMerge) {
    this.fkSystemIsMerge = fkSystemIsMerge;
  }
  public Integer getFkSystemType() {
    return fkSystemType;
  }
  public void setFkSystemType(Integer fkSystemType) {
    this.fkSystemType = fkSystemType;
  }
  public String getFkFatherSystemId() {
    return fkFatherSystemId;
  }
  public void setFkFatherSystemId(String fkFatherSystemId) {
    this.fkFatherSystemId = fkFatherSystemId;
  }
  public String getFkCompanyCode() {
    return fkCompanyCode;
  }
  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }
  public String getFkChangeMatter() {
    return fkChangeMatter;
  }
  public void setFkChangeMatter(String fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
  }
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public String getFatherCompanyName() {
    return fatherCompanyName;
  }
  public void setFatherCompanyName(String fatherCompanyName) {
    this.fatherCompanyName = fatherCompanyName;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public String getFatherSystemName() {
    return fatherSystemName;
  }
  public void setFatherSystemName(String fatherSystemName) {
    this.fatherSystemName = fatherSystemName;
  }
  public String getStandardizedCode() {
    return standardizedCode;
  }
  public void setStandardizedCode(String standardizedCode) {
    this.standardizedCode = standardizedCode;
  }
  public String getGradeRecordSysName() {
    return gradeRecordSysName;
  }
  public void setGradeRecordSysName(String gradeRecordSysName) {
    this.gradeRecordSysName = gradeRecordSysName;
  }
  public Integer getAppIsInternet() {
    return appIsInternet;
  }
  public void setAppIsInternet(Integer appIsInternet) {
    this.appIsInternet = appIsInternet;
  }
  public String getSysBusSituationType() {
    return sysBusSituationType;
  }
  public void setSysBusSituationType(String sysBusSituationType) {
    this.sysBusSituationType = sysBusSituationType;
  }
  public String getSysBusDescription() {
    return sysBusDescription;
  }
  public void setSysBusDescription(String sysBusDescription) {
    this.sysBusDescription = sysBusDescription;
  }
  public String getSysServiceSitScope() {
    return sysServiceSitScope;
  }
  public void setSysServiceSitScope(String sysServiceSitScope) {
    this.sysServiceSitScope = sysServiceSitScope;
  }
  public String getSysServiceSitObject() {
    return sysServiceSitObject;
  }
  public void setSysServiceSitObject(String sysServiceSitObject) {
    this.sysServiceSitObject = sysServiceSitObject;
  }
  public String getNpCoverageRange() {
    return npCoverageRange;
  }
  public void setNpCoverageRange(String npCoverageRange) {
    this.npCoverageRange = npCoverageRange;
  }
  public String getNpNetworkProperties() {
    return npNetworkProperties;
  }
  public void setNpNetworkProperties(String npNetworkProperties) {
    this.npNetworkProperties = npNetworkProperties;
  }
  public String getInterconnectionSit() {
    return interconnectionSit;
  }
  public void setInterconnectionSit(String interconnectionSit) {
    this.interconnectionSit = interconnectionSit;
  }
  public Date getWhenInvestmentUse() {
    return whenInvestmentUse;
  }
  public void setWhenInvestmentUse(Date whenInvestmentUse) {
    this.whenInvestmentUse = whenInvestmentUse;
  }
  public String getExecutiveOfficeName() {
    return executiveOfficeName;
  }
  public void setExecutiveOfficeName(String executiveOfficeName) {
    this.executiveOfficeName = executiveOfficeName;
  }
  public Integer getSubIsSystem() {
    return subIsSystem;
  }
  public void setSubIsSystem(Integer subIsSystem) {
    this.subIsSystem = subIsSystem;
  }
  public String getExecutiveDireCon() {
    return executiveDireCon;
  }
  public void setExecutiveDireCon(String executiveDireCon) {
    this.executiveDireCon = executiveDireCon;
  }
  public String getExecutiveDireConTel() {
    return executiveDireConTel;
  }
  public void setExecutiveDireConTel(String executiveDireConTel) {
    this.executiveDireConTel = executiveDireConTel;
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
  public String getChangeReason() {
    return changeReason;
  }
  public void setChangeReason(String changeReason) {
    this.changeReason = changeReason;
  }
  public String getChangeContent() {
    return changeContent;
  }
  public void setChangeContent(String changeContent) {
    this.changeContent = changeContent;
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
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
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
  public List<SystemKeyProducts> getSystemKeyProductsList() {
    return systemKeyProductsList;
  }
  public void setSystemKeyProductsList(List<SystemKeyProducts> systemKeyProductsList) {
    this.systemKeyProductsList = systemKeyProductsList;
  }
  public List<SystemUseServices> getSystemUseServicesList() {
    return systemUseServicesList;
  }
  public void setSystemUseServicesList(List<SystemUseServices> systemUseServicesList) {
    this.systemUseServicesList = systemUseServicesList;
  }
  public List<SystemSubResult> getSystemSubResultList() {
    return systemSubResultList;
  }
  public void setSystemSubResultList(List<SystemSubResult> systemSubResultList) {
    this.systemSubResultList = systemSubResultList;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
