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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.entity.BatchCheckHandleParam;
import com.sinopec.smcc.cpro.api.entity.GradingApiResult;
import com.sinopec.smcc.cpro.api.entity.UsmgParams;
import com.sinopec.smcc.cpro.api.service.ApiService;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationParam;
import com.sinopec.smcc.cpro.system.entity.SystemRelationResult;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.mapper.SystemRelationMapper;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.depends.dps.util.DpsConfig;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;

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
  
  /**
   * 获取定级信息
   */
  @Override
  public GradingApiResult getGradingInformation(String systemCode) throws BusinessException {
    SystemParam systemParam = new SystemParam();
    systemParam.setStandardizedCode(systemCode);
    SystemResult systemResult = systemServiceImpl.querySystemBysystemCode(systemParam);
    if(systemResult != null){
      GradingApiResult gradingApiResult = new GradingApiResult();
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
  public PageInfo<CheckListResult> getStayHandle(UsmgParams usmgParams,String userId) throws BusinessException {
    List<CheckListResult> list = new ArrayList<CheckListResult>();
    int appPagedTODOTaskTotal = 0;
    int appPagedTODOTaskPageNum = 0;
    Map<String,String> extMap = new HashMap<String,String>();
    //版本号
    extMap.put("ext003", WorkFlowConsts.CATEGORY_VERSION_NUM);
    //获取待办列表
    final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(userId,10,
        usmgParams.getCurrPage(),"",WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
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
    return pageInfo;
  }

  /**
   * 批量审批
   */
  @Override
  public Integer batchApproval(BatchCheckHandleParam batchCheckHandleParam)
      throws BusinessException {
    HttpServletRequest request = 
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    int count = 0;
    if(!ObjectUtils.isEmpty(batchCheckHandleParam.getCheckList())){
      for(CheckParam checkParam : batchCheckHandleParam.getCheckList()){
        session.setAttribute("userId",checkParam.getUserId());
        //权限
        JurisdictionDataResult organizationApiResult = 
            this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
        List<String> juri =  organizationApiResult.getPermssions();
        boolean isJur = juri.contains("0102010101");
        if(StringUtils.isNotBlank(checkParam.getFkSystemId())){
          //业务节点定级
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("1")){
            checkParam.setScoreCheckReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              checkServiceImpl.saveGradCheck(batchCheckHandleParam.getUserName(),checkParam);
            }else{
              checkServiceImpl.saveHeadGradCheck(batchCheckHandleParam.getUserName(),checkParam);
            }
            count++;
          }
          //业务节点撤销备案
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("2")){
            checkParam.setCancelRecordsReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              checkServiceImpl.saveCancelRecordsCheck(batchCheckHandleParam.getUserName(),checkParam);
            }
            count++;
          }
          //业务节点申请变更
          if(StringUtils.isNotBlank(checkParam.getFkBusinessNode()) && 
              checkParam.getFkBusinessNode().equals("3")){
            checkParam.setScoreCheckChangeReason(batchCheckHandleParam.getOpinion());
            //1企业 2总部
            if(!isJur){
              checkServiceImpl.saveGradChangeCheck(batchCheckHandleParam.getUserName(),checkParam);
            }else{
              checkServiceImpl.saveHeadGradChangeCheck(batchCheckHandleParam.getUserName(),
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
  public PageInfo<SystemRelationResult> getSystemRelationInfo(SystemRelationParam systemRelationParam) 
      throws BusinessException {
    
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
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return new PageInfo<>();
    }else{
      
      // 初始化分页拦截器
      PageHelper.startPage(systemRelationParam.getCurrentPage(), systemRelationParam.getPageSize(),
          orderBy.toString());
      
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        systemRelationResultList = this.systemRelationMapper.
            querySystemRelationInfo(systemRelationParam);
        break;
      case "2":
        systemRelationParam.setPlateList(organizationApiResult.getNameList());
        systemRelationResultList = this.systemRelationMapper.
            querySystemRelationInfo(systemRelationParam);
        break;
      case "3":
        systemRelationParam.setCompanyList(organizationApiResult.getCodeList());
        systemRelationResultList = this.systemRelationMapper.
            querySystemRelationInfo(systemRelationParam);
        break;

      default:
        break;
      }
    }
    // 装载列表数据
    PageInfo<SystemRelationResult> pageInfo = new PageInfo<>(systemRelationResultList);
    return pageInfo;
  }
}
