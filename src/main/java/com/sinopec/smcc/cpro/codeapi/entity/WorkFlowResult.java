/**
 * @Title JurisdictionData.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午8:55:04
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.Date;

/**
 * @Title JurisdictionData.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午8:55:04
 * @version V1.0
 */
public class WorkFlowResult {
  private String workFlowId;
  private String userId;
  private String systemId;
  private String businessId;
  private String businessName;
  private Integer checkResult;
  private String auditReasons;
  private String nextApprover;
  private Date createTime;
  
  
  /**
   * @return the nextApprover
   */
  public String getNextApprover() {
    return nextApprover;
  }
  /**
   * @param nextApprover the nextApprover to set
   */
  public void setNextApprover(String nextApprover) {
    this.nextApprover = nextApprover;
  }
  /**
   * @return the systemId
   */
  public String getSystemId() {
    return systemId;
  }
  /**
   * @param systemId the systemId to set
   */
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }
  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  /**
   * @return the workFlowId
   */
  public String getWorkFlowId() {
    return workFlowId;
  }
  /**
   * @param workFlowId the workFlowId to set
   */
  public void setWorkFlowId(String workFlowId) {
    this.workFlowId = workFlowId;
  }
  /**
   * @return the auditReasons
   */
  public String getAuditReasons() {
    return auditReasons;
  }
  /**
   * @param auditReasons the auditReasons to set
   */
  public void setAuditReasons(String auditReasons) {
    this.auditReasons = auditReasons;
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
  /**
   * @return the businessName
   */
  public String getBusinessName() {
    return businessName;
  }
  /**
   * @param businessName the businessName to set
   */
  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }
  /**
   * @return the checkResult
   */
  public Integer getCheckResult() {
    return checkResult;
  }
  /**
   * @param checkResult the checkResult to set
   */
  public void setCheckResult(Integer checkResult) {
    this.checkResult = checkResult;
  }
  /**
   * @return the createTime
   */
  public Date getCreateTime() {
    return createTime;
  }
  /**
   * @param createTime the createTime to set
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
