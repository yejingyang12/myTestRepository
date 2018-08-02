/**
* Copyright 2018. 
* @Title CheckListResult.java
* @Package com.sinopec.smcc.cpro.review.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月9日下午1:07:06
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title CheckListResult.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月9日下午1:07:06
 * @version V1.0
 */
public class CheckListResult {
  private String checkId;
  private String fkSystemId;
  private String instanceName;
  private String initiator;
  private String prevExecutor;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date executeTime;
  private String fkBusinessNode;
  private String fkExaminStatus;
  private String expertReviewId;
  private String expertReviewName;
  private String recordReportId;
  private String recordReportName;
  private String fkInfoSysTypeCon;
  private String companyId;
  private String taskId;
  private String businessId;
  
  
  /**
   * @return the taskId
   */
  public String getTaskId() {
    return taskId;
  }
  /**
   * @param taskId the taskId to set
   */
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
  /**
   * @return the businessId
   */
  public String getBusinessId() {
    return businessId;
  }
  /**
   * @param businessId the businessId to set
   */
  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }
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
  public String getFkBusinessNode() {
    return fkBusinessNode;
  }
  public void setFkBusinessNode(String fkBusinessNode) {
    this.fkBusinessNode = fkBusinessNode;
  }
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }
  public void setFkExaminStatus(String fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
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
  public String getRecordReportId() {
    return recordReportId;
  }
  public void setRecordReportId(String recordReportId) {
    this.recordReportId = recordReportId;
  }
  public String getRecordReportName() {
    return recordReportName;
  }
  public void setRecordReportName(String recordReportName) {
    this.recordReportName = recordReportName;
  }
  public String getFkInfoSysTypeCon() {
    return fkInfoSysTypeCon;
  }
  public void setFkInfoSysTypeCon(String fkInfoSysTypeCon) {
    this.fkInfoSysTypeCon = fkInfoSysTypeCon;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
  public String getCompanyId() {
    return companyId;
  }
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }
}
