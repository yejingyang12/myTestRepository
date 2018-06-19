/**
* Copyright 2018. 
* @Title SystemMaterialsResult.java
* @Package com.sinopec.smcc.cpro.grading.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午2:44:37
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemMaterialsResult.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:44:37
 * @version V1.0
 */
public class SystemMaterialsResult {
  private String systemMaterialsId;
  private String fkSystemId;
  
  //系统拓扑结构及说明
  private String topologyDescriptionId;
  private String topologyDescriptionName;
  //系统安全组织机构及管理制度
  private String organizationManagementId;
  private String organizationManagementName;
  //系统安全保护设施设计;实施方案或改建实施方案
  private String implementationPlanId;
  private String implementationPlanName;
  //系统使用的安全产品清单及认证、销售许可证明
  private String licenseCertificateId;
  private String licenseCertificateName;
  //测评报告
  private String evaluationPresentationId;
  private String evaluationPresentationName;
  //专家评审
  private String expertReviewId;
  private String expertReviewName;
  //上级主管部门审批意见
  private String directorOpinionId;
  private String directorOpinionName;
  
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
  public String getTopologyDescriptionId() {
    return topologyDescriptionId;
  }
  public void setTopologyDescriptionId(String topologyDescriptionId) {
    this.topologyDescriptionId = topologyDescriptionId;
  }
  public String getTopologyDescriptionName() {
    return topologyDescriptionName;
  }
  public void setTopologyDescriptionName(String topologyDescriptionName) {
    this.topologyDescriptionName = topologyDescriptionName;
  }
  public String getOrganizationManagementId() {
    return organizationManagementId;
  }
  public void setOrganizationManagementId(String organizationManagementId) {
    this.organizationManagementId = organizationManagementId;
  }
  public String getOrganizationManagementName() {
    return organizationManagementName;
  }
  public void setOrganizationManagementName(String organizationManagementName) {
    this.organizationManagementName = organizationManagementName;
  }
  public String getImplementationPlanId() {
    return implementationPlanId;
  }
  public void setImplementationPlanId(String implementationPlanId) {
    this.implementationPlanId = implementationPlanId;
  }
  public String getImplementationPlanName() {
    return implementationPlanName;
  }
  public void setImplementationPlanName(String implementationPlanName) {
    this.implementationPlanName = implementationPlanName;
  }
  public String getLicenseCertificateId() {
    return licenseCertificateId;
  }
  public void setLicenseCertificateId(String licenseCertificateId) {
    this.licenseCertificateId = licenseCertificateId;
  }
  public String getLicenseCertificateName() {
    return licenseCertificateName;
  }
  public void setLicenseCertificateName(String licenseCertificateName) {
    this.licenseCertificateName = licenseCertificateName;
  }
  public String getEvaluationPresentationId() {
    return evaluationPresentationId;
  }
  public void setEvaluationPresentationId(String evaluationPresentationId) {
    this.evaluationPresentationId = evaluationPresentationId;
  }
  public String getEvaluationPresentationName() {
    return evaluationPresentationName;
  }
  public void setEvaluationPresentationName(String evaluationPresentationName) {
    this.evaluationPresentationName = evaluationPresentationName;
  }
  public String getExpertReviewId() {
    return expertReviewId;
  }
  public void setExpertReviewId(String expertReviewId) {
    this.expertReviewId = expertReviewId;
  }
  public String getExpertReviewName() {
    return expertReviewName;
  }
  public void setExpertReviewName(String expertReviewName) {
    this.expertReviewName = expertReviewName;
  }
  public String getDirectorOpinionId() {
    return directorOpinionId;
  }
  public void setDirectorOpinionId(String directorOpinionId) {
    this.directorOpinionId = directorOpinionId;
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
