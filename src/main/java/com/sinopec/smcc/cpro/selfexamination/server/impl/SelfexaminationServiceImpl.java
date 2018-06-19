/**
* Copyright 2018. 
* @Title SelfexaminationServiceImpl.java
* @Package com.sinopec.smcc.cpro.selfexamination.server.impl
* @Description: TODO:
* @author yejingyang
* @date 2018年5月25日下午1:47:38
* @version V1.0
*/
package com.sinopec.smcc.cpro.selfexamination.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationResult;
import com.sinopec.smcc.cpro.selfexamination.mapper.SelfexaminationMapper;
import com.sinopec.smcc.cpro.selfexamination.server.SelfexaminationService;
import com.sinopec.smcc.cpro.selfexamination.utils.ConvertFieldUtil;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title SelfexaminationServiceImpl.java
 * @Package com.sinopec.smcc.cpro.selfexamination.server.impl
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年5月25日下午1:47:38
 * @version V1.0
 */
@Service
public class SelfexaminationServiceImpl implements SelfexaminationService {
  @Autowired
  private SelfexaminationMapper selfexaminationMapper;
  @Autowired
  private FileService fileServiceImpl;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_self_inspection")  
  public PageInfo<SelfexaminationListResult> querySelfexaminationList(
      SelfexaminationParam selfexaminationParam) throws BusinessException {
    StringBuffer orderBy = new StringBuffer();
    if (StringUtils.isNotBlank(selfexaminationParam.getField())) {
      orderBy.append(ConvertFieldUtil.sortFielde(selfexaminationParam.getField()));
      if (StringUtils.isNotBlank(selfexaminationParam.getSort())) {
        orderBy.append(" ").append(selfexaminationParam.getSort());
      }
    }else {
      orderBy.append("self.createTime desc");
    }
    //初始化分页拦截器
    PageHelper.startPage(selfexaminationParam.getCurrentPage(), selfexaminationParam.getPageSize(), 
        orderBy.toString());
    //获得相应列表数据
    List<SelfexaminationListResult> list = this.selfexaminationMapper.selectAllBySelfexaminationParam(selfexaminationParam);
    //装载列表数据
    PageInfo<SelfexaminationListResult> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
  
  /**
   * 添加或修改
   */
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_self_inspection")  
  @Transactional
  @Override
  public String saveSelfexamination(String userName, 
      SelfexaminationParam selfexaminationParam) throws BusinessException {
    if(StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())) {
      selfexaminationParam.setSelfexaminationId(Utils.getUuidFor32());
      selfexaminationParam.setCreateTime(new Date());
      selfexaminationParam.setDeleteStatus(1);
      selfexaminationParam.setCreateUserName("admin");
      
      //修改自查状态为已完成
      MainParam mainParam = new MainParam();
      mainParam.setEvaluationStatus("3");
      mainParam.setSystemId(selfexaminationParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
      if (StringUtils.isNotBlank(selfexaminationParam.getExaminationReportPath())) {
        //自查报告
        AttachParam examinationReport = new AttachParam();
        examinationReport.setFileId(Utils.getUuidFor32());
        examinationReport.setSystemId(selfexaminationParam.getFkSystemId());
        examinationReport.setSyssonId(selfexaminationParam.getSelfexaminationId());
        examinationReport.setAttachType("examinationReport");
        examinationReport.setUploadUrl(selfexaminationParam.getExaminationReportPath());
        examinationReport.setAttachName(selfexaminationParam.getExaminationReportName());
        this.fileServiceImpl.addFile(examinationReport);
      }
      if (StringUtils.isNotBlank(selfexaminationParam.getExaminationRectificationReportPath())) {
        //自查整改报告
        AttachParam examinationRectificationReport = new AttachParam();
        examinationRectificationReport.setFileId(Utils.getUuidFor32());
        examinationRectificationReport.setSystemId(selfexaminationParam.getFkSystemId());
        examinationRectificationReport.setSyssonId(selfexaminationParam.getSelfexaminationId());
        examinationRectificationReport.setAttachType("examinationRectificationReport");
        examinationRectificationReport.setUploadUrl(
            selfexaminationParam.getExaminationRectificationReportPath());
        examinationRectificationReport.setAttachName(
            selfexaminationParam.getExaminationRectificationReportName());
        this.fileServiceImpl.addFile(examinationRectificationReport);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(selfexaminationParam.getFkSystemId());
      nodeParam.setOperation("添加自查");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }else {
      //必须有数据，但可能不会用
      selfexaminationParam.setCreateTime(new Date());
      
      if (StringUtils.isNotBlank(selfexaminationParam.getExaminationReportPath())) {
        //保存附件  测评报告
        AttachParam examinationReport = new AttachParam();
        examinationReport.setSystemId(selfexaminationParam.getFkSystemId());
        examinationReport.setSyssonId(selfexaminationParam.getSelfexaminationId());
        examinationReport.setAttachType("examinationReport");
        //删除原附件
        this.fileServiceImpl.deleteFile(examinationReport);
        examinationReport.setFileId(Utils.getUuidFor32());
        examinationReport.setUploadUrl(selfexaminationParam.getExaminationReportPath());
        examinationReport.setAttachName(selfexaminationParam.getExaminationReportName());
        //保存附件
        this.fileServiceImpl.addFile(examinationReport);
      }
      if (StringUtils.isNotBlank(selfexaminationParam.getExaminationRectificationReportPath())) {
        //保存附件  整改报告
        AttachParam examinationRectificationReport = new AttachParam();
        examinationRectificationReport.setSystemId(selfexaminationParam.getFkSystemId());
        examinationRectificationReport.setSyssonId(selfexaminationParam.getSelfexaminationId());
        examinationRectificationReport.setAttachType("examinationRectificationReport");
        //删除原附件
        this.fileServiceImpl.deleteFile(examinationRectificationReport);
        examinationRectificationReport.setFileId(Utils.getUuidFor32());
        examinationRectificationReport.setUploadUrl(
            selfexaminationParam.getExaminationRectificationReportPath());
        examinationRectificationReport.setAttachName(
            selfexaminationParam.getExaminationRectificationReportName());
        //保存附件
        this.fileServiceImpl.addFile(examinationRectificationReport);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(selfexaminationParam.getFkSystemId());
      nodeParam.setOperation("修改自查");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    this.selfexaminationMapper.insertOrUpdateSelfexamination(selfexaminationParam);
    
    return selfexaminationParam.getSelfexaminationId();
  }
  
  /**
   * 查询修改回显信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_self_inspection")  
  public SelfexaminationResult queryEditSelfexamination(SelfexaminationParam selfexaminationParam)
      throws BusinessException{
    if (StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())) 
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    return this.selfexaminationMapper.selectSingleBySelfexaminationId(selfexaminationParam);
  }
  
  /**
   * 删除自查信息
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_self_inspection") 
  public void deleteSelfexaminationBySelfexaminationId(String userName, 
      SelfexaminationParam selfexaminationParam) throws BusinessException {
    if(StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())){
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    SelfexaminationResult selfexaminationResult = this.selfexaminationMapper.
        selectSingleBySelfexaminationId(selfexaminationParam);
    this.selfexaminationMapper.
      updateSelfexaminationDeleteStatusBySelfexaminationId(selfexaminationParam);
    
    
    //添加节点状态信息
    NodeParam nodeParam = new NodeParam();
    nodeParam.setSystemId(selfexaminationResult.getFkSystemId());
    nodeParam.setOperation("删除自查");
    nodeParam.setOperationResult("已创建");
    nodeParam.setOperationOpinion("");
    nodeParam.setOperator(userName);
    this.nodeServiceImpl.addNodeInfo(nodeParam);
  }

}
