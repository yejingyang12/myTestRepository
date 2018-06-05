/**
* 2018. 
* @Title SystemService.java
* @Package com.sinopec.smcc.cpro.system.server
* @Description: TODO:
* @author hanxin
* @date 2018年5月25日上午11:47:32
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.system.entity.SystemListResult;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;

/**
 * @Title SystemService.java
 * @Package com.sinopec.smcc.cpro.system.server
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月25日上午11:47:32
 * @version V1.0
 */
public interface SystemService {

  /**
   * 	响应系统列表数据
   * @author hanxin
   * @date 2018年5月25日下午2:46:48
   * @param systemParam
   * @return
   */
  PageInfo<SystemListResult> querySystemList(SystemParam systemParam);
  
  /**
   * 	添加或修改系统信息
   * @author hanxin
   * @date 2018年5月25日下午6:36:47
   * @param systemListResult
   * @return
   * @throws BusinessException 
   */
  String saveSystem(SystemParam systemParam) throws BusinessException;
  
  /**
   *  查询系统代码信息
   * @author dongxu
   * @date 2018年5月27日下午11:16:17
   * @param systemCodeParam
   * @return
   * @throws BusinessException
   */
//  List<SystemCodeListResult> querySystemCodeList(SystemCodeParam systemCodeParam) throws BusinessException;
  
  /**
   * 	查询系统信息详情
   * @author hanxin
   * @date 2018年5月28日下午3:49:25
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  SystemResult queryDetailsSystem(SystemParam systemParam) throws BusinessException;
  
  /**
   * 修改系统信息查询详情
   * @author hanxin
   * @date 2018年5月28日下午5:38:30
   * @param systemParam
   * @return
   * @throws BusinessException 
   */
  SystemResult queryEditSystem(SystemParam systemParam) throws BusinessException;

  /**
   * @Descrption 修改系统状态
   * @author dongxu
   * @date 2018年5月30日上午10:26:08
   * @param systemParam
   * @return
   */
  void editSystemStatusBySystemId(SystemParam systemParam) throws BusinessException ;

}
