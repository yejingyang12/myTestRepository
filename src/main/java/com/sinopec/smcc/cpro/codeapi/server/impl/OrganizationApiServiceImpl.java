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

import org.springframework.stereotype.Service;

import com.sinopec.smcc.common.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascader;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;
import com.sinopec.smcc.cpro.codeapi.server.OrganizationApiService;

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

  @Override
  public List<OrganizationApiCascaderResult> queryOrganizationApi(
      OrganizationApiParam organizationParam) throws BusinessException {
    List<OrganizationApiCascaderResult> organizationResultList = new ArrayList<OrganizationApiCascaderResult>();
    for (int i = 0; i < 10; i++) {
      List<OrganizationApiCascader> organizationList = new ArrayList<OrganizationApiCascader>();
      OrganizationApiCascaderResult organizationResult = new OrganizationApiCascaderResult();
      for (int j = 0; j < 20; j++) {
        OrganizationApiCascader organization= new OrganizationApiCascader();
        organization.setLabel("单位名称"+i+""+j);
        //因页面需要单位名称和code,遂需用&来把单位名称得和code同时返回到页面
        organization.setValue(i+""+j);
        organizationList.add(organization);
      }
      organizationResult.setValue(String.valueOf(i+1));
      organizationResult.setLabel("板块"+i);
      organizationResult.setChildren(organizationList);
      organizationResultList.add(organizationResult);
    }
    return organizationResultList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationCode(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    for (int j = 0; j < 20; j++) {
      OrganizationApi organization= new OrganizationApi();
      organization.setOrgCode(""+j);
      organization.setOrgName("单位名称"+j);
      organizationList.add(organization);
    }
    return organizationList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationName(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    for (int j = 0; j < 20; j++) {
      OrganizationApi organization= new OrganizationApi();
      organization.setOrgCode("单位名称"+j);
      organization.setOrgName("单位名称"+j);
      organizationList.add(organization);
    }
    return organizationList;
  }
  
}
