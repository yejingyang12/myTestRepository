/**
* @Title Utils.java
* @Package com.sinopec.smcc.cpro.tools
* @Description: TODO:
* @author eric
* @date 2018年5月25日下午8:05:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.tools;

import java.util.UUID;

/**
 * @Title Utils.java
 * @Package com.sinopec.smcc.cpro.tools
 * @Description: TODO:
 * @author eric
 * @date 2018年5月25日下午8:05:12
 * @version V1.0
 */
public class Utils {

  /**
   * 返回32位UUID，不包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:36
   * @return 去掉中划线的uuid
   */
  public static String getUuidFor32(){
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /**
   * 返回36位UUID，包含中划线
   * @author eric
   * @date 2017年6月16日下午5:38:11
   * @return uuid
   */
  public static String getUuidFor36(){
    return UUID.randomUUID().toString();
  }
  
}
