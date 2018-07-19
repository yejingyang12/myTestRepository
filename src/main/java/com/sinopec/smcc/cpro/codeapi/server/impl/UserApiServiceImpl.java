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

import com.sinopec.siam.agent.common.SSOPrincipal;
import com.sinopec.smcc.depends.ubs.dto.UserDTO;
import com.sinopec.smcc.depends.ubs.util.UbsFeignTemplate;
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
  private UbsFeignTemplate ubsFeignTemplate;
  
  @Value("${appId}")
  private String appId;
  
  @Override
  public UserDTO getUserInfo() {
    HttpServletRequest request = 
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    HttpSession session = request.getSession();
    String userId = (String) session.getAttribute("userId");
    UserDTO userDTO = new UserDTO();
    if (StringUtils.isBlank(userId)) {
      SSOPrincipal ssoPrincipal = (SSOPrincipal)session.getAttribute(SSOPrincipal.NAME_OF_SESSION_ATTR);
      userDTO = ubsFeignTemplate.getUserBySsoUid(ssoPrincipal.getUid());
    } else {
      userDTO = ubsFeignTemplate.getUserByUserId(userId);
    }
    if (userDTO==null) {
      userDTO = new UserDTO();
    }
    session.removeAttribute("userId");
    return userDTO;
  }

}
