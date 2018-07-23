/**
* 2018. 
* @Title HomeParam.java
* @Package com.sinopec.smcc.cpro.home.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:13:57
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.entity;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title HomeParam.java
 * @Package com.sinopec.smcc.cpro.home.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:13:57
 * @version V1.0
 */
public class DiagramParam {
  
  /*
   * 查询图表系统：
   *            1：定级系统：
   *            2：标准化网站系统：
   */
  private Integer systemType;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateEnd;
  
  private List<String> companyList;
  private List<String> plateList;
  
  /*
   * 类型:
   *      
   */
  private String codeName;
  private String year;
  private String userId;
  
  
  
  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }
  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  /**
   * @return the companyList
   */
  public List<String> getCompanyList() {
    return companyList;
  }
  /**
   * @param companyList the companyList to set
   */
  public void setCompanyList(List<String> companyList) {
    this.companyList = companyList;
  }
  /**
   * @return the plateList
   */
  public List<String> getPlateList() {
    return plateList;
  }
  /**
   * @param plateList the plateList to set
   */
  public void setPlateList(List<String> plateList) {
    this.plateList = plateList;
  }
  public String getYear() {
    return year;
  }
  public void setYear(String year) {
    this.year = year;
  }
  public Integer getSystemType() {
    return systemType;
  }
  public void setSystemType(Integer systemType) {
    this.systemType = systemType;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getDateBegin() {
    return dateBegin;
  }
  public void setDateBegin(Date dateBegin) {
    this.dateBegin = dateBegin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getDateEnd() {
    return dateEnd;
  }
  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }

  public String toString(){
    return JSON.toJSONString(this);
  }
  
}
