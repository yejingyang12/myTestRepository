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

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
public class SystemMaterialsBeanParam {
  private String systemMaterialsId;
  private String fkSystemId;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  
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
  private String saveType;
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
  public String getSaveType() {
    return saveType;
  }
  public void setSaveType(String saveType) {
    this.saveType = saveType;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
