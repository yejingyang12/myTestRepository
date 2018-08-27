/**
* Copyright 2018. 
* @Title SystemRelationParam.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author yejingyang
* @date 2018年8月6日下午8:53:07
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemRelationParam.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年8月6日下午8:53:07
 * @version V1.0
 */
public class SystemRelationParam {
  
  private String systemRelationId;
  private String fkSystemId;
  private String fkCompanyCode;
  private String systemName;//系统名称
  private String systemSmccCode;//smcc编码
  private String standardizedName;//标准化名称
  private String standardizedCode;//标准化编码
  private String systemIsMerge;
  private String systemSource;
  private Integer deleteStatus;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private String updateUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  private String userId;
  
  //当页数量
  private int pageSize = 10;
  //当前页数
  private int currentPage = 1;
  //排序字段
  private String field;
  //排序方式
  private String sort;
  
  private List<String> companyList;
  private List<String> plateList;
  
  private SystemRelationParam systemRelationParam;
  
  public String getSystemRelationId() {
    return systemRelationId;
  }
  public void setSystemRelationId(String systemRelationId) {
    this.systemRelationId = systemRelationId;
  }
  public String getUpdateUserName() {
    return updateUserName;
  }
  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  public String getFkCompanyCode() {
    return fkCompanyCode;
  }
  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }
  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public String getSystemSmccCode() {
    return systemSmccCode;
  }
  public void setSystemSmccCode(String systemSmccCode) {
    this.systemSmccCode = systemSmccCode;
  }
  public String getStandardizedName() {
    return standardizedName;
  }
  public void setStandardizedName(String standardizedName) {
    this.standardizedName = standardizedName;
  }
  public String getStandardizedCode() {
    return standardizedCode;
  }
  public void setStandardizedCode(String standardizedCode) {
    this.standardizedCode = standardizedCode;
  }
  public String getSystemIsMerge() {
    return systemIsMerge;
  }
  public void setSystemIsMerge(String systemIsMerge) {
    this.systemIsMerge = systemIsMerge;
  }
  public String getSystemSource() {
    return systemSource;
  }
  public void setSystemSource(String systemSource) {
    this.systemSource = systemSource;
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
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
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
  public List<String> getCompanyList() {
    return companyList;
  }
  public void setCompanyList(List<String> companyList) {
    this.companyList = companyList;
  }
  public List<String> getPlateList() {
    return plateList;
  }
  public void setPlateList(List<String> plateList) {
    this.plateList = plateList;
  }
  public SystemRelationParam getSystemRelationParam() {
    return systemRelationParam;
  }
  public void setSystemRelationParam(SystemRelationParam systemRelationParam) {
    this.systemRelationParam = systemRelationParam;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
}
