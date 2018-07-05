/**
* Copyright 2018. 
* @Title DiagramMapper.java
* @Package com.sinopec.smcc.cpro.home.mapper
* @Description: TODO:
* @author yejingyang
* @date 2018年6月7日下午4:44:47
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.mapper;

import java.util.List;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.entity.DiagramResult;

/**
 * @Title DiagramMapper.java
 * @Package com.sinopec.smcc.cpro.home.mapper
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月7日下午4:44:47
 * @version V1.0
 */
public interface DiagramMapper {

  /**
   * @Descrption系统等保等级分布
   * @author yejingyang
   * @date 2018年6月7日下午4:56:48
   * @param diagramParam
   * @return
   */
  DiagramResult selectSystemLevelProtectionDistribution(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption不同等保级别系统在不同等保管理状态下详情
   * @author yejingyang
   * @date 2018年6月7日下午5:32:15
   * @param diagramParam
   * @return
   */
  DiagramResult selectSystemLevelBySystemType(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月7日下午5:37:49
   * @param diagramParam
   * @return
   */
  List<DiagramListResult> selectRecordCompanyTop10(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption受理备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月7日下午5:42:11
   * @param diagramParam
   * @return
   */
  List<DiagramListResult> selectAcceptCompanyTop10(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption 系统等保管理趋势
   * @author dongxu
   * @date 2018年6月28日下午5:05:54
   * @param diagramParam
   * @return
   */
  List<DiagramListResult> selectSystemTrendByYear(DiagramParam diagramParam);
  
}
