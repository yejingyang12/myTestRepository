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

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSON;

/**
 * @Title HomeParam.java
 * @Package com.sinopec.smcc.cpro.home.entity
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:13:57
 * @version V1.0
 */
public class HomeParam {
  
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date recordDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date recordDateEnd;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date acceptDateBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date acceptDateEnd;
  private String fkCompanyCode;
  
  
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date rankTimeBegin;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date rankTimeEnd;
  
  private int pageSize = 10;// pageSize ，每一页显示多少
  private int currentPage = 1;// 当前页数
  private String field; //接收字段
  private String sort;//排序
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
  public String getFkCompanyCode() {
    return fkCompanyCode;
  }
  public void setFkCompanyCode(String fkCompanyCode) {
    this.fkCompanyCode = fkCompanyCode;
  }
  public Date getAcceptDateBegin() {
    return acceptDateBegin;
  }
  public void setAcceptDateBegin(Date acceptDateBegin) {
    this.acceptDateBegin = acceptDateBegin;
  }
  public Date getAcceptDateEnd() {
    return acceptDateEnd;
  }
  public void setAcceptDateEnd(Date acceptDateEnd) {
    this.acceptDateEnd = acceptDateEnd;
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
  public String toString(){
    return JSON.toJSONString(this);
  }
  
}
