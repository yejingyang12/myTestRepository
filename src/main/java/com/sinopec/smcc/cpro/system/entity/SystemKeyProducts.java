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
  private String productsNumber;
  private Integer fkNationalIsProducts;
  private Integer nUseProbability;
  private String otherName;
  
  private String fkSystemId;
  private Integer deleteStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

	/**
   * @return the nUseProbability
   */
  public Integer getnUseProbability() {
    return nUseProbability;
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
   * @param nUseProbability the nUseProbability to set
   */
  public void setnUseProbability(Integer nUseProbability) {
    this.nUseProbability = nUseProbability;
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
   * @return the fkNationalIsProducts
   */
  public Integer getFkNationalIsProducts() {
    return fkNationalIsProducts;
  }

  /**
   * @param fkNationalIsProducts the fkNationalIsProducts to set
   */
  public void setFkNationalIsProducts(Integer fkNationalIsProducts) {
    this.fkNationalIsProducts = fkNationalIsProducts;
  }

  public String toString(){
    return JSON.toJSONString(this);
  }
}
