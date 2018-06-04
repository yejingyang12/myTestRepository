/**
* 2018. 
* @Title RecordsListResult.java
* @Package com.sinopec.smcc.cpro.records.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月29日下午1:55:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title RecordsListResult.java
 * @Package com.sinopec.smcc.cpro.records.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月29日下午1:55:41
 * @version V1.0
 */
public class RecordsListResult {
  
  private String recordsId;
  private String fkSystemId;
  private String recordCode;
  private String recordCompany;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date recordDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date acceptDate;
  private String acceptCompany;
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
  
  
}
