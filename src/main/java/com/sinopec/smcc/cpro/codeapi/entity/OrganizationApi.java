/**
 * @Title OrganizationApi.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:33:29
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title OrganizationApi.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:33:29
 * @version V1.0
 */
public class OrganizationApi {

  private String orgCode;
  private String orgName;
  public String getOrgCode() {
    return orgCode;
  }
  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
