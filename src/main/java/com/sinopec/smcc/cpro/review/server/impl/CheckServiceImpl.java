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

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.ubs.client.UbsClient;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.mapper.CheckMapper;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.review.uitl.ConvertFieldUtil;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.server.SystemService;

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
  UbsClient ubsClient;
  @Value("${appId}") 
  private String appId;
  
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
    List<CheckListResult> list = this.checkMapper.selectAllByCheckParam(checkParam);
    //装载列表数据
    PageInfo<CheckListResult> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
  
  /**
   * 定级审核
   */
  @Override
  @Transactional
  public String saveGradCheck(String userName, CheckParam checkParam) 
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    //获取系统信息，判别信息系统建设类型(code)：1：自建；2：统建；3：总部系统；
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemId(checkParam.getFkSystemId());
    SystemResult systemResult = this.systemServiceImpl.queryDetailsSystem(systemParam);
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("定级审核");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckReason());
    switch (checkParam.getScoreCheckResult()) {
    //通过定级审核
    case 1:
      switch (systemResult.getConstructionTypeName()) {
      //自建
      case "1":
      //统建
      case "2":
        checkParam.setFkExaminStatus("待总部安全管理员审核");
        break;
      //总部系统
      case "3":
        checkParam.setFkExaminStatus("归档");
        break;
      default:
        break;
      }
      nodeParam.setOperationResult("通过");
      break;
    //未通过审核
    case 2:
      //未通过定级审核
      switch (systemResult.getConstructionTypeName()) {
      case "1":
      case "2":
        checkParam.setFkExaminStatus("企业安全员管理审核未通过");
        break;
      case "3":
        checkParam.setFkExaminStatus("总部安全管理员审核未通过");
        break;
      default:
        break;
      }
      nodeParam.setOperationResult("未通过");
      break;
    default:
      break;
    }
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    this.checkMapper.updateCheckBySystemId(checkParam);
    return checkParam.getFkSystemId();
  }

  /**
   * 定级变更审核
   */
  @Override
  @Transactional
  public String saveGradChangeCheck(String userName, CheckParam checkParam)
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    
    //获取系统信息，判别信息系统建设类型(code)：1：自建；2：统建；3：总部系统；
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemId(checkParam.getFkSystemId());
    SystemResult systemResult = this.systemServiceImpl.queryDetailsSystem(systemParam);
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(checkParam.getFkSystemId());
    nodeParam.setOperation("变更审批");
    nodeParam.setOperationOpinion(checkParam.getScoreCheckChangeReason());
    switch (checkParam.getScoreCheckChangeResult()) {
    //通过定级审核
    case 1:
      switch (systemResult.getConstructionTypeName()) {
      //自建
      case "1":
      //统建
      case "2":
        checkParam.setFkExaminStatus("待总部安全管理员审核");
        break;
      //总部系统
      case "3":
        checkParam.setFkExaminStatus("归档");
        break;
      default:
        break;
      }
      nodeParam.setOperationResult("通过");
      break;
    //未通过审核
    case 2:
      //未通过定级审核
      switch (systemResult.getConstructionTypeName()) {
      case "1":
      case "2":
        checkParam.setFkExaminStatus("企业安全员管理审核未通过");
        break;
      case "3":
        checkParam.setFkExaminStatus("总部安全管理员审核未通过");
        break;
      default:
        break;
      }
      nodeParam.setOperationResult("未通过");
      break;
    default:
      break;
    }
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
    this.checkMapper.updateCheckBySystemId(checkParam);
    return checkParam.getFkSystemId();
  }

  /**
   * 撤销备案审核
   */
  @Override
  @Transactional
  public String saveCancelRecordsCheck(String userName, CheckParam checkParam)
      throws BusinessException {
    if (StringUtils.isBlank(checkParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    
    //获取系统信息，判别信息系统建设类型(code)：1：自建；2：统建；3：总部系统；
    SystemParam systemParam = new SystemParam();
    systemParam.setSystemId(checkParam.getFkSystemId());
    SystemResult systemResult = this.systemServiceImpl.queryDetailsSystem(systemParam);
    checkParam.setPrevExecutor(userName);
    checkParam.setExecuteTime(new Date());
    if (systemResult != null) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(checkParam.getFkSystemId());
      nodeParam.setOperation("撤销备案审批");
      nodeParam.setOperationOpinion(checkParam.getCancelRecordsReason());
      switch (checkParam.getCancelRecordsResult()) {
      //通过定级审核
      case 1:
        switch (systemResult.getConstructionTypeName()) {
        //自建
        case "1":
          //统建
        case "2":
          checkParam.setFkExaminStatus("待总部安全管理员审核");
          break;
          //总部系统
        case "3":
          checkParam.setFkExaminStatus("归档");
          break;
        default:
          break;
        }
        nodeParam.setOperationResult("通过");
        break;
        //未通过审核
      case 2:
        //未通过定级审核
        switch (systemResult.getConstructionTypeName()) {
        case "1":
        case "2":
          checkParam.setFkExaminStatus("企业安全员管理审核未通过");
          break;
        case "3":
          checkParam.setFkExaminStatus("总部安全管理员审核未通过");
          break;
        default:
          break;
        }
        nodeParam.setOperationResult("未通过");
        break;
      default:
        break;
      }
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    this.checkMapper.updateCheckBySystemId(checkParam);
    return checkParam.getFkSystemId();
  }
  
}
