/**
 * @Title OrganizationApiResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:37:04
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;


/**
 * @Title OrganizationApiResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:37:04
 * @version V1.0
 */
public class OrganizationApiResult {

  private String value;//可填空，页面版块值（用不到）
  private String plateName;//板块名称
  private List<OrganizationApi> organization;//单位对象
  
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public String getPlateName() {
    return plateName;
  }
  public void setPlateName(String plateName) {
    this.plateName = plateName;
  }
  public List<OrganizationApi> getOrganization() {
    return organization;
  }
  public void setOrganization(List<OrganizationApi> organization) {
    this.organization = organization;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
