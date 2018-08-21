/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title NodeResult.java
* @Package com.sinopec.smcc.cpro.node.entity
* @Description: TODO:
* @author Aran
* @date 2018年6月7日下午10:33:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.node.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title NodeResult.java
 * @Package com.sinopec.smcc.cpro.node.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月7日下午10:33:32
 * @version V1.0
 */
public class NodeResult {

  private String nodeId;
  private String systemId;
  private String operation;
  private String operator;
  private String operationResult;
  private String  operationOpinion;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  
  private String fkChangeReason;
  private String fkChangeContent;
  private String fkSysChangeMatter;
  
  
  
  public String getFkChangeReason() {
    return fkChangeReason;
  }
  public void setFkChangeReason(String fkChangeReason) {
    this.fkChangeReason = fkChangeReason;
  }
  public String getFkChangeContent() {
    return fkChangeContent;
  }
  public void setFkChangeContent(String fkChangeContent) {
    this.fkChangeContent = fkChangeContent;
  }
  public String getFkSysChangeMatter() {
    return fkSysChangeMatter;
  }
  public void setFkSysChangeMatter(String fkSysChangeMatter) {
    this.fkSysChangeMatter = fkSysChangeMatter;
  }
  public String getNodeId() {
    return nodeId;
  }
  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }
  public String getOperation() {
    return operation;
  }
  public void setOperation(String operation) {
    this.operation = operation;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOperationResult() {
    return operationResult;
  }
  public void setOperationResult(String operationResult) {
    this.operationResult = operationResult;
  }
  public String getOperationOpinion() {
    return operationOpinion;
  }
  public void setOperationOpinion(String operationOpinion) {
    this.operationOpinion = operationOpinion;
  }
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  
}
