/**
 * 2018. 
 * @Title CompanyServiceImpl.java
 * @Package com.sinopec.smcc.cpro.company.server.impl
 * @Description: TODO:
 * @author dongxu
 * @date 2018年5月25日上午11:13:04
 * @version V1.0
 */
package com.sinopec.smcc.cpro.company.server.impl;

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
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.mapper.CompanyMapper;
import com.sinopec.smcc.cpro.company.server.CompanyService;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.tools.Utils;

@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired
  private CompanyMapper companyMapper;

  /**
   * 响应单位列表数据
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_company")
  public PageInfo<CompanyListResult> queryCompanyList(
      CompanyParam companyParam) throws BusinessException{
    StringBuffer orderBy = new StringBuffer();
    if (StringUtils.isNotBlank(companyParam.getField())) {
      orderBy.append(ConvertFiledUtil.sortField(companyParam.getField()));
      if (StringUtils.isNotBlank(companyParam.getSort())) {
        orderBy.append("").append(companyParam.getSort());
      }
    } else {
      // 默认排序
      orderBy.append("updateTime DESC");
    }
    // 初始化分页拦截器
    PageHelper.startPage(companyParam.getCurrentPage(), companyParam.getPageSize(),
        orderBy.toString());
    // 获得响应列表数据
    List<CompanyListResult> companyListResultList = 
        this.companyMapper.selectAllByCompanyParam(companyParam);
    // 装载列表数据
    PageInfo<CompanyListResult> pageInfo = new PageInfo<>(companyListResultList);
    return pageInfo;
  }

  /**
   * 添加或修改单位信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.insert, module = SmccModuleEnum.security, tableName = "t_cpro_company")
  @Transactional
  public String saveCompany(CompanyParam companyParam) throws BusinessException {
    String companyCode = this.companyMapper.selectCompanyByCompanyCode(companyParam.getCompanyCode());
    //判断单位编码是否被创建，如果已创建则抛出异常不进行添加或修改
    if(StringUtils.isNotBlank(companyCode))
      throw new BusinessException(EnumResult.LINKEDID_ERROR);
    if (StringUtils.isBlank(companyParam.getCompanyId())) {
      companyParam.setCompanyId(Utils.getUuidFor32());
      companyParam.setDeleteStatus(1);
      companyParam.setCreateTime(new Date());
    }else{
      companyParam.setCreateTime(new Date());
    }
    this.companyMapper.insertCompanyByCompanyId(companyParam);
    return companyParam.getCompanyId();
  }

  /**
   * 删除单位信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.update, module = SmccModuleEnum.security, tableName = "t_cpro_company")
  @Transactional
  public void delelteCompany(CompanyParam companyParam) throws BusinessException {
    if(companyParam.getCompanyIds() == null || companyParam.getCompanyIds().length == 0)
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    this.companyMapper.updateCompanyByCompanyIds(companyParam);
  }
  
  /**
   * 单位详情
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_company")
  public CompanyResult queryDetailsCompany(CompanyParam companyParam) {
    return this.companyMapper.selectSingleCompanyByCompanyId(companyParam);
  }
  
  /**
   * 查询单位信息
   */
  @Override
  @EnableOperateLog(tableOperation = TableOperation.query, module = SmccModuleEnum.security, tableName = "t_cpro_company")
  public CompanyResult queryEditCompany(CompanyParam companyParam) {
    return this.companyMapper.selectCompanyByCompanyId(companyParam);
  }

}
