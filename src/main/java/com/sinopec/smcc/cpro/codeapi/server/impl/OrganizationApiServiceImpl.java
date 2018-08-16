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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApi;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiCascaderResult;
import com.sinopec.smcc.cpro.codeapi.entity.OrganizationApiParam;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.OrganizationApiService;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.mapper.CompanyMapper;
import com.sinopec.smcc.depends.ubs.dto.OrganizationDTO;
import com.sinopec.smcc.depends.ubs.util.UbsConstants.OrganizationLevelEnum;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

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
  private UbsTemplate ubsTemplate;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  @Autowired
  private CompanyMapper companyMapper;
  
  @Override
  public List<OrganizationApiCascaderResult> queryOrganizationApi(
      OrganizationApiParam organizationParam) throws BusinessException {
    List<OrganizationApiCascaderResult> organizationResultList = new ArrayList<OrganizationApiCascaderResult>();
//    List<OrgResult>  organizationApiList = ubsUtil.getOrgRoot();
//    System.out.println(JSON.toJSONString(list));
//    //判断总部数据开始
//    if (organizationApiList!=null&&organizationApiList.size()>0) {
//      for (OrgResult orgResult : organizationApiList) {
//        //2级循环（板块）
//        List<OrgResult> organizationApiPlateList = ubsUtil.getOrgNode(orgResult.getOrgId());
//        if (organizationApiPlateList!=null&&organizationApiPlateList.size()>0) {
//          for (OrgResult orgResultPlate : organizationApiPlateList) {
//            OrganizationApiCascaderResult organizationResult = new OrganizationApiCascaderResult();
//            List<OrgResult> organizationApiCompanyList = ubsUtil.getOrgNode(orgResultPlate.getOrgId());
//            //3级循环（单位）
//            List<OrganizationApiCascader> organizationList = new ArrayList<OrganizationApiCascader>();
//            if (organizationApiCompanyList!=null&&organizationApiCompanyList.size()>0) {
//              for (OrgResult orgResultCompany : organizationApiCompanyList) {
//                OrganizationApiCascader organization= new OrganizationApiCascader();
//                organization.setLabel(orgResultCompany.getOrgName());
//                //因页面需要单位名称和code,遂需用&来把单位名称得和code同时返回到页面
//                organization.setValue(orgResultCompany.getOrgCode());
//                organizationList.add(organization);
//              }
//            }
//            organizationResult.setValue(orgResultPlate.getOrgCode());
//            organizationResult.setLabel(orgResultPlate.getOrgName());
//            organizationResult.setChildren(organizationList);
//            organizationResultList.add(organizationResult);
//          }
//        }
//      }
//    }
    return organizationResultList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationCode(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    
    List<OrganizationDTO>  organizationApiList = 
        this.ubsTemplate.getOrgsByDefaultTypeCodeAndLevelCode(
            OrganizationLevelEnum.ORG_SMCC_LEVEL3);
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrganizationDTO organizationDTO : organizationApiList) {
        OrganizationApi organization= new OrganizationApi();
        organization.setOrgCode(organizationDTO.getOrgCode());
        organization.setOrgName(organizationDTO.getOrgName());
        organizationList.add(organization);
      }
    }
    return organizationList;
  }

  @Override
  public List<OrganizationApi> queryOrgUnitForKeyOrganizationCode(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    //用户下所有的单位
    List<OrganizationDTO>  organizationApiList = 
        this.ubsTemplate.getOrgsByDefaultTypeCodeAndLevelCode(
            OrganizationLevelEnum.ORG_SMCC_LEVEL3);
    //权限
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    //
    List<CompanyListResult> companyListResultList  = new ArrayList<CompanyListResult>();
  //数据类型：0:无权限；1：全部权限；2：板块；3：企业；
    CompanyParam companyParam = new CompanyParam();
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
    List<String> companyCodeList = new ArrayList<String>();
    for(CompanyListResult companyListResult:companyListResultList){
      companyCodeList.add(companyListResult.getCompanyCode());
    }
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrganizationDTO organizationDTO : organizationApiList) {
         if(!companyCodeList.contains(organizationDTO.getOrgCode())){
          OrganizationApi organization= new OrganizationApi();
          organization.setOrgCode(organizationDTO.getOrgCode());
          organization.setOrgName(organizationDTO.getOrgName());
          if(StringUtils.isNotBlank(organizationDTO.getEmail())){
            organization.setEmail(organizationDTO.getEmail());
          }else{
            organization.setEmail("");
          }
          if(StringUtils.isNoneBlank(organizationDTO.getAddress())){
            organization.setAddress(organizationDTO.getAddress());
          }else{
            organization.setAddress("");
          }
          organizationList.add(organization);   
         }
      }
    }
    return organizationList;
  }

  @Override
  public List<OrganizationApi> queryOrgForKeyOrganizationName(
      OrganizationApiParam organizationApiParam) throws BusinessException {
    List<OrganizationApi> organizationList = new ArrayList<OrganizationApi>();
    
    List<OrganizationDTO>  organizationApiList = 
        this.ubsTemplate.getOrgsByDefaultTypeCodeAndLevelCode(
            OrganizationLevelEnum.ORG_SMCC_LEVEL3);
    //判断总部数据开始
    if (organizationApiList!=null&&organizationApiList.size()>0) {
      for (OrganizationDTO organizationDTO : organizationApiList) {
        OrganizationApi organization= new OrganizationApi();
        organization.setOrgCode(organizationDTO.getOrgName());
        organization.setOrgName(organizationDTO.getOrgName());
        organizationList.add(organization);
      }
    }
    return organizationList;
  }
}
