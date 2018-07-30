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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.base.exception.model.EnumResult;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascader;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.entity.CompanyResult;
import com.sinopec.smcc.cpro.company.mapper.CompanyMapper;
import com.sinopec.smcc.cpro.company.server.CompanyService;
import com.sinopec.smcc.cpro.company.utils.ConvertFiledUtil;
import com.sinopec.smcc.cpro.main.entity.MainParam;
import com.sinopec.smcc.cpro.main.server.MainService;
import com.sinopec.smcc.cpro.node.entity.NodeParam;
import com.sinopec.smcc.cpro.node.entity.NodeResult;
import com.sinopec.smcc.cpro.node.server.NodeService;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.tools.Utils;

@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired
  private CompanyMapper companyMapper;
  @Autowired
  private NodeService nodeServiceImpl;
  @Autowired
  private CheckService checkServiceImpl;
  @Autowired
  private MainService mainServiceImpl;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;

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

    
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return new PageInfo<>();
    }else{
      // 初始化分页拦截器
      PageHelper.startPage(companyParam.getCurrentPage(), companyParam.getPageSize(),
          orderBy.toString());
      
      List<CompanyListResult> companyListResultList  = new ArrayList<CompanyListResult>();
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      
      case "0":
        break;
      case "1":
        // 获得响应列表数据
      companyListResultList = 
          this.companyMapper.selectAllByCompanyParam(companyParam);
        break;
      case "2":
        companyParam.setPlateList(organizationApiResult.getNameList());
        companyListResultList =  
        this.companyMapper.selectAllByCompanyParam(companyParam);
        break;
      case "3":
        companyParam.setCompanyList(organizationApiResult.getCodeList());
        companyListResultList =  
        this.companyMapper.selectAllByCompanyParam(companyParam);
        break;

      default:
        break;
      }
      // 装载列表数据
      PageInfo<CompanyListResult> pageInfo = new PageInfo<>(companyListResultList);
      return pageInfo;
    }
  }

  /**
   * 添加或修改单位信息
   */
  @Override
  @Transactional
  public String saveCompany(String userName,CompanyParam companyParam) 
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
    if ("1".equals(companyParam.getChangeType())) {
      //添加节点状态信息
      NodeParam nodeParam = new NodeParam();
      nodeParam.setSystemId(companyParam.getSystemId());
      nodeParam.setOperation("申请变更");
      nodeParam.setOperationResult("已修改");
      nodeParam.setOperationOpinion("");
      nodeParam.setOperator(userName);
      NodeResult nodeResult = this.nodeServiceImpl.selectSingleNode(nodeParam);
      if (nodeResult == null) {
        this.nodeServiceImpl.addNodeInfo(nodeParam);
      }else{
        nodeParam.setNodeId(nodeResult.getNodeId());
        this.nodeServiceImpl.editNodeInfo(nodeParam);
      }
      
      //修改审核状态
      CheckParam checkParam = new CheckParam();
      checkParam.setFkSystemId(companyParam.getSystemId());
      checkParam.setFkExaminStatus("1");
      checkParam.setFkBusinessNode("3");
      checkParam.setPrevExecutor(userName);
      checkParam.setExecuteTime(new Date());
      checkServiceImpl.editCheckStatusBySystemId(checkParam);
      //修改审核状态为进行中
      MainParam mainParam = new MainParam();
      mainParam.setGradingStatus("2");
      mainParam.setExamineStatus("2");
      mainParam.setSystemId(companyParam.getSystemId());
      mainServiceImpl.editSystemStatusBySystemId(mainParam);
    }
    this.companyMapper.insertCompanyByCompanyId(companyParam);
    return companyParam.getCompanyId();
  }

  /**
   * 删除单位信息
   */
  @Override
  @Transactional
  public List<CompanyListResult> delelteCompany(CompanyParam companyParam) throws BusinessException {
    if(companyParam.getCompanyIds() == null || companyParam.getCompanyIds().length == 0)
      throw new BusinessException(EnumResult.UNKONW_PK_ERROR);
    //查询有系统的单位列表
    List<CompanyListResult> existSysList = this.companyMapper.
        selectAllCompanyExistSysByParam(companyParam);
    //用于提醒用户哪些单位下有系统
    List<CompanyListResult> failedDeleteList = null;
    //有单位下存在系统时
    if (existSysList != null) {
      failedDeleteList = new ArrayList<CompanyListResult>();
      int existSysListSize = existSysList.size();
      String[] companyIds = companyParam.getCompanyIds();
      if(companyIds.length > existSysListSize){
        //准备新的companyIds来删除
        String[] newCompanyIds = new String[companyIds.length - existSysListSize];
        //用于对newCompanyIds计数
        int newCompanyIdsI = 0;
        for (int i = 0; i < companyIds.length; i++) {
          boolean deleteIt = true;
          for (CompanyListResult companyListResult : existSysList) {
            //存在系统
            if (companyListResult.getCompanyId().equals(companyIds[i])) {
              failedDeleteList.add(companyListResult);
              deleteIt = false;
              break;
            }
          }
          if (deleteIt) {
            newCompanyIds[newCompanyIdsI] = companyIds[i];
            newCompanyIdsI++;
          }
        }
        //数量不对时，不删除
        if (newCompanyIds.length != 0 && newCompanyIdsI != newCompanyIds.length - 1) {
          companyParam.setCompanyIds(null);
          failedDeleteList = null;
        }else{
          companyParam.setCompanyIds(newCompanyIds);
        }
      }else if(companyIds.length == existSysListSize){
        //全部单位都存在系统
        for (CompanyListResult companyListResult : existSysList) {
          failedDeleteList.add(companyListResult);
        }
        companyParam.setCompanyIds(null);
      }
      
    }
    if(companyParam.getCompanyIds() != null && companyParam.getCompanyIds().length > 0){
      this.companyMapper.updateCompanyByCompanyIds(companyParam);
    }
    return failedDeleteList;
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
    
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return new ArrayList<CompanyListResult>();
    }else{
      List<CompanyListResult> companyListResultList  = new ArrayList<CompanyListResult>();
      
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      case "0":
        break;
      case "1":
        // 获得响应列表数据
        companyListResultList = 
            this.companyMapper.selectCompanyName(companyParam);
        break;
      case "2":
        companyParam.setPlateList(organizationApiResult.getNameList());
        companyListResultList =  
        this.companyMapper.selectCompanyName(companyParam);
        break;
      case "3":
        companyParam.setCompanyList(organizationApiResult.getCodeList());
        companyListResultList =  
        this.companyMapper.selectCompanyName(companyParam);
        break;

      default:
        break;
      }
      return companyListResultList;
    }
  }

  /**
   * 返回单位列表信息（带板块）
   */
  @Override
  public List<OrganizationApiCascaderResult> queryCompanyListByName(
      CompanyParam companyParam) {
    List<OrganizationApiCascaderResult> organizationResultList = new 
        ArrayList<OrganizationApiCascaderResult>();
    
    List<CompanyListResult> companyListResultList  = new ArrayList<CompanyListResult>();
    
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    if(organizationApiResult==null){
      return new ArrayList<OrganizationApiCascaderResult>();
    }else{
      //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
      switch (organizationApiResult.getResultType()) {
      case "0":
        break;
      case "1":
//        // 获得响应列表数据
        companyListResultList = 
            this.companyMapper.selectCompanyName(companyParam);
        break;
      case "2":
        companyParam.setPlateList(organizationApiResult.getNameList());
        companyListResultList =  
        this.companyMapper.selectCompanyName(companyParam);
        break;
      case "3":
        companyParam.setCompanyList(organizationApiResult.getCodeList());
        companyListResultList =  
        this.companyMapper.selectCompanyName(companyParam);
        break;

      default:
        break;
      }
    }
    HashMap<String, String> map = new HashMap<String, String>();
    for (CompanyListResult companyListResult : companyListResultList) {
      map.put(companyListResult.getFkPlateType(), companyListResult.getFkPlateType());
    }
    
    //返回list
    for (String strKay : map.keySet()) {
      OrganizationApiCascaderResult organizationResult = new OrganizationApiCascaderResult();
      
      List<OrganizationApiCascader> organizationList = new ArrayList<OrganizationApiCascader>();
      for (CompanyListResult companyListResult : companyListResultList) {
        if(strKay.equals(companyListResult.getFkPlateType())){
          OrganizationApiCascader organization= new OrganizationApiCascader();
          organization.setLabel(companyListResult.getCompanyName());
          //因页面需要单位名称和code,遂需用&来把单位名称得和code同时返回到页面
          organization.setValue(companyListResult.getCompanyCode());
          organizationList.add(organization);
        }
      }
      organizationResult.setValue(strKay);
      organizationResult.setLabel(map.get(strKay));
      organizationResult.setChildren(organizationList);
      organizationResultList.add(organizationResult);
    }
    return organizationResultList;
  }
}
