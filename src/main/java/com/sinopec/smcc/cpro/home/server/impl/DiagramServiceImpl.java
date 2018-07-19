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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
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
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  
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
    //获得相应图表数据
    List<DiagramListResult> list = new ArrayList<DiagramListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null || organizationApiResult.getCodeList().size() ==0){
      return null;
    }else{
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.diagramMapper.selectRecordCompanyTop10(diagramParam);
        break;
      case "2":
        diagramParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.diagramMapper.selectRecordCompanyTop10(diagramParam);
        break;
      case "3":
        diagramParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.diagramMapper.selectRecordCompanyTop10(diagramParam);
        break;

      default:
        break;
      }
    }
    return list;
  }

  /**
   * 受理备案单位数量Top10
   */
  @Override
  @Transactional
  public List<DiagramListResult> queryAcceptCompanyTop10(DiagramParam diagramParam)
      throws BusinessException {
    //获得相应图表数据
    List<DiagramListResult> list = new ArrayList<DiagramListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null || organizationApiResult.getCodeList().size() == 0){
      return null;
    }else{
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.diagramMapper.selectAcceptCompanyTop10(diagramParam);
        break;
      case "2":
        diagramParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.diagramMapper.selectAcceptCompanyTop10(diagramParam);
        break;
      case "3":
        diagramParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.diagramMapper.selectAcceptCompanyTop10(diagramParam);
        break;

      default:
        break;
      }
    }
    return list;
  }

  /**
   * 系统等保管理趋势
   */
  @Override
  public List<DiagramListResult> querySystemTrendByYear(DiagramParam diagramParam)
      throws BusinessException {
    //获得相应图表数据
    List<DiagramListResult> list = new ArrayList<DiagramListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null || organizationApiResult.getCodeList().size() == 0){
      return null;
    }else{
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        list = 
            this.diagramMapper.selectSystemTrendByYear(diagramParam);
        break;
      case "2":
        diagramParam.setPlateList(organizationApiResult.getNameList());
        list =  
            this.diagramMapper.selectSystemTrendByYear(diagramParam);
        break;
      case "3":
        diagramParam.setCompanyList(organizationApiResult.getCodeList());
        list =  
            this.diagramMapper.selectSystemTrendByYear(diagramParam);
        break;

      default:
        break;
      }
    }
    return list;
  }

}
