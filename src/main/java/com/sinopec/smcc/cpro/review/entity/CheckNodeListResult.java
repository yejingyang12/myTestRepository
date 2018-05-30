/**
* 2018. 
* @Title CproCheckNodeResult.java
* @Package com.sinopec.smcc.cpro.review.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年5月26日下午5:54:44
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title CproCheckNodeResult.java
 * @Package com.sinopec.smcc.cpro.review.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年5月26日下午5:54:44
 * @version V1.0
 */
public class CheckNodeListResult implements Cloneable,java.io.Serializable{
  private static final long serialVersionUID = 1L;
  private String checkNodeId;
  private Integer examineResult;
  private String fkCheckId;
  private String createUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String remark;
  private String fkSystemId;
  private String examineReason;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String executor;
  private Integer deleteStatus;
  private String fkExaminStatus;
  private String fkActionName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date executeTime;

  private CheckNodeListResult cproCheckNode; 

  public CheckNodeListResult clone() throws CloneNotSupportedException{
    CheckNodeListResult cproCheckNode = (CheckNodeListResult) super.clone();
    return cproCheckNode;
  }

  public String getCheckNodeId() {
    return checkNodeId;
  }

  public void setCheckNodeId(String checkNodeId) {
    this.checkNodeId = checkNodeId;
  }

  public Integer getExamineResult() {
    return examineResult;
  }

  public void setExamineResult(Integer examineResult) {
    this.examineResult = examineResult;
  }

  public String getFkCheckId(){
    return fkCheckId;
  }
  public void setFkCheckId(String fkCheckId){
    this.fkCheckId=fkCheckId;
  }

  public String getCreateUserName(){
    return createUserName;
  }
  public void setCreateUserName(String createUserName){
    this.createUserName=createUserName;
  }

  public Date getUpdateTime(){
    return updateTime;
  }
  public void setUpdateTime(Date updateTime){
    this.updateTime=updateTime;
  }

  public String getRemark(){
    return remark;
  }
  public void setRemark(String remark){
    this.remark=remark;
  }

  public String getFkSystemId(){
    return fkSystemId;
  }
  public void setFkSystemId(String fkSystemId){
    this.fkSystemId=fkSystemId;
  }

  public String getExamineReason(){
    return examineReason;
  }
  public void setExamineReason(String examineReason){
    this.examineReason=examineReason;
  }

  public Date getCreateTime(){
    return createTime;
  }
  public void setCreateTime(Date createTime){
    this.createTime=createTime;
  }

  public String getExecutor(){
    return executor;
  }
  public void setExecutor(String executor){
    this.executor=executor;
  }

  public Integer getDeleteStatus(){
    return deleteStatus;
  }
  public void setDeleteStatus(Integer deleteStatus){
    this.deleteStatus=deleteStatus;
  }

  public String getFkActionName() {
    return fkActionName;
  }

  public void setFkActionName(String fkActionName) {
    this.fkActionName = fkActionName;
  }

  public Date getExecuteTime(){
    return executeTime;
  }
  public void setExecuteTime(Date executeTime){
    this.executeTime=executeTime;
  }

  public void setCproCheckNode(CheckNodeListResult cproCheckNode){
    this.cproCheckNode=cproCheckNode;
  }
  public CheckNodeListResult getCproCheckNode(){
    return cproCheckNode;
  }
  
  public String getFkExaminStatus() {
    return fkExaminStatus;
  }

  public void setFkExaminStatus(String fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }

  public String toString(){
    return JSON.toJSONString(this);
  }
}
