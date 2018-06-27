/**
 * @Title UserApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午10:34:00
 * @version V1.0
 */
package com.sinopec.smcc.cpro.codeapi.server.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.sinopec.siam.agent.common.SSOPrincipal;
import com.sinopec.smcc.common.ubs.client.UbsClient;
import com.sinopec.smcc.cpro.codeapi.entity.UserInfoResult;
import com.sinopec.smcc.cpro.codeapi.server.UserApiService;

/**
 * @Title UserApiServiceImpl.java
 * @Package com.sinopec.smcc.cpro.codeapi.server.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年6月25日下午10:34:00
 * @version V1.0
 */
@Service
public class UserApiServiceImpl implements UserApiService{
  
  @Autowired
  UbsClient ubsClient;
  
  @Value("${appId}")
  private String appId;
  
  @Override
  public UserInfoResult getUserInfo() {
    HttpServletRequest request = 
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    SSOPrincipal ssoPrincipal = (SSOPrincipal)session.getAttribute(SSOPrincipal.NAME_OF_SESSION_ATTR);
    String userInfo = ubsClient.getUserByAccount(appId,"1", ssoPrincipal.getUid());
    UserInfoResult userInfoResult = new UserInfoResult();
    if (StringUtils.isNotBlank(userInfo)) {
      userInfoResult = JSON.parseObject(
          userInfo.replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\""), 
          UserInfoResult.class);
    }
    return userInfoResult;
  }

}
