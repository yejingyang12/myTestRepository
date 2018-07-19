/**
* 2018. 
* @Title GradingApiResult.java
* @Package com.sinopec.smcc.cpro.api.entity
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:14:54
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.entity;

/**
 * @Title GradingApiResult.java
 * @Package com.sinopec.smcc.cpro.api.entity
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:14:54
 * @version V1.0
 */
public class GradingApiResult {
  private String bizSprankLevel; //业务信息级别
  private String bizSystemLevel; //系统服务级别
  private String spranklevel;//信息系统安全保护等级
  private Integer gradingStatus;//登保定级状态
  /**
   * @return the bizSprankLevel
   */
  public String getBizSprankLevel() {
    return bizSprankLevel;
  }
  /**
   * @param bizSprankLevel the bizSprankLevel to set
   */
  public void setBizSprankLevel(String bizSprankLevel) {
    this.bizSprankLevel = bizSprankLevel;
  }
  /**
   * @return the bizSystemLevel
   */
  public String getBizSystemLevel() {
    return bizSystemLevel;
  }
  /**
   * @param bizSystemLevel the bizSystemLevel to set
   */
  public void setBizSystemLevel(String bizSystemLevel) {
    this.bizSystemLevel = bizSystemLevel;
  }
  /**
   * @return the spranklevel
   */
  public String getSpranklevel() {
    return spranklevel;
  }
  /**
   * @param spranklevel the spranklevel to set
   */
  public void setSpranklevel(String spranklevel) {
    this.spranklevel = spranklevel;
  }
  /**
   * @return the gradingStatus
   */
  public Integer getGradingStatus() {
    return gradingStatus;
  }
  /**
   * @param gradingStatus the gradingStatus to set
   */
  public void setGradingStatus(Integer gradingStatus) {
    this.gradingStatus = gradingStatus;
  }
  
  
}
