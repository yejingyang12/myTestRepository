/**
* 2018. 
* @Title SystemKeyResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年6月11日下午6:35:24
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title SystemKeyResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年6月11日下午6:35:24
 * @version V1.0
 */
public class SystemKeyResult {
  
  private String systemKeyProductsId;
  private String examinStatusName;
  private String productsNumber;
  private String nationalIsProductsName;
  private Integer nUseProbability;
  private String otherName;
  private Integer fkExaminStatus;
  private String fkNationalIsProducts;
  private String fkSystemId;
  
  
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
  public String getFkNationalIsProducts() {
    return fkNationalIsProducts;
  }
  /**
   * @param fkNationalIsProducts the fkNationalIsProducts to set
   */
  public void setFkNationalIsProducts(String fkNationalIsProducts) {
    this.fkNationalIsProducts = fkNationalIsProducts;
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
   * @return the examinStatusName
   */
  public String getExaminStatusName() {
    return examinStatusName;
  }
  /**
   * @param examinStatusName the examinStatusName to set
   */
  public void setExaminStatusName(String examinStatusName) {
    this.examinStatusName = examinStatusName;
  }
  /**
   * @return the nationalIsProductsName
   */
  public String getNationalIsProductsName() {
    return nationalIsProductsName;
  }
  /**
   * @param nationalIsProductsName the nationalIsProductsName to set
   */
  public void setNationalIsProductsName(String nationalIsProductsName) {
    this.nationalIsProductsName = nationalIsProductsName;
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
   * @return the nUseProbability
   */
  public Integer getnUseProbability() {
    return nUseProbability;
  }
  /**
   * @param nUseProbability the nUseProbability to set
   */
  public void setnUseProbability(Integer nUseProbability) {
    this.nUseProbability = nUseProbability;
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
  
  
}
