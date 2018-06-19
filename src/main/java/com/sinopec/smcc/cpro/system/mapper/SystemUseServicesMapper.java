/**
* 2018. 
* @Title SystemUseServicesMapper.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月26日上午9:56:53
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseResult;
import com.sinopec.smcc.cpro.system.entity.SystemUseServices;

/**
 * @Title SystemUseServicesMapper.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月26日上午9:56:53
 * @version V1.0
 */
public interface SystemUseServicesMapper {
  /**
   * 
   * @Descrption
   * @author hanxin
   * @date 2018年5月27日下午5:13:03
   * @param list
   */
  void insertSystemUseServicesBySystemUseServicesId(List<SystemUseServices> list);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月12日上午11:39:37
   * @param subUseList
   */
  void deleteSystemUseServicesBySystemUseServicesId(
      List<SystemUseServices> subUseList);


  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月14日下午9:31:52
   * @param systemParam
   */
  void deleteUse(SystemParam systemParam);

  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月14日下午9:40:36
   * @param subSystemList
   */
  void deleteSubUse(List<SystemResult> subSystemList);
}
