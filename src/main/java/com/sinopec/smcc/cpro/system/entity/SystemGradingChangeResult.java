/**
* 2018. 
* @Title SystemGradingChange.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年6月12日上午9:55:54
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title SystemGradingChange.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年6月12日上午9:55:54
 * @version V1.0
 */
public class SystemGradingChangeResult {

  private String systemId;
  private String fkChangeMatter;
  private String changeContent;
  private String changeReason;
  /**
   * @return the systemId
   */
  public String getSystemId() {
    return systemId;
  }
  /**
   * @param systemId the systemId to set
   */
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  /**
   * @return the fkChangeMatter
   */
  public String getFkChangeMatter() {
    return fkChangeMatter;
  }
  /**
   * @param fkChangeMatter the fkChangeMatter to set
   */
  public void setFkChangeMatter(String fkChangeMatter) {
    this.fkChangeMatter = fkChangeMatter;
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
  
  
}
