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

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;

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
  void insertOrUpdateSelfexamination(SelfexaminationParam selfexaminationParam) throws BusinessException;
}
