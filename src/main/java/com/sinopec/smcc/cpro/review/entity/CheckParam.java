
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
/**
 * @Title Check.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月25日下午12:38:21
 * @version V1.0
 */
public class CheckParam {
  
  //系统表id
  private String fkSystemId;
  //审核表id
  private String checkId;
  //审核节点状态
  private String fkExaminStatus;
  //是否删除
  private Integer deleteStatus;
  //是否审核
  private Integer examineResult;
  //创建人
  private String createUserName;
  //流程实例名称
  private String instanceName;
  //发起人
  private String initiator;
  //附件名称
  private String attachName;
  //业务节点
  private String fkbusinessNode;
  //上一步执行人
  private String prevExecutor;
  //执行时间
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTime;
  //更新时间
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  //创建时间
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  //备注
  private String remark;
  
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
  
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }
  public void setFkExaminStatus(String fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }
  public Integer getDeleteStatus() {
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }
  public Integer getExamineResult() {
    return examineResult;
  }
  public void setExamineResult(Integer examineResult) {
    this.examineResult = examineResult;
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
  public String getAttachName() {
    return attachName;
  }
  public void setAttachName(String attachName) {
    this.attachName = attachName;
  }
  public String getCheckId() {
    return checkId;
  }
  public void setCheckId(String checkId) {
    this.checkId = checkId;
  }
  public String getFkbusinessNode() {
    return fkbusinessNode;
  }
  public void setFkbusinessNode(String fkbusinessNode) {
    this.fkbusinessNode = fkbusinessNode;
  }
  public String getPrevExecutor() {
    return prevExecutor;
  }
  public void setPrevExecutor(String prevExecutor) {
    this.prevExecutor = prevExecutor;
  }
  public Date getExecuteTime() {
    return executeTime;
  }
  public void setExecuteTime(Date executeTime) {
    this.executeTime = executeTime;
  }
  public String getFkSystemId() {
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId) {
    this.fkSystemId = fkSystemId;
  }
  
  public String getCreateUserName() {
    return createUserName;
  }
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
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
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }

}
