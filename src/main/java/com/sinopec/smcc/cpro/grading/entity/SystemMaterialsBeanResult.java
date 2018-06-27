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

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sinopec.smcc.cpro.file.entity.AttachResult;

/**
 * @Title SystemMaterialsResult.java
 * @Package com.sinopec.smcc.cpro.grading.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:44:37
 * @version V1.0
 */
public class SystemMaterialsBeanResult {
  private String systemMaterialsId;
  private String fkSystemId;
  
  //系统拓扑结构及说明
  private List<AttachResult> topologyDescriptionList;
  //系统安全组织机构及管理制度
  private List<AttachResult> organizationManagementList;
  //系统安全保护设施设计;实施方案或改建实施方案
  private List<AttachResult> implementationPlanList;
  //系统使用的安全产品清单及认证、销售许可证明
  private List<AttachResult> licenseCertificateList;
  //测评报告
  private String evaluationPresentationId;
  private String evaluationPresentationName;
  private String evaluationPresentationPath;
  //专家评审
  private String expertReviewId;
  private String expertReviewName;
  private String expertReviewPath;
  //上级主管部门审批意见
  private String directorOpinionId;
  private String directorOpinionName;
  private String directorOpinionPath;
  private String changeType;
  //附件名称，用","隔开，不放数据
  private String topologyDescriptionName;
  private String organizationManagementName;
  private String implementationPlanName;
  private String licenseCertificateName;
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
  public List<AttachResult> getTopologyDescriptionList() {
    return topologyDescriptionList;
  }
  public void setTopologyDescriptionList(List<AttachResult> topologyDescriptionList) {
    this.topologyDescriptionList = topologyDescriptionList;
  }
  public List<AttachResult> getOrganizationManagementList() {
    return organizationManagementList;
  }
  public void setOrganizationManagementList(List<AttachResult> organizationManagementList) {
    this.organizationManagementList = organizationManagementList;
  }
  public List<AttachResult> getImplementationPlanList() {
    return implementationPlanList;
  }
  public void setImplementationPlanList(List<AttachResult> implementationPlanList) {
    this.implementationPlanList = implementationPlanList;
  }
  public List<AttachResult> getLicenseCertificateList() {
    return licenseCertificateList;
  }
  public void setLicenseCertificateList(List<AttachResult> licenseCertificateList) {
    this.licenseCertificateList = licenseCertificateList;
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
  public String getEvaluationPresentationPath() {
    return evaluationPresentationPath;
  }
  public void setEvaluationPresentationPath(String evaluationPresentationPath) {
    this.evaluationPresentationPath = evaluationPresentationPath;
  }
  public String getExpertReviewPath() {
    return expertReviewPath;
  }
  public void setExpertReviewPath(String expertReviewPath) {
    this.expertReviewPath = expertReviewPath;
  }
  public String getDirectorOpinionPath() {
    return directorOpinionPath;
  }
  public void setDirectorOpinionPath(String directorOpinionPath) {
    this.directorOpinionPath = directorOpinionPath;
  }
  public String getChangeType() {
    return changeType;
  }
  public void setChangeType(String changeType) {
    this.changeType = changeType;
  }
  public String getTopologyDescriptionName() {
    return topologyDescriptionName;
  }
  public void setTopologyDescriptionName(String topologyDescriptionName) {
    this.topologyDescriptionName = topologyDescriptionName;
  }
  public String getOrganizationManagementName() {
    return organizationManagementName;
  }
  public void setOrganizationManagementName(String organizationManagementName) {
    this.organizationManagementName = organizationManagementName;
  }
  public String getImplementationPlanName() {
    return implementationPlanName;
  }
  public void setImplementationPlanName(String implementationPlanName) {
    this.implementationPlanName = implementationPlanName;
  }
  public String getLicenseCertificateName() {
    return licenseCertificateName;
  }
  public void setLicenseCertificateName(String licenseCertificateName) {
    this.licenseCertificateName = licenseCertificateName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
