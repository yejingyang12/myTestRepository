/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title OrganizationApiCascader.java
* @Package com.sinopec.smcc.cpro.codeapi.entity
* @Description: TODO:
* @author Aran
* @date 2018年6月9日下午5:13:21
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.entity;

import com.alibaba.fastjson.JSON;

/**
 * @Title OrganizationApiCascader.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月9日下午5:13:21
 * @version V1.0
 */
public class OrganizationApiCascader {
  
  private String value;//单位code
  private String label;//单位名称
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
