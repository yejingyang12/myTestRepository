/**
* @Title ConvertFiledUtil.java
* @Package com.sinopec.smcc.cpro.evaluation.utils
* @Description: TODO:
* @author Aran
* @date 2018年5月25日下午10:26:44
* @version V1.0
*/
package com.sinopec.smcc.cpro.evaluation.utils;

import com.sinopec.smcc.cpro.evaluation.constant.EvaluationConstant;

/**
 * @Title ConvertFiledUtil.java
 * @Package com.sinopec.smcc.cpro.evaluation.utils
 * @Description: TODO:
 * @author Aran
 * @date 2018年5月25日下午10:26:44
 * @version V1.0
 */
public class ConvertFiledUtil {

  /**
   * @Descrption
   * @author Aran
   * @date 2018年5月25日下午2:21:15
   * @param field
   * @return
   */
  public static String sortField(String field) {
    if(EvaluationConstant.TABLE_FIELD_EVALUATION_CREATE_TIME.equalsIgnoreCase(field) ) {
      field = EvaluationConstant.TABLE_FIELD_EVALUATION_CREATE_TIME;
    }else {
      
    }
    return field;
  }
  
}
