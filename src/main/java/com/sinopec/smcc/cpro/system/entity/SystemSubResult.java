/**
* 2018. 
* @Title System.java
* @Package com.sinopec.smcc.cpro.system.entity
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:42:52
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.entity;

/**
 * @Title System.java
 * @Package com.sinopec.smcc.cpro.system.entity
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:42:52
 * @version V1.0
 */
public class SystemSubResult {
  
  private String systemId;
  private String systemName;
  private String standardizedCode;
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
   * @return the standardizedCode
   */
  public String getStandardizedCode() {
    return standardizedCode;
  }
  /**
   * @param standardizedCode the standardizedCode to set
   */
  public void setStandardizedCode(String standardizedCode) {
    this.standardizedCode = standardizedCode;
  }
  public String getSystemId() {
    return systemId;
  }
  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }
  
  
}
