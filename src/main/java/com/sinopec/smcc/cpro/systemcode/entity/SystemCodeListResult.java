/**
 * @Title SystemCode.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:49:52
 * @version V1.0
 */
package com.sinopec.smcc.cpro.systemcode.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemCode.java
 * @Package com.sinopec.smcc.cpro.systemcode.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月3日下午7:49:52
 * @version V1.0
 */
public class SystemCodeListResult {
  
  private String codeId;
  private String codeType;
  private String codeName;
  private String systemCode;
  private String codeOrder;
  private String systemFatherCode;
  private String handleDate;
  private String handleResult;
  private String deleteStatus;
  private String createUserName;
  private String createTime;
  private String updateTime;
  private String remark;
    
  public String getCodeId() {
    return codeId;
  }

  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }

  public String getCodeType() {
    return codeType;
  }

  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }

  public String getCodeName() {
    return codeName;
  }

  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }

  public String getSystemCode() {
    return systemCode;
  }

  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }

  public String getCodeOrder() {
    return codeOrder;
  }

  public void setCodeOrder(String codeOrder) {
    this.codeOrder = codeOrder;
  }

  public String getSystemFatherCode() {
    return systemFatherCode;
  }

  public void setSystemFatherCode(String systemFatherCode) {
    this.systemFatherCode = systemFatherCode;
  }

  public String getHandleDate() {
    return handleDate;
  }

  public void setHandleDate(String handleDate) {
    this.handleDate = handleDate;
  }

  public String getHandleResult() {
    return handleResult;
  }

  public void setHandleResult(String handleResult) {
    this.handleResult = handleResult;
  }

  public String getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(String deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public String getCreateUserName() {
    return createUserName;
  }

  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String toString(){
    return JSON.toJSONString(this);
  }
}
