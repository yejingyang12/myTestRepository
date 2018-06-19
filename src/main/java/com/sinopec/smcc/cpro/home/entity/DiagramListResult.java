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
  
  private Integer level;
  private String companyName;
  
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