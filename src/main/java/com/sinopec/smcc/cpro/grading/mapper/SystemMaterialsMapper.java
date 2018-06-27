/**
* Copyright 2018. 
* @Title SystemMaterialsMapper.java
* @Package com.sinopec.smcc.cpro.grading.mapper
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午2:56:27
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.mapper;

import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsBeanResult;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsResult;

/**
 * @Title SystemMaterialsMapper.java
 * @Package com.sinopec.smcc.cpro.grading.mapper
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:56:27
 * @version V1.0
 */
public interface SystemMaterialsMapper {

  /**
   * @Descrption 获取提交材料信息
   * @author yejingyang
   * @date 2018年6月11日下午3:05:52
   * @param systemMaterialsParam
   * @return
   */
  SystemMaterialsResult selectSystemMaterials(SystemMaterialsParam systemMaterialsParam);

  /**
   * @Descrption 添加或修改材料表
   * @author yejingyang
   * @date 2018年6月11日下午7:18:14
   * @param systemMaterialsParam
   */
  void insertSystemMaterials(SystemMaterialsParam systemMaterialsParam);

  /**
   * @Descrption  获取回显提交材料信息
   * @author yejingyang
   * @date 2018年6月22日下午3:50:11
   * @param systemMaterialsParam
   * @return
   */
  SystemMaterialsBeanResult selectSystemMaterialsBeanResultBySystemId(
      SystemMaterialsParam systemMaterialsParam);

  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年6月25日下午1:31:27
   * @param systemMaterialsBeanParam
   */
  void insertSystemMaterialsBean(SystemMaterialsBeanParam systemMaterialsBeanParam);

}
