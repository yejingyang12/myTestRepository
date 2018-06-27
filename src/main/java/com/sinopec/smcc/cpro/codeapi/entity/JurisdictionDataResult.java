/**
 * @Title JurisdictionDataResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午8:55:38
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Title JurisdictionDataResult.java
 * @Package com.sinopec.smcc.cpro.codeapi.entity
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午8:55:38
 * @version V1.0
 */
public class JurisdictionDataResult {
  
  /**
   * 数据类型：0:无权限；1：全部权限；2：板块；3：企业；
   */
  private String resultType;
  private List<String> codeList;
  private List<String> nameList;
  private String orgId;
  
  public String getResultType() {
    return resultType;
  }
  public void setResultType(String resultType) {
    this.resultType = resultType;
  }
  public List<String> getCodeList() {
    return codeList;
  }
  public void setCodeList(List<String> codeList) {
    this.codeList = codeList;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public List<String> getNameList() {
    return nameList;
  }
  public void setNameList(List<String> nameList) {
    this.nameList = nameList;
  }
  
  public String toString(){
    return JSON.toJSONString(this);
  }
}
