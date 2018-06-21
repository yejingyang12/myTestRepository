/**
* Copyright 2018. 
* @Title SystemMaterialsServiceImpl.java
* @Package com.sinopec.smcc.cpro.grading.server.impl
* @Description: TODO:
* @author yejingyang
* @date 2018年6月11日下午2:51:49
* @version V1.0
*/
package com.sinopec.smcc.cpro.grading.server.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.server.FileService;
import com.sinopec.smcc.cpro.grading.entity.GradingParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsParam;
import com.sinopec.smcc.cpro.grading.entity.SystemMaterialsResult;
import com.sinopec.smcc.cpro.grading.mapper.GradingMapper;
import com.sinopec.smcc.cpro.grading.mapper.SystemMaterialsMapper;
import com.sinopec.smcc.cpro.grading.server.SystemMaterialsService;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.entity.CheckResult;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.system.entity.SystemParam;
import com.sinopec.smcc.cpro.system.entity.SystemResult;
import com.sinopec.smcc.cpro.system.mapper.SystemMapper;
import com.sinopec.smcc.cpro.tools.Utils;

/**
 * @Title SystemMaterialsServiceImpl.java
 * @Package com.sinopec.smcc.cpro.grading.server.impl
 * @Description: TODO:
 * @author yejingyang
 * @date 2018年6月11日下午2:51:49
 * @version V1.0
 */
@Service
public class SystemMaterialsServiceImpl implements SystemMaterialsService {
  @Autowired
  private SystemMaterialsMapper systemMaterialsMapper;
  @Autowired
  private GradingMapper gradingMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private FileService fileServiceImpl;
  @Autowired
  private CheckService checkServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  @Autowired
  private SystemMapper systemMapperImpl;
  
  /**
   * 获取提交材料信息
   */
  @Override
  public SystemMaterialsResult querySystemMaterials(SystemMaterialsParam systemMaterialsParam)
      throws BusinessException{
    if(StringUtils.isBlank(systemMaterialsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.ERROR);
    }
    return this.systemMaterialsMapper.selectSystemMaterials(systemMaterialsParam);
  }

  /**
   * 获取材料回显信息
   */
  @Override
  public SystemMaterialsResult queryEditSystemMaterials(SystemMaterialsParam systemMaterialsParam)
      throws BusinessException{
    if(StringUtils.isBlank(systemMaterialsParam.getFkSystemId())){
      throw new BusinessException(EnumResult.ERROR);
    }
    return this.systemMaterialsMapper.selectSystemMaterials(systemMaterialsParam);
  }

  /**
   * 保存材料信息
   */
  @Override
  public String saveSystemMaterials(String userName, SystemMaterialsParam systemMaterialsParam)
      throws BusinessException{
    if (StringUtils.isBlank(systemMaterialsParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.ERROR);
    }
    systemMaterialsParam.setCreateTime(new Date());
    if (StringUtils.isBlank(systemMaterialsParam.getSystemMaterialsId())) {
      systemMaterialsParam.setSystemMaterialsId(Utils.getUuidFor32());
      
      //1.系统拓扑结构及说明
      if (StringUtils.isNotBlank(systemMaterialsParam.getTopologyDescriptionPath())) {
        String[] paths = systemMaterialsParam.getTopologyDescriptionPath().split(",");
        String[] names = systemMaterialsParam.getTopologyDescriptionName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam topologyDescription = new AttachParam();
          topologyDescription.setFileId(Utils.getUuidFor32());
          topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
          topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          topologyDescription.setAttachType("topologyDescription");
          topologyDescription.setUploadUrl(paths[i].trim());
          topologyDescription.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(topologyDescription);
        }
      }
      //2.系统安全组织机构及管理制度
      if (StringUtils.isNotBlank(systemMaterialsParam.getOrganizationManagementPath())) {
        String[] paths = systemMaterialsParam.getOrganizationManagementPath().split(",");
        String[] names = systemMaterialsParam.getOrganizationManagementName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam organizationManagement = new AttachParam();
          organizationManagement.setFileId(Utils.getUuidFor32());
          organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
          organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          organizationManagement.setAttachType("organizationManagement");
          organizationManagement.setUploadUrl(paths[i].trim());
          organizationManagement.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(organizationManagement);
        }
      }
      
      //3.系统安全保护设施设计;实施方案或改建实施方案
      if (StringUtils.isNotBlank(systemMaterialsParam.getImplementationPlanPath())) {
        String[] paths = systemMaterialsParam.getImplementationPlanPath().split(",");
        String[] names = systemMaterialsParam.getImplementationPlanName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam implementationPlan = new AttachParam();
          implementationPlan.setFileId(Utils.getUuidFor32());
          implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
          implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          implementationPlan.setAttachType("implementationPlan");
          implementationPlan.setUploadUrl(paths[i].trim());
          implementationPlan.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(implementationPlan);
        }
      }
      
      //4.系统使用的安全产品清单及认证、销售许可证明
      if (StringUtils.isNotBlank(systemMaterialsParam.getLicenseCertificatePath())) {
        String[] paths = systemMaterialsParam.getLicenseCertificatePath().split(",");
        String[] names = systemMaterialsParam.getLicenseCertificateName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam licenseCertificate = new AttachParam();
          licenseCertificate.setFileId(Utils.getUuidFor32());
          licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
          licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          licenseCertificate.setAttachType("licenseCertificate");
          licenseCertificate.setUploadUrl(paths[i].trim());
          licenseCertificate.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(licenseCertificate);
        }
      }
      
      //5.测评报告
      if (StringUtils.isNotBlank(systemMaterialsParam.getEvaluationPresentationPath())) {
        AttachParam evaluationPresentation = new AttachParam();
        evaluationPresentation.setFileId(Utils.getUuidFor32());
        evaluationPresentation.setSystemId(systemMaterialsParam.getFkSystemId());
        evaluationPresentation.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        evaluationPresentation.setAttachType("evaluationPresentation");
        evaluationPresentation.setUploadUrl(systemMaterialsParam.getEvaluationPresentationPath());
        evaluationPresentation.setAttachName(systemMaterialsParam.getEvaluationPresentationName());
        this.fileServiceImpl.addFile(evaluationPresentation);
      }
      
      //6.专家评审
      if (StringUtils.isNotBlank(systemMaterialsParam.getExpertReviewPath())) {
        AttachParam expertReview = new AttachParam();
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setSystemId(systemMaterialsParam.getFkSystemId());
        expertReview.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        expertReview.setAttachType("expertReview");
        expertReview.setUploadUrl(systemMaterialsParam.getExpertReviewPath());
        expertReview.setAttachName(systemMaterialsParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      
      //7.上级主管部门审批意见
      if (StringUtils.isNotBlank(systemMaterialsParam.getDirectorOpinionPath())) {
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setSystemId(systemMaterialsParam.getFkSystemId());
        directorOpinion.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        directorOpinion.setAttachType("directorOpinion");
        directorOpinion.setUploadUrl(systemMaterialsParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(systemMaterialsParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
    } else {
      
      //1.系统拓扑结构及说明
      if (StringUtils.isNotBlank(systemMaterialsParam.getTopologyDescriptionPath())) {
        AttachParam topologyDescription = new AttachParam();
        topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
        topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        topologyDescription.setAttachType("topologyDescription");
        this.fileServiceImpl.deleteFile(topologyDescription);
        String[] paths = systemMaterialsParam.getTopologyDescriptionPath().split(",");
        String[] names = systemMaterialsParam.getTopologyDescriptionName().split(",");
        for(int i=0;i<paths.length;i++){
          topologyDescription = new AttachParam();
          topologyDescription.setFileId(Utils.getUuidFor32());
          topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
          topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          topologyDescription.setAttachType("topologyDescription");
          topologyDescription.setUploadUrl(paths[i].trim());
          topologyDescription.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(topologyDescription);
        }
      }
      //2.系统安全组织机构及管理制度
      if (StringUtils.isNotBlank(systemMaterialsParam.getOrganizationManagementPath())) {
        AttachParam organizationManagement = new AttachParam();
        organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
        organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        organizationManagement.setAttachType("organizationManagement");
        this.fileServiceImpl.deleteFile(organizationManagement);
        String[] paths = systemMaterialsParam.getOrganizationManagementPath().split(",");
        String[] names = systemMaterialsParam.getOrganizationManagementName().split(",");
        for(int i=0;i<paths.length;i++){
          organizationManagement = new AttachParam();
          organizationManagement.setFileId(Utils.getUuidFor32());
          organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
          organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          organizationManagement.setAttachType("organizationManagement");
          organizationManagement.setUploadUrl(paths[i].trim());
          organizationManagement.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(organizationManagement);
        }
      }
      
      //3.系统安全保护设施设计;实施方案或改建实施方案
      if (StringUtils.isNotBlank(systemMaterialsParam.getImplementationPlanPath())) {
        AttachParam implementationPlan = new AttachParam();
        implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
        implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        implementationPlan.setAttachType("implementationPlan");
        this.fileServiceImpl.deleteFile(implementationPlan);
        String[] paths = systemMaterialsParam.getImplementationPlanPath().split(",");
        String[] names = systemMaterialsParam.getImplementationPlanName().split(",");
        for(int i=0;i<paths.length;i++){
          implementationPlan = new AttachParam();
          implementationPlan.setFileId(Utils.getUuidFor32());
          implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
          implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          implementationPlan.setAttachType("implementationPlan");
          implementationPlan.setUploadUrl(paths[i].trim());
          implementationPlan.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(implementationPlan);
        }
      }
      
      //4.系统使用的安全产品清单及认证、销售许可证明
      if (StringUtils.isNotBlank(systemMaterialsParam.getLicenseCertificatePath())) {
        AttachParam licenseCertificate = new AttachParam();
        licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
        licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        licenseCertificate.setAttachType("licenseCertificate");
        this.fileServiceImpl.deleteFile(licenseCertificate);
        String[] paths = systemMaterialsParam.getLicenseCertificatePath().split(",");
        String[] names = systemMaterialsParam.getLicenseCertificateName().split(",");
        for(int i=0;i<paths.length;i++){
          licenseCertificate = new AttachParam();
          licenseCertificate.setFileId(Utils.getUuidFor32());
          licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
          licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          licenseCertificate.setAttachType("licenseCertificate");
          licenseCertificate.setUploadUrl(paths[i].trim());
          licenseCertificate.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(licenseCertificate);
        }
      }
      
      //5.测评报告
      if (StringUtils.isNotBlank(systemMaterialsParam.getEvaluationPresentationPath())) {
        AttachParam evaluationPresentation = new AttachParam();
        evaluationPresentation.setSystemId(systemMaterialsParam.getFkSystemId());
        evaluationPresentation.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        evaluationPresentation.setAttachType("evaluationPresentation");
        this.fileServiceImpl.deleteFile(evaluationPresentation);
        evaluationPresentation.setFileId(Utils.getUuidFor32());
        evaluationPresentation.setUploadUrl(systemMaterialsParam.getEvaluationPresentationPath());
        evaluationPresentation.setAttachName(systemMaterialsParam.getEvaluationPresentationName());
        this.fileServiceImpl.addFile(evaluationPresentation);
      }
      
      //6.专家评审
      if (StringUtils.isNotBlank(systemMaterialsParam.getExpertReviewPath())) {
        AttachParam expertReview = new AttachParam();
        expertReview.setSystemId(systemMaterialsParam.getFkSystemId());
        expertReview.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        expertReview.setAttachType("expertReview");
        this.fileServiceImpl.deleteFile(expertReview);
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setUploadUrl(systemMaterialsParam.getExpertReviewPath());
        expertReview.setAttachName(systemMaterialsParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      
      //7.上级主管部门审批意见
      if (StringUtils.isNotBlank(systemMaterialsParam.getDirectorOpinionPath())) {
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setSystemId(systemMaterialsParam.getFkSystemId());
        directorOpinion.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        directorOpinion.setAttachType("directorOpinion");
        this.fileServiceImpl.deleteFile(directorOpinion);
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setUploadUrl(systemMaterialsParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(systemMaterialsParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
    }
    if ("1".equals(systemMaterialsParam.getChangeType())) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(systemMaterialsParam.getFkSystemId());
      nodeParam.setOperation("材料变更");
      nodeParam.setOperationResult("已修改");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      
      //修改审核状态
      CheckParam checkParam = new CheckParam();
      checkParam.setFkSystemId(systemMaterialsParam.getFkSystemId());
      checkParam.setFkExaminStatus("1");
      checkParam.setFkBusinessNode("3");
      checkServiceImpl.editCheckStatusBySystemId(checkParam);
      //修改审核,定级状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(systemMaterialsParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
    this.systemMaterialsMapper.insertSystemMaterials(systemMaterialsParam);
    return systemMaterialsParam.getFkSystemId();
  }

  /**
   * 提交材料信息修改状态
   */
  @Override
  public String submitSystemMaterials(String userName, SystemMaterialsParam systemMaterialsParam)
      throws BusinessException {
    if (StringUtils.isBlank(systemMaterialsParam.getFkSystemId())) {
      throw new BusinessException(EnumResult.ERROR);
    }
    systemMaterialsParam.setCreateTime(new Date());
    if (StringUtils.isBlank(systemMaterialsParam.getSystemMaterialsId())) {
      //创建审核记录
      SystemParam systemParam = new SystemParam();
      systemParam.setSystemId(systemMaterialsParam.getFkSystemId());
      SystemResult systemResult = systemMapperImpl.selectSystem(systemParam);
      CheckParam checkParam = new CheckParam();
      checkParam.setFkSystemId(systemMaterialsParam.getFkSystemId());
      //查询审核详情通过systemID
      CheckResult checkResult = checkServiceImpl.queryCheckInfoBySystemId(checkParam);
      
      if(checkResult != null){
        checkServiceImpl.deleteCheckByCheckId(checkParam);
      }
      CheckParam checkParamAdd = new CheckParam();
      checkParamAdd.setFkSystemId(systemMaterialsParam.getFkSystemId());
      checkParamAdd.setFkExaminStatus("1");
      checkParamAdd.setFkBusinessNode("1");
      checkParamAdd.setInstanceName(systemResult.getSystemName());
      checkParamAdd.setInitiator(userName);
      checkParamAdd.setPrevExecutor(userName);
      checkParamAdd.setExecuteTime(new Date());
      checkServiceImpl.addCheck(checkParamAdd);
      
      //修改审核状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(systemMaterialsParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
      
      
      systemMaterialsParam.setSystemMaterialsId(Utils.getUuidFor32());
      
      //1.系统拓扑结构及说明
      if (StringUtils.isNotBlank(systemMaterialsParam.getTopologyDescriptionPath())) {
        String[] paths = systemMaterialsParam.getTopologyDescriptionPath().split(",");
        String[] names = systemMaterialsParam.getTopologyDescriptionName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam topologyDescription = new AttachParam();
          topologyDescription.setFileId(Utils.getUuidFor32());
          topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
          topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          topologyDescription.setAttachType("topologyDescription");
          topologyDescription.setUploadUrl(paths[i].trim());
          topologyDescription.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(topologyDescription);
        }
      }
      //2.系统安全组织机构及管理制度
      if (StringUtils.isNotBlank(systemMaterialsParam.getOrganizationManagementPath())) {
        String[] paths = systemMaterialsParam.getOrganizationManagementPath().split(",");
        String[] names = systemMaterialsParam.getOrganizationManagementName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam organizationManagement = new AttachParam();
          organizationManagement.setFileId(Utils.getUuidFor32());
          organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
          organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          organizationManagement.setAttachType("organizationManagement");
          organizationManagement.setUploadUrl(paths[i].trim());
          organizationManagement.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(organizationManagement);
        }
      }
      
      //3.系统安全保护设施设计;实施方案或改建实施方案
      if (StringUtils.isNotBlank(systemMaterialsParam.getImplementationPlanPath())) {
        String[] paths = systemMaterialsParam.getImplementationPlanPath().split(",");
        String[] names = systemMaterialsParam.getImplementationPlanName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam implementationPlan = new AttachParam();
          implementationPlan.setFileId(Utils.getUuidFor32());
          implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
          implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          implementationPlan.setAttachType("implementationPlan");
          implementationPlan.setUploadUrl(paths[i].trim());
          implementationPlan.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(implementationPlan);
        }
      }
      
      //4.系统使用的安全产品清单及认证、销售许可证明
      if (StringUtils.isNotBlank(systemMaterialsParam.getLicenseCertificatePath())) {
        String[] paths = systemMaterialsParam.getLicenseCertificatePath().split(",");
        String[] names = systemMaterialsParam.getLicenseCertificateName().split(",");
        for(int i=0;i<paths.length;i++){
          AttachParam licenseCertificate = new AttachParam();
          licenseCertificate.setFileId(Utils.getUuidFor32());
          licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
          licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          licenseCertificate.setAttachType("licenseCertificate");
          licenseCertificate.setUploadUrl(paths[i].trim());
          licenseCertificate.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(licenseCertificate);
        }
      }
      
      //5.测评报告
      if (StringUtils.isNotBlank(systemMaterialsParam.getEvaluationPresentationPath())) {
        AttachParam evaluationPresentation = new AttachParam();
        evaluationPresentation.setFileId(Utils.getUuidFor32());
        evaluationPresentation.setSystemId(systemMaterialsParam.getFkSystemId());
        evaluationPresentation.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        evaluationPresentation.setAttachType("evaluationPresentation");
        evaluationPresentation.setUploadUrl(systemMaterialsParam.getEvaluationPresentationPath());
        evaluationPresentation.setAttachName(systemMaterialsParam.getEvaluationPresentationName());
        this.fileServiceImpl.addFile(evaluationPresentation);
      }
      
      //6.专家评审
      if (StringUtils.isNotBlank(systemMaterialsParam.getExpertReviewPath())) {
        AttachParam expertReview = new AttachParam();
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setSystemId(systemMaterialsParam.getFkSystemId());
        expertReview.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        expertReview.setAttachType("expertReview");
        expertReview.setUploadUrl(systemMaterialsParam.getExpertReviewPath());
        expertReview.setAttachName(systemMaterialsParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      
      //7.上级主管部门审批意见
      if (StringUtils.isNotBlank(systemMaterialsParam.getDirectorOpinionPath())) {
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setSystemId(systemMaterialsParam.getFkSystemId());
        directorOpinion.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        directorOpinion.setAttachType("directorOpinion");
        directorOpinion.setUploadUrl(systemMaterialsParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(systemMaterialsParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
    } else {
      //1.系统拓扑结构及说明
      if (StringUtils.isNotBlank(systemMaterialsParam.getTopologyDescriptionPath())) {
        AttachParam topologyDescription = new AttachParam();
        topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
        topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        topologyDescription.setAttachType("topologyDescription");
        this.fileServiceImpl.deleteFile(topologyDescription);
        String[] paths = systemMaterialsParam.getTopologyDescriptionPath().split(",");
        String[] names = systemMaterialsParam.getTopologyDescriptionName().split(",");
        for(int i=0;i<paths.length;i++){
          topologyDescription = new AttachParam();
          topologyDescription.setFileId(Utils.getUuidFor32());
          topologyDescription.setSystemId(systemMaterialsParam.getFkSystemId());
          topologyDescription.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          topologyDescription.setAttachType("topologyDescription");
          topologyDescription.setUploadUrl(paths[i].trim());
          topologyDescription.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(topologyDescription);
        }
      }
      //2.系统安全组织机构及管理制度
      if (StringUtils.isNotBlank(systemMaterialsParam.getOrganizationManagementPath())) {
        AttachParam organizationManagement = new AttachParam();
        organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
        organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        organizationManagement.setAttachType("organizationManagement");
        this.fileServiceImpl.deleteFile(organizationManagement);
        String[] paths = systemMaterialsParam.getOrganizationManagementPath().split(",");
        String[] names = systemMaterialsParam.getOrganizationManagementName().split(",");
        for(int i=0;i<paths.length;i++){
          organizationManagement = new AttachParam();
          organizationManagement.setFileId(Utils.getUuidFor32());
          organizationManagement.setSystemId(systemMaterialsParam.getFkSystemId());
          organizationManagement.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          organizationManagement.setAttachType("organizationManagement");
          organizationManagement.setUploadUrl(paths[i].trim());
          organizationManagement.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(organizationManagement);
        }
      }
      
      //3.系统安全保护设施设计;实施方案或改建实施方案
      if (StringUtils.isNotBlank(systemMaterialsParam.getImplementationPlanPath())) {
        AttachParam implementationPlan = new AttachParam();
        implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
        implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        implementationPlan.setAttachType("implementationPlan");
        this.fileServiceImpl.deleteFile(implementationPlan);
        String[] paths = systemMaterialsParam.getImplementationPlanPath().split(",");
        String[] names = systemMaterialsParam.getImplementationPlanName().split(",");
        for(int i=0;i<paths.length;i++){
          implementationPlan = new AttachParam();
          implementationPlan.setFileId(Utils.getUuidFor32());
          implementationPlan.setSystemId(systemMaterialsParam.getFkSystemId());
          implementationPlan.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          implementationPlan.setAttachType("implementationPlan");
          implementationPlan.setUploadUrl(paths[i].trim());
          implementationPlan.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(implementationPlan);
        }
      }
      
      //4.系统使用的安全产品清单及认证、销售许可证明
      if (StringUtils.isNotBlank(systemMaterialsParam.getLicenseCertificatePath())) {
        AttachParam licenseCertificate = new AttachParam();
        licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
        licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        licenseCertificate.setAttachType("licenseCertificate");
        this.fileServiceImpl.deleteFile(licenseCertificate);
        String[] paths = systemMaterialsParam.getLicenseCertificatePath().split(",");
        String[] names = systemMaterialsParam.getLicenseCertificateName().split(",");
        for(int i=0;i<paths.length;i++){
          licenseCertificate = new AttachParam();
          licenseCertificate.setFileId(Utils.getUuidFor32());
          licenseCertificate.setSystemId(systemMaterialsParam.getFkSystemId());
          licenseCertificate.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
          licenseCertificate.setAttachType("licenseCertificate");
          licenseCertificate.setUploadUrl(paths[i].trim());
          licenseCertificate.setAttachName(names[i].trim());
          this.fileServiceImpl.addFile(licenseCertificate);
        }
      }
      
      //5.测评报告
      if (StringUtils.isNotBlank(systemMaterialsParam.getEvaluationPresentationPath())) {
        AttachParam evaluationPresentation = new AttachParam();
        evaluationPresentation.setSystemId(systemMaterialsParam.getFkSystemId());
        evaluationPresentation.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        evaluationPresentation.setAttachType("evaluationPresentation");
        this.fileServiceImpl.deleteFile(evaluationPresentation);
        evaluationPresentation.setFileId(Utils.getUuidFor32());
        evaluationPresentation.setUploadUrl(systemMaterialsParam.getEvaluationPresentationPath());
        evaluationPresentation.setAttachName(systemMaterialsParam.getEvaluationPresentationName());
        this.fileServiceImpl.addFile(evaluationPresentation);
      }
      
      //6.专家评审
      if (StringUtils.isNotBlank(systemMaterialsParam.getExpertReviewPath())) {
        AttachParam expertReview = new AttachParam();
        expertReview.setSystemId(systemMaterialsParam.getFkSystemId());
        expertReview.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        expertReview.setAttachType("expertReview");
        this.fileServiceImpl.deleteFile(expertReview);
        expertReview.setFileId(Utils.getUuidFor32());
        expertReview.setUploadUrl(systemMaterialsParam.getExpertReviewPath());
        expertReview.setAttachName(systemMaterialsParam.getExpertReviewName());
        this.fileServiceImpl.addFile(expertReview);
      }
      
      //7.上级主管部门审批意见
      if (StringUtils.isNotBlank(systemMaterialsParam.getDirectorOpinionPath())) {
        AttachParam directorOpinion = new AttachParam();
        directorOpinion.setSystemId(systemMaterialsParam.getFkSystemId());
        directorOpinion.setSyssonId(systemMaterialsParam.getSystemMaterialsId());
        directorOpinion.setAttachType("directorOpinion");
        this.fileServiceImpl.deleteFile(directorOpinion);
        directorOpinion.setFileId(Utils.getUuidFor32());
        directorOpinion.setUploadUrl(systemMaterialsParam.getDirectorOpinionPath());
        directorOpinion.setAttachName(systemMaterialsParam.getDirectorOpinionName());
        this.fileServiceImpl.addFile(directorOpinion);
      }
    }
    if ("1".equals(systemMaterialsParam.getChangeType())) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(systemMaterialsParam.getFkSystemId());
      nodeParam.setOperation("材料变更");
      nodeParam.setOperationResult("已修改");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
      //修改审核状态
      CheckParam checkParam = new CheckParam();
      checkParam.setFkSystemId(systemMaterialsParam.getFkSystemId());
      checkParam.setFkExaminStatus("1");
      checkParam.setFkBusinessNode("3");
      checkParam.setPrevExecutor(userName);
      checkParam.setExecuteTime(new Date());
      checkServiceImpl.editCheckStatusBySystemId(checkParam);
      //修改审核状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(systemMaterialsParam.getFkSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    } else if ("2".equals(systemMaterialsParam.getChangeType())) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(systemMaterialsParam.getFkSystemId());
      nodeParam.setOperation("材料提交");
      nodeParam.setOperationResult("已创建");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      this.nodeServiceImpl.addNodeInfo(nodeParam);
    }
    this.systemMaterialsMapper.insertSystemMaterials(systemMaterialsParam);
    //修改系统定级状态
    GradingParam gradingParam = new GradingParam();
    gradingParam.setFkSystemId(systemMaterialsParam.getFkSystemId());
    this.gradingMapper.updateGradingStatus(gradingParam);
    return systemMaterialsParam.getFkSystemId();
  }

}
