/**
* 2018. 
* @Title ApiServiceImpl.java
* @Package com.sinopec.smcc.cpro.api.service.impl
* @Description: TODO:
* @author dongxu
* @date 2018年7月17日上午10:13:14
* @version V1.0
*/
package com.sinopec.smcc.cpro.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.base.result.PageUtil;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.mapper.SystemRelationMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.dps.util.DpsConfig;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.region.dto.BatchApprovalInfo;
import com.sinopec.smcc.depends.region.dto.CheckParam;
import com.sinopec.smcc.depends.region.dto.CproForeignRequestParam;
import com.sinopec.smcc.depends.region.dto.CproResultParam;
import com.sinopec.smcc.depends.region.dto.SystemRelationBaseInfo;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

/**
 * @Title ApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.api.service.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年7月17日上午10:13:14
 * @version V1.0
 */
@Service
public class ApiServiceImpl implements ApiService{
  
  @Autowired
  private GradingService gradingServiceImpl;
  @Autowired
  private SystemService systemServiceImpl;
  @Autowired
  private DpsTemplate dpsTemplate;
  @Autowired
  private SystemRelationMapper systemRelationMapper;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  @Autowired
  private DpsConfig dpsConfig;
  @Autowired
  private CheckService checkServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  @Autowired
  private WorkFlowApiService workFlowApiServiceImpl;
  @Autowired
  private UserApiService userApiServiceImpl;
  
  /**
   * 获取定级信息
   */
  @Override
  public CproResultParam getGradingInformation(String systemCode) throws BusinessException {
    SystemParam systemParam = new SystemParam();
    systemParam.setStandardizedCode(systemCode);
    SystemResult systemResult = systemServiceImpl.querySystemBysystemCode(systemParam);
    if(systemResult != null){
      CproResultParam gradingApiResult = new CproResultParam();
      GradingParam gradingParam = new GradingParam();
      gradingParam.setFkSystemId(systemResult.getSystemId());
      GradingListResult gradingListResult = gradingServiceImpl.queryDetailsGrading(gradingParam);
      if(gradingListResult != null){
        gradingApiResult.setBizSprankLevel(gradingListResult.getFkBizSPRankLevel());
        gradingApiResult.setBizSystemLevel(gradingListResult.getFkBizSystemLevel());
        gradingApiResult.setSpranklevel(gradingListResult.getFkSpRanklevel());
      }
      gradingApiResult.setGradingStatus(systemResult.getGradingStatus());
      return gradingApiResult;
    }else{
      return null;
    }
  }

  /**
   * 通过userId获取待办列表
   */
  @Override
  public PageUtil getStayHandle(CproForeignRequestParam cproForeignRequestParam) 
      throws BusinessException {
    List<CheckListResult> list = new ArrayList<CheckListResult>();
    int appPagedTODOTaskTotal = 0;
    int appPagedTODOTaskPageNum = 0;
    Map<String,String> extMap = new HashMap<String,String>();
    //版本号
    extMap.put("ext003", WorkFlowConsts.CATEGORY_VERSION_NUM);
    //获取待办列表
    final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(
        cproForeignRequestParam.getUserId(),10,cproForeignRequestParam.getCurrPage(),"",
        WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
    appPagedTODOTaskTotal = appPagedTODOTask.getTotalCount();
    appPagedTODOTaskPageNum = appPagedTODOTask.getPageIndex();
    if((appPagedTODOTask.getExecuteTaskList())!=null){
      List<ExecuteTaskData> executeTaskDataList = appPagedTODOTask.getExecuteTaskList();      
      for(ExecuteTaskData executeTaskData : executeTaskDataList){
        //获取扩展数据systemId  
        String systemId = executeTaskData.getExt001();
        SystemParam systemParam = new SystemParam();
        systemParam.setSystemId(systemId);
        SystemResult systemResult = systemServiceImpl.querySystemByCheck(systemParam);
        CheckListResult checkListResult = new CheckListResult();
        if(systemResult != null){
          checkListResult.setInstanceName(systemResult.getSystemName());
          checkListResult.setFkSystemId(systemResult.getSystemId());
          checkListResult.setCompanyId(systemResult.getCompanyId());
          checkListResult.setFkInfoSysTypeCon(systemResult.getFkInfoSysTypeCon().toString());
          checkListResult.setExpertReviewName(systemResult.getExpertReviewName());
          checkListResult.setRecordReportName(systemResult.getRecordReportName());
          checkListResult.setRecordReportId(systemResult.getRecordReportId());
        }else{
          if(StringUtils.isNotBlank(executeTaskData.getExt004())){
            checkListResult.setInstanceName(executeTaskData.getExt004());
          }
          if(StringUtils.isNotBlank(executeTaskData.getExt005())){
            checkListResult.setFkInfoSysTypeCon(executeTaskData.getExt005());
          }
        }
        checkListResult.setPrevExecutor(executeTaskData.getSendUserName());//上一步执行人
        checkListResult.setInitiator(executeTaskData.getExt002());//发起人
        checkListResult.setExecuteTime(executeTaskData.getSendDate());//执行时间
        if(executeTaskData.getBusinessName().equals("定级")){
          checkListResult.setFkBusinessNode("1");//业务节点
        }else if(executeTaskData.getBusinessName().equals("撤销备案")){
          checkListResult.setFkBusinessNode("2");//业务节点
        }else{
          checkListResult.setFkBusinessNode("3");//业务节点
        }
        
        checkListResult.setTaskId(executeTaskData.getTaskId());
        checkListResult.setBusinessId(executeTaskData.getBusinessId());
        //executeResult : 1和-1 待办状态，2 审批通过 3 审批未通过
        if(executeTaskData.getExecuteResult() == 1 || executeTaskData.getExecuteResult()==-1){
          if(executeTaskData.getActivityName().equals("企业主联络员审批")){
            //待企业待企业安全员管理审核
            checkListResult.setFkExaminStatus("1");
          }else{
            //待总部安全管理员审核
            checkListResult.setFkExaminStatus("2");
          }
        }else if(executeTaskData.getExecuteResult() == 2){
          if(executeTaskData.getActivityName().equals("企业主联络员审批")&& 
              !executeTaskData.getBusinessName().equals("撤销备案")){
            //如果企业安全员已通过，则该状态为待总部安全管理员审核
            checkListResult.setFkExaminStatus("2");
          }else{
            //归档
            checkListResult.setFkExaminStatus("5");
          }
        }else if(executeTaskData.getExecuteResult() == 3){
          if(executeTaskData.getActivityName().equals("企业主联络员审批")){
            //如果企业主联络员未通过 状态为  企业安全员管理审核未通过
            checkListResult.setFkExaminStatus("3");
          }else{
            //如果总部未通过  则状态为 总部安全管理员审核未通过
            checkListResult.setFkExaminStatus("4");
          }
        }
        list.add(checkListResult);
      }
    }
    //装载列表数据
    PageInfo<CheckListResult> pageInfo = new PageInfo<>(list);
    pageInfo.setTotal(appPagedTODOTaskTotal);
    if(appPagedTODOTaskTotal > 10){
      int totalPageNum = (appPagedTODOTaskTotal  +  9) /  10;
      pageInfo.setPages(totalPageNum);
    }else{
      pageInfo.setPages(1);
    } 
    pageInfo.setPageNum(appPagedTODOTaskPageNum);
    if(ObjectUtils.isEmpty(list)){
      pageInfo.setPageSize(0);
    }else{
      pageInfo.setPageSize(10);
    }
    PageUtil pageUtil = new PageUtil(pageInfo);
    return pageUtil;
  }

  /**
   * 批量审批
   */
  @Override
  public Integer batchApproval(BatchApprovalInfo batchCheckHandleParam)
      throws BusinessException {
    HttpServletRequest request = 
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    session.setAttribute("userId",batchCheckHandleParam.getUbsUserId());
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    List<String> juri =  organizationApiResult.getPermssions();
    boolean isJur = juri.contains("0102010101");
    int count = 0;
    if(!ObjectUtils.isEmpty(batchCheckHandleParam.getCheckList())){
      for(CheckParam checkParam : batchCheckHandleParam.getCheckList()){
        if(StringUtils.isNotBlank(checkParam.getFkSystemId())){
          //业务节点定级
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("1")){
            checkParam.setScoreCheckReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              this.saveGradCheckApi(batchCheckHandleParam.getUbsUserName(),checkParam);
            }else{
              this.saveHeadGradCheckApi(batchCheckHandleParam.getUbsUserName(),checkParam);
            }
            count++;
          }
          //业务节点撤销备案
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("2")){
            checkParam.setCancelRecordsReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              this.saveCancelRecordsCheckApi(batchCheckHandleParam.getUbsUserName(),checkParam);
            }
            count++;
          }
          //业务节点申请变更
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("3")){
            checkParam.setScoreCheckChangeReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              this.saveGradChangeCheckApi(batchCheckHandleParam.getUbsUserName(),checkParam);
            }else{
              this.saveHeadGradChangeCheckApi(batchCheckHandleParam.getUbsUserName(),
                  checkParam);
            }
            count++;
          }
        }
      }
    }else{
      return null;
    }
    return count;
  }
  
  @Override
  public PageUtil getSystemRelationInfo(CproForeignRequestParam 
      cproForeignRequestParam) throws BusinessException {
    SystemRelationParam systemRelationParam = new SystemRelationParam();
    systemRelationParam.setUserId(cproForeignRequestParam.getUserId());
    systemRelationParam.setField(cproForeignRequestParam.getField());
    systemRelationParam.setSort(cproForeignRequestParam.getSort());
    systemRelationParam.setCurrentPage(cproForeignRequestParam.getCurrPage());
    systemRelationParam.setPageSize(cproForeignRequestParam.getPageSize());
    if(StringUtils.isNotBlank(systemRelationParam.getUserId())){
      HttpServletRequest request = 
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session = request.getSession();
      session.setAttribute("userId",systemRelationParam.getUserId());
    }
    
    StringBuffer orderBy = new StringBuffer();
    if (StringUtils.isNotBlank(systemRelationParam.getField())) {
      orderBy.append(ConvertFiledUtil.sortField(systemRelationParam.getField()));
      if (StringUtils.isNotBlank(systemRelationParam.getSort())) {
        orderBy.append("").append(systemRelationParam.getSort());
      }
    } else {
      // 默认排序
      orderBy.append("relation.createTime DESC");
    }
    
    List<SystemRelationResult> systemRelationResultList = new ArrayList<SystemRelationResult>();
    
    //权限
//    JurisdictionDataResult organizationApiResult = 
//        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
//    if(organizationApiResult==null){
//      return new PageInfo<>();
//    }else{
      
      // 初始化分页拦截器
      PageHelper.startPage(systemRelationParam.getCurrentPage(), systemRelationParam.getPageSize(),
          orderBy.toString());
      
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
//      switch (organizationApiResult.getResultType()) {
//      
//      case "0":
//        break;
//      case "1":
        // 获得响应列表数据
      // 获得响应列表数据
        systemRelationResultList = this.systemRelationMapper.
          querySystemRelationInfo(systemRelationParam);
//        break;
//      case "2":
//        systemRelationParam.setPlateList(organizationApiResult.getNameList());
//        systemRelationResultList = this.systemRelationMapper.
//            querySystemRelationInfo(systemRelationParam);
//        break;
//      case "3":
//        systemRelationParam.setCompanyList(organizationApiResult.getCodeList());
//        systemRelationResultList = this.systemRelationMapper.
//            querySystemRelationInfo(systemRelationParam);
//        break;
//
//      default:
//        break;
//      }
//    }
    // 装载列表数据
    PageInfo<SystemRelationResult> pageInfo = new PageInfo<>(systemRelationResultList);
    PageUtil pageUtil = new PageUtil(pageInfo);
    return pageUtil;
  }

  @Override
  public CproResultParam editGetSystemRelationInfo(
      CproForeignRequestParam cproForeignRequestParam) throws BusinessException {
    SystemRelationParam systemRelationParam = new SystemRelationParam();
    systemRelationParam.setUserId(cproForeignRequestParam.getUserId());
    if(StringUtils.isNotBlank(systemRelationParam.getUserId())){
      HttpServletRequest request = 
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session = request.getSession();
      session.setAttribute("userId",systemRelationParam.getUserId());
    }
    CproResultParam getSystemRelationResult = new CproResultParam();
    
    List<SystemRelationResult> systemRelationResultList = this.systemRelationMapper.
        querySystemRelationInfo(systemRelationParam);
    
    List<SystemRelationBaseInfo> standardizedInfo = new ArrayList<SystemRelationBaseInfo>();

    if(ObjectUtils.isEmpty(systemRelationResultList)){
      for(SystemRelationResult systemRelationResult : systemRelationResultList){
        SystemRelationBaseInfo systemRelationBaseInfo = new SystemRelationBaseInfo();
        systemRelationBaseInfo.setSystemId(systemRelationResult.getSystemId());
        systemRelationBaseInfo.setSystemName(systemRelationResult.getSystemName());
        systemRelationBaseInfo.setSystemRelationId(systemRelationResult.getSystemRelationId());
        systemRelationBaseInfo.setSystemSmccCode(systemRelationResult.getSystemSmccCode());
        systemRelationBaseInfo.setSystemIsMerge(systemRelationResult.getSystemIsMerge());
        systemRelationBaseInfo.setFkCompanyCode(systemRelationResult.getFkCompanyCode());
        standardizedInfo.add(systemRelationBaseInfo);
      }
    }
    
    if(systemRelationResultList==null){
      return new CproResultParam();
    }
    getSystemRelationResult.setSystemRelationId(systemRelationResultList.get(0).getSystemRelationId());
    getSystemRelationResult.setSystemId(systemRelationResultList.get(0).getSystemId());
    getSystemRelationResult.setSystemName(systemRelationResultList.get(0).getSystemName());
    getSystemRelationResult.setSystemSmccCode(systemRelationResultList.get(0).getSystemSmccCode());
    getSystemRelationResult.setSystemIsMerge(systemRelationResultList.get(0).getSystemIsMerge());
    getSystemRelationResult.setFkCompanyCode(systemRelationResultList.get(0).getFkCompanyCode());
    getSystemRelationResult.setStandardizedInfo(standardizedInfo);

    return getSystemRelationResult;
  }

  @Override
  public void editSystemRelationInfo(
      CproForeignRequestParam getSystemRelationResult) throws BusinessException {
    if(StringUtils.isNotBlank(getSystemRelationResult.getUserId())){
      HttpServletRequest request = 
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session = request.getSession();
      session.setAttribute("userId",getSystemRelationResult.getUserId());
    }
    
//    SystemRelationParam systemRelationTempParam = new SystemRelationParam();
//    systemRelationTempParam.setSystemRelationId(getSystemRelationResult.getSystemRelationId());
//    SystemRelationResult systemRelationResult =  this.systemRelationMapper.
//        querySystemRelationById(systemRelationTempParam);
   
    if(getSystemRelationResult.getAddSystemInfo()!=null){
      List<SystemRelationParam> systemRelationList = new ArrayList<SystemRelationParam>();
      for (SystemRelationBaseInfo systemRelationParam:getSystemRelationResult.getAddSystemInfo()){
        SystemRelationParam systemRelParam = new SystemRelationParam();
        systemRelParam.setSystemRelationId(Utils.getUuidFor32());
        systemRelParam.setFkSystemId(systemRelationParam.getSystemId());
        systemRelParam.setFkCompanyCode(systemRelationParam.getFkCompanyCode());
        systemRelParam.setSystemName(systemRelationParam.getSystemName());
        systemRelParam.setSystemSource("0");
        systemRelParam.setSystemIsMerge(systemRelationParam.getSystemIsMerge());
        systemRelParam.setSystemSmccCode(systemRelationParam.getSystemSmccCode());
        systemRelParam.setCreateTime(new Date());
        systemRelationList.add(systemRelParam);
      }
      this.systemRelationMapper.insertBatchSystemPelation(systemRelationList);
    }
    if(getSystemRelationResult.getDeleteSystemInfo()!=null){
      for (SystemRelationBaseInfo systemRelationBaseInfo:getSystemRelationResult.
          getDeleteSystemInfo()){
        SystemRelationParam systemRelationParam = new SystemRelationParam();
        systemRelationParam.setSystemRelationId(Utils.getUuidFor32());
        systemRelationParam.setFkSystemId(systemRelationBaseInfo.getSystemId());
        systemRelationParam.setFkCompanyCode(systemRelationBaseInfo.getFkCompanyCode());
        systemRelationParam.setSystemName(systemRelationBaseInfo.getSystemName());
        systemRelationParam.setSystemSource("0");
        systemRelationParam.setSystemIsMerge(systemRelationBaseInfo.getSystemIsMerge());
        systemRelationParam.setSystemSmccCode(systemRelationBaseInfo.getSystemSmccCode());
        systemRelationParam.setCreateTime(new Date());
        this.systemRelationMapper.
          deleteSystemRelationInfoBySystemRelationId(systemRelationParam);
      }
    }
  }

  @Override
  public boolean deleteSystemRelationInfo(CproForeignRequestParam cproForeignRequestParam)
      throws BusinessException {
    SystemRelationParam systemRelationParam = new SystemRelationParam();
    systemRelationParam.setUserId(cproForeignRequestParam.getUserId());
    systemRelationParam.setSystemRelationId(cproForeignRequestParam.getSystemRelationId());
    if(StringUtils.isNotBlank(systemRelationParam.getUserId())){
      HttpServletRequest request = 
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session = request.getSession();
      session.setAttribute("userId",systemRelationParam.getUserId());
    }
    SystemRelationResult systemRelationResult =  this.systemRelationMapper.
        querySystemRelationById(systemRelationParam);
    
    if(systemRelationResult==null){
      return false;
    }
    if(systemRelationResult.getSystemSource().equals("1")){
      return false;
    }
    
    this.systemRelationMapper.
    deleteSystemRelationInfoBySystemRelationId(systemRelationParam);
    return true;
  }

  @Override
  public List<SystemRelationBaseInfo> getSystemRelationByGrade(
      String userId) throws BusinessException {
    if(StringUtils.isNotBlank(userId)){
      HttpServletRequest request = 
          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      HttpSession session = request.getSession();
      session.setAttribute("userId",userId);
    }
    
    SystemRelationParam systemRelationTempParam = new SystemRelationParam();
    systemRelationTempParam.setSystemIsMerge("0");
    List<SystemRelationResult> systemRelationResultList = this.systemRelationMapper.
        querySystemRelationInfo(systemRelationTempParam);
    List<SystemRelationBaseInfo> systemRelationBaseInfoList = new ArrayList<>();
    for(SystemRelationResult systemRelationResult : systemRelationResultList){
      SystemRelationBaseInfo systemRelationBaseInfo = new SystemRelationBaseInfo();
      systemRelationBaseInfo.setSystemRelationId(Utils.getUuidFor32());
      systemRelationBaseInfo.setFkSystemId(systemRelationResult.getSystemId());
      systemRelationBaseInfo.setFkCompanyCode(systemRelationResult.getFkCompanyCode());
      systemRelationBaseInfo.setSystemName(systemRelationResult.getSystemName());
      systemRelationBaseInfo.setSystemSource(systemRelationResult.getSystemSource());
      systemRelationBaseInfo.setSystemIsMerge(systemRelationResult.getSystemIsMerge());
      systemRelationBaseInfo.setSystemSmccCode(systemRelationResult.getSystemSmccCode());
      systemRelationBaseInfo.setCreateTime(systemRelationResult.getCreateTime());
      systemRelationBaseInfoList.add(systemRelationBaseInfo);
    }
    
    return systemRelationBaseInfoList;
  }
  
  /**
   * 企业管理员定级审核
   */
  @Override
  @Transactional
  public String saveGradCheckApi(String userName, CheckParam checkParam) 
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setUpdateUserName(userName);
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("企业业务审核（定级）");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckReason());
    if (checkParam.getScoreCheckResult() == 1) {
    //通过定级审核
      nodeParam.setOperationResult("通过");
//      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("2");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckResult(1);
//      editCheckStatusBySystemId(check);
      
      //审核通过
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskid(),
          String.valueOf(userDTO.getUserId()),userName,"2",checkParam.getBusinessId(),"定级");
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("3");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckResult(2);
//      check.setScoreCheckReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);

      //审核未通过
      workFlowApiServiceImpl.reviewNotThrough(checkParam.getBusinessId(),
          String.valueOf(userDTO.getUserId()),userName,"3","定级",checkParam.getScoreCheckReason());
      
      //修改系统状态为
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    } 
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
  
  /**
   * 总部管理员定级审核
   */
  @Override
  @Transactional
  public String saveHeadGradCheckApi(String userName, CheckParam checkParam) 
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setUpdateUserName(userName);
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("总部业务审核（定级）");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckReason());
    if (checkParam.getScoreCheckResult() == 1) {
      //通过定级审核
      nodeParam.setOperationResult("通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("5");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckResult(1);
//      editCheckStatusBySystemId(check);
      
      //审核通过
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskid(),
          String.valueOf(userDTO.getUserId()),userName,"4",checkParam.getBusinessId(),"定级");
      
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("3");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("4");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckResult(2);
//      check.setScoreCheckReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);

      //审核未通过
      workFlowApiServiceImpl.reviewNotThrough(checkParam.getBusinessId(),
          String.valueOf(userDTO.getUserId()),userName,"5","定级",checkParam.getScoreCheckReason());
      
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    } 
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
  
  /**
   * 企业管理员撤销备案审核
   */
  @Override
  @Transactional
  public String saveCancelRecordsCheckApi(String userName, CheckParam checkParam)
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setUpdateUserName(userName);
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("企业业务审核（撤销备案）");
    nodeParam.setOperationOpinion(checkParam.getCancelRecordsReason());
    if (checkParam.getCancelRecordsResult() ==1 ) {
      nodeParam.setOperationResult("通过");
//      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("5");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setCancelRecordsResult(1);
//      check.setCancelRecordsReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);
      
      //审核通过
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskid(),
          String.valueOf(userDTO.getUserId()),userName,"2",checkParam.getBusinessId(),"撤销备案");
    
    //修改系统状态
    MainParam mainParam = new MainParam();
    mainParam.setGradingStatus("4");
    mainParam.setRecordStatus("4");
    mainParam.setExamineStatus("5");
    mainParam.setEvaluationStatus("4");
    mainParam.setExaminationStatus("4");
    mainParam.setSystemId(checkParam.getFkSystemId());
    mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
//      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("4");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setCancelRecordsResult(2);
//      check.setCancelRecordsReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);
      
      //审核未通过
      workFlowApiServiceImpl.reviewNotThrough(checkParam.getBusinessId(),
          String.valueOf(userDTO.getUserId()),userName,"3","撤销备案",
          checkParam.getCancelRecordsReason());
      
      //修改系统状态
      MainParam mainParam = new MainParam();
//      mainParam.setRecordStatus("3");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
   
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
  
  /**
   * 企业管理员定级变更审核
   */
  @Override
  @Transactional
  public String saveGradChangeCheckApi(String userName, CheckParam checkParam)
      throws BusinessException {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setUpdateUserName(userName);
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("企业业务审核（定级变更）");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckChangeReason());
    if (checkParam.getScoreCheckChangeResult() == 1) {
      nodeParam.setOperationResult("通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("2");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckChangeResult(1);
//      check.setScoreCheckReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);
      
      //审核通过
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskid(),String.valueOf(userDTO.getUserId()),
          userName,"2",checkParam.getBusinessId(),"申请变更");
      
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("3");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckChangeResult(2);
//      check.setScoreCheckChangeReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);

      //审核未通过
      workFlowApiServiceImpl.reviewNotThrough(checkParam.getBusinessId(),
          String.valueOf(userDTO.getUserId()),userName,"3","申请变更",
          checkParam.getScoreCheckChangeReason());
      
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
  
  /**
   * 总部管理员定级变更审核
   */
  @Override
  @Transactional
  public String saveHeadGradChangeCheckApi(String userName, CheckParam checkParam)
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    checkParam.setUpdateUserName(userName);
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("总部业务审核（定级变更）");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckChangeReason());
    if (checkParam.getScoreCheckChangeResult() == 1) {
      nodeParam.setOperationResult("通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("5");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckChangeResult(1);
//      check.setScoreCheckReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);
      //审核通过
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskid(),
          String.valueOf(userDTO.getUserId()),userName,"4",checkParam.getBusinessId(),"申请变更");

      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("3");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
//      CheckParam check = new CheckParam();
//      check.setFkSystemId(checkParam.getFkSystemId());
//      check.setFkExaminStatus("4");
//      check.setPrevExecutor(userName);
//      check.setExecuteTime(new Date());
//      check.setScoreCheckChangeResult(2);
//      check.setScoreCheckChangeReason(checkParam.getScoreCheckReason());
//      editCheckStatusBySystemId(check);
      
      //审核未通过
      workFlowApiServiceImpl.reviewNotThrough(checkParam.getBusinessId(),
          String.valueOf(userDTO.getUserId()),userName,"5","申请变更",
          checkParam.getScoreCheckChangeReason());
      
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
}
