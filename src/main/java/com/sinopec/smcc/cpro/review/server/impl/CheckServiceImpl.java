/**
* 2018. 
* @Title CheckServiceImpl.java
* @Package com.sinopec.smcc.cpro.review.server.impl
* @Description: TODO:
* @author zhouyu
* @date 2018年5月25日下午2:54:06
* @version V1.0
*/
package com.sinopec.smcc.cpro.review.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageInfo;
import com.pcitc.ssc.dps.inte.workflow.AppTaskOpinionData;
import com.pcitc.ssc.dps.inte.workflow.ExecuteTaskData;
import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.constant.WorkFlowConsts;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowParam;
import com.sinopec.smcc.cpro.codeapi.entity.WorkFlowResult;
import com.sinopec.smcc.cpro.codeapi.mapper.WorkFlowMapper;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.MessageService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.codeapi.server.WorkFlowApiService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;
import com.sinopec.smcc.cpro.review.mapper.CheckMapper;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.tools.Utils;
import com.sinopec.smcc.depends.dps.util.DpsTemplate;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

/**
 * @Title CheckServiceImpl.java
 * @Package com.sinopec.smcc.cpro.review.server.impl
 * @Description: TODO:审核管理实现impl类
 * @author zhouyu
 * @date 2018年5月25日下午2:54:06
 * @version V1.0
 */
@Service
public class CheckServiceImpl implements CheckService {

  @Autowired
  private CheckMapper checkMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private SystemService systemServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  @Value("${appId}") 
  private String appId;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  @Autowired
  private UserApiService userApiServiceImpl;
  @Autowired
  private DpsTemplate dpsTemplate;
  @Autowired
  private WorkFlowApiService workFlowApiServiceImpl;
  @Autowired
  private MessageService messageServiceImpl;
  @Autowired
  private UbsTemplate ubsTemplate;
  @Autowired
  private WorkFlowMapper workFlowMapperImpl;
  
  /**
   * 获取备案信息列表数据
   */
  @Override
  public PageInfo<CheckListResult> queryCheckList(CheckParam checkParam) throws BusinessException{
    //创建排序字段
//    StringBuffer orderBy = new StringBuffer();
//    //判断field是否有值
//    if(StringUtils.isNotBlank(checkParam.getField())){
//      //如有值，则将排序字段放入orderBy对象
//      orderBy.append(ConvertFieldUtil.sortField(checkParam.getField()));
//      if(StringUtils.isNotBlank(checkParam.getSort())){
//        orderBy.append(" ").append(checkParam.getSort());
//      }
//    }else {
//      //默认排序规则
//      orderBy.append("che.createtime desc");
//    }
    
    //获得相应列表数据
    List<CheckListResult> list = new ArrayList<CheckListResult>();
  
    //获取用户信息
    UserDTO user=userApiServiceImpl.getUserInfo();
    String UserId=String.valueOf(user.getUserId());
    
    int appPagedTODOTaskTotal = 0;
    int appPagedTODOTaskPageNum = 0;
    //获取待办列表
    if(checkParam.getHandlingState() == 1){
      Map<String,String> extMap = new HashMap<String,String>();
      //版本号
      extMap.put("ext003", WorkFlowConsts.CATEGORY_VERSION_NUM);
      //系统名称
      if(StringUtils.isNotBlank(checkParam.getInstanceName())){
        extMap.put("ext004", checkParam.getInstanceName());
      }
      //发起人
      if(StringUtils.isNotBlank(checkParam.getInitiator())){
        extMap.put("ext007", checkParam.getInitiator());
      }
      //建设类型
      if(StringUtils.isNotBlank(checkParam.getFkInfoSysTypeCon())){
        extMap.put("ext005", checkParam.getFkInfoSysTypeCon());
      }
      //业务节点
      if(StringUtils.isNotBlank(checkParam.getFkBusinessNode())){
        if(checkParam.getFkBusinessNode().equals("1")){
          extMap.put("ext006", "定级");
        }
        if(checkParam.getFkBusinessNode().equals("2")){
          extMap.put("ext006", "撤销备案");        
        }
        if(checkParam.getFkBusinessNode().equals("3")){
          extMap.put("ext006", "申请变更");   
        }
      }
      
      //权限
//      JurisdictionDataResult organizationApiResult = 
//          this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
//      List<String> orgStr = organizationApiResult.getPermssions();
//      //企业权限  
//      if(orgStr.contains("0102010301")||orgStr.contains("0102010201")){
//        //单位Code
//        extMap.put("ext008", jurisdictionApiServiceImpl.getCompanyCode());
//      } 
      final PagedList appPagedTODOTask = dpsTemplate.appPagedTODOTask(UserId,10,
          checkParam.getCurrentPage(),"",WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
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
            if(StringUtils.isNotBlank(systemResult.getRecordReportName())){
              checkListResult.setRecordReportName(systemResult.getRecordReportName());
            }
            if(StringUtils.isNotBlank(systemResult.getRecordReportId())){
              checkListResult.setRecordReportId(systemResult.getRecordReportId());
            }
            if(StringUtils.isNotBlank(systemResult.getExpertReviewId())){
              checkListResult.setExpertReviewId(systemResult.getExpertReviewId());
            }
            if(StringUtils.isNotBlank(systemResult.getExpertReviewName())){
              checkListResult.setExpertReviewName(systemResult.getExpertReviewName());
            }
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
            if(executeTaskData.getActivityName().equals("企业主联络员审批")){
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
    }
    
    //获取已办列表
    if(checkParam.getHandlingState() == 2){
      Map<String,String> extMap = new HashMap<String,String>();
      //版本号
      extMap.put("ext003", WorkFlowConsts.CATEGORY_VERSION_NUM);
      //系统名称
      if(StringUtils.isNotBlank(checkParam.getInstanceName())){
        extMap.put("ext004", checkParam.getInstanceName());
      }
      //发起人
      if(StringUtils.isNotBlank(checkParam.getInitiator())){
        extMap.put("ext007", checkParam.getInitiator());
      }
      //建设类型
      if(StringUtils.isNotBlank(checkParam.getFkInfoSysTypeCon())){
        extMap.put("ext005", checkParam.getFkInfoSysTypeCon());
      }
      //业务节点
      if(StringUtils.isNotBlank(checkParam.getFkBusinessNode())){
        if(checkParam.getFkBusinessNode().equals("1")){
          extMap.put("ext006", "定级");
        }
        if(checkParam.getFkBusinessNode().equals("2")){
          extMap.put("ext006", "撤销备案");        
        }
        if(checkParam.getFkBusinessNode().equals("3")){
          extMap.put("ext006", "申请变更");   
        }
      }
      //权限
//      JurisdictionDataResult organizationApiResult = 
//          this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
//      List<String> orgStr = organizationApiResult.getPermssions();
//      //企业权限  
//      if(orgStr.contains("0102010301")||orgStr.contains("0102010201")){
//        //单位Code
//        extMap.put("ext008", jurisdictionApiServiceImpl.getCompanyCode());
//      }
      final PagedList appPagedTODOTaskHaveDone = dpsTemplate.appPagedDoneTask(UserId,10,
          checkParam.getCurrentPage(), "",WorkFlowConsts.CATEGORY_CODE_CPRO,extMap);
      appPagedTODOTaskTotal = appPagedTODOTaskHaveDone.getTotalCount();
      appPagedTODOTaskPageNum = appPagedTODOTaskHaveDone.getPageIndex();
      if((appPagedTODOTaskHaveDone.getExecuteTaskList())!=null){
        List<ExecuteTaskData> haveDoneList= appPagedTODOTaskHaveDone.getExecuteTaskList();
        for(ExecuteTaskData executeTaskData:haveDoneList){
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
            if(StringUtils.isNotBlank(systemResult.getRecordReportName())){
              checkListResult.setRecordReportName(systemResult.getRecordReportName());
            }
            if(StringUtils.isNotBlank(systemResult.getRecordReportId())){
              checkListResult.setRecordReportId(systemResult.getRecordReportId());
            }
            if(StringUtils.isNotBlank(systemResult.getExpertReviewId())){
              checkListResult.setExpertReviewId(systemResult.getExpertReviewId());
            }
            if(StringUtils.isNotBlank(systemResult.getExpertReviewName())){
              checkListResult.setExpertReviewName(systemResult.getExpertReviewName());
            }
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
            //待总部安全管理员审核
            checkListResult.setFkExaminStatus("2");
            //获取审批历史
            List<AppTaskOpinionData> appTaskOpinionDataList = 
                dpsTemplate.appOpinion(executeTaskData.getBusinessId());
            //如果审批历史记录大于等于2，则表示总部已进行审批
            if(appTaskOpinionDataList != null && appTaskOpinionDataList.size() >=2){
              if(appTaskOpinionDataList.get(0).getExecuteResult() == 2){
                //归档
                checkListResult.setFkExaminStatus("5");
              }else{
                //总部审核未通过 
                checkListResult.setFkExaminStatus("4");
              }
            }
          }else if(executeTaskData.getExecuteResult() == 2){
            if(executeTaskData.getActivityName().equals("企业主联络员审批") && 
                !executeTaskData.getBusinessName().equals("撤销备案")){
              //获取审批历史
              List<AppTaskOpinionData> appTaskOpinionDataList = 
                  dpsTemplate.appOpinion(executeTaskData.getBusinessId());
              if(!ObjectUtils.isEmpty(appTaskOpinionDataList) && appTaskOpinionDataList.size() >=2){
                AppTaskOpinionData appTaskOpinionData = appTaskOpinionDataList.get(0);
                if(appTaskOpinionData.getExecuteResult() == 2){
                  //归档
                  checkListResult.setFkExaminStatus("5");
                }
                if(appTaskOpinionData.getExecuteResult() == 3){
                //如果总部未通过  则状态为 总部安全管理员审核未通过
                  checkListResult.setFkExaminStatus("4");
                }
              }else{
                //如果企业安全员已通过，则该状态为待总部安全管理员审核
                checkListResult.setFkExaminStatus("2");
              }
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
    }
    
//    //权限
//    JurisdictionDataResult organizationApiResult = 
//        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
//    
//    if(organizationApiResult==null){
//      return new PageInfo<>();
//    }else{
//    //初始化分页拦截器
////      PageHelper.startPage(checkParam.getCurrentPage(), checkParam.getPageSize(),orderBy.toString());
//      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
//      switch (organizationApiResult.getResultType()) {
//      case "0":
//        break;
//      case "1":
//        // 获得响应列表数据
//        list = 
//            this.checkMapper.selectAllByCheckParam(checkParam);
//        break;
//      case "2":
//        checkParam.setPlateList(organizationApiResult.getNameList());
//        list =  
//            this.checkMapper.selectAllByCheckParam(checkParam);
//        break;
//      case "3":
//        checkParam.setCompanyList(organizationApiResult.getCodeList());
//        list =  
//            this.checkMapper.selectAllByCheckParam(checkParam);
//        break;
//
//      default:
//        break;
//      }
//    }
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
   * 企业管理员定级审核
   */
  @Override
  @Transactional
  public String saveGradCheck(String userName, CheckParam checkParam) 
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
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskId(),
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
      
      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,1,
            "3", checkParam.getScoreCheckReason(),workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
      
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
  public String saveHeadGradCheck(String userName, CheckParam checkParam) 
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
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskId(),
          String.valueOf(userDTO.getUserId()),userName,"4",checkParam.getBusinessId(),"定级");

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null, 1,
            "4", "",workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
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

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null, 1,
            "5", checkParam.getScoreCheckReason(),workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
      
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
   * 企业管理员定级变更审核
   */
  @Override
  @Transactional
  public String saveGradChangeCheck(String userName, CheckParam checkParam)
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
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskId(),String.valueOf(userDTO.getUserId()),
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

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,3,
            "3", checkParam.getScoreCheckChangeReason(),workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
      
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
  public String saveHeadGradChangeCheck(String userName, CheckParam checkParam)
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
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskId(),
          String.valueOf(userDTO.getUserId()),userName,"4",checkParam.getBusinessId(),"申请变更");

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,3,
            "4", "",workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
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

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,3,
            "5", checkParam.getScoreCheckChangeReason(),workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
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
  public String saveCancelRecordsCheck(String userName, CheckParam checkParam)
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
      workFlowApiServiceImpl.reviewPass(checkParam.getTaskId(),
          String.valueOf(userDTO.getUserId()),userName,"2",checkParam.getBusinessId(),"撤销备案");

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,2,
            "2", "",workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
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

      WorkFlowParam workFlowParam = new WorkFlowParam();
      workFlowParam.setBusinessId(checkParam.getBusinessId());
      WorkFlowResult workFlowResult
        = workFlowMapperImpl.selectWorkFlowByBusinessId(workFlowParam);
      List<UserDTO> root = this.messageServiceImpl.getUsersByUserId(workFlowResult.getUserId());
      String email = "";
      for (UserDTO userDTO2 : root) {
        if(StringUtils.isNotBlank(userDTO2.getEmail())){
          email +=userDTO2.getEmail() + ",";
        }
      }
      if(StringUtils.isNotBlank(email)){
        String [] emailArr = email.split(",");
        //发送邮件
        this.messageServiceImpl.sendMessageForCheck(emailArr, null,2,
            "3", checkParam.getCancelRecordsReason(),workFlowResult.getSystemId());
        workFlowParam.setNextApprover("null");
        workFlowMapperImpl.updateWorkFlowByBusinessId(workFlowParam);
      }
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
   * 修改审核状态
   */
  @Override
  public void editCheckStatusBySystemId(CheckParam checkParam) throws BusinessException {
    this.checkMapper.updateCheckStatusBySystemId(checkParam);
  }

  /**
   * @Descrption 添加审核信息
   * @author dongxu
   * @date 2018年6月20日下午1:53:09
   * @param checkParam
   * @throws BusinessException
   */
  @Override
  public void addCheck(CheckParam checkParam) throws BusinessException {
    checkParam.setCheckId(Utils.getUuidFor32());
    checkParam.setCreateTime(new Date());
    checkParam.setDeleteStatus(1);
    checkParam.setCreateUserName("");
    this.checkMapper.insertCheck(checkParam);
  }

  /**
   * 查询审核详情
   */
  @Override
  public CheckResult queryCheckInfoBySystemId(CheckParam checkParam) throws BusinessException {
    return this.checkMapper.selectCheckInfoBySystemId(checkParam);
  }

  /**
   * 通过审核ID删除审核记录
   */
  @Override
  public void deleteCheckByCheckId(CheckParam checkParam) throws BusinessException {
     this.checkMapper.deleteCheckByCheckId(checkParam);
  }
  
}
