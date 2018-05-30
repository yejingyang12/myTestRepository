/**
* 2018. 
* @Title CheckListResult.java
* @Package com.sinopec.smcc.cpro.review.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月25日下午1:40:51
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title CheckListResult.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月25日下午1:40:51
 * @version V1.0
 */
public class CheckListResult {
  
  private String checkId;
  private String instanceName;
  private String fkbusinessNode;
  private String initiator;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  private String fkSystemId;
  private String fkExaminStatus;
  private String prevExecutor;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Integer deleteStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String expertReviewId;
  private String gradingReportId;
  private String expertReviewName;
  private String gradingReportName;
  private Integer fkChangeMatter;
  private String changeReason;
  private String changeContent;
  private String revokeReportId;
  private String revokeReportName;
  private String revokeReason;
  private Integer fkrevokeMatter;
  private String revokeContent;

  public String getCheckId() {
    return checkId;
  }
  public void setCheckId(String checkId) {
    this.checkId = checkId;
  }
  public String getInstanceName() {
    return instanceName;
  }
  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }
  public String getFkbusinessNode() {
    return fkbusinessNode;
  }
  public void setFkbusinessNode(String fkbusinessNode) {
    this.fkbusinessNode = fkbusinessNode;
  }
  public String getInitiator() {
    return initiator;
  }
  public void setInitiator(String initiator) {
    this.initiator = initiator;
  }
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
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
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }
  public void setFkExaminStatus(String fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }
  public String getPrevExecutor() {
    return prevExecutor;
  }
  public void setPrevExecutor(String prevExecutor) {
    this.prevExecutor = prevExecutor;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public Date getExecuteTime() {
    return executeTime;
  }
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }
  public String getExpertReviewId() {
    return expertReviewId;
  }
  public void setExpertReviewId(String expertReviewId) {
    this.expertReviewId = expertReviewId;
  }
  public String getGradingReportId() {
    return gradingReportId;
  }
  public void setGradingReportId(String gradingReportId) {
    this.gradingReportId = gradingReportId;
  }
  public String getExpertReviewName() {
    return expertReviewName;
  }
  public void setExpertReviewName(String expertReviewName) {
    this.expertReviewName = expertReviewName;
  }
  public String getGradingReportName() {
    return gradingReportName;
  }
  public void setGradingReportName(String gradingReportName) {
    this.gradingReportName = gradingReportName;
  }
  public Integer getFkChangeMatter() {
    return fkChangeMatter;
  }
  public void setFkChangeMatter(Integer fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
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
  public String getRevokeReportId() {
    return revokeReportId;
  }
  public void setRevokeReportId(String revokeReportId) {
    this.revokeReportId = revokeReportId;
  }
  public String getRevokeReportName() {
    return revokeReportName;
  }
  public void setRevokeReportName(String revokeReportName) {
    this.revokeReportName = revokeReportName;
  }
  public String getRevokeReason() {
    return revokeReason;
  }
  public void setRevokeReason(String revokeReason) {
    this.revokeReason = revokeReason;
  }
  public Integer getFkrevokeMatter() {
    return fkrevokeMatter;
  }
  public void setFkrevokeMatter(Integer fkrevokeMatter) {
    this.fkrevokeMatter = fkrevokeMatter;
  }
  public String getRevokeContent() {
    return revokeContent;
  }
  public void setRevokeContent(String revokeContent) {
    this.revokeContent = revokeContent;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }

}
