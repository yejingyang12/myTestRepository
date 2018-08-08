/**
* Copyright 2018. 
* @Title SelfexaminationMapper.java
* @Package com.sinopec.smcc.cpro.selfexamination.mapper
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午2:45:24
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.mapper;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;

/**
 * @Title SelfexaminationMapper.java
 * @Package com.sinopec.smcc.cpro.selfexamination.mapper
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午2:45:24
 * @version V1.0
 */
public interface SelfexaminationMapper {

  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年5月25日下午2:45:39
   * @param selfexaminationParam
   * @return
   */
  List<SelfexaminationListResult> selectAllBySelfexaminationParam(
      SelfexaminationParam selfexaminationParam) throws BusinessException;
  
  /**
   * @Descrption添加或修改自查
   * @author yejingyang
   * @date 2018年5月28日下午1:46:22
   * @param selfexaminationParam
   * @throws BusinessException
   */
  void insertOrUpdateSelfexamination(SelfexaminationParam selfexaminationParam) 
      throws BusinessException;

  /**
   * @Descrption 通过自查ID查询修改回显信息
   * @author dongxu
   * @date 2018年6月2日下午11:23:40
   * @param selfexaminationParam
   * @return
   */
  SelfexaminationResult selectSingleBySelfexaminationId(SelfexaminationParam selfexaminationParam) 
      throws BusinessException;

  /**
   * @Descrption  通过SelfexaminationId修改自查的删除状态
   * @author yejingyang
   * @date 2018年6月6日上午10:12:53
   * @param selfexaminationParam
   */
  void updateSelfexaminationDeleteStatusBySelfexaminationId(
      SelfexaminationParam selfexaminationParam) throws BusinessException;
  
  /**
   * @Descrption 通过系统ID查询 按自查时间排序 查询一条
   * @author dongxu
   * @date 2018年8月6日下午2:41:04
   * @param selfexaminationParam
   * @return
   * @throws BusinessException
   */
  SelfexaminationResult selectSingleBySystemId(SelfexaminationParam selfexaminationParam) 
      throws BusinessException;
}
