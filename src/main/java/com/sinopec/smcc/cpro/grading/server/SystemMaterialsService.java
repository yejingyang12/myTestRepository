/**
* Copyright 2018. 
* @Title SystemMaterialsService.java
* @Package com.sinopec.smcc.cpro.grading.server
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午2:51:30
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsResult;

/**
 * @Title SystemMaterialsService.java
 * @Package com.sinopec.smcc.cpro.grading.server
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:51:30
 * @version V1.0
 */
public interface SystemMaterialsService {

  /**
   * @Descrption 获取提交材料信息
   * @author yejingyang
   * @date 2018年6月11日下午2:52:14
   * @param systemMaterialsParam
   * @return
   */
  SystemMaterialsResult querySystemMaterials(SystemMaterialsParam systemMaterialsParam)
      throws BusinessException;

  /**
   * @Descrption 获取材料回显信息
   * @author yejingyang
   * @date 2018年6月11日下午2:59:37
   * @param systemMaterialsParam
   * @return
   */
  SystemMaterialsResult queryEditSystemMaterials(SystemMaterialsParam systemMaterialsParam)
      throws BusinessException;

  /**
   * @Descrption 保存材料信息
   * @author yejingyang
   * @date 2018年6月11日下午3:01:34
   * @param userName
   * @param systemMaterialsParam
   * @return
   */
  String saveSystemMaterials(String userName, SystemMaterialsParam systemMaterialsParam)
      throws BusinessException;

  /**
   * @Descrption 提交材料信息修改状态
   * @author yejingyang
   * @date 2018年6月11日下午3:02:50
   * @param userName
   * @param systemMaterialsParam
   * @return
   */
  String submitSystemMaterials(String userName, SystemMaterialsParam systemMaterialsParam)
      throws BusinessException;

}
