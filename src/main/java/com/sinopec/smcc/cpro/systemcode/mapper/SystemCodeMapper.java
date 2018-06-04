/**
 * Copyright 2018 sinopec.com Inc. All Rights Reserved.  
 * @Title t_cpro_system_code.java
 * @Package com.sinopec.smcc.cpro.mapper
 * @author Aran
 * @date 2018年5月24日  上午09:18:12
 */
package com.sinopec.smcc.cpro.systemcode.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.systemcode.entity.SystemCode;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;

/**
 * @Description: TODO:
 * @date 2018年5月24日  上午09:18:12
 * @version V1.0
 */
public interface SystemCodeMapper {
  /**
   * @Descrption 
   * @author zhouyu
   * @date 2018年6月3日下午11:18:03
   * @param type
   * @return
   */
  public String selectConstantName(SystemCodeParam systemCodeParam);
  /**
   * @Descrption 单选key与value
   * @author zhouyu
   * @date 2018年6月4日上午9:20:32
   * @param tParam
   * @param type
   * @return
   */
  public List<SystemCode> getConstantByName(SystemCodeParam systemCodeParam);
  /**
   * @Descrption 多选树结构
   * @author zhouyu
   * @date 2018年6月4日下午1:46:43
   * @param systemCodeParam
   * @return
   */
  public List<SystemCode> getConstantTreeByName(SystemCodeParam systemCodeParam);
}