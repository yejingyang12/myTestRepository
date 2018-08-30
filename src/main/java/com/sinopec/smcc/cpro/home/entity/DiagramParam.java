/**
* 2018. 
* @Title HomeParam.java
* @Package com.sinopec.smcc.cpro.home.entity
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:13:57
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.entity;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title HomeParam.java
 * @Package com.sinopec.smcc.cpro.home.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:13:57
 * @version V1.0
 */
public class DiagramParam {
  
  /*
   * 查询图表系统：
   *            1：定级系统：
   *            2：标准化网站系统：
   */
  private Integer systemType;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateEnd;
  
  private List<String> companyList;
  private List<String> plateList;
  
  /*
   * 类型:
   *      
   */
  private String codeName;
  private String year;
  private String userId;
  
  //连动查询条件
  private String systemName;
  private Integer appIsInternet;
  private String companyName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gradingBeginTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gradingEndTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date auditTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date auditTimeEnd;
  private String acceptCompany;
  private String examOrg;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordDateEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date examTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date examTimeEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rankTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date rankTimeEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date inspectionDateEnd;
  private String[] plTypeArray;
  private Integer[] statusArray;
  private String[] sprankLevelArray;
  private String[] subordinateProvincesArray;
  private String customFiltering;
  private Long gradingBeginTimeStamp;//定级开始时间戳
  private Long gradingEndTimeStamp;//定级结束时间戳
  private Long auditTimeBeginTimeStamp;//填报开始时间戳
  private Long auditTimeEndTimeStamp;//填报结束时间戳
  private Long recordDateBeginTimeStamp;//备案开始时间戳
  private Long recordDateEndTimeStamp;//备案结束时间戳
  private Long examTimeBeginTimeStamp;//测评开始时间戳
  private Long examTimeEndTimeStamp;//测评结束时间戳
  private Long rankTimeBeginTimeStamp;//定级开始时间戳
  private Long rankTimeEndTimeStamp;//定级结束时间戳
  private Long inspectionDateBeginTimeStamp;//自查开始时间戳
  private Long inspectionDateEndTimeStamp;//自查结束时间戳
  
  private String systemCodeType;
  private String gradingStatus;
  private Integer fkExaminStatus;
  private String recordStatus;
  private String evaluationStatus;
  private String examinationStatus;
  
  private List<Integer> gradingStatus1;//定级
  private String gradingStatusType;
  private List<Integer> fkExaminStatus1;//审核
  private String fkExaminStatusType;
  private List<Integer> recordStatus1;//备案
  private String recordStatusType;
  private List<Integer> evaluationStatus1;//测评
  private String evaluationStatusType;
  private List<Integer> examinationStatus1;//自查
  private String examinationStatusType;
  private List<Integer> examinStatus1;//待审核
  private String examinStatusType;
  
  private String jurBind;
  
  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }
  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  /**
   * @return the companyList
   */
  public List<String> getCompanyList() {
    return companyList;
  }
  /**
   * @param companyList the companyList to set
   */
  public void setCompanyList(List<String> companyList) {
    this.companyList = companyList;
  }
  /**
   * @return the plateList
   */
  public List<String> getPlateList() {
    return plateList;
  }
  /**
   * @param plateList the plateList to set
   */
  public void setPlateList(List<String> plateList) {
    this.plateList = plateList;
  }
  public String getYear() {
    return year;
  }
  public void setYear(String year) {
    this.year = year;
  }
  public Integer getSystemType() {
    return systemType;
  }
  public void setSystemType(Integer systemType) {
    this.systemType = systemType;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getDateBegin() {
    return dateBegin;
  }
  public void setDateBegin(Date dateBegin) {
    this.dateBegin = dateBegin;
  }

  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getDateEnd() {
    return dateEnd;
  }
  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }

  public String getSystemName() {
    return systemName;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public Integer getAppIsInternet() {
    return appIsInternet;
  }
  public void setAppIsInternet(Integer appIsInternet) {
    this.appIsInternet = appIsInternet;
  }
  public String getCompanyName() {
    return companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getGradingBeginTime() {
    return gradingBeginTime;
  }
  public void setGradingBeginTime(Date gradingBeginTime) {
    this.gradingBeginTime = gradingBeginTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getGradingEndTime() {
    return gradingEndTime;
  }
  public void setGradingEndTime(Date gradingEndTime) {
    this.gradingEndTime = gradingEndTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getAuditTimeBegin() {
    return auditTimeBegin;
  }
  public void setAuditTimeBegin(Date auditTimeBegin) {
    this.auditTimeBegin = auditTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getAuditTimeEnd() {
    return auditTimeEnd;
  }
  public void setAuditTimeEnd(Date auditTimeEnd) {
    this.auditTimeEnd = auditTimeEnd;
  }
  public String getAcceptCompany() {
    return acceptCompany;
  }
  public void setAcceptCompany(String acceptCompany) {
    this.acceptCompany = acceptCompany;
  }
  public String getExamOrg() {
    return examOrg;
  }
  public void setExamOrg(String examOrg) {
    this.examOrg = examOrg;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRecordDateBegin() {
    return recordDateBegin;
  }
  public void setRecordDateBegin(Date recordDateBegin) {
    this.recordDateBegin = recordDateBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRecordDateEnd() {
    return recordDateEnd;
  }
  public void setRecordDateEnd(Date recordDateEnd) {
    this.recordDateEnd = recordDateEnd;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getExamTimeBegin() {
    return examTimeBegin;
  }
  public void setExamTimeBegin(Date examTimeBegin) {
    this.examTimeBegin = examTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getExamTimeEnd() {
    return examTimeEnd;
  }
  public void setExamTimeEnd(Date examTimeEnd) {
    this.examTimeEnd = examTimeEnd;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRankTimeBegin() {
    return rankTimeBegin;
  }
  public void setRankTimeBegin(Date rankTimeBegin) {
    this.rankTimeBegin = rankTimeBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getRankTimeEnd() {
    return rankTimeEnd;
  }
  public void setRankTimeEnd(Date rankTimeEnd) {
    this.rankTimeEnd = rankTimeEnd;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDateBegin() {
    return inspectionDateBegin;
  }
  public void setInspectionDateBegin(Date inspectionDateBegin) {
    this.inspectionDateBegin = inspectionDateBegin;
  }
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getInspectionDateEnd() {
    return inspectionDateEnd;
  }
  public void setInspectionDateEnd(Date inspectionDateEnd) {
    this.inspectionDateEnd = inspectionDateEnd;
  }
  public String[] getPlTypeArray() {
    return plTypeArray;
  }
  public void setPlTypeArray(String[] plTypeArray) {
    this.plTypeArray = plTypeArray;
  }
  public Integer[] getStatusArray() {
    return statusArray;
  }
  public void setStatusArray(Integer[] statusArray) {
    this.statusArray = statusArray;
  }
  public String[] getSprankLevelArray() {
    return sprankLevelArray;
  }
  public void setSprankLevelArray(String[] sprankLevelArray) {
    this.sprankLevelArray = sprankLevelArray;
  }
  public String[] getSubordinateProvincesArray() {
    return subordinateProvincesArray;
  }
  public void setSubordinateProvincesArray(String[] subordinateProvincesArray) {
    this.subordinateProvincesArray = subordinateProvincesArray;
  }
  public String getCustomFiltering() {
    return customFiltering;
  }
  public void setCustomFiltering(String customFiltering) {
    this.customFiltering = customFiltering;
  }
  public Long getGradingBeginTimeStamp() {
    return gradingBeginTimeStamp;
  }
  public void setGradingBeginTimeStamp(Long gradingBeginTimeStamp) {
    if(gradingBeginTimeStamp != null){
      this.gradingBeginTime = new Date(gradingBeginTimeStamp);
    }
    this.gradingBeginTimeStamp = gradingBeginTimeStamp;
  }
  public Long getGradingEndTimeStamp() {
    return gradingEndTimeStamp;
  }
  public void setGradingEndTimeStamp(Long gradingEndTimeStamp) {
    if(gradingEndTimeStamp != null){
      this.gradingEndTime = new Date(gradingEndTimeStamp);
    }
    this.gradingEndTimeStamp = gradingEndTimeStamp;
  }
  public Long getAuditTimeBeginTimeStamp() {
    return auditTimeBeginTimeStamp;
  }
  public void setAuditTimeBeginTimeStamp(Long auditTimeBeginTimeStamp) {
    if(auditTimeBeginTimeStamp != null){
      this.auditTimeBegin = new Date(auditTimeBeginTimeStamp);
    }
    this.auditTimeBeginTimeStamp = auditTimeBeginTimeStamp;
  }
  public Long getAuditTimeEndTimeStamp() {
    return auditTimeEndTimeStamp;
  }
  public void setAuditTimeEndTimeStamp(Long auditTimeEndTimeStamp) {
    if(auditTimeEndTimeStamp != null){
      this.auditTimeEnd = new Date(auditTimeEndTimeStamp);
    }
    this.auditTimeEndTimeStamp = auditTimeEndTimeStamp;
  }
  public Long getRecordDateBeginTimeStamp() {
    return recordDateBeginTimeStamp;
  }
  public void setRecordDateBeginTimeStamp(Long recordDateBeginTimeStamp) {
    if(recordDateBeginTimeStamp != null){
      this.recordDateBegin = new Date(recordDateBeginTimeStamp);
    }
    this.recordDateBeginTimeStamp = recordDateBeginTimeStamp;
  }
  public Long getRecordDateEndTimeStamp() {
    return recordDateEndTimeStamp;
  }
  public void setRecordDateEndTimeStamp(Long recordDateEndTimeStamp) {
    if(recordDateEndTimeStamp != null){
      this.recordDateEnd = new Date(recordDateEndTimeStamp);
    }
    this.recordDateEndTimeStamp = recordDateEndTimeStamp;
  }
  public Long getExamTimeBeginTimeStamp() {
    return examTimeBeginTimeStamp;
  }
  public void setExamTimeBeginTimeStamp(Long examTimeBeginTimeStamp) {
    if(examTimeBeginTimeStamp != null){
      this.examTimeBegin = new Date(examTimeBeginTimeStamp);
    }
    this.examTimeBeginTimeStamp = examTimeBeginTimeStamp;
  }
  public Long getExamTimeEndTimeStamp() {
    return examTimeEndTimeStamp;
  }
  public void setExamTimeEndTimeStamp(Long examTimeEndTimeStamp) {
    if(examTimeEndTimeStamp != null){
      this.examTimeEnd = new Date(examTimeEndTimeStamp);
    }
    this.examTimeEndTimeStamp = examTimeEndTimeStamp;
  }
  public Long getRankTimeBeginTimeStamp() {
    return rankTimeBeginTimeStamp;
  }
  public void setRankTimeBeginTimeStamp(Long rankTimeBeginTimeStamp) {
    if(rankTimeBeginTimeStamp != null){
      this.rankTimeBegin = new Date(rankTimeBeginTimeStamp);
    }
    this.rankTimeBeginTimeStamp = rankTimeBeginTimeStamp;
  }
  public Long getRankTimeEndTimeStamp() {
    return rankTimeEndTimeStamp;
  }
  public void setRankTimeEndTimeStamp(Long rankTimeEndTimeStamp) {
    if(rankTimeEndTimeStamp != null){
      this.rankTimeEnd = new Date(rankTimeEndTimeStamp);
    }
    this.rankTimeEndTimeStamp = rankTimeEndTimeStamp;
  }
  public Long getInspectionDateBeginTimeStamp() {
    return inspectionDateBeginTimeStamp;
  }
  public void setInspectionDateBeginTimeStamp(Long inspectionDateBeginTimeStamp) {
    if(inspectionDateBeginTimeStamp != null){
      this.inspectionDateBegin = new Date(inspectionDateBeginTimeStamp);
    }
    this.inspectionDateBeginTimeStamp = inspectionDateBeginTimeStamp;
  }
  public Long getInspectionDateEndTimeStamp() {
    return inspectionDateEndTimeStamp;
  }
  public void setInspectionDateEndTimeStamp(Long inspectionDateEndTimeStamp) {
    if(inspectionDateEndTimeStamp != null){
      this.inspectionDateEnd = new Date(inspectionDateEndTimeStamp);
    }
    this.inspectionDateEndTimeStamp = inspectionDateEndTimeStamp;
  }
  public String getSystemCodeType() {
    return systemCodeType;
  }
  public void setSystemCodeType(String systemCodeType) {
    this.systemCodeType = systemCodeType;
  }
  public String getGradingStatus() {
    return gradingStatus;
  }
  public void setGradingStatus(String gradingStatus) {
    this.gradingStatus = gradingStatus;
  }
  public Integer getFkExaminStatus() {
    return fkExaminStatus;
  }
  public void setFkExaminStatus(Integer fkExaminStatus) {
    this.fkExaminStatus = fkExaminStatus;
  }
  public String getRecordStatus() {
    return recordStatus;
  }
  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }
  public String getEvaluationStatus() {
    return evaluationStatus;
  }
  public void setEvaluationStatus(String evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
  }
  public String getExaminationStatus() {
    return examinationStatus;
  }
  public void setExaminationStatus(String examinationStatus) {
    this.examinationStatus = examinationStatus;
  }
  public List<Integer> getGradingStatus1() {
    return gradingStatus1;
  }
  public void setGradingStatus1(List<Integer> gradingStatus1) {
    this.gradingStatus1 = gradingStatus1;
  }
  public String getGradingStatusType() {
    return gradingStatusType;
  }
  public void setGradingStatusType(String gradingStatusType) {
    this.gradingStatusType = gradingStatusType;
  }
  public List<Integer> getFkExaminStatus1() {
    return fkExaminStatus1;
  }
  public void setFkExaminStatus1(List<Integer> fkExaminStatus1) {
    this.fkExaminStatus1 = fkExaminStatus1;
  }
  public String getFkExaminStatusType() {
    return fkExaminStatusType;
  }
  public void setFkExaminStatusType(String fkExaminStatusType) {
    this.fkExaminStatusType = fkExaminStatusType;
  }
  public List<Integer> getRecordStatus1() {
    return recordStatus1;
  }
  public void setRecordStatus1(List<Integer> recordStatus1) {
    this.recordStatus1 = recordStatus1;
  }
  public String getRecordStatusType() {
    return recordStatusType;
  }
  public void setRecordStatusType(String recordStatusType) {
    this.recordStatusType = recordStatusType;
  }
  public List<Integer> getEvaluationStatus1() {
    return evaluationStatus1;
  }
  public void setEvaluationStatus1(List<Integer> evaluationStatus1) {
    this.evaluationStatus1 = evaluationStatus1;
  }
  public String getEvaluationStatusType() {
    return evaluationStatusType;
  }
  public void setEvaluationStatusType(String evaluationStatusType) {
    this.evaluationStatusType = evaluationStatusType;
  }
  public List<Integer> getExaminationStatus1() {
    return examinationStatus1;
  }
  public void setExaminationStatus1(List<Integer> examinationStatus1) {
    this.examinationStatus1 = examinationStatus1;
  }
  public String getExaminationStatusType() {
    return examinationStatusType;
  }
  public void setExaminationStatusType(String examinationStatusType) {
    this.examinationStatusType = examinationStatusType;
  }
  public List<Integer> getExaminStatus1() {
    return examinStatus1;
  }
  public void setExaminStatus1(List<Integer> examinStatus1) {
    this.examinStatus1 = examinStatus1;
  }
  public String getExaminStatusType() {
    return examinStatusType;
  }
  public void setExaminStatusType(String examinStatusType) {
    this.examinStatusType = examinStatusType;
  }
  public String getJurBind() {
    return jurBind;
  }
  public void setJurBind(String jurBind) {
    this.jurBind = jurBind;
  }
  public String toString(){
    return JSON.toJSONString(this);
  }
  
}
