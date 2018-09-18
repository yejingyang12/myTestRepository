/**
* Copyright 2018. 
* @Title DiagramService.java
* @Package com.sinopec.smcc.cpro.home.server
* @Description: TODO:
* @author yejingyang
* @date 2018年6月7日下午4:52:15
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.server;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.entity.DiagramResult;
import com.sinopec.smcc.depends.region.dto.CproResultParam;

/**
 * @Title DiagramService.java
 * @Package com.sinopec.smcc.cpro.home.server
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月7日下午4:52:15
 * @version V1.0
 */
public interface DiagramService {

  /**
   * @Descrption系统等保等级分布
   * @author yejingyang
   * @date 2018年6月7日下午4:53:41
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  DiagramResult querySystemLevelDiagram(DiagramParam diagramParam) throws BusinessException;

  /**
   * @Descrption不同等保级别系统在不同等保管理状态下详情
   * @author yejingyang
   * @date 2018年6月7日下午5:31:00
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  DiagramResult querySystemLevelBySystemType(DiagramParam diagramParam) throws BusinessException;

  /**
   * @Descrption备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月7日下午5:36:28
   * @param diagramParam
   * @return
   * @throws BusinessException
   */
  List<DiagramListResult> queryRecordCompanyTop10(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption受理备案单位数量Top10
   * @author yejingyang
   * @date 2018年6月7日下午5:38:53
   * @param diagramParam
   * @return
   */
  List<DiagramListResult> queryAcceptCompanyTop10(DiagramParam diagramParam) 
      throws BusinessException;

  /**
   * @Descrption 系统等保管理趋势
   * @author dongxu
   * @param request 
   * @date 2018年6月28日下午5:02:02
   * @param diagramParam
   * @return
   */
  List<DiagramListResult> querySystemTrendByYear(HttpServletRequest request, DiagramParam diagramParam) 
      throws BusinessException; 
  
  /**
   * @Descrption 系统等保管理趋势
   * @author dongxu
   * @param request 
   * @date 2018年6月28日下午5:02:02
   * @param diagramParam
   * @return
   */
  List<CproResultParam> queryApiSystemTrendByYear(HttpServletRequest request, DiagramParam diagramParam) 
      throws BusinessException; 

}
