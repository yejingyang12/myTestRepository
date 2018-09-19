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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pcitc.ssc.dps.inte.workflow.AppTaskOpinionData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.home.entity.DiagramListResult;
import com.sinopec.smcc.cpro.home.entity.DiagramParam;
import com.sinopec.smcc.cpro.home.entity.DiagramResult;
import com.sinopec.smcc.cpro.home.mapper.DiagramMapper;
import com.sinopec.smcc.cpro.home.server.DiagramService;
import com.sinopec.smcc.cpro.tools.DateUtils;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.region.dto.CproResultParam;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

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
  @Autowired
  private DpsTemplate dpsTemplate;
  @Autowired
  private UserApiService userApiServiceImpl;
  
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
  //处理提报时间结束时间;因为提报时间是通过节点表查询，节点表中创建时间有时分秒，所以需要加一天查询
    if(diagramParam.getAuditTimeEnd()!=null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String auditTimeEnd = sdf.format(diagramParam.getAuditTimeEnd());
      String[] auditTimes = auditTimeEnd.split("-");
      int dayTime = Integer.valueOf(auditTimes[auditTimes.length-1]);
      auditTimeEnd = auditTimes[0] + "-" + auditTimes[1] + "-" + (dayTime+1)+"";
      try {
        diagramParam.setAuditTimeEnd(DateUtils.getDate("yyyy-MM-dd", auditTimeEnd));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
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
  //处理提报时间结束时间;因为提报时间是通过节点表查询，节点表中创建时间有时分秒，所以需要加一天查询
    if(diagramParam.getAuditTimeEnd()!=null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String auditTimeEnd = sdf.format(diagramParam.getAuditTimeEnd());
      String[] auditTimes = auditTimeEnd.split("-");
      int dayTime = Integer.valueOf(auditTimes[auditTimes.length-1]);
      auditTimeEnd = auditTimes[0] + "-" + auditTimes[1] + "-" + (dayTime+1)+"";
      try {
        diagramParam.setAuditTimeEnd(DateUtils.getDate("yyyy-MM-dd", auditTimeEnd));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
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
  //处理提报时间结束时间;因为提报时间是通过节点表查询，节点表中创建时间有时分秒，所以需要加一天查询
    if(diagramParam.getAuditTimeEnd()!=null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String auditTimeEnd = sdf.format(diagramParam.getAuditTimeEnd());
      String[] auditTimes = auditTimeEnd.split("-");
      int dayTime = Integer.valueOf(auditTimes[auditTimes.length-1]);
      auditTimeEnd = auditTimes[0] + "-" + auditTimes[1] + "-" + (dayTime+1)+"";
      try {
        diagramParam.setAuditTimeEnd(DateUtils.getDate("yyyy-MM-dd", auditTimeEnd));
      } catch (ParseException e) {
        e.printStackTrace();
      }
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
    List<DiagramListResult> list2 = new ArrayList<DiagramListResult>();
    if(list == null || list.size() == 0){
      list2 = list;
    }else{
      //如果有数据，对数据进行组装
      Map<Integer, Integer> monthMap = new HashMap<Integer, Integer>();
      Map<Integer, String> monthNameMap = new HashMap<Integer, String>();
      monthNameMap.put(1, "一月");
      monthNameMap.put(2, "二月");
      monthNameMap.put(3, "三月");
      monthNameMap.put(4, "四月");
      monthNameMap.put(5, "五月");
      monthNameMap.put(6, "六月");
      monthNameMap.put(7, "七月");
      monthNameMap.put(8, "八月");
      monthNameMap.put(9, "九月");
      monthNameMap.put(10, "十月");
      monthNameMap.put(11, "十一月");
      monthNameMap.put(12, "十二月");
      //获取有数据的月份
      for (DiagramListResult diagramListResult : list) {
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCount(), diagramListResult.getMouthCount());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountExam(), diagramListResult.getMouthCountExam());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountExamine(), diagramListResult.getMouthCountExamine());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountGrad(), diagramListResult.getMouthCountGrad());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountRecords(), diagramListResult.getMouthCountRecords());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountSelf(), diagramListResult.getMouthCountSelf());
        }
      }
      //将有数据的月份的数据生成出来(1至12月)
      for (int i = 1; i < 13; i++) {
        //如果有该月份,生成对象，合并统计数据
        if(monthMap.get(i) != null){
          DiagramListResult diagramListResultTemp = new DiagramListResult();
          diagramListResultTemp.setMonth(monthNameMap.get(i));
          diagramListResultTemp.setMouthCount(i);
          Integer readyGradCount = 0;
          Integer checkGradCount = 0;
          Integer recordsCount = 0;
          Integer evaluationCount = 0;
          Integer selfInspectionCount = 0;
          //循环查询获取的集合，将本月的同性质数据加起来
          for (DiagramListResult diagramListResult : list) {
            //已定级数
            if(diagramListResult.getMouthCountGrad() != null && diagramListResult.getMouthCountGrad() == i){
              readyGradCount += diagramListResult.getReadyGradCount();
            }
            //审核定级数
            if(diagramListResult.getMouthCountExamine() != null && diagramListResult.getMouthCountExamine() == i){
              checkGradCount += diagramListResult.getCheckGradCount();
            }
            //备案数
            if(diagramListResult.getMouthCountRecords() != null && diagramListResult.getMouthCountRecords() == i){
              recordsCount += diagramListResult.getRecordsCount();
            }
            //测评数
            if(diagramListResult.getMouthCountExam() != null && diagramListResult.getMouthCountExam() == i){
              evaluationCount += diagramListResult.getEvaluationCount();
            }
            //自查数
            if(diagramListResult.getMouthCountSelf() != null && diagramListResult.getMouthCountSelf() == i){
              selfInspectionCount += diagramListResult.getSelfInspectionCount();
            }
          }
          diagramListResultTemp.setReadyGradCount(readyGradCount);
          diagramListResultTemp.setCheckGradCount(checkGradCount);
          diagramListResultTemp.setRecordsCount(recordsCount);
          diagramListResultTemp.setEvaluationCount(evaluationCount);
          diagramListResultTemp.setSelfInspectionCount(selfInspectionCount);
          list2.add(diagramListResultTemp);
        }
      }
    }
    return list2;
  }

  /**
   * 系统等保管理趋势
   */
  @Override
  public List<CproResultParam> queryApiSystemTrendByYear(DiagramParam diagramParam)
      throws BusinessException {
    HttpServletRequest request = 
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    if(StringUtils.isNotBlank(diagramParam.getUserId())){
      request.getSession().setAttribute("userId", diagramParam.getUserId());
    }
    if("".equals(diagramParam.getYear())){
      diagramParam.setYear(null);
    }
    if(diagramParam.getStatusArray() != null){
      this.handleStatus(diagramParam);
    }
  //处理提报时间结束时间;因为提报时间是通过节点表查询，节点表中创建时间有时分秒，所以需要加一天查询
    if(diagramParam.getAuditTimeEnd()!=null){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String auditTimeEnd = sdf.format(diagramParam.getAuditTimeEnd());
      String[] auditTimes = auditTimeEnd.split("-");
      int dayTime = Integer.valueOf(auditTimes[auditTimes.length-1]);
      auditTimeEnd = auditTimes[0] + "-" + auditTimes[1] + "-" + (dayTime+1)+"";
      try {
        diagramParam.setAuditTimeEnd(DateUtils.getDate("yyyy-MM-dd", auditTimeEnd));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    //获得相应图表数据
    List<DiagramListResult> list = new ArrayList<DiagramListResult>();
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if(organizationApiResult==null){
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
    List<CproResultParam> list2 = new ArrayList<CproResultParam>();
    if(list == null || list.size() == 0){
      list2 = null;
    }else{
      //如果有数据，对数据进行组装
      Map<Integer, Integer> monthMap = new HashMap<Integer, Integer>();
      Map<Integer, String> monthNameMap = new HashMap<Integer, String>();
      monthNameMap.put(1, "一月");
      monthNameMap.put(2, "二月");
      monthNameMap.put(3, "三月");
      monthNameMap.put(4, "四月");
      monthNameMap.put(5, "五月");
      monthNameMap.put(6, "六月");
      monthNameMap.put(7, "七月");
      monthNameMap.put(8, "八月");
      monthNameMap.put(9, "九月");
      monthNameMap.put(10, "十月");
      monthNameMap.put(11, "十一月");
      monthNameMap.put(12, "十二月");
      //获取有数据的月份
      for (DiagramListResult diagramListResult : list) {
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCount(), diagramListResult.getMouthCount());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountExam(), diagramListResult.getMouthCountExam());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountExamine(), diagramListResult.getMouthCountExamine());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountGrad(), diagramListResult.getMouthCountGrad());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountRecords(), diagramListResult.getMouthCountRecords());
        }
        if(diagramListResult.getMouthCount() != null){
          monthMap.put(diagramListResult.getMouthCountSelf(), diagramListResult.getMouthCountSelf());
        }
      }
      //将有数据的月份的数据生成出来(1至12月)
      for (int i = 1; i < 13; i++) {
        //如果有该月份,生成对象，合并统计数据
        if(monthMap.get(i) != null){
          CproResultParam diagramListResultTemp = new CproResultParam();
          diagramListResultTemp.setMouthCount(i);
          Integer readyGradCount = 0;
          Integer checkGradCount = 0;
          Integer recordsCount = 0;
          Integer evaluationCount = 0;
          Integer selfInspectionCount = 0;
          //循环查询获取的集合，将本月的同性质数据加起来
          for (DiagramListResult diagramListResult : list) {
            //已定级数
            if(diagramListResult.getMouthCountGrad() != null && diagramListResult.getMouthCountGrad() == i){
              readyGradCount += diagramListResult.getReadyGradCount();
            }
            //审核定级数
            if(diagramListResult.getMouthCountExamine() != null && diagramListResult.getMouthCountExamine() == i){
              checkGradCount += diagramListResult.getCheckGradCount();
            }
            //备案数
            if(diagramListResult.getMouthCountRecords() != null && diagramListResult.getMouthCountRecords() == i){
              recordsCount += diagramListResult.getRecordsCount();
            }
            //测评数
            if(diagramListResult.getMouthCountExam() != null && diagramListResult.getMouthCountExam() == i){
              evaluationCount += diagramListResult.getEvaluationCount();
            }
            //自查数
            if(diagramListResult.getMouthCountSelf() != null && diagramListResult.getMouthCountSelf() == i){
              selfInspectionCount += diagramListResult.getSelfInspectionCount();
            }
          }
          diagramListResultTemp.setReadyGradCount(readyGradCount);
          diagramListResultTemp.setCheckGradCount(checkGradCount);
          diagramListResultTemp.setRecordsCount(recordsCount);
          diagramListResultTemp.setEvaluationCount(evaluationCount);
          diagramListResultTemp.setSelfInspectionCount(selfInspectionCount);
          list2.add(diagramListResultTemp);
        }
      }
    }
    return list2;
  }
  
  /**
   * @Descrption
   * @author yejingyang
   * @date 2018年8月29日下午3:33:35
   * @param diagramParam
   */
  private void handleStatus(DiagramParam diagramParam) {
    
    Map<String,String> extMap = new HashMap<String,String>();
  //获取用户信息
    UserDTO user=userApiServiceImpl.getUserInfo();
    String UserId=String.valueOf(user.getUserId());
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    List<String> orgStr = organizationApiResult.getPermssions();
    //企业权限  
    if(orgStr.contains("0102010301")||orgStr.contains("0102010201")){
      //单位Code
      extMap.put("ext008", jurisdictionApiServiceImpl.getCompanyCode());
    } 
    //版本号
    extMap.put("ext003", WorkFlowConsts.CATEGORY_VERSION_NUM);
    //获取所有待办
    final PagedList appTODOTask = dpsTemplate.appTODOTask(UserId,"",
        WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
    //获取所有已办
    final PagedList appDoneTask = dpsTemplate.appDoneTask(UserId,"",
        WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
    
    //判断待办或已办是否为空
    List<ExecuteTaskData> executeTaskDataList = new ArrayList<ExecuteTaskData>();
    if(appTODOTask != null && ! ObjectUtils.isEmpty(appTODOTask.getExecuteTaskList())){
      executeTaskDataList = appTODOTask.getExecuteTaskList(); 
      if(appDoneTask != null && ! ObjectUtils.isEmpty(appDoneTask.getExecuteTaskList())){
        executeTaskDataList.addAll(appDoneTask.getExecuteTaskList());
      }
    }
    List<Integer> gradingStatus = new ArrayList<Integer>();//定级
    List<Integer> fkExaminStatus = new ArrayList<Integer>();//审核
    List<Integer> recordStatus = new ArrayList<Integer>();//备案
    List<Integer> evaluationStatus = new ArrayList<Integer>();//测评
    List<Integer> examinationStatus = new ArrayList<Integer>();//自查
    List<Integer> examinStatus = new ArrayList<Integer>();//待。。。审核
    List<String> systemIdList = new ArrayList<String>();//系统ID集合
    
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
      if(stsusArray[i] == 15 || stsusArray[i] == 16 ||  stsusArray[i] == 17 || stsusArray[i] == 18){
        if(!ObjectUtils.isEmpty(executeTaskDataList)){
          for(ExecuteTaskData executeTaskData : executeTaskDataList){
            //executeResult : 1和-1 待办状态，2 审批通过 3 审批未通过
            if(executeTaskData.getExecuteResult() == 1 || executeTaskData.getExecuteResult()==-1){
              if(executeTaskData.getActivityName().equals("企业主联络员审批") && stsusArray[i] == 15 ){
                //待企业待企业安全员管理审核
                if(StringUtils.isNotBlank(executeTaskData.getExt001())){
                  systemIdList.add(executeTaskData.getExt001());
                }
              }
            }else if(executeTaskData.getExecuteResult() == 2){
              //获取审批历史
              List<AppTaskOpinionData> appTaskOpinionDataList = 
                  dpsTemplate.appOpinion(executeTaskData.getBusinessId());
              AppTaskOpinionData appTaskOpinionData = appTaskOpinionDataList.get(0);
              if(!ObjectUtils.isEmpty(appTaskOpinionDataList) &&appTaskOpinionDataList.size() >=2&&
                  stsusArray[i]==18){
                if(appTaskOpinionData.getExecuteResult() == 3){
                  //总部安全管理员审核未通过
                  systemIdList.add(executeTaskData.getExt001());
                }
              }
              if(stsusArray[i]==16 && appTaskOpinionDataList.size() == 1){
                //待总部审核
                if(StringUtils.isNotBlank(executeTaskData.getExt001())){
                  systemIdList.add(executeTaskData.getExt001());
                }
              }
            }else if(executeTaskData.getExecuteResult() == 3){
              if(executeTaskData.getActivityName().equals("企业主联络员审批") && stsusArray[i] == 17){
                //如果企业主联络员未通过
                if(StringUtils.isNotBlank(executeTaskData.getExt001())){
                  systemIdList.add(executeTaskData.getExt001());
                }
              }
            }
          }
        }
        diagramParam.setExaminStatusType("28");
        diagramParam.setSystemIdList(systemIdList);
      }
//      if(stsusArray[i] == 16){//待总部安全审核
//        mainParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(2);
//        examinStatus.add(2);
//      }
//      
//      //状态为审核未通过，查询审核状态为
//      //3：企业安全员管理审核未通过；
//      //4：总部安全管理员审核未通过；
//      /*if(mainParam.getStatus() == 7){
//      mainParam.setFkExaminStatus(3);
//    }*/
//      if(stsusArray[i] == 17){//企业业务审核未通过
//        mainParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(3);
//        examinStatus.add(3);
//      }
//      if(stsusArray[i] == 18){//总部安全审核未通过
//        mainParam.setExaminStatusType("28");
//        mainParam.setFkExaminStatus(4);
//        examinStatus.add(4);
//      }
    }
    diagramParam.setGradingStatus1(gradingStatus);
    diagramParam.setFkExaminStatus1(fkExaminStatus);
    diagramParam.setRecordStatus1(recordStatus);
    diagramParam.setEvaluationStatus1(evaluationStatus);
    diagramParam.setExaminationStatus1(examinationStatus);
    diagramParam.setExaminStatus1(examinStatus);
  }

}
