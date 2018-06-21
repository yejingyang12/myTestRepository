
/**
 * 2018. 
 * @Title Check.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月25日下午12:38:21
 * @version V1.0
 */

package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Title Check.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月25日下午12:38:21
 * @version V1.0
 */
public class CheckParam {
  private String checkId;
  private String fkSystemId;
  private String fkExaminStatus;
  private String fkBusinessNode;
  private String instanceName;
  private String initiator;
  private String prevExecutor;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTimeEnd;
  private Integer scoreCheckResult;
  private Integer scoreCheckResultBegin;
  private Integer scoreCheckResultEnd;
  private String scoreCheckReason;
  private Integer scoreCheckChangeResult;
  private Integer scoreCheckChangeResultBegin;
  private Integer scoreCheckChangeResultEnd;
  private String scoreCheckChangeReason;
  private Integer cancelRecordsResult;
  private Integer cancelRecordsResultBegin;
  private Integer cancelRecordsResultEnd;
  private String cancelRecordsReason;
  private Integer deleteStatus;
  private Integer deleteStatusBegin;
  private Integer deleteStatusEnd;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTimeEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTimeEnd;
  private String remark;
  
  private String attachName;
  
  //办理状态
  private Integer handlingState;
  private String role;
  
  private int pageSize;
  private int currentPage;
  private String field;
  private String sort;
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getExecuteTime() {
    return executeTime;
  }
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getExecuteTimeBegin() {
    return executeTimeBegin;
  }
  public void setExecuteTimeBegin(Date executeTimeBegin) {
    this.executeTimeBegin = executeTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getExecuteTimeEnd() {
    return executeTimeEnd;
  }
  public void setExecuteTimeEnd(Date executeTimeEnd) {
    this.executeTimeEnd = executeTimeEnd;
  }
  public Integer getScoreCheckResult() {
    return scoreCheckResult;
  }
  public void setScoreCheckResult(Integer scoreCheckResult) {
    this.scoreCheckResult = scoreCheckResult;
  }
  public Integer getScoreCheckResultBegin() {
    return scoreCheckResultBegin;
  }
  public void setScoreCheckResultBegin(Integer scoreCheckResultBegin) {
    this.scoreCheckResultBegin = scoreCheckResultBegin;
  }
  public Integer getScoreCheckResultEnd() {
    return scoreCheckResultEnd;
  }
  public void setScoreCheckResultEnd(Integer scoreCheckResultEnd) {
    this.scoreCheckResultEnd = scoreCheckResultEnd;
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
  public Integer getScoreCheckChangeResultBegin() {
    return scoreCheckChangeResultBegin;
  }
  public void setScoreCheckChangeResultBegin(Integer scoreCheckChangeResultBegin) {
    this.scoreCheckChangeResultBegin = scoreCheckChangeResultBegin;
  }
  public Integer getScoreCheckChangeResultEnd() {
    return scoreCheckChangeResultEnd;
  }
  public void setScoreCheckChangeResultEnd(Integer scoreCheckChangeResultEnd) {
    this.scoreCheckChangeResultEnd = scoreCheckChangeResultEnd;
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
  public Integer getCancelRecordsResultBegin() {
    return cancelRecordsResultBegin;
  }
  public void setCancelRecordsResultBegin(Integer cancelRecordsResultBegin) {
    this.cancelRecordsResultBegin = cancelRecordsResultBegin;
  }
  public Integer getCancelRecordsResultEnd() {
    return cancelRecordsResultEnd;
  }
  public void setCancelRecordsResultEnd(Integer cancelRecordsResultEnd) {
    this.cancelRecordsResultEnd = cancelRecordsResultEnd;
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
  public Integer getDeleteStatusBegin() {
    return deleteStatusBegin;
  }
  public void setDeleteStatusBegin(Integer deleteStatusBegin) {
    this.deleteStatusBegin = deleteStatusBegin;
  }
  public Integer getDeleteStatusEnd() {
    return deleteStatusEnd;
  }
  public void setDeleteStatusEnd(Integer deleteStatusEnd) {
    this.deleteStatusEnd = deleteStatusEnd;
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
  public Date getCreateTimeBegin() {
    return createTimeBegin;
  }
  public void setCreateTimeBegin(Date createTimeBegin) {
    this.createTimeBegin = createTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTimeEnd() {
    return createTimeEnd;
  }
  public void setCreateTimeEnd(Date createTimeEnd) {
    this.createTimeEnd = createTimeEnd;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getUpdateTimeBegin() {
    return updateTimeBegin;
  }
  public void setUpdateTimeBegin(Date updateTimeBegin) {
    this.updateTimeBegin = updateTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getUpdateTimeEnd() {
    return updateTimeEnd;
  }
  public void setUpdateTimeEnd(Date updateTimeEnd) {
    this.updateTimeEnd = updateTimeEnd;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  /**
   * @Descrption  获取办理状态：
   *          1.待办；
   *          2.已办；
   *          其他：全部
   * @author yejingyang
   * @date 2018年6月13日下午5:52:28
   * @return
   */
  public Integer getHandlingState() {
    return handlingState;
  }
  /**
   * @Descrption设置办理状态
   * @author yejingyang
   * @date 2018年6月13日下午5:52:02
   * @param handlingState
   */
  public void setHandlingState(Integer handlingState) {
    this.handlingState = handlingState;
  }
  
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getCurrentPage() {
    return currentPage;
  }
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  public String getField() {
    return field;
  }
  public void setField(String field) {
    this.field = field;
  }
  public String getSort() {
    return sort;
  }
  public void setSort(String sort) {
    this.sort = sort;
  }
  public String getAttachName() {
    return attachName;
  }
  public void setAttachName(String attachName) {
    this.attachName = attachName;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
}
