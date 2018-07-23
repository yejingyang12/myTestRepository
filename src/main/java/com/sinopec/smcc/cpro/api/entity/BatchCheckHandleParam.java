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

  private List<CheckParam> checkList;

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
