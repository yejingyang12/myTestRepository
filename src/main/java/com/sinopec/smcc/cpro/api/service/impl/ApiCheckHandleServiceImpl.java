/**
 * @Title ApiCheckHandleServiceImpl.java
 * @Package com.sinopec.smcc.cpro.api.service.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午3:02:10
 * @version V1.0
 */
package com.sinopec.smcc.cpro.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.sinopec.smcc.base.exception.classify.BusinessException;
import com.sinopec.smcc.cpro.api.service.ApiCheckHandleService;
import com.sinopec.smcc.cpro.codeapi.entity.JurisdictionDataResult;
import com.sinopec.smcc.cpro.codeapi.server.JurisdictionApiService;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;
import com.sinopec.smcc.cpro.review.entity.CheckListResult;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;
import com.sinopec.smcc.cpro.tools.CommonParam;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;

/**
 * @Title ApiCheckHandleServiceImpl.java
 * @Package com.sinopec.smcc.cpro.api.service.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午3:02:10
 * @version V1.0
 */
@Service
public class ApiCheckHandleServiceImpl implements ApiCheckHandleService{
  
  @Autowired
  private CheckService checkServiceImpl;
  @Autowired
  private JurisdictionApiService jurisdictionApiServiceImpl;
  
  @Autowired
  private UserApiService userApiServiceImpl;
  
  @Override
  public List<CheckListResult> queryCheckList(HttpServletRequest request, String userId) 
      throws BusinessException {
    CheckParam checkParam = new CheckParam();
    checkParam.setHandlingState(1);
    request.getSession().setAttribute("userId", userId);
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    if (organizationApiResult == null||"0".equals(organizationApiResult.getResultType())) {
      return new ArrayList<CheckListResult>();
    }
    
    List<String> permssionsList = organizationApiResult.getPermssions();
    
    String examinStatus = "";
    
    if (permssionsList.contains(CommonParam.S_STR_PERMIT_PARAM_ENTERPRISE_AUDIT)) {
      examinStatus = "1";
    } else if (permssionsList.contains(CommonParam.S_STR_PERMIT_PARAM_HEADQUARTERS_AUDIT)) {
      examinStatus = "2";
    } else {
      return new ArrayList<CheckListResult>();
    }
    
    PageInfo<CheckListResult> pageInfo = checkServiceImpl.queryCheckList(checkParam);
    
    List<CheckListResult> checkList = pageInfo.getList();
    for (int i = 0; i < checkList.size(); i++) {
      if (!examinStatus.equals(checkList.get(i).getFkExaminStatus())) {
        checkList.remove(i);
      }
    }
    request.getSession().removeAttribute("userId");
    return checkList;
  }

  @Override
  public List<String> saveCheck(List<CheckParam> checkList, 
      String userId, HttpServletRequest request) throws BusinessException{
    if (checkList == null || checkList.size()==0) {
      return new ArrayList<String>();
    }
    request.getSession().setAttribute("userId", userId);
    
    JurisdictionDataResult organizationApiResult = 
        this.jurisdictionApiServiceImpl.queryDataJurisdictionApi();
    
    UserDTO userDTO = userApiServiceImpl.getUserInfo();
    
    if (organizationApiResult==null||"0".equals(organizationApiResult.getResultType())) {
      return new ArrayList<String>();
    }
    
    String examinStatus = "";
    
    List<String> permssionsList = organizationApiResult.getPermssions();
    
    if (permssionsList.contains(CommonParam.S_STR_PERMIT_PARAM_ENTERPRISE_AUDIT)) {
      examinStatus = "1";
    } else if (permssionsList.contains(CommonParam.S_STR_PERMIT_PARAM_HEADQUARTERS_AUDIT)) {
      examinStatus = "2";
    } else {
      return new ArrayList<String>();
    }
    
    for (CheckParam checkParam : checkList) {
      switch (checkParam.getFkBusinessNode()) {
      case "1":
        if ("1".equals(examinStatus)) {
          this.checkServiceImpl.saveGradCheck(userDTO.getUserName(), checkParam);
        } else {
          this.checkServiceImpl.saveHeadGradCheck(userDTO.getUserName(), checkParam);
        }
        break;
      case "2":
        if ("2".equals(examinStatus)) {
          this.checkServiceImpl.saveCancelRecordsCheck(userDTO.getUserName(), checkParam);
        }
        break;
      case "3":
        if ("1".equals(examinStatus)) {
          this.checkServiceImpl.saveGradChangeCheck(userDTO.getUserName(), checkParam);
        } else {
          this.checkServiceImpl.saveHeadGradChangeCheck(userDTO.getUserName(), checkParam);
        }
        break;

      default:
        break;
      }
    }
    request.getSession().removeAttribute("userId");
    return null;
  }

}
