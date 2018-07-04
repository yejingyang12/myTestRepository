/**
* Copyright 2018. 
* @Title DiagramServiceImpl.java
* @Package com.sinopec.smcc.cpro.home.server.impl
* @Description: TODO:
* @author yejingyang
* @date 2018年6月7日下午4:52:40
* @version V1.0
*/
package com.sinopec.smcc.cpro.home.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.entity.DiagramResult;
import com.sinopec.smcc.cpro.home.mapper.DiagramMapper;
import com.sinopec.smcc.cpro.home.server.DiagramService;

/**
 * @Title DiagramServiceImpl.java
 * @Package com.sinopec.smcc.cpro.home.server.impl
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月7日下午4:52:40
 * @version V1.0
 */
@Service
public class DiagramServiceImpl implements DiagramService {
  @Autowired
  private DiagramMapper diagramMapper;
  
  /**
   * 系统等保等级分布
   */
  @Override
  @Transactional
  public DiagramResult querySystemLevelDiagram(DiagramParam diagramParam) 
      throws BusinessException {
    return this.diagramMapper.selectSystemLevelProtectionDistribution(diagramParam);
  }

  /**
   * 不同等保级别系统在不同等保管理状态下详情
   */
  @Override
  @Transactional
  public DiagramResult querySystemLevelBySystemType(DiagramParam diagramParam)
      throws BusinessException {
    return this.diagramMapper.selectSystemLevelBySystemType(diagramParam);
  }

  /**
   * 备案单位数量Top10
   */
  @Override
  @Transactional
  public List<DiagramListResult> queryRecordCompanyTop10(DiagramParam diagramParam)
      throws BusinessException {
    return this.diagramMapper.selectRecordCompanyTop10(diagramParam);
  }

  /**
   * 受理备案单位数量Top10
   */
  @Override
  @Transactional
  public List<DiagramListResult> queryAcceptCompanyTop10(DiagramParam diagramParam)
      throws BusinessException {
    return this.diagramMapper.selectAcceptCompanyTop10(diagramParam);
  }

  /**
   * 系统等保管理趋势
   */
  @Override
  public List<DiagramListResult> querySystemTrendByYear(DiagramParam diagramParam)
      throws BusinessException {
    return this.diagramMapper.selectSystemTrendByYear(diagramParam);
  }

}
