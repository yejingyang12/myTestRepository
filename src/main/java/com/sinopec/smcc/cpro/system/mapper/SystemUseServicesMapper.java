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
}
