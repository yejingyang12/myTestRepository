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



/**
 * @Title CompanyListResult.java
 * @Package com.sinopec.smcc.cpro.company.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日下午1:37:56
 * @version V1.0
 */
public class CompanyListResult {
  private String companyId;//主键
  private String liabilityDepartmentContactEmail;
  private String companyName;
  private String postalCode;
  private String compPrincipalWorkTel;
  private String createUserName;
  private String liabilityDepartmentContactName;
  private String responsibleDepartment;
  private String companyPrincipalEmail;
  private String liabilityDepartmentContactPhone;
  private Integer administrativeDivisionNum;
  private Integer compPrincipalPhone;
  private String companyCode;
  private String compPrincipalPost;
  private String liabilityDepartmentContactPost;
  private String gradeProtectionReportingComp;
  private String companyAddress;
  private String liabilityDepartmentContactWorkTel;
  private String compPrincipalName;
  private String provincesName;
  private String categoryName;
  private String affiliationName;
  private String companyType;
  private String plateType;
  private String companyTypeName;
  private String plateTypeName;
  private String fkSubordinateProvinces;
  private String industryCategory;
  private String affiliation;

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
   * @return the fkSubordinateProvinces
   */
  public String getFkSubordinateProvinces() {
    return fkSubordinateProvinces;
  }
  /**
   * @param fkSubordinateProvinces the fkSubordinateProvinces to set
   */
  public void setFkSubordinateProvinces(String fkSubordinateProvinces) {
    this.fkSubordinateProvinces = fkSubordinateProvinces;
  }
  /**
   * @return the industryCategory
   */
  public String getIndustryCategory() {
    return industryCategory;
  }
  /**
   * @param industryCategory the industryCategory to set
   */
  public void setIndustryCategory(String industryCategory) {
    this.industryCategory = industryCategory;
  }
  /**
   * @return the affiliation
   */
  public String getAffiliation() {
    return affiliation;
  }
  /**
   * @param affiliation the affiliation to set
   */
  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }
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
   * @return the liabilityDepartmentContactEmail
   */
  public String getLiabilityDepartmentContactEmail() {
    return liabilityDepartmentContactEmail;
  }
  /**
   * @param liabilityDepartmentContactEmail the liabilityDepartmentContactEmail to set
   */
  public void setLiabilityDepartmentContactEmail(String liabilityDepartmentContactEmail) {
    this.liabilityDepartmentContactEmail = liabilityDepartmentContactEmail;
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
  public String getPostalCode() {
    return postalCode;
  }
  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(String postalCode) {
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
   * @return the liabilityDepartmentContactName
   */
  public String getLiabilityDepartmentContactName() {
    return liabilityDepartmentContactName;
  }
  /**
   * @param liabilityDepartmentContactName the liabilityDepartmentContactName to set
   */
  public void setLiabilityDepartmentContactName(String liabilityDepartmentContactName) {
    this.liabilityDepartmentContactName = liabilityDepartmentContactName;
  }
  /**
   * @return the responsibleDepartment
   */
  public String getResponsibleDepartment() {
    return responsibleDepartment;
  }
  /**
   * @param responsibleDepartment the responsibleDepartment to set
   */
  public void setResponsibleDepartment(String responsibleDepartment) {
    this.responsibleDepartment = responsibleDepartment;
  }
  /**
   * @return the companyPrincipalEmail
   */
  public String getCompanyPrincipalEmail() {
    return companyPrincipalEmail;
  }
  /**
   * @param companyPrincipalEmail the companyPrincipalEmail to set
   */
  public void setCompanyPrincipalEmail(String companyPrincipalEmail) {
    this.companyPrincipalEmail = companyPrincipalEmail;
  }
  /**
   * @return the liabilityDepartmentContactPhone
   */
  public String getLiabilityDepartmentContactPhone() {
    return liabilityDepartmentContactPhone;
  }
  /**
   * @param liabilityDepartmentContactPhone the liabilityDepartmentContactPhone to set
   */
  public void setLiabilityDepartmentContactPhone(String liabilityDepartmentContactPhone) {
    this.liabilityDepartmentContactPhone = liabilityDepartmentContactPhone;
  }
  /**
   * @return the administrativeDivisionNum
   */
  public Integer getAdministrativeDivisionNum() {
    return administrativeDivisionNum;
  }
  /**
   * @param administrativeDivisionNum the administrativeDivisionNum to set
   */
  public void setAdministrativeDivisionNum(Integer administrativeDivisionNum) {
    this.administrativeDivisionNum = administrativeDivisionNum;
  }
  /**
   * @return the compPrincipalPhone
   */
  public Integer getCompPrincipalPhone() {
    return compPrincipalPhone;
  }
  /**
   * @param compPrincipalPhone the compPrincipalPhone to set
   */
  public void setCompPrincipalPhone(Integer compPrincipalPhone) {
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
   * @return the liabilityDepartmentContactPost
   */
  public String getLiabilityDepartmentContactPost() {
    return liabilityDepartmentContactPost;
  }
  /**
   * @param liabilityDepartmentContactPost the liabilityDepartmentContactPost to set
   */
  public void setLiabilityDepartmentContactPost(String liabilityDepartmentContactPost) {
    this.liabilityDepartmentContactPost = liabilityDepartmentContactPost;
  }
  /**
   * @return the gradeProtectionReportingComp
   */
  public String getGradeProtectionReportingComp() {
    return gradeProtectionReportingComp;
  }
  /**
   * @param gradeProtectionReportingComp the gradeProtectionReportingComp to set
   */
  public void setGradeProtectionReportingComp(String gradeProtectionReportingComp) {
    this.gradeProtectionReportingComp = gradeProtectionReportingComp;
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
   * @return the liabilityDepartmentContactWorkTel
   */
  public String getLiabilityDepartmentContactWorkTel() {
    return liabilityDepartmentContactWorkTel;
  }
  /**
   * @param liabilityDepartmentContactWorkTel the liabilityDepartmentContactWorkTel to set
   */
  public void setLiabilityDepartmentContactWorkTel(String liabilityDepartmentContactWorkTel) {
    this.liabilityDepartmentContactWorkTel = liabilityDepartmentContactWorkTel;
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
   * @return the companyType
   */
  public String getCompanyType() {
    return companyType;
  }
  /**
   * @param companyType the companyType to set
   */
  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }
  /**
   * @return the plateType
   */
  public String getPlateType() {
    return plateType;
  }
  /**
   * @param plateType the plateType to set
   */
  public void setPlateType(String plateType) {
    this.plateType = plateType;
  }
 
  
}
