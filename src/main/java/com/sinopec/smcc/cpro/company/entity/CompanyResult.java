/**
* 2018. 
* @Title CompanyListResult.java
* @Package com.sinopec.smcc.cpro.company.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日下午1:37:56
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.entity;

import com.alibaba.fastjson.JSON;



/**
 * @Title CompanyListResult.java
 * @Package com.sinopec.smcc.cpro.company.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日下午1:37:56
 * @version V1.0
 */
public class CompanyResult {
  private String companyId;//主键
  private String ldContactEmail;
  private String companyName;
  private Integer postalCode;
  private String compPrincipalWorkTel;
  private String createUserName;
  private String ldContactName;
  private String rDepartment;
  private String compPrincipalEm;
  private String ldContactPhone;
  private Integer administrativeNum;
  private String compPrincipalPhone;
  private String companyCode;
  private String compPrincipalPost;
  private String ldContactPost;
  private String gpReportingComp;
  private String companyAddress;
  private String ldContactWorkTel;
  private String compPrincipalName;
  private String provincesName;
  private String categoryName;
  private String affiliationName;
  private String fkCompanyType;
  private String fkPlateType;
  private String companyTypeName;
  private String plateTypeName;
  private String fkSubordinatePro;
  private String fkIndustryCategory;
  private String fkAffiliation;
  
  
  /**
   * @return the companyId
   */
  public String getCompanyId() {
    return companyId;
  }


  /**
   * @param companyId the companyId to set
   */
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }


  /**
   * @return the ldContactEmail
   */
  public String getLdContactEmail() {
    return ldContactEmail;
  }


  /**
   * @param ldContactEmail the ldContactEmail to set
   */
  public void setLdContactEmail(String ldContactEmail) {
    this.ldContactEmail = ldContactEmail;
  }


  /**
   * @return the companyName
   */
  public String getCompanyName() {
    return companyName;
  }


  /**
   * @param companyName the companyName to set
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  /**
   * @return the postalCode
   */
  public Integer getPostalCode() {
    return postalCode;
  }


  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }


  /**
   * @return the compPrincipalWorkTel
   */
  public String getCompPrincipalWorkTel() {
    return compPrincipalWorkTel;
  }


  /**
   * @param compPrincipalWorkTel the compPrincipalWorkTel to set
   */
  public void setCompPrincipalWorkTel(String compPrincipalWorkTel) {
    this.compPrincipalWorkTel = compPrincipalWorkTel;
  }


  /**
   * @return the createUserName
   */
  public String getCreateUserName() {
    return createUserName;
  }


  /**
   * @param createUserName the createUserName to set
   */
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }


  /**
   * @return the ldContactName
   */
  public String getLdContactName() {
    return ldContactName;
  }


  /**
   * @param ldContactName the ldContactName to set
   */
  public void setLdContactName(String ldContactName) {
    this.ldContactName = ldContactName;
  }


  /**
   * @return the rDepartment
   */
  public String getrDepartment() {
    return rDepartment;
  }


  /**
   * @param rDepartment the rDepartment to set
   */
  public void setrDepartment(String rDepartment) {
    this.rDepartment = rDepartment;
  }


  /**
   * @return the companyPrincipalEm
   */
  public String getCompPrincipalEm() {
    return compPrincipalEm;
  }


  /**
   * @param companyPrincipalEm the companyPrincipalEm to set
   */
  public void setCompPrincipalEm(String compPrincipalEm) {
    this.compPrincipalEm = compPrincipalEm;
  }


  /**
   * @return the ldContactPhone
   */
  public String getLdContactPhone() {
    return ldContactPhone;
  }


  /**
   * @param ldContactPhone the ldContactPhone to set
   */
  public void setLdContactPhone(String ldContactPhone) {
    this.ldContactPhone = ldContactPhone;
  }


  /**
   * @return the administrativeNum
   */
  public Integer getAdministrativeNum() {
    return administrativeNum;
  }


  /**
   * @param administrativeNum the administrativeNum to set
   */
  public void setAdministrativeNum(Integer administrativeNum) {
    this.administrativeNum = administrativeNum;
  }


  /**
   * @return the compPrincipalPhone
   */
  public String getCompPrincipalPhone() {
    return compPrincipalPhone;
  }


  /**
   * @param compPrincipalPhone the compPrincipalPhone to set
   */
  public void setCompPrincipalPhone(String compPrincipalPhone) {
    this.compPrincipalPhone = compPrincipalPhone;
  }


  /**
   * @return the companyCode
   */
  public String getCompanyCode() {
    return companyCode;
  }


  /**
   * @param companyCode the companyCode to set
   */
  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }


  /**
   * @return the compPrincipalPost
   */
  public String getCompPrincipalPost() {
    return compPrincipalPost;
  }


  /**
   * @param compPrincipalPost the compPrincipalPost to set
   */
  public void setCompPrincipalPost(String compPrincipalPost) {
    this.compPrincipalPost = compPrincipalPost;
  }


  /**
   * @return the ldContactPost
   */
  public String getLdContactPost() {
    return ldContactPost;
  }


  /**
   * @param ldContactPost the ldContactPost to set
   */
  public void setLdContactPost(String ldContactPost) {
    this.ldContactPost = ldContactPost;
  }


  /**
   * @return the gpReportingComp
   */
  public String getGpReportingComp() {
    return gpReportingComp;
  }


  /**
   * @param gpReportingComp the gpReportingComp to set
   */
  public void setGpReportingComp(String gpReportingComp) {
    this.gpReportingComp = gpReportingComp;
  }


  /**
   * @return the companyAddress
   */
  public String getCompanyAddress() {
    return companyAddress;
  }


  /**
   * @param companyAddress the companyAddress to set
   */
  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }


  /**
   * @return the ldContactWorkTel
   */
  public String getLdContactWorkTel() {
    return ldContactWorkTel;
  }


  /**
   * @param ldContactWorkTel the ldContactWorkTel to set
   */
  public void setLdContactWorkTel(String ldContactWorkTel) {
    this.ldContactWorkTel = ldContactWorkTel;
  }


  /**
   * @return the compPrincipalName
   */
  public String getCompPrincipalName() {
    return compPrincipalName;
  }


  /**
   * @param compPrincipalName the compPrincipalName to set
   */
  public void setCompPrincipalName(String compPrincipalName) {
    this.compPrincipalName = compPrincipalName;
  }


  /**
   * @return the provincesName
   */
  public String getProvincesName() {
    return provincesName;
  }


  /**
   * @param provincesName the provincesName to set
   */
  public void setProvincesName(String provincesName) {
    this.provincesName = provincesName;
  }


  /**
   * @return the categoryName
   */
  public String getCategoryName() {
    return categoryName;
  }


  /**
   * @param categoryName the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  /**
   * @return the affiliationName
   */
  public String getAffiliationName() {
    return affiliationName;
  }


  /**
   * @param affiliationName the affiliationName to set
   */
  public void setAffiliationName(String affiliationName) {
    this.affiliationName = affiliationName;
  }


  /**
   * @return the fkCompanyType
   */
  public String getFkCompanyType() {
    return fkCompanyType;
  }


  /**
   * @param fkCompanyType the fkCompanyType to set
   */
  public void setFkCompanyType(String fkCompanyType) {
    this.fkCompanyType = fkCompanyType;
  }


  /**
   * @return the fkPlateType
   */
  public String getFkPlateType() {
    return fkPlateType;
  }


  /**
   * @param fkPlateType the fkPlateType to set
   */
  public void setFkPlateType(String fkPlateType) {
    this.fkPlateType = fkPlateType;
  }


  /**
   * @return the companyTypeName
   */
  public String getCompanyTypeName() {
    return companyTypeName;
  }


  /**
   * @param companyTypeName the companyTypeName to set
   */
  public void setCompanyTypeName(String companyTypeName) {
    this.companyTypeName = companyTypeName;
  }


  /**
   * @return the plateTypeName
   */
  public String getPlateTypeName() {
    return plateTypeName;
  }


  /**
   * @param plateTypeName the plateTypeName to set
   */
  public void setPlateTypeName(String plateTypeName) {
    this.plateTypeName = plateTypeName;
  }


  /**
   * @return the fkSubordinatePro
   */
  public String getFkSubordinatePro() {
    return fkSubordinatePro;
  }


  /**
   * @param fkSubordinatePro the fkSubordinatePro to set
   */
  public void setFkSubordinatePro(String fkSubordinatePro) {
    this.fkSubordinatePro = fkSubordinatePro;
  }


  /**
   * @return the fkIndustryCategory
   */
  public String getFkIndustryCategory() {
    return fkIndustryCategory;
  }


  /**
   * @param fkIndustryCategory the fkIndustryCategory to set
   */
  public void setFkIndustryCategory(String fkIndustryCategory) {
    this.fkIndustryCategory = fkIndustryCategory;
  }


  /**
   * @return the fkAffiliation
   */
  public String getFkAffiliation() {
    return fkAffiliation;
  }


  /**
   * @param fkAffiliation the fkAffiliation to set
   */
  public void setFkAffiliation(String fkAffiliation) {
    this.fkAffiliation = fkAffiliation;
  }


  public String toString() {
    return JSON.toJSONString(this);
  }
  
}
