/**
 * @Title MainParam.java
 * @Package com.sinopec.smcc.cpro.main.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年5月25日下午1:24:27
 * @version V1.0
 */
package com.sinopec.smcc.cpro.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Title MainParam.java
 * @Package com.sinopec.smcc.cpro.main.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年5月29日上午9:53:56
 * @version V1.0
 */
public class MainParam {

  private String systemId;
  private String[] systemIds;
  private String fkCompanyCode;
  private String customizedSearch;
  private String acceptCompany;
  private String examOrg;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date auditTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date auditTimeEnd;
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
  private String systemCodeType;
  private String systemCode;
  private String systemName;
  private String plateType;
  private String sprankLevel;
  private String subordinateProvinces;
  private String gradingStatus;
  private String examineStatus;
  private String recordStatus;
  private String evaluationStatus;
  private String examinationStatus;
  private String companyId;
  private Integer status;
  private Integer fkExaminStatus;
  private String customFiltering;
  private Integer fkChangeMatter;
  private String changeReason;
  private String changeContent;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gradingBeginTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date gradingEndTime;
  private Integer gradingShapeType;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordsEndTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date recordsBeginTime;
  private Integer systemType;
  private String[] sprankLevelArray;
  private String[] plTypeArray;
  private String[] subordinateProvincesArray;
  private Long gradingBeginTimeStamp;//定级开始时间戳
  private Long gradingEndTimeStamp;//定级结束时间戳
  
  // pagesize ，每一页显示多少
  private int pageSize = 10;
  // 当前页数
  private int currentPage = 1;
  private String field; // 排序字段
  private String sort;// 排序方式

  private List<String> companyList;
  private List<String> plateList;
  
  private String companyName;
  private Integer[] statusArray;
  
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
  private List<Integer> examinStatus1;//待。。。审核
  private String examinStatusType;
  
  public String getGradingStatusType() {
    return gradingStatusType;
  }
  public void setGradingStatusType(String gradingStatusType) {
    this.gradingStatusType = gradingStatusType;
  }
  public String getFkExaminStatusType() {
    return fkExaminStatusType;
  }
  public void setFkExaminStatusType(String fkExaminStatusType) {
    this.fkExaminStatusType = fkExaminStatusType;
  }
  public String getRecordStatusType() {
    return recordStatusType;
  }
  public void setRecordStatusType(String recordStatusType) {
    this.recordStatusType = recordStatusType;
  }
  public String getEvaluationStatusType() {
    return evaluationStatusType;
  }
  public void setEvaluationStatusType(String evaluationStatusType) {
    this.evaluationStatusType = evaluationStatusType;
  }
  public String getExaminationStatusType() {
    return examinationStatusType;
  }
  public void setExaminationStatusType(String examinationStatusType) {
    this.examinationStatusType = examinationStatusType;
  }
  public String getExaminStatusType() {
    return examinStatusType;
  }
  public void setExaminStatusType(String examinStatusType) {
    this.examinStatusType = examinStatusType;
  }
  public List<Integer> getGradingStatus1() {
    return gradingStatus1;
  }
  public void setGradingStatus1(List<Integer> gradingStatus1) {
    this.gradingStatus1 = gradingStatus1;
  }
  public List<Integer> getFkExaminStatus1() {
    return fkExaminStatus1;
  }
  public void setFkExaminStatus1(List<Integer> fkExaminStatus1) {
    this.fkExaminStatus1 = fkExaminStatus1;
  }
  public List<Integer> getRecordStatus1() {
    return recordStatus1;
  }
  public void setRecordStatus1(List<Integer> recordStatus1) {
    this.recordStatus1 = recordStatus1;
  }
  public List<Integer> getEvaluationStatus1() {
    return evaluationStatus1;
  }
  public void setEvaluationStatus1(List<Integer> evaluationStatus1) {
    this.evaluationStatus1 = evaluationStatus1;
  }
  public List<Integer> getExaminationStatus1() {
    return examinationStatus1;
  }
  public void setExaminationStatus1(List<Integer> examinationStatus1) {
    this.examinationStatus1 = examinationStatus1;
  }
  public List<Integer> getExaminStatus1() {
    return examinStatus1;
  }
  public void setExaminStatus1(List<Integer> examinStatus1) {
    this.examinStatus1 = examinStatus1;
  }
  public void setStatusArray(Integer[] statusArray) {
    this.statusArray = statusArray;
  }
  public Integer[] getStatusArray() {
    return statusArray;
  }
  
  
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public String getCompanyName() {
    return companyName;
  }

  
  
  public String[] getPlTypeArray() {
		return plTypeArray;
	}
	public void setPlTypeArray(String[] plTypeArray) {
		this.plTypeArray = plTypeArray;
	}
	/**
   * @return the subordinateProvincesArray
   */
  public String[] getSubordinateProvincesArray() {
    return subordinateProvincesArray;
  }
  /**
   * @param subordinateProvincesArray the subordinateProvincesArray to set
   */
  public void setSubordinateProvincesArray(String[] subordinateProvincesArray) {
    this.subordinateProvincesArray = subordinateProvincesArray;
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
  /**
   * @return the sprankLevelArray
   */
  public String[] getSprankLevelArray() {
    return sprankLevelArray;
  }
  /**
   * @param sprankLevelArray the sprankLevelArray to set
   */
  public void setSprankLevelArray(String[] sprankLevelArray) {
    this.sprankLevelArray = sprankLevelArray;
  }
  /**
   * @return the systemType
   */
  public Integer getSystemType() {
    return systemType;
  }
  /**
   * @param systemType the systemType to set
   */
  public void setSystemType(Integer systemType) {
    this.systemType = systemType;
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
  /**
   * @return the recordsEndTime
   */
  public Date getRecordsEndTime() {
    return recordsEndTime;
  }

  /**
   * @param recordsEndTime the recordsEndTime to set
   */
  public void setRecordsEndTime(Date recordsEndTime) {
    this.recordsEndTime = recordsEndTime;
  }

  /**
   * @return the recordsBeginTime
   */
  public Date getRecordsBeginTime() {
    return recordsBeginTime;
  }

  /**
   * @param recordsBeginTime the recordsBeginTime to set
   */
  public void setRecordsBeginTime(Date recordsBeginTime) {
    this.recordsBeginTime = recordsBeginTime;
  }

  /**
   * @return the gradingShapeType
   */
  public Integer getGradingShapeType() {
    return gradingShapeType;
  }

  /**
   * @param gradingShapeType the gradingShapeType to set
   */
  public void setGradingShapeType(Integer gradingShapeType) {
    this.gradingShapeType = gradingShapeType;
  }

  /**
   * @return the gradingBeginTime
   */
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  
  public Date getGradingBeginTime() {
    return gradingBeginTime;
  }

  /**
   * @param gradingBeginTime the gradingBeginTime to set
   */
  public void setGradingBeginTime(Date gradingBeginTime) {
    this.gradingBeginTime = gradingBeginTime;
  }

  /**
   * @return the gradingEndTime
   */
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  public Date getGradingEndTime() {
    return gradingEndTime;
  }

  /**
   * @param gradingEndTime the gradingEndTime to set
   */
  public void setGradingEndTime(Date gradingEndTime) {
    this.gradingEndTime = gradingEndTime;
  }

  /**
   * @return the changeReason
   */
  public String getChangeReason() {
    return changeReason;
  }

  /**
   * @param changeReason the changeReason to set
   */
  public void setChangeReason(String changeReason) {
    this.changeReason = changeReason;
  }

  /**
   * @return the changeContent
   */
  public String getChangeContent() {
    return changeContent;
  }

  /**
   * @param changeContent the changeContent to set
   */
  public void setChangeContent(String changeContent) {
    this.changeContent = changeContent;
  }

  /**
   * @return the fkChangeMatter
   */
  public Integer getFkChangeMatter() {
    return fkChangeMatter;
  }

  /**
   * @param fkChangeMatter the fkChangeMatter to set
   */
  public void setFkChangeMatter(Integer fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
  }

  /**
   * @return the customFiltering
   */
  public String getCustomFiltering() {
    return customFiltering;
  }

  /**
   * @param customFiltering the customFiltering to set
   */
  public void setCustomFiltering(String customFiltering) {
    this.customFiltering = customFiltering;
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
   * @return the status
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(Integer status) {
    this.status = status;
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
   * @return the systemIds
   */
  public String[] getSystemIds() {
    return systemIds;
  }

  /**
   * @param systemIds the systemIds to set
   */
  public void setSystemIds(String[] systemIds) {
    this.systemIds = systemIds;
  }

  /**
   * @return the auditTimeBegin
   */
  public Date getAuditTimeBegin() {
    return auditTimeBegin;
  }

  /**
   * @param auditTimeBegin the auditTimeBegin to set
   */
  public void setAuditTimeBegin(Date auditTimeBegin) {
    this.auditTimeBegin = auditTimeBegin;
  }

  /**
   * @return the auditTimeEnd
   */
  public Date getAuditTimeEnd() {
    return auditTimeEnd;
  }

  /**
   * @param auditTimeEnd the auditTimeEnd to set
   */
  public void setAuditTimeEnd(Date auditTimeEnd) {
    this.auditTimeEnd = auditTimeEnd;
  }

  /**
   * @return the systemCodeType
   */
  public String getSystemCodeType() {
    return systemCodeType;
  }

  /**
   * @param systemCodeType the systemCodeType to set
   */
  public void setSystemCodeType(String systemCodeType) {
    this.systemCodeType = systemCodeType;
  }

  /**
   * @return the systemCode
   */
  public String getSystemCode() {
    return systemCode;
  }

  /**
   * @param systemCode the systemCode to set
   */
  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }

  /**
   * @return the systemName
   */
  public String getSystemName() {
    return systemName;
  }

  /**
   * @param systemName the systemName to set
   */
  public void setSystemName(String systemName) {
    this.systemName = systemName;
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
   * @return the sprankLevel
   */
  public String getSprankLevel() {
    return sprankLevel;
  }

  /**
   * @param sprankLevel the sprankLevel to set
   */
  public void setSprankLevel(String sprankLevel) {
    this.sprankLevel = sprankLevel;
  }

  /**
   * @return the subordinateProvinces
   */
  public String getSubordinateProvinces() {
    return subordinateProvinces;
  }

  /**
   * @param subordinateProvinces the subordinateProvinces to set
   */
  public void setSubordinateProvinces(String subordinateProvinces) {
    this.subordinateProvinces = subordinateProvinces;
  }

  /**
   * @return the gradingStatus
   */
  public String getGradingStatus() {
    return gradingStatus;
  }

  /**
   * @param gradingStatus the gradingStatus to set
   */
  public void setGradingStatus(String gradingStatus) {
    this.gradingStatus = gradingStatus;
  }

  /**
   * @return the examineStatus
   */
  public String getExamineStatus() {
    return examineStatus;
  }

  /**
   * @param examineStatus the examineStatus to set
   */
  public void setExamineStatus(String examineStatus) {
    this.examineStatus = examineStatus;
  }

  /**
   * @return the recordStatus
   */
  public String getRecordStatus() {
    return recordStatus;
  }

  /**
   * @param recordStatus the recordStatus to set
   */
  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }

  /**
   * @return the evaluationStatus
   */
  public String getEvaluationStatus() {
    return evaluationStatus;
  }

  /**
   * @param evaluationStatus the evaluationStatus to set
   */
  public void setEvaluationStatus(String evaluationStatus) {
    this.evaluationStatus = evaluationStatus;
  }

  /**
   * @return the examinationStatus
   */
  public String getExaminationStatus() {
    return examinationStatus;
  }

  /**
   * @param examinationStatus the examinationStatus to set
   */
  public void setExaminationStatus(String examinationStatus) {
    this.examinationStatus = examinationStatus;
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public String getFkCompanyCode() {
    return fkCompanyCode;
  }

  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }

  public String getCustomizedSearch() {
    return customizedSearch;
  }

  public void setCustomizedSearch(String customizedSearch) {
    this.customizedSearch = customizedSearch;
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

  public Date getRecordDateBegin() {
    return recordDateBegin;
  }

  public void setRecordDateBegin(Date recordDateBegin) {
    this.recordDateBegin = recordDateBegin;
  }

  public Date getRecordDateEnd() {
    return recordDateEnd;
  }

  public void setRecordDateEnd(Date recordDateEnd) {
    this.recordDateEnd = recordDateEnd;
  }

  public Date getExamTimeBegin() {
    return examTimeBegin;
  }

  public void setExamTimeBegin(Date examTimeBegin) {
    this.examTimeBegin = examTimeBegin;
  }

  public Date getExamTimeEnd() {
    return examTimeEnd;
  }

  public void setExamTimeEnd(Date examTimeEnd) {
    this.examTimeEnd = examTimeEnd;
  }

  public Date getRankTimeBegin() {
    return rankTimeBegin;
  }

  public void setRankTimeBegin(Date rankTimeBegin) {
    this.rankTimeBegin = rankTimeBegin;
  }

  public Date getRankTimeEnd() {
    return rankTimeEnd;
  }

  public void setRankTimeEnd(Date rankTimeEnd) {
    this.rankTimeEnd = rankTimeEnd;
  }

  public Date getInspectionDateBegin() {
    return inspectionDateBegin;
  }

  public void setInspectionDateBegin(Date inspectionDateBegin) {
    this.inspectionDateBegin = inspectionDateBegin;
  }

  public Date getInspectionDateEnd() {
    return inspectionDateEnd;
  }

  public void setInspectionDateEnd(Date inspectionDateEnd) {
    this.inspectionDateEnd = inspectionDateEnd;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String toString() {
    return JSON.toJSONString(this);
  }
}
