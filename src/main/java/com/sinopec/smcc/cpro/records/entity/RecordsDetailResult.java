/**
* 2018. 
* @Title RecordsListResult.java
* @Package com.sinopec.smcc.cpro.records.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月31日下午9:55:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title RecordsListResult.java
 * @Package com.sinopec.smcc.cpro.records.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月31日下午9:55:41
 * @version V1.0
 */
public class RecordsDetailResult {
  
  private String recordsId;
  private String fkSystemId;
  private String recordCode;
  private String recordCompany;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date acceptDate;
  private String acceptCompany;
  
  private String recordReportId;
  private String recordReportName;
  
  private String fkrevokematter;
  private String revokereason;
  private String revokeRecordsId;
  private String revokeRecordsName;
  public String getFkrevokematter() {
    return fkrevokematter;
  }
  public void setFkrevokematter(String fkrevokematter) {
    this.fkrevokematter = fkrevokematter;
  }
  public String getRevokereason() {
    return revokereason;
  }
  public void setRevokereason(String revokereason) {
    this.revokereason = revokereason;
  }
  public String getRevokeRecordsId() {
    return revokeRecordsId;
  }
  public void setRevokeRecordsId(String revokeRecordsId) {
    this.revokeRecordsId = revokeRecordsId;
  }
  public String getRevokeRecordsName() {
    return revokeRecordsName;
  }
  public void setRevokeRecordsName(String revokeRecordsName) {
    this.revokeRecordsName = revokeRecordsName;
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
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
   * @return the acceptDate
   */
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
  
  public String toString() {
    return JSON.toJSONString(this);
  }
}
