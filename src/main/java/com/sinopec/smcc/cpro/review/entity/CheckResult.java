/**
* Copyright 2018. 
* @Title CheckResult.java
* @Package com.sinopec.smcc.cpro.review.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月8日下午12:06:13
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title CheckResult.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月8日下午12:06:13
 * @version V1.0
 */
public class CheckResult {
  private String checkId;
  private String fkSystemId;
  private String fkExaminStatus;
  private String fkBusinessNode;
  private String instanceName;
  private String initiator;
  private String prevExecutor;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date executeTime;
  private Integer scoreCheckResult;
  private String scoreCheckReason;
  private Integer scoreCheckChangeResult;
  private String scoreCheckChangeReason;
  private Integer cancelRecordsResult;
  private String cancelRecordsReason;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  private String fkInfoSysTypeCon;
  
  private String expertReviewId;
  private String expertReviewName;
  private String recordReportId;
  private String recordReportName;
  
  public String getCheckId() {
    return checkId;
  }
  public void setCheckId(String checkId) {
    this.checkId = checkId;
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
  public String getFkBusinessNode() {
    return fkBusinessNode;
  }
  public void setFkBusinessNode(String fkBusinessNode) {
    this.fkBusinessNode = fkBusinessNode;
  }
  public String getInstanceName() {
    return instanceName;
  }
  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }
  public String getInitiator() {
    return initiator;
  }
  public void setInitiator(String initiator) {
    this.initiator = initiator;
  }
  public String getPrevExecutor() {
    return prevExecutor;
  }
  public void setPrevExecutor(String prevExecutor) {
    this.prevExecutor = prevExecutor;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getExecuteTime() {
    return executeTime;
  }
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }
  public Integer getScoreCheckResult() {
    return scoreCheckResult;
  }
  public void setScoreCheckResult(Integer scoreCheckResult) {
    this.scoreCheckResult = scoreCheckResult;
  }
  public String getScoreCheckReason() {
    return scoreCheckReason;
  }
  public void setScoreCheckReason(String scoreCheckReason) {
    this.scoreCheckReason = scoreCheckReason;
  }
  public Integer getScoreCheckChangeResult() {
    return scoreCheckChangeResult;
  }
  public void setScoreCheckChangeResult(Integer scoreCheckChangeResult) {
    this.scoreCheckChangeResult = scoreCheckChangeResult;
  }
  public String getScoreCheckChangeReason() {
    return scoreCheckChangeReason;
  }
  public void setScoreCheckChangeReason(String scoreCheckChangeReason) {
    this.scoreCheckChangeReason = scoreCheckChangeReason;
  }
  public Integer getCancelRecordsResult() {
    return cancelRecordsResult;
  }
  public void setCancelRecordsResult(Integer cancelRecordsResult) {
    this.cancelRecordsResult = cancelRecordsResult;
  }
  public String getCancelRecordsReason() {
    return cancelRecordsReason;
  }
  public void setCancelRecordsReason(String cancelRecordsReason) {
    this.cancelRecordsReason = cancelRecordsReason;
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
  public String getFkInfoSysTypeCon() {
    return fkInfoSysTypeCon;
  }
  public void setFkInfoSysTypeCon(String fkInfoSysTypeCon) {
    this.fkInfoSysTypeCon = fkInfoSysTypeCon;
  }
  public String getExpertReviewId() {
    return expertReviewId;
  }
  public void setExpertReviewId(String expertReviewId) {
    this.expertReviewId = expertReviewId;
  }
  public String getRecordReportId() {
    return recordReportId;
  }
  public void setRecordReportId(String recordReportId) {
    this.recordReportId = recordReportId;
  }
  public String getExpertReviewName() {
    return expertReviewName;
  }
  public void setExpertReviewName(String expertReviewName) {
    this.expertReviewName = expertReviewName;
  }
  public String getRecordReportName() {
    return recordReportName;
  }
  public void setRecordReportName(String recordReportName) {
    this.recordReportName = recordReportName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }

}
