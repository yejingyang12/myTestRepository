/**
* 2018. 
* @Title SystemKeyProductsMapper.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author hanxin
* @date 2018年5月26日上午9:56:31
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.system.entity.SystemKeyProducts;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;

/**
 * @Title SystemKeyProductsMapper.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月26日上午9:56:31
 * @version V1.0
 */
public interface SystemKeyProductsMapper {
  /**
   * 
   * @Descrption
   * @author hanxin
   * @date 2018年5月27日下午5:13:12
   * @param list
   */
  void insertSystemKeyProductsBySystemKeyProductsId(List<SystemKeyProducts> list);



  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月14日下午9:31:13
   * @param systemParam
   */
  void deleteKey(SystemParam systemParam);



  /**
   * @Descrption
   * @author hanxin
   * @date 2018年6月14日下午9:40:26
   * @param subSystemList
   */
  void deleteSubKey(List<SystemResult> subSystemList);



  /**
   * @Descrption
   * @author Aran
   * @date 2018年6月21日下午6:14:01
   * @param systemParamAddList
   */
  void deleteSubKeyInfo(List<SystemParam> systemParamAddList);

}
