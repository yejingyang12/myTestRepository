/**
 * Copyright 2018 sinopec.com Inc. All Rights Reserved.  
 * @Title t_cpro_system_code.java
 * @Package com.sinopec.smcc.cpro.mapper
 * @author Aran
 * @date 2018年5月24日  上午09:18:12
 */
package com.sinopec.smcc.cpro.systemcode.mapper;

import java.util.List;

import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;

/**
 * @Description: TODO:
 * @date 2018年5月24日  上午09:18:12
 * @version V1.0
 */
public interface SystemCodeMapper {
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月6日下午8:08:09
   * @param systemCodeParam
   */
  List<SystemCodeListResult> selectSystemCodeForByParamKeySystemCode(SystemCodeParam systemCodeParam);
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月6日下午8:08:09
   * @param systemCodeParam
   */
  List<SystemCodeListResult> selectSystemCodeForByParamKeyCodeName(SystemCodeParam systemCodeParam);
}