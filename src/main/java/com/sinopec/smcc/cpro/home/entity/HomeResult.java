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

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title HomeResult.java
 * @Package com.sinopec.smcc.cpro.home.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:56:39
 * @version V1.0
 */
public class HomeResult {
  
  private String companyName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date acceptDate;
  private String fkCompanyCode;
  private String level1;
  private String level2;
  private String level3;
  private String level4;
  private String level5;
  private String level;
  private int num;
  private int count;
  
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public Date getRecordDate() {
    return recordDate;
  }
  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }
  public Date getAcceptDate() {
    return acceptDate;
  }
  public void setAcceptDate(Date acceptDate) {
    this.acceptDate = acceptDate;
  }
  public String getFkCompanyCode() {
    return fkCompanyCode;
  }
  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }
  public String getLevel1() {
    return level1;
  }
  public void setLevel1(String level1) {
    this.level1 = level1;
  }
  public String getLevel2() {
    return level2;
  }
  public void setLevel2(String level2) {
    this.level2 = level2;
  }
  public String getLevel3() {
    return level3;
  }
  public void setLevel3(String level3) {
    this.level3 = level3;
  }
  public String getLevel4() {
    return level4;
  }
  public void setLevel4(String level4) {
    this.level4 = level4;
  }
  public String getLevel5() {
    return level5;
  }
  public void setLevel5(String level5) {
    this.level5 = level5;
  }
  public String getLevel() {
    return level;
  }
  public void setLevel(String level) {
    this.level = level;
  }
  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
  
}