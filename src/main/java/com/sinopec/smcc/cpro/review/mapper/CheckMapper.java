/**
 * Copyright 2018 sinopec.com Inc. All Rights Reserved.  
 * @Title t_cpro_check.java
 * @Package com.sinopec.smcc.cpro.mapper
 * @author Aran
 * @date 2018年5月24日  上午09:18:12
 */
package com.sinopec.smcc.cpro.review.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;

/**
 * @Description: TODO:
 * @date 2018年5月24日  上午09:18:12
 * @version V1.0
 */
public interface CheckMapper {

  /**
   * @Descrption获取审核列表
   * @author zhouyu
   * @date 2018年5月25日下午3:04:45
   * @param checkParam
   * @return
   */
  public List<CheckListResult> selectAllByCheckParam(CheckParam checkParam);

  /**
   * @Descrption修改审核信息
   * @author yejingyang
   * @date 2018年6月8日下午2:28:35
   * @param checkParam
   */
  public void updateCheckBySystemId(CheckParam checkParam);
  
}