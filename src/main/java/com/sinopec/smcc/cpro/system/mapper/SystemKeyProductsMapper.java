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
}
