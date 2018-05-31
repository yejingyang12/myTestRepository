/**
* 2018. 
* @Title CproSystemKeyProducts.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午9:40:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title CproSystemKeyProducts.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午9:40:32
 * @version V1.0
 */
public class SystemKeyProducts {
	
  private String systemKeyProductsId;
  private Integer fkExaminStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Integer deleteStatus;
  private String productsNumber;
  private Integer nUseProbability;
  private String otherName;
  private String fkNationalIsProducts;
	private String fkSystemId;
  private SystemKeyProducts cproSystemKeyProducts; 
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序
  
  
  /**
   * @return the nUseProbability
   */
  public int getnUseProbability() {
    return nUseProbability;
  }

  /**
   * @param nUseProbability the nUseProbability to set
   */
  public void setnUseProbability(int nUseProbability) {
    this.nUseProbability = nUseProbability;
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

	public SystemKeyProducts clone() throws CloneNotSupportedException{
    SystemKeyProducts cproSystemKeyProducts = (SystemKeyProducts) super.clone();
    return cproSystemKeyProducts;
  }

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @return the systemKeyProductsId
	 */
	public String getSystemKeyProductsId() {
		return systemKeyProductsId;
	}

	/**
	 * @param systemKeyProductsId the systemKeyProductsId to set
	 */
	public void setSystemKeyProductsId(String systemKeyProductsId) {
		this.systemKeyProductsId = systemKeyProductsId;
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
	 * @return the productsNumber
	 */
	public String getProductsNumber() {
		return productsNumber;
	}

	/**
	 * @param productsNumber the productsNumber to set
	 */
	public void setProductsNumber(String productsNumber) {
		this.productsNumber = productsNumber;
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
	 * @return the cproSystemKeyProducts
	 */
	public SystemKeyProducts getCproSystemKeyProducts() {
		return cproSystemKeyProducts;
	}

	/**
	 * @param cproSystemKeyProducts the cproSystemKeyProducts to set
	 */
	public void setCproSystemKeyProducts(SystemKeyProducts cproSystemKeyProducts) {
		this.cproSystemKeyProducts = cproSystemKeyProducts;
	}

  /**
	 * @return the fkExaminStatus
	 */
	public Integer getFkExaminStatus() {
		return fkExaminStatus;
	}

	/**
	 * @param fkExaminStatus the fkExaminStatus to set
	 */
	public void setFkExaminStatus(Integer fkExaminStatus) {
		this.fkExaminStatus = fkExaminStatus;
	}

	/**
	 * @return the fknationalIsProducts
	 */
	public String getFkNationalIsProducts() {
		return fkNationalIsProducts;
	}

	/**
	 * @param fknationalIsProducts the fknationalIsProducts to set
	 */
	public void setFkNationalIsProducts(String fkNationalIsProducts) {
		this.fkNationalIsProducts = fkNationalIsProducts;
	}
	
	public String toString(){
    return JSON.toJSONString(this);
  }
}
