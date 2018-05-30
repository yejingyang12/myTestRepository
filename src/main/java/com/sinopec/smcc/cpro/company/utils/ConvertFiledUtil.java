/**
* 2018. 
* @Title ConvertFiledUtil.java
* @Package com.sinopec.smcc.cpro.company.utils
* @Description: TODO:
* @author dongxu
* @date 2018年5月25日下午2:20:14
* @version V1.0
*/
package com.sinopec.smcc.cpro.company.utils;

import com.sinopec.smcc.cpro.company.constant.CompanyConstant;

/**
 * @Title ConvertFiledUtil.java
 * @Package com.sinopec.smcc.cpro.company.utils
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日下午2:20:14
 * @version V1.0
 */
public class ConvertFiledUtil {

  /**
   * @Descrption
   * @author dongxu
   * @date 2018年5月25日下午2:21:15
   * @param field
   * @return
   */
  public static String sortField(String field) {
    if(CompanyConstant.TABLE_FIELD_COMPANY_CREATE_TIME.equalsIgnoreCase(field) ) {
      field = CompanyConstant.TABLE_FIELD_COMPANY_CREATE_TIME;
    }else {
      
    }
    return field;
  }
  
}
