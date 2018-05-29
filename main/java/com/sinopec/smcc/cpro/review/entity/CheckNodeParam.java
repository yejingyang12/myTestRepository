/**
* 2018. 
* @Title CheckNodeParam.java
* @Package com.sinopec.smcc.cpro.review.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月26日下午6:12:41
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title CheckNodeParam.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月26日下午6:12:41
 * @version V1.0
 */
public class CheckNodeParam {
  
  private String fkCheckId;
  private Integer examineResult;
  private String createUserName;
  private String remark;
  private String fkSystemId;
  private String examineReason;
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String executor;
  private String fkExaminStatus;
  private String fkActionName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTime;
  private Integer deleteStatus;
  
  //每页显示多少条
  private int pageSize =10;
  //当前页数
  private int currentPage =1;
  //排序字段
  private String field;
  //排序
  private String sort;
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
  
  public String getFkCheckId() {
    return fkCheckId;
  }
  public void setFkCheckId(String fkCheckId) {
    this.fkCheckId = fkCheckId;
  }
  
  public Integer getExamineResult() {
    return examineResult;
  }
  public void setExamineResult(Integer examineResult) {
    this.examineResult = examineResult;
  }
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  public String getExamineReason() {
    return examineReason;
  }
  public void setExamineReason(String examineReason) {
    this.examineReason = examineReason;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public String getExecutor() {
    return executor;
  }
  public void setExecutor(String executor) {
    this.executor = executor;
  }
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }
  public void setFkExaminStatus(String fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }
  public String getFkActionName() {
    return fkActionName;
  }
  public void setFkActionName(String fkActionName) {
    this.fkActionName = fkActionName;
  }
  public Date getExecuteTime() {
    return executeTime;
  }
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }
  
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }

}
