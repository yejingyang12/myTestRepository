/**
* 2018. 
* @Title CompanyParam.java
* @Package com.sinopec.smcc.cpro.company.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日下午1:37:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
   * @Title CompanyParam.java
   * @Package com.sinopec.smcc.cpro.company.entity
   * @Description: TODO:
   * @author dongxu
   * @date 2018年5月25日下午1:37:15
   * @version V1.0
   */
public class CompanyParam {
  
  private String companyId;
  private String industryCategory;
  private String affiliation;
  private String companyType;
  private String plateType;
  private String fkSubordinateProvinces;
  private String liabilityDepartmentContactEmail;
  private String companyName;
  private String postalCode;
  private String compPrincipalWorkTel;
  private String createUserName;
  private String remark;
  private String liabilityDepartmentContactName;
  private String responsibleDepartment;
  private String companyPrincipalEmail;
  private Integer deleteStatus;
  private Integer liabilityDepartmentContactPhone;
  private Integer administrativeDivisionNum;
  private Integer compPrincipalPhone;
  private String companyCode;
  private String compPrincipalPost;
  private String liabilityDepartmentContactPost;
  private Date updateTime;
  private String gradeProtectionReportingComp;

  private Date createTime;
  private String companyAddress;
  private String liabilityDepartmentContactWorkTel;
  private String compPrincipalName;
  private String companyIds;
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序
  
  /**
   * @return the companyIds
   */
  public String getCompanyIds() {
    return companyIds;
  }


  /**
   * @param companyIds the companyIds to set
   */
  public void setCompanyIds(String companyIds) {
    this.companyIds = companyIds;
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
   * @return the remark
   */
  public String getRemark() {
    return remark;
  }


  /**
   * @param remark the remark to set
   */
  public void setRemark(String remark) {
    this.remark = remark;
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
  public Integer getLiabilityDepartmentContactPhone() {
    return liabilityDepartmentContactPhone;
  }


  /**
   * @param liabilityDepartmentContactPhone the liabilityDepartmentContactPhone to set
   */
  public void setLiabilityDepartmentContactPhone(Integer liabilityDepartmentContactPhone) {
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
   * @return the updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }


  /**
   * @param updateTime the updateTime to set
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
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
 
  public String toString() {

    return "{" + "\"companyId\":\"" + companyId + "\"," + "\"industryCategory\":\""
        + industryCategory + "\"," + "\"affiliation\":\"" + affiliation
        + "\"," + "\"companyType\":\"" + companyType + "\","
        + "\"plateType\":\"" + plateType + "\"," 
        + "\"fkSubordinateProvinces\":\"" + fkSubordinateProvinces + "\"," 
        + "\"liabilityDepartmentContactEmail\":\"" + liabilityDepartmentContactEmail
        + "\"," + "\"companyName\":\"" + companyName + "\","
        + "\"postalCode\":\"" + postalCode + "\"," + "\"compPrincipalWorkTel\":\""
        + compPrincipalWorkTel + "\"," + "\"createUserName\":\"" + createUserName + "\","
        + "\"remark\":\"" + remark + "\","
        + "\"liabilityDepartmentContactName\":\"" + liabilityDepartmentContactName + "\","
        + "\"responsibleDepartment\":\"" + responsibleDepartment + "\","
        + "\"companyPrincipalEmail\":\"" + companyPrincipalEmail + "\","
        + "\"deleteStatus\":\"" + deleteStatus + "\","
        + "\"liabilityDepartmentContactPhone\":\"" + liabilityDepartmentContactPhone + "\","
        + "\"administrativeDivisionNum\":\"" + administrativeDivisionNum + "\","
        + "\"compPrincipalPhone\":\"" + compPrincipalPhone + "\","
        + "\"compPrincipalPost\":\"" + compPrincipalPost + "\","
        + "\"liabilityDepartmentContactPost\":\"" + liabilityDepartmentContactPost + "\","
        + "\"updateTime\":\"" + updateTime + "\","
        + "\"gradeProtectionReportingComp\":\"" + gradeProtectionReportingComp + "\","
        + "\"createTime\":\"" + createTime + "\","
        + "\"companyAddress\":\"" + companyAddress + "\","
        + "\"liabilityDepartmentContactWorkTel\":\"" + liabilityDepartmentContactWorkTel + "\","
        + "\"compPrincipalName\":\"" + compPrincipalName + "\","
        + "\"companyIds\":\"" + companyIds + "\","
        
        + "\"pageSize\":\"" + pageSize + "\"," + "\"currentPage\":\""
        + currentPage + "\"," + "\"field\":\"" + field + "\"," + "\"sort\":\""
        + sort + "\"" + "}";
  }
  
}
