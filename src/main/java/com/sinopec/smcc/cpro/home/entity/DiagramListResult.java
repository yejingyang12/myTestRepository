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
public class DiagramListResult {
  private Integer level1;
  private Integer level2;
  private Integer level3;
  private Integer level4;
  private Integer level5;

  private Integer readyGradCount;
  private Integer checkGradCount;
  private Integer recordsCount;
  private Integer evaluationCount;
  private Integer selfInspectionCount;
  
  private Integer level;
  private String companyName;
  
  private String month;
  private Integer mouthCount;
  
  
  /**
   * @return the mouthCount
   */
  public Integer getMouthCount() {
    return mouthCount;
  }
  /**
   * @param mouthCount the mouthCount to set
   */
  public void setMouthCount(Integer mouthCount) {
    this.mouthCount = mouthCount;
  }
  /**
   * @return the month
   */
  public String getMonth() {
    return month;
  }
  /**
   * @param month the month to set
   */
  public void setMonth(String month) {
    this.month = month;
  }
  /**
   * @return the readyGradCount
   */
  public Integer getReadyGradCount() {
    return readyGradCount;
  }
  /**
   * @param readyGradCount the readyGradCount to set
   */
  public void setReadyGradCount(Integer readyGradCount) {
    this.readyGradCount = readyGradCount;
  }
  /**
   * @return the checkGradCount
   */
  public Integer getCheckGradCount() {
    return checkGradCount;
  }
  /**
   * @param checkGradCount the checkGradCount to set
   */
  public void setCheckGradCount(Integer checkGradCount) {
    this.checkGradCount = checkGradCount;
  }
  /**
   * @return the recordsCount
   */
  public Integer getRecordsCount() {
    return recordsCount;
  }
  /**
   * @param recordsCount the recordsCount to set
   */
  public void setRecordsCount(Integer recordsCount) {
    this.recordsCount = recordsCount;
  }
  /**
   * @return the evaluationCount
   */
  public Integer getEvaluationCount() {
    return evaluationCount;
  }
  /**
   * @param evaluationCount the evaluationCount to set
   */
  public void setEvaluationCount(Integer evaluationCount) {
    this.evaluationCount = evaluationCount;
  }
  /**
   * @return the selfInspectionCount
   */
  public Integer getSelfInspectionCount() {
    return selfInspectionCount;
  }
  /**
   * @param selfInspectionCount the selfInspectionCount to set
   */
  public void setSelfInspectionCount(Integer selfInspectionCount) {
    this.selfInspectionCount = selfInspectionCount;
  }
  public Integer getLevel1() {
    return level1;
  }
  public void setLevel1(Integer level1) {
    this.level1 = level1;
  }

  public Integer getLevel2() {
    return level2;
  }
  public void setLevel2(Integer level2) {
    this.level2 = level2;
  }

  public Integer getLevel3() {
    return level3;
  }
  public void setLevel3(Integer level3) {
    this.level3 = level3;
  }

  public Integer getLevel4() {
    return level4;
  }
  public void setLevel4(Integer level4) {
    this.level4 = level4;
  }

  public Integer getLevel5() {
    return level5;
  }
  public void setLevel5(Integer level5) {
    this.level5 = level5;
  }

  public Integer getLevel() {
    return level;
  }
  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String toString(){
    return JSON.toJSONString(this);
  }
  
}