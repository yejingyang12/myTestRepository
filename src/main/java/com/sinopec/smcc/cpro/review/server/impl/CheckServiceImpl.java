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
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.common.result.ResultApi;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeListResult;
import com.sinopec.smcc.cpro.review.entity.CheckNodeParam;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.mapper.CheckMapper;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.review.uitl.ConvertFieldUtil;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
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
  private SystemMapper systemMapper;
  
 
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_check")
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
   orderBy.append("createtime desc");
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
   * 审核节点新增
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_check_node")
  public String checkNodeSave(CheckNodeListResult checkNodeListResult,String fkExaminStatus,String fkbusinessNode,String checkId,String fkSystemId) throws BusinessException {
    if (StringUtils.isNotBlank(checkNodeListResult.getCheckNodeId())) {
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    checkNodeListResult.setCheckNodeId(Utils.getUuidFor32());
    checkNodeListResult.setCreateTime(new Date());
    checkNodeListResult.setExecuteTime(new Date());
    this.checkMapper.checkNodeSave(checkNodeListResult);
//    int istepId = Integer.parseInt(fkExaminStatus);
//    int businessNode = Integer.parseInt(fkbusinessNode);
    if (StringUtils.isNotBlank(fkbusinessNode)) {
      CheckParam checkParam = new CheckParam();
      SystemParam systemParam = new SystemParam();
      systemParam.setSystemId(fkSystemId);
      switch (fkbusinessNode) {
      case "1":
        //定级审核
        if("1".equals(fkExaminStatus)){
        //待企业安全员管理审核
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果企业管理员审核通过
            fkExaminStatus = "2";
            systemParam.setGradingStatus(2);
          } else {
            //如果企业管理员审核未通过
            fkExaminStatus = "3";
            systemParam.setGradingStatus(1);
          }
          this.systemMapper.updateKeySystem(systemParam);
        }else if("2".equals(fkExaminStatus)){
         //待总部安全管理员审核 
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果总部管理员审核通过
            fkExaminStatus = "5";
            systemParam.setGradingStatus(3);
          } else {
            //如果总部管理员审核未通过
            fkExaminStatus = "4";
            systemParam.setGradingStatus(1);
          }
          this.systemMapper.updateKeySystem(systemParam);
        } 
        break;
      case "2":
        //撤销备案审核
        if ("1".equals(fkExaminStatus)) {
        //待企业安全员管理审核 
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果企业管理员审核通过
            fkExaminStatus = "5";
            systemParam.setRecordStatus(4);
          }else if (2 == checkNodeListResult.getExamineResult()){
            //如果企业管理员审核未通过
            fkExaminStatus = "3";
            systemParam.setRecordStatus(3);
          }
          this.systemMapper.updateKeySystem(systemParam);
        }else if ("2".equals(fkExaminStatus)) {
        //待总部安全管理员审核
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果总部管理员审核通过
            fkExaminStatus = "5";
            systemParam.setRecordStatus(4);
          }else if (2 == checkNodeListResult.getExamineResult()){
            //如果总部管理员审核未通过
            fkExaminStatus = "4";
            systemParam.setRecordStatus(3);
          }
          this.systemMapper.updateKeySystem(systemParam);
        }
        break;
      case "3":
        //定级信息变更
        if("1".equals(fkExaminStatus)){
         //待企业安全管理员审核
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果企业管理员审核通过
            fkExaminStatus = "5";
            systemParam.setGradingStatus(2);
          }else if (2 == checkNodeListResult.getExamineResult()){
            //如果企业管理员审核未通过
            fkExaminStatus = "3";
            systemParam.setGradingStatus(3);
          }
          this.systemMapper.updateKeySystem(systemParam);
        }else if("2".equals(fkExaminStatus)){
        //待总部安全管理员审核
          if (1 == checkNodeListResult.getExamineResult()) {
            //如果企业管理员审核通过
            fkExaminStatus = "5";
            systemParam.setRecordStatus(2);
          }else if (2 == checkNodeListResult.getExamineResult()){
            //如果企业管理员审核未通过
            fkExaminStatus = "4";
            systemParam.setRecordStatus(3);
          }
          this.systemMapper.updateKeySystem(systemParam);
        }
        break;
      }
      this.checkMapper.updateKeyCheck(checkParam);
      checkParam.setFkExaminStatus(fkExaminStatus);
      checkParam.setPrevExecutor(checkNodeListResult.getExecutor());
      checkParam.setExecuteTime(new Date());
    }
    return checkNodeListResult.getCheckNodeId();

  }
   /**
    * 审核节点列表 
    */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_check_node")
  public PageInfo<CheckNodeListResult> queryCheckNodeList(CheckNodeParam checkNodeParam) throws BusinessException{
    
    //创建排序字段
    StringBuffer orderBy = new StringBuffer();
    //判断field是否有值
    if(StringUtils.isNotBlank(checkNodeParam.getField())){
     //如有值，则将排序字段放入orderBy对象
     orderBy.append(ConvertFieldUtil.sortField(checkNodeParam.getField()));
     if(StringUtils.isNotBlank(checkNodeParam.getSort())){
      orderBy.append(" ").append(checkNodeParam.getSort());
     }
    }else {
     //默认排序规则
     orderBy.append("createtime desc");
    }
    //初始化分页拦截器
    PageHelper.startPage(checkNodeParam.getCurrentPage(), checkNodeParam.getPageSize(),orderBy.toString());
    //获得相应列表数据
    List<CheckNodeListResult> list = this.checkMapper.selectAllByCheckNodeParam(checkNodeParam);
    //装载列表数据
    PageInfo<CheckNodeListResult> pageInfo = new PageInfo<>(list);

    return pageInfo;
  }

  /**
   * 新增业务节点审核信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_check")
  public String saveCheck(CheckParam checkParam) throws BusinessException{
    if (StringUtils.isBlank(checkParam.getCheckId())) {
      checkParam.setCheckId(Utils.getUuidFor32());
      checkParam.setDeleteStatus(1);
      checkParam.setExecuteTime(new Date());
      checkParam.setCreateTime(new Date());
    }
    this.checkMapper.insertByCheck(checkParam);
    return checkParam.getCheckId();
  }

  /**
   * 系统审核全部详细
   */
  @Override
  public PageInfo<CheckNodeListResult> queryNodeAllList(CheckNodeParam checkNodeParam) {
  //创建排序字段
    StringBuffer orderBy = new StringBuffer();
    //判断field是否有值
    if(StringUtils.isNotBlank(checkNodeParam.getField())){
     //如有值，则将排序字段放入orderBy对象
     orderBy.append(ConvertFieldUtil.sortField(checkNodeParam.getField()));
     if(StringUtils.isNotBlank(checkNodeParam.getSort())){
      orderBy.append(" ").append(checkNodeParam.getSort());
     }
    }else {
     //默认排序规则
     orderBy.append("createtime desc");
    }
    //初始化分页拦截器
    PageHelper.startPage(checkNodeParam.getCurrentPage(), checkNodeParam.getPageSize(),orderBy.toString());
    //获得相应列表数据
    List<CheckNodeListResult> list = this.checkMapper.selectAllCheckNodeParam(checkNodeParam);
    //装载列表数据
    PageInfo<CheckNodeListResult> pageInfo = new PageInfo<>(list);

    return pageInfo;
  }

}
