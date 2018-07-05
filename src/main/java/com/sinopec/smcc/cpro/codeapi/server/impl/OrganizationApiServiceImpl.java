/**
 * @Title OrganizationApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:55
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascader;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;
import com.sinopec.smcc.cpro.codeapi.server.OrganizationApiService;
import com.sinopec.smcc.depends.ubs.model.OrgResult;
import com.sinopec.smcc.depends.ubs.util.UbsUtil;

/**
 * @Title OrganizationApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月9日下午1:38:55
 * @version V1.0
 */
@Service
public class OrganizationApiServiceImpl implements OrganizationApiService{
  
  @Autowired
  private UbsUtil ubsUtil;

  @Override
  public List<OrganizationApiCascaderResult> queryOrganizationApi(
      OrganizationApiParam organizationParam) throws BusinessException {
    List<OrganizationApiCascaderResult> organizationResultList = new ArrayList<OrganizationApiCascaderResult>();
    List<OrgResult>  organizationApiList = ubsUtil.getOrgRoot();
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrgResult orgResult : organizationApiList) {
        //2级循环（板块）
        List<OrgResult> organizationApiPlateList = ubsUtil.getOrgNode(orgResult.getOrgId());
        if (organizationApiPlateList!=null&&organizationApiPlateList.size()>0) {
          for (OrgResult orgResultPlate : organizationApiPlateList) {
            OrganizationApiCascaderResult organizationResult = new OrganizationApiCascaderResult();
            List<OrgResult> organizationApiCompanyList = ubsUtil.getOrgNode(orgResultPlate.getOrgId());
            //3级循环（单位）
            List<OrganizationApiCascader> organizationList = new ArrayList<OrganizationApiCascader>();
            if (organizationApiCompanyList!=null&&organizationApiCompanyList.size()>0) {
              for (OrgResult orgResultCompany : organizationApiCompanyList) {
                OrganizationApiCascader organization= new OrganizationApiCascader();
                organization.setLabel(orgResultCompany.getOrgName());
                //因页面需要单位名称和code,遂需用&来把单位名称得和code同时返回到页面
                organization.setValue(orgResultCompany.getOrgCode());
                organizationList.add(organization);
              }
            }
            organizationResult.setValue(orgResultPlate.getOrgCode());
            organizationResult.setLabel(orgResultPlate.getOrgName());
            organizationResult.setChildren(organizationList);
            organizationResultList.add(organizationResult);
          }
        }
      }
    }
    return organizationResultList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationCode(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    
    List<OrgResult>  organizationApiList = ubsUtil.getOrgRoot();
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrgResult orgResult : organizationApiList) {
        //2级循环（板块）
        List<OrgResult> organizationApiPlateList = ubsUtil.getOrgNode(orgResult.getOrgId());
        if (organizationApiPlateList!=null&&organizationApiPlateList.size()>0) {
          for (OrgResult orgResultPlate : organizationApiPlateList) {
            List<OrgResult> organizationApiCompanyList = ubsUtil.getOrgNode(orgResultPlate.getOrgId());
            //3级循环（单位）
            if (organizationApiCompanyList!=null&&organizationApiCompanyList.size()>0) {
              for (OrgResult orgResultCompany : organizationApiCompanyList) {
                OrganizationApi organization= new OrganizationApi();
                organization.setOrgCode(orgResultCompany.getOrgCode());
                organization.setOrgName(orgResultCompany.getOrgName());
                organizationList.add(organization);
              }
            }
          }
        }
      }
    }
    return organizationList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationName(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    
    List<OrgResult>  organizationApiList = ubsUtil.getOrgRoot();
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrgResult orgResult : organizationApiList) {
        //2级循环（板块）
        List<OrgResult> organizationApiPlateList = ubsUtil.getOrgNode(orgResult.getOrgId());
        if (organizationApiPlateList!=null&&organizationApiPlateList.size()>0) {
          for (OrgResult orgResultPlate : organizationApiPlateList) {
            List<OrgResult> organizationApiCompanyList = ubsUtil.getOrgNode(orgResultPlate.getOrgId());
            //3级循环（单位）
            if (organizationApiCompanyList!=null&&organizationApiCompanyList.size()>0) {
              for (OrgResult orgResultCompany : organizationApiCompanyList) {
                OrganizationApi organization= new OrganizationApi();
                organization.setOrgCode(orgResultCompany.getOrgName());
                organization.setOrgName(orgResultCompany.getOrgName());
                organizationList.add(organization);
              }
            }
          }
        }
      }
    }
    return organizationList;
  }
}
