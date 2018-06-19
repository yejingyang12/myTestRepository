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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.common.consts.SmccModuleEnum;
import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.common.exception.model.EnumResult;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
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
  @Transactional
  public String saveCompany(HttpServletRequest request,CompanyParam companyParam) 
      throws BusinessException {
    if (StringUtils.isBlank(companyParam.getCompanyId())) {
      String companyCode = this.companyMapper.selectCompanyByCompanyCode(companyParam.getCompanyCode());
      //判断单位编码是否被创建，如果已创建则抛出异常不进行添加或修改
      if(StringUtils.isNotBlank(companyCode))
        throw new BusinessException(EnumResult.LINKEDID_ERROR);
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
  @Transactional
  public void delelteCompany(CompanyParam companyParam) throws BusinessException {
    System.out.println("companyParam:"+companyParam);
    if(companyParam.getCompanyIds() == null || companyParam.getCompanyIds().length == 0)
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    this.companyMapper.updateCompanyByCompanyIds(companyParam);
  }
  
  /**
   * 单位详情
   */
  @Override
  public CompanyResult queryDetailsCompany(CompanyParam companyParam) {
    return this.companyMapper.selectSingleCompanyByCompanyId(companyParam);
  }
  
  /**
   * 查询单位信息
   */
  @Override
  public CompanyResult queryEditCompany(CompanyParam companyParam) {
    return this.companyMapper.selectCompanyByCompanyId(companyParam);
  }

  /**
   * 查询单位信息 根据code
   */
  @Override
  public CompanyResult queryCompanyByCode(CompanyParam companyParam) {
   
    return this.companyMapper.selectCompanyInfoByCompanyCode(companyParam);
  }

  /**
   * 高级搜索获取单位名称
   */
  @Override
  public List<CompanyListResult> queryCompanyName(CompanyParam companyParam)
      throws BusinessException {
    return this.companyMapper.selectCompanyName(companyParam);
  }
}
