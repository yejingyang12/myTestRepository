/**
* 2018. 
* @Title ConvertFieldUtil.java
* @Package com.sinopec.smcc.cpro.system.util
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日下午2:21:22
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.util;

import com.sinopec.smcc.cpro.system.constant.SystemConstant;

/**
 * @Title ConvertFieldUtil.java
 * @Package com.sinopec.smcc.cpro.system.util
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日下午2:21:22
 * @version V1.0
 */
public class ConvertFieldUtil {
	/**
	 * 
	 * @Descrption	
	 * @author hanxin
	 * @date 2018年5月25日下午2:24:43
	 * @param field
	 * @return
	 */
  public static String sortField(String field) {
    if(SystemConstant.TABLE_FIELD_SYSTEM_CREATE_TIME.equalsIgnoreCase(field) ) {
      field = SystemConstant.TABLE_FIELD_SYSTEM_CREATE_TIME;
    }else {
    }
    return field;
  }
}
