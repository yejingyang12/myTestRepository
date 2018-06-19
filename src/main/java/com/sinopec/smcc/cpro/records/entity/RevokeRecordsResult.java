/**
* Copyright 2018. 
* @Title RevokeRecordsResult.java
* @Package com.sinopec.smcc.cpro.records.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年6月9日上午11:03:11
* @version V1.0
*/
package com.sinopec.smcc.cpro.records.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title RevokeRecordsResult.java
 * @Package com.sinopec.smcc.cpro.records.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月9日上午11:03:11
 * @version V1.0
 */
public class RevokeRecordsResult {
  private String recordsId;
  private String fkSystemId;
  private String fkrevokematter;
  private String revokereason;
  private String revokeRecordsId;
  private String revokeRecordsName;
  
  public String getRecordsId() {
    return recordsId;
  }
  public void setRecordsId(String recordsId) {
    this.recordsId = recordsId;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
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
  
  public String toString() {
    return JSON.toJSONString(this);
  }
}
