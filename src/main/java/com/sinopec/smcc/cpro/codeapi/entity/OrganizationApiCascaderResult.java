/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title OrganizationApiCascaderResult.java
* @Package com.sinopec.smcc.cpro.codeapi.entity
* @Description: TODO:
* @author Aran
* @date 2018年6月9日下午5:11:59
* @version V1.0
*/
package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Title OrganizationApiCascaderResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author Aran
 * @date 2018年6月9日下午5:11:59
 * @version V1.0
 */
public class OrganizationApiCascaderResult {

  private String value;//可填空，页面版块值（用不到）
  private String label;//板块名称
  private List<OrganizationApiCascader> children;//单位对象
  
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


  public List<OrganizationApiCascader> getChildren() {
    return children;
  }


  public void setChildren(List<OrganizationApiCascader> children) {
    this.children = children;
  }


  public String toString(){
    return JSON.toJSONString(this);
  }
}
