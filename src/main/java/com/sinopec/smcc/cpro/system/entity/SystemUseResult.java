/**
* 2018. 
* @Title SystemUseResult.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年6月11日下午6:35:47
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title SystemUseResult.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年6月11日下午6:35:47
 * @version V1.0
 */
public class SystemUseResult {
  
  private String systemUseServicesId;
  private String productsTypeName;
  private String responsibleTypeName;
  private String useName;
  private String otherName;
  private Integer fkResponsibleType;
  private Integer fkProductsType;
  private Integer serviceIsUse;
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
  public Integer getFkResponsibleType() {
    return fkResponsibleType;
  }
  /**
   * @param fkResponsibleType the fkResponsibleType to set
   */
  public void setFkResponsibleType(Integer fkResponsibleType) {
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
   * @return the productsTypeName
   */
  public String getProductsTypeName() {
    return productsTypeName;
  }
  /**
   * @param productsTypeName the productsTypeName to set
   */
  public void setProductsTypeName(String productsTypeName) {
    this.productsTypeName = productsTypeName;
  }
  /**
   * @return the responsibleTypeName
   */
  public String getResponsibleTypeName() {
    return responsibleTypeName;
  }
  /**
   * @param responsibleTypeName the responsibleTypeName to set
   */
  public void setResponsibleTypeName(String responsibleTypeName) {
    this.responsibleTypeName = responsibleTypeName;
  }
  /**
   * @return the useName
   */
  public String getUseName() {
    return useName;
  }
  /**
   * @param useName the useName to set
   */
  public void setUseName(String useName) {
    this.useName = useName;
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
