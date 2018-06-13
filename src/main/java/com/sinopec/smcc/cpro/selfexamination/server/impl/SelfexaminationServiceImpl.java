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

import java.io.File;
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
import com.sinopec.smcc.cpro.file.constant.FileConstant;
import com.sinopec.smcc.cpro.file.entity.AttachParam;
import com.sinopec.smcc.cpro.file.mapper.AttachMapper;
import com.sinopec.smcc.cpro.file.server.FileService;
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
  private AttachMapper attachMapper;
  @Autowired
  private FileService fileServiceImpl;
  
  @Override
  public PageInfo<SelfexaminationListResult> querySelfexaminationList(
      SelfexaminationParam selfexaminationParam) throws BusinessException {
    StringBuffer orderBy = new StringBuffer();
    if (StringUtils.isNotBlank(selfexaminationParam.getField())) {
      orderBy.append(ConvertFieldUtil.sortFielde(selfexaminationParam.getField()));
      if (StringUtils.isNotBlank(selfexaminationParam.getSort())) {
        orderBy.append(" ").append(selfexaminationParam.getSort());
      }
    }else {
      orderBy.append("createTime desc");
    }
    PageHelper.startPage(selfexaminationParam.getCurrentPage(), 
        selfexaminationParam.getPageSize(), orderBy.toString());
    List<SelfexaminationListResult> list= this.selfexaminationMapper.
        selectAllBySelfexaminationParam(selfexaminationParam);
    PageInfo<SelfexaminationListResult> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
  
  /**
   * 添加或修改
   */
  @Transactional
  @Override
  public String saveSelfexamination(SelfexaminationParam selfexaminationParam) 
      throws BusinessException {
    //两个附件的数据
    AttachParam review = null;
    AttachParam rectification = null;
    if(StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())) {
      selfexaminationParam.setSelfexaminationId(Utils.getUuidFor32());
      selfexaminationParam.setCreateTime(new Date());
      selfexaminationParam.setDeleteStatus(1);
      selfexaminationParam.setCreateUserName("admin");
      //:TODO对附件表添加数据
      review = new AttachParam();
      review.setFileId(Utils.getUuidFor32());
      review.setSystemId(selfexaminationParam.getFkSystemId());
      review.setSyssonId(selfexaminationParam.getSelfexaminationId());
      review.setAttachType("evaluationPresentation");
      review.setAttachName(selfexaminationParam.getReviewReportName());
      review.setUploadUrl(selfexaminationParam.getRectificationReportPath());
      review.setCreateTime(new Date());
      
      rectification = new AttachParam();
      rectification.setFileId(Utils.getUuidFor32());
      rectification.setSystemId(selfexaminationParam.getFkSystemId());
      rectification.setSyssonId(selfexaminationParam.getSelfexaminationId());
      rectification.setAttachType("rectificationReport");
      rectification.setAttachName(selfexaminationParam.getReviewReportName());
      rectification.setUploadUrl(selfexaminationParam.getRectificationReportPath());
      rectification.setCreateTime(new Date());
      
      
    }else {
      //必须有数据，但可能不会用
      selfexaminationParam.setCreateTime(new Date());
      //:TODO对附件表修改数据
      if (selfexaminationParam.getReviewReportName() != null 
            && !"".equals(selfexaminationParam.getReviewReportName())
            && selfexaminationParam.getReviewReportPath() != null 
            && !"".equals(selfexaminationParam.getReviewReportPath())) {
        review = new AttachParam();
      }
      if (selfexaminationParam.getRectificationReportName() != null 
          && !"".equals(selfexaminationParam.getRectificationReportName())
          && selfexaminationParam.getRectificationReportPath() != null 
          && !"".equals(selfexaminationParam.getRectificationReportPath())) {
        rectification = new AttachParam();
      }
    }
    this.selfexaminationMapper.insertOrUpdateSelfexamination(selfexaminationParam);
    //添加或修改完自查信息后，看看自查和整改的附件对象是否存在；
    /*if (review != null) {
      review.setSystemId(selfexaminationParam.getFkSystemId());
      review.setSyssonId(selfexaminationParam.getSelfexaminationId());
      review.setAttachType("evaluationPresentation");
      review.setAttachName(selfexaminationParam.getReviewReportName());
      review.setUploadUrl(selfexaminationParam.getRectificationReportPath());
      
      this.fileServiceImpl.addFile(review);
      this.deleteAttach(review);
      
      review.setFileId(Utils.getUuidFor32());
      review.setCreateTime(new Date());
      
      this.attachMapper.insertAttach(review);
    }
    if (rectification != null) {
      rectification.setSystemId(selfexaminationParam.getFkSystemId());
      rectification.setSyssonId(selfexaminationParam.getSelfexaminationId());
      rectification.setAttachType("rectificationReport");
      rectification.setAttachName(selfexaminationParam.getReviewReportName());
      rectification.setUploadUrl(selfexaminationParam.getRectificationReportPath());
      
      this.fileServiceImpl.addFile(rectification);
      this.deleteAttach(rectification);
      
      rectification.setFileId(Utils.getUuidFor32());
      rectification.setCreateTime(new Date());
      
      this.attachMapper.insertAttach(rectification);
    }*/
    return selfexaminationParam.getSelfexaminationId();
  }
  /**
   * @Descrption如果附件对应的文件存在，删除原附件信息
   * @author yejingyang
   * @date 2018年6月6日下午6:42:33
   * @param attachParam
   * @throws BusinessException 
   */
  private void deleteAttach(AttachParam attachParam) throws BusinessException{
    //根据文件信息获取文件
    File file = new File(FileConstant.TEMPORARY_FILE_PATH + attachParam.getUploadUrl());
    //如果文件存在，则删除原附件信息
    if (file.exists()) {
      this.fileServiceImpl.deleteFile(attachParam);
    }
  }
  
  /**
   * 查询修改回显信息
   */
  @Override
  @Transactional
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
  public void deleteSelfexaminationBySelfexaminationId(SelfexaminationParam selfexaminationParam)
      throws BusinessException {
    if(StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())){
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    }
    this.selfexaminationMapper.updateSelfexaminationDeleteStatusBySelfexaminationId(selfexaminationParam);
  }

}
