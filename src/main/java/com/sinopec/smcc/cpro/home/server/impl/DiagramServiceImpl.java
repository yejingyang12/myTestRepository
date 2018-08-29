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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
    if(organizationApiResult==null){
      return list;
    }else{
      if(diagramParam.getStatusArray() != null){
        this.handleStatus(diagramParam);
      }
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
    if(organizationApiResult==null){
      return list;
    }else{
      if(diagramParam.getStatusArray() != null){
        this.handleStatus(diagramParam);
      }
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
  public List<DiagramListResult> querySystemTrendByYear(HttpServletRequest request,
      DiagramParam diagramParam)throws BusinessException {
    
    if(StringUtils.isNotBlank(diagramParam.getUserId())){
      request.getSession().setAttribute("userId", diagramParam.getUserId());
    }
    if("".equals(diagramParam.getYear())){
      diagramParam.setYear(null);
    }
    if(diagramParam.getStatusArray() != null){
      this.handleStatus(diagramParam);
    }
    //获得相应图表数据
    List<DiagramListResult> list = new ArrayList<DiagramListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null){
      return list;
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

  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年8月29日下午3:33:35
   * @param diagramParam
   */
  private void handleStatus(DiagramParam diagramParam) {
    
    List<Integer> gradingStatus = new ArrayList<Integer>();//定级
    List<Integer> fkExaminStatus = new ArrayList<Integer>();//审核
    List<Integer> recordStatus = new ArrayList<Integer>();//备案
    List<Integer> evaluationStatus = new ArrayList<Integer>();//测评
    List<Integer> examinationStatus = new ArrayList<Integer>();//自查
    List<Integer> examinStatus = new ArrayList<Integer>();//待。。。审核
    
    Integer[] stsusArray = diagramParam.getStatusArray();
    for (int i = 0; i < stsusArray.length; i++) {
      if(stsusArray[i] == 1){//未定级
        diagramParam.setGradingStatusType("23");
//        mainParam.setGradingStatus("1");
        gradingStatus.add(1);
      }
      if(stsusArray[i] == 2){//预定级
        diagramParam.setGradingStatusType("23");
//        mainParam.setGradingStatus("2");
        gradingStatus.add(2);
        }
      if(stsusArray[i] == 3){//已定级
        diagramParam.setGradingStatusType("23");
//        mainParam.setGradingStatus("3");
        gradingStatus.add(3);  
      }
      //审核
      if(stsusArray[i] == 4 ){//未审核
        diagramParam.setFkExaminStatusType("24");
//        mainParam.setFkExaminStatus(0);
        fkExaminStatus.add(2);
      }
      if(stsusArray[i] == 6 ){//已审核
        diagramParam.setFkExaminStatusType("24");
//        mainParam.setFkExaminStatus(3);
        fkExaminStatus.add(3);
      }
      //备案
      if(stsusArray[i] == 8){//未备案
        diagramParam.setRecordStatusType("25");
//        mainParam.setRecordStatus("");
        recordStatus.add(1);
      }
      if(stsusArray[i] == 9){//已备案
        diagramParam.setRecordStatusType("25");
//        mainParam.setRecordStatus("");
        recordStatus.add(3);
      }
      if(stsusArray[i] == 10){//撤销备案
        diagramParam.setRecordStatusType("25");
//        mainParam.setRecordStatus("");
        recordStatus.add(4);
      }
      
      //测评
      if(stsusArray[i] == 11){//未测评
        diagramParam.setEvaluationStatusType("26");
//        mainParam.setEvaluationStatus("");
        evaluationStatus.add(1);
      }
      if(stsusArray[i] == 12){//以测评
        diagramParam.setEvaluationStatusType("26");
//      mainParam.setEvaluationStatus("");
        evaluationStatus.add(3);
      }
      
      //自查
      if(stsusArray[i] == 13){
        diagramParam.setExaminationStatusType("27");
//        mainParam.setExaminationStatus("1");
        examinationStatus.add(1);
      }
      if(stsusArray[i] == 14){//以自查
        diagramParam.setExaminationStatusType("27");
//      mainParam.setExaminationStatus("1");
        examinationStatus.add(3);
      }
      //状态为待审核，查询审核状态为：
      //1：待企业安全员管理审核；
      //2：待总部安全管理员审核；
      /*if(mainParam.getStatus() == 5){
    mainParam.setFkExaminStatus(1);
    }*/
      if(stsusArray[i] == 15){//待企业业务审核
        diagramParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(1);
        examinStatus.add(1);
      }
      if(stsusArray[i] == 16){//待总部安全审核
        diagramParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(2);
        examinStatus.add(2);
      }
      
      //状态为审核未通过，查询审核状态为
      //3：企业安全员管理审核未通过；
      //4：总部安全管理员审核未通过；
      /*if(mainParam.getStatus() == 7){
      mainParam.setFkExaminStatus(3);
    }*/
      if(stsusArray[i] == 17){//企业业务审核未通过
        diagramParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(3);
        examinStatus.add(3);
      }
      if(stsusArray[i] == 18){//总部安全审核未通过
        diagramParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(4);
        examinStatus.add(4);
      }
    }
    diagramParam.setGradingStatus1(gradingStatus);
    diagramParam.setFkExaminStatus1(fkExaminStatus);
    diagramParam.setRecordStatus1(recordStatus);
    diagramParam.setEvaluationStatus1(evaluationStatus);
    diagramParam.setExaminationStatus1(examinationStatus);
    diagramParam.setExaminStatus1(examinStatus);
  }

}
