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
import com.sinopec.smcc.common.log.aop.EnableOperateLog;
import com.sinopec.smcc.common.log.aop.TableOperation;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationListResult;
import com.sinopec.smcc.cpro.selfexamination.entity.SelfexaminationParam;
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
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_self_inspection")  
  @Transactional
  @Override
  public String saveSelfexamination(SelfexaminationParam selfexaminationParam) 
      throws BusinessException {
    if(StringUtils.isBlank(selfexaminationParam.getSelfexaminationId())) {
      selfexaminationParam.setSelfexaminationId(Utils.getUuidFor32());
      selfexaminationParam.setCreateTime(new Date());
      selfexaminationParam.setDeleteStatus(1);
      selfexaminationParam.setCreateUserName("admin");
      //:TODO对附件表添加数据
    }else {
      //必须有数据，但可能不会用
      selfexaminationParam.setCreateTime(new Date());
      //:TODO对附件表修改数据
    }
    this.selfexaminationMapper.insertOrUpdateSelfexamination(selfexaminationParam);
    return selfexaminationParam.getSelfexaminationId();
  }

}
