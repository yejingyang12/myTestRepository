/**
 * @Title JurisdictionApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:21:05
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.entity.UserInfoResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.mapper.CompanyMapper;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.mapper.SystemCodeMapper;
import com.sinopec.smcc.depends.ubs.client.UbsClient;
import com.sinopec.smcc.depends.ubs.model.AuthResult;
import com.sinopec.smcc.depends.ubs.util.UbsUtil;

/**
 * @Title JurisdictionApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月20日下午3:21:05
 * @version V1.0
 */
@Service
public class JurisdictionApiServiceImpl implements JurisdictionApiService{
  
  @Autowired
  private UbsClient ubsClient;
  @Autowired
  private UbsUtil ubsUtil;
  
  @Value("${appId}")
  private String appId;
  
  @Value("${redisId}")
  private String redisId;
  
  @Value("${wildcard.org}")
  private String org;
  @Value("${wildcard.all}")
  private String all;
  @Value("${wildcard.none}")
  private String none;
  
  @Autowired
  private UserApiService userApiServiceImpl;
  
  @Autowired
  private CompanyMapper companyMapperImpl;
  
  @Autowired
  private SystemCodeMapper systemCodeMapperImpl;
  
  private static Map<String, String> COMPANY_NAME_MAP;
  private static Map<String, String> PLATE_NAME_MAP;

  @Override
  public JurisdictionDataResult queryDataJurisdictionApi() {
    JurisdictionDataResult jurisdictionDataResult = new JurisdictionDataResult();
    AuthResult jsonMenu = this.ubsUtil.getDataPermissions(redisId);
    String rulesTemp = jsonMenu.getRule();
    if (StringUtils.isBlank(rulesTemp)) {
      return jurisdictionDataResult;
    }
    //通过Or切分多个
    String[] rulesOr = rulesTemp.split("Or");
    
    List<String> codeList = new ArrayList<String>();
    List<String> nameList = new ArrayList<String>();
    
    for (String ruleOr : rulesOr) {
    //通过等号切分
      String[] rules = ruleOr.split("=");
      if (rules.length==2) {
        //如果是两个，代表数据正确，去掉左右空格
        if ("orgCode".equals(rules[0].trim())) {
          //去掉空格后通过逗号切分
          String[] rulesValue = rules[1].trim().split(",");
          //强转数字，如果是数字则为组织机构
          try {
            Integer.parseInt(rules[1].trim());
            for (String rule : rulesValue) {
              //返回单位code
              codeList.add(rule.trim());
              //返回单位名称
              String companyName = this.getCompanyName(rule.trim());
              //获得单位数据
              nameList.add(companyName);
            }
            jurisdictionDataResult.setResultType("3");
          } catch (NumberFormatException e) {
            //如果不是数字则为板块
            for (String rule : rulesValue) {
              //返回单位code
              codeList.add(rule.trim());
              //返回单位名称
              String plateName = this.getPlateName(rule.trim());
              //获得单位数据
              nameList.add(plateName);
            }
            jurisdictionDataResult.setResultType("2");
          }
        } else if ("组织通配符".equals(rules[0].trim())){
          //获得用户信息
          UserInfoResult userInfoResult = userApiServiceImpl.getUserInfo();
          if (org.equals(rules[1].trim())) {
            String[] orgCodes = userInfoResult.getData().getOrgCode().split(",");
            for (String orgCode : orgCodes) {
              if (orgCode.trim().length()>8) {
                orgCode = orgCode.trim().substring(0, 8);
              }else {
                orgCode = orgCode.trim();
              }
              codeList.add(orgCode.trim());
              //返回单位名称
              String companyName = this.getCompanyName(orgCode);
              //获得单位数据
              nameList.add(companyName);
            }
            jurisdictionDataResult.setResultType("3");
          } else if (all.equals(rules[1].trim())) {
            jurisdictionDataResult.setResultType("1");
          } else if (none.equals(rules[1].trim())) {
            jurisdictionDataResult.setResultType("0");
          } else {
            jurisdictionDataResult.setResultType("0");
          }
        }
      }
    }
    jurisdictionDataResult.setCodeList(codeList);
    return jurisdictionDataResult;
  }

  /**
   * @Descrption
   * @author eric
   * @date 2018年6月26日下午1:11:34
   * @param trim
   * @return
   */
  private String getCompanyName(String rules) {
    if (COMPANY_NAME_MAP!=null&&COMPANY_NAME_MAP.size()>0) {
      return COMPANY_NAME_MAP.get(rules);
    }
    COMPANY_NAME_MAP = new HashMap<String, String>();
    List<CompanyListResult> companyList = this.companyMapperImpl.selectCompanyName(new CompanyParam());
    for (CompanyListResult companyListResult : companyList) {
      COMPANY_NAME_MAP.put(companyListResult.getCompanyCode(), companyListResult.getCompanyName());
    }
    return COMPANY_NAME_MAP.get(rules);
  }
  
  /**
   * @Descrption
   * @author eric
   * @date 2018年6月26日下午1:39:32
   * @param trim
   * @return
   */
  private String getPlateName(String rules) {
    if (PLATE_NAME_MAP!=null&&PLATE_NAME_MAP.size()>0) {
      return PLATE_NAME_MAP.get(rules);
    }
    PLATE_NAME_MAP = new HashMap<String, String>();
    SystemCodeParam systemCodeParam = new SystemCodeParam();
    systemCodeParam.setCodeType("6");
    List<SystemCodeListResult> plateList = this.systemCodeMapperImpl.selectSystemCodeForByParamKeySystemCode(systemCodeParam);
    for (SystemCodeListResult systemCodeListResult : plateList) {
      PLATE_NAME_MAP.put(systemCodeListResult.getSystemCode(), systemCodeListResult.getCodeName());
    }
    return PLATE_NAME_MAP.get(rules);
  }
}
