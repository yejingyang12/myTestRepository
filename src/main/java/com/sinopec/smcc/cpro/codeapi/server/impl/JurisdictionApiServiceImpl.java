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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.company.entity.CompanyListResult;
import com.sinopec.smcc.cpro.company.entity.CompanyParam;
import com.sinopec.smcc.cpro.company.mapper.CompanyMapper;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeListResult;
import com.sinopec.smcc.cpro.systemcode.entity.SystemCodeParam;
import com.sinopec.smcc.cpro.systemcode.mapper.SystemCodeMapper;
import com.sinopec.smcc.depends.ubs.dto.AuthorizationDTO;
import com.sinopec.smcc.depends.ubs.dto.PermissionDTO;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsTemplate;

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
  private UbsTemplate ubsTemplate;
  
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

  /**
   * 废弃
   */
  @Override
  public JurisdictionDataResult queryDataJurisdictionApi1() {
    JurisdictionDataResult jurisdictionDataResult = new JurisdictionDataResult();
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    List<AuthorizationDTO> jsonMenu = this.ubsTemplate.
        getDataAuthByUserIdAfterChange(userDTO.getUserId()+"");
//    JSONArray json = JSONArray.fromObject(jsonMenu); 
//    String str = json.toString();//将json对象转换为字符串
//    System.out.println(str);
//    List<AuthorizationDTO> dataAuthByUserIdAfterChange = this.ubsTemplate.
//        getDataAuthByUserIdAfterChange(userDTO.getUserId()+""); 
//    JSONArray json1 = JSONArray.fromObject(dataAuthByUserIdAfterChange); 
//    String str1 = json1.toString();//将json对象转换为字符串
//    System.out.println(str1);
    
    List<String> codeList = new ArrayList<String>();
    List<String> nameList = new ArrayList<String>();
    List<String> permssionsList = new ArrayList<String>();
    
    for (AuthorizationDTO authorizationDTO : jsonMenu) {
      //增加权限许可
      if (authorizationDTO.getPermissionList() != null) {
        List<PermissionDTO> permissionDTOList = authorizationDTO.getPermissionList();
        for (PermissionDTO permissionDTO : permissionDTOList) {
          permssionsList.add(permissionDTO.getPermcode());
        }
      }
      //通过等号切分
      String[] rules = authorizationDTO.getRule().split("=");
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
        } else if ("TPF".equals(rules[0].trim())){
          if (org.equals(rules[1].trim())) {
            String[] orgCodes = userDTO.getOrgCode().split(",");
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
    jurisdictionDataResult.setPermssions(permssionsList);
    jurisdictionDataResult.setCodeList(codeList);
    return jurisdictionDataResult;
  }
  
  /**
   * @Descrption  查询指定用户的数据权限
   * @author yejingyang
   * @date 2018年9月21日上午10:40:09
   * @param userDTO 指定的用户
   * @return
   */
  @Override
  public JurisdictionDataResult queryDataJurisdictionApi(UserDTO userDTO) {
    JurisdictionDataResult jurisdictionDataResult = new JurisdictionDataResult();
    List<AuthorizationDTO> jsonMenu = this.ubsTemplate.
        getDataAuthByUserIdAfterChange(userDTO.getUserId()+"");
//    JSONArray json = JSONArray.fromObject(jsonMenu); 
//    String str = json.toString();//将json对象转换为字符串
//    System.out.println(str);
    boolean booValue = false;
    List<String> codeList = new ArrayList<String>();
    List<String> nameList = new ArrayList<String>();
    List<String> permssionsList = new ArrayList<String>();
    Map<String,List<String>> permitMap = new HashMap<String,List<String>>();
    for (AuthorizationDTO authorizationDTO : jsonMenu) {
      
      List<String> permitList = new ArrayList<String>();
      //增加权限许可
      if (authorizationDTO.getPermissionList() != null) {
        List<PermissionDTO> permissionDTOList = authorizationDTO.getPermissionList();
        for (PermissionDTO permissionDTO : permissionDTOList) {
          //申请变更权限
          if(permissionDTO.getPermcode().equals("0102010106")){
            booValue = true;
          }
          permitList.add(permissionDTO.getPermcode());
          permssionsList.add(permissionDTO.getPermcode());
        }
      }
      //通过等号切分
      String[] rules = authorizationDTO.getRule().split("or");
      if (rules.length>0) {
        for (int i=0;i<rules.length;i++) {
          
          String[] orgInfo = rules[i].trim().split("=");
          if(orgInfo.length<=1){
            continue;
          }
          String orgCode = orgInfo[1].replace("'", "");
          if (orgCode.trim().length()>8) {
            orgCode = orgCode.trim().substring(0, 8);
          }else {
            orgCode = orgCode.trim();
          }
          codeList.add(orgCode);
          if(booValue){
            nameList.add(orgCode);
          }
        }
        for (String strPermit : permitList) {
          if(strPermit.equals("01020101")||strPermit.equals("01020102")
              ||strPermit.equals("01020103")){
            continue;
          }
          List<String> permit = new ArrayList<String>();
          for (int i=0;i<rules.length;i++) {
            
            String[] orgInfo = rules[i].trim().split("=");
            if(orgInfo.length<=1){
              continue;
            }
            String orgCode = orgInfo[1].replace("'", "");
            if (orgCode.trim().length()>8) {
              orgCode = orgCode.trim().substring(0, 8);
            }else {
              orgCode = orgCode.trim();
            }
            permit.add(orgCode);
          }
          permitMap.put(strPermit, permit);
        }
        jurisdictionDataResult.setResultType("3");
      }else{
        jurisdictionDataResult.setResultType("0");
      }
      booValue = false;
    }
    jurisdictionDataResult.setPermitMap(permitMap);
    jurisdictionDataResult.setPermssions(permssionsList);
    jurisdictionDataResult.setCodeList(codeList);
    jurisdictionDataResult.setNameList(nameList);
    return jurisdictionDataResult;
  }
  @Override
  public JurisdictionDataResult queryDataJurisdictionApi() {
    JurisdictionDataResult jurisdictionDataResult = new JurisdictionDataResult();
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    List<AuthorizationDTO> jsonMenu = this.ubsTemplate.
        getDataAuthByUserIdAfterChange(userDTO.getUserId()+"");
//    JSONArray json = JSONArray.fromObject(jsonMenu); 
//    String str = json.toString();//将json对象转换为字符串
//    System.out.println(str);
    boolean booValue = false;
    List<String> codeList = new ArrayList<String>();
    List<String> nameList = new ArrayList<String>();
    List<String> permssionsList = new ArrayList<String>();
    Map<String,List<String>> permitMap = new HashMap<String,List<String>>();
    for (AuthorizationDTO authorizationDTO : jsonMenu) {
      
      List<String> permitList = new ArrayList<String>();
      //增加权限许可
      if (authorizationDTO.getPermissionList() != null) {
        List<PermissionDTO> permissionDTOList = authorizationDTO.getPermissionList();
        for (PermissionDTO permissionDTO : permissionDTOList) {
          //申请变更权限
          if(permissionDTO.getPermcode().equals("0102010106")){
            booValue = true;
          }
          permitList.add(permissionDTO.getPermcode());
          permssionsList.add(permissionDTO.getPermcode());
        }
      }
      //通过等号切分
      String[] rules = authorizationDTO.getRule().split("or");
      if (rules.length>0) {
        for (int i=0;i<rules.length;i++) {
          
          String[] orgInfo = rules[i].trim().split("=");
          if(orgInfo.length<=1){
            continue;
          }
          String orgCode = orgInfo[1].replace("'", "");
          if (orgCode.trim().length()>8) {
            orgCode = orgCode.trim().substring(0, 8);
          }else {
            orgCode = orgCode.trim();
          }
          codeList.add(orgCode);
          if(booValue){
            nameList.add(orgCode);
          }
        }
        for (String strPermit : permitList) {
          if(strPermit.equals("01020101")||strPermit.equals("01020102")
              ||strPermit.equals("01020103")){
            continue;
          }
          List<String> permit = new ArrayList<String>();
          for (int i=0;i<rules.length;i++) {
            
            String[] orgInfo = rules[i].trim().split("=");
            if(orgInfo.length<=1){
              continue;
            }
            String orgCode = orgInfo[1].replace("'", "");
            if (orgCode.trim().length()>8) {
              orgCode = orgCode.trim().substring(0, 8);
            }else {
              orgCode = orgCode.trim();
            }
            permit.add(orgCode);
          }
          permitMap.put(strPermit, permit);
        }
        jurisdictionDataResult.setResultType("3");
      }else{
        jurisdictionDataResult.setResultType("0");
      }
      booValue = false;
    }
    jurisdictionDataResult.setPermitMap(permitMap);
    jurisdictionDataResult.setPermssions(permssionsList);
    jurisdictionDataResult.setCodeList(codeList);
    jurisdictionDataResult.setNameList(nameList);
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
   * @Descrption 获取单位Code
   * @author dongxu
   * @date 2018年7月18日下午8:12:31
   * @return
   */
  @Override
  public String getCompanyCode() {
    //获得用户信息
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    String[] orgCodes = userDTO.getOrgCode().split(",");
    String companyCode = "";
    for (String orgCode : orgCodes) {
      if (orgCode.trim().length()>8) {
        companyCode = orgCode.trim().substring(0, 8);
      }else {
        companyCode = orgCode.trim();
      }
    }
    return companyCode;
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
