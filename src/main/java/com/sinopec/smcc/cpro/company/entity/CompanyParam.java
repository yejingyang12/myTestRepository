/**
* 2018. 
* @Title CompanyParam.java
* @Package com.sinopec.smcc.cpro.company.entity
* @Description: TODO:
* @author dongxu
* @date 2018年5月31日下午9:37:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
/**
   * @Title CompanyParam.java
   * @Package com.sinopec.smcc.cpro.company.entity
   * @Description: TODO:
   * @author dongxu
   * @date 2018年5月31日下午9:37:15
   * @version V1.0
   */
public class CompanyParam {
  
  private String companyId;
  private String fkIndustryCategory;
  private String fkAffiliation;
  private String fkCompanyType;
  private String fkPlateType;
  private String fkSubordinatePro;
  private String ldContactEmail;
  private String companyName;
  private String postalCode;
  private String compPrincipalWorkTel;
  private String createUserName;
  private String remark;
  private String ldContactName;
  private String rDepartment;
  private String compPrincipalEm;
  private Integer deleteStatus;
  private String ldContactPhone;
  private Integer administrativeNum;
  private String compPrincipalPhone;
  private String companyCode;
  private String compPrincipalPost;
  private String ldContactPost;
  private String gpReportingComp;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private String updateUserName;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;
  private String companyAddress;
  private String ldContactWorkTel;
  private String compPrincipalName;
  private String[] companyIds;
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序

  private String systemId;
  private String changeType;
  
  private List<String> companyList;
  private List<String> plateList;
  
  private Integer jurisdictionDel;
  
  private String fkNodeChangeReason;//原因
  private String fkNodeChangeContent;//内容
  private String fkNodeSysChangeMatter;//事项
  
  
  public String getFkNodeChangeReason() {
    return fkNodeChangeReason;
  }
  public void setFkNodeChangeReason(String fkNodeChangeReason) {
    this.fkNodeChangeReason = fkNodeChangeReason;
  }
  public String getFkNodeChangeContent() {
    return fkNodeChangeContent;
  }
  public void setFkNodeChangeContent(String fkNodeChangeContent) {
    this.fkNodeChangeContent = fkNodeChangeContent;
  }
  public String getFkNodeSysChangeMatter() {
    return fkNodeSysChangeMatter;
  }
  public void setFkNodeSysChangeMatter(String fkNodeSysChangeMatter) {
    this.fkNodeSysChangeMatter = fkNodeSysChangeMatter;
  }
  /**
   * @return the jurisdictionDel
   */
  public Integer getJurisdictionDel() {
    return jurisdictionDel;
  }
  /**
   * @param jurisdictionDel the jurisdictionDel to set
   */
  public void setJurisdictionDel(Integer jurisdictionDel) {
    this.jurisdictionDel = jurisdictionDel;
  }
  public String getUpdateUserName() {
    return updateUserName;
  }
  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
  }
  public List<String> getCompanyList() {
    return companyList;
  }
  public void setCompanyList(List<String> companyList) {
    this.companyList = companyList;
  }
  public List<String> getPlateList() {
    return plateList;
  }
  public void setPlateList(List<String> plateList) {
    this.plateList = plateList;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  public String getChangeType() {
    return changeType;
  }
  public void setChangeType(String changeType) {
    this.changeType = changeType;
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
   * @return the compPrincipalEm
   */
  public String getCompPrincipalEm() {
    return compPrincipalEm;
  }


  /**
   * @param compPrincipalEm the compPrincipalEm to set
   */
  public void setCompPrincipalEm(String compPrincipalEm) {
    this.compPrincipalEm = compPrincipalEm;
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
   * @return the companyIds
   */
  public String[] getCompanyIds() {
    return companyIds;
  }


  /**
   * @param companyIds the companyIds to set
   */
  public void setCompanyIds(String[] companyIds) {
    this.companyIds = companyIds;
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
}
