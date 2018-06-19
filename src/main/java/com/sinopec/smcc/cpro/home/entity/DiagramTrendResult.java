/**
* 2018. 
* @Title HomeResult.java
* @Package com.sinopec.smcc.cpro.home.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:56:39
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title HomeResult.java
 * @Package com.sinopec.smcc.cpro.home.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:56:39
 * @version V1.0
 */
public class DiagramTrendResult {
  private String month;
  private Integer exceptGradCount;
  private Integer checkGradCount;
  private Integer recordsCount;
  private Integer evaluationCount;
  private Integer selfInspectionCount;
  
  public String getMonth() {
    return month;
  }
  public void setMonth(String month) {
    this.month = month;
  }
  public Integer getExceptGradCount() {
    return exceptGradCount;
  }
  public void setExceptGradCount(Integer exceptGradCount) {
    this.exceptGradCount = exceptGradCount;
  }
  public Integer getCheckGradCount() {
    return checkGradCount;
  }
  public void setCheckGradCount(Integer checkGradCount) {
    this.checkGradCount = checkGradCount;
  }
  public Integer getRecordsCount() {
    return recordsCount;
  }
  public void setRecordsCount(Integer recordsCount) {
    this.recordsCount = recordsCount;
  }
  public Integer getEvaluationCount() {
    return evaluationCount;
  }
  public void setEvaluationCount(Integer evaluationCount) {
    this.evaluationCount = evaluationCount;
  }
  public Integer getSelfInspectionCount() {
    return selfInspectionCount;
  }
  public void setSelfInspectionCount(Integer selfInspectionCount) {
    this.selfInspectionCount = selfInspectionCount;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
  
}