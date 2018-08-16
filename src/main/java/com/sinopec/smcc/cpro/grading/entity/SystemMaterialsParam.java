/**
* Copyright 2018. 
* @Title SystemMaterialsParam.java
* @Package com.sinopec.smcc.cpro.grading.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午2:33:06
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemMaterialsParam.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:33:06
 * @version V1.0
 */
public class SystemMaterialsParam {
  private String systemMaterialsId;
  private String fkSystemId;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private String updateUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  
  //系统拓扑结构及说明
  private String topologyDescriptionPath;
  private String topologyDescriptionName;
  //系统安全组织机构及管理制度
  private String organizationManagementPath;
  private String organizationManagementName;
  //系统安全保护设施设计;实施方案或改建实施方案
  private String implementationPlanPath;
  private String implementationPlanName;
  //系统使用的安全产品清单及认证、销售许可证明
  private String licenseCertificatePath;
  private String licenseCertificateName;
  //测评报告
  private String evaluationPresentationPath;
  private String evaluationPresentationName;
  //专家评审
  private String expertReviewPath;
  private String expertReviewName;
  //上级主管部门审批意见
  private String directorOpinionPath;
  private String directorOpinionName;
  private String changeType;
  public String getChangeType() {
    return changeType;
  }
  public void setChangeType(String changeType) {
    this.changeType = changeType;
  }
  public String getSystemMaterialsId() {
    return systemMaterialsId;
  }
  public void setSystemMaterialsId(String systemMaterialsId) {
    this.systemMaterialsId = systemMaterialsId;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
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
  public String getUpdateUserName() {
    return updateUserName;
  }
  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
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
  public String getTopologyDescriptionPath() {
    return topologyDescriptionPath;
  }
  public void setTopologyDescriptionPath(String topologyDescriptionPath) {
    this.topologyDescriptionPath = topologyDescriptionPath;
  }
  public String getTopologyDescriptionName() {
    return topologyDescriptionName;
  }
  public void setTopologyDescriptionName(String topologyDescriptionName) {
    this.topologyDescriptionName = topologyDescriptionName;
  }
  public String getOrganizationManagementPath() {
    return organizationManagementPath;
  }
  public void setOrganizationManagementPath(String organizationManagementPath) {
    this.organizationManagementPath = organizationManagementPath;
  }
  public String getOrganizationManagementName() {
    return organizationManagementName;
  }
  public void setOrganizationManagementName(String organizationManagementName) {
    this.organizationManagementName = organizationManagementName;
  }
  public String getImplementationPlanPath() {
    return implementationPlanPath;
  }
  public void setImplementationPlanPath(String implementationPlanPath) {
    this.implementationPlanPath = implementationPlanPath;
  }
  public String getImplementationPlanName() {
    return implementationPlanName;
  }
  public void setImplementationPlanName(String implementationPlanName) {
    this.implementationPlanName = implementationPlanName;
  }
  public String getLicenseCertificatePath() {
    return licenseCertificatePath;
  }
  public void setLicenseCertificatePath(String licenseCertificatePath) {
    this.licenseCertificatePath = licenseCertificatePath;
  }
  public String getLicenseCertificateName() {
    return licenseCertificateName;
  }
  public void setLicenseCertificateName(String licenseCertificateName) {
    this.licenseCertificateName = licenseCertificateName;
  }
  public String getEvaluationPresentationPath() {
    return evaluationPresentationPath;
  }
  public void setEvaluationPresentationPath(String evaluationPresentationPath) {
    this.evaluationPresentationPath = evaluationPresentationPath;
  }
  public String getEvaluationPresentationName() {
    return evaluationPresentationName;
  }
  public void setEvaluationPresentationName(String evaluationPresentationName) {
    this.evaluationPresentationName = evaluationPresentationName;
  }
  public String getExpertReviewPath() {
    return expertReviewPath;
  }
  public void setExpertReviewPath(String expertReviewPath) {
    this.expertReviewPath = expertReviewPath;
  }
  public String getExpertReviewName() {
    return expertReviewName;
  }
  public void setExpertReviewName(String expertReviewName) {
    this.expertReviewName = expertReviewName;
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
