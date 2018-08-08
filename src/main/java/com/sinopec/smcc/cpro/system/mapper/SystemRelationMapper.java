/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title SystemRelation.java
* @Package com.sinopec.smcc.cpro.system.mapper
* @Description: TODO:
* @author Aran
* @date 2018年8月6日下午12:21:47
* @version V1.0
*/
package com.sinopec.smcc.cpro.system.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;

/**
 * @Title SystemRelation.java
 * @Package com.sinopec.smcc.cpro.system.mapper
 * @Description: TODO:
 * @author Aran
 * @date 2018年8月6日下午12:21:47
 * @version V1.0
 */
public interface SystemRelationMapper {

  /**
   * @Descrption
   * @author Aran
   * @date 2018年8月6日下午12:29:00
   * @param systemRelationParam
   * @return
   */
  List<SystemRelationResult> querySystemRelationInfo(
      SystemRelationParam systemRelationParam);

  /**
   * @Descrption  批量添加系统关联表信息
   * @author yejingyang
   * @date 2018年8月7日上午10:29:38
   * @param systemRelationList
   */
  void insertBatchSystemPelation(List<SystemRelationParam> systemRelationList);

  /**
   * @Descrption  根据systemId和standardizedName修改系统关联表信息
   * @author yejingyang
   * @date 2018年8月7日下午12:54:25
   * @param systemRelationTempParam
   */
  void updateSystemRelationBySystemIdAndStandardizedName(
      SystemRelationParam systemRelationTempParam);

  /**
   * @Descrption  根据systemId获取系统下所有未删除的关联信息列表
   * @author yejingyang
   * @date 2018年8月7日下午3:17:00
   * @param querySystemRelationListParam
   * @return
   */
  List<SystemRelationResult> selectSystemRelationListBySystemId(
      SystemRelationParam querySystemRelationListParam);

}
