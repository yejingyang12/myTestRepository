/**
* Copyright 2018. 
* @Title ConvertFieldUtil.java
* @Package com.sinopec.smcc.cpro.selfexamination.utils
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午2:20:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.utils;

import com.sinopec.smcc.cpro.selfexamination.constant.SelfexaminationConstant;

/**
 * @Title ConvertFieldUtil.java
 * @Package com.sinopec.smcc.cpro.selfexamination.utils
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午2:20:12
 * @version V1.0
 */
public class ConvertFieldUtil {
  
  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年5月25日下午2:22:28
   * @return
   */
  public static String sortFielde(String field){
    if(SelfexaminationConstant.TABLE_FIELD_SELFEXAMINATION_CREATE_TIME.equalsIgnoreCase(field) ) {
      field = SelfexaminationConstant.TABLE_FIELD_SELFEXAMINATION_CREATE_TIME;
    }else {
      
    }
    return fieldStr(field);
  }
  
  private static String fieldStr(String str) {
    String o = str.replace("_", "");
    return o;
  }
}
