/**
 * Copyright 2018 sinopec.com Inc. All Rights Reserved.  
 * @Title t_cpro_check.java
 * @Package com.sinopec.smcc.cpro.mapper
 * @author Aran
 * @date 2018年5月24日  上午09:18:12
 */
package com.sinopec.smcc.cpro.review.mapper;

import java.util.List;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeParam;
import com.sinopec.smcc.cpro.review.entity.CheckParam;

/**
 * @Description: TODO:
 * @date 2018年5月24日  上午09:18:12
 * @version V1.0
 */
public interface CheckMapper {

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月25日下午3:04:45
   * @param checkParam
   * @return
   */
  public List<CheckListResult> selectAllByCheckParam(CheckParam checkParam);

  /**
   * @Descrption
   * @author zhouyu
   * @date 2018年5月28日下午12:58:57
   */
  public void updateKeyCheck(CheckParam checkParam);
  
  /**
   * @Descrption 新增审核
   * @author zhouyu
   * @date 2018年5月25日下午1:50:04
   * @param checkParam 参数
   * @return 数据
   */
  public void checkNodeSave(CheckNodeListResult checkNodeListResult) throws BusinessException;

  /**
   * @Descrption 审核节点列表
   * @author zhouyu
   * @date 2018年5月28日上午9:51:24
   * @param checkNodeParam
   * @return
   */
  public List<CheckNodeListResult> selectAllByCheckNodeParam(CheckNodeParam checkNodeParam);

  /**
   * @Descrption 新增审核信息
   * @author zhouyu
   * @date 2018年5月29日下午4:13:39
   * @param checkParam
   */
  public void insertByCheck(CheckParam checkParam);
}