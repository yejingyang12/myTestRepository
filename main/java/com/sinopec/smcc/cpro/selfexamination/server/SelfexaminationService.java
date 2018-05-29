/**
* Copyright 2018. 
* @Title SelfexaminationService.java
* @Package com.sinopec.smcc.cpro.selfexamination.server
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午1:40:12
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.server;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;

/**
 * @Title SelfexaminationService.java
 * @Package com.sinopec.smcc.cpro.selfexamination.server
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午1:40:12
 * @version V1.0
 */
public interface SelfexaminationService {

  /**
   * @Descrption响应自查列表数据
   * @author yejingyang
   * @date 2018年5月25日下午1:44:29
   * @param selfexaminationParam  查询参数
   * @return  列表分页数据
   */
  PageInfo<SelfexaminationListResult> querySelfexaminationList(
      SelfexaminationParam selfexaminationParam) throws BusinessException;

  /**
   * @Descrption添加或修改自查数据
   * @author yejingyang
   * @date 2018年5月25日下午8:16:25
   * @param selfexamination  添加或修改的必要参数
   * @return  添加或修改数据成功后的id值
   */
  String saveOrUpdateSelfexamination(SelfexaminationParam selfexaminationParam) 
      throws BusinessException;

}
