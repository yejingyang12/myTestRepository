/**
 * @Title ApiCheckHandleServiceImpl.java
 * @Package com.sinopec.smcc.cpro.api.service.impl
 * @Description: TODO:
 * @author eric
 * @date 2018年7月19日下午3:02:10
 * @version V1.0
 */
package com.sinopec.smcc.cpro.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinopec.smcc.cpro.api.service.ApiCheckHandleService;
import com.sinopec.smcc.cpro.review.entity.CheckParam;
import com.sinopec.smcc.cpro.review.server.CheckService;

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
  
  private CheckService checkServiceImpl;

  @Override
  public List<String> saveCheck(CheckParam checkParam) {
    
    return null;
  }

}
