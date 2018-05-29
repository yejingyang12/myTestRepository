/**
* 2018. 
* @Title CproSystemUseServices.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午9:41:56
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title CproSystemUseServices.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午9:41:56
 * @version V1.0
 */
public class SystemUseServices {
  private String systemUseServicesId;
  private String fkResponsibleType;
  private Integer fkProductsType;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Integer deleteStatus;
  private Integer serviceIsUse;
  private String otherName;
  private String fkSystemId;
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序

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

	private SystemUseServices cproSystemUseServices; 

  public SystemUseServices clone() throws CloneNotSupportedException{
    SystemUseServices cproSystemUseServices = (SystemUseServices) super.clone();
    return cproSystemUseServices;
  }

	/**
	 * @return the systemUseServicesId
	 */
	public String getSystemUseServicesId() {
		return systemUseServicesId;
	}

	/**
	 * @param systemUseServicesId the systemUseServicesId to set
	 */
	public void setSystemUseServicesId(String systemUseServicesId) {
		this.systemUseServicesId = systemUseServicesId;
	}

	/**
	 * @return the fkResponsibleType
	 */
	public String getFkResponsibleType() {
		return fkResponsibleType;
	}

	/**
	 * @param fkResponsibleType the fkResponsibleType to set
	 */
	public void setFkResponsibleType(String fkResponsibleType) {
		this.fkResponsibleType = fkResponsibleType;
	}

	/**
	 * @return the fkProductsType
	 */
	public Integer getFkProductsType() {
		return fkProductsType;
	}

	/**
	 * @param fkProductsType the fkProductsType to set
	 */
	public void setFkProductsType(Integer fkProductsType) {
		this.fkProductsType = fkProductsType;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the deleteStatus
	 */
	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * @param deleteStatus the deleteStatus to set
	 */
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/**
	 * @return the serviceIsUse
	 */
	public Integer getServiceIsUse() {
		return serviceIsUse;
	}

	/**
	 * @param serviceIsUse the serviceIsUse to set
	 */
	public void setServiceIsUse(Integer serviceIsUse) {
		this.serviceIsUse = serviceIsUse;
	}

	/**
	 * @return the otherName
	 */
	public String getOtherName() {
		return otherName;
	}

	/**
	 * @param otherName the otherName to set
	 */
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	/**
	 * @return the fkSystemId
	 */
	public String getFkSystemId() {
		return fkSystemId;
	}

	/**
	 * @param fkSystemId the fkSystemId to set
	 */
	public void setFkSystemId(String fkSystemId) {
		this.fkSystemId = fkSystemId;
	}

	/**
	 * @return the cproSystemUseServices
	 */
	public SystemUseServices getCproSystemUseServices() {
		return cproSystemUseServices;
	}

	/**
	 * @param cproSystemUseServices the cproSystemUseServices to set
	 */
	public void setCproSystemUseServices(SystemUseServices cproSystemUseServices) {
		this.cproSystemUseServices = cproSystemUseServices;
	}
	public String toString(){
    return JSON.toJSONString(this);
  }
}
