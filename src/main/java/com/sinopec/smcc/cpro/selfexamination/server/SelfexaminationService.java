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
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;

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
  String saveSelfexamination(String userName, SelfexaminationParam selfexaminationParam)  
      throws BusinessException;

  /**
   * @Descrption 查询修改回显信息
   * @author dongxu
   * @date 2018年6月2日下午11:20:24
   * @param selfexaminationParam
   * @return
   */
  SelfexaminationResult queryEditSelfexamination(SelfexaminationParam selfexaminationParam)
      throws BusinessException;

  /**
   * @Descrption 删除自查信息数据
   * @author yejingyang
   * @date 2018年6月6日上午10:05:34
   * @param selfexaminationParam
   * @throws BusinessException
   */
  void deleteSelfexaminationBySelfexaminationId(String userName, 
      SelfexaminationParam selfexaminationParam) throws BusinessException;

}
