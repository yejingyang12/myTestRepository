/**
* 2018. 
* @Title SystemListResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午2:42:46
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title SystemListResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午2:42:46
 * @version V1.0
 */
public class SystemListResult {
	
  private String systemId;
	private String systemName;
	private String sysBusSituationType;
	private String sysBusDescription;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date whenInvestmentUse;
	
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序
	
  
  /**
   * @return the sysBusSituationType
   */
  public String getSysBusSituationType() {
    return sysBusSituationType;
  }
  /**
   * @param sysBusSituationType the sysBusSituationType to set
   */
  public void setSysBusSituationType(String sysBusSituationType) {
    this.sysBusSituationType = sysBusSituationType;
  }
  /**
   * @return the sysBusDescription
   */
  public String getSysBusDescription() {
    return sysBusDescription;
  }
  /**
   * @param sysBusDescription the sysBusDescription to set
   */
  public void setSysBusDescription(String sysBusDescription) {
    this.sysBusDescription = sysBusDescription;
  }
  /**
   * @return the pageSize
   */
  public int getPageSize() {
    return pageSize;
  }
  /**
   * @param pageSize the pageSize to set
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  /**
   * @return the currentPage
   */
  public int getCurrentPage() {
    return currentPage;
  }
  /**
   * @param currentPage the currentPage to set
   */
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  /**
   * @return the field
   */
  public String getField() {
    return field;
  }
  /**
   * @param field the field to set
   */
  public void setField(String field) {
    this.field = field;
  }
  /**
   * @return the sort
   */
  public String getSort() {
    return sort;
  }
  /**
   * @param sort the sort to set
   */
  public void setSort(String sort) {
    this.sort = sort;
  }
  /**
   * @return the systemId
   */
  public String getSystemId() {
    return systemId;
  }
  /**
   * @param systemId the systemId to set
   */
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  /**
   * @return the systemName
   */
  public String getSystemName() {
    return systemName;
  }
  /**
   * @param systemName the systemName to set
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  /**
   * @return the whenInvestmentUse
   */
  public Date getWhenInvestmentUse() {
    return whenInvestmentUse;
  }
  /**
   * @param whenInvestmentUse the whenInvestmentUse to set
   */
  public void setWhenInvestmentUse(Date whenInvestmentUse) {
    this.whenInvestmentUse = whenInvestmentUse;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
