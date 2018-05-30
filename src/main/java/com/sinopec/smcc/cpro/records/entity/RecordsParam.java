/**
* 2018. 
* @Title RecordsParam.java
* @Package com.sinopec.smcc.cpro.records.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:55:21
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title RecordsParam.java
 * @Package com.sinopec.smcc.cpro.records.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:55:21
 * @version V1.0
 */
public class RecordsParam {
  private String recordsId;
  private String fkSystemId;
  private Integer fkrevokematter;
  private String recordCode;
  private String recordCompany;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date recordDate;
  private String acceptCompany;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date acceptDate;
  private String acceptReason;
  private String revokereason;
  private String revokecontent;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  /**
   * @return the recordsId
   */
  public String getRecordsId() {
    return recordsId;
  }
  /**
   * @param recordsId the recordsId to set
   */
  public void setRecordsId(String recordsId) {
    this.recordsId = recordsId;
  }
  /**
   * @return the fkSystemId
   */
  public String getFkSystemId() {
    return fkSystemId;
  }
  /**
   * @param fkSystemId the fkSystemId to set
   */
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  /**
   * @return the fkrevokematter
   */
  public Integer getFkrevokematter() {
    return fkrevokematter;
  }
  /**
   * @param fkrevokematter the fkrevokematter to set
   */
  public void setFkrevokematter(Integer fkrevokematter) {
    this.fkrevokematter = fkrevokematter;
  }
  /**
   * @return the recordCode
   */
  public String getRecordCode() {
    return recordCode;
  }
  /**
   * @param recordCode the recordCode to set
   */
  public void setRecordCode(String recordCode) {
    this.recordCode = recordCode;
  }
  /**
   * @return the recordCompany
   */
  public String getRecordCompany() {
    return recordCompany;
  }
  /**
   * @param recordCompany the recordCompany to set
   */
  public void setRecordCompany(String recordCompany) {
    this.recordCompany = recordCompany;
  }
  /**
   * @return the recordDate
   */
  public Date getRecordDate() {
    return recordDate;
  }
  /**
   * @param recordDate the recordDate to set
   */
  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }
  /**
   * @return the acceptCompany
   */
  public String getAcceptCompany() {
    return acceptCompany;
  }
  /**
   * @param acceptCompany the acceptCompany to set
   */
  public void setAcceptCompany(String acceptCompany) {
    this.acceptCompany = acceptCompany;
  }
  /**
   * @return the acceptDate
   */
  public Date getAcceptDate() {
    return acceptDate;
  }
  /**
   * @param acceptDate the acceptDate to set
   */
  public void setAcceptDate(Date acceptDate) {
    this.acceptDate = acceptDate;
  }
  /**
   * @return the acceptReason
   */
  public String getAcceptReason() {
    return acceptReason;
  }
  /**
   * @param acceptReason the acceptReason to set
   */
  public void setAcceptReason(String acceptReason) {
    this.acceptReason = acceptReason;
  }
  /**
   * @return the revokereason
   */
  public String getRevokereason() {
    return revokereason;
  }
  /**
   * @param revokereason the revokereason to set
   */
  public void setRevokereason(String revokereason) {
    this.revokereason = revokereason;
  }
  /**
   * @return the revokecontent
   */
  public String getRevokecontent() {
    return revokecontent;
  }
  /**
   * @param revokecontent the revokecontent to set
   */
  public void setRevokecontent(String revokecontent) {
    this.revokecontent = revokecontent;
  }
  /**
   * @return the deleteStatus
   */
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  /**
   * @param deleteStatus the deleteStatus to set
   */
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  /**
   * @return the createUserName
   */
  public String getCreateUserName() {
    return createUserName;
  }
  /**
   * @param createUserName the createUserName to set
   */
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
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
  /**
   * @return the updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }
  /**
   * @param updateTime the updateTime to set
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  /**
   * @return the remark
   */
  public String getRemark() {
    return remark;
  }
  /**
   * @param remark the remark to set
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
