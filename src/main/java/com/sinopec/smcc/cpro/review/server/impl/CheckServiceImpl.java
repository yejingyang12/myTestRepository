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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.depends.ubs.client.UbsClient;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;
import com.sinopec.smcc.cpro.review.mapper.CheckMapper;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.review.uitl.ConvertFieldUtil;
import com.sinopec.smcc.cpro.system.server.SystemService;
import com.sinopec.smcc.cpro.tools.Utils;

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
  @Autowired
  UbsClient ubsClient;
  @Value("${appId}") 
  private String appId;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  
  /**
   * 获取备案信息列表数据
   */
  @Override
  public PageInfo<CheckListResult> queryCheckList(CheckParam checkParam) throws BusinessException{
    //创建排序字段
    StringBuffer orderBy = new StringBuffer();
    //判断field是否有值
    if(StringUtils.isNotBlank(checkParam.getField())){
      //如有值，则将排序字段放入orderBy对象
      orderBy.append(ConvertFieldUtil.sortField(checkParam.getField()));
      if(StringUtils.isNotBlank(checkParam.getSort())){
        orderBy.append(" ").append(checkParam.getSort());
      }
    }else {
      //默认排序规则
      orderBy.append("che.createtime desc");
    }
    //初始化分页拦截器
    PageHelper.startPage(checkParam.getCurrentPage(), checkParam.getPageSize(),orderBy.toString());
    //获得相应列表数据
    List<CheckListResult> list = new ArrayList<CheckListResult>();
    
//    //权限
//    JurisdictionDataResult organizationApiResult = 
//        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
//    
//    if(organizationApiResult==null){
//      return new PageInfo<>();
//    }else{
//      
//      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
//      switch (organizationApiResult.getResultType()) {
//      
//      case "0":
//        break;
//      case "1":
//        // 获得响应列表数据
        list = 
            this.checkMapper.selectAllByCheckParam(checkParam);
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
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("定级审核");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckReason());
    if (checkParam.getScoreCheckResult() == 1) {
    //通过定级审核
      nodeParam.setOperationResult("通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("2");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckResult(1);
      editCheckStatusBySystemId(check);
   
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("3");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckResult(2);
      check.setScoreCheckReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态为
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
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
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("定级审核");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckReason());
    if (checkParam.getScoreCheckResult() == 1) {
      //通过定级审核
      nodeParam.setOperationResult("通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("5");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckResult(1);
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("3");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("4");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckResult(2);
      check.setScoreCheckReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
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
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("变更审核");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckChangeReason());
    if (checkParam.getScoreCheckChangeResult() == 1) {
      nodeParam.setOperationResult("通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("2");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckChangeResult(1);
      check.setScoreCheckReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("3");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckChangeResult(2);
      check.setScoreCheckChangeReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
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
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("变更审核");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckChangeReason());
    if (checkParam.getScoreCheckChangeResult() == 1) {
      nodeParam.setOperationResult("通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("5");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckChangeResult(1);
      check.setScoreCheckReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("3");
      mainParam.setExamineStatus("3");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("4");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setScoreCheckChangeResult(2);
      check.setScoreCheckChangeReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    return checkParam.getFkSystemId();
  }
  /**
   * 总部管理员撤销备案审核
   */
  @Override
  @Transactional
  public String saveCancelRecordsCheck(String userName, CheckParam checkParam)
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("撤销备案审核");
    nodeParam.setOperationOpinion(checkParam.getCancelRecordsReason());
    if (checkParam.getCancelRecordsResult() ==1 ) {
      nodeParam.setOperationResult("通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("5");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setCancelRecordsResult(1);
      check.setCancelRecordsReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setExamineStatus("3");
      mainParam.setRecordStatus("4");
      mainParam.setEvaluationStatus("4");
      mainParam.setExaminationStatus("4");
      mainParam.setSystemId(checkParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }else{
      nodeParam.setOperationResult("未通过");
      //修改审核状态
      CheckParam check = new CheckParam();
      check.setFkSystemId(checkParam.getFkSystemId());
      check.setFkExaminStatus("4");
      check.setPrevExecutor(userName);
      check.setExecuteTime(new Date());
      check.setCancelRecordsResult(2);
      check.setCancelRecordsReason(checkParam.getScoreCheckReason());
      editCheckStatusBySystemId(check);
      //修改系统状态
      MainParam mainParam = new MainParam();
      mainParam.setRecordStatus("2");
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
