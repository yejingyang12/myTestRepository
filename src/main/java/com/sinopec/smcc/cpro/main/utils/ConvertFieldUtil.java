/**
  * @Title ConvertFieldUtil.java
  * @Package com.sinopec.smcc.cpro.main.utils
  * @Description: TODO:
  * @author dongxu
  * @date 2018年5月25日下午2:19:44
  * @version V1.0
  */
package com.sinopec.smcc.cpro.main.utils;

import com.sinopec.smcc.cpro.main.constant.MainConstant;

/**
 * @Title ConvertFieldUtil.java
 * @Package com.sinopec.smcc.cpro.main.utils
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日下午2:19:44
 * @version V1.0
 */
public class ConvertFieldUtil {

  /**
   * @Descrption 转换排序字段
   * @author dongxu
   * @date 2018年5月25日下午2:20:00
   * @param field
   * @return
   */
  public static String sortField(String field) {
    if(MainConstant.TABLE_FIELD_GRADING_CREATE_TIME.equalsIgnoreCase(field) ) {
      field = MainConstant.TABLE_FIELD_GRADING_CREATE_TIME;
    }else {
      
    }
    return field;
  }

}
