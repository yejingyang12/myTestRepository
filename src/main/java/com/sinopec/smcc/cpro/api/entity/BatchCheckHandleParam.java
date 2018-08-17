/**
 * @Title BatchCheckHandleParam.java
 * @Package com.sinopec.smcc.cpro.api.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年7月20日上午10:03:48
 * @version V1.0
 */
package com.sinopec.smcc.cpro.api.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sinopec.smcc.cpro.review.entity.CheckParam;

/**
 * @Title BatchCheckHandleParam.java
 * @Package com.sinopec.smcc.cpro.api.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年7月20日上午10:03:48
 * @version V1.0
 */
public class BatchCheckHandleParam {

  private String userId;
  private String userName;
  private String opinion;
  private Integer result;
  private List<CheckParam> checkList;

  
  /**
   * @return the opinion
   */
  public String getOpinion() {
    return opinion;
  }
  /**
   * @param opinion the opinion to set
   */
  public void setOpinion(String opinion) {
    this.opinion = opinion;
  }
  /**
   * @return the result
   */
  public Integer getResult() {
    return result;
  }
  /**
   * @param result the result to set
   */
  public void setResult(Integer result) {
    this.result = result;
  }
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }
  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public List<CheckParam> getCheckList() {
    return checkList;
  }
  public void setCheckList(List<CheckParam> checkList) {
    this.checkList = checkList;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
