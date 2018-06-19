/**
* 2018. 
* @Title ConvertFieldUtil.java
* @Package com.sinopec.smcc.cpro.home.utils
* @Description: TODO:
* @author zhouyu
* @date 2018年6月6日下午12:18:51
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.utils;

import com.sinopec.smcc.cpro.home.constant.HomeConstant;

/**
 * @Title ConvertFieldUtil.java
 * @Package com.sinopec.smcc.cpro.home.utils
 * @Description: TODO:
 * @author zhouyu
 * @date 2018年6月6日下午12:18:51
 * @version V1.0
 */
public class ConvertFieldUtil {
  
  public static String sortField(String field) {
    if(HomeConstant.CREATE_TIME.equalsIgnoreCase(field) ) {
      field = HomeConstant.CREATE_TIME;
    }else {
      
    }
    return field;
  } 
}

