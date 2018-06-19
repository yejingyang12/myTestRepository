/**
* 2018. 
* @Title GradingServiceImpl.java
* @Package com.sinopec.smcc.cpro.grading.server.impl
* @Description: TODO:
* @author hanxin
* @date 2018年5月29日上午10:11:00
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.grading.entity.GradingListResult;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.mapper.GradingMapper;
import com.sinopec.smcc.cpro.grading.server.GradingService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title GradingServiceImpl.java
 * @Package com.sinopec.smcc.cpro.grading.server.impl
 * @Description: TODO:
 * @author hanxin
 * @date 2018年5月29日上午10:11:00
 * @version V1.0
 */
@Service
public class GradingServiceImpl implements GradingService{ 

  @Autowired
  private GradingMapper gradingMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private FileService fileServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  
  
  /**
   * 查询定级详情信息
   * @throws BusinessException 
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public GradingListResult queryDetailsGrading(GradingParam gradingParam) 
      throws BusinessException {
    if(StringUtils.isBlank(gradingParam.getFkSystemId()))
      throw new BusinessException(EnumResult.ERROR);
    return this.gradingMapper.selectDetailsGrading(gradingParam);
  }

  /**
   * 查询定级回显信息
   * @throws BusinessException 
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public GradingListResult queryEditGrading(GradingParam gradingParam) throws BusinessException {
    if (StringUtils.isBlank(gradingParam.getFkSystemId())) 
      throw new BusinessException(EnumResult.ERROR);
    return this.gradingMapper.selectEditGrading(gradingParam);
  }

  /**
   * 保存添加定级信息数据
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public String saveGrading(String userName,GradingParam gradingParam) throws BusinessException {
    if (StringUtils.isBlank(gradingParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.ERROR);
    }
    gradingParam.setCreateTime(new Date());
    //有则修改，无则添加
    if (StringUtils.isBlank(gradingParam.getGradingId())) {
      gradingParam.setGradingId(Utils.getUuidFor32());
      gradingParam.setCreateUserName(userName);
      
      //修改定级状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setSystemId(gradingParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
      if (StringUtils.isNotBlank(gradingParam.getGradingReportPath())) {
        //保存附件  定级报告
        AttachParam gradingReport = new AttachParam();
        gradingReport.setFileId(Utils.getUuidFor32());
        gradingReport.setSystemId(gradingParam.getFkSystemId());
        gradingReport.setSyssonId(gradingParam.getGradingId());
        gradingReport.setAttachType("gradingReport");
        gradingReport.setUploadUrl(gradingParam.getGradingReportPath());
        gradingReport.setAttachName(gradingParam.getGradingReportName());
        this.fileServiceImpl.addFile(gradingReport);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  专家评审报告
        AttachParam expertReview = new AttachParam();
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setSystemId(gradingParam.getFkSystemId());
        expertReview.setSyssonId(gradingParam.getGradingId());
        expertReview.setAttachType("expertReview");
        expertReview.setUploadUrl(gradingParam.getExpertReviewPath());
        expertReview.setAttachName(gradingParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  上级主管部门审批意见
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setSystemId(gradingParam.getFkSystemId());
        directorOpinion.setSyssonId(gradingParam.getGradingId());
        directorOpinion.setAttachType("directorOpinion");
        directorOpinion.setUploadUrl(gradingParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(gradingParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(gradingParam.getFkSystemId());
      nodeParam.setOperation("创建");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }else{
      
      if (StringUtils.isNotBlank(gradingParam.getGradingReportPath())) {
        //保存附件  定级报告
        AttachParam gradingReport = new AttachParam();
        gradingReport.setSystemId(gradingParam.getFkSystemId());
        gradingReport.setSyssonId(gradingParam.getGradingId());
        gradingReport.setAttachType("gradingReport");
        this.fileServiceImpl.deleteFile(gradingReport);
        gradingReport.setFileId(Utils.getUuidFor32());
        gradingReport.setUploadUrl(gradingParam.getGradingReportPath());
        gradingReport.setAttachName(gradingParam.getGradingReportName());
        this.fileServiceImpl.addFile(gradingReport);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  专家评审报告
        AttachParam expertReview = new AttachParam();
        expertReview.setSystemId(gradingParam.getFkSystemId());
        expertReview.setSyssonId(gradingParam.getGradingId());
        expertReview.setAttachType("expertReview");
        this.fileServiceImpl.deleteFile(expertReview);
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setUploadUrl(gradingParam.getExpertReviewPath());
        expertReview.setAttachName(gradingParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  上级主管部门审批意见
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setSystemId(gradingParam.getFkSystemId());
        directorOpinion.setSyssonId(gradingParam.getGradingId());
        directorOpinion.setAttachType("directorOpinion");
        this.fileServiceImpl.deleteFile(directorOpinion);
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setUploadUrl(gradingParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(gradingParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(gradingParam.getFkSystemId());
      nodeParam.setOperation("变更");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    this.gradingMapper.insertGrading(gradingParam);
    return gradingParam.getFkSystemId();
  }
  
  /**
   * 提交定级信息修改定级状态
   */
  @Override
  @Transactional
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_score")
  public String submitGrading(String userName,GradingParam gradingParam) throws BusinessException {
    if (StringUtils.isBlank(gradingParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.ERROR);
    }
    gradingParam.setCreateTime(new Date());
    if(StringUtils.isBlank(gradingParam.getFkSystemId())) {
      gradingParam.setGradingId(Utils.getUuidFor32());
      gradingParam.setCreateUserName(userName);
      //修改定级状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setSystemId(gradingParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
      if (StringUtils.isNotBlank(gradingParam.getGradingReportPath())) {
        //保存附件  定级报告
        AttachParam gradingReport = new AttachParam();
        gradingReport.setFileId(Utils.getUuidFor32());
        gradingReport.setSystemId(gradingParam.getFkSystemId());
        gradingReport.setSyssonId(gradingParam.getGradingId());
        gradingReport.setAttachType("gradingReport");
        gradingReport.setUploadUrl(gradingParam.getGradingReportPath());
        gradingReport.setAttachName(gradingParam.getGradingReportName());
        this.fileServiceImpl.addFile(gradingReport);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  专家评审报告
        AttachParam expertReview = new AttachParam();
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setSystemId(gradingParam.getFkSystemId());
        expertReview.setSyssonId(gradingParam.getGradingId());
        expertReview.setAttachType("expertReview");
        expertReview.setUploadUrl(gradingParam.getExpertReviewPath());
        expertReview.setAttachName(gradingParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  上级主管部门审批意见
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setSystemId(gradingParam.getFkSystemId());
        directorOpinion.setSyssonId(gradingParam.getGradingId());
        directorOpinion.setAttachType("directorOpinion");
        directorOpinion.setUploadUrl(gradingParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(gradingParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(gradingParam.getFkSystemId());
      nodeParam.setOperation("创建");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    } else {
      
      if (StringUtils.isNotBlank(gradingParam.getGradingReportPath())) {
        //保存附件  定级报告
        AttachParam gradingReport = new AttachParam();
        gradingReport.setSystemId(gradingParam.getFkSystemId());
        gradingReport.setSyssonId(gradingParam.getGradingId());
        gradingReport.setAttachType("gradingReport");
        this.fileServiceImpl.deleteFile(gradingReport);
        gradingReport.setFileId(Utils.getUuidFor32());
        gradingReport.setUploadUrl(gradingParam.getGradingReportPath());
        gradingReport.setAttachName(gradingParam.getGradingReportName());
        this.fileServiceImpl.addFile(gradingReport);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  专家评审报告
        AttachParam expertReview = new AttachParam();
        expertReview.setSystemId(gradingParam.getFkSystemId());
        expertReview.setSyssonId(gradingParam.getGradingId());
        expertReview.setAttachType("expertReview");
        this.fileServiceImpl.deleteFile(expertReview);
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setUploadUrl(gradingParam.getExpertReviewPath());
        expertReview.setAttachName(gradingParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      if (StringUtils.isNotBlank(gradingParam.getExpertReviewPath())) {
        //保存附件  上级主管部门审批意见
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setSystemId(gradingParam.getFkSystemId());
        directorOpinion.setSyssonId(gradingParam.getGradingId());
        directorOpinion.setAttachType("directorOpinion");
        this.fileServiceImpl.deleteFile(directorOpinion);
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setUploadUrl(gradingParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(gradingParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
      
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(gradingParam.getFkSystemId());
      nodeParam.setOperation("变更");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    //修改系统定级状态
    this.gradingMapper.updateGradingStatus(gradingParam);
    //修改或添加信息
    this.gradingMapper.insertGrading(gradingParam);
    return gradingParam.getFkSystemId();
  }
  /**
   * 根据系统ID查询定级列表
   */
  @Override
  public List<GradingListResult> queryGradingByParam(GradingParam gradingParam)
      throws BusinessException {
    List<GradingListResult> gradingListResultList =
        this.gradingMapper.selectGradingBySystemIds(gradingParam);
    return gradingListResultList;
  }
}
